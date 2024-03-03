package com.wook.app.services.login;

import java.util.HashMap;
import java.util.List;

import com.wook.app.services.login.vo.LoginVo;

public interface LoginDao {

	public LoginVo getLoginInfo(HashMap<String, Object> param);
	
	public List<String> getLoginAuthrtList(HashMap<String, Object> param);
}
