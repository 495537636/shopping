/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: UserInfoServiceImpl
 * @Prject: shopping
 * @Package: com.sunshine.shopping.service.impl
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/24 14:46
 * @version: V1.0
 */

package com.sunshine.shopping.service.impl;

import com.sunshine.shopping.common.util.ValidateUtil;
import com.sunshine.shopping.mapper.UserInfoMapper;
import com.sunshine.shopping.model.dto.UserInfoRequestDTO;
import com.sunshine.shopping.model.dto.UserInfoResponseDTO;
import com.sunshine.shopping.model.entity.UserInfoEntity;
import com.sunshine.shopping.service.UserInfoService;
import com.sunshine.shopping.util.GenerateIDUtil;
import com.sunshine.shopping.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserInfoServiceImpl
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/2414:46
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * @Title: queryUserInfo
     * @Description: 获取用户信息详情
     * @author LiMG
     * @date 2017/7/3 13:46
     * @see [类、类#方法、类#成员]
     */
    @Override
    public UserInfoResponseDTO queryUserInfo(UserInfoRequestDTO userInfoRequestDTO) throws Exception {
        ValidateUtil.paramRequired(userInfoRequestDTO, "登录名和密码不能为空");
        UserInfoEntity queryEntity = new UserInfoEntity();
        BeanUtils.copyProperties(userInfoRequestDTO, queryEntity);
        UserInfoEntity userInfo = userInfoMapper.query(queryEntity);
        UserInfoResponseDTO userInfoResponseDTO = new UserInfoResponseDTO();
        if (null != userInfo) {
            BeanUtils.copyProperties(userInfo, userInfoResponseDTO);
            return userInfoResponseDTO;
        } else {
            return null;
        }
    }

    /** 
     * @Title: queryUserInfoByUsername
     * @Description: 根据用户名获取用户信息详情
     * @author LiMG
     * @date 2017/7/12 16:21 
     * @see [类、类#方法、类#成员]
     */
    @Override
    public UserInfoResponseDTO queryUserInfoByUsername(String username) throws Exception {
        ValidateUtil.paramRequired(username, "用户名不能为空");
        UserInfoEntity queryEntity = new UserInfoEntity();
        queryEntity.setUsername(username);
        UserInfoEntity userInfo = userInfoMapper.query(queryEntity);
        UserInfoResponseDTO userInfoResponseDTO = new UserInfoResponseDTO();
        if (null != userInfo) {
            BeanUtils.copyProperties(userInfo, userInfoResponseDTO);
            return userInfoResponseDTO;
        } else {
            return null;
        }
    }

    /**
     * @Title: queryUserInfoByPhone
     * @Description: 根据手机号查询用户
     * @author LiMG
     * @date 2017/7/21 16:37
     * @see [类、类#方法、类#成员]
     */
    @Override
    public UserInfoResponseDTO queryUserInfoByPhone(String phone) throws Exception {
        ValidateUtil.paramRequired(phone, "手机号不能为空");
        UserInfoEntity queryEntity = new UserInfoEntity();
        queryEntity.setUserPhone(phone);
        UserInfoEntity userInfo = userInfoMapper.query(queryEntity);
        UserInfoResponseDTO userInfoResponseDTO = new UserInfoResponseDTO();
        if (null != userInfo) {
            BeanUtils.copyProperties(userInfo, userInfoResponseDTO);
            return userInfoResponseDTO;
        } else {
            return null;
        }
    }

    /**
     * @Title: saveUserInfo
     * @Description: 保存用户信息
     * @author LiMG
     * @date 2017/7/25 18:33
     * @see [类、类#方法、类#成员]
     */
    @Override
    public Boolean saveUserInfo(UserInfoRequestDTO userInfoRequestDTO) throws Exception {
        ValidateUtil.paramRequired(userInfoRequestDTO, "参数不能为空");
        ValidateUtil.paramRequired(userInfoRequestDTO.getUsername(), "用户名不能为空");
        ValidateUtil.paramRequired(userInfoRequestDTO.getPassword(), "密码不能为空");
        ValidateUtil.paramRequired(userInfoRequestDTO.getUserPhone(), "手机号不能为空");
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        BeanUtils.copyProperties(userInfoRequestDTO, userInfoEntity);
        userInfoEntity.setUserId(GenerateIDUtil.generateID());
        userInfoEntity.setPassword(MD5Util.md5(userInfoRequestDTO.getPassword()));
        int result = userInfoMapper.save(userInfoEntity);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
