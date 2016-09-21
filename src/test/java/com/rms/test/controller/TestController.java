package com.rms.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.test.model.po.User;
import com.rms.test.service.ITestSerive;

/**
 * 
 * Title:TestController
 * Description:Test控制器
 * @author    zwb
 * @date      2016年9月21日 下午12:18:03
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ITestSerive iTestSerive;
	
	@GetMapping("")
	@ResponseBody
    public String home(){
    	return "Hello World!";
    }
	
	@GetMapping("/welcome")
	public String error(){
		List<User> users = iTestSerive.findAll();
		System.out.println(users.size());
		return "welcome";
	}
}
