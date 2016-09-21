package com.rms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.base.controller.BaseController;
import com.rms.model.po.TUser;

@Controller
@RequestMapping("/rms")
public class UserController extends BaseController {
	
	/**
	 * 通用mapper测试
	 * @return
	 */
	@GetMapping("/json/v1/userListTest")
	@ResponseBody
	public String userListTestV1(){
		List<TUser> users = iUserService.findAll();
		System.out.println(users.size());
		return "test,yes!";
	}
}
