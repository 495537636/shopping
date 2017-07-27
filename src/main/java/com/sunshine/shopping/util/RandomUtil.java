/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: RandomUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/24 15:58
 * @version: V1.0
 */

package com.sunshine.shopping.util;

/**
 * @Title: RandomUtil
 * @Description: <功能详细描述>
 * @author LiMG
 * @date 2017/7/24 15:58
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class RandomUtil {

    /**
     * @Title: generateSixLengthNum
     * @Description: 生成6位随机数
     * @author LiMG
     * @date 2017年6月5日 下午7:23:50
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generateSixLengthNum() {
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }

}
