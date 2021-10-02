var RegisterParames={
    action:{
        register:"user/regist"
    }
}

$(function () {
    $("#btnSubmit").click(function () {
        regist();
    });

    $("#btnReset").click(function () {
        $("#regist_form")[0].reset();
    });
});


function regist() {
        if(checkData()){
            var data=$("#regist_form").serialize();
            console.log(data);
            //return false;
            //requestUtilParames.xhr(userEditParames.action.addUser,data).done(function (res) {
            requestUtilParames.xhr(RegisterParames.action.register,data).done(function (res) {
                var user=res.data;
                if(user!=null){
                    alert(res.message);
                    window.location.href="login.html";
                }
                else {
                    alert("注册失败");
                }
            });
        }
}

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