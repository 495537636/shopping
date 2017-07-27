package com.sunshine.shopping.service;

import com.sunshine.shopping.model.dto.UserInfoRequestDTO;
import com.sunshine.shopping.model.dto.UserInfoResponseDTO;

public interface UserInfoService {

    /**
     * @Title: queryUserInfo
     * @Description: 获取用户信息详情
     * @author LiMG
     * @date 2017/7/3 13:46
     * @see [类、类#方法、类#成员]
     */
    UserInfoResponseDTO queryUserInfo(UserInfoRequestDTO userInfoRequestDTO) throws Exception;
    
    /** 
     * @Title: 根据用户名获取用户信息详情
     * @Description: 根据用户名查询用户
     * @author LiMG
     * @date 2017/7/12 16:19 
     * @see [类、类#方法、类#成员]
     */
    UserInfoResponseDTO queryUserInfoByUsername(String username) throws Exception;

    /**
     * @Title: queryUserInfoByPhone
     * @Description: 根据手机号查询用户
     * @author LiMG
     * @date 2017/7/21 16:31
     * @see [类、类#方法、类#成员]
     */
    UserInfoResponseDTO queryUserInfoByPhone(String phone) throws Exception;

    /**
     * @Title: saveUserInfo
     * @Description: 保存用户信息
     * @author LiMG
     * @date 2017/7/25 18:30
     * @see [类、类#方法、类#成员]
     */
    Boolean saveUserInfo(UserInfoRequestDTO userInfoRequestDTO) throws Exception;

}
