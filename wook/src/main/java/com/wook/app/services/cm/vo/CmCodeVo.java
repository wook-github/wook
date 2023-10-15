package com.wook.app.services.cm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmCodeVo extends CommVo {

	private String cmcdTy;
	private String cmcdSe;
	private String cmcdCd;
	private String cmcdNm;
	private String cmcdExpln;
}
