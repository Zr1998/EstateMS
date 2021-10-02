package com.hnwlxy.zr.EstateMS.web.controller;


import com.hnwlxy.zr.EstateMS.web.baseController.BaseController;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manage")
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    /*
     * @title:<h3> 查询当前登录用户信息 <h3>
     * @author: Zr
     * @date: 2021/1/14  11:02
     * @params [baseModel]
     **/
    @ApiOperation(value = "查询当前登录用户信息",httpMethod = "GET")
    @RequestMapping("findMyProFile")
    public BaseModel findMyProFile(BaseModel baseModel) throws Exception{
        userService.selectUserVoByUserId(baseModel,getSessionUser().getUser_id());
        return baseModel;
    }

}
