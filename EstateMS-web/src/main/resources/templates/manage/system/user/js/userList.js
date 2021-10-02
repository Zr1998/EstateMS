var userListParames={
    page:{
        add:["system/user/addUser.html","nav_userAdd","新增用户"],
        edit:["system/user/editUser.html?pk=","nav_userEdit","编辑用户"]
    },
    action:{
        findPageData:"sysUser/selectPageUserVo",
        delUser:"sysUser/deleteUser/",
        changeState:"sysUser/tiggerState/",
        resetPassword:"sysUser/resetPassword/",
        exp:"sysUser/expUserDTO",
        imp:"sysUser/impUserDTO"
    },
    model:{
        "su.user_account":{
            "comment":"用户名",
            "type":"varchar",
        }
        ,"su.user_name":{
            "comment":"姓名",
            "type":"varchar"
        }
        ,"su.gender":{
            "comment":"性别",
            "type":"number",
            "item":{
                "0":"男",
                "1":"女"
            }
         }
        ,"su.user_phone":{
            "comment":"电话"
        }
        ,"su.birthday":{
            "comment":"出生日期",
            "type":"date"
        }
        ,"su.create_time":{
            "comment":"创建时间",
            "type":"datetime"
        }
    }
}

$(function () {
    requestUtilParames.staticJson=false;
    findPageData();
    $("#btnAdd").click(function () {
        globalParams.tabInnerChange(userListParames.page.add);
        // var $navTemp=parent.$("#nav_temp");
        // $navTemp.attr("data-url","addUser.html").attr("data-id","nav_userAdd").text("用户新增");
        // $navTemp.click()
    });

    $("#btnAdvancedSearch").click(function () {
        requestUtilParames.createAdvancedQueryDialog(userListParames.model,"#userSearchDialog",findPageData);
    });

    $("#myTable tbody").on("click",".layui-form-switch",function () {
        var state = $(this).prev(".state").val();
        requestUtilParames.xhrGet(userListParames.action.changeState + state).done(function (res) {
            alert(res.message);
        });
    });

    $("#btnDelmore").click(function () {
        deleteUser();
    });
    $("#btnSearch").click(function () {
        requestUtilParames.searchForm("#formSearch");
        findPageData();
    });

    $("#btnExp").click(function () { //导出
        requestUtilParames.expData(userListParames.action.exp,$("#printPage .layui-laypage-curr").text());
    });
    $("#btnImp").click(function () { //导入
        requestUtilParames.impData(userListParames.action.imp);
        findPageData();
    });
});

/*
* @title:<h3> 分页查询用户列表 <h3>
* @author: Zr
* @date: 2018/10/22  13:35
* @params 
* @return 
**/
function findPageData(cpage){
    requestUtilParames.findPageData(userListParames.action.findPageData,cpage,"#myTable","printPage",findPageData).done(function (res) {
        var list=res.data.list;
        var $tbody=$("#myTable tbody");
         //  $tbody.empty();
        for (var i=0;i<list.length;i++){
            var $tr=$("<tr></tr>");
            $tr.append('<td><input type="checkbox"   class="layui-form inpDel" value="'+list[i].user_id+'"></td>');
            $tr.append("<td>"+list[i].user.user_account+"</td>");
            $tr.append("<td>"+list[i].user.user_name+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].role_names)+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].user.email)+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].user.user_address)+"</td>");
            $tr.append("<td>"+baseUtilParames.simpDataFormat(list[i].user.birthday)+"</td>");
            // $tr.append('<td class="layui-form"><input type="checkbox" class="state permission_update hidden_per" lay-skin="switch" value="'+list[i].user_id+'"></td>');
            // if(list[i].user.state==1){
            //     $tr.find(".state").prop("checked",true);
            // }
            var $btns=$("<td></td>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-normal\" onclick='gotoEditPage("+list[i].user_id+")'>编辑</a>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-warm permission_update hidden_per\" onclick='resetPsw("+list[i].user_id+")'>重置密码</a>")
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-danger permission_delete hidden_per\" onclick='deleteUser("+list[i].user_id+")'>删除</a>");
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
* @title:<h3> 删除用户信息 <h3>
* @author: Zr
* @date: 2018/10/27  14:47
* @params
* @return
**/
function deleteUser(pk) {
    requestUtilParames.deleteData(userListParames.action.delUser,pk,"#myTable",findPageData)
}



/*
* @title:<h3> 重置用户密码 <h3>
* @author: Zr
* @date: 2018/10/27  14:49
* @params 
* @return 
**/
function resetPsw(pk) {
    requestUtilParames.xhrGet(userListParames.action.resetPassword + pk).done(function (res) {
       alert(res.message);
    });
}

/*
* @title:<h3> 跳转到编辑页面 <h3>
* @author: Zr
* @date: 2018/10/15  16:31
* @params
* @return
**/
function gotoEditPage(pk) {
    globalParams.tabInnerChange(userListParames.page.edit,pk);
}

