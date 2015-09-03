/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".roleCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".roleCheckbox").prop("checked", false);
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
	 * 增加权限
	 */
	$('#addQxglLink').on('click', function(e){
		/*角色*/
		$("#addRolenameLabel").css({"color":"#333"});
		$("#addRolename").removeClass("borderFail validateFail borderSuc validateSuc");
		$("#addRolename").val("");
		$("#validateAddRolename").empty();		
		
		/*操作————checkbox*/
		$("#addOperationLabel").css({"color":"#333"});
		$("#addOperation").removeClass("borderFail validateFail borderSuc validateSuc");
		//$("#addAuthority").val("");
		$("#validateAddOperation").empty();
		
		
		$("#addRoleModal").modal('show');
	});
	
	/**
	 * 编辑权限
	 */
	 $('#editQxglLink').on('click', function(e){
		e.preventDefault();
		var $checked = $('#qxglTable tbody :checked');
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
			var bh = tr.children("td:eq(0)").find("#rolebh").val();
			var rolename = tr.children("td:eq(1)").html();
			var operation = tr.children("td:eq(2)").html();
			var resource = tr.children("td:eq(3)").find("#detailReses").val();
			
			$("#editBh").val(bh);
			$("#editRolename").val(rolename);
			
			/*选择现有权限*/
			var operationArray = operation.split(" ");
			$("input[name=editOperation]:checkbox").each(function(index){
				//首先清空之前的勾选状态
				this.checked = false;
				
				//这里需要区分操作和范围
				for(var i=0,length=operationArray.length;i<length;i++){
					var operation_range = operationArray[i].split(":");
					var op = operation_range[0];
					var range = operation_range[1];
					if($(this).attr('data-operation') == op){
						this.checked = true;
						$(this).next().val(range);
					}
				}
			});
			
			/*选择现有资源*/
			var resArray = resource.split(" ");
			for(var i=0;i<resArray.length;i++){
				var oneTwoRes = resArray[i].split(":");
				var parent = oneTwoRes[0];
				var children = oneTwoRes[1].split(",");
				
				var childChecked = false;
				$("input[data-parent=" + parent + "]:checkbox").each(function(index){
					this.checked = false;
					
					for(var j=0;j<children.length;j++){
						var child = children[j];
						if($(this).val() == child){
							this.checked = true;
							childChecked = true;
						}
					}
				});
				
				if(childChecked == true){
					$("input[name='editRes'][data-res=" + parent + "]:checkbox").attr("checked", "true");
				}
			}
			
			$("#editRoleModal").modal('show');
		}
	});
	
	/**
	 * 删除角色
	 */
	$('#deleteQxglLink').click(function(){
		var $checked = $('#qxglTable tbody :checked');
		
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
	                var action = basePath + "/role/deleteRole";
	                form.attr('action', action).attr('method', 'post').append($checked);
	                form.appendTo(document.body).submit();
	            }
	        });
	    }
	});
	
	/**
	 * 打印角色
	 */
	$("#printQxglLink").on('click', function(){
		var wind=window.open('about:blank', '', ''),
	      cont=$('#qxglTable').html();
	      //打印样式
	      wind.document.write('<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} @media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	      wind.print();
	});
	
	/**
	 * 确认增加角色权限
	 */
	confirmAdd = function(){
		$("#addRolename").blur();
		//$("#addAuthority").blur();
		
		if(validAddRolename && validateAddOperation){
			$("#addRoleForm").submit();
		}
	};
	
	/**
	 * 确认编辑角色权限
	 */
	confirmEdit = function(){
		//验证输入
		$("#editRolename").blur();
		//$("#editAuthority").blur();
		
		if(validEditRolename && validEditOperation){
			$("#editRoleForm").submit();
		}
		
	};
	
	var qxglTable = $("#qxglTable").DataTable({
		"order": [[ 1, "asc" ]],
		"dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	
	$(document).on("click",".t_parent",function(){
		var $this=$(this);
		if($this.prop("checked")){
			$this.closest("li").find(".t_child").prop("checked",true);
		}else{
			$this.closest("li").find(".t_child").prop("checked",false);
		}
	});
	
	$(document).on("click",".t_child",function(){
		var $t_p_li=$(this).closest("li").parent().parent();
		var p_check=false;
		$t_p_li.find(".t_child").each(function(){
			if($(this).prop("checked")){
				p_check=true;
			}
		});
		$t_p_li.find(".t_parent").prop("checked",p_check);		
	});
	
	
	/* 表单验证 */
	var validAddRolename = true;
	var validAddOperation = true;
	
	var validEditRolename = true;
	var validEditOperation = true;
	
	
	
	
});