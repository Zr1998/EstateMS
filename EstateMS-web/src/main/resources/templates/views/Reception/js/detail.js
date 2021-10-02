var detailParames={
    page:{
        edit: ["editPassword.html"],
        buy: ["purchaseInfo.html?pk="],
        update:["updateMyInfo.html"]
    },
    action:{
        findMyMenu: "index/findMyMenu",
        findSessionUser:"sysUser/findMyUserVo",
        loginOut: "index/loginOut",
        findPageData: "estate/selectPageEstates",
        findEstateVoByPk:"estate/selectEstateVoByEstateId/",
    }
}

$(function () {
    findSessionUser();
    findMyMenu();
    $("#btnLoginOut").click(function () {
        requestUtilParames.xhrGet(detailParames.action.loginOut).done(function (res) {
            window.location.href = "../../login.html";
        });
    });
    //获得地址栏的地址
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
        //  pk=31;
        showDetailData(pk);
    }
    $("#btnBuy").click(function () {
        gotoPurchasePage($(this).val());
    });
    $("#btnBack").click(function () {
       window.location.href="home.html";
    });
    $("#btnEdit").click(function () {
        window.location.href=detailParames.page.edit;
    });
    $("#btnUpdate").click(function () {
        window .location.href=detailParames.page.update;
    });
});

function findMyMenu() {
    requestUtilParames.xhrGet(detailParames.action.findMyMenu).done(function (res) {
        //alert("查询成功");
    });
}

function findSessionUser() {
    requestUtilParames.xhrGet(detailParames.action.findSessionUser).done(function (res) {
        var user = res.data.user; //获得用户信息
        if(user!=null&&user.user_id!=0){
            $("#login_div").addClass("hidden");
            $("#session_user_div").removeClass("hidden");
            $("#session_user_name").text(user.user_name);
            $("#userId").val(user.user_id);
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
        requestUtilParames.xhrGet(detailParames.action.findEstateVoByPk+pk).done(function (res) {
            var estate=res.data.estate;
            //房屋图片
            if (res.data.fileLog != null) {
                $("#estate_photo").attr("src", requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
            $("#estateTable h2").text(estate.estate_name+estate.estate_buildingNum+estate.estate_unitNum+estate.estate_floorNum+estate.estate_roomNum);
            $("#estateName").text(estate.estate_name);
            $("#estateStructure").text(estate.estate_structure);
            $("#estateArea").text(estate.estate_area+"m²");
            $("#estatePrice").text("￥"+estate.estate_price);
            $("#btnBuy").val(estate.estate_id);
            if(estate.estate_state==1){
                $("#btnBuy").addClass("hidden");
            }
        });
    });
}

function gotoPurchasePage(pk) {
    window.location.href=detailParames.page.buy+pk;
}