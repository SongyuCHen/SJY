/**
 * 
 */

var basePath = getRootPath();

$(document).ready(function(){	
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".gzCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".gzCheckbox").prop("checked", false);
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
	
	var gzlbTable = $("#gzlbTable").DataTable({
        "order": [[ 1, "asc" ]],
        "dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/*增加规则类别*/
	$("#addGzlbLink").click(function(){
		$("#addGzlbModal").modal('show');
	});
	
	/*删除规则类别*/
	$('#deleteGzlbLink').click(function() {
        var $checked = $('#gzlbTable tbody :checked');
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
                  form.attr('action', basePath + '/pfpz/deleteGzlb').attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
    });
	
	/*编辑规则类别*/
	$('#editGzlbLink').on('click', function(e) { // 编辑仓房资料
        e.preventDefault();
        var $checked = $('#gzlbTable tbody :checked');
        if($checked.size() < 1) {
          $.jGrowl('请选择要编辑的项', {
            life: 5000
          });
        }
        else if(($checked.size() > 1)) {
          $.jGrowl('只能同时编辑一项', {
            life: 5000
          });
        }
        else {
          $('#editGzlbModal #editGzbh').val($checked.val());
          $('#editGzlbModal #editMc').val($checked.data('mc'));
          $('#editGzlbModal').modal('show');
        }
    });
	
	/**
	 * 确认增加一个规则
	 */
	confirmAdd = function(){
		$("#addMc").blur();
		
		if(validAddMc){
			$("#addGzlbForm").submit();
		}
	};
	
	/**
	 * 确认编辑规则
	 */
	confirmEdit = function(){
		//验证输入
		$("#editMc").blur();
		
		if(!validEditMc){
			return false;
		}
		
		var gzmc = $("#editMc").val();
		
		var $checked = $('#gzlbTable tbody :checked');
		var tr = $checked.closest("tr");
		var bh = tr.children("td:eq(0)").find("#gzbh").val();
		var pzbh = tr.children("td:eq(1)").html();
		//var mc = tr.children("td:eq(2)").html();
		$("#editGzbh").val(bh);
		//$("#editGzpzbh").val(pzbh);
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/pfpz/editGzlb",
			data:$('#editGzlbForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				
				$("#editGzlbModal").modal('hide');
					
				var text = "<strong>编辑成功！</strong>";
				$("#gzlbSucInfo").empty().append(text);
				$("#gzlbSuc").modal('show');
					
				//$("#editMc").val(mc);
				tr.children("td:eq(2)").html(gzmc);
				
			},
			error:function(request){
				$("#editGzlbModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#gzlbErrorInfo").empty();
				$("#gzlbErrorInfo").append(text);
				$("#gzlbError").modal('show');
			}
		});
	}
	
	
	/* 表单验证 */
	var validAddMc = false;
	var validEditMc = false;
	
	$("#addMc").blur(function(){
		var mc = $(this).val();
		
		if(!mc){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddMc=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validAddMc=true;
		}
	});
	
	$("#editMc").blur(function(){
		var mc = $(this).val();
		
		if(!mc){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditMc=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validEditMc=true;
		}
	});
});