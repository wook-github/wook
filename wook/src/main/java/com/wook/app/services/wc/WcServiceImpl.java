package com.wook.app.services.wc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wook.app.services.cm.CmService;
import com.wook.app.services.cm.vo.CmFileVo;
import com.wook.app.services.wc.vo.WcBbsExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileVo;
import com.wook.app.services.wc.vo.WcBbsVo;

@Service
public class WcServiceImpl implements WcService {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(WcServiceImpl.class);
	
	@Autowired CmService	cmService;
	@Autowired WcDao		wcDao;

	@Override
	public List<WcBbsExtendsVo> selectWcBbsList(HashMap<String, Object> param) {
		try {
			return wcDao.selectWcBbsList(param);
		} catch(DataAccessException e) {
			return new ArrayList<WcBbsExtendsVo>();
		}
	}

	@Override
	public WcBbsVo getWcBbsInfo(HashMap<String, Object> param) {
		try {
			return wcDao.getWcBbsInfo(param);
		} catch(DataAccessException e) {
			return new WcBbsVo();
		}
	}
	
	@Override
	public List<WcBbsFileExtendsVo> getWcBbsFileList(HashMap<String, Object> param) {
		try {
			if((String) param.get("bbsSn") != null && !"".equals((String)param.get("bbsSn"))) {
				return wcDao.getWcBbsFileList(param);
			} else {
				return new ArrayList<WcBbsFileExtendsVo>();
			}
		} catch(DataAccessException e) {
			LOG.error("게시판 상세정보 첨부파일 목록 조회에 실패하였습니다.");
			return new ArrayList<WcBbsFileExtendsVo>();
		}
	}
	
	@Override
	public int deleteWcBbsFile(List<String> wcBbsFileSnList) {
		try {
			return wcDao.deleteWcBbsFile(wcBbsFileSnList);
		} catch(DataAccessException e) {
			LOG.error("게시판 상세정보 첨부파일 삭제를 실패하였습니다.");
			return -1;
		}
	}

	@Override
	@Transactional
	public HashMap<String, Object> insertWcBbs(HashMap<String, Object> param, List<MultipartFile> fileList, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			//1. 게시판 상세정보 입력
			WcBbsVo wcBbsInfo = new WcBbsVo();
			wcBbsInfo.setBbsSe((String) param.get("bbsSe"));
			wcBbsInfo.setBbsTtl((String) param.get("bbsTtl"));
			wcBbsInfo.setBbsCn((String) param.get("bbsCn"));
			wcBbsInfo.setBbsFixYn((String) param.get("bbsFixYn"));
			wcBbsInfo.setRmks((String) param.get("rmks"));
			
			if(((String) param.get("pstgBgngYmd") != null || !"".equals((String) param.get("pstgBgngYmd")) ) && ((String) param.get("pstgEndYmd") != null || !"".equals((String) param.get("pstgEndYmd")))) {
				wcBbsInfo.setPstgBgngYmd((String) param.get("pstgBgngYmd"));
				wcBbsInfo.setPstgEndYmd((String) param.get("pstgEndYmd"));
				if(wcBbsInfo.getPstgBgngYmd() != null && !"".equals(wcBbsInfo.getPstgBgngYmd())) {
					wcBbsInfo.setPstgBgngYmd(wcBbsInfo.getPstgBgngYmd().replace("-", ""));
				}
				if(wcBbsInfo.getPstgEndYmd() != null && !"".equals(wcBbsInfo.getPstgEndYmd())) {
					wcBbsInfo.setPstgEndYmd(wcBbsInfo.getPstgEndYmd().replace("-", ""));
				}
				
			}
			
			wcBbsInfo.setUseYn("Y");
			wcBbsInfo.setMdfrId("ADMIN");
			wcBbsInfo.setMdfrIp("127.0.0.1");
			
			if(wcDao.insertWcBbs(wcBbsInfo) <= 0) {
				result.put("status", "fail");
				result.put("message", "게시판 등록 실패");
				return result;
			} else {
				result.put("status", "success");
				result.put("message", "게시판 등록 성공");
			}
			
			// 2. 첨부파일 체크
			if(!(fileList.isEmpty())) {
				for(MultipartFile mf : fileList) {
					// 3. 파일확장자 체크
					String fileExt = cmService.getFileExtension(mf.getOriginalFilename());
	
					if (!fileExt.equals("gif") && !fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") &&
					    !fileExt.equals("hwp") && !fileExt.equals("pdf") && !fileExt.equals("doc") && !fileExt.equals("docx") &&
					    !fileExt.equals("ppt") && !fileExt.equals("pptx") && !fileExt.equals("xls") && !fileExt.equals("xlsx") &&
					    !fileExt.equals("zip")) {
						LOG.error("게시판 등록시 첨부파일 확장자가 올바르지 않습니다.");
					    result.put("status",	"fail");
					    result.put("message",	"업로드 제한 파일(확장자)");
					    return result;
					}
					// 4. 파일업로드 및 파일정보 등록 체크
					CmFileVo cmFileVo = new CmFileVo();
					cmFileVo.setFileSe((String) param.get("fileSe"));				// 디렉토리 구분 : sy
					cmFileVo.setFilePath("/" + cmFileVo.getFileSe() + "/");			// 디렉토리 : /sy/
					//if(loginVo != null && loginVo.getUserId() != null && !"".equals(loginVo.getUserId())) {
					//	cmFileVo.setMdfrId(loginVo.getUserId());
					//} else {
						LOG.error("현재 사용자의 아이디 정보가 없어서 임의로 설정");
						cmFileVo.setMdfrId("ADMIN");
					//}
					
					cmFileVo = cmService.insertCmFile(cmFileVo, mf);
	
					if (cmFileVo != null) {
						// 5. 데이터 등록
						WcBbsFileVo wcBbsFileInfo = new WcBbsFileVo();
						wcBbsFileInfo.setFileSn(cmFileVo.getFileSn());
						wcBbsFileInfo.setBbsSn(wcBbsInfo.getBbsSn());
	
						if (wcDao.insertWcBbsFile(wcBbsFileInfo) > 0) {
						    result.put("status",	"success");
						    result.put("message",	"게시판 첨부파일 등록 성공");
						} else {
						    result.put("status",	"fail");
						    result.put("message",	"게시판 첨부파일 등록 실패");
						}
					} else {
						LOG.error("게시판 첨부파일 등록에 실패하였습니다.");
					    result.put("status",	"fail");
					    result.put("message",	"파일업로드 및 파일정보 등록 실패");
					    return result;
					}
				}
			}
		
			return result;
		
		} catch(DataAccessException e) {
			LOG.info("게시판 등록에 실패하였습니다.");
			result.put("status", "fail");
			result.put("message", "게시판 등록 실패");
			return result;
		}
	}

	@Override
	public HashMap<String, Object> updateWcBbs(HashMap<String, Object> param, List<MultipartFile> fileList, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			WcBbsVo wcBbsInfo = new WcBbsVo();
			wcBbsInfo.setBbsSn(new BigDecimal((String) param.get("bbsSn")));
			wcBbsInfo.setBbsTtl((String) param.get("bbsTtl"));
			wcBbsInfo.setBbsCn((String) param.get("bbsCn"));
			wcBbsInfo.setBbsFixYn((String) param.get("bbsFixYn"));
			wcBbsInfo.setRmks((String) param.get("rmks"));
			
			if(((String) param.get("pstgBgngYmd") != null || !"".equals((String) param.get("pstgBgngYmd")) ) && ((String) param.get("pstgEndYmd") != null || !"".equals((String) param.get("pstgEndYmd")))) {
				wcBbsInfo.setPstgBgngYmd((String) param.get("pstgBgngYmd"));
				wcBbsInfo.setPstgEndYmd((String) param.get("pstgEndYmd"));
				if(wcBbsInfo.getPstgBgngYmd() != null && !"".equals(wcBbsInfo.getPstgBgngYmd())) {
					wcBbsInfo.setPstgBgngYmd(wcBbsInfo.getPstgBgngYmd().replace("-", ""));
				}
				if(wcBbsInfo.getPstgEndYmd() != null && !"".equals(wcBbsInfo.getPstgEndYmd())) {
					wcBbsInfo.setPstgEndYmd(wcBbsInfo.getPstgEndYmd().replace("-", ""));
				}
				
			}
			
			wcBbsInfo.setMdfrId("ADMIN");
			wcBbsInfo.setMdfrIp("127.0.0.1");
			
			if(wcDao.updateWcBbs(wcBbsInfo) <= 0) {
				result.put("status", "fail");
				result.put("message", "게시판 수정 실패");
				return result;
			}
			
			//2. 첨부파일 체크
			if(!(fileList.isEmpty())) {
				for(MultipartFile mf : fileList) {
					// 3. 파일확장자 체크
					String fileExt = cmService.getFileExtension(mf.getOriginalFilename());

					if (!fileExt.equals("gif") && !fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") &&
					    !fileExt.equals("hwp") && !fileExt.equals("pdf") && !fileExt.equals("doc") && !fileExt.equals("docx") &&
					    !fileExt.equals("ppt") && !fileExt.equals("pptx") && !fileExt.equals("xls") && !fileExt.equals("xlsx") &&
					    !fileExt.equals("zip")) {
						LOG.error("게시판 수정시 첨부파일 확장자가 올바르지 않습니다.");
					    result.put("status",	"fail");
					    result.put("message",	"업로드 제한 파일(확장자)");
					    return result;
					}
					// 4. 파일업로드 및 파일정보 등록 체크
					CmFileVo cmFileVo = new CmFileVo();
					cmFileVo.setFileSe((String) param.get("fileSe"));		// 디렉토리 구분 : sy
					cmFileVo.setFilePath("/" + cmFileVo.getFileSe() + "/");	// 디렉토리 : /sy/
					//if(loginVo != null && loginVo.getUserId() != null && !"".equals(loginVo.getUserId())) {
					//	cmFileVo.setMdfrId(loginVo.getUserId());
					//} else {
						LOG.error("현재 사용자의 아이디 정보가 없어서 임의로 설정");
						cmFileVo.setMdfrId("ADMIN");
					//}

					cmFileVo = cmService.insertCmFile(cmFileVo, mf);

					if (cmFileVo != null) {
						// 5. 데이터 등록
						WcBbsFileVo wcBbsFileInfo = new WcBbsFileVo();
						wcBbsFileInfo.setFileSn(cmFileVo.getFileSn());
						wcBbsFileInfo.setBbsSn(wcBbsInfo.getBbsSn());

						if (wcDao.insertWcBbsFile(wcBbsFileInfo) > 0) {
						    result.put("status",	"success");
						    result.put("message",	"게시판 첨부파일 등록 성공");
						} else {
						    result.put("status",	"fail");
						    result.put("message",	"게시판 첨부파일 등록 실패");
						}
					} else {
						LOG.error("게시판 첨부파일 등록에 실패하였습니다.");
					    result.put("status",	"fail");
					    result.put("message",	"파일업로드 및 파일정보 등록 실패");
					    return result;
					}
				}
			} else {
				result.put("status", "success");
				result.put("message", "게시판 수정 성공");
			}
			return result;
		} catch(DataAccessException e) {
			LOG.info("게시판 수정에 실패하였습니다.");
			result.put("status", "fail");
			result.put("message", "게시판 수정 실패");
			return result;
		}
	}
	
	@Override
	public HashMap<String, Object> updateWcBbs(WcBbsVo wcBbsInfo) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String messageTitle = "조회수 증가";
		try {
			WcBbsVo bbsInfo = new WcBbsVo();
			bbsInfo.setBbsSn(wcBbsInfo.getBbsSn());
			bbsInfo.setBbsHits(wcBbsInfo.getBbsHits().add(new BigDecimal(1)));
			
			if(wcDao.updateWcBbs(bbsInfo) > 0) {
				result.put("status", "fail");
				result.put("message", "게시판 " + messageTitle + " 성공");
			} else {
				result.put("status", "fail");
				result.put("message", "게시판 " + messageTitle + " 실패");	
			}
			return result;
		} catch(DataAccessException e) {
			LOG.error(e.getMessage());
			result.put("status", "fail");
			result.put("message", "게시판 " + messageTitle + " 실패");
			return result;
		}
	}

	@Override
	public HashMap<String, Object> deleteWcBbs(HashMap<String, Object> param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			WcBbsVo wcBbsInfo = new WcBbsVo();
			wcBbsInfo.setBbsSn(new BigDecimal((String) param.get("bbsSn")));
			
			if(wcDao.deleteWcBbs(wcBbsInfo) > 0) {
				result.put("status", "success");
				result.put("message", "게시판 삭제 성공");
			} else {
				result.put("status", "fail");
				result.put("message", "게시판 삭제 실패");
			}
			return result;
		} catch(DataAccessException e) {
			LOG.info("게시판 삭제에 실패하였습니다.");
			result.put("status", "fail");
			result.put("message", "게시판 삭제 실패");
			return result;
		}
	}
}
