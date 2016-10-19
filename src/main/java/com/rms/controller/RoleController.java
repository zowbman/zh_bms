package com.rms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rms.model.po.TRole;
import com.rms.model.po.TRoleCustom;
import com.rms.model.vo.PubRetrunMsg;
import com.rms.model.vo.TRoleVo;
import com.rms.util.BaseUtil;

/**
 * 
 * Title:RoleController
 * Description:角色管理controller
 * @author    zwb
 * @date      2016年10月19日 下午12:55:18
 *
 */
@Controller
@RequestMapping("/rms/role")
public class RoleController extends BaseController {
	
	/**
	 * 角色列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/role_lst";
	}
	
	/**
	 * 角色列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TRole> roles = iRoleService.findAll();
		data.put("list", roles);
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
		
		if("edit".equals(type)){
			TRole role = iRoleService.getById(id);
			TRoleCustom roleCustom = new TRoleCustom();
			BeanUtils.copyProperties(role,roleCustom);
			model.addAttribute("role", roleCustom);
		}else{//add
			TRoleCustom role = new TRoleCustom();
			role.setAddtime((int)BaseUtil.currentTimeMillis());
			role.setStatus((byte)0);
			model.addAttribute("role", role);
		}
		model.addAttribute("type", type);
		return "sys/role_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param privilegeVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TRoleVo roleVo){
		if("edit".equals(type)){
			TRole role = iRoleService.getById(roleVo.getRole().getId());
			role.setRolename(roleVo.getRole().getRolename());
			role.setAddtime((int)(roleVo.getAddtime().getTime() / 1000L));
			role.setStatus(roleVo.getRole().getStatus());
			iRoleService.updateSeletive(role);
		}else{//add
			TRole role = new TRole();
			role.setRolename(roleVo.getRole().getRolename());
			role.setStatus(roleVo.getRole().getStatus());
			role.setAddtime((int)(roleVo.getAddtime().getTime() / 1000L));
			iRoleService.saveSeletive(role);
		}
		return "result";
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteRole/{id}")
	public String deleteRole(@PathVariable("id") Integer id){
		iRoleService.delete(id);
		return "result";
	}
	
	/**
	 * 批量删除角色
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteRoles")
	public String deleteRoles(Integer[] ids){
		iRoleService.delete(ids);
		return "result";
	}
}
