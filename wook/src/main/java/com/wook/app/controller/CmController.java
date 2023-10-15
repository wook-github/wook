package com.wook.app.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cm")
public class CmController {

	/**
	 * �޼���  ������ �並 ȣ���Ѵ�.
	 * @param model ModelAndView
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/messageBox", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView messageBox(@RequestParam HashMap<String,Object> param
			, ModelAndView model) throws Exception {
		model.addObject("info", param);
		model.setViewName("common/messageBox");

		return model;
	}

}
