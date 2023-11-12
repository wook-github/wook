package com.wook.app.services.wc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.wook.app.services.wc.vo.WcBbsExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileExtendsVo;
import com.wook.app.services.wc.vo.WcBbsVo;

public interface WcService {

	public List<WcBbsExtendsVo> selectWcBbsList(HashMap<String, Object> param);
	
	public WcBbsVo getWcBbsInfo(HashMap<String, Object> param);
	
	public List<WcBbsFileExtendsVo> getWcBbsFileList(HashMap<String, Object> param);

	public int deleteWcBbsFile(List<String> wcBbsFileSnList);
	
	public HashMap<String, Object> insertWcBbs(HashMap<String, Object> param, List<MultipartFile> fileList, HttpServletRequest request);
	
	public HashMap<String, Object> updateWcBbs(HashMap<String, Object> param, List<MultipartFile> fileList, HttpServletRequest request);
	
	public HashMap<String, Object> updateWcBbs(WcBbsVo wcBbsInfo);
	
	public HashMap<String, Object> deleteWcBbs(HashMap<String, Object> param);
}
