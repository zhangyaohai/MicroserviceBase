package com.daizhen.controller.webpagetest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/webtest")
public class WebpageTestController {

    @RequestMapping(value = "/{sUser}/{sFace}/index.html", method = RequestMethod.GET)
    public ModelAndView startDianCai(
    		@PathVariable("sUser") String sUser,
            @PathVariable("sFace") String sFace,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("/index");
        mav.addObject("xname", "我人有的个，主产不为这");
        mav.addObject("ceshi1", sUser + sFace);
        mav.addObject("ceshi2", "lifengfeng");
        return mav;
    }
    
    @ResponseBody
    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public String ajax(@RequestParam("username") String username) {
        return username;
    }
	
}
