package com.sunshine.shopping.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * OSSUtil.java
 * Description: 阿里OSS工具类
 * Author: lmg
 * Date: 2017年1月20日 上午10:24:48
 */
public class OSSUtil {

	// 外网地址
	public static String endpoint = "xxxxx";
	// 内网地址
	// public static String endpoint = "http://oss-cn-qingdao-internal.aliyuncs.com";
	public static String accessKeyId = "xxxxx";
	public static String accessKeySecret = "xxxxx";
	public static String bucketName = "quandl";
	private static OSSClient client = null;
	private static String DOMAIN_URL = "http://image.loving1314.com/";

	public static OSSClient ossClient() {
		if (client == null)
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		return client;
	}
	
	/**
	 * 上传文流到OSS
	 */
	public static boolean putObjectByStream(String key,InputStream input) {
		boolean uploadStatus = false;
		try {
			ossClient().putObject(bucketName, key ,input);
			uploadStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadStatus;
	}
	
	/**
	 * 获取指定路径下所有的文件访问路径
	 * @param key
	 * @return
	 */
	public static List<String> getObjectsUrlByDir(String key) {
		ObjectListing objectListing = ossClient().listObjects(bucketName, key);
		List<OSSObjectSummary> summaries = objectListing.getObjectSummaries();
		List<String> urlList = new ArrayList<>();
		for (OSSObjectSummary summary : summaries) {
			String imageUrl = summary.getKey();
			urlList.add(DOMAIN_URL + imageUrl);
		}
		return urlList;
	}

}
