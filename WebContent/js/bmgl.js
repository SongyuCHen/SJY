/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".bmCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".bmCheckbox").prop("checked", false);
			$(this).text("全  选");
		}	
	});
	
	/**
	 * 上次的查询条件
	 */
	var lastBmlx = $("#lastBmlx").val();
	$("#bmlx").val(lastBmlx);
	
//	/**
//	 * uniform美化
//	 */
//	$("input[type='checkbox'],input[type='radio']").uniform();//单选框和复选框
//	$("input:file").uniform({//文件选择
//		fileDefaultHtml: '请选择文件',
//		fileButtonHtml: '浏览'
//	});
	
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
	
	/**
	 * 增加部门
	 */
	$('#addBmglLink').on('click', function(e){
		/* 部门名称 */
		$("#addBmmcLabel").css({"color":"#333"});
		$("#addBmmc").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addBmmc").val("");
		$("#validateAddBmmc").empty();
		
		/* 部门标识 */
		$("#addBmidLabel").css({"color":"#333"});
		$("#addBmid").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addBmid").val("");
		$("#validateAddBmid").empty();
		
		$("#addBmModal").modal('show');
	});
	
	/**
	 * 编辑用户
	 */
	$('#editBmglLink').on('click', function(e){
		e.preventDefault();
		var $checked = $('#bmglTable tbody :checked');
		if ($checked.size() < 1) {
			$.jGrowl('请选择要编辑的项', {
				life : 5000
			});
		} else if (($checked.size() > 1)) {
			$.jGrowl('只能同时编辑一项', {
				life : 5000
			});
		}else{
			//显示数据
			var tr = $(":checkbox:checked").closest("tr");
			var bmbh = tr.children("td:eq(0)").find("#bmbh").val();
			var bmmc = tr.children("td:eq(1)").html();
			//var bmid = tr.children("td:eq(2)").html();
			var bmid = tr.children("td:eq(0)").find("#bmbh").attr("data-bmid");
			var bmlx = tr.children("td:eq(2)").html();
			
			$("#editBmbh").val(bmbh);
			$("#editBmmc").val(bmmc);
			$("#editBmid").val(bmid);
			$("#editBmlx").val(bmlx);
			
			//处理样式
			//处理样式
			$("#editBmmc").parent().prev().css({"color":"#333"});
			$("#editBmmc").parent().next().empty();
			
			//处理样式
			$("#editBmid").parent().prev().css({"color":"#333"});
			$("#editBmid").parent().next().empty();
			
			$("#editBmModal").modal('show');
		}
	});
	
	/**
	 * 删除用户
	 */
	$('#deleteBmglLink').click(function(){
		var $checked = $('#bmglTable tbody :checked');
		
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
	                var action = basePath + "/bm/deleteBm";
	                form.attr('action', action).attr('method', 'post').append($checked);
	                form.appendTo(document.body).submit();
	            }
	        });
	    }
	});
	
	var bmglTable = $("#bmglTable").DataTable({
		"order": [[ 2, "asc" ]],
		"dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/**
	 * 确认增加部门
	 */
	confirmAdd = function(){
		$("#addBmmc").blur();
		$("#addBmid").blur();
		$("#addBmlx").blur();
		
		if(validAddBmmc && validAddBmid){
			$("#addBmForm").submit();
		}
	};
	
	/**
	 * 确认编辑部门
	 */
	confirmEdit = function(){
		$("#editBmmc").blur();
		$("#editBmid").blur();
		$("#editBmlx").blur();
		
		if(!validEditBmmc || !validEditBmid){
			return false;
		}
		
		var bmmc = $("#editBmmc").val();
		var bmid = $("#editBmid").val();
		var bmlx = $("#editBmlx").val();
		
		var $checked = $('#bmglTable tbody :checked');
		var tr = $checked.closest("tr");
		
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/bm/editBm",
			data:$('#editBmForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var status = data.status;
				if(status=="success"){
					$("#editBmModal").modal('hide');
					
					var text = "<strong>编辑成功！</strong>";
					$("#bmglSucInfo").empty().append(text);
					$("#bmglSuc").modal('show');
					
					//显示编辑的数据
					//tr.children("td:eq(2)").html(bmid);
					tr.children("td:eq(0)").find("#bmbh").attr("data-bmid", bmid);
					tr.children("td:eq(1)").html(bmmc);
					tr.children("td:eq(2)").html(bmlx);

				}
				else{
					$("#editBmModal").modal('hide');
					var text = "<strong>编辑时发生了错误</strong>";
					$("#bmglErrorInfo").empty();
					$("#bmglErrorInfo").append(text);
					$("#bmglError").modal('show');
				}	
			},
			error:function(request){
				$("#editBmModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#bmglErrorInfo").empty();
				$("#bmglErrorInfo").append(text);
				$("#bmglError").modal('show');
			}
		});
	};
	
	/*对addModal和editModal中的字段进行验证*/
	var validAddBmmc = false;
	var validAddBmid = false;
	
	var validEditBmmc = false;
	var validEditBmid = false;
	
	$("#addBmmc").blur(function(){
		var bmmc = $(this).val();
		if(!bmmc){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddBmmc = false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validAddBmmc = true;
		}
	});
	
	$("#addBmid").blur(function(){
		var bmid = $(this).val();
		if(!bmid){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddBmid = false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validAddBmid = true;
		}
	});
	
	$("#editBmmc").blur(function(){
		var bmmc = $(this).val();
		if(!bmmc){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditBmmc = false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validEditBmmc = true;
		}
	});
	
	$("#editBmid").blur(function(){
		var bmid = $(this).val();
		if(!bmid){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditBmid = false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validEditBmid = true;
		}
	});
});