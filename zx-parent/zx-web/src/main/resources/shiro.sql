/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-12-14 10:10:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menuname` varchar(100) NOT NULL COMMENT '资源名',
  `menuurl` varchar(100) NOT NULL COMMENT '资源路径',
  `parentid` bigint(20) NOT NULL COMMENT '父级id',
  `menuicon` varchar(100) DEFAULT NULL COMMENT '资源显示图标',
  `menuorder` int(3) NOT NULL COMMENT '排序',
  `level` int(3) NOT NULL COMMENT '层级',
  `permexp` varchar(255) DEFAULT NULL COMMENT '权限表达式',
  `isbtn` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为按钮',
  `treeIconSkin` varchar(100) DEFAULT NULL COMMENT '树形图标',
  `isshow` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限字符串',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('1', '首页', 'welcome', '0', 'fa fa-home faa-flash', '1', '1', null, '0', '', '1', null, '2017-12-01 15:15:49', null);
INSERT INTO `sys_menus` VALUES ('2', '系统管理', '', '0', 'fa fa-cog faa-flash', '2', '1', null, '0', '', '1', null, null, null);
INSERT INTO `sys_menus` VALUES ('3', '用户管理', 'sys/user/tolist', '2', 'fa fa-user faa-flash', '1', '2', null, '0', '', '1', null, null, null);
INSERT INTO `sys_menus` VALUES ('4', '角色管理', 'sys/role/tolist', '2', 'fa fa-users faa-flash', '2', '2', null, '0', '', '1', null, null, null);
INSERT INTO `sys_menus` VALUES ('6', '菜单管理', 'sys/menu/tolist', '2', 'fa fa-tasks faa-flash', '4', '2', null, '0', '', '1', null, null, null);
INSERT INTO `sys_menus` VALUES ('7', '新增', 'btn-add', '3', '', '1', '3', 'sys:user:add', '1', 'pIconadd', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('8', '修改', 'btn-edit', '3', '', '2', '3', 'sys:user:update', '1', 'pIconedit', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('9', '删除', 'btn-del', '3', '', '3', '3', 'sys:user:del', '1', 'pIcondel', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('23', '查看', 'btn-view', '3', '', '4', '3', 'sys:user:view', '1', 'pIconview', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('24', '查询', 'btn-query', '3', '', '5', '3', 'sys:user:query', '1', 'pIconquery', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('25', '批量删除', 'btn-batch-del', '3', '', '6', '3', 'sys:user:batchdel', '1', 'pIcontrash', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('26', '重置', 'btn-reset', '3', '', '7', '3', 'sys:user:reset', '1', 'pIconreset', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('27', '刷新', 'btn-refresh', '3', '', '8', '3', 'sys:user:refresh', '1', 'pIconrefresh', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('28', '新增', 'btn-add', '6', '', '1', '3', 'sys:menu:add', '1', 'pIconadd', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('29', '修改', 'btn-edit', '6', '', '2', '3', 'sys:menu:update', '1', 'pIconedit', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('30', '删除', 'btn-del', '6', '', '3', '3', 'sys:menu:del', '1', 'pIcondel', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('31', '查看', 'btn-view', '6', '', '4', '3', 'sys:menu:view', '1', 'pIconview', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('32', '查询', 'btn-query', '6', '', '5', '3', 'sys:menu:query', '1', 'pIconquery', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('33', '批量删除', 'btn-batch-del', '6', '', '6', '3', 'sys:menu:batchdel', '1', 'pIcontrash', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('34', '重置', 'btn-reset', '6', '', '7', '3', 'sys:menu:reset', '1', 'pIconreset', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('35', '刷新', 'btn-refresh', '6', '', '8', '3', 'sys:menu:refresh', '1', 'pIconrefresh', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('36', '新增', 'btn-add', '4', '', '1', '3', null, '1', 'pIconadd', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('37', '新增', 'btn-add', '5', '', '1', '3', null, '1', 'pIconadd', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('38', '修改', 'btn-edit', '4', '', '2', '3', null, '1', 'pIconedit', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('39', '删除', 'btn-del', '4', '', '3', '3', null, '1', 'pIcondel', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('40', '查看', 'btn-view', '4', '', '4', '3', null, '1', 'pIconview', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('41', '查询', 'btn-query', '4', '', '5', '3', null, '1', 'pIconquery', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('42', '批量删除', 'btn-batch-del', '4', '', '6', '3', null, '1', 'pIcontrash', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('43', '重置', 'btn-reset', '4', '', '7', '3', null, '1', 'pIconreset', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('44', '刷新', 'btn-refresh', '4', '', '8', '3', null, '1', 'pIconrefresh', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('45', '修改', 'btn-edit', '5', '', '2', '3', null, '1', 'pIconedit', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('46', '删除', 'btn-del', '5', '', '3', '3', null, '1', 'pIcondel', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('47', '查看', 'btn-view', '5', '', '4', '3', null, '1', 'pIconview', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('48', '查询', 'btn-query', '5', '', '5', '3', null, '1', 'pIconquery', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('49', '批量删除', 'btn-batch-del', '5', '', '6', '3', null, '1', 'pIcontrash', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('50', '重置', 'btn-reset', '5', '', '7', '3', null, '1', 'pIconreset', '0', null, null, null);
INSERT INTO `sys_menus` VALUES ('51', '刷新', 'btn-refresh', '5', '', '8', '3', null, '1', 'pIconrefresh', '0', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('5', '系统管理员', '只拥有系统权限-不具有业务权限', '0');
INSERT INTO `sys_roles` VALUES ('6', '超级管理员', '最高权限-具有所有权限', '0');
INSERT INTO `sys_roles` VALUES ('7', '运维人员', '拥有业务操作权限', '0');

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
INSERT INTO `sys_roles_permissions` VALUES ('5', '1');
INSERT INTO `sys_roles_permissions` VALUES ('5', '2');
INSERT INTO `sys_roles_permissions` VALUES ('5', '3');
INSERT INTO `sys_roles_permissions` VALUES ('5', '4');
INSERT INTO `sys_roles_permissions` VALUES ('5', '6');
INSERT INTO `sys_roles_permissions` VALUES ('5', '7');
INSERT INTO `sys_roles_permissions` VALUES ('5', '8');
INSERT INTO `sys_roles_permissions` VALUES ('5', '9');
INSERT INTO `sys_roles_permissions` VALUES ('5', '23');
INSERT INTO `sys_roles_permissions` VALUES ('5', '24');
INSERT INTO `sys_roles_permissions` VALUES ('5', '25');
INSERT INTO `sys_roles_permissions` VALUES ('5', '26');
INSERT INTO `sys_roles_permissions` VALUES ('5', '27');
INSERT INTO `sys_roles_permissions` VALUES ('5', '36');
INSERT INTO `sys_roles_permissions` VALUES ('5', '38');
INSERT INTO `sys_roles_permissions` VALUES ('5', '39');
INSERT INTO `sys_roles_permissions` VALUES ('5', '40');
INSERT INTO `sys_roles_permissions` VALUES ('5', '41');
INSERT INTO `sys_roles_permissions` VALUES ('5', '42');
INSERT INTO `sys_roles_permissions` VALUES ('5', '43');
INSERT INTO `sys_roles_permissions` VALUES ('5', '44');
INSERT INTO `sys_roles_permissions` VALUES ('6', '2');
INSERT INTO `sys_roles_permissions` VALUES ('6', '3');
INSERT INTO `sys_roles_permissions` VALUES ('6', '4');
INSERT INTO `sys_roles_permissions` VALUES ('6', '5');
INSERT INTO `sys_roles_permissions` VALUES ('6', '6');
INSERT INTO `sys_roles_permissions` VALUES ('6', '7');
INSERT INTO `sys_roles_permissions` VALUES ('6', '8');
INSERT INTO `sys_roles_permissions` VALUES ('6', '9');
INSERT INTO `sys_roles_permissions` VALUES ('6', '23');
INSERT INTO `sys_roles_permissions` VALUES ('6', '24');
INSERT INTO `sys_roles_permissions` VALUES ('6', '25');
INSERT INTO `sys_roles_permissions` VALUES ('6', '26');
INSERT INTO `sys_roles_permissions` VALUES ('6', '27');
INSERT INTO `sys_roles_permissions` VALUES ('6', '28');
INSERT INTO `sys_roles_permissions` VALUES ('6', '29');
INSERT INTO `sys_roles_permissions` VALUES ('6', '30');
INSERT INTO `sys_roles_permissions` VALUES ('6', '31');
INSERT INTO `sys_roles_permissions` VALUES ('6', '32');
INSERT INTO `sys_roles_permissions` VALUES ('6', '33');
INSERT INTO `sys_roles_permissions` VALUES ('6', '34');
INSERT INTO `sys_roles_permissions` VALUES ('6', '35');
INSERT INTO `sys_roles_permissions` VALUES ('6', '36');
INSERT INTO `sys_roles_permissions` VALUES ('6', '37');
INSERT INTO `sys_roles_permissions` VALUES ('6', '38');
INSERT INTO `sys_roles_permissions` VALUES ('6', '39');
INSERT INTO `sys_roles_permissions` VALUES ('6', '40');
INSERT INTO `sys_roles_permissions` VALUES ('6', '41');
INSERT INTO `sys_roles_permissions` VALUES ('6', '42');
INSERT INTO `sys_roles_permissions` VALUES ('6', '43');
INSERT INTO `sys_roles_permissions` VALUES ('6', '44');
INSERT INTO `sys_roles_permissions` VALUES ('6', '45');
INSERT INTO `sys_roles_permissions` VALUES ('6', '46');
INSERT INTO `sys_roles_permissions` VALUES ('6', '47');
INSERT INTO `sys_roles_permissions` VALUES ('6', '48');
INSERT INTO `sys_roles_permissions` VALUES ('6', '49');
INSERT INTO `sys_roles_permissions` VALUES ('6', '50');
INSERT INTO `sys_roles_permissions` VALUES ('6', '51');
INSERT INTO `sys_roles_permissions` VALUES ('7', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', '管理员', 'f9983992ee2aeec3f25cfcbc7200ed64', 'fa66d9e193d2505e219df169e617b176', '17798502761', '17798502762@189.cn', '0', '2017-12-14 10:05:24');
INSERT INTO `sys_users` VALUES ('2', 'zhangyifeng', '张益峰', '4196e30b70f49c948a3d3787162f5fba', '96e6be2b15b33fc138f43c0b9a746d70', '15295778261', '15295778261@163.com', '0', '2017-12-14 10:07:28');
INSERT INTO `sys_users` VALUES ('4', 'test', '测试账户', '37a3f45dadad6baca3084592765dfd6a', '3faf9af894e7b0cc6ec93b5a19b9211b', '13333333333', '133@sina.com', '0', '2017-12-14 10:05:38');
INSERT INTO `sys_users` VALUES ('5', 'liujian', '刘建', '5def5c21f67fedd17342a68c071923a9', '738d3438da9d3e08ef7089bd3773f922', '18933333333', '18933333333@189.cn', '0', '2017-12-14 10:05:12');

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
INSERT INTO `sys_users_roles` VALUES ('1', '6');
INSERT INTO `sys_users_roles` VALUES ('2', '6');
INSERT INTO `sys_users_roles` VALUES ('2', '7');
INSERT INTO `sys_users_roles` VALUES ('3', '7');
INSERT INTO `sys_users_roles` VALUES ('4', '7');
