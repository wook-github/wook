package com.wook.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView main(@RequestParam HashMap<String,Object> param
			, HttpServletResponse response
			, ModelAndView model) throws Exception {

		response.setHeader("Content-Security-Policy", "default-src http:; script-src http: 'unsafe-inline' 'unsafe-eval'; style-src http: 'unsafe-inline'; object-src http:; img-src http: data:; worker-src http: blob:;");

		model.addObject("info", param);

		model.setViewName("main/main");
		return model;
	}

	@RequestMapping(value = "/main/include", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView include(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletResponse response
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);

		response.setHeader("Content-Security-Policy", "default-src http:; script-src http: 'unsafe-inline' 'unsafe-eval'; style-src http: 'unsafe-inline'; object-src http:; img-src http: data:; worker-src http: blob:;");

		model.setViewName("main/include");

		return model;
	}

	@RequestMapping(value = "/main/header", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView header(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);
		model.setViewName("main/header");
		return model;
	}

	@RequestMapping(value = "/main/nav", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView nav(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);
		model.setViewName("main/nav");
		return model;
	}

	@RequestMapping(value = "/main/article", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView article(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);
		model.setViewName("main/article");
		return model;
	}
	
	@RequestMapping(value = "/main/search", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView search(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);
		model.setViewName("main/search");
		return model;
	}

	@RequestMapping(value = "/main/footer", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView footer(@RequestParam HashMap<String,Object> param
			, ModelAndView model
			, HttpServletRequest request) throws Exception {

		model.addObject("info", param);
		model.setViewName("main/footer");
		return model;
	}


}
