var estateEditParames={
    action:{
        addEstate:"estate/insertEstateVo",
        findEstateVoByPk:"estate/selectEstateVoByEstateId/",
        updateEstate:"estate/updateEstateVo",
        findEstateDeveloper:"estate/selectDeveloperName"
    }
}

$(function () {
    new uploadPreview({
        UpBtn: "tempMFile",
        DivShow: "imgGroup",
        ImgShow: "imgPreview"
    });
    findEstateDeveloper();

    //获得地址栏的地址
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
        //  pk=31;
        showEditData(pk);
    }

    $("#btnSubmitUpdate").click(function () {
        submitUpdateEstate();
    });

    $("#btnSubmit").click(function () {
        submitAddEstate();
    });
});

/*
* @title:<h3> 客户房产 <h3>
* @author: Zr
* @date: 2018/12/22  14:55
* @params
* @return
**/
function findEstateDeveloper() {
    requestUtilParames.staticJson=false;
    requestUtilParames.xhrGet(estateEditParames.action.findEstateDeveloper,"",{async:false}).done(function (res) {
        var list = res.data;
        $("#developerNameGroup").empty();
        for (var i = 0; i < list.length; i++) {
            $("#developerNameGroup").append("<input type=\"checkbox\" class='estateDeveloperItem' value='" + list[i].developer_id + "' name=\"\" " +
                "title=\"" + list[i].developer_name+"\"> ");
        }
        layui.use('form', function () {
            var form = layui.form;
            form.render(); //刷新渲染
        });
    });
}

/*
* @title:<h3> 显示房产信息 <h3>
* @author: Zr
* @date: 2018/12/22  14:58
* @params
* @return
**/
function showEditData(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(estateEditParames.action.findEstateVoByPk+pk).done(function (res) {
            var estate = res.data.estate;
            var developer=res.data.developer;
            //房屋图片
            if(res.data.fileLog!=null){
                $("#imgPreview").attr("src",requestUtilParames.converFilePath(res.data.fileLog.save_path));
            }
            $("#name-estate-submit").val(estate.estate_name);
            $("#buildingNum-estate-submit").val(estate.estate_buildingNum);
            $("#unitNum-estate-submit").val(estate.estate_unitNum);
            $("#floorNum-estate-submit").val(estate.estate_floorNum);
            $("#roomNum-estate-submit").val(estate.estate_roomNum);
            $("#structure-estate-submit").val(estate.estate_structure);
            $("#area-estate-submit").val(estate.estate_area);
            $("#price-estate-submit").val(estate.estate_price);
            var viewTypeSelect = 'dd[lay-value=' + estate.estate_viewId + ']';
            $('#viewTypeGroup').siblings("div.layui-form-select").find('dl').find(viewTypeSelect).click();
            var houseTypeSelect = 'dd[lay-value=' + estate.estate_houseType_id + ']';
            $('#houseTypeGroup').siblings("div.layui-form-select").find('dl').find(houseTypeSelect).click();
            $("#id-estate-submit").val(estate.estate_id);
            $("#developerNameGroup .estateDeveloperItem[value='" + estate.estate_developer_id + "']").next(".layui-form-checkbox").click();
            $("#form_submit :input").not(":button").prop("disabled",true);
        });

    });
}


function submitAddEstate() {
    if(checkData()){
        var data=$("#form_submit").serialize();
        console.log(data);
        //return false;
        requestUtilParames.xhrUpload("#form_submit",estateEditParames.action.addEstate).done(function (res) {
            alert(res.message);
            $("#form_submit")[0].reset();
        });
    }
}

function submitUpdateEstate() {
    if (checkData()) {
        var data=$("#form_submit").serialize();
        console.log(data);
        requestUtilParames.xhrUpload("#form_submit",estateEditParames.action.updateEstate).done(function (res) {
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
    var estate_name=$("#name-estate-submit").val();
    if(estate_name==""){
        str+="小区名称不能为空 \n";
    }

    if(str.length>0){
        alert(str);
        return false;
    }else{
       // var i = 0;
        $.each($("#form_submit .estateDeveloperItem:checked"), function () {
            $(this).attr("name", "estate.estate_developer_id");
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