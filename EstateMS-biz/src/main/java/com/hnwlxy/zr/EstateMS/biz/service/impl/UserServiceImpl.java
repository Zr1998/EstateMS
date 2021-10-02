package com.hnwlxy.zr.EstateMS.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hnwlxy.zr.EstateMS.common.dto.UserDTO;
import com.hnwlxy.zr.EstateMS.common.em.ResultMesgEnum;
import com.hnwlxy.zr.EstateMS.common.encrypt.MD5DES;
import com.hnwlxy.zr.EstateMS.common.exception.BusinessException;
import com.hnwlxy.zr.EstateMS.common.model.BaseModel;
import com.hnwlxy.zr.EstateMS.common.pojo.Role;
import com.hnwlxy.zr.EstateMS.common.pojo.User;
import com.hnwlxy.zr.EstateMS.common.pojo.UserRole;
import com.hnwlxy.zr.EstateMS.common.util.BaseUtil;
import com.hnwlxy.zr.EstateMS.common.util.ExcelUtil;
import com.hnwlxy.zr.EstateMS.common.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import com.hnwlxy.zr.EstateMS.biz.mapper.FileLogMapper;
import com.hnwlxy.zr.EstateMS.biz.mapper.RoleMapper;
import com.hnwlxy.zr.EstateMS.biz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hnwlxy.zr.EstateMS.biz.service.FileLogService;
import com.hnwlxy.zr.EstateMS.biz.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    public static final String DEFAULT_PASSWORD="111111"; //默认密码

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileLogMapper fileLogMapper;
    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private RoleMapper roleMapper;
    /*
     * @title:<h3> 根据id查询用户详情 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [baseModel, userId]
     * @return void
     **/
    public void selectUserVoByUserId(BaseModel baseModel, int userId) throws Exception {
        SysUserVo userVo=userMapper.selectUserVoByUserId(userId);
        if (userVo==null) {
            throw new BusinessException("该用户信息已被删除");
        }
        baseModel.setData(userVo);
    }

    public void registUserVo(BaseModel baseModel, SysUserVo userVo) throws Exception {
        //验证用户名
        SysUserVo oldUser=userMapper.selectUserByAccount(userVo.getUser().getUser_account());
        if(oldUser!=null){
            throw new BusinessException("用户名已存在，请重新输入");
        }
        //判断是否进行文件上传
//        fileLogService.uploadFiles(baseModel,userVo.getUser().getCreate_user_name());
//        userVo.getUser().setHead_img_url(baseModel.getFilesArray());
        //新增用户
        userVo.getUser().setPassword(MD5DES.encrypt(userVo.getUser().getPassword()));

        userMapper.insertUser(userVo.getUser());
        //新增用户角色关系
        if(userVo.getListUserRole()!=null&&userVo.getListUserRole().size()>0){
            //存在用户角色关系
            for(int i=0;i<userVo.getListUserRole().size();i++){
                UserRole userRole=userVo.getListUserRole().get(i);
                userRole.setFk_role_id(1);
                userRole.setFk_user_id(userVo.getUser().getUser_id());
                userMapper.insertUserRoleId(userRole);
            }
        }
        baseModel.setData(userVo);
        baseModel.setMessage("用户注册成功");
    }

    /*
     * @title:<h3> 用户列表 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [baseModel]
     * @return void
     **/
    public void selectPageUserVo(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(), baseModel.getQueryParams().getPage_size());
        List<SysUserVo> userVo = userMapper.selectUserVo(baseModel.getQueryParams());
        PageInfo userliset = new PageInfo(userVo);
        baseModel.setData(userliset);
    }

    /*
     * @title:<h3> 新增用户 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [userVo, baseModel]
     * @return void
     **/
    public void inserUserVo(SysUserVo userVo, BaseModel baseModel)throws Exception {
        //验证用户名
        SysUserVo oldUser=userMapper.selectUserByAccount(userVo.getUser().getUser_account());
        if(oldUser!=null){
            throw new BusinessException("用户名已存在，请勿重复添加");
        }
        //判断是否进行文件上传
        fileLogService.uploadFiles(baseModel,userVo.getUser().getCreate_user_name());
        userVo.getUser().setUser_head_url(baseModel.getFilesArray());
        //新增用户
        userMapper.insertUser(userVo.getUser());
        //新增用户角色关系
        if(userVo.getListUserRole()!=null&&userVo.getListUserRole().size()>0){
            //存在用户角色关系
            for(int i=0;i<userVo.getListUserRole().size();i++){
                UserRole userRole=userVo.getListUserRole().get(i);
                userRole.setFk_user_id(userVo.getUser().getUser_id());
                userMapper.insertUserRole(userRole);
            }
        }
    }

    /*
     * @title:<h3> 理论删除用户 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [userIds, baseModel]
     * @return void
     **/
    public void deleteUserInPk(String userIds, BaseModel baseModel) throws Exception {
        //删除用户角色信息表记录
        userMapper.deleteRoleNames(userIds);
        //删除用户信息
        int count=userMapper.removeUserInPk(userIds);
        baseModel.setMessage("删除"+count+"条用户信息成功");
    }

    /*
     * @title:<h3> 修改用户详情信息 <h3>
     * @author: Zr
     * @date:2021/1/16  15:33
     * @params [userVo, baseModel]
     * @return void
     **/
    public void udateUserVo(SysUserVo userVo, BaseModel baseModel) throws Exception {
        SysUserVo oldUser = userMapper.selectUserByAccount(userVo.getUser().getUser_account());
        if(oldUser!=null&&oldUser.getUser().getUser_id().intValue()!=userVo.getUser().getUser_id().intValue()){ //说明用户存在用户信息并且id不相等
            throw new BusinessException("用户账号 '" + userVo.getUser().getUser_account() + "' 已存在，请勿重复添加");
        }
        //判断是否进行文件上传
        fileLogService.uploadFiles(baseModel,userVo.getUser().getUpdate_user_name());
        if(baseModel.getFilesArray()!=null){//如果存在头像更新，则更新头像id
            userVo.getUser().setUser_head_url(baseModel.getFilesArray());
            //删除文件上传记录表中的记录
            fileLogMapper.removeFileLogByUserIds(userVo.getUser().getUser_id() + "");
        }

        int count = userMapper.updateUserVo(userVo.getUser());
        if(count == 0){
            throw new BusinessException(ResultMesgEnum.UPDATE_VES);
        }
        userMapper.deleteRoleNames(userVo.getUser().getUser_id() + "");
        //新增用户角色关系
        if(userVo.getListUserRole() != null && userVo.getListUserRole().size()>0){//存在用户角色关系表记录
            for(int i=0;i<userVo.getListUserRole().size();i++){
                UserRole userRole = userVo.getListUserRole().get(i);
                userRole.setFk_user_id(userVo.getUser().getUser_id());
                userMapper.insertUserRole(userRole);
            }
        }
        baseModel.setMessage("修改用户信息成功");
    }

    /*
     * @title:<h3> 修改用户密码 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [user, baseModel]
     * @return void
     **/
    public void updatePassword(SysUserVo userVo, BaseModel baseModel) throws Exception {
        if(userVo.getOld_password()!=null&&!"".equals(userVo.getOld_password())){ //如果传入了旧密码，则进行密码更新验证
            SysUserVo oldUser=userMapper.selectUserVoByUserId(userVo.getUser().getUser_id());
            if(!MD5DES.encrypt(userVo.getOld_password()).equals(oldUser.getUser().getPassword())){
                throw new BusinessException("原密码不正确");
            }else {
                //密码加密
                userVo.getUser().setPassword(MD5DES.encrypt(userVo.getUser().getPassword()));
            }
        }else { //如果未传入原密码，则不更新密码
            userVo.getUser().setPassword(null);
        }
        userMapper.updatePassword(userVo.getUser());
    }

    /*
     * @title:<h3> 重置用户密码 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [user, baseModel]
     * @return void
     **/
    public void resetPassword(User user,BaseModel baseModel) throws Exception {
        int count = userMapper.resetPassword(user);
        if (count == 0) {
            throw new BusinessException("重置密码失败");
        }
    }

    public void updateMyUserVo(SysUserVo userVo, BaseModel baseModel) throws Exception {
        userVo.getUser().setUser_account(null); //不更新账号
       //如果未传入原密码，则不更新密码
        userVo.getUser().setPassword(null);
        //处理头像上传
        //判断是否进行文件上传（更新上传文件id）
        fileLogService.uploadFiles(baseModel,userVo.getUser().getUpdate_user_name());
        if(baseModel.getFilesArray()!=null){//如果存在头像更新，则更新头像id
            userVo.getUser().setUser_head_url(baseModel.getFilesArray());
            //删除文件上传记录表中的记录
            fileLogMapper.removeFileLogByUserIds(userVo.getUser().getUser_id() + "");
        }
        //修改用户信息
        int count=userMapper.updateUserVo(userVo.getUser());
        if(count==0){
            throw new BusinessException(ResultMesgEnum.UPDATE_VES);
        }

    }


    /*
     * @title:<h3> 导出用户信息 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [baseModel]
     * @return java.lang.String
     **/
    public String exportUserDTO(BaseModel baseModel) throws Exception{
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page(),baseModel.getQueryParams().getPage_size());
        List<SysUserVo> listVo=userMapper.selectUserVo(baseModel.getQueryParams());
        List<UserDTO> listDTO=new ArrayList<UserDTO>();
        String sex[]={"男","女"};
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<listVo.size();i++){
            UserDTO dto=new UserDTO();
            dto.setCount(i+1);
            dto.setAccount(listVo.get(i).getUser().getUser_account());
            dto.setName(listVo.get(i).getUser().getUser_name());
            dto.setSex_str(sex[listVo.get(i).getUser().getGender()]);
            dto.setRole_names(listVo.get(i).getRole_names());
            dto.setPhone(listVo.get(i).getUser().getUser_phone());
            dto.setEmail(listVo.get(i).getUser().getEmail());
            dto.setAddress(listVo.get(i).getUser().getUser_address());
            if(listVo.get(i).getUser().getBirthday()!=null){
                dto.setBirthday_str(sdf.format(listVo.get(i).getUser().getBirthday()));
            }
            listDTO.add(dto);
        }
        return ExcelUtil.expTemplateExcel(listDTO,"UserExp.xls");
    }

    /*
     * @title:<h3> 导入用户信息 <h3>
     * @author: Zr
     * @date: 2021/1/16  15:33
     * @params [sessionUser, baseModel]
     * @return void
     **/
    @Transactional(rollbackFor = BusinessException.class)
    public void impUserDTO(User sessionUser, BaseModel baseModel)throws Exception{
        int count=0;
        StringBuilder userName=new StringBuilder("");
        //解析excel,返回ListMap
        List<Map<String,Object>> listMap=ExcelUtil.getExcelData(baseModel.getTempMFile());
        //遍历ListMap.将map转po对象
        Map<String,Integer> mapSex=new HashMap<String, Integer>();
        mapSex.put("男",0);
        mapSex.put("女",1);

        Map<String,Integer> mapRolo=new HashMap<String, Integer>();//角色名称id
        List<Role> listRole=roleMapper.selectRoleName();//查询全部角色名称
        for(int i=0;i<listRole.size();i++){ //遍历角色名称，将其存入map
            mapRolo.put(listRole.get(i).getName(),listRole.get(i).getRole_id());
        }
        for(int i=0;i<listMap.size();i++){
            User user=new User();
            try{
                //user.setAccount(listMap.get(i).get("account").toString());
                // user.setName(listMap.get(i).get("name").toString());
                // user.setPhone(listMap.get(i).get("phone").toString());
                BaseUtil.mapToEntity(listMap.get(i),user);
                //验证用户数据
                if(user.getUser_account()==null || "".equals(user.getUser_account())){
                    throw  new BusinessException("第"+(i+1)+"行，账号不能为空");
                }else if(user.getUser_name()==null || "".equals(user.getUser_name())){
                    throw  new BusinessException("第"+(i+1)+"行，用户姓名不能为空");
                }
                //验证用户名
                SysUserVo oldUser=userMapper.selectUserByAccount(user.getUser_account());
                if(oldUser!=null){
                    throw new BusinessException("第"+(i+1)+"行，账号“"+user.getUser_account()+"”已存在，请勿重复添加");
                }
                user.setPassword(MD5DES.encrypt(DEFAULT_PASSWORD));
                user.setCreate_user_name(sessionUser.getUser_name());
                user.setCreate_user_id(sessionUser.getUser_id());

                //验证导入参数是否合法
                String sex_str=(String)listMap.get(i).get("sex_str");//性别
                String role_names=(String)listMap.get(i).get("role_names");//角色名称
                if(sex_str!=null&&!"".equals(sex_str)){ //性别验证
                    if(mapSex.get(sex_str)==null){
                        throw new BusinessException("第"+(i+1)+"行,性别不能为“"+sex_str+"”");
                    }
                    user.setGender(mapSex.get(sex_str));
                }
                List<UserRole> listUserRole=new ArrayList<UserRole>();
                if(role_names!=null&&!"".equals(role_names)){ //验证角色名称
                    String arrRoleName[]=role_names.split(",");
                    for (int j=0;j<arrRoleName.length;j++){
                        if(mapRolo.get(arrRoleName[j])==null){ //如果角色名称不存在map中，即角色名称不存在
                            throw new BusinessException("第"+(i+1)+"行,角色名称“"+arrRoleName[j]+"”不存在");
                        }
                        UserRole userRole=new UserRole();
                        userRole.setFk_role_id(mapRolo.get(arrRoleName[j]));
                        listUserRole.add(userRole);
                    }
                }
                //验证po对象数据，进行插入
                count+= userMapper.insertUser(user);
                //插入用户角色关系表
                for(int j=0;j<listUserRole.size();j++){
                    listUserRole.get(j).setFk_user_id(user.getUser_id());
                    userMapper.insertUserRole(listUserRole.get(j));
                }
            }catch (BusinessException be){
                throw  be;
            } catch (Exception e){
                throw new BusinessException("第"+(i+1)+"行数据转换异常");
            }
        }
        baseModel.setMessage("导入'"+count+"'条用户信息成功");
        if(userName.toString().length()>0){
            baseModel.setAop_mesg(userName.toString().substring(1));
        }
    }
}