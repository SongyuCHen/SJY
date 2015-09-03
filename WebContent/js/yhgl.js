/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".ryCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".ryCheckbox").prop("checked", false);
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
	
	/**
	 * 所有静态select应用chosen美化
	 */
	//$("select").chosen();
	
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
	 * 保存上次查询条件
	 */
	var lastBm = $("#lastBm").val();
	var lastRole = $("#lastRole").val();
	$("#bm").val(lastBm);
	$("#role").val(lastRole);
		
	/**
	 * 增加用户
	 */
	$('#addYhglLink').on('click', function(e){
		/*姓名*/
		$("#addXmLabel").css({"color":"#333"});
		$("#addXm").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addXm").val("");
		$("#validateAddXm").empty();		
		
		/*用户名*/
		$("#addUsernameLabel").css({"color":"#333"});
		$("#addUsername").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addUsername").val("");
		$("#validateAddUsername").empty();
		
		/*密码*/
		$("#addPasswordLabel").css({"color":"#333"});
		$("#addPassword").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addPassword").val("");
		$("#validateAddPassword").empty();
		
		$("#addUserModal").modal('show');
	});
	
	/**
	 * 编辑用户
	 */
	 $('#editYhglLink').on('click', function(e){
		e.preventDefault();
		var $checked = $('#yhglTable tbody :checked');
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
			var bh = tr.children("td:eq(0)").find("#rybh").val();
			var ryxm = tr.children("td:eq(1)").html();
			var bmmc = tr.children("td:eq(2)").html();
			var role = tr.children("td:eq(3)").html();
			var username = tr.children("td:eq(4)").html();
			var password = tr.children("td:eq(5)").find("#password").val();
			
			$("#editBh").val(bh);
			$("#editXm").val(ryxm);
			$("#editBm").val(bmmc);
			$("#editRole").val(role);
			$("#editUsername").val(username);
			$("#editPassword").val(password);
			
			//处理样式
			$("#editXm").parent().prev().css({"color":"#333"});
			$("#editXm").parent().next().empty();
			
			$("#editUsername").parent().prev().css({"color":"#333"});
			$("#editUsername").parent().next().empty();
			
			$("#editPassword").parent().prev().css({"color":"#333"});
			$("#editPassword").parent().next().empty();
			
			$("#editUserModal").modal('show');
		}
	});
	 
	/**
	 * 删除用户
	 */
	$('#deleteYhglLink').click(function(){
		var $checked = $('#yhglTable tbody :checked');
		var bmmc = $("#lastBm").val();
		
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
	                var action = basePath + "/user/deleteUser";
	                form.attr('action', action).attr('method', 'post').append($checked);
	                form.appendTo(document.body).submit();
	              }
	        });
	    }
	});
	 
	 
	/**
	 * 打印用户
	 */
	$("#printYhglLink").on('click', function(){
		var wind=window.open('about:blank', '', ''),
	      cont=$('#yhglTable').html();
	      //打印样式
	      wind.document.write('<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} @media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	      wind.print();
	});
	
	/**
	 * 确认增加用户
	 */
	confirmAdd = function(){
		$("#addXm").blur();
		$("#addUsername").blur();
		$("#addPassword").blur();
		
		if(validAddXm && validAddUsername && validateAddPassword){
			$("#addUserForm").submit();
		}
	};
	
	/**
	 * 确认编辑用户
	 */
	confirmEdit = function(){
		//验证输入
		$("#editXm").blur();
		$("#editBm").blur();
		$("#editUsername").blur();
		$("#editPassword").blur();
		
		if(!validEditXm || !validEditUsername || !validEditPassword){
			return false;
		}
		
		var ryxm = $("#editXm").val();
		var bmmc = $("#editBm").val();
		var rolename = $("#editRole").val();
		var username = $("#editUsername").val();
		var password = $("#editPassword").val();
		
		var $checked = $('#yhglTable tbody :checked');
		var tr = $checked.closest("tr");
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/user/editUser",
			data:$('#editUserForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var status = data.status;
				if(status=="success"){
					$("#editUserModal").modal('hide');
					
					var text = "<strong>编辑成功！</strong>";
					$("#yhglSucInfo").empty().append(text);
					$("#yhglSuc").modal('show');
					
					//显示编辑的数据
					tr.children("td:eq(1)").html(ryxm);
					tr.children("td:eq(2)").html(bmmc);
					tr.children("td:eq(3)").html(rolename);
					tr.children("td:eq(4)").html(username);
					tr.children("td:eq(5)").find("#password").val(password);

				}
				else{
					$("#editUserModal").modal('hide');
					var text = "<strong>编辑时发生了错误</strong>";
					$("#yhglErrorInfo").empty();
					$("#yhglErrorInfo").append(text);
					$("#yhglError").modal('show');
				}	
			},
			error:function(request){
				$("#editUserModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#yhglErrorInfo").empty();
				$("#yhglErrorInfo").append(text);
				$("#yhglError").modal('show');
			}
		});
	};
	
	var yhglTable = $("#yhglTable").DataTable({
		//"order": [[ 1, "asc" ]],
		"dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/*对addModal和editModal中的字段进行验证*/
	var validAddXm = false;
	var validAddUsername = false;
	var validAddPassword = false;
	
	var validEditXm = false;
	var validEditUsername = false;
	var validEditPassword = false;
	
	$("#addXm").blur(function(){
		var xm = $(this).val();
		if(!xm){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddXm=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validAddXm=true;
		}
	});
	
	$("#addUsername").blur(function(){
		var username = $(this).val();
		if(!username){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddUsername=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validAddUsername=true;
		}
	});
	
	$("#addPassword").blur(function(){
		var password = $(this).val();
		if(!password){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddPassword=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validAddPassword=true;
		}
	});
	
	//editModal验证
	$("#editXm").blur(function(){
		var xm = $(this).val();
		if(!xm){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditXm=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validEditXm=true;
		}
	});
	
	$("#editUsername").blur(function(){
		var username = $(this).val();
		if(!username){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditUsername=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validEditUsername=true;
		}
	});
	
	$("#editPassword").blur(function(){
		var password = $(this).val();
		if(!password){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditPassword=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
				
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

			validEditPassword=true;
		}
	});
	
	
	
	
});