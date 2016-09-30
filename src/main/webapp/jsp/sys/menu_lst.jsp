<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:0 20px 20px 20px;height:85%;">
		<table id="menus-table"></table>
	</div>
	<script type="text/javascript">		
		$(function(){
		    //1.初始化Table
		    var oTable = new TableInit();
		    oTable.Init();
		});
	</script>
</body>
<%@ include file="footer.jsp" %>