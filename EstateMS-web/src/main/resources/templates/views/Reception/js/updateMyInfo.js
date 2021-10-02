var MyInfoParames = {
    page:{
        update:["editPassword.html"],
        home:["home.html"]
    },
    action: {
        findMyInfo: "sysUser/findMyUserVo",
        updateMyInfo:"sysUser/updateMyUserVo",
        loginOut: "index/loginOut"
    }
}

$(function () {
    new uploadPreview({
        UpBtn: "tempMFile",
        DivShow: "imgGroup",
        ImgShow: "imgPreview"
    });
    findProFile();
    $("#btnSubmitUpdate").click(function () {
        submitUpdateUser();
    });
    $("#btnUpdate").click(function () {
        window .location.href=MyInfoParames.page.update;
    });
    $("#btnLoginOut").click(function () {
        requestUtilParames.xhrGet(MyInfoParames.action.loginOut).done(function (res) {
            window.location.href = "../../login.html";
        });
    });

    $("#btnClose").click(function () {
        window .location.href=MyInfoParames.page.home;
    });
});


function findProFile(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(MyInfoParames.action.findMyInfo).done(function (res) {
            var user = res.data.user;
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
        requestUtilParames.xhrUpload("#form_profile",MyInfoParames.action.updateMyInfo).done(function (res) {
            alert(res.message);
            gotoHomePage();
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

function gotoHomePage() {
    window .location.href=MyInfoParames.page.home;
}
