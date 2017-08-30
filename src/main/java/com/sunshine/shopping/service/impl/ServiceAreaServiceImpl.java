/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ServiceAreaServiceImpl
 * @Prject: shopping
 * @Package: com.sunshine.shopping.service.impl
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/30 11:21
 * @version: V1.0
 */

package com.sunshine.shopping.service.impl;

import com.sunshine.shopping.mapper.ServiceAreaMapper;
import com.sunshine.shopping.model.dto.ServiceAreaRequestDTO;
import com.sunshine.shopping.model.dto.ServiceAreaResponseDTO;
import com.sunshine.shopping.model.entity.ServiceAreaEntity;
import com.sunshine.shopping.service.ServiceAreaService;
import com.sunshine.shopping.util.AddressUtil;
import com.sunshine.shopping.util.CopyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: ServiceAreaServiceImpl
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/8/30 11:21
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class ServiceAreaServiceImpl implements ServiceAreaService {

    @Autowired
    private ServiceAreaMapper serviceAreaMapper;

    @Override
    public List<ServiceAreaResponseDTO> getServiceAreaList(ServiceAreaRequestDTO serviceAreaRequestDTO) throws Exception {
        ServiceAreaEntity entity = new ServiceAreaEntity();
        if (null != serviceAreaRequestDTO) {
            BeanUtils.copyProperties(serviceAreaRequestDTO, entity);
        }
        List<ServiceAreaEntity> serviceAreaList = serviceAreaMapper.queryList(entity);
        if (serviceAreaList.size() != 0) {
            List<ServiceAreaResponseDTO> areaList = CopyUtil.copyTo(serviceAreaList, ServiceAreaResponseDTO.class);
            return areaList;
        } else {
            return null;
        }
    }

    @Override
    public ServiceAreaResponseDTO getCurrentAddressAreaInfo(ServiceAreaRequestDTO serviceAreaRequestDTO) throws Exception {
        String regionCode = null;
        if (StringUtils.isEmpty(serviceAreaRequestDTO.getAreaCode())) {
            regionCode = AddressUtil.getAddressRegion();
        } else {
            regionCode = serviceAreaRequestDTO.getAreaCode();
        }
//        String regionCode = "150000";
        if(StringUtils.isNotEmpty(regionCode)) {
            ServiceAreaEntity queryEntity = new ServiceAreaEntity();
            queryEntity.setAreaCode(regionCode);
            ServiceAreaEntity entity = serviceAreaMapper.query(queryEntity);
            if (null == entity) {
                return null;
            } else {
                ServiceAreaResponseDTO responseDTO = new ServiceAreaResponseDTO();
                BeanUtils.copyProperties(entity, responseDTO);
                return responseDTO;
            }
        }
        return null;
    }

}
