package com.wook.app.mybatis.mappers;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.wook.app.services.wc.vo.WcBbsExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileVo;
import com.wook.app.services.wc.vo.WcBbsVo;

@Mapper
public interface WcMapper {

	List<WcBbsExtendsVo> selectWcBbsList(HashMap<String, Object> param) throws DataAccessException;
	
	WcBbsVo getWcBbsInfo(HashMap<String, Object> param) throws DataAccessException;
	
	public List<WcBbsFileExtendsVo> getWcBbsFileList(HashMap<String, Object> param) throws DataAccessException;
	
	public int insertWcBbsFile(WcBbsFileVo wcBbsFileInfo) throws DataAccessException;

	public int deleteWcBbsFile(List<String> wcBbsFileSnList) throws DataAccessException;
	
	int insertWcBbs(WcBbsVo wcBbsVo) throws DataAccessException;
	
	int updateWcBbs(WcBbsVo wcBbsVo) throws DataAccessException;
	
	int deleteWcBbs(WcBbsVo wcBbsVo) throws DataAccessException;
}
