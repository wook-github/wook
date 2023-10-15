package com.wook.app.services.cm;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.wook.app.services.cm.vo.CmFileVo;

public interface CmService {

	/********************************/
	/*		1. 공통 파일 관리			*/
	/********************************/
	
	/**
	 * 파일정보를 조회한다.
	 * @param fileSn BigDecimal
	 * @return CmFileVo
	 */
	public CmFileVo getCmFileInfo(BigDecimal fileSn);

	/**
	 * 업로드 파일 및 파일정보를 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return Decimal
	 */
	public BigDecimal saveCmFile(CmFileVo param, MultipartFile multipartFile);

	/**
	 * 업로드 파일 및 파일정보를 등록한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return BigDecimal
	 */
	public CmFileVo insertCmFile(CmFileVo param, MultipartFile multipartFile);

	/**
	 * 업로드 파일 및 파일정보를 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return BigDecimal
	 */
	public CmFileVo insertCmFileNm(CmFileVo param, MultipartFile multipartFile);

	/**
	 * 업로드 파일 및 파일정보를 수정한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return String
	 */
	public BigDecimal updateCmFile(CmFileVo param, MultipartFile multipartFile);

	/**
	 * 파일 정보를 삭제한다.
	 * @param fileSn BigDecimal
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> deleteCmFile(BigDecimal fileSn);

	/**
	 * 파일확장자를 조회한다.
	 * @param String String
	 * @return String
	 */
	public String getFileExtension(String fileNm);

	/**
	 * 파일이름를 조회한다.
	 * @param String String
	 * @return String
	 */
	public String getFileOrgnNm(String fileNm);
}
