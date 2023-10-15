package com.wook.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wook.app.services.login.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired private LoginService loginService;

}
