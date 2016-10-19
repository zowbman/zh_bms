package com.rms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.base.controller.BaseController;
import com.rms.helper.CodeHelper.CODE;
import com.rms.model.po.TDepartment;
import com.rms.model.po.TUser;
import com.rms.model.po.TUserCustom;
import com.rms.model.vo.PubRetrunMsg;
import com.rms.model.vo.TUserVo;
import com.rms.util.BaseUtil;

@Controller
@RequestMapping("/rms/user")
public class UserController extends BaseController {
	
	/**
	 * 用户列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/user_lst";
	}
	
	/**
	 * 用户组列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TUser> users = iUserService.findAll();
		data.put("list", users);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 编辑页
	 * @param type 编辑类型
	 * @param id
	 * @return
	 */
	@GetMapping("/save/{type}")
	public String save(Model model, @PathVariable("type") String type, Integer id){
		//...参数校验
		
		//部门列表
		List<TDepartment> departments = iDepartmentService.findAll();
		model.addAttribute("departments", departments);
		if("edit".equals(type)){
			TUser user = iUserService.getById(id);
			TUserCustom userCustom = new TUserCustom();
			BeanUtils.copyProperties(user,userCustom);
			userCustom.setUserpassword(null);
			model.addAttribute("user", userCustom);
		}else{//add
			TUserCustom user = new TUserCustom();
			user.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("user", user);
		}
		model.addAttribute("type", type);
		return "sys/user_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param groupVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TUserVo userVo){
		//...参数校验
		
		if("edit".equals(type)){
			TUser user = iUserService.getById(userVo.getUser().getId());
			user.setUseraccount(userVo.getUser().getUseraccount());
			user.setDepartmentid(userVo.getUser().getDepartmentid());
			user.setAddtime((int)(userVo.getAddtime().getTime() / 1000L));
			iUserService.updateSeletive(user);
		}else{//add
			//二次密码校验
			
			TUser user = new TUser();
			user.setUseraccount(userVo.getUser().getUseraccount());
			user.setDepartmentid(userVo.getUser().getDepartmentid());
			user.setAddtime((int)(userVo.getAddtime().getTime() / 1000L));
			user.setUserpassword(DigestUtils.md5Hex(userVo.getUserpassword()));
			iUserService.saveSeletive(user);
		}
		return "result";
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Integer id){
		iUserService.delete(id);
		return "result";
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteUsers")
	public String deleteUsers(Integer[] ids){
		iUserService.delete(ids);
		return "result";
	}
}
