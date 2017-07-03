/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ParamDefectException
 * @Prject: shopping
 * @Package: com.sunshine.shopping.common.exception
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:52
 * @version: V1.0
 */

package com.sunshine.shopping.common.exception;

/**
 * @Title: ParamDefectException
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/6/29 10:52
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParamDefectException extends Exception {

    private static final long serialVersionUID = 6644911532342324909L;

    public ParamDefectException() {
        super();
    }

    public  ParamDefectException(String msg) {
        super(msg);
    }

}
