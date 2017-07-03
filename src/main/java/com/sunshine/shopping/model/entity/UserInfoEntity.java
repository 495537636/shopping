/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserInfoEntity
 * @Prject: shopping
 * @Package: com.sunshine.shopping.model
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/24 14:49
 * @version: V1.0
 */

package com.sunshine.shopping.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: UserInfoEntity
 * @Description: 用户信息实体类
 * @author LiMG
 * @date 2017/6/24 14:49
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 2437012951252785499L;

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
     * 用户类型
     */
    private Integer userType;

    /**
     * 用户标识
     */
    private Integer userFlag;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Integer userFlag) {
        this.userFlag = userFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
