$(document).ready(function(){
    $('#list').dataTable({
        "sPaginationType":"full_numbers",
        "bFilter":false,
        "bSort":true,
        "bLengthChange":false,
        "oLanguage":{
            "sProcessing" : "处理中...",
            "sLengthMenu" : "显示 _MENU_ 项结果",
            "sZeroRecords" : "没有匹配结果",
            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix" : "",
            "sSearch" : "搜索:",
            "sUrl" : "",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上页",
                "sNext" : "下页",
                "sLast" : "末页"
            }
        },
        "bSortClasses": false,
        "iDisplayLength": 4,
        "bRetrieve":true,
        "bDestory":true
    });
});