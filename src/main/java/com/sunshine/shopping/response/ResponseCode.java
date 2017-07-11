/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResponseCode
 * @Prject: shopping
 * @Package: com.sunshine.shopping.response
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/11 16:03
 * @version: V1.0
 */

package com.sunshine.shopping.response;

/**
 * @Title: ResponseCode
 * @Description: 返回代码
 * @author LiMG
 * @date 2017/7/11 16:03
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseCode {

    public enum UserCode {

        USER_CERTIFICATE_ERROR("0001", "用户名或密码错误"),
        // 用户名或密码错误3次，页面展示验证码
        USER_CERTIFICATE_ERROR_THREE("0002", "用户名或密码错误"),
        USER_CHECKCODE_TIMEOUT("0003", "验证码过期");

        private String code;
        private String msg;

        private UserCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
