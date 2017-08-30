/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ServiceAreaResponseDTO
 * @Prject: shopping
 * @Package: com.sunshine.shopping.model.dto
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/30 11:17
 * @version: V1.0
 */

package com.sunshine.shopping.model.dto;

import java.io.Serializable;

/**
 * @Title: ServiceAreaResponseDTO
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/8/30 11:17
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServiceAreaResponseDTO implements Serializable {

    private static final long serialVersionUID = 6171612838571325310L;

    /**
     * 主键ID
     */
    private Integer pkid;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 地区代码
     */
    private String areaCode;

    /**
     * 编码
     */
    private Integer areaNum;

    /**
     * 地区标识
     */
    private Integer areaFlag;

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(Integer areaNum) {
        this.areaNum = areaNum;
    }

    public Integer getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(Integer areaFlag) {
        this.areaFlag = areaFlag;
    }

}
