package com.wook.app.services.cm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wook.app.services.cm.vo.CmFileVo;

@Service
public class CmServiceImpl implements CmService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CmServiceImpl.class);
	
	private static final String SYNC1 = "CM_SYNC1" ;
	 
	private static final String SYNC2 = "CM_SYNC2" ;

	@Autowired CmDao cmDao;
	
	/********************************/
	/*		1. 공통 파일 관리			*/
	/********************************/

	/**
	 * 파일정보를 조회한다.
	 * @param fileSeq String
	 * @return CmFileVo
	 */
	public CmFileVo getCmFileInfo(BigDecimal fileSn){
		try {
			CmFileVo info = cmDao.getCmFileInfo(fileSn);
			if (info == null) info = new CmFileVo();
			return info;
		} catch (DataAccessException e) {
			return new CmFileVo();
		}
	}

	/**
	 * 업로드 파일 및 파일정보를 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return BigDecimal
	 */
	public BigDecimal saveCmFile(CmFileVo param, MultipartFile multipartFile) {
		param.setFileOrgnlNm(multipartFile.getOriginalFilename());					/* 원본파일명 */
		param.setFileExtn(getFileExtension(multipartFile.getOriginalFilename()));	/* 확장자 */
		param.setFileSz(new BigDecimal(multipartFile.getSize()));					/* 파일사이즈 */

		if (cmDao.insertCmFile(param).compareTo(BigDecimal.ZERO) == 1) {
			try {
				if (!saveToFile(param, multipartFile)) {
					cmDao.deleteCmFile(param.getFileSn());
					param.setFileSn(null);
				}
			} catch (IOException e) {
				cmDao.deleteCmFile(param.getFileSn());
				param.setFileSn(null);
			}
		}

		return param.getFileSn();
	}

	/**
	 * 업로드 파일 및 파일정보를 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return BigDecimal
	 */
	public CmFileVo insertCmFile(CmFileVo param, MultipartFile multipartFile) {
		CmFileVo cmFileVo = new CmFileVo();

		param.setFileOrgnlNm(multipartFile.getOriginalFilename());					/* 원본파일명 */
		param.setFileExtn(getFileExtension(multipartFile.getOriginalFilename()));	/* 확장자 */
		param.setFileSz(new BigDecimal(multipartFile.getSize()));					/* 파일사이즈 */

		if (cmDao.insertCmFile(param).compareTo(BigDecimal.ZERO) == 1) {
			cmFileVo = cmDao.getCmFileInfo(param.getFileSn());
			try {
				if (!saveToFile(cmFileVo, multipartFile)) {
					cmDao.deleteCmFile(cmFileVo.getFileSn());
					param.setFileSn(null);
				}
			} catch (IOException e) {
				cmDao.deleteCmFile(cmFileVo.getFileSn());
				param.setFileSn(null);
			}
		}

		return cmFileVo;
	}

	/**
	 * 업로드 파일 및 파일정보를 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return BigDecimal
	 */
	public CmFileVo insertCmFileNm(CmFileVo param, MultipartFile multipartFile) {
		CmFileVo cmFileVo = new CmFileVo();

		param.setFileOrgnlNm(multipartFile.getOriginalFilename());					/* 원본파일명 */
		param.setFileExtn(getFileExtension(multipartFile.getOriginalFilename()));	/* 확장자 */
		param.setFileSz(new BigDecimal(multipartFile.getSize()));					/* 파일사이즈 */

		if (cmDao.insertCmFile(param).compareTo(BigDecimal.ZERO) == 1) {
			cmFileVo = cmDao.getCmFileInfo(param.getFileSn());
			try {
				if (!saveToFileNm(cmFileVo, multipartFile)) {
					cmDao.deleteCmFile(cmFileVo.getFileSn());
					param.setFileSn(null);
				}
			} catch (IOException e) {
				cmDao.deleteCmFile(cmFileVo.getFileSn());
				param.setFileSn(null);
			}
		}

		return cmFileVo;
	}

	/**
	 * 업로드 파일 및 파일정보를 수정한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return String
	 */
	public BigDecimal updateCmFile(CmFileVo param, MultipartFile multipartFile) {
		param.setFileOrgnlNm(multipartFile.getOriginalFilename());					/* 원본파일명 */
		param.setFileExtn(getFileExtension(multipartFile.getOriginalFilename()));	/* 확장자 */
		param.setFileSz(new BigDecimal(multipartFile.getSize()));					/* 파일사이즈 */

		if (cmDao.updateCmFile(param).compareTo(BigDecimal.ZERO) == 1) {
			CmFileVo cmFileVo = cmDao.getCmFileInfo(param.getFileSn());

			try {
				if (deleteFile(param)) {
					LOG.info("파일 삭제 성공");
				}else {
					LOG.info("파일 삭제 실패");
				}

				if (!saveToFile(cmFileVo, multipartFile)) {
					cmDao.deleteCmFile(cmFileVo.getFileSn());
					param.setFileSn(null);
				}
			} catch (IOException e) {
				cmDao.deleteCmFile(cmFileVo.getFileSn());
				param.setFileSn(null);
			}
		}

		return param.getFileSn();
	}



	/**
	 * 업로드 파일을 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return String
	 */
	private boolean saveToFile(CmFileVo param, MultipartFile multipartFile) throws IOException {
		String uploadPath 	= "C:\\upload";
		String savePath 	= uploadPath + param.getFilePath();
		String fileNm		= savePath + param.getFileNm();

		boolean retFlag = false;

		BufferedOutputStream bos = null;
		try {
			java.io.File file = new java.io.File(savePath);
			if (!file.isDirectory()) {
				if (file.mkdirs()) {
					LOG.info("디렉토리 생성 성공");
				} else {
					LOG.info("디렉토리 생성 실패");
				}
			}

			byte[] bytes = multipartFile.getBytes();
			bos = new BufferedOutputStream(new FileOutputStream(fileNm));
			bos.write(bytes);
			bos.flush();
			bos.close();
			retFlag = true;
		} catch (FileNotFoundException ex) {
			LOG.info("Error : FileNotFoundException opening the input file: " + fileNm);
		} catch (IOException ex) {
			LOG.info("Error : IOException reading the input file.\n");
			throw new IOException(ex);
		} catch (Exception ex) {
			LOG.info("Error : Exception reading the input file.\n");
			throw new IOException(ex);
		}  finally {
			if(bos!=null) bos.close();
		}

		return retFlag;
	}


	/**
	 * 업로드 파일을 파일명으로 저장한다.
	 * @param param CmFileVo
	 * @param multipartFile MultipartFile
	 * @return String
	 */
	private boolean saveToFileNm(CmFileVo param, MultipartFile multipartFile) throws IOException {
		String uploadPath 	= "C:\\upload";
		String savePath 	= uploadPath + param.getFilePath();
		String saveFileNm	= savePath + param.getFileNm() + "." + param.getFileExtn();

		boolean retFlag = false;

		BufferedOutputStream bos = null;
		try {
			java.io.File file = new java.io.File(savePath);
			if (!file.isDirectory()) {
				if (file.mkdirs()) {
					LOG.info("디렉토리 생성 성공");
				} else {
					LOG.info("디렉토리 생성 실패");
				}
			}

			byte[] bytes = multipartFile.getBytes();
			bos = new BufferedOutputStream(new FileOutputStream(saveFileNm));
			bos.write(bytes);
			bos.flush();
			bos.close();
			retFlag = true; 
		} catch (FileNotFoundException ex) {
			LOG.info("Error : FileNotFoundException opening the input file: " + saveFileNm);
		} catch (IOException ex) {
			LOG.info("Error : IOException reading the input file.\n");
			throw new IOException(ex);
		} catch (Exception ex) {
			LOG.info("Error : Exception reading the input file.\n");
			throw new IOException(ex);
		}  finally {
			if(bos!=null) bos.close();
		}

		return retFlag;
	}

	/**
	 * 파일 및 정보를 삭제한다.
	 * @param fileSeq String
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> deleteCmFile(BigDecimal fileSn) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		synchronized(SYNC1) {
			CmFileVo cmFileVo = cmDao.getCmFileInfo(fileSn);
	
			String uploadPath 	= "C:\\upload";
			String savePath 	= uploadPath + cmFileVo.getFilePath();
			String fileNm	 	= savePath + cmFileVo.getFileNm();
			
			File file = new java.io.File(fileNm);
			if (file.exists()) {
				if (file.delete()){
					cmDao.deleteCmFile(fileSn);
					result.put("status", "success");
				}else{
					result.put("status", "fail");
				}
			} else {
				result.put("status", "success");
			}
		}
		return result;
	}


	/**
	 * 파일이름으로부터 확장자를 반환하는 메서드
	 * 파일이름에 확장자 구분을 위한 . 문자가 없거나. 가장 끝에 있는 경우는 빈문getFileExtension자열 ""을 리턴
	 */
	public String getFileExtension(String fileNm) {
		int dotPosition = fileNm.lastIndexOf('.');

		if (dotPosition != -1 && dotPosition < fileNm.length() - 1) {
			return fileNm.substring(dotPosition + 1).toLowerCase();
		} else {
			return "";
		}
	}

	/**
	 * 파일이름으로부터 확장자를 제외한 파일이름을 반환하는 메서드
	 * 파일이름에 확장자 구분을 위한 . 문자가 없거나. 가장 끝에 있는 경우는 빈문getFileExtension자열 ""을 리턴
	 */
	public String getFileOrgnNm(String fileNm) {
		int dotPosition = fileNm.lastIndexOf('.');

		if (dotPosition != -1 && dotPosition < fileNm.length() - 1) {
			return fileNm.substring(0, dotPosition).toLowerCase();
		} else {
			return "";
		}
	}


	/**
	 * 파일을 삭제한다.
	 * @param fileSeq String
	 * @return HashMap<String, Object>
	 */
	private boolean deleteFile(CmFileVo param) {
		
		boolean retFlag = false;
		synchronized(SYNC2) {
			String uploadPath 	= "C:\\upload";
			String savePath 	= uploadPath + param.getFilePath();
			String fileNm	 	= savePath + param.getFileNm();
		
			File file = new java.io.File(fileNm);
			if (file.exists()) {
				if (file.delete()){
					retFlag = true;
				}
			}
		}
		return retFlag;
	}
	
}
