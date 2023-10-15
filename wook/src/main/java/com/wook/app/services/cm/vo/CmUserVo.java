package com.wook.app.services.cm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmUserVo extends CommVo {

	private String userId;
	private String userPswd;
	private String userNm;
	private String joinYmd;
	private String aprvYn;
	private String aprvYmd;
	private String autzrId;
	private String whdwlYn;
	private String whdwlYmd;
}
