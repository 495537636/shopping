/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: MyShiroRealm
 * @Prject: shopping
 * @Package: com.sunshine.shopping.shiro
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/12 15:54
 * @version: V1.0
 */

package com.sunshine.shopping.shiro;

import com.sunshine.shopping.model.dto.UserInfoResponseDTO;
import com.sunshine.shopping.service.UserInfoService;
import com.sunshine.shopping.util.CipherUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiMG
 * @Title: MyShiroRealm
 * @Description: <功能详细描述>
 * @date 2017/7/12 15:54
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        try {
            UserInfoResponseDTO userInfoResponseDTO = userInfoService.queryUserInfoByUsername(username);
            if (userInfoResponseDTO != null) {
                return new SimpleAuthenticationInfo(userInfoResponseDTO.getUsername(), CipherUtil.generatePassword(userInfoResponseDTO.getPassword()), getName());
            } else {
                throw new AuthenticationException("用户名或密码错误");
            }
        } catch (Exception e) {
            throw new AuthenticationException("查询用户异常");
        }
    }

}
