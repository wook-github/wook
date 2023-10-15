package com.wook.app.services.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired private LoginDao loginDao;

	@Override
	public String getUserInfo() {
		return loginDao.getUserInfo();
	}


}
