/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ParamMistakeException
 * @Prject: shopping
 * @Package: com.sunshine.shopping.common.exception
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:51
 * @version: V1.0
 */

package com.sunshine.shopping.common.exception;

/**
 * @Title: ParamMistakeException
 * @Description: 参数错误异常信息类
 * @author LiMG
 * @date 2017/6/29 10:51
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParamMistakeException extends Exception {

    private static final long serialVersionUID = -8636569022553422069L;

    public ParamMistakeException() {
        super();
    }

    public ParamMistakeException(String msg) {
        super(msg);
    }

}
