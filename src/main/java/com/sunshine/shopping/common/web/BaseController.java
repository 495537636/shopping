/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: BaseController
 * @Prject: shopping
 * @Package: com.sunshine.shopping.common.web
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:36
 * @version: V1.0
 */

package com.sunshine.shopping.common.web;

import com.sunshine.shopping.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: BaseController
 * @Description: 公用Controlelr
 * @author LiMG
 * @date 2017/6/29 10:36
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class BaseController {

    public static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected void printJsonMessage(HttpServletResponse response, String message) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/x-json;charset=UTF-8");
        try {
            message = message==null?"":String.valueOf(message);
            response.getWriter().print(message);
            response.getWriter().close();
        } catch (IOException e) {
            LOGGER.error("输出信息异常,异常信息:{}", e.getMessage(), e);
        }
    }

    /**
     * @Title: toHome
     * @Description: 跳转到主页
     * @author LiMG
     * @date 2017/7/11 13:44
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/")
    public String toHome(HttpServletRequest request, HttpServletResponse response) {
        // 开启session，会生成JSESSIONID并传到前台，同时放入Cookie中
        request.getSession(true);
        return "index";
    }

    /**
     * @Title: getSessiongId
     * @Description: 获取sessionId
     * @author LiMG
     * @date 2017/7/20 10:43
     * @see [类、类#方法、类#成员]
     */
    public String getSessiongId(HttpServletRequest request) {
        return request.getSession().getId();
    }

}
