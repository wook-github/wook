package com.wook.app.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

	//@Autowired private LoginService loginService;
	
	@RequestMapping(value = "/loginView", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView loginView(@RequestParam HashMap<String,Object> param, ModelAndView model) throws Exception {
		model.addObject("info", param);

		model.setViewName("login/loginView");
		return model;
	}

}
