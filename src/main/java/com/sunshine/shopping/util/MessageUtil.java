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
    public static boolean sendPhoneCode(String phone, int sendType) {
        String num = RandomUtil.generateSixLengthNum();
        String redisKey = null;
        if (sendType == 1) {
            // 用户注册验证码
            redisKey = StaticUtil.USER_REGISTER_PHONE_CODE + phone;
        }
        if (sendType == 2) {
            // 用户密码找回验证码
            redisKey = StaticUtil.USER_FORGET_PHONE_CODE + phone;
        }
        // 10分钟有效期
        RedisUtil.set(redisKey, num, 600);
        return true;
    }

}
