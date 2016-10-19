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
import com.rms.model.po.TMenu;
import com.rms.model.po.TPrivilege;
import com.rms.model.po.TPrivilegeCustom;
import com.rms.model.vo.PubRetrunMsg;
import com.rms.model.vo.TPrivilegeVo;
import com.rms.util.BaseUtil;

/**
 * 
 * Title:PrivilegeController
 * Description:权限管理控制类
 * @author    zwb
 * @date      2016年10月19日 下午12:01:07
 *
 */
@Controller
@RequestMapping("/rms/privilege")
public class PrivilegeController extends BaseController {
	
	/**
	 * 权限列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/privilege_lst";
	}
	
	/**
	 * 权限列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TPrivilege> privileges = iPrivilegeService.findAll();
		data.put("list", privileges);
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
		
		//获取底层菜单（没有子菜单的菜单）
		List<TMenu> menus = iMenuService.findAllBottomMenus();
		model.addAttribute("menus", menus);
		if("edit".equals(type)){
			TPrivilege privilege = iPrivilegeService.getById(id);
			TPrivilegeCustom privilegeCustom = new TPrivilegeCustom();
			BeanUtils.copyProperties(privilege,privilegeCustom);
			model.addAttribute("privilege", privilegeCustom);
		}else{//add
			TPrivilegeCustom privilege = new TPrivilegeCustom();
			privilege.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("privilege", privilege);
		}
		model.addAttribute("type", type);
		return "sys/privilege_save";
	}

	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param privilegeVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TPrivilegeVo privilegeVo){
		if("edit".equals(type)){
			TPrivilege privilege = iPrivilegeService.getById(privilegeVo.getPrivilege().getId());
			privilege.setPrivilegename(privilegeVo.getPrivilege().getPrivilegename());
			privilege.setPrivilegeurl(privilegeVo.getPrivilege().getPrivilegeurl());
			privilege.setMenuid(privilegeVo.getPrivilege().getMenuid() == -1 ? null : privilegeVo.getPrivilege().getMenuid());
			privilege.setAddtime((int)(privilegeVo.getAddtime().getTime() / 1000L));
			iPrivilegeService.updateSeletive(privilege);
		}else{//add
			TPrivilege privilege = new TPrivilege();
			privilege.setPrivilegename(privilegeVo.getPrivilege().getPrivilegename());
			privilege.setPrivilegeurl(privilegeVo.getPrivilege().getPrivilegeurl());
			privilege.setMenuid(privilegeVo.getPrivilege().getMenuid() == -1 ? null : privilegeVo.getPrivilege().getMenuid());
			privilege.setAddtime((int)(privilegeVo.getAddtime().getTime() / 1000L));
			iPrivilegeService.saveSeletive(privilege);
		}
		return "result";
	}
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	@GetMapping("/deletePrivilege/{id}")
	public String deletePrivilege(@PathVariable("id") Integer id){
		iPrivilegeService.delete(id);
		return "result";
	}
	
	/**
	 * 批量删除权限
	 * @param ids
	 * @return
	 */
	@PostMapping("/deletePrivileges")
	public String deletePrivileges(Integer[] ids){
		iPrivilegeService.delete(ids);
		return "result";
	}
}
