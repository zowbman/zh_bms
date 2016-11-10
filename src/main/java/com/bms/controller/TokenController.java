package com.bms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.base.controller.BaseController;
import com.bms.exception.CustomException;
import com.bms.helper.CodeHelper.CODE;
import com.bms.helper.TokenHelper;
import com.bms.rms.model.vo.PubRetrunMsg;

/**
 * 
 * Title:TokenController
 * Description:token接口
 * @author    zwb
 * @date      2016年11月8日 下午4:31:26
 *
 */
@Controller
public class TokenController extends BaseController {
	
	/**
	 * 获取tonken
	 * @return
	 * @throws CustomException 
	 */
	@GetMapping("/json/v1/token")
	@ResponseBody
	public PubRetrunMsg getToken(HttpServletRequest request) throws CustomException{
		Map<String, Object> data = new HashMap<String, Object>();
		TokenHelper.setToken(request);
		String tokenName = (String) request.getAttribute(TokenHelper.TOKEN_NAME_FIELD);
		String tokenValue = (String) request.getAttribute(tokenName);
		data.put(tokenName, tokenValue);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 校验token是否有效
	 * @param request
	 * @param token
	 * @return
	 */
	@GetMapping("/json/v1/validToken")
	@ResponseBody
	public PubRetrunMsg validToken(HttpServletRequest request){
		Map<String, Object> data = new HashMap<String, Object>();
		boolean isValid = TokenHelper.validToken(request);
		data.put("isValid", isValid);
		return new PubRetrunMsg(CODE.D100000, data);
	}

}
