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
				$('input').attr("readonly","readonly");
			},
			error:function(request){
				
			}
		});
	});
});