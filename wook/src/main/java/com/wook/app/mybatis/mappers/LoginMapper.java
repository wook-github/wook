package com.wook.app.mybatis.mappers;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.wook.app.services.login.vo.LoginVo;

@Mapper
public interface LoginMapper {

	public LoginVo getLoginInfo(HashMap<String, Object> param) throws DataAccessException;
	
	public List<String> getLoginAuthrtList(HashMap<String, Object> param) throws DataAccessException;
}
