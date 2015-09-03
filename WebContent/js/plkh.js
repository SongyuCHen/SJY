/**
 * 
 */
var basePath = getRootPath();
//保存上一次的查询条件，用于导出Excel时传递参数
var bmmc, nf, jd;

$(document).ready(function(){
	
	var curBmmc = $("#curBmmc").val();
	var curYear = $("#curYear").val();
	var curQuarter = $("#curQuarter").val();
	
	$("#bmmc").val(curBmmc);
	$("#nf").val(curYear);
	$("#jd").val(curQuarter);
	
	$("#operationBtn").attr("disabled","disabled");
	
	$("#searchForm").on("submit", function(e){
		e.preventDefault();
		bmmc = $("#bmmc").val();
		nf = $("#nf").val();
		jd = $("#jd").val();
		
		$("#table_content").empty();
		
		$("#loading-indicator").show();
		//禁用查询和操作按钮
		$("#searchBtn").attr("disabled","disabled");
		$("#operationBtn").attr("disabled","disabled");
		
		var getAllKfxmReq = $.ajax({
			url: basePath +"/pfkh/scplkh.aj",
			data: {
				bmmc: encodeURI(bmmc),
				nf: nf,
				jd: jd
			},
			type:'get',
			cache: false,
			dataType:'json'
		}).done(function(data){
			//由于dataType:json使得jquery将json字符串全部转成了对象，需要将其中list的转回字符串，
			//ie8支持该方法，更低版本不支持
			var mlist = data.mlist;
			for(var i = 0;i<mlist.length;i++){
				mlist[i].pfkhgzJsonStr = JSON.stringify(mlist[i].pfkhgzJsonStr);
			}
			
			var cont = Mustache.to_html($('#plkhTableTemplate').html(), data);
		    $('#table_content').html(cont);
		    
		    var plkhTable = $("#plkhTable").DataTable({
		        "order": [[ 0, "asc" ]],
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
	
	/**
	 * 打印批量考核
	 */
	$("#printPlkhLink").on('click', function(){
		var wind=window.open('about:blank', '', ''),
	      cont=$('#plkhTable').html();
	      //打印样式
	      wind.document.write('<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} @media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	      wind.print();
	});
	
	$("#exportPlkhLink").on('click', function(){
		var url = basePath + "/pfkh/downloadPlkhExcel?bmmc=" + encodeURI(encodeURI($("#bmmc").val())) + "&nf=" + nf + "&jd=" + jd;
		
		window.location = url;
	})
	
	$(document).on('click', ".pfkhgz", function(){
		var pfkhgzJsonStr = $(this).attr("data-pfkhgz");
		var pfkhgzArr = jQuery.parseJSON(pfkhgzJsonStr);
		$("#showDetailModal tbody").empty();
		for(var i=0;i<pfkhgzArr.length;i++){
			var pfkhgz = pfkhgzArr[i];
			var text = "<tr>" +
						   "<td>" + pfkhgz.gzmc + "</td>" +
						   "<td>" + pfkhgz.gzdf + "</td>" +
					   "</tr>";
			$("#showDetailModal tbody").append(text);
		}
		
		$("#showDetailModal").modal('show');
	});
	
});
