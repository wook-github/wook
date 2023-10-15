package com.wook.app.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface LoginMapper {

	public String getUserInfo() throws DataAccessException;
}
