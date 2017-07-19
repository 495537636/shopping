/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ShiroConfig
 * @Prject: shopping
 * @Package: com.sunshine.shopping.config
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/12 13:52
 * @version: V1.0
 */

package com.sunshine.shopping.config;

import com.sunshine.shopping.shiro.MyShiroRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ShiroConfig
 * @Description: shiro权限配置类
 * @author LiMG
 * @date 2017/7/12 13:52
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        MyShiroRealm realm = new MyShiroRealm();
        securityManager.setRealm(realm);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
