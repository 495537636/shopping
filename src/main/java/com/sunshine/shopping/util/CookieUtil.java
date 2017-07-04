/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CookieUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/4 15:49
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiMG
 * @Title: CookieUtil
 * @Description: Cookie工具类
 * @date 2017/7/4 15:49
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CookieUtil {

    private transient static Logger log = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * @Title: setCookie
     * @Description: 设置cookie信息
     * @author LiMG
     * @date 2017/7/4 15:51
     * @see [类、类#方法、类#成员]
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int expiry, String domain, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + " [ " + value + " ] ' on path '" + path + "'");
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        if (domain != null)
            cookie.setDomain(domain);
        cookie.setPath(path != null ? path : "/");
        cookie.setMaxAge(expiry); // 30 days
        response.addCookie(cookie);
    }

    /**
     * @Title: deleteCookie
     * @Description: 删除cookie信息
     * @author LiMG
     * @date 2017/7/4 15:57
     * @see [类、类#方法、类#成员]
     */
    public static void deleteCookie(HttpServletResponse response, String name, String domain, String path) {
        Cookie cookie = new Cookie(name, "");
        deleteCookie(response, cookie, domain, path);
    }

    /**
     * @Title: deleteCookies
     * @Description: 删除多个cookie信息
     * @author LiMG
     * @date 2017/7/4 15:58
     * @see [类、类#方法、类#成员]
     */
    public static void deleteCookies(HttpServletResponse response, String[] names, String domain, String path) {
        if (null == names) {
            return;
        }
        for (int i = 0; i < names.length; i++) {
            Cookie cookie = new Cookie(names[i], "");
            deleteCookie(response, cookie, domain, path);
        }
    }

    /**
     * @Title: deleteCookie
     * @Description: 删除cookie信息
     * @author LiMG
     * @date 2017/7/4 15:58
     * @see [类、类#方法、类#成员]
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String domain, String path) {
        if (cookie != null) {
            if (log.isDebugEnabled()) {
                log.debug("Deleting cookie '" + cookie.getName() + "' on domain '" + cookie.getDomain() + "' path '" + path + "'");
            }
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            if (domain != null)
                cookie.setDomain(domain);
            cookie.setPath(path != null ? path : "/");
            response.addCookie(cookie);
        }
    }

    /**
     * @Title: getCookie
     * @Description: 获取cookie信息
     * @author LiMG
     * @date 2017/7/4 15:58
     * @see [类、类#方法、类#成员]
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie thisCookie = cookies[i];

            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (StringUtils.isNotBlank(thisCookie.getValue())) {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }
        return returnCookie;
    }

    /**
     * @Title: getCookieValue
     * @Description: 获取cookie值
     * @author LiMG
     * @date 2017/7/4 15:59
     * @see [类、类#方法、类#成员]
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null) {
            return "";
        } else {
            return cookie.getValue();
        }
    }

}
