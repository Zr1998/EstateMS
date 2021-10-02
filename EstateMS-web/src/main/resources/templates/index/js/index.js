var indexParams = {
    /**
     * @title:<h3> 如果有传入layid，则关闭对应的选项卡，否则关闭当前选项卡 <h3>
     * @author: Zr
     * @date: 2018-10-9 15:28
     * @params lid【选项卡lay-id】
     * @return
     **/
    tabInnerClose: function (lid) {
        layui.use('element', function () {
            var element = layui.element;
            var layid = $("#main_tab_group>ul.layui-tab-title>li.layui-this").attr("lay-id");
            if (lid != null) {
                layid = lid;
            }
            element.tabDelete("main_tab_group", layid);
        });
    }
    , action: {
         findMyMenu: "index/findMyMenu"
        ,findMyUser: "manage/findMyProFile"
        ,loginOut: "index/loginOut"
    }
}
$(function () {
    $("#navGroup,#navTopGroup").on("click", ".nav_item", function () {
        var than = this;
        //动态选项卡
        layui.use('element', function () {
            var element = layui.element;
            var lid = $(than).attr("data-id");
            if ($("#ifm_" + lid).length > 0) {//说明选项卡已存在，将进行选项卡切换
                element.tabChange("main_tab_group", "lid_" + lid);
            } else {
                var url = $(than).attr("data-url");
                element.tabAdd("main_tab_group", {
                    title: $(than).text()//'选项卡的标题'
                    , content: "<iframe src=\"" + url + "\" id='ifm_" + lid + "'></iframe>"//'选项卡的内容' //支持传入html
                    , id: "lid_" + lid
                });
                element.tabChange("main_tab_group", "lid_" + lid);
            }

        });
        //动态设置iframe框的高度
        $(".main_right_tab>.layui-tab-content>.layui-tab-item")
            .css("height", $(".main_right.layui-body").height() - 80 + "px");

    });
    //绑定关闭当前选项卡事件
    $("#tabClose_temp").click(function () {
        indexParams.tabInnerClose();
    });
    findMyMenu();
    findMyUser();
    $("#btnLoginOut").click(function () {
        loginOut();
    });
});

/**
 * @title:<h3>查询用户菜单  <h3>
 * @author: Zr
 * @date: 2018-10-26 15:36
 * @params
 * @return
 **/
function findMyMenu() {
    function convertUrl(url) {
        if (url.indexOf("../../swagger/index.html") > 0) {
            path = requestUtilParames.host + url;
            return path;
        }
        var path = url.replace("manage/", "").replace(".jsp", ".html");
        return path;
    }

    requestUtilParames.xhrGet(indexParams.action.findMyMenu).done(function (res) {
        var menuOne = res.data.menu_1;
        var menuTwo = res.data.menu_2;
        for (var i = 0; i < menuOne.length; i++) {
            var $li = $(' <li class="layui-nav-item layui-nav-itemed menuOne_'
                + menuOne[i].menu_id + '"><a data-id="nav_' + menuOne[i].code
                + '" data-url="' +menuOne[i].url + '"><i class="'
                + menuOne[i].icon + '"></i> ' + menuOne[i].name + '</a></li>');
            if (menuOne[i].url != null && menuOne[i].url != "") {
                $li.find("a").addClass("nav_item");
            }
            $("#navGroup").append($li);
        }
        for (var i = 0; i < menuTwo.length; i++) {
            var $parent = $("#navGroup .menuOne_" + menuTwo[i].fk_parent_id);
            if ($parent.find("dl").length == 0) {
                $parent.append(" <dl class=\"layui-nav-child\"></dl>");
            }
            var $dd = $("<dd><a class=\"nav_item\" data-url=\"" + convertUrl(menuTwo[i].url)
                + "\" data-id=\"nav_" + menuTwo[i].code + "\"><i class=\"" + menuTwo[i].icon
                + "\"></i> " + menuTwo[i].name + "</a></dd>");
            $parent.find("dl").append($dd);
        }

        layui.use('element', function () {
            var element = layui.element;
            element.render();
        });
    });
}

/**
 * @title:<h3>查询我的个人信息  <h3>
 * @author: Zr
 * @date: 2018-10-29 9:15
 * @params
 * @return
 **/
function findMyUser() {
    requestUtilParames.xhrGet(indexParams.action.findMyUser).done(function (res) {
        var user = res.data.user; //获得用户信息
        if(user!=null&&user.user_id!=0){
            $("#session_user_name").text(user.user_name);
            $("#Session_user_name").text(user.user_name);
            if (res.data.fileLog != null) {
                $("#session_user_url").attr("src", requestUtilParames.converFilePath(res.data.fileLog.save_path));
                $("#session_user_head_url").attr("src", requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
        }else{
            window.location.href="../login.html";
        }
    });
}

/**
 * @title:<h3> 注销 <h3>
 * @author: Zr
 * @date: 2018-11-23 9:43
 * @params
 * @return
 **/
function loginOut() {
    requestUtilParames.xhrGet(indexParams.action.loginOut).done(function (res) {
        window.location.href = "../login.html";
    });
}
