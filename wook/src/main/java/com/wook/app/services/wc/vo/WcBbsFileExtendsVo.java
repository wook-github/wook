package com.wook.app.services.wc.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WcBbsFileExtendsVo extends WcBbsFileVo {

	private String 				fileSe;				/* 파일_구분 */
	private String 				fileNm;				/* 파일_이름 */
	private String 				fileOrgnlNm;		/* 파일_원본_이름 */
	private String 				fileExtn;			/* 파일_확장자 */
	private String 				filePath;			/* 파일_경로 */
	private BigDecimal	 		fileSz;				/* 파일_크기 */
}
