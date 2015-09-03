/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	$(".input-group").removeClass("timeGroup");
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".gzsjCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".gzsjCheckbox").prop("checked", false);
			$(this).text("全  选");
		}	
	});
	
//	/**
//	 * uniform美化
//	 */
//	$("input[type='checkbox'],input[type='radio']").uniform();//单选框和复选框
//	$("input:file").uniform({//文件选择
//		fileDefaultHtml: '请选择文件',
//		fileButtonHtml: '浏览'
//	});
	
//	/**
//	 * 所有静态select应用chosen美化
//	 */
//	$("select").chosen();
	
	/**
	 * jGrowl消息提示
	 */
	$.jGrowl.defaults.pool = 5;
	$.jGrowl.defaults.closerTemplate = '<h5>关闭全部</h5>';
	$.jGrowl.defaults.position = 'bottom-right';
	
	/**
	 * Alerts全局设置
	 */
	$.alerts.okButton = "确定";
	$.alerts.cancelButton = "取消";
	
	var gzsjTable = $("#gzsjTable").DataTable({
		"order": [[ 3, "asc" ]],
		"dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/**
	 * 日期选择控件
	 */
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
	    format: 'yyyy-mm',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 3,
		minView: 3,
		forceParse: 0,
		pickerPosition: "bottom-left"
	});
	
	/**
	 * 保存上次查询条件
	 */
	var curBmmc = $("#curBmmc").val();
	var curStartDate = $("#curStartDate").val();
	var curEndDate = $("#curEndDate").val();
	$("#bm").val(curBmmc);
	$("#startDate").val(curStartDate);
	$("#endDate").val(curEndDate);
	
	/**
	 * 重复添加失败提醒
	 */
	var status = $("#status").val();
	if(status=="fail"){
		var text = "<strong>添加失败！该人员在该年该月已有工作实绩，只能编辑，不能重复添加！</strong>";
		$("#gzsjErrorInfo").empty();
		$("#gzsjErrorInfo").append(text);
		$("#gzsjError").modal('show');
	}
		
	/**
	 * 配置项有多少个
	 */
	var pzSizeVal = $("#pzSize").val();
	var pzSize = parseInt(pzSizeVal);
	
	/**
	 * 部门和姓名的二级联动
	 */
	$("#addModalBm").change(function(){
		var bm = $("#addModalBm").val();
		$.ajax({
			url: basePath + '/user/getAccessNeedEvaluationRyxm.aj',
			type:'post',
			dataType:'json',
			data:{bm:bm},
			success:function(data){
				var userList = data.userList;
				$("#addModalXm").empty();
				var text;
				for(var i=0;i<userList.length;i++){
					text += "<option>" + userList[i].xm + "</option>";
				}
				$("#addModalXm").append(text);
			},
			error:function(){
				
			}
		});
	});
	
	/* 操作的点击事件 */
	$(".operationLi").on('click', function(e){
		var operate = $(this).attr("id");
		if(operate=="add"){
			addGzsj();
		}
		else if(operate=="edit"){
			editGzsj();
		}
		else if(operate=="delete"){
			deleteGzsj();
		}
		else if(operate=="approval"){
			approvalGzsj();
		}
		else if(operate=="print"){
			printGzsj();
		}
		else if(operate=="export"){
			exportGzsj();
		}
	});
	
	/* 增加工作实绩————响应按钮事件 */
	var addGzsj = function(){
		$("#addSjLabel").css({"color":"#333"});
		$("#addModalSj").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addSpan").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#validateAddSj").empty();
		
		$("#addModalSj").val("");
		
		for(var i=1;i<=pzSize;i++){
			$("#addpz"+i).removeClass("borderFail validateFail borderSuc validateSuc");
			$("#addpz"+i).parent().prev().css({"color":"#333"});
			$("#addpz"+i).parent().next().empty();
			
			$("#addpz"+i).val("");
		}
		
		$("#addGzsjModal").modal('show');
	};
	
	/* 编辑工作实绩————响应按钮事件 */
	var editGzsj = function(){
		//e.preventDefault();
		var $checked = $('#gzsjTable tbody :checked');
		if ($checked.size() < 1) {
			$.jGrowl('请选择要编辑的项', {
				life : 5000
			});
		} else if (($checked.size() > 1)) {
			$.jGrowl('只能同时编辑一项', {
				life : 5000
			});
		}
		else{
			//显示数据
			var tr = $(":checkbox:checked").closest("tr");
			var bh = tr.children("td:eq(0)").find("#gzsjBh").val();
			var ryxm = tr.children("td:eq(1)").html();
			var bmmc = tr.children("td:eq(2)").html();
			var rq = tr.children("td:eq(3)").html();
			
			$("#editModalBh").val(bh);
			$("#editModalXm").val(ryxm);
			$("#editModalBm").val(bmmc);
			$("#editModalSj").val(rq);
			
			for(var i=1;i<=pzSize;i++){
				var index = "td:eq(" + (3 + i) + ")";
				var pzVal = tr.children(index).children(0).html();
				$("#editpz"+i).val(pzVal);
				
				$("#editpz"+i).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				$("#editpz"+i).parent().prev().css({"color":"#3c763d"});
				$("#editpz"+i).parent().next().empty().append(
						'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');
			}
			
			$("#editGzsjModal").modal('show');
		}
	};
	
	/* 删除工作实绩————响应按钮事件 */
	var deleteGzsj = function(){
		var $checked = $('#gzsjTable tbody :checked');
		var bmmc = curBmmc;
		var startDate = curStartDate;
		var endDate = curEndDate;
		
		if($checked.size() < 1) {
	        $.jGrowl('请选择要删除的项', {
	            life: 5000
	        });
	    }
	    else {
	        $.alerts.dialogClass = 'alert-danger';
	        jConfirm('是否确认删除？', '确认对话框', function(r) {
	        	if(r == true) {
	                var form = $('<form></form>');
	                bmmc = encodeURI(encodeURI(bmmc));
	                var action = basePath + "/gzsj/delete?bmmc=" + bmmc + "&startDate=" + startDate + "&endDate=" + endDate;
	                form.attr('action', action).attr('method', 'post').append($checked);
	                form.appendTo(document.body).submit();
	            }
	        });
	    }
	};
	
	/* 审批————响应按钮事件 */
	var approvalGzsj = function(){
		var $checked = $('#gzsjTable tbody :checked');
		if($checked.size() < 1) {
	        $.jGrowl('请选择要进行审批的项', {
	            life: 5000
	        });
	    }
	    else {
	        $.alerts.dialogClass = 'alert-info';
	        jConfirm('是否确认审批这些记录？', '确认对话框', function(r) {
	        	var bhArr = "";
	        	$checked.each(function(){
	        		bhArr += bhArr==""?$(this).val():"-"+$(this).val();
	        	});
	        	
	    		$.ajax({
	    			cache:false,
	    			type:"POST",
	    			url:basePath + "/gzsj/approval",
	    			data:{bhArr:bhArr},
	    			dataType:'json',
	    			async:false,
	    			success:function(data){
	    				var result = data.result;
	    				var status = result.status;
	    				var msg = result.msg;
	    				if(status==5){
	    					var zt = result.zt;
	    					$checked.each(function(){
		    					var tr = $(this).closest("tr");
		    					var index = "td:eq(" + (4 + pzSize) + ")";
		    					tr.children(index).html(zt);
		    				});
	    				}
	    				else{
	    					var text = "<strong>" + msg + "</strong>";
	    					$("#gzsjErrorInfo").empty();
	    					$("#gzsjErrorInfo").append(text);
	    					$("#gzsjError").modal('show');
	    				}	
	    			},
	    			error:function(request){
	    				
	    			}
	    		});
	        });
	    }
	} ;
	
	/**
	 * 打印工作实绩
	 */
	var printGzsj = function(){
		var wind=window.open('about:blank', '', ''),
	    cont=$('#gzsjTable').html();
	    //打印样式
	    wind.document.write(
	    		'<style>@media print{.no-print{display:none;}.no-wrap{word-break: ' + 
	    		'keep-all;white-space:nowrap;}} @media all{th,td{border:1px solid #000;}' + 
	    		'table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	    wind.print();
	};
	
	var exportGzsj = function(){
		var bmmc = encodeURI(encodeURI(curBmmc));
		var startDate = curStartDate;
		var endDate = curEndDate;
		var url = basePath + "/gzsj/downloadExcel?bmmc=" + bmmc + "&startDate=" + startDate + "&endDate=" + endDate;
		window.location = url;
	};
	
	/**
	 * 确认增加工作实绩
	 */
	confirmAdd = function(){
		$("#addModalSj").change();
		for(var i=1;i<=pzSize;i++){
			$("#addpz"+i).blur();
			if(!validAddPz){
				return false;
			}
		}
		
		if(validAddTime && validAddPz){
			$("#addGzsjForm").submit();
		}
	};
	
	/**
	 * 确认编辑工作实绩
	 */
	confirmEdit = function(){
		//验证输入
		$("#editModalSj").change();
		for(var i=1;i<=pzSize;i++){
			$("#editpz"+i).blur();
			if(!validEditPz){
				return false;
			}
		}
		
		if(!validEditTime || !validEditPz){
			return false;
		}
		
		var $checked = $('#gzsjTable tbody :checked');
		var tr = $checked.closest("tr");
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/gzsj/edit",
			data:$('#editGzsjForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var statusMsg = data.StatusMsg;
				var status = statusMsg.status;
				
				if(status==1){
					$("#editGzsjModal").modal('hide');
					
					for(var i=1;i<=pzSize;i++){
						var value = $("#editpz"+i).val();
						var index = "td:eq(" + (3 + i) + ")";
						tr.children(index).children(0).html(value);
					}
					
					$("#gzsjSuc").modal('show');
				}
				else if(status==0){
					var msg = statusMsg.msg;
					var text = "<strong>" + msg + "</strong>";
					$("#gzsjErrorInfo").empty();
					$("#gzsjErrorInfo").append(text);
					$("#gzsjError").modal('show');
				}
	
			},
			error:function(request){
				$("#editGzsjModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#gzsjErrorInfo").empty();
				$("#gzsjErrorInfo").append(text);
				$("#gzsjError").modal('show');
			}
		});
	};
	/**
	 * 确认编辑提交工作实绩
	 */
	confirmEditSubmit = function(){
		//验证输入
		$("#editModalSj").change();
		for(var i=1;i<=pzSize;i++){
			$("#editpz"+i).blur();
			if(!validEditPz){
				return false;
			}
		}
		
		if(!validEditTime || !validEditPz){
			return false;
		}
		
		var $checked = $('#gzsjTable tbody :checked');
		var tr = $checked.closest("tr");
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/gzsj/editSubmit",
			data:$('#editGzsjForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var statusMsg = data.StatusMsg;
				var status = statusMsg.status;
				
				if(status==1){
					$("#editGzsjModal").modal('hide');
					
					for(var i=1;i<=pzSize;i++){
						var value = $("#editpz"+i).val();
						var index = "td:eq(" + (3 + i) + ")";
						tr.children(index).children(0).html(value);
					}
					var index = "td:eq(" + (4 + pzSize) + ")";
					tr.children(index).children(0).html('用户已提交');
					$("#gzsjSuc").modal('show');
				}
				else if(status==0){
					var msg = statusMsg.msg;
					var text = "<strong>" + msg + "</strong>";
					$("#gzsjErrorInfo").empty();
					$("#gzsjErrorInfo").append(text);
					$("#gzsjError").modal('show');
				}
	
			},
			error:function(request){
				$("#editGzsjModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#gzsjErrorInfo").empty();
				$("#gzsjErrorInfo").append(text);
				$("#gzsjError").modal('show');
			}
		});
	};
	
	/* 数值的click事件 */
	$(document).on('click', ".szHref", function(){
		var attrname = $('tr:first>th:eq('+$(this).parent().index()+')').html();
		var tr = $(this).parent().parent();
		var bh = tr.children("td:eq(0)").find("#gzsjBh").val();
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/sjygzl/detail.aj",
			data:{
				attrname:attrname,
				gzsjbh:bh,
				kssj:curStartDate,
				jssj:curEndDate
			},
			dataType:'json',
			async:false,
			success:function(data){
				var title = data.attrname;
				$("#sjygzlTitle").html(title);
				
				var gzsjbh = data.gzsjbh;
				$("#sjygzlGzsjbh").val(gzsjbh);
				
				cont = Mustache.to_html($('#sjygzlTableTemplate').html(), data);
				$('#sjygzlTableContent').html(cont);
				
				var sjygzlTable = $("#sjygzlTable").DataTable({
			        "order": [[ 0, "asc" ]],
			        "dom": '<pfltpi>',
					"pagingType": "full_numbers",
					"lengthChange": false
				});
				
				$("#sjygzlModal").modal('show');
			},
			error:function(request){
			}
		});
	});
	
	/* 图片的click事件 */
	$(document).on('click', ".czHref", function(){
		var tr = $(this).parent().parent();
		var bh = tr.children("td:eq(0)").find("#gzsjBh").val();
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/gzsj/previous.aj",
			data:{
				gzsjbh:bh
			},
			dataType:'json',
			async:false,
			success:function(data){
				var status = data.status;
				if(status == 1){
					cont = Mustache.to_html($('#previousTableTemplate').html(), data);
					$('#previousTableContent').empty().html(cont);
				}
				else{
					var text = "<h3>该记录未被编辑过</h3>";
					$('#previousTableContent').empty().html(text);
				}
				
				$("#previousModal").modal('show');
			},
			error:function(request){
			}
		});
	});
	
	$("#checkoutBtn").on('click', function(){
		var attrname = $("#sjygzlTitle").html();
		attrname = encodeURI(encodeURI(attrname));
		var bh = $("#sjygzlGzsjbh").val();
		var startDate = curStartDate;
		var endDate = curEndDate;
		
		var url = basePath + "/sjygzl/downloadExcel?attrname=" + attrname + "&bh=" + bh + "&kssj=" + startDate + "&jssj=" + endDate;
		window.location = url;
	});
	
	/*对addModal和editModal中的字段进行验证*/
	var regex = /^[0-9]*$/;/*正整数和0*/
	
	var validAddTime = false;
	var validAddPz = false;
	var validEditTime = false;
	var validEditPz = false;
	var i=1;
	for(i=1;i<=pzSize;i++){
		$("#addpz"+i).blur(function(){
			var sz = $(this).val();
			if(!sz){
				$(this).parent().prev().css({"color":"#a94442"});
				$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
				$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
				validAddPz=false;
			}
			else if(!regex.test(sz))
			{
				$(this).parent().prev().css({"color":"#a94442"});
				$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
				$(this).parent().next().empty().append('<label class="control-label validateFail">请输入数字</label>');
				validAddPz=false;
			}
			else
			{
				$(this).parent().prev().css({"color":"#3c763d"});
				$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
				$(this).parent().next().empty().append(
						'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			    validAddPz=true;
			}
		});
		
		$("#editpz"+i).blur(function(){
			var sz = $(this).val();
			if(!sz){
				$(this).parent().prev().css({"color":"#a94442"});
				$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
				$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
				validEditPz=false;
			}
			else if(!regex.test(sz))
			{
				$(this).parent().prev().css({"color":"#a94442"});
				$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
				$(this).parent().next().empty().append('<label class="control-label validateFail">请输入数字</label>');
				validEditPz=false;
			}
			else
			{
				$(this).parent().prev().css({"color":"#3c763d"});
				$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
				$(this).parent().next().empty().append(
						'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			    validEditPz=true;
			}
		});
	}
	
	$("#addModalSj").change(function(){
		var time = $(this).val();
		if(!time){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$("#addSpan").removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$("#validateAddSj").empty();
			$("#validateAddSj").append('<label class="control-label validateFail">不能为空</label>');
			validAddTime=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$("#addSpan").removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$("#validateAddSj").empty();
		    $("#validateAddSj").append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');
		    validAddTime=true;
		}
	});
	
	$("#editModalSj").change(function(){
		var time = $(this).val();
		if(!time){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$("#editSpan").removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$("#validateEditSj").empty();
			$("#validateEditSj").append('<label class="control-label validateFail">不能为空</label>');
			validEditTime=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$("#editSpan").removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$("#validateEditSj").empty();
		    $("#validateEditSj").append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');
		    validEditTime=true;
		}
	});
});


