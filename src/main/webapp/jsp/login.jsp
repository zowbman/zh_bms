<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>boboface|后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/css/index.css">
	<script src="/js/jquery.min.js"></script> 
	<script src="/js/login.js"></script> 
  </head>
  <body id="login-body">
  	<div class="container">
  		<form id="form-sigin" method="post" class="form-sigin" action="/loginSubmit">
  			<c:if test="${error != null }">
  				<span class="hint error" style="display:block;">${error }</span>
  			</c:if>
  		  	<span class="hint hint-useraccount">*请输入用户名</span>
  		  	<span class="hint hint-userpassword">*请输入密码</span>
	  		<input type="text" name="useraccount" class="form-control" placeholder="UserAccount">
	  		<input type="password" name="userpassword" class="form-control" placeholder="UserPassword">
	  		<button class="login-btn" type="submit">登陆</button>
  		</form>
  	<div>
  </body>
</html>
