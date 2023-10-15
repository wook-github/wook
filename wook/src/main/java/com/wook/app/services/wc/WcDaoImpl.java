package com.wook.app.services.wc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wook.app.mybatis.mappers.WcMapper;
import com.wook.app.services.wc.vo.WcBbsExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileExtendsVo;
import com.wook.app.services.wc.vo.WcBbsFileVo;
import com.wook.app.services.wc.vo.WcBbsVo;

@Repository
public class WcDaoImpl implements WcDao {

	@Resource(name="wcMapper")
	private WcMapper wcMapper;

	@Override
	public List<WcBbsExtendsVo> selectWcBbsList(HashMap<String, Object> param) {
		try {
			return wcMapper.selectWcBbsList(param);
		} catch(DataAccessException e) {
			return new ArrayList<WcBbsExtendsVo>();
		}
	}

	@Override
	public WcBbsVo getWcBbsInfo(HashMap<String, Object> param) {
		try {
			return wcMapper.getWcBbsInfo(param);
		} catch(DataAccessException e) {
			return new WcBbsVo();
		}
	}
	
	@Override
	public List<WcBbsFileExtendsVo> getWcBbsFileList(HashMap<String, Object> param) {
		try {
			return wcMapper.getWcBbsFileList(param);
		} catch(DataAccessException e) {
			return new ArrayList<WcBbsFileExtendsVo>();
		}
	}
	
	@Override
	public int insertWcBbsFile(WcBbsFileVo wcBbsFileInfo) {
		try {
			return wcMapper.insertWcBbsFile(wcBbsFileInfo);
		} catch(DataAccessException e) {
			return -1;
		}
	}
	
	@Override
	public int deleteWcBbsFile(List<String> wcBbsFileSnList) {
		try {
			return wcMapper.deleteWcBbsFile(wcBbsFileSnList);
		} catch(DataAccessException e) {
			return -1;
		}
	}

	@Override
	public int insertWcBbs(WcBbsVo wcBbsVo) {
		try {
			return wcMapper.insertWcBbs(wcBbsVo);
		} catch(DataAccessException e) {
			return -1;
		}
	}

	@Override
	public int updateWcBbs(WcBbsVo wcBbsVo) {
		try {
			return wcMapper.updateWcBbs(wcBbsVo);
		} catch(DataAccessException e) {
			return -1;
		}
	}

	@Override
	public int deleteWcBbs(WcBbsVo wcBbsVo) {
		try {
			return wcMapper.deleteWcBbs(wcBbsVo);
		} catch(DataAccessException e) {
			return -1;
		}
	}
}
