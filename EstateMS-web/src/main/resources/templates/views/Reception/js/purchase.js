var purchaseParames={
    page:{
        edit: ["editPassword.html"],
        update:["updateMyInfo.html"]
    },
    action:{
        findMyMenu: "index/findMyMenu",
        findSessionUser:"sysUser/findMyUserVo",
        loginOut: "index/loginOut",
        findPageData: "estate/selectPageEstates",
        findEstateVoByPk:"estate/selectEstateVoByEstateId/",
        addCustomer:"customer/insertCustomerVo",
        addContract:"contract/insertContract"
    }
}

$(function () {
    var pk=baseUtilParames.GetQueryString("pk");
    $("#estateId").val(pk);
    if(pk!=null){
        $("#btnCancel").click(function () {
            gotoDetailPage(pk);
        });
    }
    findMyMenu();
    findSessionUser();
    $("#btnAddCustomerInfo").click(function () {
        submitCustomerInfo();
    });
    $("#btnEdit").click(function () {
        window.location.href=purchaseParames.page.edit;
    });
    $("#btnUpdate").click(function () {
        window .location.href=purchaseParames.page.update;
    });
    $("#btnLoginOut").click(function () {
        requestUtilParames.xhrGet(purchaseParames.action.loginOut).done(function (res) {
            window.location.href = "../../login.html";
        });
    });
});

function findMyMenu() {
    requestUtilParames.xhrGet(purchaseParames.action.findMyMenu).done(function (res) {
        //alert("查询成功");
    });
}

function findSessionUser() {
    requestUtilParames.xhrGet(purchaseParames.action.findSessionUser).done(function (res) {
        var user = res.data.user; //获得用户信息
        if(user!=null&&user.user_id!=0){
            $("#login_div").addClass("hidden");
            $("#session_user_div").removeClass("hidden");
            $("#session_user_name").text(user.user_name);
            $("#buyerName").val(user.user_name);
            $("#buyerId").val(user.user_id);
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

function showDetailData(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(purchaseParames.action.findEstateVoByPk+pk).done(function (res) {
            var estate=res.data.estate;
          $("#estate").val(estate.estate_name+estate.estate_buildingNum+estate.estate_unitNum+estate.estate_floorNum+estate.estate_roomNum);
            $("#developer").val(res.data.developer.developer_name);
            $("#developerId").val(res.data.developer.developer_id);
        });
    });
}

function submitCustomerInfo() {
    if(checkData()){
        var pk=baseUtilParames.GetQueryString("pk");
        $("#estateId").val(pk);
        if(pk!=null){
            showDetailData(pk);
        }
        $("#btnSubmit").click(function () {
            var r=window.confirm("一旦确认无法更改，您是否确认？");
            if(r==true){
                var data=$("#CustomerForm").serialize();
                console.log(data);
                //return false;
                requestUtilParames.xhr(purchaseParames.action.addCustomer,data).done(function (res) {
                    addContract();
                    alert("预购成功！请您于5个工作日内到销售中心办理购房合同。");
                    window.location.href="home.html";
                });
            }else {
                $('.claseDialogBtn').click();
            }
        });
    }
}

function addContract() {
    var pk=baseUtilParames.GetQueryString("pk");
    $("#estate_Id").val(pk);
    var data=$("#editForm").serialize();
    requestUtilParames.xhr(purchaseParames.action.addContract,data)
}

function  checkData() {
    var str="";
    var card_id=$("#card_id").val();
    var customer_name=$("#name").val();
    if(card_id==""){
        str+="身份证号不能为空 \n";
    }
    if(customer_name==""){
        str+="客户姓名不能为空 \n";
    }
    if(str.length>0){
        alert(str);
        return false;
    }else{
        return true;
    }
}

function gotoDetailPage(pk) {
    window.location.href="detailsPage.html?pk="+pk;
}