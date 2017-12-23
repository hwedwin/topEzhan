package com.topaiebiz.file.mgmt.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.file.mgmt.exception.FileMgmtExceptionEnum;
import com.topaiebiz.file.mgmt.service.FileMgmtService;

@Service
public class FileMgmtServiceImpl implements FileMgmtService {

	@Override
	public List<String> saveBase64Picture(String[] base64Array, String filePath, String module, String func) throws GlobalException{
		List<String> resultList = new ArrayList<String>();
		if (base64Array == null || base64Array.length == 0) {
			throw new GlobalException(FileMgmtExceptionEnum.FILE_IS_EMPTY);
		} 
		for (String base64Data : base64Array) {
			if(base64Data.contains("http")) {
				resultList.add(base64Data);
				continue;
			}
			String[] d = base64Data.split("base64,");
			String dataPrix = ""; //格式结尾
			String data = "";     //数据
			if (d != null && d.length == 2) {
				dataPrix = d[0];
				data = d[1];
			} else {
				throw new GlobalException(FileMgmtExceptionEnum.FILE_FORMAT_IS_CORRECT);
			}
			String suffix = "";
			if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {// data:image/jpeg;base64,base64编码的jpeg图片数据
				suffix = ".jpeg";
			} else if ("data:image/jpg;".equalsIgnoreCase(dataPrix)) {// data:image/x-icon;base64,base64编码的icon图片数据
				suffix = ".jpg";
			} else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {// data:image/png;base64,base64编码的png图片数据
				suffix = ".png";
			} else {
				throw new GlobalException(FileMgmtExceptionEnum.FILE_FORMAT_IS_CORRECT);
			}
			String fileName = UUID.randomUUID().toString() + suffix;
			byte[] bs = Base64Utils.decodeFromString(data);
			try {
				// 使用apache提供的工具类操作流
				FileUtils.writeByteArrayToFile(new File(filePath + module + "/" + func + "/" + fileName), bs);
			} catch (Exception ee) {
				throw new GlobalException(FileMgmtExceptionEnum.FILE_FORMAT_IS_CORRECT);
			}
			resultList.add(module + "/" + func + "/" + fileName);
		}
		
		return resultList;
	}

}
