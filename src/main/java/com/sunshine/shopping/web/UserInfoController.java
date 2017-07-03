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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunshine.shopping.common.web.BaseController;
import com.sunshine.shopping.model.dto.UserInfoRequestDTO;
import com.sunshine.shopping.model.dto.UserInfoResponseDTO;
import com.sunshine.shopping.service.UserInfoService;

/**
 * @Title: UserInfoController
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/7/3 13:39
 * @see  [相关类/方法]
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
    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserInfoRequestDTO userInfoRequestDTO = new UserInfoRequestDTO();
        userInfoRequestDTO.setUsername(username);
        userInfoRequestDTO.setPassword(password);
        try {
            UserInfoResponseDTO userInfoResponseDTO = userInfoService.queryUserInfo(userInfoRequestDTO);
            if (null == userInfoResponseDTO) {
                printJsonMessage(response, "用户名或密码错误");
            }
        }catch (Exception e) {
            LOGGER.error("用户登录异常,异常信息:{}", e.getMessage(), e);
            printJsonMessage(response, e.getMessage());
        }
    }

}
