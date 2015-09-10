var basePath = getRootPath();

$(function() {
	
	$(".floatDiv").eq(1).css("margin-left","138px");
//	/*鼠标进入链接*/
//    $(document).on('mouseenter.collapse', '[data-toggle=collapse]', function(e) {
//        var $this = $(this);
//        //找到目标panel
//        var href, target = $this.attr('data-target') || e.preventDefault() || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, ''); //strip for ie7
//        if(!$(target).hasClass('in'))//如果是折叠状态就触发点击事件
//        	$this.trigger("click");
//    });
    /*从href中解析出图片路径*/
    function extractImagePathFromHref(href){
    	var array = href.split("/");
    	if(array.length > 1)
    		return array[array.length-2] + "_" + array[array.length-1];
    	else
    		return "blank";
    }
    $(".panelBody ul li").each(function(){
    	var $curLi = $(this);
    	var $img = $curLi.find("img").eq(0);
    	var href = $curLi.find("a").eq(0).attr("href");
    	var imagePath = extractImagePathFromHref(href);
    	$img.attr("src","../images/"+imagePath+".png");
    	$curLi.mouseover(function(){
    		$img.attr("src","../images/"+imagePath+"_hover.png");
    	}).mouseout(function(){
    		$img.attr("src","../images/"+imagePath+".png");
    	});
    });
    
    modifyPwd = function(){
    	$("#modifyPwdModal").modal('show');
    };
    
    confirmModify = function(){
    	$("#oldPwd").blur();
    	$("#newPwd").blur();
    	$("#confirmPwd").blur();
    	
    	if(validOldPwd && validNewPwd && validConfirmPwd){
    		$("#modifyPwdForm").submit();
    	}
    }
    
    /* 验证修改密码的字段 */
    var validOldPwd = false;
    var validNewPwd = false;
    var validConfirmPwd = false;
    
    var regex = /^[a-zA-Z0-9]{2,10}$/;
    
    $("#oldPwd").blur(function(){
    	var pwd = $(this).val();
		if(!pwd){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validOldPwd=false;
		}
		else
		{
			$.ajax({
				cache:false,
				type:"GET",
				url:basePath + "/user/checkPwd.aj",
				data:{
					pwd:pwd
				},
				dataType:'json',
				async:false,
				success:function(data){
					var status = data.status;
					if(status==1){
						$("#oldPwd").parent().prev().css({"color":"#3c763d"});
						$("#oldPwd").removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
						
						$("#oldPwd").parent().next().empty().append(
								'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

					    validOldPwd=true;
					}
					else{
						$("#oldPwd").parent().prev().css({"color":"#a94442"});
						$("#oldPwd").removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
						
						$("#oldPwd").parent().next().empty().append('<label class="control-label validateFail">旧密码错误</label>');
						validOldPwd=false;
					}
				},
				error:function(request){
				}
			});
		}
    });
    
    $("#newPwd").blur(function(){
    	var pwd = $(this).val();
		if(!pwd){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不能为空</label>');
			validNewPwd=false;
		}
		else if(!regex.test(pwd)){
			$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">不符合规则</label>');
			validNewPwd=false;
		}
		else{
			$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append(
					'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validNewPwd=true;
		    
		    var confirmPwd = $("#confirmPwd").val();
		    if(pwd != confirmPwd){
		    	$("#confirmPwd").parent().prev().css({"color":"#a94442"});
				$("#confirmPwd").removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
				
				$("#confirmPwd").parent().next().empty().append('<label class="control-label validateFail">两次密码不一致</label>');
				validConfirmPwd=false;
		    }
		}
    });
    
    $("#confirmPwd").blur(function(){
    	var pwd = $("#newPwd").val();
    	var confirmPwd = $(this).val();
    	if(pwd == confirmPwd){
    		$(this).parent().prev().css({"color":"#3c763d"});
			$(this).removeClass("borderFail validateFail").addClass("borderSuc validateSuc");
			
			$(this).parent().next().empty().append(
					'<label class="control-label"><span class="glyphicon glyphicon-ok validateSuc"></span></label>');

		    validConfirmPwd=true;
    	}
    	else{
    		$(this).parent().prev().css({"color":"#a94442"});
			$(this).removeClass("borderSuc validateSuc").addClass("borderFail validateFail");
			
			$(this).parent().next().empty().append('<label class="control-label validateFail">两次密码不一致</label>');
			validConfirmPwd=false;
    	}
    });
});