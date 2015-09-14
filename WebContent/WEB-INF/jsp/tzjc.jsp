<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tjfx.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
$(function(){
	var chart1 = echarts.init(document.getElementById('bmkhmcb'));
	//$("#page2").removeClass("item");
	var chart2 = echarts.init(document.getElementById('fsbh'));
	//$("#page2").addClass("item");
	chart1.showLoading({
		text: '数据读取中...',
		effect: 'whirling'
		});
	chart2.showLoading({
		text: '数据读取中...',
		effect: 'whirling'
		});
	init();
	
	function init(){
		var nf1 = $("#nf1").val();
		var jd1 = $("#jd1").val();
		var bm1 = $("#bm1").val();
		var nf2_1 = $("#nf2_1").val();
		var jd2_1 = $("#jd2_1").val();
		var nf2_2 = $("#nf2_2").val();
		var jd2_2 = $("#jd2_2").val();
		var bm2 = $("#bm2").val();
		var xm2 = $("#xm2").val();
		$.ajax({
			url : getRootPath()+"/tjfx/getTzjcByBm.aj",
			data:{year:nf1,quarter:jd1,bm:bm1},
			type:'post',
			dataType:'json',
   		 	success : function(data){
   		 		chart1.hideLoading();
   		 	    //部门考核名次表
   				chart1.setOption({
   					title : {
   				        text: '部门考核名次表',
   				        //subtext: '注：'
   				    },
   				 	grid :{
				    	y2: 50
				    },
   				    tooltip : {
   				        trigger: 'item'
   				    },
   				    legend: {
   				        data:['得分']
   				    },
   				    toolbox: {
   				        show : true,
   				        feature : {
   				            //mark : {show: true},
   				            //dataView : {show: true, readOnly: false},
   				            magicType : {show: true, type: ['line', 'bar']},
   				            restore : {show: true},
   				            saveAsImage : {show: true}
   				        }
   				    },
   				    //calculable : true,
   				    xAxis : [
   				        {
   				            type : 'category',
   				            data : data.xms,
   				         	axisLabel : {
				         		interval : 0,
				         		rotate: 45,
				         		textStyle : {
				         			fontSize: 10,
				         			fontFamily: '微软雅黑',
			                    	fontStyle: 'italic'
				         		}
				         	}
   				        }
   				    ],
   				    yAxis : [
   				        {
   				            type : 'value'
   				        }
   				    ],
   				    series : [
   				        {
   				            name:'得分',
   				            type:'bar',
   				         	itemStyle: {
	   				    		normal: {
	   				    			label :{
	   				    				show : true,
	   				    				position : 'inside'
	   				    			},
	   				    			color: function(params) {
                        						//return "#"+("00000"+((1000000*params.dataIndex%16777215+0.5)>>0).toString(16)).slice(-6); 
									 var colorList = [
                         							 '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                          							 '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           							'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                          							 '#9ACD32','#FF7256','#87CEFA','#EEAD0E','#CD69C9',
                          							'#ADFF2F','#EEEE00','#9AC0CD','#CD3278','#B23AEE',
                          							'#AEEEEE','#7CCD7C','#DAA520','#EE0000','#EE7621',
                          							'#9F79EE','#9AFF9A','#CD6600','#C0FF3E','#FFD700',
                         							 '#EE7AE9','#ADD8E6','#EE8262','#F08080','#8FBC8F',
                         							'#00EE00','#87CEFA','#EE9A00','#EED5D2','#FF7256',
                          							'#FF00FF','#8DEEEE','#71C671','#B3EE3A','#EE2C2C'
                        							];
                        						return colorList[params.dataIndex]
                    						}
	   				    		}
	   				 		},
   				            data:data.scores,
   				            markPoint : {
   				                data : [
   				                    {type : 'max', name: '最大值'},
   				                    {type : 'min', name: '最小值'}
   				                ],
   				                itemStyle :{
   				                	normal : {
   				                		color : '#fcaf17'
   				                	}
   				                }
   				            },
   				            markLine : {
   				                data : [
   				                    {type : 'average', name: '平均值'}
   				                ]
   				            }
   				        }
   				    ]
   				});
   		 	    
   				var thead = "<tr><td>人员</td>";
   		 	    var tbody = "<tr><td>得分</td>";
   		 	    for(var i=0;i<data.xms.length;i++){
   		 	    	thead += "<td>"+data.xms[i]+"</td>";
   		 	    	tbody += "<td>"+data.scores[i]+"</td>";
   		 	    }
   		 	    var html = "<div class='table-title'>部门考核名次表</div><table class='table table-bordered dataTable no-footer'>"+thead+"</tr>"+tbody+"</tr></table>";
   		 	    $("#bmkhmcb_table").html(html);
   		 	}
		});
		
		$.ajax({
			url : getRootPath()+"/tjfx/getTzjcByGr.aj",
   		 	type : 'post',
   		    data:{fromYear:nf2_1,fromQuarter:jd2_1,toYear:nf2_2,toQuarter:jd2_2,bm:bm2,xm:xm2},
   		    dataType:'json',
   		 	success : function(data){
   		 		chart2.hideLoading();
   		 	    //个人考核分布变化表
   				chart2.setOption({
   					title : {
   				        text: '个人考核分布变化表',
   				        //subtext: '注：'
   				    },
   				 	grid :{
				    	y2: 100
				    },
   				    tooltip : {
   				        trigger: 'item'
   				    },
   				    legend: {
   				        data:['个人得分','部门平均得分']
   				    },
   				    toolbox: {
   				        show : true,
   				        feature : {
   				            //mark : {show: true},
   				            //dataView : {show: true, readOnly: false},
   				            magicType : {show: true, type: ['line', 'bar']},
   				            restore : {show: true},
   				            saveAsImage : {show: true}
   				        }
   				    },
   				    //calculable : true,
   				    xAxis : [
   				        {
   				            type : 'category',
   				            data : data.jds,
   				         	axisLabel : {
				         		interval : 0,
				         		rotate: 45,
				         		textStyle : {
				         			fontSize: 10,
				         			fontFamily: '微软雅黑',
			                    	fontStyle: 'italic'
				         		}
				         	}
   				        }
   				    ],
   				    yAxis : [
   				        {
   				            type : 'value'
   				        }
   				    ],
   				    series : [
   				        {
   				            name:'个人得分',
   				            type:'line',
   				        	itemStyle: {
	   				    		normal: {
	   				    			label :{
	   				    				show : true
	   				    			},
	   				    			color: function(params) {
                        						//return "#"+("00000"+((1000000*params.dataIndex%16777215+0.5)>>0).toString(16)).slice(-6); 
									 var colorList = [
                         							 '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                          							 '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           							'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                          							 '#9ACD32','#FF7256','#87CEFA','#EEAD0E','#CD69C9',
                          							'#ADFF2F','#EEEE00','#9AC0CD','#CD3278','#B23AEE',
                          							'#AEEEEE','#7CCD7C','#DAA520','#EE0000','#EE7621',
                          							'#9F79EE','#9AFF9A','#CD6600','#C0FF3E','#FFD700',
                         							 '#EE7AE9','#ADD8E6','#EE8262','#F08080','#8FBC8F',
                         							'#00EE00','#87CEFA','#EE9A00','#EED5D2','#FF7256',
                          							'#FF00FF','#8DEEEE','#71C671','#B3EE3A','#EE2C2C'
                        							];
                        						return colorList[params.dataIndex]
                    						}
	   				    		}
	   				 		},
   				            data:data.ryScores,
   				            /*markPoint : {
   				                data : [
   				                    {type : 'max', name: '最大值'},
   				                    {type : 'min', name: '最小值'}
   				                ]
   				            },
   				            markLine : {
   				                data : [
   				                    {type : 'average', name: '平均值'}
   				                ]
   				            }*/
   				        },
   				     	{
   				            name:'部门平均得分',
   				            type:'line',
   				         	itemStyle: {
	   				    		normal: {
	   				    			label :{
	   				    				show : true
	   				    			}
	   				    		}
	   				 		},
   				            data:data.bmScores,
   				            /*markPoint : {
   				                data : [
   				                    {type : 'max', name: '最大值'},
   				                    {type : 'min', name: '最小值'}
   				                ]
   				            },
   				            markLine : {
   				            	data : [
   	   				                {type : 'average', name: '平均值'}
   	   				            ]
   				            }*/
   				        }
   				    ]
   				});
   		 	}
		});
	}
	
	$("#searchBtn1").on("click",function(){
		chart1.showLoading({
			text: '数据读取中...',
			effect: 'whirling'
			});
		var nf = $("#nf1").val();
		var jd = $("#jd1").val();
		var bm = $("#bm1").val();
		updateBmkhmcb(nf, jd, bm);
	});
	
	$("#searchBtn2").on("click",function(){
		chart2.showLoading({
			text: '数据读取中...',
			effect: 'whirling'
			});
		var nf2_1 = $("#nf2_1").val();
		var jd2_1 = $("#jd2_1").val();
		var nf2_2 = $("#nf2_2").val();
		var jd2_2 = $("#jd2_2").val();
		var bm2 = $("#bm2").val();
		var xm2 = $("#xm2").val();
		updateFsbh(nf2_1, jd2_1, nf2_2, jd2_2, bm2, xm2);
	});
	
	$("#bm2").change(function(){
		var bm = $("#bm2").val();
		$.ajax({
			url: getRootPath() + '/user/getXmByBmmc.aj',
			type:'post',
			dataType:'json',
			data:{bm:bm},
			success:function(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					html += "<option>" + data[i].xm + "</option>";
				}
				$("#xm2").html(html);
			}
		});
	});
	
	$("#tb_select").on("change",function(){
		var select = $("#tb_select").val();
		if(select=="t"){
			$("#bmkhmcb").show();
			$("#bmkhmcb_table").hide();
		}else{
			$("#bmkhmcb").hide();
			$("#bmkhmcb_table").show();
		}
	});
	
	function updateBmkhmcb(year, quarter, bm){
		$.ajax({
			url : getRootPath()+"/tjfx/getTzjcByBm.aj",
   		 	type : 'post',
   		    data:{year:year,quarter:quarter,bm:bm},
		    dataType:'json',
   		 	success : function(data){
   		 		chart1.hideLoading();
   				chart1.setOption({
   					xAxis : [{data:data.xms}],
   					series : [{data:data.scores}]
   				});
   				
   				var thead = "<tr><td>人员</td>";
   		 	    var tbody = "<tr><td>得分</td>";
   		 	    for(var i=0;i<data.xms.length;i++){
   		 	    	thead += "<td>"+data.xms[i]+"</td>";
   		 	    	tbody += "<td>"+data.scores[i]+"</td>";
   		 	    }
   		 	    var html = "<div class='table-title'>部门考核名次表</div><table class='table table-bordered dataTable no-footer'>"+thead+"</tr>"+tbody+"</tr></table>";
   		 	    $("#bmkhmcb_table").html(html);
   		 	}
		});
	}
	
	function updateFsbh(fromYear, fromQuarter,toYear, toQuarter, bm, xm){
		$.ajax({
			url : getRootPath()+"/tjfx/getTzjcByGr.aj",
   		 	type : 'post',
   		    data:{fromYear:fromYear,fromQuarter:fromQuarter,toYear:toYear,toQuarter:toQuarter,bm:bm,xm:xm},
		    dataType:'json',
   		 	success : function(data){
   		 		chart2.hideLoading();
   				chart2.setOption({
   					xAxis : [{data:data.jds}],
   					series : [{data:data.ryScores},{data:data.bmScores}]
   				});
   		 	}
		});
	}
});
</script>
<title>庭长决策</title>
</head>
<body 	style="background-image:url(../images/background06.jpg); background-size:100%;">
	<jsp:include page="header.jsp" />

	<div id="wrapperinner">

		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<label class="label-control">庭长决策</label>
				</div>
			</div>
		</div>
		<div>
			<div style="height:60px;">
				<div id="menu_left">
					<select class="select-control" style="width:100px;" id="tb_select">
						<option value="t">图</option>
						<option value="b">表</option>
					</select>
				</div>
				<div id="menu_right">
					<label class="label-control" style="display:inline;">部门：</label>
					<select class="select-control" name="bm1" id="bm1">
						<c:forEach items="${bmArr }" var="bm">
							<option>${bm }</option>
						</c:forEach>
					</select>
					
					<label style="display:inline;">考核时间：</label>
					<select class="select-control" name="nf1" id="nf1">
						<c:forEach items="${years }" var="nf">
							<option>${nf }</option>
						</c:forEach>
					</select>
					<label style="display:inline;">年</label>
					<select class="select-control" class="jd" name="jd1" id="jd1">
						<c:forEach items="${quarters }" var="jd">
							<c:choose>
								<c:when test="${jd==thisQuarter}"><option selected="selected">${jd }</option></c:when>
								<c:otherwise><option>${jd }</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<label style="display:inline;">季度</label>
					
					<button type="submit" class="btn btn-primary" id="searchBtn1">
						<span class="glyphicon glyphicon-search"></span> 查 询
					</button>
				</div>
			</div>
			<div id="bmkhmcb" style="height:450px;"></div>
			<div id="bmkhmcb_table" style="display:none;"></div>
		</div>
		<div style="margin-top:50px;">
			<div  style="height:140px;">
				<div id="menu_right">
					<label class="label-control" style="display:inline;">部门：</label>
					<select class="select-control" name="bm2" id="bm2">
						<c:forEach items="${bmArr }" var="bm">
							<option>${bm }</option>
						</c:forEach>
					</select>
							
					<label style="display:inline;">姓名:</label> 
					<select class="select-control" name="xm2" id="xm2">
						<c:forEach items="${xmList }" var="xm">
							<option>${xm }</option>
						</c:forEach>
					</select>
					
					<label style="display:inline;">考核时间范围：</label>
					<select class="select-control" name="nf2_1" id="nf2_1">
						<c:forEach items="${years }" var="nf" varStatus="status">
							<c:choose>
								<c:when test="${status.index==1 }"><option selected="selected">${nf }</option></c:when>
								<c:otherwise><option>${nf }</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<label style="display:inline;">年</label>
					<select class="select-control" class="jd" name="jd2_1" id="jd2_1">
						<c:forEach items="${quarters }" var="jd">
							<c:choose>
								<c:when test="${jd==thisQuarter}"><option selected="selected">${jd }</option></c:when>
								<c:otherwise><option>${jd }</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<label style="display:inline;">季度</label>
						
					<button type="submit" class="btn btn-primary" id="searchBtn2">
						<span class="glyphicon glyphicon-search"></span> 查 询
					</button>
				</div>
				<div id="menu_right" style="margin-right:124px;">
					<label style="display:inline;">至</label>
					<select class="select-control" name="nf2_2" id="nf2_2">
						<c:forEach items="${years_bef }" var="nf">
							<option>${nf }</option>
						</c:forEach>
					</select>
					<label style="display:inline;">年</label>
					<select class="select-control" class="jd" name="jd2_2" id="jd2_2">
						<c:forEach items="${quarters }" var="jd">
							<c:choose>
								<c:when test="${jd==thisQuarter}"><option selected="selected">${jd }</option></c:when>
								<c:otherwise><option>${jd }</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<label style="display:inline;">季度</label>
				</div>
			</div>
			<div id="fsbh" style="height:450px;"></div>
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/jquery.dataTables.min.js"></script>
	<!-- dataTables针对bootstrap的表格风格 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts-all.js"></script>
</body>
</html>