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
import com.rms.model.po.TGroup;
import com.rms.model.po.TGroupCustom;
import com.rms.model.vo.PubRetrunMsg;
import com.rms.model.vo.TGroupVo;
import com.rms.util.BaseUtil;

/**
 * 
 * Title:GroupController
 * Description:用户组contoller类
 * @author    zwb
 * @date      2016年10月19日 下午3:23:12
 *
 */
@Controller
@RequestMapping("/rms/group")
public class GroupController extends BaseController {
	
	/**
	 * 用户组列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/group_lst";
	}
	
	/**
	 * 用户组列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TGroup> groups = iGroupService.findAll();
		data.put("list", groups);
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
			TGroup group = iGroupService.getById(id);
			TGroupCustom groupCustom = new TGroupCustom();
			BeanUtils.copyProperties(group,groupCustom);
			model.addAttribute("group", groupCustom);
		}else{//add
			TGroupCustom group = new TGroupCustom();
			group.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("group", group);
		}
		model.addAttribute("type", type);
		return "sys/group_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param groupVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TGroupVo groupVo){
		if("edit".equals(type)){
			TGroup group = iGroupService.getById(groupVo.getGroup().getId());
			group.setGroupname(groupVo.getGroup().getGroupname());
			group.setAddtime((int)(groupVo.getAddtime().getTime() / 1000L));
			iGroupService.updateSeletive(group);
		}else{//add
			TGroup group = new TGroup();
			group.setGroupname(groupVo.getGroup().getGroupname());
			group.setAddtime((int)(groupVo.getAddtime().getTime() / 1000L));
			iGroupService.saveSeletive(group);
		}
		return "result";
	}
	
	/**
	 * 删除用户组
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteGroup/{id}")
	public String deleteGroup(@PathVariable("id") Integer id){
		iGroupService.delete(id);
		return "result";
	}
	
	/**
	 * 批量删除用户组
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteGroups")
	public String deleteGroups(Integer[] ids){
		iGroupService.delete(ids);
		return "result";
	}
}
