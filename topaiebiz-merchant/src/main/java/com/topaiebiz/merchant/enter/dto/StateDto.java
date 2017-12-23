package com.topaiebiz.merchant.enter.dto;

import java.util.List;

public class StateDto {
	
	/**总状态。*/
	private Integer State;

	private List<MerchantauditLogDto> logList;

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public List<MerchantauditLogDto> getLogList() {
		return logList;
	}

	public void setLogList(List<MerchantauditLogDto> logList) {
		this.logList = logList;
	}

	
}
