package com.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.base.controller.BaseController;
import com.rms.model.po.TGroup;
import com.rms.model.po.TUserCustom;

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
		//List<TUser> users = iUserService.findAll();
		//System.out.println(users.size());
		return "test,yes!";
	}
	
	@GetMapping("/jspTest")
	public 	String jspTestV1(Model model/*,RedirectAttributes redirectAttributes*/){
		//List<TUser> users = iUserService.findAll();
		//model.addAttribute("list", users);
		//redirectAttributes.addFlashAttribute("key1", "阿什顿");
		//return "redirect:welcome";
		
		/*List<TUser> users = iUserService.findAllUser();
		model.addAttribute("list", users);*/
		
		/*List<TUser> users = new ArrayList<TUser>();
		users.add(iUserService.findUserByIdAndUseraccount(1, "admin"));
		model.addAttribute("list", users);*/
		
		/*List<TUserCustom> userCustoms = new ArrayList<TUserCustom>();
		userCustoms.add(iUserService.findUserAndDepartmentByUserId(1));O
		model.addAttribute("list", userCustoms);*/
		
		/*List<TUser> users = iUserService.findAllUserV1();
		model.addAttribute("list", users);*/
		//List<TRole> roles = iRoleService.findAllRole();
		//System.out.println(roles.iterator().next().getRolename());
		
		System.out.println(iUserService.findAllUserAndDepartment().iterator().next().getDepartment().getDepartmentname());
		
		System.out.println(iUserService.findAllDepartmentAndUsers().iterator().next().getUsers().size());
		
		System.out.println(iUserService.findAllUserAndGroups().iterator().next().getGroups().size());
		
		for (TUserCustom userCustom : iUserService.findAllUserAndGroups()) {
			System.out.println(userCustom.getUseraccount());
			for (TGroup group: userCustom.getGroups()) {
				System.out.println("组别：" + group.getGroupname());
			}
			
			System.out.println("------");
		}
		
		return "welcome";
	}
}
