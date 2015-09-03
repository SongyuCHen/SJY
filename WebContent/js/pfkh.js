/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	
	var curBm = $("#curBm").val();
	var curXm = $("#curXm").val();
	var curNf = $("#curNf").val();
	var curJd = $("#curJd").val();
	$("#bm").val(curBm);
	$("#xm").val(curXm);
	$("#nf").val(curNf);
	$("#jd").val(curJd);
	
	$("#bm").change(function(){
		var bm = $("#bm").val();
		$.ajax({
			url: basePath + '/user/getAccessNeedEvaluationRyxm.aj',
			type:'post',
			dataType:'json',
			data:{bm:bm},
			success:function(data){
				var userList = data.userList;
				$("#xm").empty();
				var text;
				for(var i=0;i<userList.length;i++){
					text += "<option>" + userList[i].xm + "</option>";
				}
				$("#xm").append(text);
			},
			error:function(){
				
			}
		});
	});
	
	/**
	 * 开关添加hider类，对数值td，div和兄弟节点名称td，div
	 * @param $curSzTd 数值TDjquery对象
	 */
	function toggleHider($curSzTd){
		var $curSzDiv = $curSzTd.find("div").eq(0);
		var $curMcTd = $curSzTd.prev();
		var $curMcDiv = $curMcTd.find("div").eq(0);
		$curSzTd.toggleClass("hider");
		$curSzDiv.toggleClass("hider");
		$curMcTd.toggleClass("hider");
		$curMcDiv.toggleClass("hider");
	}
	/*需要完整显示的类别名称列表*/
	var showArray = ['工作实绩','加分情形'];
	/**
	 * 效果先隐藏，向下滑动，结束后回调，执行slideToggle折叠得分为0的项目
	 * 注意：由于带有rowspan的td不能被折叠，后台根据数值倒序排列，保证这类td不会被折叠，
	 * 如果数值都是0，需要对第一个td进行修改，文本置空
	 */
	$("#table_content").css("display","none").slideDown(2500, function(){
		//每个分类的计数器
		var lbCount = 1;
		//保存每个分类第一个数值TD
		var $lastSzTd;
		var lastLbMc;
		//总条目
		var totalCount = $('.gypz-sz').length;
		$(".gypz-sz").each(function(index){
			var $curSzTd = $(this);
			var $curSzDiv = $curSzTd.find("div").eq(0);
			var $curMcTd = $curSzTd.prev();
			var $curMcDiv = $curMcTd.find("div").eq(0);
			
			var curLbmc = $.trim($curSzTd.prev().prev().text());
			
			if(curLbmc != ""){//进入下一种类别
				if(lbCount == 0 && ($.inArray(lastLbMc, showArray) == -1)){//需要置空
					$lastSzTd.prev().find("div").eq(0).text("无");
					toggleHider($lastSzTd);
				}
				lbCount = 0;
				$lastSzTd = $curSzTd;
				lastLbMc = curLbmc;
			}
			
			if($.trim($curSzDiv.text()) == "0"){
				if($.inArray(lastLbMc, showArray) == -1)
					toggleHider($curSzTd);
			}else{
				lbCount++;
			}
		});
		
		if(lbCount == 0 && ($.inArray(lastLbMc, showArray) == -1)){//需要置空
			$lastSzTd.prev().find("div").eq(0).text("无");
			toggleHider($lastSzTd);
		}
		
		//折叠hider类节点
		$( ".hider" ).slideToggle("slow");
	});
	
});