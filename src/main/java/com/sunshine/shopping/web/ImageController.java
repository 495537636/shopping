/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ImageController
 * @Prject: shopping
 * @Package: com.sunshine.shopping.web
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/28 17:36
 * @version: V1.0
 */

package com.sunshine.shopping.web;


import com.sunshine.shopping.common.web.BaseController;
import com.sunshine.shopping.model.dto.LoginImageRequestDTO;
import com.sunshine.shopping.model.dto.LoginImageResponseDTO;
import com.sunshine.shopping.response.ResponseExceptionUtil;
import com.sunshine.shopping.response.ResponseResult;
import com.sunshine.shopping.response.ResponseUtil;
import com.sunshine.shopping.service.LoginImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: ImageController
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/28 17:36
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("image/")
public class ImageController extends BaseController {

    @Autowired
    private LoginImageService loginImageService;

    /**
     * @Title: queryLoginImageList
     * @Description: 查询登录宣传图片列表
     * @author LiMG
     * @date 2017/6/29 11:36
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("queryLoginImageList")
    @ResponseBody
    public ResponseResult<List<LoginImageResponseDTO>> queryLoginImageList(LoginImageRequestDTO queryDTO, HttpServletRequest request) {
        try {
            List<LoginImageResponseDTO> imageList = loginImageService.queryImageList(queryDTO);
            return ResponseUtil.success(imageList);
        } catch (Exception e) {
            return ResponseExceptionUtil.handleException(e);
        }
    }

}
