/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ValidateUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.common.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 10:53
 * @version: V1.0
 */

package com.sunshine.shopping.common.util;

import com.sunshine.shopping.common.exception.BusinessException;
import com.sunshine.shopping.common.exception.ParamDefectException;
import com.sunshine.shopping.common.exception.ParamMistakeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: ValidateUtil
 * @Description: 校验数据工具类
 * @author LiMG
 * @date 2017/6/29 10:53
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class ValidateUtil {

    public static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

    /**
     * @Title: paramRequired
     * @Description: 检验参数是否为，为空则抛出参数缺失异常
     * @author LiMG
     * @date 2017/6/29 10:56
     * @see [类、类#方法、类#成员]
     */
    public static void paramRequired(Object value, String message)
            throws ParamDefectException {
        if (value == null) {
            throw new ParamDefectException(message);
        } else {
            if (value instanceof String
                    && StringUtils.isBlank(String.valueOf(value))) {
                throw new ParamDefectException(message);
            }
        }
    }

    /** 
     * @Title: paramValidate
     * @Description: 校验参数是否符合规则，符合则抛出参数错误异常
     * @author LiMG
     * @date 2017/6/29 10:57 
     * @see [类、类#方法、类#成员]
     */
    public static void paramValidate(boolean checked, String message)
            throws ParamMistakeException {
        if (checked) {
            throw new ParamMistakeException(message);
        }
    }

    /** 
     * @Title: businessValidate
     * @Description: 判断是否符合指定条件，如果符合则抛出业务异常
     * @author LiMG
     * @date 2017/6/29 10:58 
     * @see [类、类#方法、类#成员]
     */
    public static void businessValidate(boolean checked, String message)
            throws BusinessException {
        if (checked) {
            throw new BusinessException(message);
        }
    }

}
