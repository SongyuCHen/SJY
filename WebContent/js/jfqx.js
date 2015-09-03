var basePath = getRootPath();

var inArray;

var kflbList, kfxmList, bmList, ryList;

var getKfxmByXmbh, getKfxmByLb, getRyByBmbh;

$(document).ready(function(){	
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".jfqxCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".jfqxCheckbox").prop("checked", false);
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
	
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		pickerPosition: "bottom-left"
	});
	
	/*默认选中当前年份和月份*/
	var curBmbh = $("#curBmbh").val();
	var curStartDate = $("#curStartDate").val();
	var curEndDate = $("#curEndDate").val();
	$("#bm").val(curBmbh);
	$("#startDate").val(curStartDate);
	$("#endDate").val(curEndDate);
	
	inArray = function (array, e) {
	  for(i=0;i<array.length;i++){
	    if(array[i] == e)
	      return true;
	  }
	  return false;
	};
	var kfqxTable = $("#kfqxTable").DataTable({
        "order": [[ 5, "asc" ]],
        "dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/**
	 * 对选中的记录进行审批
	 */
	$('#confirmJfqxLink').on('click', function(e){
		var $checked = $('#kfqxTable tbody :checked');
		if($checked.size() < 1) {
	        $.jGrowl('请选择要进行审批的项', {
	            life: 5000
	        });
	    }
	    else {
	        $.alerts.dialogClass = 'alert-info';
	        jConfirm('是否审批这些记录？', '确认对话框', function(r) {
	        	var bhArr = "";
	        	$checked.each(function(){
	        		bhArr += bhArr==""?$(this).val():"-"+$(this).val();
	        	});
	        	
	        	// ajax submit
	    		$.ajax({
	    			cache:false,
	    			type:"POST",
	    			url:basePath + "/jfqx/approval",
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
		    					var index = "td:eq(9)";
		    					tr.children(index).html(zt);
		    				});
	    				}
	    				else{
	    					var text = "<strong>" + msg + "</strong>";
	    					$("#jfqxErrorInfo").empty();
	    					$("#jfqxErrorInfo").append(text);
	    					$("#jfqxError").modal('show');
	    				}
	    			},
	    			error:function(request){
	    				
	    			}
	    		});
	        });
	    }
	});
	
	
	/*删除扣分类别*/
	$('#deleteKfqxLink').click(function() {
        var $checked = $('#kfqxTable tbody :checked');
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
                  form.attr('action', basePath + '/jfqx/deleteJfqx').attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
    });
	
	/*编辑扣分类别*/
	$('#editKfqxLink').on('click', function(e) { // 编辑仓房资料
        e.preventDefault();
        var $checked = $('#kfqxTable tbody :checked');
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
          $('#editKfqxModal #modalKfqxbh').val($checked.val());
          $('#editKfqxModal #modalBmmcInEdit').val($checked.data('bmmc'));
          $('#editKfqxModal #modalRyxmInEdit').val($checked.data('ryxm'));
          $('#editKfqxModal #modalLbbhInEdit').val($checked.data('lbbh'));
          $("#modalLbbhInEdit").change();
          $('#editKfqxModal #modalXmbhInEdit').val($checked.data('xmbh'));
          $("#modalXmbhInEdit").change();
          $('#editKfqxModal #modalHdsjInEdit').val($checked.data('hdsj'));
          $('#editKfqxModal').modal('show');
        }
    });
	
	/**
	 * 根据项目编号查询
	 * @param {int} xmbh 项目编号
	 * @return 某个项目
	 */
	getKfxmByXmbh = function(xmbh){
		for(var i=0;i<kfxmList.length;i++){
			if(kfxmList[i].bh == xmbh)
				return kfxmList[i];
		}
	}
	/**
	 * @param {int} lbbh 类别编号
	 * @return 项目列表
	 */
	getKfxmByLb = function(lbbh){
		var retArray = [];
			for(var i=0;i<kfxmList.length;i++){
				if(kfxmList[i].kflb.bh == lbbh)
					retArray.push(kfxmList[i]);
			}
		return retArray;
	}
	/**
	 * @param {int} bmbh 部门编号
	 * @return 人员列表
	 */
	getRyByBmbh = function(bmbh){
		var retArray = [];
		for(var i=0;i<ryList.length;i++){
			if(ryList[i].bm.bmbh == bmbh)
				retArray.push(ryList[i]);
		}
		return retArray;
	};
//==================新建====================//	
	/*部门下拉菜单*/
	$("#modalBmbh").on("change", function(){
		var curBmbh = $(this).val();
		var ret = getRyByBmbh(curBmbh);
		var optionContent = "";
		for(var i=0;i<ret.length;i++){
			var ry = ret[i];
			optionContent+="<option value="+ry.rybh+">"+ry.xm+"</option>";
		}
		$("#modalRybh").empty();
		$("#modalRybh").html(optionContent).change();
	});
	
	/*具体扣分项目下拉菜单*/
	$("#modalXmbh").on("change", function(){
		var curXmbh = $(this).val();
		var curXm = getKfxmByXmbh(curXmbh);
		$("#modalFs").val(curXm.fs);
	});
	
	/*扣分类别下拉菜单*/
	$("#modalLbbh").on("change", function(){
		var curLbbh = $(this).val();
		var ret = getKfxmByLb(curLbbh);
		var optionContent = "";
		for(var i=0;i<ret.length;i++){
			var kflb = ret[i];
			optionContent+="<option value="+kflb.bh+">"+kflb.mc+"</option>";
		}
		$("#modalXmbh").empty();
		$("#modalXmbh").html(optionContent).change();
	});
//==================编辑====================//	
	/*具体扣分项目下拉菜单*/
	$("#modalXmbhInEdit").on("change", function(){
		var curXmbh = $(this).val();
		var curXm = getKfxmByXmbh(curXmbh);
		$("#modalFsInEdit").val(curXm.fs);
	});
	
	/*扣分类别下拉菜单*/
	$("#modalLbbhInEdit").on("change", function(){
		var curLbbh = $(this).val();
		var ret = getKfxmByLb(curLbbh);
		var optionContent = "";
		for(var i=0;i<ret.length;i++){
			var kflb = ret[i];
			optionContent+="<option value="+kflb.bh+">"+kflb.mc+"</option>";
		}
		$("#modalXmbhInEdit").empty();
		$("#modalXmbhInEdit").html(optionContent).change();
	});
	
	/*获取所有扣分项目*/
	var getAllKfxmReq = $.ajax({
		url: basePath +"/jfqx/getAllJfxm.aj",
		type:'get',
		dataType:'json',
		cache: true
	}).done(function(data){
		//console.log("获取所有扣分项目");
		kfxmList = data.jfxmList;
	});
	
	/*获取所有扣分情形类别*/
	getAllKfxmReq.done(function(){
		$.ajax({
			url: basePath +"/jfqx/getAllJflb.aj",
			type:'get',
			dataType:'json',
			cache: true
		}).done(function(data){
			//console.log("获取所有扣分情形类别");
			kflbList = data.jflbList;
			var optionContent="";
			if(kflbList!=null){
				for(var i=0;i<kflbList.length;i++){
					var kflb = kflbList[i];
					optionContent+="<option value="+kflb.bh+">"+kflb.mc+"</option>";
				}
			}
			
			$("#modalLbbh, #modalLbbhInEdit").empty();
			$("#modalLbbh, #modalLbbhInEdit").html(optionContent);
			$("#modalLbbh, #modalLbbhInEdit").get(0).selectedIndex = 0;//默认选中第一个
			$("#modalLbbh, #modalLbbhInEdit").change();
		});
	});
	
	/*获取所有人员*/
	var getAllRyReq = $.ajax({
		url: basePath +"/user/getAccessNeedEvaluationUser.aj",
		type:'get',
		dataType:'json',
		cache: true
	}).done(function(data){
		//console.log("获取所有人员");
		ryList = data.ryList;
	});
	
	/*获取所有部门*/
	getAllRyReq.done(function(){
		$.ajax({
			url: basePath +"/bm/getAccessBmExcludeQtbm.aj",
			type:'get',
			dataType:'json',
			cache: true
		}).done(function(data){
			//console.log("获取所有部门");
			bmList = data.bmList;
			var optionContent="";
			if(bmList!=null){
				for(var i=0;i<bmList.length;i++){
					var bm = bmList[i];
					optionContent+="<option value="+bm.bmbh+">"+bm.bmmc+"</option>";
				}
			}
			
			$("#modalBmbh, #modalBmbhInEdit").empty();
			$("#modalBmbh, #modalBmbhInEdit").html(optionContent);
			$("#modalBmbh, #modalBmbhInEdit").get(0).selectedIndex = 0;//默认选中第一个
			$("#modalBmbh, #modalBmbhInEdit").change();
		});
	});
	
	$('#printJfqxLink').on('click',function(){
		var wind=window.open('about:blank', '', ''),
		cont=$('#kfqxTable').html();
		//打印样式
		wind.document.write(
				'<style>@media print{.no-print{display:none;}.' + 
				'no-wrap{word-break: keep-all;white-space:nowrap;}} ' + 
				'@media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
		wind.print();   
    });
	
	var regex = /^[1-9]*[1-9][0-9]*$/;
	$("#addJfqxForm").on('submit', function(){
		if($("#modalHdsj").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var addJfcs = $("#addJfcs").val();
		if(!addJfcs || addJfcs==""){
			$.jGrowl('您必须填写加分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(addJfcs)){
			$.jGrowl('加分次数必须为正整数', {
				life: 5000
        	});
			return false;
		}
		
		
	});
	
	$("#editJfqxForm").on('submit', function(){
		if($("#modalHdsjInEdit").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var editJfcs = $("#editJfcs").val();
		if(!editJfcs || editJfcs==""){
			$.jGrowl('您必须填写加分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(editJfcs)){
			$.jGrowl('加分次数必须为正整数', {
				life: 5000
        	});
			return false;
		}
	});
	
	/**
	 * 确认编辑加分
	 */
	confirmEdit = function(){
		//验证输入
		if($("#modalHdsjInEdit").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var editJfcs = $("#editJfcs").val();
		if(!editJfcs || editJfcs==""){
			$.jGrowl('您必须填写加分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(editJfcs)){
			$.jGrowl('加分次数必须为正整数', {
				life: 5000
        	});
			return false;
		}
		
		var lb = $("#modalLbbhInEdit").find("option:selected").text();
		var xm = $("#modalXmbhInEdit").find("option:selected").text();
		var lbbh = $("#modalLbbhInEdit").val();
		var xmbh = $("#modalXmbhInEdit").val();
		var fs = $("#modalFsInEdit").val();
		var sj = $("#modalHdsjInEdit").val();
		var jfcs = $("#editJfcs").val();
		var comment = $("#editComment").val();
		
		var $checked = $('#kfqxTable tbody :checked');
		var tr = $checked.closest("tr");
		
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/jfqx/editJfqx",
			data:$('#editJfqxForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var statusMsg = data.StatusMsg;
				var status = statusMsg.status;
				
				if(status==1){
					$("#editKfqxModal").modal('hide');
					
					//显示编辑的数据
					tr.children("td:eq(3)").html(lb);
					tr.children("td:eq(4)").html(xm);
					tr.children("td:eq(5)").html(sj);
					tr.children("td:eq(6)").html(fs);
					tr.children("td:eq(7)").html(jfcs);
					tr.children("td:eq(8)").html(comment);
					tr.find("td:eq(0) input").data("lbbh",lbbh);
					tr.find("td:eq(0) input").data("xmbh",xmbh);
					tr.find("td:eq(0) input").data("jfcs", jfcs);
					tr.find("td:eq(0) input").data("comment", comment);
					
					$("#jfqxSuc").modal('show');
				}
				else if(status==0){
					var msg = statusMsg.msg;
					var text = "<strong>" + msg + "</strong>";
					$("#jfqxErrorInfo").empty();
					$("#jfqxErrorInfo").append(text);
					$("#jfqxError").modal('show');
				}
			},
			error:function(request){
				$("#editKfqxModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#jfqxErrorInfo").empty();
				$("#jfqxErrorInfo").append(text);
				$("#jfqxError").modal('show');
			}
		});
	};
	
	
	/* 操作的点击事件 */
	$(".operationLi").on('click', function(e){
		var operate = $(this).attr("id");
		if(operate=="add"){
			addJfqx();
		}
		else if(operate=="edit"){
			editJfqx();
		}
		else if(operate=="delete"){
			deleteJfqx();
		}
		else if(operate=="approval"){
			approvalJfqx();
		}
		else if(operate=="print"){
			printJfqx();
		}
		else if(operate=="export"){
			exportJfqx();
		}
	});
	
	/* 增加加分情形 */
	var addJfqx = function(){
		$("#addKfqxModal").modal('show');
	};
	
	/* 编辑加分情形 */
	var editJfqx = function(){
		var $checked = $('#kfqxTable tbody :checked');
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
          $('#editKfqxModal #modalKfqxbh').val($checked.val());
          $('#editKfqxModal #modalBmmcInEdit').val($checked.data('bmmc'));
          $('#editKfqxModal #modalRyxmInEdit').val($checked.data('ryxm'));
          $('#editKfqxModal #modalLbbhInEdit').val($checked.data('lbbh'));
          $("#modalLbbhInEdit").change();
          $('#editKfqxModal #modalXmbhInEdit').val($checked.data('xmbh'));
          $("#modalXmbhInEdit").change();
          $('#editKfqxModal #modalHdsjInEdit').val($checked.data('hdsj'));
          $('#editKfqxModal #editJfcs').val($checked.data('jfcs'));
          $('#editKfqxModal #editComment').val($checked.data('comment'));
          $('#editKfqxModal').modal('show');
        }
	}
	
	/* 删除加分情形 */
	var deleteJfqx = function(){
		var $checked = $('#kfqxTable tbody :checked');
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
                  var url = basePath + "/jfqx/deleteJfqx?bmbh=" + curBmbh + "&startDate=" + curStartDate + "&endDate=" + curEndDate;
                  form.attr('action', url).attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
	}
	
	/* 审批加分情形 */
	var approvalJfqx = function(){
		var $checked = $('#kfqxTable tbody :checked');
		if($checked.size() < 1) {
	        $.jGrowl('请选择要进行审批的项', {
	            life: 5000
	        });
	    }
	    else {
	        $.alerts.dialogClass = 'alert-info';
	        jConfirm('是否审批这些记录？', '确认对话框', function(r) {
	        	var bhArr = "";
	        	$checked.each(function(){
	        		bhArr += bhArr==""?$(this).val():"-"+$(this).val();
	        	});
	        	
	        	// ajax submit
	    		$.ajax({
	    			cache:false,
	    			type:"POST",
	    			url:basePath + "/jfqx/approval",
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
		    					var index = "td:eq(7)";
		    					tr.children(index).html(zt);
		    				});
	    				}
	    				else{
	    					var text = "<strong>" + msg + "</strong>";
	    					$("#jfqxErrorInfo").empty();
	    					$("#jfqxErrorInfo").append(text);
	    					$("#jfqxError").modal('show');
	    				}
	    			},
	    			error:function(request){
	    				
	    			}
	    		});
	        });
	    }
	}
	
	/* 打印加分情形 */
	var printJfqx = function(){
		var wind=window.open('about:blank', '', ''),
	    cont=$('#kfqxTable').html();
	    //打印样式
	    wind.document.write(
	    		'<style>@media print{.no-print{display:none;}.' + 
	    		'no-wrap{word-break: keep-all;white-space:nowrap;}} ' + 
	    		'@media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	    wind.print();
	}
	
	/* 导出加分情形 */
	var exportJfqx = function(){
		var bmbh = curBmbh;
		var startDate = curStartDate;
		var endDate = curEndDate;
		var url = basePath + "/jfqx/downloadExcel?bmbh=" + bmbh + "&startDate=" + startDate + "&endDate=" + endDate;
		window.location = url;
	}
});