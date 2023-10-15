package com.wook.app.mybatis.mappers;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.wook.app.services.cm.vo.CmFileVo;

@Mapper
public interface CmMapper {

	/********************************/
	/*		1. 공통 파일 관리			*/
	/********************************/

	/**
	 * 파일정보를 조회한다.
	 * @param fileSeq String
	 * @return CmFileVo
	 */
	public CmFileVo getCmFileInfo(BigDecimal fileSn)  throws DataAccessException;


	/**
	 * 파일 정보를 등록한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public int insertCmFile(CmFileVo param)  throws DataAccessException;

	/**
	 * 파일 정보를 수정한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public int updateCmFile(CmFileVo param)  throws DataAccessException;

	/**
	 * 파일 정보를 삭제한다.
	 * @param fileSeq String
	 * @return BigDecimal
	 */
	public int deleteCmFile(BigDecimal fileSn)  throws DataAccessException;
}
