package com.wook.app.services.wc;

import java.util.HashMap;
import java.util.List;

import com.wook.app.services.wc.vo.WcBbsExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileVo;
import com.wook.app.services.wc.vo.WcBbsVo;

public interface WcDao {

	public List<WcBbsExtendsVo> selectWcBbsList(HashMap<String, Object> param);
	
	public WcBbsVo getWcBbsInfo(HashMap<String, Object> param);
	
	public List<WcBbsFileExtendsVo> getWcBbsFileList(HashMap<String, Object> param);
	
	public int insertWcBbsFile(WcBbsFileVo wcBbsFileInfo);

	public int deleteWcBbsFile(List<String> wcBbsFileSnList);
	
	public int insertWcBbs(WcBbsVo wcBbsVo);
	
	public int updateWcBbs(WcBbsVo wcBbsVo);
	
	public int deleteWcBbs(WcBbsVo wcBbsVo);
}
