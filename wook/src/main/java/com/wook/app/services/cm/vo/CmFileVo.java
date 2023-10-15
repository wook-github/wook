package com.wook.app.services.cm.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmFileVo extends CommVo {
    private BigDecimal	fileSn;			/* 파일순번 */
    private String		fileSe;			/* 파일구분 */
    private String		fileNm;			/* 파일이름 */
    private String		fileOrgnlNm;	/* 원본파일이름 */
    private String		fileExtn;		/* 확장자이름 */
    private String		filePath;		/* 경로이름 */
    private BigDecimal	fileSz;			/* 파일크기 */
}