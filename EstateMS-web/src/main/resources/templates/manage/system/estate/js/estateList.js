var estateListParams = {
    page:{
        add: ["system/estate/addEstate.html", "nav_addEstate", "新增房源信息"],
        update: ["system/estate/editEstate.html?pk=", "nav_editEstate", "编辑房源信息"]
    },
    action:{
        findPageData: "estate/selectPageEstates",
        delEstate: "estate/deleteEstate/",
        changeState:"estate/tiggerState/"
    }
}
$(function () {
    requestUtilParames.staticJson = false;
    $("#btnAdd").click(function () {
        globalParams.tabInnerChange(estateListParams.page.add);
    });
    findPageData();
    $("#btnDelete").click(function () {
        deleteEstate();
    });
    $("#btnSearch").click(function () {//搜索
        requestUtilParames.searchForm("#formSearch");
        findPageData();
    });
    $("#estateTable tbody").on("click",".layui-form-switch",function () {
        var state = $(this).prev(".state").val();
        requestUtilParames.xhrGet(estateListParams.action.changeState + state).done(function (res) {
            alert(res.message);
        });
    });
});

/**
 * @title:<h3> 分页查询角色列表信息 <h3>
 * @author: JZL
 * @date: 2018-10-15 16:02
 * @params
 * @return
 **/
function findPageData(cpage) {
    requestUtilParames.findPageData(estateListParams.action.findPageData, cpage, "#estateTable", "printPage", findPageData).done(function (res) {
        var list = res.data.list;
        var $tbody = $("#estateTable tbody");
        for (var i = 0; i < list.length; i++) {
            var $tr = $("<tr></tr>");
            $tr.append(" <td><input type=\"checkbox\" class=\"inpDel\" value='" + list[i].estate_id + "'></td>");
            $tr.append("<td>" + list[i].estate.estate_name + "</td>");
            $tr.append("<td>" + list[i].estate.estate_buildingNum + "</td>");
            $tr.append("<td>" + list[i].estate.estate_unitNum + "</td>");
            $tr.append("<td>" + list[i].estate.estate_floorNum + "</td>");
            $tr.append("<td>" + list[i].estate.estate_roomNum + "</td>");
            $tr.append("<td>" + list[i].estate.estate_structure + "</td>");
            $tr.append("<td>" + list[i].estate.estate_price + "元</td>");
            $tr.append('<td class="layui-form"><input type="checkbox" class="state permission_update hidden_per" lay-skin="switch" lay-text="在售|已售" value="'+list[i].estate_id+'"></td>');
            if(list[i].estate.estate_state==0){
                $tr.find(".state").prop("checked",true);
            }
            var $btns = $("<td></td>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-normal\" onclick=gotoEditPage(" + list[i].estate_id + ")>编辑</a>")
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-danger permission_delete hidden_per\"  onclick='deleteEstate(" + list[i].estate_id + ")'>删除</a>");
            $tr.append($btns);
            $tbody.append($tr);
        }
        requestUtilParames.removePermissionBtns(res.permission_btns);
        $(".permission_update.hidden_per").each(function () {
            $(this).prop("disabled",true);
        });
        layui.use("form",function () {
            var form=layui.form;
            form.render();
        });
    });
}

/*
* @title:<h3> 删除房产 <h3>
* @author: Zr
* @date: 2018/12/22  17:29
* @params
* @return
**/
function deleteEstate(pk) {
    requestUtilParames.deleteData(estateListParams.action.delEstate,pk,"#estateTable",findPageData)
}

/**
 * @title:<h3>跳转到编辑页面  <h3>
 * @author: Zr
 * @date: 2018-10-15 16:31
 * @params
 * @return
 **/
function gotoEditPage(pk) {
    globalParams.tabInnerChange(estateListParams.page.update, pk);
}