package com.wook.app.services.login.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsVo implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userPswd;
	private String userAuthrt;
	

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public String getPassword() {
		return userPswd;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPswd() {
		return userPswd;
	}
	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}
	public String getUserAuthrt() {
		return userAuthrt;
	}
	public void setUserAuthrt(String userAuthrt) {
		this.userAuthrt = userAuthrt;
	}

	@Override
	public String toString() {
		return "UserDetailsVo [userId=" + userId + ", userPswd=" + userPswd + ", userAuthrt=" + userAuthrt + "]";
	}
	
}
