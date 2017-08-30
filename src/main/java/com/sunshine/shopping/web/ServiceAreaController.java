/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ServiceProvinceController
 * @Prject: shopping
 * @Package: com.sunshine.shopping.web
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/30 11:07
 * @version: V1.0
 */

package com.sunshine.shopping.web;

import com.sunshine.shopping.common.web.BaseController;
import com.sunshine.shopping.model.dto.ServiceAreaRequestDTO;
import com.sunshine.shopping.model.dto.ServiceAreaResponseDTO;
import com.sunshine.shopping.response.ResponseExceptionUtil;
import com.sunshine.shopping.response.ResponseResult;
import com.sunshine.shopping.response.ResponseUtil;
import com.sunshine.shopping.service.ServiceAreaService;
import com.sunshine.shopping.util.AddressUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: ServiceAreaController
 * @Description: 服务地区Controller
 * @author LiMG
 * @date 2017/8/30 11:07
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@RequestMapping("serviceArea/")
@Controller
public class ServiceAreaController extends BaseController {

    @Autowired
    private ServiceAreaService serviceAreaService;

    /**
     * @Title: queryServiceAreaList
     * @Description: 获取服务地区列表
     * @author LiMG
     * @date 2017/8/30 11:46
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ResponseResult<List<ServiceAreaResponseDTO>> queryServiceAreaList(ServiceAreaRequestDTO serviceAreaRequestDTO) {
        try {
            List<ServiceAreaResponseDTO> areaList = serviceAreaService.getServiceAreaList(serviceAreaRequestDTO);
            return ResponseUtil.success(areaList);
        } catch (Exception e) {
            LOGGER.error("获取服务地区列表异常,异常信息:{}", e.getMessage(), e);
            return ResponseExceptionUtil.handleException(e);
        }
    }

    /**
     * @Title: getRegionCode
     * @Description: 获取当前IP地址的所属区域信息
     * @author LiMG
     * @date 2017/8/30 15:01
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("getRegionCode")
    @ResponseBody
    public ResponseResult<ServiceAreaResponseDTO> getRegionCode(HttpServletRequest request) {
        try {
            String areaCode = request.getParameter("areaCode");
            ServiceAreaRequestDTO requestDTO = new ServiceAreaRequestDTO();
            if (StringUtils.isNotEmpty(areaCode)) {
                requestDTO.setAreaCode(areaCode);
            }
            ServiceAreaResponseDTO responseDTO = serviceAreaService.getCurrentAddressAreaInfo(requestDTO);
            return ResponseUtil.success(responseDTO);
        } catch (Exception e) {
            LOGGER.error("获取当前IP地址的所属区域代码异常,异常信息:{}", e.getMessage(), e);
            return ResponseExceptionUtil.handleException(e);
        }
    }

}
