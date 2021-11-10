package myMavenProject.FrontendPart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontController 
{
	@RequestMapping(value="/")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","My Shopping Destination");
		
		return mv;
	}
}
