
$(document).ready(function(){
	var status = $("#resStatus").val();
	if(status=="error"){
		$("#resInfo").empty();
		var text = "<strong>用户名或密码错误</strong>";
		$("#resInfo").append(text);
		$("#resModal").modal('show');
	}
	
	if($.cookie("remember") == "true"){
		$("#remember").prop("checked", true);
       	$("#username").val($.cookie("username"));
       	$("#password").val($.cookie("password"));
   	}
	
	$("#btn_login").on("click",function(){
		remember();
		return true;
	});
	
	function remember(){
		var checked = $("#remember").prop("checked");
		if(checked == "checked" || checked) {
			var username = $("#username").val();
  	        var password = $("#password").val();
  	        $.cookie("remember", "true", {expires: 30});
	        $.cookie("username", username, {expires: 30});
	        $.cookie("password", password, {expires: 30});
		}else{
  	        $.cookie("remember", "false", {expires: -1});
  	        $.cookie("username", "", {expires: -1});
  	        $.cookie("password", "", {expires: -1});
  	    }
	}
	
	$("#remember").on("change", function(){
		var checked = $("#remember").prop("checked");
		if(checked == "checked" || checked) {
			
		}else{
  	        $.cookie("remember", "false", {expires: -1});
  	        $.cookie("username", "", {expires: -1});
  	        $.cookie("password", "", {expires: -1});
  	    }
	});
});