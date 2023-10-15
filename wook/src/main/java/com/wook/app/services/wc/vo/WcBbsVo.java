package com.wook.app.services.wc.vo;

import java.math.BigDecimal;

import com.wook.app.services.cm.vo.CommVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WcBbsVo extends CommVo {

	private BigDecimal 	bbsSn;
	private String 		bbsSe;
	private String 		bbsTtl;
	private String 		bbsCn;
	private BigDecimal	bbsHits;
	private String 		bbsFixYn;
	private String		pstgBgngYmd;
	private String		pstgEndYmd;
}
