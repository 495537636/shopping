/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: ResponseUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.response
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/28 18:05
 * @version: V1.0
 */

package com.sunshine.shopping.response;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author LiMG
 * @Title: ResponseUtil
 * @Description: <功能详细描述>
 * @date 2017/6/28 18:05
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseUtil<T> {

    public static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 返回成功数据
     *
     * @param o
     * @return
     * @Title: success
     * @Description: <功能详细描述>
     * @author LiMG
     * @date 2017年5月2日 下午6:39:43
     * @see [类、类#方法、类#成员]
     */
    public static <T> ResponseResult<T> success(T o) {
        ResponseResult<T> rr = new ResponseResult<T>();
        rr.setCode("0000");
        rr.setMsg("操作成功");
        rr.setData(o);
        return rr;
    }

    /**
     * 返回错误数据
     *
     * @param code
     * @param msg
     * @return
     * @Title: error
     * @Description: <功能详细描述>
     * @author LiMG
     * @date 2017年5月2日 下午6:40:14
     * @see [类、类#方法、类#成员]
     */
    public static <T> ResponseResult<T> error(String code, String msg) {
        ResponseResult<T> rr = new ResponseResult<T>();
        rr.setCode(code);
        rr.setMsg(msg);
        return rr;
    }

    public static void info(HttpServletResponse response,
                            ResponseResult<?> result) {
        PrintWriter out = null;
        try {
            if (response != null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                out = response.getWriter();
                out.print(JSON.toJSONString(result));
            }
        } catch (Exception e) {
            logger.error("封装JSON异常,异常信息:{}", e.getMessage(), e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}