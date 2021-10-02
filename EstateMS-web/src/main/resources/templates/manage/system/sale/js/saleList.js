var SaleListParames={
    page:{
        show:["system/sale/viewSalesInfo.html?pk=","nav_saleInfo","查看详情"],
    },
    action:{
        findPageData:"contract/selectPageContractVo"
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
* @title:<h3> 分页查询销售信息 <h3>
* @author: Zr
* @date: 2018/12/21  12:49
* @params
* @return
**/
function findPageData(cpage) {
    requestUtilParames.findPageData(SaleListParames.action.findPageData,cpage,"#myTable","printPage",findPageData).done(function (res) {
        var list=res.data.list;
        var user=res.data.list.user;
        var contract=res.data.list.contract;
        var $tbody=$("#myTable tbody");

        for (var i=0;i<list.length;i++){
            var $tr=$("<tr></tr>");
            $tr.append("<td>"+list[i].user.user_name+"</td>");
            $tr.append("<td>"+list[i].estate_nums+"</td>");
            $tr.append("<td>"+list[i].developer_name+"</td>");
            $tr.append("<td>"+baseUtilParames.simpDataForYYYYMMDD(list[i].sign_time)+"</td>");

            $tbody.append($tr);
            var $btns=$("<td></td>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-normal permission_show hidden_per\" onclick='gotoShowPage("+list[i].user.user_id+")'>查看</a>");
            $tr.append($btns);
            $tbody.append($tr);
        }
        requestUtilParames.removePermissionBtns(res.permission_btns);
        $(".permission_show.hidden_per").each(function () {
            $(this).prop("disabled",true);
        });
        layui.use("form",function () {
            var form=layui.form;
            form.render();
        });
    });
}


/*
* @title:<h3> 跳转到详情页面 <h3>
* @author: Zr
* @date: 2018/10/15  16:31
* @params
* @return
**/
function gotoShowPage(pk) {
    globalParams.tabInnerChange(SaleListParames.page.show,pk);
}