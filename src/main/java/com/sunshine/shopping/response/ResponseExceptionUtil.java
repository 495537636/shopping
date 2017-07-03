/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResponseExceptionUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.response
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 11:07
 * @version: V1.0
 */

package com.sunshine.shopping.response;

import com.sunshine.shopping.common.exception.BusinessException;
import com.sunshine.shopping.common.exception.ParamDefectException;
import com.sunshine.shopping.common.exception.ParamMistakeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiMG
 * @Title: ResponseExceptionUtil
 * @Description: 处理异常工具类
 * @date 2017/6/29 11:07
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseExceptionUtil {

    public static Logger logger = LoggerFactory.getLogger(ResponseExceptionUtil.class);

    public static <T> ResponseResult<T> handleException(Exception e) {
        return handleException("", e);
    }

    public static <T> ResponseResult<T> handleException(String exceptionKey,
                                                        Exception e) {
        if (e instanceof ParamDefectException) {
            // 参数缺失异常
            logger.error("业务返回异常Key:[{}][参数缺失],异常信息:{}", exceptionKey, e.getMessage(), e);
            return ResponseUtil.error("0004", e.getMessage());
        } else if (e instanceof ParamMistakeException) {
            // 参数错误异常
            logger.error("业务返回异常Key:[{}][参数错误],异常信息:{}", exceptionKey, e.getMessage(), e);
            return ResponseUtil.error("0002", e.getMessage());
        } else if (e instanceof BusinessException) {
            // 业务异常
            logger.warn("业务返回异常Key:[{}][数据错误],异常信息:{}", exceptionKey, e.getMessage(), e);
            return ResponseUtil.error("0001", e.getMessage());
        } else {
            // 其他异常
            logger.error("业务返回异常Key:[{}],异常信息:{}", exceptionKey, e.getMessage(), e);
            return ResponseUtil.error("0003", e.getMessage());
        }
    }

}
