<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<form action="/rms/user/deleteUsers" method="post" onsubmit="return checkDelForm();">
	<div style="padding:0 20px 20px 20px;height:85%;">
		<div id="toolbar">
        	<button id="userBtn_add" type="button" class="btn btn-default access-control">
            	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        	</button>
	        <button id="userBtn_delete" type="submit" class="btn btn-default access-control">
	          	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
	       	</button>
        	<div id="hiddenInputs"></div>
    	</div>
		<table id="user-table"></table>
	</div>
	</form>
	<script type="text/javascript">		
		$(function(){
		    //1.初始化Table
		    var oTable = new userTableInit();
		    oTable.Init();
		});
	</script>
</body>
<%@ include file="footer.jsp" %>