package com.rms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rms.base.controller.BaseController;
import com.rms.model.po.TMenu;

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
	 * 菜单列表
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		List<TMenu> menus = iMenuService.findAll();
		model.addAttribute("menus", menus);
		return "sys/menu_lst";
	}
}
