<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>RMS|权限管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/css/index.css">
	<link rel='stylesheet prefetch' href='/menu/css/foundation.css'>
	<link href="/menu/css/mtree.css" rel="stylesheet" type="text/css">
	<script src="/js/jquery-3.1.1.min.js"></script> 
	<style>
		.mtree-demo .mtree {
			background: #EEE;
			/*margin: 20px auto;*/
			max-width: 320px;
			border-radius: 3px;
			min-width: 200px;
		}
		
		ul.mtree.transit {
			background-color: #EEE;
		}
		
		.mtree-skin-selector {
			text-align: center;
			background: #EEE;
			padding: 10px 0 15px;
		}
		
		.mtree-skin-selector li {
			display: inline-block;
			float: none;
		}
		ul.mtree.transit a {
			color: black;
		}
		
		.mtree-skin-selector button {
			padding: 5px 10px;
			margin-bottom: 1px;
			background: #BBB;
		}
		
		.mtree-skin-selector button:hover {
			background: #999;
		}
		
		.mtree-skin-selector button.active {
			background: #999;
			font-weight: bold;
		}
		
		.mtree-skin-selector button.csl.active {
			background: #FFC000;
		}
	</style>
  </head>
  
  <body>
    <div>
    	<div id="head">
    		<div class="head-left">
    			<span>RMS权限管理系统<span>
    		</div>
    		<div class="head-right">
    			<span>xxx</span>
    			<a href="#">退出</a>
    		</div>
    	</div><!-- head结束 -->