$(function(){
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
})
window.operateEvents = {
    'click .menu-edit': function (e, value, row, index) {
       window.location.href = '/rms/menu/save/edit?id=' + row.id;
    },
    'click .menu-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?（如有子级数据则会级联操作）'))
    		return false;
    	window.location.href = '/rms/menu/deleteMenu/' + row.id;
    }
};
//菜单表格
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function(menuType) {
		$('#menus-table').bootstrapTable({
			url : '/rms/menu/parentListData/' + menuType,
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			uniqueId : "id",
			detailView : true,
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			//注册加载子表的事件。注意下这里的三个参数！
			onExpandRow : function(index, row, $detail) {
				var oButtonInit = new ButtonInit();
				oButtonInit.InitSubTable(index, row, $detail);
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuicon',
				title : '菜单图标',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuname',
				title : '菜单名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menutype',
				title : '菜单类型',
				align: 'center',
				valign: 'middle'
					
			}, {
				field : 'mastermenuid',
				title : '主（Master）菜单',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'sort',
				title : '排序',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'status',
				title : '状态',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: operateFormatter
			} ]
		});
	}
	return oTableInit;
}

var ButtonInit = function() {
	var oInit = new Object();
	//初始化子表格(无线循环)
	oInit.InitSubTable = function(index, row, $detail) {
		var parentid = row.id;
		var cur_table = $detail.html('<table></table>').find('table');
		$(cur_table).bootstrapTable({
			url : '/rms/menu/childrenListData/' + parentid,
			method : 'get',
			clickToSelect : true,
			detailView : true,//父子表
			uniqueId : "id",
			pageSize : 10,
			pageList : [ 10, 25 ],
			columns : [ {
				field : 'id',
				title : 'ID',
				align: 'center'
			}, {
				field : 'menuicon',
				title : '菜单图标',
				align: 'center'
			}, {
				field : 'menuname',
				title : '菜单名称',
				align: 'center'
			}, {
				field : 'menutype',
				title : '菜单类型',
				align: 'center'
			}, {
				field : 'mastermenuid',
				title : '主（Master）菜单',
				align: 'center'
			}, {
				field : 'sort',
				title : '排序',
				align: 'center'
			}, {
				field : 'status',
				title : '状态',
				align: 'center'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				events : operateEvents,
				formatter: operateFormatter
			} ],
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			//无线循环取子表，直到子表里面没有记录
			onExpandRow : function(index, row, $Subdetail) {
				oInit.InitSubTable(index, row, $Subdetail);
			}
		});
	};
	oInit.Init = function(){
		$('#menuBtn_add').click(function(){
			 window.location.href = '/rms/menu/save/add';
		});
		$('#menuBtn_delete').click(function(){
			createHiddenInputs('#menus-table');
		})
	}
	return oInit;
}

//按钮
function operateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm menu-edit">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm menu-delete">删除</button>',
            '</div>'
    ].join('');
}

$(function(){
	//主菜单不用选择附属主菜单，从菜单可以选择父级菜单
	if($('#menu-form').find('input[name="menu.menutype"]').length != 0){
		menuType($('#menu-form').find('input[name="menu.menutype"]:checked'));
	}
	$('#menu-form').find('input[name="menu.menutype"]').change(function(){
		menuType(this);
	});
})

function menuType(_target){
	if($(_target).val() == 0){
		$('#mastermenus').parent().attr('style','display:none;');
		$('#parentmenus').parent().attr('style','display:none;');
		$('#mastermenus').parent().find('span').html('');
		$('#parentmenus').parent().find('span').html('');
	}else{//1
		$('#mastermenus').parent().attr('style','display:block;');
		$('#parentmenus').parent().attr('style','display:block;');
		$('#mastermenus').parent().find('span').html('*');
		$('#parentmenus').parent().find('span').html('*');
	}
}

//菜单编辑
$(function(){
	//主（Master）菜单选择
	if($('#mastermenus').length == 1){
		masterMenusLoad($('#mastermenus').val(),$('input[name="menu.id"]').val());
	}
	$('#mastermenus').change(function(){
		masterMenusLoad($('#mastermenus').val(),$('input[name="menu.id"]').val());
	});
	function masterMenusLoad(masterMenu,menuId){
		if(masterMenu != -1){
			$.ajax({
				type: 'GET',
				url: '/rms/menu/parentListDataByMasterMenuId/' + masterMenu,
				data:{
					isNotMenuId:menuId
				},
			    success: function(data){
			    	if(data.code != 100000){
			    		alert(data.msg);
			    	}else{
			    		$('#parentmenus').empty();
			    		$('#parentmenus').html('<option value="-1">请选择父级菜单</option>');
			    		$.each(data.data.list,function(i,item){
			    			$('#parentmenus').append('<option value="'+ item.id +'">'+ item.menuname +'</option>')
			    		});
			    	}
			    },
			    error: function() {  
			    	alert('请求异常');
		      	}
			});
		}else{//清空父级菜单
			$('#parentmenus').empty();
			$('#parentmenus').html('<option value="-1">请选择父级菜单</option>');
		}
	}
});




