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
import com.rms.model.vo.MenuTypeEnum;
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
		//List<TMenu> menus = iMenuService.findAll();
		//model.addAttribute("menus", menus);
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
		List<TMenu> menus = iMenuService.findTopMenus(MenuTypeEnum.slave);
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
			
			List<TMenu> masterMenus;
			//主(Master)菜单
			if(menuCustom.getMenutype() == MenuTypeEnum.master.getValue()){
				masterMenus = iMenuService.findMasterMenusByStatusAndNotMe(null, menuCustom.getId());
			}else{
				masterMenus = iMenuService.findMasterMenusByStatus(null);
			}
			model.addAttribute("masterMenus", masterMenus);
			if(menu.getParentid() != null){
				//父级菜单
				List<TMenu> parentMenus = iMenuService.findSlaveMenusIsNotMe(menu.getId());
				model.addAttribute("parentMenus", parentMenus);
			}
		}else{//add
			
		}
		
		model.addAttribute("type", type);
		return "sys/menu_save";
	}	
	
	/**
	 * 根据masterMenuId查询父级菜单
	 * @param masterMenuId
	 * @return
	 */
	@GetMapping("/parentListDataByMasterMenuId/{id}/{isNotMenuId}")
	@ResponseBody
	public PubRetrunMsg parentMenuListDataByMasterMenuId(@PathVariable("id") Integer masterMenuId, @PathVariable("isNotMenuId") Integer isNotMenuId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TMenu> menus = iMenuService.findParentMenusByMasterMenuIdIsNotMe(masterMenuId,isNotMenuId);
		data.put("list", menus);
		return new PubRetrunMsg(CODE.D100000, data);
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
			TMenu menu = iMenuService.getById(menuVo.getMenu().getId());
			menu.setMenuname(menuVo.getMenu().getMenuname());//菜单名称
			//主变从 或 从变主 
			if(menu.getMenutype() != menuVo.getMenu().getMenutype()){//查询出来对象和vo的菜单类型不相等
				if(menu.getMenutype() == MenuTypeEnum.master.getValue()){//本来是主，要换成从
					menu.setMastermenuid(menuVo.getMenu().getMastermenuid());//master菜单id
					menu.setParentid(menuVo.getMenu().getParentid() == -1 ? null : menuVo.getMenu().getParentid());//父级菜单id
				}else{//本来是从要换成主
					//把当前节点的子节点的parentId为空，且把masterMenuId设置为当前节点id
					iMenuService.updateSlaveToMasterMenu(menu.getMastermenuid(),menu.getId());
					menu.setMastermenuid(null);
					menu.setParentid(null);
					
				}
			}else if(menu.getMenutype() == MenuTypeEnum.slave.getValue()){//更改的是从节点
				menu.setMastermenuid(menuVo.getMenu().getMastermenuid());//master菜单id
				menu.setParentid(menuVo.getMenu().getParentid() == -1 ? null : menuVo.getMenu().getParentid());//父级菜单id
			}
			menu.setMenutype(menuVo.getMenu().getMenutype());//菜单类型
			menu.setSort(menuVo.getMenu().getSort());//排序
			menu.setStatus(menuVo.getMenu().getStatus());//启用状态
			menu.setAddtime((int) (menuVo.getAddtime().getTime() / 1000L));//添加时间修改
			iMenuService.updateSeletive(menu);
		}else{//add
			
		}
		return "result";
	}
}
