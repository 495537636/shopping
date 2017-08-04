/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserInfoRequestDTO
 * @Prject: shopping
 * @Package: com.sunshine.shopping.model.dto
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/28 17:58
 * @version: V1.0
 */

package com.sunshine.shopping.model.dto;

import java.io.Serializable;

/**
 * @Title: UserInfoRequestDTO
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/28 17:58
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoRequestDTO implements Serializable {

    private static final long serialVersionUID = 3924579085267185942L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 登录名
     */
    private String loginName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
