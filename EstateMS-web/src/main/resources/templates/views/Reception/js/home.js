var homeParames={
    page:{
        edit: ["editPassword.html"],
        detail: ["detailsPage.html?pk="],
        update:["updateMyInfo.html"]
    },
    action:{
        findMyMenu: "index/findMyMenu",
        findSessionUser:"sysUser/findMyUserVo",
        loginOut: "index/loginOut",
        findPageData: "estate/selectPageEstates",
    }
}

$(function () {
    findMyMenu();
    findSessionUser();
    findEstates();

    $("#btnLoginOut").click(function () {
        requestUtilParames.xhrGet(homeParames.action.loginOut).done(function (res) {
            window.location.href = "../../login.html";
        });
    });
    $("#btnSearch").click(function () {//搜索
        requestUtilParames.searchForm("#formSearch");
        findEstates();
    });
    $("#btnEdit").click(function () {
       window.location.href=homeParames.page.edit;
    });

    $("#btnUpdate").click(function () {
       window .location.href=homeParames.page.update;
    });
});

function findMyMenu() {
    requestUtilParames.xhrGet(homeParames.action.findMyMenu).done(function (res) {
       //alert("查询成功");
    });
}

function findSessionUser() {
        requestUtilParames.xhrGet(homeParames.action.findSessionUser).done(function (res) {
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
                res.alert("您尚未登录，请登录！");
                window.location.href="../../login.html";
            }
        });
}

function findEstates(cpage) {
    requestUtilParames.findIndexData(homeParames.action.findPageData,cpage,"#container","pagination",findEstates).done(function (res) {
        var list = res.data.list;
        var $row = $(".container .row");
        $row.empty();
        for (var i = 0; i < list.length; i++) {

            var $div = $("<div class=\"col-lg-4 col-sm-6 layout-item-wrap\"></div>");
            var $article = $("<article class=\"property layout-item clearfix\"></article>");
            var $figure = $("<figure class=\"feature-image\"></figure>");
            var $clearfix = $("<div class=\"property-contents clearfix\"></div>");
            var $meta=$("<div class=\"property-meta clearfix\"></div>")
            var $header = $(" <header class=\"property-header clearfix\"></header>");
            var $left = $(" <div class=\"pull-left\"></div>");
            var $btn = $(" <button class=\"btn btn-default btn-price pull-right \" type='button' onclick=gotoDetailPage("+list[i].estate_id+")></button>");
            $figure.append("<a class=\"clearfix \"  onclick=gotoDetailPage(" + list[i].estate_id + ")>" +
                            "<img src='' alt=\"Property Image\" /></a>" +
                            "<span class=\"btn btn-warning btn-sale\"></span>");
            $left.append("<h6 class=\"entry-title\"><a  onclick=gotoDetailPage("+list[i].estate_id+")>"+list[i].estate.estate_name+"</a></h6>" +
                        "<span class=\"property-location\"><i class=\"fa fa-map-marker\"></i> 湖南常德</span>");
            $btn.append("<strong>"+list[i].estate.estate_price+"</strong>");
            $meta.append("<span><i class=\"fa fa-arrows-alt\"></i>"+list[i].estate.estate_area+"</span>");
            $meta.append("<span><i class=\"fa fa-home\"></i>"+list[i].estate.estate_structure+"</span>");
            $meta.append("<span id='view'><i class=\"fa fa-arrows\"></i>"+list[i].vType_name+"</span>");
            $meta.append("<span id='housetype'><i class=\"fa fa-tags\"></i>"+list[i].hType_name+"</span>");
            //房屋图片
            if( list[i].fileLog!=null&&list[i].fileLog.file_log_id!=0){
                $figure.find("img").attr("src",requestUtilParames.converFilePath(list[i].fileLog.save_path));
            }
            if(list[i].estate.estate_state==0){
                $figure.find("span").text("在售");
            }else {
                $figure.find("span").text("已售");
            }
            $header.append($left);
            $header.append($btn);
            $clearfix.append($header);
            $clearfix.append($meta);
            $article.append($figure);
            $article.append($clearfix);
            $div.append($article);
            $row.append($div);
        }

        layui.use("form",function () {
            var form=layui.form;
            form.render();
        });
    });
            // if(list[i].estate.estate_viewId==1){
            //     $(".property-meta #view").text("朝街房");
            // }else if(list[i].estate.estate_viewId==2){
            //     $(".property-meta #view").text("背街房");
            // } else if(list[i].estate.estate_viewId==3){
            //     $(".property-meta #view").text("城景房");
            // } else if(list[i].estate.estate_viewId==4){
            //     $(".property-meta #view").text("海景房");
            // } else if(list[i].estate.estate_viewId==5){
            //     $(".property-meta #view").text("园景房");
            // } else if(list[i].estate.estate_viewId==6){
            //     $(".property-meta #view").text("湖景房");
            // }
            //
            // if(list[i].estate.estate_houseType_id==1){
            //     $(".property-meta #housetype").text("朝街房");
            // }else if(list[i].estate.estate_houseType_id==2){
            //     $(".property-meta #housetype").text("背街房");
            // } else if(list[i].estate.estate_houseType_id==3){
            //     $(".property-meta #housetype").text("城景房");
            // } else if(list[i].estate.estate_houseType_id==4){
            //     $(".property-meta #housetype").text("海景房");
            // } else if(list[i].estate.estate_houseType_id==5){
            //     $(".property-meta #housetype").text("园景房");
            // } else if(list[i].estate.estate_houseType_id==6){
            //     $(".property-meta #housetype").text("湖景房");
            // }


}

function gotoDetailPage(pk) {
    window.location.href=homeParames.page.detail+pk;
}

