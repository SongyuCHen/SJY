/**
 * 
 */

var basePath = getRootPath();

$(document).ready(function(){
	
//	$(".glyphicon-check").click(function(){
//		$(this).removeClass("glyphicon-check").addClass("glyphicon-unchecked");
//	});
//	
//	$(".glyphicon-unchecked").click(function(){
//		$(this).removeClass("glyphicon-unchecked").addClass("glyphicon-check");
//	});
	
	$(".checkDiv").click(function(){
		var pre = $(this).prev().find("#splcbh");
		if(pre.attr('checked')){
			pre.removeAttr("checked");
			var icon = $(this).find("#checkIcon");
			icon.removeClass("glyphicon-check").addClass("glyphicon-unchecked");
		}
		else{
			pre.attr("checked",'true');
			var icon = $(this).find("#checkIcon");
			icon.removeClass("glyphicon-unchecked").addClass("glyphicon-check");
		}
	});
});