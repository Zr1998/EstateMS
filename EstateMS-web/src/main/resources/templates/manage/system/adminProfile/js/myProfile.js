var proFileParames = {
        action: {
            findMyProFile: "manage/findMyProFile",
            findRoleName:"role/selectRoleName",
            updateMyProFile:"sysUser/updateMyProFile"
        }
}

$(function () {
    new uploadPreview({
        UpBtn: "tempMFile",
        DivShow: "imgGroup",
        ImgShow: "imgPreview"
    });
    findRoleName();
    findProFile();
    $("#btnSubmitUpdate").click(function () {
        submitUpdateUser();
    });

});

function findRoleName() {
    requestUtilParames.staticJson=false;
    requestUtilParames.xhrGet(proFileParames.action.findRoleName,"",{async:false}).done(function (res) {
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

function findProFile(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(proFileParames.action.findMyProFile).done(function (res) {
            var user = res.data.user;
            var listUserRole = res.data.listUserRole;
            //头像
            if(res.data.fileLog!=null){
                $("#imgPreview").attr("src",requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
            $("#account_profile").val(user.user_account);
            $("#user_name_profile").val(user.user_name);
            $("#phone_user_profile").val(user.user_phone);
            $("#address_user_profile").val(user.user_address);
            $("#email_user_profile").val(user.email);
            $("#sexGroup input[value='" + user.gender + "']").not(":checked").next(".layui-form-radio").click();
            $("#birthday_user_profile").val(baseUtilParames.simpDataForYYYYMMDD(user.birthday));
            $("#version_user_profile").val(user.version);
            $("#user_id_user_profile").val(user.user_id);
            if (res.data.role_id != 0) {
                    $("#roleNameGroup .userRoleItem[value='" + res.data.role_id + "']").next(".layui-form-checkbox").click();
            }
            $("#form_profile :input").not(":button").prop("disabled",true);

            //$("#btn_cancel").click();
        });
    });
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
        var data=$("#form_profile").serialize();
        console.log(data);
        requestUtilParames.xhrUpload("#form_profile",proFileParames.action.updateMyProFile).done(function (res) {
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
    var account=$("#account_profile").val();
    var username=$("#user_name_profile").val();
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
        $.each($("#form_profile .userRoleItem:checked"), function () {
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
