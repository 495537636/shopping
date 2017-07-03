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
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
}
