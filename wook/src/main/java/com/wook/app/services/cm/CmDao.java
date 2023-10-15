package com.wook.app.services.cm;

import java.math.BigDecimal;

import com.wook.app.services.cm.vo.CmFileVo;

public interface CmDao {

	/********************************/
	/*		1. 공통 파일 관리			*/
	/********************************/

	/**
	 * 파일정보를 조회한다.
	 * @param fileSeq String
	 * @return CmFileVo
	 */
	public CmFileVo getCmFileInfo(BigDecimal fileSn);

	/**
	 * 파일 정보를 등록한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public BigDecimal insertCmFile(CmFileVo param);

	/**
	 * 파일 정보를 수정한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public BigDecimal updateCmFile(CmFileVo param);

	/**
	 * 파일 정보를 삭제한다.
	 * @param fileSeq String
	 * @return BigDecimal
	 */
	public BigDecimal deleteCmFile(BigDecimal fileSn);
}
