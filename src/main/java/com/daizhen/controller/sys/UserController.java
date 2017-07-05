package com.daizhen.controller.sys;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daizhen.model.UserInfo;
import com.daizhen.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserInfoService userInfoService;
	
    @RequestMapping(value = "/validate/{username}/{password}")
    public UserInfo validate(@PathVariable String username, @PathVariable String password) {
        UserInfo userInfo = userInfoService.getByNameAndPassword(username, password);
        return userInfo;
    }
    
    @RequestMapping(value = {"/list"})
    public List<UserInfo> getAllUser(@RequestParam("username") String username) {
    	
    	UserInfo param = new UserInfo();
    	if(StringUtils.isNotEmpty(username)) {
    		param.setName(username);
    	}
    	param.setPage(null);
    	return userInfoService.getByName(param);
    	
    }
    
    @RequestMapping(value = {"/listpage"})
    public List<UserInfo> getAllUser(@RequestParam("username") String username,@RequestParam("page") String page) {
    	
    	UserInfo param = new UserInfo();
    	if(StringUtils.isNotEmpty(username)) {
    		param.setName(username);
    	}
    	param.setPage(Integer.valueOf(page));
    	return userInfoService.getByName(param);
    	
    }
    
    @RequestMapping(value = "/del/{id}")
    public void deluser(@PathVariable String id) {
    	userInfoService.deleteById(Integer.valueOf(id));
    }
    
    @RequestMapping(value = "/delBatch")
    public void delmoreuser(@RequestParam("ids") String ids) {
    	String[] id = ids.split(",");
    	for(int i = 0;i<id.length;i++) {
    		userInfoService.deleteById(Integer.valueOf(id[i]));
    	}
    	
    }
	
    @RequestMapping(value = "/save")
    public void saveuser(@RequestParam("data") String data) {
    	UserInfo user = (UserInfo) JSON.parseObject(data, UserInfo.class);  
    	userInfoService.save(user);
    }
}
