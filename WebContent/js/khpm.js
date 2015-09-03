/**
 * 
 */
var basePath = getRootPath();
//保存上一次查询的条件，用于导出Excel
var khzb, bmlx, nf, jd;

$(document).ready(function(){
	$("#pfkh").addClass('active').siblings().removeClass('active');
	
	var curKhzbmc = $("#curKhzbmc").val();
	var curBmlxmc = $("#curBmlxmc").val();
	var curYear = $("#curYear").val();
	var curQuarter = $("#curQuarter").val();
	
	$("#khzb").val(curKhzbmc);
	$("#bmlx").val(curBmlxmc);
	$("#nf").val(curYear);
	$("#jd").val(curQuarter);
	
	$("#operationBtn").attr("disabled","disabled");
	
	$("#searchForm").on("submit", function(e){
		e.preventDefault();
		//读取并保存
		khzb = $("#khzb").val();
		bmlx = $("#bmlx").val();
		nf = $("#nf").val();
		jd = $("#jd").val();
		
		$("#table_content").empty();
		
		$("#loading-indicator").show();
		$("#searchBtn").attr("disabled","disabled");
		$("#operationBtn").attr("disabled","disabled");
		
		var getAllKfxmReq = $.ajax({
			url: basePath +"/khpm/cx.aj",
			data: {
				khzb: encodeURI(khzb),
				bmlx: encodeURI(bmlx),
				nf: nf,
				jd: jd
			},
			type:'get',
			cache: false,
			dataType:'json'
		}).done(function(data){
			var cont, mlist;
			if(data.curKhzbmc == '所有指标'){
				mlist = data.mPfkhList || [];
				for(var i = 0;i<mlist.length;i++){
					//由于dataType:json使得jquery将json字符串全部转成了对象，需要将其中list的转回字符串，
					//ie8支持该方法，更低版本不支持
					mlist[i].pfkhgzJsonStr = JSON.stringify(mlist[i].pfkhgzJsonStr);
					//手动添加序号
					mlist[i].index = i+1;
				}
				cont = Mustache.to_html($('#syzbKhpmTableTemplate').html(), data);
			}
			else{
				mlist = data.mPfkhgzList || [];
				for(var i = 0;i<mlist.length;i++){
					//手动添加序号
					mlist[i].index = i+1;
				}
				cont = Mustache.to_html($('#khpmTableTemplate').html(), data);
			}
			
		    $('#table_content').html(cont);
		    
		    var khpmTable = $("#khpmTable").DataTable({
		        "order": [[ 0, "asc" ]],
		        "dom": '<pfltpi>',
				"pagingType": "full_numbers"
			});
		    
		    $("#loading-indicator").hide();
		    $("#searchBtn").removeAttr("disabled");
		    $("#operationBtn").removeAttr("disabled");
		});
	});
	
	/**
	 * 打印考核排名
	 */
	$("#printKhpmLink").on('click', function(){
		var wind=window.open('about:blank', '', ''),
	      cont=$('#khpmTable').html();
	      //打印样式
	      wind.document.write('<style>@media print{.no-print{display:none;}.no-wrap{word-break: keep-all;white-space:nowrap;}} @media all{th,td{border:1px solid #000;}table{border-collapse:collapse;width:95%;}}</style><table>'+cont+'</table>');
	      wind.print();
	});
	
	/**
	 * 导出考核排名
	 */
	$("#exportKhpmLink").on('click', function(){
		var khzbmc = encodeURI(encodeURI(khzb));
		var bmlxmc = encodeURI(encodeURI(bmlx));
		var url = basePath + "/khpm/downloadExcel?khzbmc=" + khzbmc + "&bmlxmc=" + bmlxmc + "&nf=" + nf + "&jd=" + jd;
		
		window.location = url;
	});
	
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