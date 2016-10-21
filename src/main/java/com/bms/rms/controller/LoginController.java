package com.bms.rms.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bms.base.controller.BaseController;
import com.bms.rms.model.po.TUser;

/**
 * 
 * Title:LoginController
 * Description:登陆controller
 * @author    zwb
 * @date      2016年10月21日 下午4:13:07
 *
 */
@Controller
public class LoginController extends BaseController {
	
	/**
	 * 登陆页
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}	
	
	/**
	 * 登陆
	 * @return
	 */
	@PostMapping("/loginSubmit")
	public String loginSubmit(Model model,String useraccount, String userpassword){
		//校验参数
		TUser user = iUserService.findUserByUserAccountAndUserPassword(useraccount,userpassword);
		
		if(user == null){
			model.addAttribute("error", "*您输入的帐号或密码有误");
			return "login";
		}
		System.out.println(user.getId());
		
		
		return "redirect:";
		
	}
	
	/**
	 * 注销
	 * @return
	 */
	@GetMapping("/logout")
	public String loginOut(){
		return "loginOut";
	}
}
