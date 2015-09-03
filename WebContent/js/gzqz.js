/**
 * 
 */

var basePath = getRootPath();

$(document).ready(function(){
	
	var gzxxLen = $("#gzxxLen").val();
	
	$("#selectAllBtn").on('click', function(){
		var text = $(this).text();
		if(text == "全  选"){
			$(".pfCheckbox").prop("checked", true);
			$(this).text("反  选");
		}
		else{
			$(".pfCheckbox").prop("checked", false);
			$(this).text("全  选");
		}	
	});
	
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
	
	var gzqzTable = $("#gzqzTable").DataTable({
        "order": [[ 1, "asc" ]],
        "dom": '<pfltpi>',
		"pagingType": "full_numbers"
	});
	
	/*增加规则类别*/
	$("#addGzqzLink").click(function(){
		$("#addGzqzModal").modal('show');
	});
	
	
	/*编辑规则类别*/
	$('#editGzqzLink').on('click', function(e) {
        e.preventDefault();
        var $checked = $('#gzqzTable tbody :checked');
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
        	var tr = $(":checkbox:checked").closest("tr");
        	var bh = tr.children("td:eq(0)").find("#pfbh").val();
			var mc = tr.children("td:eq(1)").html();
			var lx = tr.children("td:eq(2)").html();
			var fs = tr.children("td:eq(3)").html();
        	$('#editGzqzModal #editPfbh').val(bh);
        	$('#editGzqzModal #editMc').val(mc);
        	$('#editGzqzModal #editLx').val(lx);
        	$('#editGzqzModal #editFs').val(fs);
        	
        	$('#editGzqzModal').modal('show');
        }
    });
	
	
	/*删除规则类别*/
	$('#deleteGzqzLink').click(function() {
        var $checked = $('#gzqzTable tbody :checked');
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
                  form.attr('action', basePath + '/pfpz/deleteGzqz').attr('method', 'post').append($checked);
                  form.appendTo(document.body).submit();
                }
          });
        }
    });
	
	/**
	 * 确认增加一个规则
	 */
	confirmAdd = function(){
		$("#addMc").blur();
		$("#addLx").blur();
		$("#addFs").blur();
		
		if(validAddMc && validAddFs){
			$("#addGzqzForm").submit();
		}
	};
	
	/**
	 * 确认编辑规则
	 */
	confirmEdit = function(){
		//验证输入
		$("#editMc").blur();
		$("#editLx").blur();
		$("#editFs").blur();
		
		if(!validEditMc || !validEditLx || !validEditFs){
			return false;
		}
		
		var mc = $("#editMc").val();
		var lx = $("#editLx").val();
		var fs = $("#editFs").val();
		
		var $checked = $('#gzqzTable tbody :checked');
		var tr = $checked.closest("tr");
		var bh = tr.children("td:eq(0)").find("#pfbh").val();
		$("#editPfbh").val(bh);
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/pfpz/editGzqz",
			data:$('#editGzqzForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				
				$("#editGzqzModal").modal('hide');
					
				var text = "<strong>编辑成功！</strong>";
				$("#gzqzSucInfo").empty().append(text);
				$("#gzqzSuc").modal('show');
					
				tr.children("td:eq(1)").html(mc);
				tr.children("td:eq(2)").html(lx);
				tr.children("td:eq(3)").html(fs);
			},
			error:function(request){
				$("#editGzqzModal").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#gzqzErrorInfo").empty();
				$("#gzqzErrorInfo").append(text);
				$("#gzqzError").modal('show');
			}
		});
	}
	
	/**
	 * 编辑工作实绩规则权重
	 */
	editGzsjGzqz = function(){
		var tr = $("#gzsjConfigBtn").closest("tr");
		var gzsjVal = tr.children("td:eq(3)").html();
		console.log(gzsjVal);
		$("#gzsjTotal").html(gzsjVal);
		$("#editGzsjGzqz").modal('show');
		console.log(gzxxLen);
	}
	
	/**
	 * 确认提交工作实绩规则权重
	 */
	configGzsjGzqz = function(){
		var tr = $("#gzsjConfigBtn").closest("tr");
		var gzsjVal = tr.children("td:eq(3)").html();
		var totalVal = 0;
		for(var i=0;i<gzxxLen;i++){
			var gzxxVal = $("#gzxx" + i).val();
			console.log(gzxxVal);
			totalVal += parseInt(gzxxVal, 10);
		}
		
		gzsjVal = parseInt(gzsjVal, 10);
		$("#editGzsjGzqz").modal('show');
		console.log(gzxxLen);
	}
	
	/**
	 * 确认提交工作实绩规则权重
	 */
	configGzsjGzqz = function(){
		var tr = $("#gzsjConfigBtn").closest("tr");
		var gzsjVal = tr.children("td:eq(3)").html();
		var totalVal = 0;
		for(var i=0;i<gzxxLen;i++){
			var gzxxVal = $("#gzxx" + i).val();
			console.log(gzxxVal);
			totalVal += parseInt(gzxxVal, 10);
		}
		
		if(totalVal != gzsjVal){
			var text = "<label class='control-label validateFail'>总分必须等于" + gzsjVal + "分</label>";
			$("#gzsjValValidate").empty().append(text);
			
			return;
		}
		
		// ajax submit
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/pfpz/editGzsjGzqz",
			data:$('#editGzsjGzqzForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				
				$("#editGzsjGzqz").modal('hide');
				$("#gzsjValValidate").empty();
					
				var text = "<strong>编辑成功！</strong>";
				$("#gzqzSucInfo").empty().append(text);
				$("#gzqzSuc").modal('show');
					
			},
			error:function(request){
				$("#editGzsjGzqz").modal('hide');
				var text = "<strong>编辑时发生了错误</strong>";
				$("#gzqzErrorInfo").empty();
				$("#gzqzErrorInfo").append(text);
				$("#gzqzError").modal('show');
			}
		});
	}
	
	/* 增加和编辑的表单验证 */
	//var regex = /^(100|[1-9]?\d(\.\d\d?)?)%$/;  //验证百分数的正则表达式
	var regex = /^[0-9]+$/;//非负整数
	
	var validAddMc = false;
	var validAddLx = false;
	var validAddFs = false;
	
	var validEditMc = false;
	var validEditLx = false;
	var validEditFs = false;
	
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
	
	$("#addLx").blur(function(){
		var lx = $(this).val();
		
		if(!lx){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddLx=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validAddLx=true;
		}
	});
	
	$("#addFs").blur(function(){
		var fs = $(this).val();
		
		if(!fs){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validAddFs=false;
		}
		else if(!regex.test(fs)){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">您输入的不是数字</label>');
			validAddFs=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validAddFs=true;
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
	
	$("#editLx").blur(function(){
		var lx = $(this).val();
		
		if(!lx){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditLx=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validEditLx=true;
		}
	});
	
	$("#editFs").blur(function(){
		var fs = $(this).val();
		
		if(!fs){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validEditFs=false;
		}
		else if(!regex.test(fs)){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">您输入的不是数字</label>');
			validEditFs=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append('<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validEditFs=true;
		}
	});
	
	
	
});