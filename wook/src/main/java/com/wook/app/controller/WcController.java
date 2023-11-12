package com.wook.app.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.wook.app.services.wc.WcService;
import com.wook.app.services.wc.vo.WcBbsVo;

@Controller
@RequestMapping("/wc")
public class WcController {

	//private static final Logger logger = LoggerFactory.getLogger(WcController.class);

	@Autowired private WcService wcService;

	@Resource MappingJackson2JsonView	jsonView;

	/* 공지사항 조회 */
	@RequestMapping(value = "/wcBbsMa", method = RequestMethod.GET)
	public ModelAndView wcBbsMa(@RequestParam HashMap<String, Object> param, ModelAndView model) throws Exception {
		model.addObject("info", param);
		model.setViewName("wc/wcBbsMa");
		return model;
	}

	@RequestMapping(value="/selectWcBbsList", method=RequestMethod.POST)
	public ModelAndView selectWcBbsList(@RequestParam HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();

		result.put("result", wcService.selectWcBbsList(param));
		return new ModelAndView(jsonView, result);
	}
	
	@RequestMapping(value="/getWcBbsFileList", method=RequestMethod.POST)
	public ModelAndView getWcBbsFileList(@RequestParam HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", wcService.getWcBbsFileList(param));
		return new ModelAndView(jsonView, result);
	}
	
	@RequestMapping(value="/deleteWcBbsFile", method=RequestMethod.POST)
	public ModelAndView deleteWcBbsFile(@RequestParam(value="wcBbsFileSnList[]") List<String> wcBbsFileSnList) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", wcService.deleteWcBbsFile(wcBbsFileSnList));
		return new ModelAndView(jsonView, result);
	}

	@RequestMapping(value="/wcBbsDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getWcBbsDetail(@RequestParam HashMap<String, Object> param, ModelAndView model) throws Exception {
		WcBbsVo wcBbsInfo = wcService.getWcBbsInfo(param);
		if(wcBbsInfo != null) {
			wcService.updateWcBbs(wcBbsInfo);
		}
		
		model.addObject("wcBbsInfo", wcBbsInfo);
		model.addObject("info", param);
		model.setViewName("wc/wcBbsDetail");
		return model;
	}
	
	@RequestMapping(value="/wcBbsUpdate", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getWcBbsUpdate(@RequestParam HashMap<String, Object> param, ModelAndView model) throws Exception {
		WcBbsVo wcBbsInfo = new WcBbsVo();
		
		if( param.get("pageMode") != null && param.get("pageMode") != "" && "U".equals(param.get("pageMode"))) {
			wcBbsInfo = wcService.getWcBbsInfo(param);
		}
				
		model.addObject("wcBbsInfo", wcBbsInfo);
		model.addObject("info", param);
		model.setViewName("wc/wcBbsUpdate");
		return model;
	}
	
	@RequestMapping(value="/insertWcBbs", method=RequestMethod.POST)
	public ModelAndView insertWcBbs(@RequestParam HashMap<String, Object> param
			, @RequestParam("attachFileList") List<MultipartFile> fileList
			, HttpServletRequest request) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", wcService.insertWcBbs(param, fileList, request));
		return new ModelAndView(jsonView, result);
	}
	
	@RequestMapping(value="/updateWcBbs", method=RequestMethod.POST)
	public ModelAndView updateWcBbs(@RequestParam HashMap<String, Object> param
			, @RequestParam("attachFileList") List<MultipartFile> fileList
			, @RequestParam(required = false, value = "deleteFileList") List<String> deleteFileList
			, HttpServletRequest request) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", wcService.updateWcBbs(param, fileList, request));
		if(deleteFileList != null && deleteFileList.size() > 0) {
			wcService.deleteWcBbsFile(deleteFileList);
		}
		return new ModelAndView(jsonView, result);
	}
	
	@RequestMapping(value="/deleteWcBbs", method=RequestMethod.POST)
	public ModelAndView deleteWcBbs(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", wcService.deleteWcBbs(param));
		return new ModelAndView(jsonView, result);
	}
}