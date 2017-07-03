/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: PropertiesUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/24 13:49
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @Title: PropertiesUtil
 * @Description: 操作properties文件工具类
 * @author LiMG
 * @date 2017/6/24 13:49
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class PropertiesUtil {

    private static Properties properties = null;

    /**
     * @Title: loadProperties
     * @Description: 加载properties文件
     * @author LiMG
     * @date 2017/6/23 15:42
     * @see [类、类#方法、类#成员]
     */
    public static void loadProperties(String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: getValue
     * @Description: 获取对应key的value值
     * @author LiMG
     * @date 2017/6/23 15:40
     * @see [类、类#方法、类#成员]
     */
    public static String getValue(String fileName, String key) {
        loadProperties(fileName);
        if (null != properties) {
            Object obj = properties.get(key);
            return null == obj ? null : String.valueOf(obj);
        }
        return null;
    }

}
