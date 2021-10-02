var customerListParames= {
    page: {
        add: ["system/customer/addCustomer.html", "nav_customerAdd", "新增客户"],
        edit: ["system/customer/editCustomer.html?pk=", "nav_customerEdit", "编辑客户"]
    },
    action: {
        findPageData: "customer/selectPageCustomerVo",
        delCustomer: "customer/deleteCustomer/",
        // exp: "sysUser/expUserDTO",
        // imp: "sysUser/impUserDTO"
    }
}

$(function () {
    findPageData();

    $("#btnSearch").click(function () {
        requestUtilParames.searchForm("#formSearch");
        findPageData();
    });

    $("#btnAdd").click(function () {
       gotoAddPage();
    });

    $("#btnDelmore").click(function () {
        deleteCustomer();
        findPageData();
    });
});

/*
* @title:<h3> 分页查询客户信息 <h3>
* @author: Zr
* @date: 2018/12/21  16:04
* @params
* @return
**/
function findPageData(cpage){
    requestUtilParames.findPageData(customerListParames.action.findPageData,cpage,"#myTable","printPage",findPageData).done(function (res) {
        var list=res.data.list;
        var $tbody=$("#myTable tbody");
        //  $tbody.empty();
        for (var i=0;i<list.length;i++){
            var $tr=$("<tr></tr>");
            $tr.append('<td><input type="checkbox"   class="layui-form inpDel" value="'+list[i].customer_id+'"></td>');
            $tr.append("<td>"+list[i].customer.customer_name+"</td>");
            $tr.append("<td>"+list[i].customer.customer_card_id+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].customer.customer_phone)+"</td>");
            if(list[i].customer.customer_sex==1){
                $tr.append("<td>女</td>");
            }else if(list[i].customer.customer_sex==0){
                $tr.append("<td>男</td>");
            }
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].customer.customer_address)+"</td>");
            $tr.append("<td>"+baseUtilParames.strNullFilter(list[i].estate_names)+"</td>");
            var $btns=$("<td></td>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-normal\" onclick='gotoEditPage("+list[i].customer_id+")'>编辑</a>");
            $btns.append("<a class=\"layui-btn layui-btn-xs layui-btn-danger permission_delete hidden_per\" onclick='deleteCustomer("+list[i].customer_id+")'>删除</a>");
            $tr.append($btns);
            $tbody.append($tr);
        }
        requestUtilParames.removePermissionBtns(res.permission_btns);
        $(".permission_update.hidden_per").each(function () {
            $(this).prop("disabled",true);
        });
        layui.use("form",function () {
            var form=layui.form;
            form.render();
        });
    });
}


/*
* @title:<h3> 删除客户信息 <h3>
* @author: Zr
* @date: 2018/10/27  14:47
* @params
* @return
**/
function deleteCustomer(pk) {
    requestUtilParames.deleteData(customerListParames.action.delCustomer,pk,"#myTable",findPageData)
}


/*
* @title:<h3> 跳转到编辑页面 <h3>
* @author: Zr
* @date: 2018/10/15  16:31
* @params
* @return
**/
function gotoEditPage(pk) {
    globalParams.tabInnerChange(customerListParames.page.edit,pk);
}

function gotoAddPage() {
    globalParams.tabInnerChange(customerListParames.page.add);
}