var editPsdParames = {
    action: {
        findMyProFile: "manage/findMyProFile",
        updatePassword:"sysUser/updatePassword"
    }
}

$(function () {
    findProFile();
    $("#btnSubmit").click(function () {
        updatePassword();
    });
});

function findProFile() {
    layui.use('form', function(){
        requestUtilParames.xhrGet(editPsdParames.action.findMyProFile).done(function (res) {
            var user = res.data.user;
            $("#version_user_profile").val(user.version);
            $("#user_id_user_profile").val(user.user_id);
            //$("#btn_cancel").click();
        });
    });
}

/*
* @title:<h3> 修改当前登录管理员密码 <h3>
* @author: Zr
* @date: 2018/12/20  9:43
* @params
* @return
**/
function updatePassword() {
    if (checkData()) {
        var data=$("#Password_form").serialize();
        console.log(data);
        requestUtilParames.xhr(editPsdParames.action.updatePassword,data).done(function (res) {
            alert(res.message);
            $("#btnClose").click();
        });
    }
}


function checkData() {
    var str = "";
        if ($("#old_password_submit").val() == "") {
            str += "原密码不能为空\n";
        }
        if ($("#password_user_submit").val() == ""||$("#password_user_confim").val()== "") {
            str += "新密码不能为空\n";
        }
        if ($("#password_user_submit").val()!=$("#passowrd_user_comfim").val()) {
            str += "两次输入的密码不一致\n";
        }
    if (str.length > 0) {
        alert(str);
        return false;
    } else {
        return true;
    }

}