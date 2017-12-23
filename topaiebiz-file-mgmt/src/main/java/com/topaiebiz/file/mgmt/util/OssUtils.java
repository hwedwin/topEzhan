package com.topaiebiz.file.mgmt.util;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import com.aliyun.oss.OSSClient;

public class OssUtils {
	
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	
	private static String accessKeyId = "LTAI2k4KFUXkRfwN";
	
	// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
	private static String accessKeySecret = "Q3xvsc6Rq42iC0QqDtKxGcI5Qa7MJB";
	
	private static String bucket = "qinziezhan";
	
	private static OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
	
	private OssUtils() {
	}
	
	/**
	 * Description： 给阿里云OSS上传文件
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param file  二进制文件
	 * @param suffixName 文件后缀
	 * @return
	 */
	public static String fileUpload(byte[] file, String suffixName) {
		// 创建OSSClient实例
		//文件名
		String fileName = UUID.randomUUID() + suffixName;
		// 上传
		ossClient.putObject(bucket, fileName, new ByteArrayInputStream(file));
		// 关闭client
//		ossClient.shutdown();
		return fileName;
	}
	
	/**
	 * Description： 删除阿里云OSS上的文件（单个） 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		ossClient.deleteObject(bucket, fileName);
	}
	
}
