/**
 * 
 */
var basePath = getRootPath();

var regex = /^(100|[1-9]?\d(\.\d\d?)?)%$/;

$(function(){
	
	var validGzsz = false;
	var validGzzl = false;
	var validZfsj = false;
	var validJfqx = false;
	
	$("#gzsz").blur(function(){
		var gzszVal = $(this).val();
		if(!gzszVal){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateGzsz").empty();
			$("#validateGzsz").append('<label class="control-label labelFail">不能为空</label>');
			validGzsz=false;
		}
		else if(!regex.test(gzszVal))
		{
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateGzsz").empty();
			$("#validateGzsz").append('<label class="control-label labelFail">您输入的不是百分数</label>');
			validGzsz=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("inputTextFail").addClass("inputTextSuc");
			
			$("#validateGzsz").empty();
		    $("#validateGzsz").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		    validGzsz=true;
		}
	});
	
	$("#gzzl").blur(function(){
		var gzzlVal = $(this).val();
		if(!gzzlVal){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateGzzl").empty();
			$("#validateGzzl").append('<label class="control-label labelFail">不能为空</label>');
			validGzzl=false;
		}
		else if(!regex.test(gzzlVal))
		{
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateGzzl").empty();
			$("#validateGzzl").append('<label class="control-label labelFail">您输入的不是百分数</label>');
			validGzzl=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("inputTextFail").addClass("inputTextSuc");
			
			$("#validateGzzl").empty();
		    $("#validateGzzl").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		    validGzzl=true;
		}
	});
	
	$("#zfsj").blur(function(){
		var zfsjVal = $(this).val();
		if(!zfsjVal){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateZfsj").empty();
			$("#validateZfsj").append('<label class="control-label labelFail">不能为空</label>');
			validZfsj=false;
		}
		else if(!regex.test(zfsjVal))
		{
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateZfsj").empty();
			$("#validateZfsj").append('<label class="control-label labelFail">您输入的不是百分数</label>');
			validZfsj=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("inputTextFail").addClass("inputTextSuc");
			
			$("#validateZfsj").empty();
		    $("#validateZfsj").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		    validZfsj=true;
		}
	});
	
	$("#jfqx").blur(function(){
		var jfqxVal = $(this).val();
		if(!jfqxVal){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateJfqx").empty();
			$("#validateJfqx").append('<label class="control-label labelFail">不能为空</label>');
			validJfqx=false;
		}
		else if(!regex.test(jfqxVal))
		{
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("inputTextSuc").addClass("inputTextFail");
			
			$("#validateJfqx").empty();
			$("#validateJfqx").append('<label class="control-label labelFail">您输入的不是百分数</label>');
			validJfqx=false;
		}
		else
		{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("inputTextFail").addClass("inputTextSuc");
			
			$("#validateJfqx").empty();
		    $("#validateJfqx").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		    validJfqx=true;
		}
	});
	
	initPfpzFormat = function(){
		$("#gzszLabel").css({"color":"#3c763d"});
		$("#gzsz").removeClass("inputTextFail").addClass("inputTextSuc");
		$("#validateGzsz").empty();
	    $("#validateGzsz").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		
	    $("#gzzlLabel").css({"color":"#3c763d"});
		$("#gzzl").removeClass("inputTextFail").addClass("inputTextSuc");
		$("#validateGzzl").empty();
	    $("#validateGzzl").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		
	    $("#zfsjLabel").css({"color":"#3c763d"});
		$("#zfsj").removeClass("inputTextFail").addClass("inputTextSuc");
		$("#validateZfsj").empty();
	    $("#validateZfsj").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
		
	    $("#jfqxLabel").css({"color":"#3c763d"});
		$("#jfqx").removeClass("inputTextFail").addClass("inputTextSuc");
		$("#validateJfqx").empty();
	    $("#validateJfqx").append('<label class="control-label"><span class="glyphicon glyphicon-ok spanSuc"></span></label>');
	};
	
	editPfpz = function(){
		initPfpzFormat();
		
		var gzsz = $("#pfpzTable tr").eq(1).children("td:eq(2)").html();
		var gzzl = $("#pfpzTable tr").eq(2).children("td:eq(2)").html();
		var zfsj = $("#pfpzTable tr").eq(3).children("td:eq(2)").html();
		var jfqx = $("#pfpzTable tr").eq(4).children("td:eq(2)").html();
		
		$("#gzsz").val(gzsz);
		$("#gzzl").val(gzzl);
		$("#zfsj").val(zfsj);
		$("#jfqx").val(jfqx);
		
		$("#editPfpz").modal('show');
	};

	confirmEdit = function(){
		
		$("#gzsz").blur();
		$("#gzzl").blur();
		$("#zfsj").blur();
		$("#jfqx").blur();
		
		if(validGzsz && validGzzl && validZfsj && validJfqx){
			var gzsz = $("#gzsz").val();
			var gzzl = $("#gzzl").val();
			var zfsj = $("#zfsj").val();
			var jfqx = $("#jfqx").val();
			
			$("#pfpzTable tr").eq(1).children("td:eq(2)").html(gzsz);
			$("#pfpzTable tr").eq(2).children("td:eq(2)").html(gzzl);
			$("#pfpzTable tr").eq(3).children("td:eq(2)").html(zfsj);
			$("#pfpzTable tr").eq(4).children("td:eq(2)").html(jfqx);
			
			$("#editPfpz").modal('hide');
			
			$("#alertInfo").show();
		}
		else{
			return false;
		}
		
	};

	savePfpz = function(){
		//$("#pfpzForm").submit();
		//使用ajax提交
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/pfpz/save",
			data:$('#pfpzForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				$("#saveSuc").modal('show');
				$("#alertInfo").hide();
			},
			error:function(request){
				$("#saveError").modal('show');
			}
		});
	};
	
	
});

$(document).ready(function(){
	
	
	
	
	
});