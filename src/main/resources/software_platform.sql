/*
 Navicat Premium Data Transfer

 Source Server         : 116.62.45.116
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : 116.62.45.116:3306
 Source Schema         : software_platform

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 29/01/2021 11:12:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for circulation
-- ----------------------------
DROP TABLE IF EXISTS `circulation`;
CREATE TABLE `circulation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL COMMENT '流转理由',
  `to_person_id` int(11) DEFAULT NULL COMMENT '被流转人编号',
  `create_person_id` int(11) DEFAULT NULL COMMENT '创建人编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='子项目流转';

-- ----------------------------
-- Records of circulation
-- ----------------------------
BEGIN;
INSERT INTO `circulation` VALUES (5, '不想做', 9, 7);
INSERT INTO `circulation` VALUES (6, '不想干了', 9, 7);
INSERT INTO `circulation` VALUES (7, '我也不想干', 7, 9);
INSERT INTO `circulation` VALUES (8, '难度太大', 9, 7);
INSERT INTO `circulation` VALUES (9, '他会我不会，让他干', 7, 10);
INSERT INTO `circulation` VALUES (10, '阿斯顿撒', 7, 10);
INSERT INTO `circulation` VALUES (11, '实在不会', 10, 12);
COMMIT;

-- ----------------------------
-- Table structure for funds
-- ----------------------------
DROP TABLE IF EXISTS `funds`;
CREATE TABLE `funds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `create_person_id` int(11) DEFAULT NULL,
  `last_deal_person_type` int(11) DEFAULT NULL COMMENT '最终处理人类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of funds
-- ----------------------------
BEGIN;
INSERT INTO `funds` VALUES (5, '测试', 183.41, 7, 1, '2021-01-27 11:00:59', '2021-01-27 11:07:12');
INSERT INTO `funds` VALUES (6, '项目经理测试', 144.12, 2, 0, '2021-01-27 11:12:33', '2021-01-27 11:13:05');
INSERT INTO `funds` VALUES (7, '项目经理测试', 14123.12, 2, 0, '2021-01-27 11:14:54', '2021-01-27 11:18:35');
INSERT INTO `funds` VALUES (8, '吃饭的钱', 1841.11, 7, 1, '2021-01-27 11:28:32', '2021-01-28 00:08:37');
INSERT INTO `funds` VALUES (9, '出差', 218412.13, 7, 2, '2021-01-27 11:58:17', '2021-01-27 12:00:31');
INSERT INTO `funds` VALUES (10, '有用', 100000.00, 10, 2, '2021-01-27 23:34:24', '2021-01-27 23:34:24');
INSERT INTO `funds` VALUES (11, '过年', 500000.00, 10, 0, '2021-01-28 00:39:46', '2021-01-28 00:40:51');
INSERT INTO `funds` VALUES (12, '出去吃饭', 3000.00, 12, 2, '2021-01-28 19:05:35', '2021-01-28 19:05:35');
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES (1, '下宇昂的项目日报(测试新项目)', '测试', '2021-01-24 20:17:14');
INSERT INTO `log` VALUES (2, '普通职员2的项目日报(测试新项目)', '测试123124124', '2021-01-24 21:03:59');
INSERT INTO `log` VALUES (3, '测试人员的项目日报测试新项目', '<p><b>测试</b></p><p><b><br></b></p><p><b>123</b></p>', '2021-01-24 23:05:59');
INSERT INTO `log` VALUES (4, '测试人员的项目日报测试新项目', '<p><b>今日工作：</b></p><p>1. 测试日志</p><p>2. 开发项目列表</p><p>3. 调配审核</p><p><b>明日计划：</b></p><p>1. 正式上班</p>', '2021-01-24 23:09:15');
INSERT INTO `log` VALUES (5, '测试人员姓名的项目日报测试新项目', '测试中', '2021-01-25 13:24:28');
INSERT INTO `log` VALUES (6, '测试人员姓名的项目日报(测试新项目)', '<p><b>我是测试的日志</b></p><p><b><i>hello</i></b></p>', '2021-01-25 13:59:14');
INSERT INTO `log` VALUES (7, '测试人员姓名的项目日报(测试新项目)', '<p>测试数量刷新</p><p><br></p><p>12</p>', '2021-01-25 15:16:40');
INSERT INTO `log` VALUES (8, '下宇昂-项目经理的项目日报(项目5)', '今天必须要完成全部的功能开发', '2021-01-25 15:36:51');
INSERT INTO `log` VALUES (9, '下宇昂-项目经理的项目日报(测试新项目)', '<p>今天划水了</p><p><br></p>', '2021-01-27 22:03:13');
INSERT INTO `log` VALUES (10, '张三的项目日报(测试新项目)', '不想干了锕', '2021-01-27 23:33:25');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, '人员管理', '&#xe70b;', '', 0, 0);
INSERT INTO `menu` VALUES (2, '用户管理', '', './pages/admin/list.html', 1, 0);
INSERT INTO `menu` VALUES (3, '项目管理', '&#xe6b2;', '', 0, 0);
INSERT INTO `menu` VALUES (6, '项目列表', NULL, './pages/project/list.html', 3, 0);
INSERT INTO `menu` VALUES (7, '流转审核', NULL, './pages/deployment/list.html', 14, 0);
INSERT INTO `menu` VALUES (8, '项目管理', '&#xe6b2;', NULL, 0, 2);
INSERT INTO `menu` VALUES (9, '项目列表', NULL, './pages/project/list_normal.html', 8, 2);
INSERT INTO `menu` VALUES (10, 'OA审批', '&#xe723;', NULL, 0, 2);
INSERT INTO `menu` VALUES (11, '流转审批', NULL, './pages/deployment/list_normal.html', 10, 2);
INSERT INTO `menu` VALUES (12, '项目管理', '&#xe6b2;', NULL, 0, 1);
INSERT INTO `menu` VALUES (13, '项目列表', NULL, './pages/project/list_xmjl.html', 12, 1);
INSERT INTO `menu` VALUES (14, 'OA审批', '&#xe723;', NULL, 0, 0);
INSERT INTO `menu` VALUES (15, '经费审核', NULL, './pages/deployment/funds_list_admin.html', 14, 0);
INSERT INTO `menu` VALUES (16, 'OA审批', '&#xe723;', NULL, 0, 1);
INSERT INTO `menu` VALUES (17, '流转审核', NULL, './pages/deployment/list.html', 16, 1);
INSERT INTO `menu` VALUES (18, '经费审核', NULL, './pages/deployment/funds_list_xmjl.html', 16, 1);
INSERT INTO `menu` VALUES (19, '经费审核', NULL, './pages/deployment/funds_list_normal.html', 10, 2);
INSERT INTO `menu` VALUES (20, '进度管理', NULL, './pages/project/jd_list_admin.html', 3, 0);
INSERT INTO `menu` VALUES (21, '日志管理', NULL, './pages/project/log_list_admin.html', 3, 0);
INSERT INTO `menu` VALUES (22, '进度管理', NULL, './pages/project/jd_list_xmjl.html', 12, 1);
INSERT INTO `menu` VALUES (23, '日志管理', NULL, './pages/project/log_list_xmjl.html', 12, 1);
INSERT INTO `menu` VALUES (24, '进度管理', NULL, './pages/project/jd_list_normal.html', 8, 2);
INSERT INTO `menu` VALUES (25, '日志管理', NULL, './pages/project/log_list_normal.html', 8, 2);
COMMIT;

-- ----------------------------
-- Table structure for middle_subscription
-- ----------------------------
DROP TABLE IF EXISTS `middle_subscription`;
CREATE TABLE `middle_subscription` (
  `project_id` int(11) DEFAULT NULL,
  `create_user_true_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `pid` int(11) DEFAULT NULL COMMENT '父任务编号，父类为0',
  `describe_info` varchar(4000) DEFAULT NULL COMMENT '任务描述',
  `duty_person_id` int(11) DEFAULT NULL COMMENT '负责人编号',
  `duty_person_name` varchar(255) DEFAULT NULL COMMENT '负责人姓名',
  `start_time` datetime DEFAULT NULL COMMENT '项目开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '项目结束时间',
  `need_time` int(11) DEFAULT NULL COMMENT '项目所需工时（小时）',
  `status` int(11) DEFAULT NULL COMMENT '完成1、未完成0',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES (4, '测试新项目', 0, '测试一下新的项目1', 2, '秦始皇-项目经理', '2021-01-01 00:00:00', '2021-02-28 00:00:00', NULL, 0, '2021-01-24 15:38:45', '2021-01-27 19:14:22');
INSERT INTO `project` VALUES (6, '子项目1', 4, '测试', 7, '韩一多-普通职员', '2021-01-01 00:00:00', '2021-01-28 00:00:00', NULL, 1, '2021-01-24 15:40:17', '2021-01-29 00:15:10');
INSERT INTO `project` VALUES (7, '项目2', 0, '测试', 2, '秦始皇-项目经理', '2021-01-25 00:00:00', '2021-02-14 00:00:00', NULL, 1, '2021-01-24 17:20:16', '2021-01-24 17:20:16');
INSERT INTO `project` VALUES (8, '项目3', 0, '测试2', 8, '郑于刚-项目经理', '2021-01-25 00:00:00', '2021-01-29 00:00:00', NULL, 0, '2021-01-24 17:20:32', '2021-01-24 17:20:32');
INSERT INTO `project` VALUES (9, '项目4', 0, '123456', 2, '秦始皇-项目经理', '2021-01-25 00:00:00', '2021-01-29 00:00:00', NULL, 1, '2021-01-24 17:20:50', '2021-01-24 17:20:50');
INSERT INTO `project` VALUES (10, '项目5', 0, '3252341', 2, '秦始皇-项目经理', '2021-01-25 00:00:00', '2021-02-23 00:00:00', NULL, 0, '2021-01-24 17:21:03', '2021-01-24 17:21:03');
INSERT INTO `project` VALUES (11, '项目6', 0, 'AA风', 2, '秦始皇-项目经理', '2021-02-11 00:00:00', '2021-02-22 00:00:00', NULL, 0, '2021-01-24 17:21:29', '2021-01-24 17:21:29');
INSERT INTO `project` VALUES (12, '测试7', 0, '1234方式', 8, '郑于刚-项目经理', '2021-01-27 00:00:00', '2021-02-28 00:00:00', NULL, 0, '2021-01-24 17:21:42', '2021-01-24 17:21:42');
INSERT INTO `project` VALUES (13, '项目8', 0, '9876', 2, '秦始皇-项目经理', '2021-01-25 00:00:00', '2021-02-28 00:00:00', NULL, 0, '2021-01-24 17:21:56', '2021-01-24 17:21:56');
INSERT INTO `project` VALUES (14, '项目9', 0, '问题23', 8, '郑于刚-项目经理', '2021-01-27 00:00:00', '2021-02-22 00:00:00', NULL, 0, '2021-01-24 17:22:28', '2021-01-24 17:22:28');
INSERT INTO `project` VALUES (15, '项目10', 0, '撒大啊', 2, '秦始皇-项目经理', '2021-01-26 00:00:00', '2021-02-27 00:00:00', NULL, 0, '2021-01-24 17:22:41', '2021-01-24 17:22:41');
INSERT INTO `project` VALUES (16, '项目11', 0, '214', 2, '秦始皇-项目经理', '2021-01-25 00:00:00', '2021-02-25 00:00:00', NULL, 0, '2021-01-24 17:23:00', '2021-01-24 17:23:00');
INSERT INTO `project` VALUES (17, '子项目2', 4, '测试', 7, '韩一多-普通职员', '2021-01-01 00:00:00', '2021-02-28 00:00:00', NULL, 1, '2021-01-25 14:31:36', '2021-01-27 19:16:31');
INSERT INTO `project` VALUES (18, '测试准点项目', 4, '测试', 9, '驴得水-普通职员', '2021-01-01 00:00:00', '2021-02-28 00:00:00', NULL, 1, '2021-01-27 19:15:22', '2021-01-27 19:15:22');
INSERT INTO `project` VALUES (19, '测试超时', 4, '测试', 9, '驴得水-普通职员', '2021-01-01 00:00:00', '2021-01-06 00:00:00', NULL, 1, '2021-01-27 19:16:00', '2021-01-27 19:16:00');
INSERT INTO `project` VALUES (20, '测试', 4, '123', 9, '驴得水-普通职员', '2021-01-01 00:00:00', '2021-01-11 00:00:00', NULL, 1, '2021-01-27 19:17:11', '2021-01-27 19:17:11');
INSERT INTO `project` VALUES (21, '测试', 4, '12345', 7, '韩一多-普通职员', '2021-01-04 00:00:00', '2021-01-19 00:00:00', NULL, 1, '2021-01-27 19:17:24', '2021-01-27 19:17:24');
INSERT INTO `project` VALUES (22, '项目12', 0, '编写实体类', 8, '郑于刚-项目经理', '2021-01-27 00:00:00', '2021-01-31 00:00:00', NULL, 0, '2021-01-27 21:50:24', '2021-01-27 21:50:24');
INSERT INTO `project` VALUES (23, '修改数据库', 4, '创建表', 10, '张三', '2021-02-11 00:00:00', '2021-02-24 00:00:00', NULL, 1, '2021-01-27 22:10:41', '2021-01-27 23:58:20');
INSERT INTO `project` VALUES (24, '开发', 11, '前端页面修改', 7, '张三', '2021-02-11 00:00:00', '2021-02-18 00:00:00', NULL, 0, '2021-01-27 22:20:33', '2021-01-28 00:12:50');
INSERT INTO `project` VALUES (25, '写好实体类', 4, '打扫厕所', 12, '顾小刚-孤儿', '2021-01-28 00:00:00', '2021-02-01 00:00:00', NULL, 1, '2021-01-28 19:04:46', '2021-01-28 19:06:20');
COMMIT;

-- ----------------------------
-- Table structure for relation_project_to_circulation
-- ----------------------------
DROP TABLE IF EXISTS `relation_project_to_circulation`;
CREATE TABLE `relation_project_to_circulation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `circulation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='流转信息表';

-- ----------------------------
-- Records of relation_project_to_circulation
-- ----------------------------
BEGIN;
INSERT INTO `relation_project_to_circulation` VALUES (5, 6, 5);
INSERT INTO `relation_project_to_circulation` VALUES (6, 6, 6);
INSERT INTO `relation_project_to_circulation` VALUES (7, 6, 7);
INSERT INTO `relation_project_to_circulation` VALUES (8, 6, 8);
INSERT INTO `relation_project_to_circulation` VALUES (9, 23, 9);
INSERT INTO `relation_project_to_circulation` VALUES (10, 24, 10);
INSERT INTO `relation_project_to_circulation` VALUES (11, 25, 11);
COMMIT;

-- ----------------------------
-- Table structure for relation_project_to_funds
-- ----------------------------
DROP TABLE IF EXISTS `relation_project_to_funds`;
CREATE TABLE `relation_project_to_funds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `funds_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation_project_to_funds
-- ----------------------------
BEGIN;
INSERT INTO `relation_project_to_funds` VALUES (4, 4, 5);
INSERT INTO `relation_project_to_funds` VALUES (5, 4, 6);
INSERT INTO `relation_project_to_funds` VALUES (6, 4, 7);
INSERT INTO `relation_project_to_funds` VALUES (7, 4, 8);
INSERT INTO `relation_project_to_funds` VALUES (8, 4, 9);
INSERT INTO `relation_project_to_funds` VALUES (9, 4, 10);
INSERT INTO `relation_project_to_funds` VALUES (10, 4, 11);
INSERT INTO `relation_project_to_funds` VALUES (11, 4, 12);
COMMIT;

-- ----------------------------
-- Table structure for relation_project_to_log
-- ----------------------------
DROP TABLE IF EXISTS `relation_project_to_log`;
CREATE TABLE `relation_project_to_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation_project_to_log
-- ----------------------------
BEGIN;
INSERT INTO `relation_project_to_log` VALUES (1, 1, 4);
INSERT INTO `relation_project_to_log` VALUES (2, 2, 4);
INSERT INTO `relation_project_to_log` VALUES (3, 3, 4);
INSERT INTO `relation_project_to_log` VALUES (4, 4, 4);
INSERT INTO `relation_project_to_log` VALUES (5, 5, 4);
INSERT INTO `relation_project_to_log` VALUES (6, 6, 4);
INSERT INTO `relation_project_to_log` VALUES (7, 7, 4);
INSERT INTO `relation_project_to_log` VALUES (8, 8, 10);
INSERT INTO `relation_project_to_log` VALUES (9, 9, 4);
INSERT INTO `relation_project_to_log` VALUES (10, 10, 4);
COMMIT;

-- ----------------------------
-- Table structure for relation_subscription_to_circulation
-- ----------------------------
DROP TABLE IF EXISTS `relation_subscription_to_circulation`;
CREATE TABLE `relation_subscription_to_circulation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subscription_id` int(11) DEFAULT NULL,
  `circulation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='项目流转表';

-- ----------------------------
-- Records of relation_subscription_to_circulation
-- ----------------------------
BEGIN;
INSERT INTO `relation_subscription_to_circulation` VALUES (5, 5, 5);
INSERT INTO `relation_subscription_to_circulation` VALUES (6, 6, 6);
INSERT INTO `relation_subscription_to_circulation` VALUES (7, 7, 7);
INSERT INTO `relation_subscription_to_circulation` VALUES (8, 8, 8);
INSERT INTO `relation_subscription_to_circulation` VALUES (9, 20, 9);
INSERT INTO `relation_subscription_to_circulation` VALUES (10, 21, 10);
INSERT INTO `relation_subscription_to_circulation` VALUES (11, 24, 11);
COMMIT;

-- ----------------------------
-- Table structure for relation_subscription_to_founds
-- ----------------------------
DROP TABLE IF EXISTS `relation_subscription_to_founds`;
CREATE TABLE `relation_subscription_to_founds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subscription_id` int(11) DEFAULT NULL,
  `funds_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation_subscription_to_founds
-- ----------------------------
BEGIN;
INSERT INTO `relation_subscription_to_founds` VALUES (6, 14, 5);
INSERT INTO `relation_subscription_to_founds` VALUES (7, 15, 6);
INSERT INTO `relation_subscription_to_founds` VALUES (8, 16, 7);
INSERT INTO `relation_subscription_to_founds` VALUES (9, 17, 8);
INSERT INTO `relation_subscription_to_founds` VALUES (10, 18, 9);
INSERT INTO `relation_subscription_to_founds` VALUES (11, 19, 10);
INSERT INTO `relation_subscription_to_founds` VALUES (12, 22, 11);
INSERT INTO `relation_subscription_to_founds` VALUES (13, 23, 12);
COMMIT;

-- ----------------------------
-- Table structure for relation_user_to_log
-- ----------------------------
DROP TABLE IF EXISTS `relation_user_to_log`;
CREATE TABLE `relation_user_to_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation_user_to_log
-- ----------------------------
BEGIN;
INSERT INTO `relation_user_to_log` VALUES (1, 1, 2);
INSERT INTO `relation_user_to_log` VALUES (2, 2, 9);
INSERT INTO `relation_user_to_log` VALUES (3, 3, 1);
INSERT INTO `relation_user_to_log` VALUES (4, 4, 1);
INSERT INTO `relation_user_to_log` VALUES (5, 5, 7);
INSERT INTO `relation_user_to_log` VALUES (6, 6, 7);
INSERT INTO `relation_user_to_log` VALUES (7, 7, 7);
INSERT INTO `relation_user_to_log` VALUES (8, 8, 2);
INSERT INTO `relation_user_to_log` VALUES (9, 9, 2);
INSERT INTO `relation_user_to_log` VALUES (10, 10, 10);
COMMIT;

-- ----------------------------
-- Table structure for subscription
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL COMMENT '审批状态',
  `deal_person_id` int(11) DEFAULT NULL COMMENT '处理人',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of subscription
-- ----------------------------
BEGIN;
INSERT INTO `subscription` VALUES (5, 1, 1, '2021-01-25 21:58:17', '2021-01-25 23:46:10');
INSERT INTO `subscription` VALUES (6, 1, 1, '2021-01-25 23:52:21', '2021-01-25 23:52:56');
INSERT INTO `subscription` VALUES (7, 1, 2, '2021-01-25 23:54:36', '2021-01-26 00:10:51');
INSERT INTO `subscription` VALUES (8, -1, 2, '2021-01-26 00:15:10', '2021-01-26 00:15:30');
INSERT INTO `subscription` VALUES (11, 1, 1, '2021-01-26 23:43:51', '2021-01-27 10:54:07');
INSERT INTO `subscription` VALUES (13, -1, 1, '2021-01-27 10:55:40', '2021-01-27 10:59:09');
INSERT INTO `subscription` VALUES (14, -1, 2, '2021-01-27 11:00:59', '2021-01-27 11:07:12');
INSERT INTO `subscription` VALUES (15, 1, 1, '2021-01-27 11:12:33', '2021-01-27 11:13:05');
INSERT INTO `subscription` VALUES (16, 1, 1, '2021-01-27 11:14:54', '2021-01-27 11:18:35');
INSERT INTO `subscription` VALUES (17, 1, 2, '2021-01-27 11:28:33', '2021-01-28 00:08:37');
INSERT INTO `subscription` VALUES (18, -2, 7, '2021-01-27 11:58:18', '2021-01-27 12:00:31');
INSERT INTO `subscription` VALUES (19, 0, NULL, '2021-01-27 23:34:24', '2021-01-27 23:34:24');
INSERT INTO `subscription` VALUES (20, -1, 1, '2021-01-27 23:58:20', '2021-01-28 00:33:52');
INSERT INTO `subscription` VALUES (21, 1, 1, '2021-01-28 00:12:50', '2021-01-28 00:18:26');
INSERT INTO `subscription` VALUES (22, 1, 1, '2021-01-28 00:39:46', '2021-01-28 00:40:51');
INSERT INTO `subscription` VALUES (23, 0, NULL, '2021-01-28 19:05:35', '2021-01-28 19:05:35');
INSERT INTO `subscription` VALUES (24, 0, NULL, '2021-01-28 19:06:20', '2021-01-28 19:06:20');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(32) DEFAULT NULL,
  `true_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admintest', '123456', 0, '管理员', '2021-01-22 10:37:24', '19999999999', NULL, NULL, '');
INSERT INTO `user` VALUES (2, 'yaxia', '123456', 1, '秦始皇-项目经理', '2021-01-22 10:52:25', '13226477889', NULL, NULL, '');
INSERT INTO `user` VALUES (7, 'normal', '123456', 2, '韩一多-普通职员', '2021-01-22 14:11:06', '13588833345', '男', 22, '');
INSERT INTO `user` VALUES (8, 'jl', '123456', 1, '郑于刚-项目经理', '2021-01-24 16:36:27', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, 'normal2', '123456', 2, '驴得水-普通职员', '2021-01-24 16:36:44', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (10, 'test', '123456', 2, '张三', '2021-01-27 21:32:12', '13026477219', NULL, NULL, '');
INSERT INTO `user` VALUES (11, 'admin', '123456', 0, '管理员2', '2021-01-27 23:41:12', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (12, 'gxg', '123456', 2, '顾小刚-孤儿', '2021-01-28 19:02:29', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (13, 'testCharles', '123456', 1, '他', '2021-01-28 21:27:42', NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
