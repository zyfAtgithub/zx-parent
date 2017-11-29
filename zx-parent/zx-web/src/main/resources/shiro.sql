/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-29 18:52:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(100) DEFAULT NULL,
  `menuurl` varchar(100) DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL,
  `menuicon` varchar(100) DEFAULT NULL,
  `menuorder` int(3) DEFAULT NULL,
  `level` int(3) DEFAULT NULL,
  `isbtn` tinyint(1) DEFAULT '0',
  `treeIconSkin` varchar(100) DEFAULT NULL,
  `isshow` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('1', '首页', 'welcome', '0', 'fa fa-home faa-flash', '1', '1', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('2', '系统管理', '', '0', 'fa fa-cog faa-flash', '2', '1', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('3', '用户管理', 'sys/user/tolist', '2', 'fa fa-user faa-flash', '1', '2', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('4', '角色管理', 'sys/role/tolist', '2', 'fa fa-users faa-flash', '2', '2', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('5', '权限管理', 'sys/perm/tolist', '2', 'fa fa-key faa-flash', '3', '2', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('6', '菜单管理', 'sys/menu/tolist', '2', 'fa fa-tasks faa-flash', '4', '2', '0', '', '1');
INSERT INTO `sys_menus` VALUES ('7', '新增', 'btn-add', '3', '', '1', '3', '1', 'pIconadd', '1');
INSERT INTO `sys_menus` VALUES ('8', '修改', 'btn-edit', '3', '', '2', '3', '1', 'pIconedit', '0');
INSERT INTO `sys_menus` VALUES ('9', '删除', 'btn-batch-del', '3', '', '3', '3', '1', 'pIcondel', '0');
INSERT INTO `sys_menus` VALUES ('23', '查看', 'btn-view', '3', '', '4', '3', '1', 'pIconview', '0');
INSERT INTO `sys_menus` VALUES ('24', '查询', 'btn-query', '3', '', '5', '3', '1', 'pIconquery', '0');

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `lastlogin_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_loginname` (`loginname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', '管理员', 'f9983992ee2aeec3f25cfcbc7200ed64', 'fa66d9e193d2505e219df169e617b176', '17798502761', '17798502762@189.cn', '1', '2017-11-29 18:31:24');
INSERT INTO `sys_users` VALUES ('2', 'zhangyifeng', '张益峰', '4196e30b70f49c948a3d3787162f5fba', '96e6be2b15b33fc138f43c0b9a746d70', '15295778261', '15295778261@163.com', '0', '2017-11-29 18:38:31');

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
