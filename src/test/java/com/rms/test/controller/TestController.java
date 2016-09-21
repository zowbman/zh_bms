package com.rms.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Title:TestController
 * Description:Test控制器
 * @author    zwb
 * @date      2016年9月21日 下午12:18:03
 *
 */
@RestController
public class TestController {
	 @RequestMapping("/")
	    String home() {
	        return "Hello World!";
	    }
}
