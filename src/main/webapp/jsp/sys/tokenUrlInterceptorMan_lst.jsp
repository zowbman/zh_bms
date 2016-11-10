<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">TokenUrl拦截管理</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6">
						<form id="customTokenUrls-form" action="/rms/tokenUrlInterceptorMan/customUrls/saveSubmit" method="POST" enctype="multipart/form-data">
							<div class="form-group">
						    	<div class="input-group">
						    		<span class="font_exp" style="display:none;">*</span><label style="display:none;">拦截名称、权限URL</label>
						    		<input type="text" name="tokenUrlInterceptor.name" class="form-control" style="width:50%;" placeholder="拦截名称">
							    	<input type="text" name="tokenUrlInterceptor.interceptorurl" class="form-control" style="width:50%;" placeholder="权限URL">
							    	<span class="input-group-btn">
							        	<button class="btn btn-default" type="submit">添加</button>
							     	</span>
							    </div><!-- /input-group -->
							</div>
							<div class="form-group">
						    	<label>自定义权限</label>
								<table id="customTokenUrls" class="table table-striped">
								</table>
							</div>
						</form>
					</div>
					<div class="col-md-6">	
						<form id="sysDefaultUrls-form" action="/rms/tokenUrlInterceptorMan/sysDefaultUrls/saveSubmit" method="POST" enctype="multipart/form-data">
							<div class="form-group">
								<button class="btn btn-default btn-block" type="submit">保存</button>
							</div>
							<div class="form-group">
								<label>默认权限</label>
								<ul id="privilegeTree">
									<%@ include file="/jsp/public/tokenPrivilege_ckbox.jsp"%>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#privilegeTree').treeview();
		$(function(){
			// 指定事件处理函数
			$("[name=privilegeIds]").click(function(){
				$(this).siblings("ul").find("input").prop("checked", this.checked);
				if(this.checked == true){
					$(this).parents("li").children("input").prop("checked", true);
				}
			});
		    //1.初始化Table
		    var oTable = new tokenUrlInterceptorTableInit();
		    oTable.Init();
		});
	</script>
</body>
<%@ include file="footer.jsp" %>