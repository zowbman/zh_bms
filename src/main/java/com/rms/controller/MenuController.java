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
import com.rms.model.po.TMenuCustom;
import com.rms.model.vo.PubRetrunMsg;
import com.rms.model.vo.TMenuVo;

/**
 * 
 * Title:MenuController
 * Description:菜单controller
 * @author    zwb
 * @date      2016年9月29日 下午6:24:09
 *
 */
@Controller
@RequestMapping("/rms/menu")
public class MenuController extends BaseController {
	
	/**
	 * 菜单列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		List<TMenu> menus = iMenuService.findAll();
		model.addAttribute("menus", menus);
		return "sys/menu_lst";
	}
	
	/**
	 * 从菜单父级列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/parentListData")
	@ResponseBody
	public PubRetrunMsg parentListData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TMenu> menus = iMenuService.findTopSlaveMenus();
		data.put("list", menus);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 从菜单子级列表数据
	 * @param parentId 父级id
	 * @return PubRetrunMsg
	 */
	@GetMapping("/childrenListData/{parentId}")
	@ResponseBody
	public PubRetrunMsg childrenListData(@PathVariable("parentId") Integer parentId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TMenu> menus = iMenuService.findChildrenSlaveMenus(parentId);
		data.put("list", menus);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 编辑页
	 * @param type 编辑类型
	 * @param id
	 * @return
	 */
	@GetMapping("/save/{type}/{id}")
	public String save(Model model, @PathVariable("type") String type,@PathVariable("id") Integer id){
		if("edit".equals(type)){
			TMenu menu = iMenuService.getById(id);
			TMenuCustom menuCustom = new TMenuCustom();
			BeanUtils.copyProperties(menu,menuCustom);
			model.addAttribute("menu", menuCustom);
			//主(Master)菜单
			List<TMenu> masterMenus = iMenuService.findMasterMenusByStatus(null);
			model.addAttribute("masterMenus", masterMenus);
			List<TMenu> parentMenus = iMenuService.findSlaveMenusIsNotMe(menu.getId());
			model.addAttribute("parentMenus", parentMenus);
		}else{//add
			
		}
		
		model.addAttribute("type", type);
		return "sys/menu_save";
	}	
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param menuVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TMenuVo menuVo){
		if("edit".equals(type)){
			//主变从
			//从变主
			
			//TMenu menu = iMenuService.getById(menuVo.getMenu().getId());
			//menu.setMenuname(menuVo.getMenu().getMenuname());//菜单名称
		}else{//add
			
		}
		return "result";
	}
}
