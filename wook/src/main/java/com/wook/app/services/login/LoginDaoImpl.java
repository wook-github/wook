package com.wook.app.services.login;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wook.app.mybatis.mappers.LoginMapper;
import com.wook.app.services.login.vo.LoginVo;


@Repository
public class LoginDaoImpl implements LoginDao {

	@Resource(name="loginMapper")
	private LoginMapper loginMapper;

	@Override
	public LoginVo getLoginInfo(HashMap<String, Object> param) {
		return loginMapper.getLoginInfo(param);
	}

	@Override
	public List<String> getLoginAuthrtList(HashMap<String, Object> param) {
		return loginMapper.getLoginAuthrtList(param);
	}

}
