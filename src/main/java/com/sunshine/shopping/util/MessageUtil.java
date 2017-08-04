/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: MessageUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/24 15:53
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunshine.shopping.common.exception.ParamDefectException;

/**
 * @Title: MessageUtil
 * @Description: 短信工具类
 * @author LiMG
 * @date 2017/7/24 15:53
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class MessageUtil {

    /**
     * @Title: sendPhoneCode
     * @Description 发送短信验证码
     * @author LiMG
     * @date 2017/7/24 15:55
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Object> sendPhoneCode(String phone, int sendType) {
        String num = RandomUtil.generateSixLengthNum();
        String timeOutKey = StaticUtil.USER_PHONE_CODE_TIME_OUT + phone;
        String countKey = StaticUtil.USER_REGISTER_PHONE_CODE_COUNT + phone;
        if (sendType == 2) {
            countKey = StaticUtil.USER_FIND_PASSWORD_PHONE_CODE_COUNT + phone;
        }
        String timeOut = RedisUtil.get(timeOutKey);
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(timeOut)) {
            map.put("flag", false);
            map.put("message", "120秒内只能获取一次验证码");
            return map;
        }
        String count = RedisUtil.get(countKey);
        if (StringUtils.isNotEmpty(count) && Integer.parseInt(count) >= 5) {
            map.put("flag", false);
            map.put("message", "今日认证次数超过限制");
            return map;
        }
        String redisKey = null;
        if (sendType == 1) {
            // 用户注册验证码
            redisKey = StaticUtil.USER_REGISTER_PHONE_CODE + phone;
        }
        if (sendType == 2) {
            // 用户密码找回验证码
            redisKey = StaticUtil.USER_FORGET_PHONE_CODE + phone;
        }
        // 获取验证码时间间隔
        RedisUtil.set(timeOutKey, "1", StaticUtil.USER_PHONE_CODE_TIME);
        // 保存认证次数
        int sendCount = 0;
        if (StringUtils.isNotEmpty(count)) {
            sendCount = Integer.parseInt(count) + 1;
        } else {
            sendCount = 1;
        }
        RedisUtil.set(countKey, String.valueOf(sendCount), 3600);
        // 10分钟有效期
        RedisUtil.set(redisKey, num, 600);
        map.put("flag", true);
        map.put("message", null);
        return map;
    }

    /**
     * @Title: checkPhoneCode
     * @Description: 校验手机验证码
     * @author LiMG
     * @date 2017/7/25 11:35
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Object> checkPhoneCode(String phoneNum, String phoneCode, int checkType) throws ParamDefectException {
        String key = null;
        if (checkType == 1) {
            // 校验用户注册
            key = StaticUtil.USER_REGISTER_PHONE_CODE + phoneNum;
        }
        if (checkType == 2) {
            // 校验用户密码找回
            key = StaticUtil.USER_FORGET_PHONE_CODE + phoneNum;
        }
        Map<String, Object> map = new HashMap<>();
        String value = RedisUtil.get(key);
        if (StringUtils.isNotEmpty(value)) {
            if (!value.toLowerCase().equals(phoneCode.toLowerCase())) {
                map.put("flag", false);
                map.put("message", "验证码错误");
            } else {
                map.put("flag", true);
            }
        } else {
            map.put("flag", false);
            map.put("message", "验证码已失效，请重新获取");
        }
        return map;
    }

}
