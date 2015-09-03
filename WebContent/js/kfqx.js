var basePath = getRootPath();

var inArray;

var kflbList, kfxmList, bmList, ryList;

var getKfxmByXmbh, getKfxmByLb, getRyByBmbh;

$(document).ready(function(){
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".kfqxCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".kfqxCheckbox").prop("checked", false);
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
	$("#startDate").val(curStartDate);
	$("#endDate").val(curEndDate);
	$("#bm").val(curBmbh);
	
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
                  form.attr('action', basePath + '/kfqx/deleteKfqx').attr('method', 'post').append($checked);
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
          $('#editKfqxModal #editKfcs').val($checked.data('kfcs'));
          $('#editKfqxModal #editComment').val($checked.data('comment'));
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
		url: basePath +"/kfqx/getAllKfxm.aj",
		type:'get',
		dataType:'json',
		cache: true
	}).done(function(data){
		//console.log("获取所有扣分项目");
		kfxmList = data.kfxmList;
	});
	
	/*获取所有扣分情形类别*/
	getAllKfxmReq.done(function(){
		$.ajax({
			url: basePath +"/kfqx/getAllKflb.aj",
			type:'get',
			dataType:'json',
			cache: true
		}).done(function(data){
			//console.log("获取所有扣分情形类别");
			kflbList = data.kflbList;
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
	
	$('#printKfqxLink').on('click',function(){
		var wind=window.open('about:blank', '', ''),
		cont=$('#kfqxTable').html();
		//打印样式
		wind.document.write(
    		  '<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} ' + 
    		  '@media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
		wind.print();   
    });
	
	var regex = /^[1-9]*[1-9][0-9]*$/;
	$("#addKfqxForm").on('submit', function(){
		if($("#modalHdsj").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var addKfcs = $("#addKfcs").val();
		if(!addKfcs || addKfcs==""){
			$.jGrowl('您必须填写扣分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(addKfcs)){
			$.jGrowl('扣分次数必须为正整数', {
				life: 5000
        	});
			return false;
		}
		
	});
	
	$("#editKfqxForm").on('submit', function(){
		if($("#modalHdsjInEdit").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var editKfcs = $("#editKfcs").val();
		if(!editKfcs || editKfcs==""){
			$.jGrowl('您必须填写扣分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(editKfcs)){
			$.jGrowl('扣分次数必须为正整数', {
				life: 5000
        	});
			return false;
		}
	});
	
	confirmEdit = function(){
		//验证输入
		if($("#modalHdsjInEdit").val() == ""){
			$.jGrowl('您必须选择获得时间', {
				life: 5000
        	});
			return false;
		}
		
		var editKfcs = $("#editKfcs").val();
		if(!editKfcs || editKfcs==""){
			$.jGrowl('您必须填写扣分次数', {
				life: 5000
        	});
			return false;
		}
		else if(!regex.test(editKfcs)){
			$.jGrowl('扣分次数必须为正整数', {
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
		var kfcs = $("#editKfcs").val();
		var comment = $("#editComment").val();
		
		var $checked = $('#kfqxTable tbody :checked');
		var tr = $checked.closest("tr");
		
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/kfqx/editKfqx",
			data:$('#editKfqxForm').serialize(),
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
					tr.children("td:eq(7)").html(kfcs);
					tr.children("td:eq(8)").html(comment);
					tr.find("td:eq(0) input").data("lbbh",lbbh);
					tr.find("td:eq(0) input").data("xmbh",xmbh);
					tr.find("td:eq(0) input").data("comment", comment);
				}
				else if(status==0){
					var msg = statusMsg.msg;
					alert(msg);
				}	
			},
			error:function(request){
				$("#editKfqxModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#yhglErrorInfo").empty();
				$("#yhglErrorInfo").append(text);
				$("#yhglError").modal('show');
			}
		});
	};
	
	
	
	/* 操作的点击事件 */
	$(".operationLi").on('click', function(e){
		var operate = $(this).attr("id");
		if(operate=="add"){
			addKfqx();
		}
		else if(operate=="edit"){
			editKfqx();
		}
		else if(operate=="delete"){
			deleteKfqx();
		}
		else if(operate=="approval"){
			approvalKfqx();
		}
		else if(operate=="print"){
			printKfqx();
		}
		else if(operate=="export"){
			exportKfqx();
		}
	});
	
	var addKfqx = function(){
		$("#addKfqxModal").modal('show');
	}
	
	var editKfqx = function(){
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
          $('#editKfqxModal #editKfcs').val($checked.data('kfcs'));
          $('#editKfqxModal #editComment').val($checked.data('comment'));
          $('#editKfqxModal').modal('show');
        }
	}
	
	var deleteKfqx = function(){
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
                  var url = basePath + "/kfqx/deleteKfqx?bmbh=" + curBmbh + "&startDate=" + curStartDate + "&endDate=" + curEndDate;
                  form.attr('action', url).attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
	}
	
	var approvalKfqx = function(){
		alert("扣分情形没有审批");
	}
	
	var printKfqx = function(){
		var wind=window.open('about:blank', '', ''),
	    cont=$('#kfqxTable').html();
	    //打印样式
	    wind.document.write(
	    		'<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} ' + 
	    		'@media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	    wind.print();
	}
	
	var exportKfqx = function(){
		var bmbh = curBmbh;
		var startDate = curStartDate;
		var endDate = curEndDate;
		var url = basePath + "/kfqx/downloadExcel?bmbh=" + bmbh + "&startDate=" + startDate + "&endDate=" + endDate;
		window.location = url;
	}
	
	
	
	
});