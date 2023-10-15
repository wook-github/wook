package com.wook.app.services.wc.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WcBbsFileVo {

	private BigDecimal bbsFileSn;		/* 게시판파일순번 */
	private BigDecimal bbsSn;			/* 게시판순번 */
	private BigDecimal fileSn;			/* 파일순번 */
}
