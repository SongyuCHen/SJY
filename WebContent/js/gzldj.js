/**
 * 
 */
var basePath = getRootPath();

$(document).ready(function(){
	
	
	/**
	 * 保存上次查询条件
	 */
	var curYear = $("#curYear").val();
	var curMonth = $("#curMonth").val();
	$("#nf").val(curYear);
	$("#yf").val(curMonth);
	
	$("#operationBtn").attr("disabled","disabled");
	
	$("#searchForm").on("submit", function(e){
		e.preventDefault();
		nf = $("#nf").val();
		yf = $("#yf").val();
		
		$("#table_content").empty();
		
		$("#loading-indicator").show();
		//禁用查询和操作按钮
		$("#searchBtn").attr("disabled","disabled");
		$("#operationBtn").attr("disabled","disabled");
		
		var getRemoteGzsjReq = $.ajax({
			url: basePath +"/gzldj/fetch.aj",
			data: {
				nf: nf,
				yf: yf
			},
			type:'get',
			cache: false,
			dataType:'json'
		}).done(function(data){
			
			var cont = Mustache.to_html($('#gzldjTableTemplate').html(), data);
		    $('#table_content').html(cont);
		    
		    var gzldjTable = $("#gzldjTable").DataTable({
				"order": [[ 1, "asc" ]],
				"dom": '<pfltpi>',
				"pagingType": "full_numbers"
			});
		    
		    /**
			 * uniform美化
			 */
			$("input[type='checkbox'],input[type='radio']").uniform();//单选框和复选框
			$("input:file").uniform({//文件选择
				fileDefaultHtml: '请选择文件',
				fileButtonHtml: '浏览'
			});
		    
		    $("#loading-indicator").hide();//隐藏gif
		    $("#searchBtn").removeAttr("disabled");//解除禁用状态
		    $("#operationBtn").removeAttr("disabled");
		});
	});
});