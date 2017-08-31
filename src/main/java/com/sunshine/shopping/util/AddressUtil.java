/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: AddressUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/8/30 13:36
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiMG
 * @Title: AddressUtil
 * @Description: IP地址工具类
 * @date 2017/8/30 13:36
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AddressUtil {

    /**
     * @Title: getRemoteIP
     * @Description: 获取外网IP地址
     * @author LiMG
     * @date 2017/8/30 14:55
     * @see [类、类#方法、类#成员]
     */
    public static String getRemoteIP() {
        try {
            URL url = new URL("http://1212.ip138.com/ic.asp");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            br.close();
            webContent = sb.toString();
            int start = webContent.indexOf("[") + 1;
            int end = webContent.indexOf("]");
            webContent = webContent.substring(start, end);
            return webContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAddressRegion() {
        try {
            String ip = getRemoteIP();
//            String ip = "111.127.255.255";
            if (StringUtils.isBlank(ip)) {
                return null;
            }
            String host = "https://dm-81.data.aliyun.com";
            String path = "/rest/160601/ip/getIpInfo.json";
            String method = "GET";
            // 我的appcode码
            String appcode = "xxxxxxxxx";
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("ip", ip);
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            String result = EntityUtils.toString(response.getEntity());
            Map maps = (Map) JSON.parse(result);
            int flag = null == maps.get("code") ? -1 : Integer.parseInt(String.valueOf(maps.get("code")));
            if (0 == flag) {
                String data = String.valueOf(maps.get("data"));
                Map dataMap = (Map) JSON.parse(data);
                String region_id = null == dataMap.get("region_id") ? "" : String.valueOf(dataMap.get("region_id"));
                return region_id;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //"202.142.28.42"
        String region = AddressUtil.getAddressRegion();
        System.out.println(region);
    }

}
