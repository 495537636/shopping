/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserInfoController
 * @Prject: shopping
 * @Package: com.sunshine.shopping.web
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/3 13:39
 * @version: V1.0
 */

package com.sunshine.shopping.web;

import com.sunshine.shopping.common.util.ValidateUtil;
import com.sunshine.shopping.common.web.BaseController;
import com.sunshine.shopping.model.dto.UserInfoRequestDTO;
import com.sunshine.shopping.model.dto.UserInfoResponseDTO;
import com.sunshine.shopping.response.ResponseResult;
import com.sunshine.shopping.response.ResponseUtil;
import com.sunshine.shopping.service.UserInfoService;
import com.sunshine.shopping.util.CaptchaUtil;
import com.sunshine.shopping.util.MessageUtil;
import com.sunshine.shopping.util.RedisUtil;
import com.sunshine.shopping.util.StaticUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sunshine.shopping.response.ResponseCode.UserCode.*;

/**
 * @author LiMG
 * @Title: UserInfoController
 * @Description: <功能详细描述>
 * @date 2017/7/3 13:39
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("userInfo/")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Title: login
     * @Description: 用户登录方法
     * @author LiMG
     * @date 2017/7/3 16:36
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("login")
    public ResponseResult<UserInfoResponseDTO> login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserInfoRequestDTO userInfoRequestDTO = new UserInfoRequestDTO();
        userInfoRequestDTO.setUsername(username);
        userInfoRequestDTO.setPassword(password);
        try {
            String sessionId = getSessiongId(request);
            String redisCheckCode = RedisUtil.get(StaticUtil.USER_LOGIN_CHECK_CODE + sessionId);
            if (StringUtils.isNotEmpty(redisCheckCode)) {
                // 校验验证码正确性
                String checkCode = request.getParameter("checkCode");
                if (StringUtils.isEmpty(checkCode)) {
                    return ResponseUtil.error(USER_CHECKCODE_EMPTY.getCode(), USER_CHECKCODE_EMPTY.getMsg());
                }
                if (!checkCode.toLowerCase().equals(redisCheckCode.toLowerCase())) {
                    return ResponseUtil.error(USER_CHECKCODE_ERROR.getCode(), USER_CHECKCODE_ERROR.getMsg());
                }
            }
            UserInfoResponseDTO userInfoResponseDTO = userInfoService.queryUserInfo(userInfoRequestDTO);
            if (null == userInfoResponseDTO) {
                // 用户名或密码错误
                String key = StaticUtil.USER_ERROR_COUNT + sessionId;
                String value = RedisUtil.get(key);
                if (StringUtils.isNotEmpty(value)) {
                    int errorCount = Integer.parseInt(value);
                    RedisUtil.set(key, String.valueOf(errorCount + 1), 600);
                    if (errorCount >= 2) {
                        // 登录错误3次，页面展示验证码
                        return ResponseUtil.error(USER_CERTIFICATE_ERROR_THREE.getCode(), USER_CERTIFICATE_ERROR_THREE.getMsg());
                    } else {
                        return ResponseUtil.error(USER_CERTIFICATE_ERROR.getCode(), USER_CERTIFICATE_ERROR.getMsg());
                    }
                } else {
                    RedisUtil.set(key, "1", 600);
                }
                return ResponseUtil.error(USER_CERTIFICATE_ERROR.getCode(), USER_CERTIFICATE_ERROR.getMsg());
            }
            // 登录成功，保存用户登录标识到redis
            String loginKey = StaticUtil.USER_LOGIN_FLAG + userInfoResponseDTO.getUserId();
            // 登录状态保持1小时
            RedisUtil.set(loginKey, "true", 3600);
            return ResponseUtil.success(userInfoResponseDTO);
        } catch (Exception e) {
            LOGGER.error("用户登录异常,异常信息:{}", e.getMessage(), e);
            return ResponseUtil.error("0001", "登录失败，请稍后重试");
        }
    }

    /**
     * @Title: checkUsername
     * @Description: 校验用户名
     * @author LiMG
     * @date 2017/7/19 16:57
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("checkSessionId")
    public ResponseResult<Boolean> checkSessionId(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = getSessiongId(request);
        if (null == sessionId) {
            request.getSession(true);
            return null;
        }
        String key = StaticUtil.USER_ERROR_COUNT + sessionId;
        String value = RedisUtil.get(key);
        if (StringUtils.isEmpty(value)) {
            return ResponseUtil.success(false);
        }
        if (Integer.parseInt(value) >= 3) {
            return ResponseUtil.success(true);
        }
        return ResponseUtil.success(false);
    }

    /**
     * @Title: getCaptchaImage
     * @Description: 获取图片验证码
     * @author LiMG
     * @date 2017/7/10 15:23
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("getCaptchaImage")
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        int type = null == request.getParameter("type") ? 1 : "".equals(request.getParameter("type")) ? 1 : Integer.parseInt(request.getParameter("type"));
        try {
            String sessionId = getSessiongId(request);
            String code = CaptchaUtil.generateVerifyCode(4);
            String key = null;
            if (type == 1) {
                key = StaticUtil.USER_LOGIN_CHECK_CODE + sessionId;
            } else {
                key = StaticUtil.USER_REGISTER_CHECK_CODE + sessionId;
            }
            // 10分钟有效期缓存
            RedisUtil.set(key, code, 600);
            CaptchaUtil.getCaptcha(request, response, 130, 48, code);
        } catch (Exception e) {
            LOGGER.error("获取图片验证码异常,异常信息:{}", e.getMessage(), e);
        }
    }

    /**
     * @Title: checkUsername
     * @Description: 校验用户名是否存在
     * @author LiMG
     * @date 2017/7/21 10:46
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("checkUsername")
    public ResponseResult<Boolean> checkUsername(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            if (StringUtils.isNotEmpty(username)) {
                UserInfoResponseDTO userInfoResponseDTO = userInfoService.queryUserInfoByUsername(username);
                if (null == userInfoResponseDTO) {
                    return ResponseUtil.success(false);
                } else {
                    return ResponseUtil.success(true);
                }
            } else {
                return ResponseUtil.error("0001", "用户名不能为空");
            }
        } catch (Exception e) {
            LOGGER.error("校验用户名异常,异常信息:{}", e.getMessage(), e);
            return ResponseUtil.error("0001", "系统异常，请稍后重试");
        }
    }

    /**
     * @Title: checkPhoneNum
     * @Description: 校验手机号
     * @author LiMG
     * @date 2017/7/21 16:32
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("checkPhoneNum")
    public ResponseResult<Boolean> checkPhoneNum(HttpServletRequest request, HttpServletResponse response) {
        try {
            String phone = request.getParameter("phone");
            if (StringUtils.isNotEmpty(phone)) {
                UserInfoResponseDTO userInfoResponseDTO = userInfoService.queryUserInfoByPhone(phone);
                if (null == userInfoResponseDTO) {
                    return ResponseUtil.success(false);
                } else {
                    return ResponseUtil.success(true);
                }
            } else {
                return ResponseUtil.error("0001", "手机号不能为空");
            }
        } catch (Exception e) {
            LOGGER.error("校验手机号异常,异常信息:{}", e.getMessage(), e);
            return ResponseUtil.error("0001", "系统异常，请稍后重试");
        }
    }

    /**
     * @Title: verifyCheckCode
     * @Description: 校验验证码
     * @author LiMG
     * @date 2017/7/21 16:41
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("verifyCheckCode")
    public ResponseResult<Boolean> verifyCheckCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sessionId = getSessiongId(request);
            String checkCode = request.getParameter("checkCode");
            if (StringUtils.isNotEmpty(checkCode)) {
                String key = StaticUtil.USER_REGISTER_CHECK_CODE + sessionId;
                String value = RedisUtil.get(key);
                if (StringUtils.isEmpty(value)) {
                    return ResponseUtil.error("0001", "验证码已失效，请刷新");
                } else {
                    if (!checkCode.toLowerCase().equals(value.toLowerCase())) {
                        return ResponseUtil.success(false);
                    } else {
                        return ResponseUtil.success(true);
                    }
                }
            } else {
                return ResponseUtil.error("0001", "验证码不能为空");
            }
        } catch (Exception e) {
            LOGGER.error("校验验证码异常,异常信息:{}", e.getMessage(), e);
            return ResponseUtil.error("0001", "系统异常，请稍后重试");
        }
    }

    /**
     * @Title: sendPhoneCode
     * @Description: 发送手机验证码
     * @author LiMG
     * @date 2017/7/24 15:16
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("sendPhoneCode")
    public ResponseResult<Boolean> sendPhoneCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String phone = request.getParameter("phone");
            ValidateUtil.paramRequired(phone, "手机号不能为空");
            String type = request.getParameter("sendType");
            ValidateUtil.paramRequired(phone, "发送类型不能为空");
            boolean result = MessageUtil.sendPhoneCode(phone, Integer.parseInt(type));
            return ResponseUtil.success(result);
        } catch (Exception e) {
            LOGGER.error("手机验证码发送异常,异常信息:{}", e.getMessage(), e);
            return ResponseUtil.error("0001", "系统异常，请稍后重试");
        }
    }

}
