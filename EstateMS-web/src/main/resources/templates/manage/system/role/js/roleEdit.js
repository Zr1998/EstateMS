var roleEditParames={
    action:{
        findRoleVoByPk:"role/selectRoleVoById/",
        findRolePermission:"index/selectEnumRoleMenu",
        addRole:"role/insertRoleVo",
        update: "role/updateRoleVo"
    }
}
$(function () {
    //角色权限全选
    $("#rolePermissionGroup").on("click","dt .layui-form-checkbox",function () {
        if($(this).hasClass("layui-form-checked")){//说明选中
            $(this).parent().siblings("dd").find(".layui-form-checkbox").not(".layui-form-checked").click();
            $(this).siblings("input[type='checkbox']",true);
        }else{//说明没选中
            $(this).parent().siblings("dd").find(".layui-form-checkbox.layui-form-checked").click();
            $(this).siblings("input[type='checkbox']",false);
        }

    });

    $("#rolePermissionGroup").on("click","dd .layui-form-checkbox",function () {
            var dLength=$(this).parent().parent().find("dd").length;//全部的权限功能
            var dd_Checked= $(this).parent().parent().find(".layui-form-checked").length;//选中的权限功能
        if(dLength==dd_Checked){
            $(this).parent().siblings("dt").find(".layui-form-checkbox").addClass("layui-form-checked");
            $(this).parent().siblings("dt").find("input").prop("checked",true);
        }else {
            $(this).parent().siblings("dt").find(".layui-form-checkbox").removeClass("layui-form-checked");
            $(this).parent().siblings("dt").find("input").prop("checked",false);
        }
    });

    findRolePermission();
    //获得地址栏的地址
    var pk=baseUtilParames.GetQueryString("pk");
    if(pk!=null){ //说明当前是编辑页面
      //  pk=31;
        showEditData(pk);
    }

    $("#btnSubmit").click(function () {
        submitAdd();
    });
    $("#btnSubmitUpdate").click(function () {
        submitUpdateRole();
    });
});



/*
* @title:<h3> 显示角色管理编辑页面数据 <h3>
* @author: Zr
* @date: 2018/10/17  13:28
* @params pk[主键id]
* @return
**/
 function showEditData(pk) {
     layui.use('form', function(){
         requestUtilParames.xhrGet(roleEditParames.action.findRoleVoByPk+pk).done(function (res) {

         var role = res.data.role;
         var listRolePers = res.data.listRolePermission;
         $("#name-role-submit").val(role.name);
         $("#memo-role-submit").val(role.memo);
         $("#version_role_submit").val(role.version);
         $("#role_id_role_submit").val(role.role_id);
         for(var i=0;i<listRolePers.length;i++){
             var $dt = $("#rolePermissionGroup dt .r_"+listRolePers[i].code).parent();
             for (var j=0;j<listRolePers[i].permission_value.length;j++){
                 if (listRolePers[i].permission_value[j]=='1'){
                     $dt.siblings("dd").eq(j).find("input[type='checkbox']").prop("checked",true);
                     $dt.siblings("dd").eq(j).find(".layui-form-checkbox").addClass("layui-form-checked");
                 }
             }
             if( $dt.siblings("dd").find("input[type='checkbox']").length==$dt.siblings("dd").find("input[type='checkbox']:checked").length){
                    $dt.find("input[type='checkbox']").prop("checked",true);
                 $dt.find(".layui-form-checkbox").addClass("layui-form-checked");
             }
         }
         $("#form_submit :input").not(":button").prop("disabled",true);
            });
     });
 }

 /*
 * @title:<h3> 查询权限值 <h3>
 * @author: Zr
 * @date: 2018/10/22  15:31
 * @params
 * @return
 **/
 function findRolePermission() {
     requestUtilParames.staticJson = false;
     requestUtilParames.xhrGet(roleEditParames.action.findRolePermission,"",{async:false}).done(function (res) {
         var list=res.data;
         $("#rolePermissionGroup").empty();
         for(var i=0;i<list.length;i++){
            var $dl=$("<dl></dl>");
             $dl.append('<dt class="permissionName">' +
                 '<input type="checkbox"  title="'+list[i].name+'" class="r_'+list[i].code+'" >'+
                 '<input type="hidden" class="in_code" name="" value="'+list[i].code+'">' +
                 '<input type="hidden" class="in_permission_value" name="" value=""></dt>');
             var values=list[i].roleValue.split(",");
             for(var j=0;j<values.length;j++){
                 $dl.append('<dd><input type="checkbox"  title="'+values[j]+'"></dd>')
             }
             $("#rolePermissionGroup").append($dl);
         }
         layui.use("form",function () {
             var form=layui.form;
             form.render();
         });
     });
 }

 /*
 * @title:<h3> 新增角色信息 <h3>
 * @author: Zr
 * @date: 2018/10/22  16:12
 * @params
 * @return
 **/
 function submitAdd() {
     if(checkData()){
         var data=$("#formSubmit").serialize();
         console.log(data);
         //return false;
         requestUtilParames.xhr(roleEditParames.action.addRole,data).done(function (res) {
             alert(res.message);
             $("#formSubmit")[0].reset();
         });
     }
 }

 /*
 * @title:<h3> 验证角色提交 <h3>
 * @author: Zr
 * @date: 2018/10/22  16:13
 * @params
 * @return
 **/
 function  checkData() {
     var str="";
     var name=$("#name-role-submit").val();
     if(name==""){
         str+="请输入角色名称 \n";
     }
     if($("#rolePermissionGroup input[type='checkbox']:checked").length==0){
         str+="请至少选择一个角色权限 \n";
     }
     if(str.length>0){
         alert(str);
         return false;
     }else{
         //遍历角色权限
         var num=0;
         $("#rolePermissionGroup dl").each(function () {
             if($(this).find("input:checked").length==0){
                 $(this).find(".in_code").attr("name","");
                 $(this).find(".in_permission_value").attr("name","");
                 return true;
             }
             $(this).find(".in_code").attr("name","listRolePermission["+num+"].code");

             var value="";
             $(this).find("dd>input").each(function () {
                 if($(this).prop("checked")){
                     value+="1";
                 }else {
                     value+="0";
                 }
             });
             //alert(value);
             $(this).find(".in_permission_value").attr("name","listRolePermission["+num+"].permission_value").val(value);
             num++;
         });
         return true;
     }
 }

 /*
 * @title:<h3> 提交修改角色信息 <h3>
 * @author: Zr
 * @date: 2018/10/23  11:21
 * @params
 * @return
 **/
function submitUpdateRole() {
    if (checkData()) {
        var formData = $("#form_submit").serialize();
        requestUtilParames.xhr(roleEditParames.action.update, formData).done(function (res) {
            alert(res.message);
            $("#btnClose").click();
        });
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