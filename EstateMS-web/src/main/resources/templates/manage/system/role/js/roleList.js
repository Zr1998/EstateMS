var roleListParames={
    page:{
        add:["system/role/addRole.html","nav_roleAdd","新增角色"],
        edit:["system/role/editRole.html?pk=","nav_roleEdit","编辑角色"]
    },
    action:{
        //findPageData:"files/data/role/selectPageRole.json"
        findPageData:"role/selectPageRole",
        delRole:"role/deleteRole/",
    }
}

$(function () {
    requestUtilParames.staticJson=false;
    $("#btnAdd").click(function () {
        globalParams.tabInnerChange(roleListParames.page.add);
        // var $navTemp=parent.$("#nav_temp");
        // $navTemp.attr("data-url","addRole.html").attr("data-id","nav_roleAdd").text("角色新增");
        // $navTemp.click()
    });
    findPageData();
    $("#btnSearch").click(function () {
       requestUtilParames.searchForm("#formSearch");
       findPageData();
    });
    $("#btnDelmore").click(function () {
        delRole();
    });
});


/*
* @title:<h3>分页查询角色列表信息<h3>
* @author: Zr
* @date: 2018/10/15  16:02
* @params
* @return
**/
function findPageData(cpage) {
    if(cpage==null){
        cpage=1;
    }
    requestUtilParames.findPageData(roleListParames.action.findPageData,cpage,"#myTable","printPage",findPageData).done(function (res) {
        var list=res.data.list;
        var $tbody=$("#myTable tbody");
        for (var i=0;i<list.length;i++){
            var $tr=$("<tr></tr>");

            $tr.append('<td><input type="checkbox" class="inpDel" value="'+list[i].role_id+'"></td>');
            $tr.append("<td>"+list[i].name+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].memo)+"</td>");
            if(list[i].update_user_name ==null || list[i].update_user_name ==""){
                $tr.append("<td>"+(list[i].create_user_name)+"</td>");
            }else{
                $tr.append("<td>"+(list[i].update_user_name)+"</td>");
            }
            if(list[i].update_time ==null || list[i].update_user_name ==""){
                $tr.append("<td>"+(baseUtilParames.simpDataFormat(list[i].create_time,null))+"</td>");
            }else{
                $tr.append("<td>"+(baseUtilParames.simpDataFormat(list[i].update_time,null))+"</td>");
            }
            var $btns=$("<td></td>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-normal\" onclick='gotoEditPage("+list[i].role_id+")'>编辑</a>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-danger\" onclick='delRole("+list[i].role_id+")'>删除</a>");
            $tr.append($btns);
            $tbody.append($tr);
        }
        layui.use("form",function () {
            var form=layui.form;
            form.render();
        });
    });
}

/*
* @title:<h3>  <h3>
* @author: Zr
* @date: 删除角色  14:17
* @params
* @return
**/
function delRole(pk) {
    requestUtilParames.staticJson=false;
    requestUtilParames.deleteData(roleListParames.action.delRole,pk,"#myTable",findPageData);
}

/*
* @title:<h3> 跳转到编辑页面 <h3>
* @author: Zr
* @date: 2018/10/15  16:31
* @params
* @return
**/
function gotoEditPage(pk) {
    globalParams.tabInnerChange(roleListParames.page.edit,pk);
}

