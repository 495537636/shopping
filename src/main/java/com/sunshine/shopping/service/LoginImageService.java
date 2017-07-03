package com.sunshine.shopping.service;

import com.sunshine.shopping.model.dto.LoginImageRequestDTO;
import com.sunshine.shopping.model.dto.LoginImageResponseDTO;

import java.util.List;

public interface LoginImageService {

    /** 
     * @Title: queryImageList
     * @Description: 查询图片集合
     * @author LiMG
     * @date 2017/6/29 10:43 
     * @see [类、类#方法、类#成员]
     */
    List<LoginImageResponseDTO> queryImageList(LoginImageRequestDTO queryDTO) throws Exception;

}
