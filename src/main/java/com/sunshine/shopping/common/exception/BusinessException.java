/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: BusinessException
 * @Prject: shopping
 * @Package: com.sunshine.shopping.common.exception
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:48
 * @version: V1.0
 */

package com.sunshine.shopping.common.exception;

/**
 * @Title: BusinessException
 * @Description: 业务异常信息类
 * @author LiMG
 * @date 2017/6/29 10:48
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = -4225898452702037153L;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
    }

}
