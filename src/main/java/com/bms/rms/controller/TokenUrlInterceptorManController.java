package com.bms.rms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.base.controller.BaseController;
import com.bms.helper.CodeHelper.CODE;
import com.bms.rms.model.po.TPrivilegeCustom;
import com.bms.rms.model.po.TTokenUrlInterceptor;
import com.bms.rms.model.vo.PubRetrunMsg;

/**
 * 
 * Title:TokenUrlInterceptorManController
 * Description:TokenUrl拦截管理控制器
 * @author    zwb
 * @date      2016年11月8日 下午6:43:43
 *
 */
@Controller
@RequestMapping("/rms/tokenUrlInterceptorMan")
public class TokenUrlInterceptorManController extends BaseController {
	
	/**
	 * TokenUrl拦截管理列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model){
		//系统默认权限列表
		List<TPrivilegeCustom> privileges = iPrivilegeService.findPrivilegesForCascade();
		model.addAttribute("privileges", privileges);
		
		//拦截系统默认权限url(privilegeId not null)
		List<Integer> sysDefaultUrls = iTokenUrlInterceptorService.findAllSysDefaultUrlTokenInterceptor();
		model.addAttribute("sysDefaultUrls", sysDefaultUrls);
		
		//默认拦截自定义url
/*		List<TTokenUrlInterceptor> customUrls = iTokenUrlInterceptorService.findAllCustomUrlTokenInterceptor();
		model.addAttribute("customUrls", customUrls);*/
		
		return "sys/tokenUrlInterceptorMan_lst";
	}
	
	/**
	 * Token自定义拦截url
	 * @return
	 */
	@GetMapping("/customUrls")
	@ResponseBody
	public PubRetrunMsg customUrls(){
		Map<String, Object> data = new HashMap<String, Object>();
		//默认拦截自定义url
		List<TTokenUrlInterceptor> customUrls = iTokenUrlInterceptorService.findAllCustomUrlTokenInterceptor();
		data.put("customUrls", customUrls);
		return new PubRetrunMsg(CODE.D100000, data);
	}
}
