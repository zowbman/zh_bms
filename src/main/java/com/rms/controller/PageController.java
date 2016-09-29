package com.rms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rms.base.controller.BaseController;
import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;


/**
 * 
 * Title:PageController
 * Description:jsp页面控制器
 * @author    zwb
 * @date      2016年9月29日 上午11:32:01
 *
 */
@Controller
public class PageController extends BaseController {
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("")
	public String index(Model model){
		List<TMenu> masterMenus =  iMenuService.findMasterMenusByStatus((byte)0);
		model.addAttribute("masterMenus", masterMenus);
		List<TMenuCustom> topSlaveMenus = iMenuService.findTopSlaveMenus();
		model.addAttribute("topSlaveMenus", topSlaveMenus);
		return "index";
	}
	
	/**
	 * 登陆页
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}	
}
