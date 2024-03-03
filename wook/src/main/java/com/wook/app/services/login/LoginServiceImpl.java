package com.wook.app.services.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.wook.app.services.login.vo.LoginVo;
import com.wook.app.services.login.vo.UserDetailsVo;

@Service
public class LoginServiceImpl implements AuthenticationProvider {

	@Autowired private LoginDao loginDao;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		String userId = (String) authentication.getPrincipal();
		String userPswd = (String) authentication.getCredentials();
		
		logger.info("로그인 시도 계정 => id : {}, pswd : {}", userId, userPswd);
		
		if(userId == null || "".equals(userId)) {
			throw new BadCredentialsException("로그인 아이디가 없습니다.");
		} else {
			param.put("userId", userId);
		}
		
		if(userPswd == null || "".equals(userPswd)) {
			throw new BadCredentialsException("로그인 비밀번호가 없습니다.");
		} else {
			param.put("userPswd", userPswd);
		}
		
		
		UserDetailsVo loginInfo = new UserDetailsVo();
		LoginVo userInfo = loginDao.getLoginInfo(param);
		
		if(userInfo != null) {
			loginInfo.setUserId(userInfo.getUserId());
			loginInfo.setUserPswd(userInfo.getUserPswd());
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if(userInfo != null) {
			roles.add(new SimpleGrantedAuthority(userInfo.getAuthrtCd()));
		} else {
			roles.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
		}
		
		UsernamePasswordAuthenticationToken result 
    	= new UsernamePasswordAuthenticationToken(userId, userPswd, roles);
		
		result.setDetails(loginInfo);
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("AuthenticationProvider > supports 호출");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
}
