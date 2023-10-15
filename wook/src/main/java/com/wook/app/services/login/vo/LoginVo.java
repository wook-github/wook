package com.wook.app.services.login.vo;

import com.wook.app.services.cm.vo.CommVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginVo extends CommVo {

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
