package com.sunshine.shopping.service;

import com.sunshine.shopping.model.dto.ServiceAreaRequestDTO;
import com.sunshine.shopping.model.dto.ServiceAreaResponseDTO;

import java.util.List;

public interface ServiceAreaService {

    /**
     * @Title: getServiceAreaList
     * @Description: 获取服务地区列表
     * @author LiMG
     * @date 2017/8/30 11:20
     * @see [类、类#方法、类#成员]
     */
    List<ServiceAreaResponseDTO> getServiceAreaList(ServiceAreaRequestDTO serviceAreaRequestDTO) throws Exception;

    /**
     * @Title: getCurrentAddressAreaInfo
     * @Description: 获取当前IP地址的区域信息
     * @author LiMG
     * @date 2017/8/30 15:34
     * @see [类、类#方法、类#成员]
     */
    ServiceAreaResponseDTO getCurrentAddressAreaInfo(ServiceAreaRequestDTO serviceAreaRequestDTO) throws Exception;

}
