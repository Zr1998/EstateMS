var customerEditParames={
    action:{
        findEstateName: "estate/selectEstateName",
        addCustomer:"customer/insertCustomerVo",
        findCustomerVoByPk:"customer/selectCustomerVoByCustomerId/",
        updateCustomer:"customer/updateCustomerVo"
    }
}

$(function () {
    findEstateName();

    //获得地址栏的地址
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
        //  pk=31;
        showEditData(pk);
    }

    $("#btnSubmitUpdate").click(function () {
        submitUpdateCustomer();
    });

    $("#btnSubmit").click(function () {
        submitAddCustomer();
    });
});

/*
* @title:<h3> 客户房产 <h3>
* @author: Zr
* @date: 2018/12/22  14:55
* @params
* @return
**/
function findEstateName() {
    requestUtilParames.staticJson=false;
    requestUtilParames.xhrGet(customerEditParames.action.findEstateName,"",{async:false}).done(function (res) {
        var list = res.data;
        $("#estateNameGroup").empty();
        for (var i = 0; i < list.length; i++) {
            $("#estateNameGroup").append("<input type=\"checkbox\" class='customerEstateItem' value='" + list[i].estate_id + "' name=\"\" " +
                "title=\"" + list[i].estate_name+list[i].estate_buildingNum+list[i].estate_unitNum+list[i].estate_floorNum+list[i].estate_roomNum+"\"> ");
        }
        layui.use('form', function () {
            var form = layui.form;
            form.render(); //刷新渲染
        });
    });
}

/*
* @title:<h3> 显示客户信息 <h3>
* @author: Zr
* @date: 2018/12/22  14:58
* @params
* @return
**/
function showEditData(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(customerEditParames.action.findCustomerVoByPk+pk).done(function (res) {
            var customer = res.data.customer;
            var listCustomerEstate = res.data.listCustomerEstate;

            $("#customer-cardId-submit").val(customer.customer_card_id);
            $("#name-customer-submit").val(customer.customer_name);
            $("#phone-customer-submit").val(customer.customer_phone);
            $("#address-customer-submit").val(baseUtilParames.strNullFilter(customer.customer_address));
            $("#sexGroup input[value='" + customer.customer_sex + "']").not(":checked").next(".layui-form-radio").click();
            $("#customer_id-customer-submit").val(customer.customer_id);
            if (listCustomerEstate != null) {
                for (var i = 0; i < listCustomerEstate.length; i++) {
                    $("#estateNameGroup .customerEstateItem[value='" + listCustomerEstate[i].estate_id + "']").next(".layui-form-checkbox").click();
                }
            }
            $("#form_submit :input").not(":button").prop("disabled",true);

            //$("#btn_cancel").click();
        });
    });
}


function submitAddCustomer() {
    if(checkData()){
        var data=$("#form_submit").serialize();
        console.log(data);
        //return false;
        requestUtilParames.xhr(customerEditParames.action.addCustomer,data).done(function (res) {
            alert(res.message);
            $("#form_submit")[0].reset();
        });
    }
}

function submitUpdateCustomer() {
    if (checkData()) {
        var data=$("#form_submit").serialize();
        console.log(data);
        requestUtilParames.xhr(customerEditParames.action.updateCustomer,data).done(function (res) {
            alert(res.message);
            $("#btnClose").click();
        });
    }
}

/*
* @title:<h3> 验证客户提交 <h3>
* @author: Zr
* @date: 2018/10/22  16:13
* @params
* @return
**/
function  checkData() {
    var str="";
    var card_id=$("#customer-cardId-submit").val();
    var customer_name=$("#name-customer-submit").val();
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
        var i = 0;
        $.each($("#form_submit .customerEstateItem:checked"), function () {
            $(this).attr("name", "listCustomerEstate[" + i + "].estate_id");
            i++;
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