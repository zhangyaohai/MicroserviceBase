package com.daizhen.controller.sys;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping(value = {"/listall", "/list/{username}"})
    public List<UserInfo> getAllUser(@PathVariable String username) {
    	
    	UserInfo param = new UserInfo();
    	if(StringUtils.isNotEmpty(username)) {
    		param.setUsername(username);
    	}
    	return userInfoService.getAll(param);
    	
    }
    
	
}
