/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: SearchController
 * @Prject: shopping
 * @Package: com.sunshine.shopping.web
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/23 17:25
 * @version: V1.0
 */

package com.sunshine.shopping.web;

import com.sunshine.shopping.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: SearchController
 * @Description: 搜索功能Controller
 * @author LiMG
 * @date 2017/8/23 17:25
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("search/")
public class SearchController extends BaseController {

    @RequestMapping("searchGoods")
    public String searchGoods(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        return "search";
    }

}
