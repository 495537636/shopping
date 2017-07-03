/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: LoginImageEntity
 * @Prject: shopping
 * @Package: com.sunshine.shopping.model.entity
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/28 18:19
 * @version: V1.0
 */

package com.sunshine.shopping.model.entity;

import java.io.Serializable;

/**
 * @Title: LoginImageEntity
 * @Description: 登录页面宣传图片实体类
 * @author LiMG
 * @date 2017/6/28 18:19
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginImageEntity implements Serializable {

    private static final long serialVersionUID = -6542240361646183093L;

    /**
     * 图片ID
     */
    private String imageId;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 图片标题
     */
    private String imageTitle;

    /**
     * 图片描述
     */
    private String imageComment;

    /**
     * 图片排序
     */
    private Integer imageOrder;

    /**
     * 图片标识
     */
    private Integer imageFlag;

    /**
     * 图片是否默认
     */
    private Integer imageActive;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }

    public Integer getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(Integer imageOrder) {
        this.imageOrder = imageOrder;
    }

    public Integer getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(Integer imageFlag) {
        this.imageFlag = imageFlag;
    }

    public Integer getImageActive() {
        return imageActive;
    }

    public void setImageActive(Integer imageActive) {
        this.imageActive = imageActive;
    }
}
