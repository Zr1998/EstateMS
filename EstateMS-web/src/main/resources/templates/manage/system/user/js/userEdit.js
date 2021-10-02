var userEditParames={
    action:{
        findRoleName: "role/selectRoleName",
        addUser:"sysUser/insertUserVo",
        findUserVoByPk:"sysUser/selectUserVoByUserId/",
        updateUser:"sysUser/updateUserVo"
    }
}

$(function () {
    requestUtilParames.staticJson=false;
    new uploadPreview({
        UpBtn:"tempMFile",
        DivShow:"imgGroup",
        ImgShow:"imgPreview"
    });
    findRoleName();
    $("#btnSubmit").click(function () {
        submitAddUser();
    });
    $("#btnSubmitUpdate").click(function () {
        submitUpdateUser();
    });
    //获得地址栏的地址
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
        //  pk=31;
        showEditData(pk);
    }
});

/*
* @title:<h3> 所属角色 <h3>
* @author: Zr
* @date: 2018/10/23  17:12
* @params
* @return
**/
function findRoleName() {
    requestUtilParames.staticJson=false;
    requestUtilParames.xhrGet(userEditParames.action.findRoleName,"",{async:false}).done(function (res) {
        var list = res.data;
        $("#roleNameGroup").empty();
        for (var i = 0; i < list.length; i++) {
            $("#roleNameGroup").append("<input type=\"checkbox\" class='userRoleItem' value='" + list[i].role_id + "' name=\"\" title=\"" + list[i].name + "\"> ");
        }
        layui.use('form', function () {
            var form = layui.form;
            form.render(); //刷新渲染
        });
    });
}

/*
* @title:<h3> 显示用户管理编辑页面数据 <h3>
* @author: Zr
* @date: 2018/10/17  13:28
* @params pk[主键id]
* @return
**/
function showEditData(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(userEditParames.action.findUserVoByPk+pk).done(function (res) {
            var user = res.data.user;
            var listUserRole = res.data.listUserRole;
            //头像
            if(res.data.fileLog!=null){
                $("#imgPreview").attr("src",requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
            $("#account_user_submit").val(user.user_account);
            $("#name_user_submit").val(user.user_name);
            $("#phone_user_submit").val(user.user_phone);
            $("#email-user-submit").val(user.email);
            $("#address-user-submit").val(user.user_address);
            $("#user_sex_group input[value='" + user.gender + "']").not(":checked").next(".layui-form-radio").click();
            $("#birthday_user_submit").val(baseUtilParames.simpDataForYYYYMMDD(user.birthday));
            $("#version_user_submit").val(user.version);
            $("#user_id_user_submit").val(user.user_id);
            if (listUserRole != null) {
                for (var i = 0; i < listUserRole.length; i++) {
                    $("#roleNameGroup .userRoleItem[value='" + listUserRole[i].fk_role_id + "']").next(".layui-form-checkbox").click();
                }
            }
            $("#formSubmit :input").not(":button").prop("disabled",true);

            //$("#btn_cancel").click();
        });
    });
}

/*
* @title:<h3> 新增用户信息 <h3>
* @author: Zr
* @date: 2018/10/22  16:12
* @params
* @return
**/
function submitAddUser() {
    if(checkData()){
        var data=$("#formSubmit").serialize();
        console.log(data);
        //return false;
        //requestUtilParames.xhr(userEditParames.action.addUser,data).done(function (res) {
        requestUtilParames.xhrUpload("#formSubmit",userEditParames.action.addUser).done(function (res) {
            alert(res.message);
            $("#formSubmit")[0].reset();
        });
    }
}

/*
* @title:<h3>提交修改用户 <h3>
* @author: Zr
* @date: 2018/10/24  18:45
* @params
* @return
**/
function submitUpdateUser() {
    if (checkData()) {
        var data=$("#formSubmit").serialize();
        console.log(data);
        requestUtilParames.xhrUpload("#formSubmit", userEditParames.action.updateUser).done(function (res) {
            alert(res.message);
            $("#btnClose").click();
        });
    }
}

/*
* @title:<h3> 验证用户提交 <h3>
* @author: Zr
* @date: 2018/10/22  16:13
* @params
* @return
**/
function  checkData() {
    var str="";
    var account=$("#account_user_submit").val();
    var username=$("#name_user_submit").val();
    if(account==""){
        str+="账号不能为空 \n";
    }
    if(username==""){
        str+="用户姓名不能为空 \n";
    }

    if(str.length>0){
        alert(str);
        return false;
    }else{
        var i = 0;
        $.each($("#formSubmit .userRoleItem:checked"), function () {
            $(this).attr("name", "listUserRole[" + i + "].fk_role_id");
            i++;
        });
        return true;
    }
}

/*
* @title:<h3> 绑定编辑页面编辑按钮事件 <h3>
* @author: Zr
* @date: 2018/10/23  14:04
* @params
* @return
**/
$("#btnEdit").click(function () {
    $(this).addClass("hidden");//隐藏编辑按钮
    //显示取消按钮，显示修改按钮
    $("#btnCancel,btnSubmitUpdate").removeClass("hideen");
    $(this).parents("form").find(":input").not(":button").prop("disabled",false);
});