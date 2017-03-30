package com.daizhen.controller.webpagetest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daizhen.model.UserInfo;
import com.daizhen.service.UserInfoService;

@RestController
@RequestMapping("/webtest")
public class WebpageTestController {
	
	@Autowired
    private UserInfoService userInfoService;

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

    @RequestMapping(value = "/add")
    public ModelAndView add() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/userform");
        mav.addObject("userInfo",new UserInfo());
        return mav;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/userform");
        UserInfo userInfo = userInfoService.getById(id);
        mav.addObject("userInfo",userInfo);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        userInfoService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(UserInfo userInfo) {
        ModelMap result = new ModelMap();
        String msg = userInfo.getId() == null ? "新增成功!" : "更新成功!";
        userInfoService.save(userInfo);
        result.put("userInfo", userInfo);
        result.put("msg", msg);
        return result;
    }
	
}
