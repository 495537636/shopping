/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CopyUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/29 11:03
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LiMG
 * @Title: CopyUtil
 * @Description: 复制工具类
 * @date 2017/6/29 11:03
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CopyUtil {

    /** 
     * @Title: copyTo
     * @Description: 复制集合
     * @author LiMG
     * @date 2017/6/29 11:04 
     * @see [类、类#方法、类#成员]
     */
    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) throws Exception {
        if (source.size() == 0) return Collections.emptyList();
        List<E> res = new ArrayList<E>(source.size());
        for (Object o : source) {
            E e = destinationClass.newInstance();
            BeanUtils.copyProperties(o, e);
            res.add(e);
        }
        return res;
    }

}
