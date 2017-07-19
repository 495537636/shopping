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
     * @Description: <功能详细描述>
     * @author LiMG
     * @date 2017/7/12 16:19 
     * @see [类、类#方法、类#成员]
     */
    UserInfoResponseDTO queryUserInfoByUsername(String username) throws Exception;

}
