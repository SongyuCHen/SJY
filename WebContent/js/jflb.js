var basePath = getRootPath();

var inArray;

$(document).ready(function(){	
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".jflbCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".jflbCheckbox").prop("checked", false);
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
	var jflbTable = $("#jflbTable").DataTable({
        "order": [[ 1, "asc" ]],
        "dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/*删除扣分类别*/
	$('#deleteJflbLink').click(function() {
        var $checked = $('#jflbTable tbody :checked');
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
                  form.attr('action', basePath + '/jfqx/deleteJflb').attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
    });
	
	/*编辑扣分类别*/
	$('#editJflbLink').on('click', function(e) { // 编辑仓房资料
        e.preventDefault();
        var $checked = $('#jflbTable tbody :checked');
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
          $('#editJflbModal #modalJflbbh').val($checked.val());
          $('#editJflbModal #modalMc').val($checked.data('mc'));
          $('#editJflbModal').modal('show');
        }
    });
});