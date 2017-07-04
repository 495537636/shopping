/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ShoppingInterceptor
 * @Prject: shopping
 * @Package: com.sunshine.shopping.interceptor
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/4 14:15
 * @version: V1.0
 */

package com.sunshine.shopping.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiMG
 * @Title: ShoppingInterceptor
 * @Description: 拦截器
 * @date 2017/7/4 14:15
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class ShoppingInterceptor implements HandlerInterceptor {

    public static Logger LOGGER = LoggerFactory.getLogger(ShoppingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        System.out.println(uri);
        System.out.println(url.toString());
        response.sendRedirect("error");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
