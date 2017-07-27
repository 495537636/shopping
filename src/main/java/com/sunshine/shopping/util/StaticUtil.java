/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: StaticUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/11 16:37
 * @version: V1.0
 */

package com.sunshine.shopping.util;

/**
 * @Title: StaticUtil
 * @Description: 存放静态数据工具类
 * @author LiMG
 * @date 2017/7/11 16:37
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class StaticUtil {

    // 用户注册手机号获取验证码次数
    public static final int USER_REGISTER_COUNT = 5;

    // 获取手机验证码时间间隔
    public static final int USER_PHONE_CODE_TIME = 120;

    // 用户登录错误次数前缀
    public static final String USER_ERROR_COUNT =  "USER_ERROR_COUNT_";

    // 用户注册获取验证码次数
    public static final String USER_REGISTER_PHONE_CODE_COUNT = "USER_REGISTER_PHONE_CODE_COUNT_";

    // 用户登录状态标识
    public static final String USER_LOGIN_FLAG = "USER_LOGIN_FLAG_";

    // 登录验证码标识
    public static final String USER_LOGIN_CHECK_CODE = "USER_LOGIN_CHECK_CODE_";

    // 用户注册验证码标识
    public static final String USER_REGISTER_CHECK_CODE = "USER_REGISTER_CHECK_CODE_";

    // 用户密码找回验证码标识
    public static final String USER_FORGET_CHECK_CODE = "USER_FORGET_CHECK_CODE_";

    // 用户注册手机验证码标识
    public static final String USER_REGISTER_PHONE_CODE = "USER_REGISTER_PHONE_CODE_";

    // 用户密码找回手机验证码标识
    public static final String USER_FORGET_PHONE_CODE = "USER_FORGET_PHONE_CODE_";

    // 用户获取验证码超时时间
    public static final String USER_REGISTER_PHONE_CODE_TIME_OUT = "USER_REGISTER_PHONE_CODE_TIME_OUT_";

}
