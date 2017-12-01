drop table if exists sys_users;
drop table if exists sys_roles;
drop table if exists sys_permissions;
drop table if exists sys_users_roles;
drop table if exists sys_roles_permissions;
drop table if exists sys_menus;

create table sys_users (
  id bigint auto_increment,
  loginname varchar(100),
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  phone varchar(100),
  email varchar(100),
  locked bool default false,
  lastlogin_time datetime,
  constraint pk_sys_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_users_loginname on sys_users(loginname);

create table sys_roles (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_roles_role on sys_roles(role);

create table sys_permissions (
  id bigint auto_increment,
  permission varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_permissions_permission on sys_permissions(permission);

create table sys_users_roles (
  user_id bigint,
  role_id bigint,
  constraint pk_sys_users_roles primary key(user_id, role_id)
) charset=utf8 ENGINE=InnoDB;

create table sys_roles_permissions (
  role_id bigint,
  permission_id bigint,
  constraint pk_sys_roles_permissions primary key(role_id, permission_id)
) charset=utf8 ENGINE=InnoDB;

create table sys_menus (
  id bigint auto_increment,
  menuname varchar(100),
  menuurl varchar(100),
  parentid bigint,
  menuicon varchar(100),
  menuorder tinyint(3),
  level tinyint(3),
  permission bigint,
  isbtn bool default false,
  treeIconSkin varchar(100),
  isshow bool default true,
  constraint pk_sys_menus primary key(id)
) charset=utf8 ENGINE=InnoDB;

INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('1', '首页', '#', '0', 'fa fa-home faa-flash', '1', '1', '0', '1', 'pIcon01');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin) 
VALUES ('2', '系统管理', '', '0', 'fa fa-cog faa-flash', '2', '1', '0', '1', '');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin) 
VALUES ('3', '用户管理', 'sys/user/tolist', '2', 'fa fa-user faa-flash', '1', '2', '0', '1', '');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('4', '角色管理', 'sys/role/tolist', '2', 'fa fa-users faa-flash', '2', '2', '0', '1', '');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin) 
VALUES ('5', '权限管理', 'sys/perm/tolist', '2', 'fa fa-user-plus faa-flash', '3', '2', '0', '1', '');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('6', '菜单管理', 'sys/menu/tolist', '2', 'fa fa-sitemap faa-flash', '4', '2', '0', '1', '');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('7', '新增', '', '3', '', '1', '3', '1', '0', 'pIconadd');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('8', '修改', '', '3', '', '2', '3', '1', '0', 'pIconedit');
INSERT INTO `sys_menus`(id, menuname, menuurl, parentid, menuicon, menuorder, level, isbtn, isshow, treeIconSkin)
 VALUES ('9', '删除', '', '3', '', '3', '3', '1', '0', 'pIcondel');