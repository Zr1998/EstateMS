var showSaleInfoParames={
    action:{
        showInfo:"contract/selectContractVoByUserId/"
    }
}

$(function () {
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
        //  pk=31;
        showSaleData(pk);
    }
});

function showSaleData(pk) {
    layui.use('form', function(){
        requestUtilParames.xhrGet(showSaleInfoParames.action.showInfo+pk).done(function (res) {
            var user = res.data.user;
            var contract=res.data.contract;
            $("#user_name_contract").text(user.user_name);
            $("#purchase_estates_contract").text(res.data.estate_names);
            $("#developer_name_contract").text(res.data.developer_name);
            $("#purchase_total_contract").text(res.data.price);
            $("#purchase_time_contract").val(baseUtilParames.simpDataForYYYYMMDD(contract.create_time));

            //$("#btn_cancel").click();
        });
    });
}