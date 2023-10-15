package com.wook.app.services.cm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommVo {

	private String useYn;
	private String mdfcnYmd;
	private String mdfrId;
	private String mdfrIp;
	private String rmks;

	private int rownum;
	private int page;
	private int totalCount;
}
