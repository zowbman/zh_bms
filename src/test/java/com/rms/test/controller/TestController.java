package com.rms.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("")
	@ResponseBody
    public String home(){
    	return "Hello World!";
    }
	
	@GetMapping("/welcome")
	public String error(){
		return "welcome";
	}
}
