package com.bms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Title:TokenInterceptor
 * Description:token拦截器
 * @author    zwb
 * @date      2016年11月8日 下午4:17:14
 *
 */
public class TokenInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		/*String requestURI = request.getRequestURI();
		boolean isValid = true;
		//需要token校验的url请求
		if((requestURI.indexOf("Submit") > -1)){//模拟
			isValid = TokenHelper.validToken(request);
		}
		
		if(WebUtil.isAjaxRequest(request)){
			
		}else{
			if(!isValid){
				request.getRequestDispatcher("/error").forward(request, response);
			}
			return isValid;
		}
		
		
		
		if(requestURI.startsWith("/login")){
			return true;
		}
*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
	}
}
