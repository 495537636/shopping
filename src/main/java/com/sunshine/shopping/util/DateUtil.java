/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: DateUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/4 15:58
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: DateUtil
 * @Description: 日期时间工具类
 * @author LiMG
 * @date 2017/8/4 15:58
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class DateUtil {

    /**
     * @Title: countTimeFromCurrentToTomorrow
     * @Description: 计算当前时间到明天的时间差，单位秒s
     * @author LiMG
     * @date 2017/8/4 16:02
     * @see [类、类#方法、类#成员]
     */
    public static long countTimeFromCurrentToTomorrow() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        long currTime = currentDate.getTime();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date newDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(newDate));
        long tomorrowTime = newDate.getTime();
        System.out.println("currentTime=" + currTime);
        System.out.println("tomorrowTime=" + tomorrowTime);
        long time = (tomorrowTime - currTime) / 1000;
        return time;
    }

    public static void main(String[] args) {
        long time = DateUtil.countTimeFromCurrentToTomorrow();
        System.out.println(time);
    }

}
