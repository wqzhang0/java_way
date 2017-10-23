/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-23 16:54:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', '张文强');
INSERT INTO `student` VALUES ('3', '王明丽');
INSERT INTO `student` VALUES ('4', '李志');
INSERT INTO `student` VALUES ('5', '赵磊');
INSERT INTO `student` VALUES ('8', '王满');
INSERT INTO `student` VALUES ('9', '王琳');
INSERT INTO `student` VALUES ('10', '王锐');
INSERT INTO `student` VALUES ('11', '王琳');
INSERT INTO `student` VALUES ('12', '王艺博');
INSERT INTO `student` VALUES ('13', '王伟');
INSERT INTO `student` VALUES ('14', '王辰硕');
INSERT INTO `student` VALUES ('15', '王鸿轩');
INSERT INTO `student` VALUES ('16', '王涵润');
INSERT INTO `student` VALUES ('17', '王涵涵');
INSERT INTO `student` VALUES ('18', '王兴');
INSERT INTO `student` VALUES ('19', '王淳曦');
INSERT INTO `student` VALUES ('20', '王雨微');
INSERT INTO `student` VALUES ('21', '王浩晏');
INSERT INTO `student` VALUES ('22', '王芊语');
INSERT INTO `student` VALUES ('23', '王满');
INSERT INTO `student` VALUES ('24', '王琳');
INSERT INTO `student` VALUES ('25', '王锐');
INSERT INTO `student` VALUES ('26', '王韬茫');
INSERT INTO `student` VALUES ('27', '王九雏');
INSERT INTO `student` VALUES ('28', '王子骏');
INSERT INTO `student` VALUES ('29', '王之骏');
INSERT INTO `student` VALUES ('30', '王楠');
INSERT INTO `student` VALUES ('31', '王静雯');
INSERT INTO `student` VALUES ('32', '王镜文');
INSERT INTO `student` VALUES ('33', '王翠楠');
INSERT INTO `student` VALUES ('34', '王镜雯');
INSERT INTO `student` VALUES ('35', '王靖雯');
INSERT INTO `student` VALUES ('36', '王雨辰');
INSERT INTO `student` VALUES ('37', '王芊语');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `full_score` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(1) NOT NULL COMMENT '1 目录  2 菜单 3  按钮',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类id',
  `perm` varchar(255) DEFAULT NULL COMMENT '权限',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', null, null, null, null, '系统管理');
INSERT INTO `sys_menu` VALUES ('2', '1', null, '1', 'sys:user', null, '用户管理');
INSERT INTO `sys_menu` VALUES ('3', '1', null, '1', 'sys:role', null, '角色管理');
INSERT INTO `sys_menu` VALUES ('4', '1', null, '1', 'sys:roles', null, '角色组管理');
INSERT INTO `sys_menu` VALUES ('5', '1', null, '1', 'sys:menu', null, '菜单管理');
INSERT INTO `sys_menu` VALUES ('6', '1', null, '1', 'sys:roles', null, '用户组权限查看');
INSERT INTO `sys_menu` VALUES ('7', '0', null, null, null, null, '系统日志');
INSERT INTO `sys_menu` VALUES ('8', '1', null, '7', 'sys:log:login', null, '登陆日志');
INSERT INTO `sys_menu` VALUES ('9', '1', null, '7', 'sys:log:user', null, '用户操作日志');
INSERT INTO `sys_menu` VALUES ('10', '1', null, '7', 'sys:log:roleAssign', null, '权限分配日志');
INSERT INTO `sys_menu` VALUES ('11', '1', null, '7', 'sys:log:userOperation', null, '用户操作日志');
INSERT INTO `sys_menu` VALUES ('12', '2', null, '5', 'sys:menu:list', null, '查看');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` char(1) NOT NULL COMMENT '状态 1 有效 2 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '系统管理员', '全部权限', '1');
INSERT INTO `sys_role` VALUES ('1', '普通用户', '基本权限', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '0');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '12');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) NOT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `status` char(1) NOT NULL DEFAULT '' COMMENT '状态 1有效 0 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'admin', '0', '1');
INSERT INTO `sys_user` VALUES ('2', 'Zhang Wq', 'wqzhang', '123', '1', '1');

-- ----------------------------
-- Table structure for test_record
-- ----------------------------
DROP TABLE IF EXISTS `test_record`;
CREATE TABLE `test_record` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `student_id` int(18) NOT NULL,
  `subject_id` int(18) NOT NULL,
  `score` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `out_key_student_id` (`student_id`),
  KEY `out_key_subject_id` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_record
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
