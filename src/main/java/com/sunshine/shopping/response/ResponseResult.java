/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResponseResult
 * @Prject: shopping
 * @Package: com.sunshine.shopping.response
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/28 17:38
 * @version: V1.0
 */

package com.sunshine.shopping.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: ResponseResult
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/28 17:38
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseResult<T> {

    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取 data
     *
     * @return 返回 data
     */
    public T getData() {
        return data;
    }

    /**
     * 设置 data
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
