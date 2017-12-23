package com.topaiebiz.file.mgmt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.file.mgmt.exception.FileMgmtExceptionEnum;
import com.topaiebiz.file.mgmt.util.OssUtils;


/**
 * Description 文件统一处理控制层
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月8日 上午11:15:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/file/mgmt")
public class FileMgmtController {
	
	/**
	 * 
	 * Description 上传单张图片。
	 * 
	 * Author Aaron.Xue
	 * 
	 * @param file
	 *            上传的文件名，name必须为picture
	 * @param module
	 *            上传文件的模块名
	 * @param func
	 *            上传文件的功能名
	 * @return 上传成功后的文件相对路径
	 * @throws Exception
	 *             上传失败，返回的异常信息
	 */
	@RequestMapping(path = "/addOnePicture", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addOnePicture(@RequestParam("picture") MultipartFile file, String module, String func)
			throws Exception {
		long start = System.currentTimeMillis();
		/** 校验文件是否为空。 */
		if (file.isEmpty()) {
			throw new GlobalException(FileMgmtExceptionEnum.FILE_IS_EMPTY);
		}
		/** 获取文件名，文件后缀。 */
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		/** 校验上传格式是否正确,如果不是jpg,jpeg,png的即不正确。 */
		if (!(".jpg".equalsIgnoreCase(suffixName) || ".jpeg".equalsIgnoreCase(suffixName)
				|| ".png".equalsIgnoreCase(suffixName))) {
			throw new GlobalException(FileMgmtExceptionEnum.FILE_FORMAT_IS_CORRECT);
		}
		//调用oss工具 上传文件
		fileName = OssUtils.fileUpload(file.getBytes(), suffixName);
		long end = System.currentTimeMillis();
		System.out.println("耗时-------------------------" + (end-start));
		return new ResponseInfo(fileName);
	}

	@RequestMapping(path = "/cancelOnePicture", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOnePicture(String fileName) {
		/** 将要删除的路径传给，文件服务器。 */
		OssUtils.deleteFile(fileName);
		return new ResponseInfo();
	}
	
//	@RequestMapping(path = "/addBase64Picture", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseInfo addBase64Picture(String base64Array) throws Exception {
//		String module = "goods";
//		String func = "details";
//		if(StringUtils.isEmpty(base64Array)) {
//			throw new GlobalException(FileMgmtExceptionEnum.FILE_IS_EMPTY);
//		}
//		String[] split = base64Array.split("[$]");
//		List<String> list = fileMgmtService.saveBase64Picture(split, FILE_PATH, module, func);
//		return new ResponseInfo(list);
//	}
	
}
