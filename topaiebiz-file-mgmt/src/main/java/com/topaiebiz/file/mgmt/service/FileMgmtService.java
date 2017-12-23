package com.topaiebiz.file.mgmt.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;

public interface FileMgmtService {

	List<String> saveBase64Picture(String[] base64Array,String filePath, String module, String func)  throws GlobalException;
	
}
