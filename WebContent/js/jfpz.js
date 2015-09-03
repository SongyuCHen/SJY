/**
 * 
 */
var basePath = getRootPath();

showAddJfpz = function(){
	$("#addJfpz").modal('show');

};
	
showEditJfpz = function(){
	var checkedNum = 0;
	$("input:checked").each(function(){
			checkedNum += 1;
	});
	
	if(checkedNum==0){
		$("#chooseErrorInfo").empty();
		var text = "<strong>警告！您没有选择任何记录进行编辑!</strong>";
		$("#chooseErrorInfo").append(text);
		$("#chooseError").modal('show');
		return false;
	}
	
	if(checkedNum>1){
		
		$("#chooseErrorInfo").empty();
		var text = "<strong>警告！每次只能选择一条记录进行编辑!</strong>";
		$("#chooseErrorInfo").append(text);
		$("#chooseError").modal('show');
		return false;
	}
	
	var tr = $(":checkbox:checked").closest("tr");
	var bh = tr.children("td:eq(0)").find("#hiddenBh").val();
	var jb = tr.children("td:eq(1)").html();
	var jfz = tr.children("td:eq(2)").html();
	
	$("#editModalBh").val(bh);
	$("#editModalJb").val(jb);
	$("#editModalJfz").val(jfz);
	
	$("#editJfpz").modal('show');
};

addJfpz = function(){
	$.ajax({
		cache:false,
		type:"POST",
		url:basePath + "/jfpz/add",
		data:$('#addJfpzForm').serialize(),
		dataType:'json',
		async:false,
		success:function(data){
			$("#addJfpz").modal('hide');
			
			var jfpz = data.jfpz;
			
			var text = "<tr>" +
						 "<td>" +
						   "<input type='checkbox' />" +
						   "<input type='hidden' value='" + jfpz.bh + "' />" +
						 "</td>" +
						 "<td>" + jfpz.jb + "</td>" +
						 "<td>" + jfpz.jfz + "</td>" +
					   "</tr>";
			
			$("#tableBody").append(text);
			
			$("#addJfpzSuc").modal('show');
		},
		error:function(request){
			$("#addJfpz").modal('hide');
			$("#jfpzError").modal('show');
		}
	});
};

editJfpz = function(){
	var bh = $("#editModalBh").val();
	var jb = $("#editModalJb").val();
	var jfz = $("#editModalJfz").val();
	
	var tr = $(":checkbox:checked").closest("tr");
	
	$.ajax({
		cache:false,
		type:"POST",
		url:basePath + "/jfpz/edit",
		data:$('#editJfpzForm').serialize(),
		dataType:'json',
		async:false,
		success:function(data){
			var status = data.status;
			if(status=="success"){
				$("#editJfqx").modal('hide');
				$("#editJfqxSuc").modal('show');
				
				tr.children("td:eq(1)").html(ryxm);
				tr.children("td:eq(2)").html(bmmc);
				tr.children("td:eq(3)").html(jllx);
				tr.children("td:eq(4)").html(hdsj);
				tr.children("td:eq(5)").html(jblx);
				tr.children("td:eq(6)").html(jfz);
			}
			else{
				$("#editJfqx").modal('hide');
				$("#jfqxError").modal('show');
			}	
		},
		error:function(request){
			$("#editJfqx").modal('hide');
			$("#jfqxError").modal('show');
		}
	});
};

$(document).ready(function(){
	$("#pfpz").addClass('active').siblings().removeClass('active');
	
});