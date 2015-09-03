/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	$("#pfkh").addClass('active').siblings().removeClass('active');
	
	var lastBm = $("#lastBm").val();
	var lastXm = $("#lastXm").val();
	var lastNf = $("#lastNf").val();
	var lastJd = $("#lastJd").val();
	$("#bm").val(lastBm);
	$("#xm").val(lastXm);
	$("#nf").val(lastNf);
	$("#jd").val(lastJd);
	
	$("#bm").change(function(){
		var bm = $("#bm").val();
		$.ajax({
			url: basePath + '/bm/getXm',
			type:'post',
			dataType:'json',
			data:{bm:bm},
			success:function(data){
				$("#xm").empty();
				var text;
				for(var i=0;i<data.length;i++){
					text += "<option>" + data[i].xm + "</option>";
				}
				$("#xm").append(text);
			},
			error:function(){
				
			}
		});
	});
});