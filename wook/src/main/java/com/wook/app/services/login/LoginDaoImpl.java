package com.wook.app.services.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wook.app.mybatis.mappers.LoginMapper;


@Repository
public class LoginDaoImpl implements LoginDao {

	@Resource(name="loginMapper")
	private LoginMapper loginMapper;

	@Override
	public String getUserInfo() {
		System.out.println(loginMapper.getUserInfo());
		return loginMapper.getUserInfo();
	}


}
