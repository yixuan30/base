/*
 Navicat Premium Data Transfer

 Source Server         : 公网
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 39.98.202.172:3306
 Source Schema         : base

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 03/09/2020 16:09:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fz_menu
-- ----------------------------
DROP TABLE IF EXISTS `fz_menu`;
CREATE TABLE `fz_menu`  (
  `menu_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父类菜单id',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `open` int(1) NULL DEFAULT NULL COMMENT '0：不展开，1：展开',
  `available` int(11) NULL DEFAULT NULL COMMENT '0：不可用，1：可用',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0：菜单，1：按钮',
  `perms` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `order_num` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fz_menu
-- ----------------------------
INSERT INTO `fz_menu` VALUES ('1', '系统管理', NULL, '0', '', '2020-08-21 13:47:18', '2020-08-21 13:47:20', 1, 1, '0', NULL, 1);
INSERT INTO `fz_menu` VALUES ('10', '用户添加', NULL, '5', 'el-icon-plus', '2020-08-21 14:57:10', '2020-08-21 14:57:12', 0, 1, '1', 'user:add', 1);
INSERT INTO `fz_menu` VALUES ('11', '用户编辑', NULL, '5', 'el-icon-s-promotion', '2020-08-21 14:58:13', '2020-08-21 14:58:15', 0, 1, '1', 'user:edit', 3);
INSERT INTO `fz_menu` VALUES ('12', '用户更新', NULL, '5', 'el-icon-refresh', '2020-08-28 15:25:24', '2020-08-28 15:25:26', 0, 1, '1', 'user:update', 4);
INSERT INTO `fz_menu` VALUES ('13', '禁用用户', NULL, '5', 'el-icon-circle-close', '2020-08-28 15:28:10', '2020-08-28 15:28:12', 0, 1, '1', 'user:status', 5);
INSERT INTO `fz_menu` VALUES ('1300614802288185345', '测试菜单', '', '4', NULL, '2020-09-01 10:01:48', '2020-09-01 10:01:48', 0, 1, '1', '', 6);
INSERT INTO `fz_menu` VALUES ('14', '分配角色', NULL, '5', 'el-icon-s-tools', '2020-08-28 15:30:16', '2020-08-28 15:30:18', 0, 1, '1', 'user:assign', 6);
INSERT INTO `fz_menu` VALUES ('15', '编辑菜单', NULL, '4', 'el-icon-edit', '2020-08-31 09:22:51', '2020-09-01 10:00:10', 0, 0, '1', 'menu:edit', 1);
INSERT INTO `fz_menu` VALUES ('16', '菜单添加', NULL, '4', 'el-icon-s-opportunity', '2020-08-31 09:23:54', '2020-08-31 09:23:57', 0, 1, '1', 'menu:add', 2);
INSERT INTO `fz_menu` VALUES ('17', '菜单修改', NULL, '4', 'el-icon-share', '2020-08-31 09:24:35', '2020-08-31 09:24:38', 0, 1, '1', 'menu:update', 3);
INSERT INTO `fz_menu` VALUES ('18', '菜单删除', NULL, '4', 'el-icon-folder-opened', '2020-08-31 09:25:21', '2020-08-31 09:25:24', 0, 1, '1', 'menu:delete', 4);
INSERT INTO `fz_menu` VALUES ('19', '角色编辑', NULL, '6', 'el-icon-s-promotion', '2020-08-31 10:07:10', '2020-08-31 10:07:07', 0, 1, '1', 'role:edit', 1);
INSERT INTO `fz_menu` VALUES ('20', '角色删除', NULL, '6', 'el-icon-s-marketing', '2020-08-31 10:07:45', '2020-08-31 10:07:47', 0, 1, '1', 'role:delete', 2);
INSERT INTO `fz_menu` VALUES ('21', '角色添加', NULL, '6', 'el-icon-help', '2020-08-31 10:08:43', '2020-08-31 10:08:45', 0, 1, '1', 'role:add', 3);
INSERT INTO `fz_menu` VALUES ('22', '角色更新', NULL, '6', 'el-icon-refresh-left', '2020-08-31 10:09:42', '2020-08-31 10:09:44', 0, 1, '1', 'role:update', 4);
INSERT INTO `fz_menu` VALUES ('23', '角色状态', NULL, '6', 'el-icon-refresh', '2020-08-31 10:10:35', '2020-08-31 10:10:37', 0, 1, '1', 'role:status', 5);
INSERT INTO `fz_menu` VALUES ('24', '分配权限', NULL, '6', 'el-icon-document-add', '2020-08-31 10:11:20', '2020-08-31 10:11:22', 0, 1, '1', 'role:authority', 6);
INSERT INTO `fz_menu` VALUES ('4', '菜单管理', '/menus', '1', 'el-icon-help', '2020-08-21 13:48:26', '2020-09-01 10:30:18', 0, 1, '0', '', 3);
INSERT INTO `fz_menu` VALUES ('5', '用户管理', '/users', '1', 'el-icon-user', '2020-08-21 13:49:40', '2020-08-21 13:49:42', 0, 1, '0', 'users', 4);
INSERT INTO `fz_menu` VALUES ('6', '角色管理', '/roles', '1', 'el-icon-postcard', '2020-08-21 13:51:05', '2020-08-21 13:51:07', 0, 1, '0', NULL, 5);
INSERT INTO `fz_menu` VALUES ('9', '用户删除', NULL, '5', 'el-icon-picture', '2020-08-21 14:55:25', '2020-08-21 14:55:28', 0, 1, '1', 'user:delete', 2);

-- ----------------------------
-- Table structure for fz_role
-- ----------------------------
DROP TABLE IF EXISTS `fz_role`;
CREATE TABLE `fz_role`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `state` int(1) NULL DEFAULT 1 COMMENT '角色状态',
  `role_authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户权限',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fz_role
-- ----------------------------
INSERT INTO `fz_role` VALUES ('1', '综合评分角色', NULL, 1, 'vip', '2020-08-19 09:53:07', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('10', '研发部角色', NULL, 1, 'vip', '2020-08-20 14:57:00', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('11', '销售部角色', NULL, 1, 'vip', '2020-08-20 14:57:21', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('1300313119628836865', '系统人员', '系统测试人员', 1, NULL, '2020-08-31 14:03:01', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('2', '调研问卷角色', NULL, 1, 'vip', '2020-08-20 11:10:50', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('20', '系统管理员', '拥有最大的权限', 1, '', '2020-08-24 11:11:40', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('3', '总部调研问卷角色', NULL, 1, 'vip', '2020-08-20 11:11:37', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('4', '总部交运期角色', NULL, 1, 'vip', '2020-08-20 11:13:09', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('5', '财富与机构业务部角色', NULL, 1, 'vip', '2020-08-20 11:14:46', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('6', '总部审计廉洁从业调查问卷角色', NULL, 1, 'vip', '2020-08-20 14:53:56', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('7', '总部18年末人员数量查看角色', NULL, 1, 'vip', '2020-08-20 14:55:28', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('8', '市场部角色', NULL, 1, 'vip', '2020-08-20 14:56:11', '2020-08-31 14:03:09');
INSERT INTO `fz_role` VALUES ('9', '产品部角色', NULL, 1, 'vip', '2020-08-20 14:56:39', '2020-08-31 14:03:09');

-- ----------------------------
-- Table structure for fz_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `fz_role_menu`;
CREATE TABLE `fz_role_menu`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `role_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色菜单id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fz_role_menu
-- ----------------------------
INSERT INTO `fz_role_menu` VALUES ('20', '1', '1', '2020-08-27 13:27:04', '2020-08-27 13:27:06');
INSERT INTO `fz_role_menu` VALUES ('20', '17', '10', '2020-08-31 09:34:10', '2020-08-31 09:34:13');
INSERT INTO `fz_role_menu` VALUES ('20', '18', '11', '2020-08-31 09:34:25', '2020-08-31 09:34:27');
INSERT INTO `fz_role_menu` VALUES ('20', '19', '12', '2020-08-31 10:14:02', '2020-08-31 10:14:05');
INSERT INTO `fz_role_menu` VALUES ('20', '4', '1297766365255561217', '2020-08-24 13:23:07', '2020-08-24 13:23:07');
INSERT INTO `fz_role_menu` VALUES ('20', '5', '1297766370586521602', '2020-08-24 13:23:09', '2020-08-24 13:23:09');
INSERT INTO `fz_role_menu` VALUES ('20', '6', '1297766374835351554', '2020-08-24 13:23:10', '2020-08-24 13:23:10');
INSERT INTO `fz_role_menu` VALUES ('20', '20', '13', '2020-08-31 10:14:11', '2020-08-31 10:14:08');
INSERT INTO `fz_role_menu` VALUES ('1300313119628836865', '15', '1300614952490405890', '2020-09-01 10:02:24', '2020-09-01 10:02:24');
INSERT INTO `fz_role_menu` VALUES ('1300313119628836865', '1300614802288185345', '1300614953262157826', '2020-09-01 10:02:24', '2020-09-01 10:02:24');
INSERT INTO `fz_role_menu` VALUES ('1300313119628836865', '1', '1300614953966800897', '2020-09-01 10:02:24', '2020-09-01 10:02:24');
INSERT INTO `fz_role_menu` VALUES ('1300313119628836865', '4', '1300614954692415490', '2020-09-01 10:02:24', '2020-09-01 10:02:24');
INSERT INTO `fz_role_menu` VALUES ('20', '21', '14', '2020-08-31 10:14:24', '2020-08-31 10:14:29');
INSERT INTO `fz_role_menu` VALUES ('20', '22', '15', '2020-08-31 10:14:39', '2020-08-31 10:14:41');
INSERT INTO `fz_role_menu` VALUES ('20', '23', '16', '2020-08-31 10:15:04', '2020-08-31 10:15:06');
INSERT INTO `fz_role_menu` VALUES ('20', '24', '17', '2020-08-31 10:15:19', '2020-08-31 10:15:22');
INSERT INTO `fz_role_menu` VALUES ('20', '9', '2', '2020-08-27 13:27:22', '2020-08-27 13:27:25');
INSERT INTO `fz_role_menu` VALUES ('20', '10', '3', '2020-08-27 13:27:36', '2020-08-27 13:27:40');
INSERT INTO `fz_role_menu` VALUES ('20', '11', '4', '2020-08-27 13:27:52', '2020-08-27 13:27:57');
INSERT INTO `fz_role_menu` VALUES ('20', '12', '5', '2020-08-28 15:31:31', '2020-08-28 15:31:33');
INSERT INTO `fz_role_menu` VALUES ('20', '13', '6', '2020-08-28 15:31:49', '2020-08-28 15:31:50');
INSERT INTO `fz_role_menu` VALUES ('20', '14', '7', '2020-08-28 15:32:01', '2020-08-28 15:32:04');
INSERT INTO `fz_role_menu` VALUES ('20', '15', '8', '2020-08-31 09:33:45', '2020-08-31 09:33:47');
INSERT INTO `fz_role_menu` VALUES ('20', '16', '9', '2020-08-31 09:33:59', '2020-08-31 09:34:01');

-- ----------------------------
-- Table structure for fz_user
-- ----------------------------
DROP TABLE IF EXISTS `fz_user`;
CREATE TABLE `fz_user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `state` int(1) NULL DEFAULT 1 COMMENT '状态',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '0:超级管理员，1：系统用户',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fz_user
-- ----------------------------
INSERT INTO `fz_user` VALUES ('1', 'lisa', 'lisa', '\r\nhttps://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 1, '123456', '489456', 1, '12345', '2020-08-19 00:00:00', '2020-08-20 10:00:09', NULL, 0, '2020-08-28');
INSERT INTO `fz_user` VALUES ('1296265144208232449', '三哥', '三哥', '\r\nhttps://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 0, 'string', 'string', 1, 'string', '2020-08-20 09:57:48', '2020-08-20 10:05:53', NULL, 0, '2020-08-28');
INSERT INTO `fz_user` VALUES ('1299238466156077057', 'yixuan30', 'yixuan330', '\r\nhttps://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 0, '988e280760aca1d75d33a1dd2ece428c', '17680161485', 1, '3043451759@qq.com', '2020-08-28 14:52:44', '2020-09-01 09:59:36', 'ed342c72-5583-4a72-8ae2-886cb2e9', 0, '2020-08-28');
INSERT INTO `fz_user` VALUES ('1300622360419774466', '系统测试', '系统测试一号', 'https://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 1, 'c1ab4fffd665a312ba3dce5578a7c401', '17680161485', 1, '3043451759@qq.com', '2020-09-01 10:31:50', '2020-09-01 10:31:50', 'd1ae6d2e-a87c-4d3c-bb30-6f883f8e', 1, '2020-09-30');
INSERT INTO `fz_user` VALUES ('1300623936366698498', 'test', '系统测试人员3号', 'https://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 0, '3d5751a624a9f56af2f2c77db239970a', '17680161485', 1, '3043451759@qq.com', '2020-09-01 10:38:06', '2020-09-01 10:38:06', 'a94d82fd-ea62-4c0d-be9e-f1a400f9', 1, '2020-09-30');
INSERT INTO `fz_user` VALUES ('1300625653116297218', 'test1', '系统测试人员2号', 'https://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 0, 'c8fb83ed061eec6cd31a85ba460f4283', '17680161485', 1, '3043451759@qq.com', '2020-09-01 10:44:55', '2020-09-01 10:44:55', '77ad107f-87af-4950-9f1c-d92229cb', 1, '2020-09-30');
INSERT INTO `fz_user` VALUES ('2', 'admin', '管理员用户', '\r\nhttps://education-1010.oss-cn-beijing.aliyuncs.com/logo.jpg', 1, 'e32c883cc9717909848a83f9ac697715', '17680161485', 1, '3043451759@qq.com', '2020-08-26 16:41:10', '2020-08-28 16:47:28', 'dbf28b78-4eca-40cf-ae93-88f647ccb234', 0, '2020-08-27');

-- ----------------------------
-- Table structure for fz_user_role
-- ----------------------------
DROP TABLE IF EXISTS `fz_user_role`;
CREATE TABLE `fz_user_role`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `user_role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fz_user_role
-- ----------------------------
INSERT INTO `fz_user_role` VALUES ('2', '20', '1', '2020-08-27 13:25:58', '2020-08-27 13:26:00');
INSERT INTO `fz_user_role` VALUES ('1', '1', '1297714829146009602', '2020-08-24 09:58:20', '2020-08-24 09:58:20');
INSERT INTO `fz_user_role` VALUES ('1', '3', '1297714830886645762', '2020-08-24 09:58:21', '2020-08-24 09:58:21');
INSERT INTO `fz_user_role` VALUES ('1', '4', '1297714832434343937', '2020-08-24 09:58:21', '2020-08-24 09:58:21');
INSERT INTO `fz_user_role` VALUES ('1299238466156077057', '20', '1300612486331928577', '2020-09-01 09:52:36', '2020-09-01 09:52:36');
INSERT INTO `fz_user_role` VALUES ('1299238466156077057', '1', '1300612487351144449', '2020-09-01 09:52:36', '2020-09-01 09:52:36');
INSERT INTO `fz_user_role` VALUES ('1299238466156077057', '3', '1300612488097730562', '2020-09-01 09:52:36', '2020-09-01 09:52:36');
INSERT INTO `fz_user_role` VALUES ('1299238466156077057', '4', '1300612488840122370', '2020-09-01 09:52:36', '2020-09-01 09:52:36');
INSERT INTO `fz_user_role` VALUES ('1299238466156077057', '1300313119628836865', '1300612489582514177', '2020-09-01 09:52:36', '2020-09-01 09:52:36');

SET FOREIGN_KEY_CHECKS = 1;
