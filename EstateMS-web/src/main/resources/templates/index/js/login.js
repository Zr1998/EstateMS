var LoginParames={
    action:{
        login:"index/login"
    }
}

$(function () {
   getCookie();
});



$(document).ready(function() {
    //验证码
    var code;
    function createCode(){
        code = '';//首先默认code为空字符串
        var codeLength = 4;//设置长度，这里看需求，我这里设置了4
        var codeV = $(".verification");

        //设置随机字符
        var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R', 'S','T','U','V','W','X','Y','Z');
        for(var i = 0; i < codeLength; i++){ //循环codeLength 我设置的4就是循环4次
            var index = Math.floor(Math.random()*36); //设置随机数范围,这设置为0 ~ 36
            code += random[index]; //字符串拼接 将每次随机的字符 进行拼接
        }
        codeV.text(code);//将拼接好的字符串赋值给展示的Value
    }
    //页面开始加载验证码
    createCode();
    //验证码加载点击事件
    $(".verification").bind('click',function() {
        createCode();
    });
    //下面就是判断是否==的代码，无需解释
    $("#btnSubmit").click(function () {
        var oValue = $("#in1").val().toUpperCase();
        $("#yz").html("");
        if(oValue ==""){
            $("#yz").html("<font color='red'>请输入验证码</font>");
        }else if(oValue != code){
            $("#yz").html("<font color='red'>验证码不正确，请重新输入</font>");
            oValue = "";
            createCode();
        }else{
            $("#yz").html("<font color='#32cd32'>验证码正确</font>");
            login();
        }
    });
});

function getCookie() {
    var cookiesName = $.cookie("user_account");
    var cookiesPassword = $.cookie("password");
    var cookiesIsSaveNP = $.cookie("isSaveNP");
    if (cookiesIsSaveNP == 1) {
        $("#user_account-submit").val(cookiesName);
        $("#user_password-submit").val(cookiesPassword);
        $("#isSaved").prop("checked", true);
    } else {
        $("#isSaved").prop("checked", false);
    }
}

function login() {
    if(checkData()){ //如果验证通过，则进行登录请求
        var pass=rasEncrypt($("#user_password-submit").val());
        console.log(pass);
        $("#user_password-submit_hidden").val(pass);
        var data=$("#login_form").serialize();
        requestUtilParames.xhr(LoginParames.action.login,data).done(function (res) {
            var user=res.data;
            if(res.resultCode>0){
                //登录失败
                alert(res.message);
            }else {
                // alert("登录成功");
                if ($("#isSaved").prop("checked")) {
                    $.cookie("user_account", $("#user_account-submit").val());
                    $.cookie("password", $("#user_password-submit").val());
                    $.cookie("isSaveNP", $("#isSaved:checked").val());
                } else {
                    $.cookie("user_account", "");
                    $.cookie("password", "");
                    $.cookie("isSaveNP", 0);
                }
                if(user.role_id>1){
                    window.location.href="manage/index.html";
                }else{
                    window.location.href="views/Reception/home.html";
                }

            }

        });
    }
}

/*
* @title:<h3> 验证提交 <h3>
* @author: Zr
* @date: 2018/12/5  10:58
* @params
* @return
**/
function checkData() {
    var str="";
    var user_account = $("#user_account-submit").val();
    var user_password = $("#user_password-submit").val();
    if(user_account==""){
        str += "用户名不能为空 \n";
    }
    if(user_password==""){
        str += "密码不能为空 \n";
    }
    if(str.length>0){ //如果消息不为空，则说验证不通过
        alert(str);
        return false;
    }
    return true;
}

/*
* @title:<h3> 密码加密 <h3>
* @author: Zr
* @date: 2018/12/5  10:58
* @params
* @return
**/
function rasEncrypt(str) {
    var encrypedPwd = "";
    $.ajax({
        url:requestUtilParames.host+"rsa/generateRSAJsKey",
        async:false,
        success:function (result) {
            if (result!=null){
                //加密模
                var Modulus = result.split(';')[0];
                //公钥指数
                var public_exponent = result.split(';')[1];
                //通过模和公钥参数获取公钥
                var key=new RSAUtils.getKeyPair(public_exponent,"",Modulus);
                //颠倒密码的顺序，要不然解密后会发现密码顺序是反的
                var reversedPwd=str.split("").reverse().join("");
                //对密码进行加密传输
                encrypedPwd = RSAUtils.encryptedString(key,reversedPwd);
            }
        },error:function () {
            alert("请求异常");
        }
    });
    return encrypedPwd;
}
