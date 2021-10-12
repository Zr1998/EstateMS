/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50552
Source Host           : 127.0.0.1:3306
Source Database       : estatems

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2021-10-12 20:25:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_s_contract
-- ----------------------------
DROP TABLE IF EXISTS `t_s_contract`;
CREATE TABLE `t_s_contract` (
  `contract_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同id',
  `developer_id` int(11) NOT NULL COMMENT '开发商id',
  `user_id` int(11) NOT NULL COMMENT '签约用户id',
  `estate_id` int(11) NOT NULL COMMENT '签售房产id',
  `create_time` datetime DEFAULT NULL COMMENT '签订合同时间',
  PRIMARY KEY (`contract_id`),
  KEY `t_s_contract_ibfk_1` (`estate_id`),
  KEY `t_s_contract_ibfk_2` (`developer_id`),
  KEY `t_s_contract_ibfk_3` (`user_id`),
  CONSTRAINT `t_s_contract_ibfk_1` FOREIGN KEY (`estate_id`) REFERENCES `t_s_estate` (`estate_id`) ON UPDATE CASCADE,
  CONSTRAINT `t_s_contract_ibfk_2` FOREIGN KEY (`developer_id`) REFERENCES `t_s_developer` (`developer_id`) ON UPDATE CASCADE,
  CONSTRAINT `t_s_contract_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `t_s_user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_s_customer`;
CREATE TABLE `t_s_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `customer_card_id` varchar(20) NOT NULL COMMENT '客户身份证号',
  `customer_name` varchar(64) NOT NULL COMMENT '客户姓名',
  `customer_phone` varchar(20) NOT NULL COMMENT '客户电话',
  `customer_address` varchar(255) NOT NULL COMMENT '客户籍贯',
  `customer_sex` int(11) NOT NULL COMMENT '客户性别 0为男 1为女',
  `create_user_id` int(11) NOT NULL COMMENT '创建人用户id',
  `create_user_name` varchar(55) NOT NULL COMMENT '创建人用户名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人用户id',
  `update_user_name` varchar(55) DEFAULT NULL COMMENT '更新人用户名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0存在1删除',
  PRIMARY KEY (`customer_id`),
  KEY `create_user_id` (`create_user_id`),
  CONSTRAINT `t_s_customer_ibfk_1` FOREIGN KEY (`create_user_id`) REFERENCES `t_s_user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_customer_estate
-- ----------------------------
DROP TABLE IF EXISTS `t_s_customer_estate`;
CREATE TABLE `t_s_customer_estate` (
  `customer_estate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户房产自增id',
  `estate_id` int(11) NOT NULL COMMENT '房产id',
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `developer_id` int(11) NOT NULL COMMENT '开发商id',
  PRIMARY KEY (`customer_estate_id`),
  KEY `t_s_contract_developerId_fk` (`developer_id`),
  CONSTRAINT `t_s_contract_developerId_fk` FOREIGN KEY (`developer_id`) REFERENCES `t_s_developer` (`developer_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_developer
-- ----------------------------
DROP TABLE IF EXISTS `t_s_developer`;
CREATE TABLE `t_s_developer` (
  `developer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '开发商编号',
  `developer_name` varchar(64) NOT NULL COMMENT '开发商名称',
  `developer_telphone` varchar(15) NOT NULL COMMENT '开发商电话号码',
  `developer_address` varchar(128) NOT NULL COMMENT '开发商地址',
  `developer_legalPerson` varchar(64) NOT NULL COMMENT '开发商法人',
  `developer_regNumber` varchar(20) NOT NULL COMMENT '营业执照编号',
  PRIMARY KEY (`developer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_estate
-- ----------------------------
DROP TABLE IF EXISTS `t_s_estate`;
CREATE TABLE `t_s_estate` (
  `estate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋编号',
  `estate_name` varchar(64) NOT NULL COMMENT '小区名称',
  `estate_buildingNum` varchar(64) NOT NULL COMMENT '楼号',
  `estate_unitNum` varchar(64) NOT NULL COMMENT '单元号',
  `estate_floorNum` varchar(64) NOT NULL COMMENT '楼层',
  `estate_roomNum` varchar(64) NOT NULL DEFAULT '房间号',
  `estate_houseType_id` int(5) NOT NULL COMMENT '房型编号',
  `estate_area` varchar(10) NOT NULL COMMENT '房屋面积',
  `estate_structure` varchar(60) NOT NULL COMMENT '房屋格局',
  `estate_price` varchar(20) NOT NULL COMMENT '房产价格',
  `estate_photo` varchar(64) DEFAULT NULL COMMENT '房屋图片',
  `estate_viewId` int(10) NOT NULL COMMENT '房屋朝向编号',
  `estate_developer_id` int(10) NOT NULL COMMENT '房屋开发商编号',
  `is_deleted` int(1) NOT NULL COMMENT '是否删除 0存在 1已删除',
  `estate_state` int(1) NOT NULL DEFAULT '0' COMMENT '房屋状态，0在售 1已售',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(55) DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `update_user_name` varchar(55) DEFAULT NULL COMMENT '更新人用户名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`estate_id`),
  KEY `fk_developer_id` (`estate_developer_id`),
  KEY `fk_houseType_id` (`estate_houseType_id`),
  KEY `fk_viewType_id` (`estate_viewId`),
  CONSTRAINT `fk_developer_id` FOREIGN KEY (`estate_developer_id`) REFERENCES `t_s_developer` (`developer_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_houseType_id` FOREIGN KEY (`estate_houseType_id`) REFERENCES `t_s_housetype` (`hType_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_viewType_id` FOREIGN KEY (`estate_viewId`) REFERENCES `t_s_viewtype` (`vType_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_filelog
-- ----------------------------
DROP TABLE IF EXISTS `t_s_filelog`;
CREATE TABLE `t_s_filelog` (
  `file_name` varchar(500) DEFAULT NULL COMMENT '原始文件名',
  `file_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `save_Path` varchar(2000) DEFAULT NULL COMMENT '文件保存路径',
  `file_rename` varchar(500) DEFAULT '0' COMMENT '重命名后的文件名',
  `file_Size` int(11) DEFAULT NULL COMMENT '文件大小',
  `file_Type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `file_Length` int(11) DEFAULT NULL COMMENT '文件长度（视频时长、文档页数）',
  `uploader` varchar(50) DEFAULT NULL COMMENT '创建人',
  `upload_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` int(11) DEFAULT '0' COMMENT '是否删除',
  `message` varchar(500) DEFAULT NULL COMMENT '异常消息',
  `state` int(11) DEFAULT NULL COMMENT '文件转化状态',
  PRIMARY KEY (`file_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_housetype
-- ----------------------------
DROP TABLE IF EXISTS `t_s_housetype`;
CREATE TABLE `t_s_housetype` (
  `hType_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房型编号',
  `hType_name` varchar(64) NOT NULL COMMENT '房型名称',
  `hType_parentId` int(11) NOT NULL COMMENT '房型父节点id',
  PRIMARY KEY (`hType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_s_menu`;
CREATE TABLE `t_s_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单自增id',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `is_leaf` int(1) DEFAULT NULL COMMENT '0不是叶子，1是叶子',
  `fk_parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `is_show` int(1) DEFAULT NULL COMMENT '0不显示1显示',
  `url` varchar(500) DEFAULT NULL COMMENT '地址',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `ids` varchar(500) DEFAULT NULL COMMENT '前端按钮id',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_operationlog
-- ----------------------------
DROP TABLE IF EXISTS `t_s_operationlog`;
CREATE TABLE `t_s_operationlog` (
  `operationlog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `user_account` varchar(255) DEFAULT NULL COMMENT '操作人账号',
  `date` datetime DEFAULT NULL COMMENT '操作时间',
  `content` varchar(2048) DEFAULT NULL COMMENT '操作内容',
  `type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `module` varchar(255) DEFAULT NULL COMMENT '操作模块',
  `request_ip` varchar(255) DEFAULT NULL COMMENT '请求ip',
  `param_before` varchar(2000) DEFAULT NULL COMMENT '请求前数据',
  `param_after` varchar(2000) DEFAULT NULL COMMENT '请求后数据',
  `request_params` varchar(2000) DEFAULT NULL COMMENT '请求参数，最好记录文件中',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  PRIMARY KEY (`operationlog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_role
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role`;
CREATE TABLE `t_s_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人用户名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '0删除1存在',
  `version` int(11) DEFAULT '0',
  `memo` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_permission`;
CREATE TABLE `t_s_role_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限自增id',
  `fk_role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `code` varchar(255) DEFAULT NULL COMMENT '二级菜单编号',
  `permission_value` varchar(255) DEFAULT NULL COMMENT '权限值',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人用户id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`permission_id`),
  KEY `fk_role_rolepermission_id` (`fk_role_id`),
  CONSTRAINT `fk_role_rolepermission_id` FOREIGN KEY (`fk_role_id`) REFERENCES `t_s_role` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user`;
CREATE TABLE `t_s_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_account` varchar(64) NOT NULL COMMENT '用户账号',
  `user_name` varchar(64) NOT NULL COMMENT '用户姓名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `user_head_url` varchar(64) DEFAULT NULL COMMENT '用户头像地址',
  `user_phone` varchar(64) NOT NULL COMMENT '用户号码',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户籍贯',
  `birthday` date DEFAULT NULL COMMENT '用户出生日期',
  `gender` int(11) NOT NULL COMMENT '用户性别 0为男 1为女',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否已删除 0为保留1为删除',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(55) DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人用户id',
  `update_user_name` varchar(55) DEFAULT NULL COMMENT '更新人用户名',
  `session_id` varchar(255) DEFAULT NULL COMMENT '会话sessionId',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user_role`;
CREATE TABLE `t_s_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色自增id',
  `fk_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `fk_role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`),
  KEY `fk_user_user_role_userid` (`fk_user_id`),
  KEY `fk_user_user_role_roleid` (`fk_role_id`),
  CONSTRAINT `fk_user_user_role_roleid` FOREIGN KEY (`fk_role_id`) REFERENCES `t_s_role` (`role_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_user_user_role_userid` FOREIGN KEY (`fk_user_id`) REFERENCES `t_s_user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_s_viewtype
-- ----------------------------
DROP TABLE IF EXISTS `t_s_viewtype`;
CREATE TABLE `t_s_viewtype` (
  `vType_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '朝向id',
  `vType_name` varchar(64) NOT NULL COMMENT '朝向名称',
  PRIMARY KEY (`vType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
