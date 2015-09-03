package nju.software.sjy.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GztbController 
{
	private static Logger log = Logger.getLogger(GztbController.class);
	
	@RequestMapping("/gztb")
	public ModelAndView gztb()
	{
		log.info("come to the gztb page");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gztb");
		return mav;
	}
	
	
}
