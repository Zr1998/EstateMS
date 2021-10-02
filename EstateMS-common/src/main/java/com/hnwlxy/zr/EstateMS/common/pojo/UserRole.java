package com.hnwlxy.zr.EstateMS.common.pojo;

import io.swagger.annotations.ApiModelProperty;

public class UserRole {
    /*
     * @title:<h3> 用户角色关联 <h3>
     * @author: Zr
     * @date: 2018/11/12  18:09
     * @params
     * @return
     **/
        @ApiModelProperty(value = "用户角色自增id")
        private Integer user_role_id;

        @ApiModelProperty(value = "用户id")
        private Integer fk_user_id;

        @ApiModelProperty(value = "角色id")
        private Integer fk_role_id;

        public Integer getUser_role_id() {
            return user_role_id;
        }

        public void setUser_role_id(Integer user_role_id) {
            this.user_role_id = user_role_id;
        }

        public Integer getFk_user_id() {
            return fk_user_id;
        }

        public void setFk_user_id(Integer fk_user_id) {
            this.fk_user_id = fk_user_id;
        }

        public Integer getFk_role_id() {
            return fk_role_id;
        }

        public void setFk_role_id(Integer fk_role_id) {
            this.fk_role_id = fk_role_id;
        }

}
