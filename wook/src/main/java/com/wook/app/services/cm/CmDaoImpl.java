package com.wook.app.services.cm;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wook.app.mybatis.mappers.CmMapper;
import com.wook.app.services.cm.vo.CmFileVo;

@Repository
public class CmDaoImpl implements CmDao {

	@Resource(name="cmMapper")
	private CmMapper cmMapper;

	/********************************/
	/*		1. 공통 파일 관리			*/
	/********************************/

	/**
	 * 파일정보를 조회한다.
	 * @param fileSeq String
	 * @return CmFileVo
	 */
	public CmFileVo getCmFileInfo(BigDecimal fileSn) {
		return cmMapper.getCmFileInfo(fileSn);
	}


	/**
	 * 파일 정보를 등록한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public BigDecimal insertCmFile(CmFileVo param){
		return new BigDecimal(cmMapper.insertCmFile(param));
	}

	/**
	 * 파일 정보를 수정한다.
	 * @param param CmFileVo
	 * @return BigDecimal
	 */
	public BigDecimal updateCmFile(CmFileVo param){
		return new BigDecimal(cmMapper.updateCmFile(param));
	}

	/**
	 * 파일 정보를 삭제한다.
	 * @param fileSeq String
	 * @return BigDecimal
	 */
	public BigDecimal deleteCmFile(BigDecimal fileSn){
		return new BigDecimal(cmMapper.deleteCmFile(fileSn));
	}
}
