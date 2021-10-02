var OptionLogParames={
    action:{
        findPageData:"operationLog/selectPageOperationLog"
    },
}

$(function () {
    requestUtilParames.staticJson=false;
    findPageData();
    $("#btnSearch").click(function () {
       requestUtilParames.searchForm("#formSearch");
        findPageData();
    });
});

/*
* @title:<h3> 分页查询操作日志 <h3>
* @author: Zr
* @date: 2018/10/19  13:49
* @params
* @return
**/
function findPageData(cpage) {
    requestUtilParames.findPageData(OptionLogParames.action.findPageData,cpage,"#myTable","printPage",findPageData).done(function (res) {
        var list=res.data.list;
        for (var i=0;i<list.length;i++){
            var $tr=$("<tr></tr>");
            $tr.append("<td>"+list[i].user_account+"</td>");
            $tr.append("<td>"+list[i].user_name+"</td>");
            $tr.append("<td>"+list[i].request_ip+"</td>");
            $tr.append("<td>"+baseUtilParames.simpDataFormat(list[i].date)+"</td>");
            $tr.append("<td>"+list[i].module+"</td>");
            $tr.append("<td>"+[list[i].type]+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].content)+"</td>");
            $("#myTable tbody").append($tr);
        }
    });
}