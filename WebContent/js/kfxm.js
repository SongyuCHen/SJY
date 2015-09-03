var basePath = getRootPath();

var inArray;

$(document).ready(function(){
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".kfxmCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".kfxmCheckbox").prop("checked", false);
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
	
	inArray = function (array, e) {
	  for(i=0;i<array.length;i++){
	    if(array[i] == e)
	      return true;
	  }
	  return false;
	};
	var kfxmTable = $("#kfxmTable").DataTable({
        "order": [[ 1, "asc" ]],
        "dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/*删除扣分类别*/
	$('#deleteKfxmLink').click(function() {
        var $checked = $('#kfxmTable tbody :checked');
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
                  form.attr('action', basePath + '/kfqx/deleteKfxm').attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
    });
	/*编辑扣分类别*/
	$('#editKfxmLink').on('click', function(e) { // 编辑仓房资料
        e.preventDefault();
        var $checked = $('#kfxmTable tbody :checked');
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
          $('#editKfxmModal #modalKfxmbh').val($checked.val());
          $('#editKfxmModal #modalLbbhInEdit').val($checked.data('lbbh'));
          $('#editKfxmModal #modalMcInEdit').val($checked.data('mc'));
          $('#editKfxmModal #modalFsInEdit').val($checked.data('fs'));
          $('#editKfxmModal').modal('show');
        }
      });
	
	/*获取所有扣分情形类别*/
	$.ajax({
		url: basePath +"/kfqx/getAllKflb.aj",
		type:'get',
		dataType:'json',
		cache: true
	}).done(function(data){
		var kflbList = data.kflbList;
		var optionContent;
		for(var i=0;i<kflbList.length;i++){
			var kflb = kflbList[i];
			optionContent="<option value="+kflb.bh+">"+kflb.mc+"</option>";
			$("#modalLbbh, #modalLbbhInEdit").append(optionContent);
		}
	});
	
	/**
	 * 确认编辑加分
	 */
	confirmEdit = function(){
		//验证输入
		$("#modalMcInEdit").blur();
		$("#modalFsInEdit").blur();
		
		var lb = $("#modalLbbhInEdit").find("option:selected").text();
		var lbbh = $("#modalLbbhInEdit").val();
		var mc = $("#modalMcInEdit").val();
		var fs = $("#modalFsInEdit").val();
		
		var $checked = $('#kfxmTable tbody :checked');
		var tr = $checked.closest("tr");
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/kfqx/editKfxm",
			data:$('#editKfxmForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var status = data.status;
				if(status=="success"){
					$("#editKfxmModal").modal('hide');
					
					var text = "<strong>编辑成功！</strong>";
					$("#yhglSucInfo").empty().append(text);
					$("#yhglSuc").modal('show');
					
					//显示编辑的数据
					tr.children("td:eq(1)").html(lb);
					tr.children("td:eq(2)").html(mc);
					tr.children("td:eq(3)").html(fs);
					tr.find("td:eq(0) input").data("lbbh",lbbh);
					tr.find("td:eq(0) input").data("mc",mc);
					tr.find("td:eq(0) input").data("fs",fs);
				}
				else{
					$("#editKfxmModal").modal('hide');
					var text = "<strong>编辑时发生了错误</strong>";
					$("#yhglErrorInfo").empty();
					$("#yhglErrorInfo").append(text);
					$("#yhglError").modal('show');
				}	
			},
			error:function(request){
				$("#editKfxmModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#yhglErrorInfo").empty();
				$("#yhglErrorInfo").append(text);
				$("#yhglError").modal('show');
			}
		});
	};
});