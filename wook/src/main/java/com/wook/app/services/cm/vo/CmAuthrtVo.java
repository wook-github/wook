package com.wook.app.services.cm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmAuthrtVo extends CommVo {

	private String authrtCd;
	private String authrtNm;
	private String authrtExpln;
	private String crtYmd;
}
