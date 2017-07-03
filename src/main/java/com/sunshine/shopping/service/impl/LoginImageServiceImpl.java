/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: LoginImageServiceImpl
 * @Prject: shopping
 * @Package: com.sunshine.shopping.service.impl
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:43
 * @version: V1.0
 */

package com.sunshine.shopping.service.impl;

import com.sunshine.shopping.common.util.ValidateUtil;
import com.sunshine.shopping.mapper.LoginImageMapper;
import com.sunshine.shopping.model.dto.LoginImageRequestDTO;
import com.sunshine.shopping.model.dto.LoginImageResponseDTO;
import com.sunshine.shopping.model.entity.LoginImageEntity;
import com.sunshine.shopping.service.LoginImageService;
import com.sunshine.shopping.util.CopyUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: LoginImageServiceImpl
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/29 10:43
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class LoginImageServiceImpl implements LoginImageService {

    @Autowired
    private LoginImageMapper loginImageMapper;

    /**
     * @Title: queryImageList
     * @Description: 查询图片列表
     * @author LiMG
     * @date 2017/6/29 10:44
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<LoginImageResponseDTO> queryImageList(LoginImageRequestDTO queryDTO) throws Exception {
        try {
            LoginImageEntity entity = new LoginImageEntity();
            if (null != queryDTO) {
                BeanUtils.copyProperties(queryDTO, entity);
            }
            List<LoginImageEntity> imageList = loginImageMapper.queryList(entity);
            List<LoginImageResponseDTO> list = CopyUtil.copyTo(imageList, LoginImageResponseDTO.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
