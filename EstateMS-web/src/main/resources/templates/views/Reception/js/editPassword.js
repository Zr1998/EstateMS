var editPsdParames = {
    page:{
        update:["updateMyInfo.html"]
    },
    action: {
        findMyInfo: "sysUser/findMyUserVo",
        updatePassword:"sysUser/updatePassword",
        loginOut: "index/loginOut"
    }
}

$(function () {
    findProFile();
    findSessionUser();
    $("#btnSubmit").click(function () {
        updatePassword();
    });
    $("#btnClose").click(function () {
       window.location.href="home.html" ;
    });
    $("#btnUpdate").click(function () {
        window .location.href=editPsdParames.page.update;
    });
    $("#btnLoginOut").click(function () {
        requestUtilParames.xhrGet(editPsdParames.action.loginOut).done(function (res) {
            window.location.href = "../../login.html";
        });
    });
});

function findSessionUser() {
    requestUtilParames.xhrGet(editPsdParames.action.findMyInfo).done(function (res) {
        var user = res.data.user; //获得用户信息
        if(user!=null&&user.user_id!=0){
            $("#login_div").addClass("hidden");
            $("#session_user_div").removeClass("hidden");
            $("#session_user_name").text(user.user_name);
            if (res.data.fileLog != null) {
                $("#session_user_url").attr("src", requestUtilParames.converFilePath(res.data.fileLog.save_path));
                $("#session_user_head_url").attr("src", requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
        }else {
            alert("您尚未登录，请登录！");
            window.location.href="../../login.html";
        }
    });
}

function findProFile() {
    layui.use('form', function(){
        requestUtilParames.xhrGet(editPsdParames.action.findMyInfo).done(function (res) {
            var user = res.data.user;
            $("#version_user_profile").val(user.version);
            $("#user_id_user_profile").val(user.user_id);
            //$("#btn_cancel").click();
        });
    });
}

/*
* @title:<h3> 修改当前登录用户密码 <h3>
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
            window.location.href="home.html";
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