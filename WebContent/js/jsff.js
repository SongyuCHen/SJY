/**
 * 
 */

var basePath = getRootPath();

$(document).ready(function(){
	
	$("#editJsffBtn").click(function(){
		$('input').removeAttr("readonly");
	});
	
	$("#saveJsffBtn").click(function(){
		//var maxScore = $("#maxScore").val();
		
		$.ajax({
			cache:false,
			type:"POST",
			url:basePath + "/pfpz/editJsff",
			data:$('#editJsffForm').serialize(),
			dataType:'json',
			async:false,
			success:function(data){
				var maxScore = $("#maxScore").val();
				var minScore = $("#minScore").val();
				$('input').attr("readonly","readonly");
				$('.jumbotron').html("<h1>方法说明</h1><p>工作量按照每个分项值计算</p><p>分项值最高为"
						+maxScore+"分，最低为"+minScore+"分</p><p>假定分项高值为X，低值为Y，某人分项值为M</p><p>则得分为：(M-Y)/(X-Y)*("+
						maxScore+"-"+minScore+")+"+minScore+"</p>");
			},
			error:function(request){
				
			}
		});
	});
});