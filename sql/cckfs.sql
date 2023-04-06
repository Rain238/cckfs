/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : cckfs

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/04/2023 18:12:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `bill_id` int NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日期',
  `wechat` decimal(10, 2) NOT NULL COMMENT '微信金额',
  `cash` decimal(10, 2) NOT NULL COMMENT '现金金额',
  `alipay` decimal(10, 2) NOT NULL COMMENT '支付宝金额',
  `takeaway` decimal(10, 2) NOT NULL COMMENT '外卖金额',
  `old_member` decimal(10, 2) NOT NULL COMMENT '旧会员金额',
  `new_member` decimal(10, 2) NOT NULL COMMENT '新会员金额',
  `member_recharge` decimal(10, 2) NOT NULL COMMENT '会员充值金额',
  `jiahe_distribution` decimal(10, 2) NOT NULL COMMENT '佳禾配货金额',
  `customer_distribution` decimal(10, 2) NOT NULL COMMENT '众客配货金额',
  `jambe_distribution` decimal(10, 2) NOT NULL COMMENT '詹贝配货金额',
  `ruimao_distribution` decimal(10, 2) NOT NULL COMMENT '瑞茂配货金额',
  `huashun_distribution` decimal(10, 2) NOT NULL COMMENT '华顺配货金额',
  `auntie_distribution` decimal(10, 2) NOT NULL COMMENT '姑姑配货金额',
  `update_date` bigint NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`bill_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for debit
-- ----------------------------
DROP TABLE IF EXISTS `debit`;
CREATE TABLE `debit`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借记日期',
  `store_name1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店名1',
  `store_name2` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店名2',
  `commodity` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借记商品',
  `payment` decimal(10, 2) NULL DEFAULT NULL COMMENT '借记货款',
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'i:进行中，c:已完成',
  `update_time` bigint NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of debit
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '充值日期',
  `card_number` int NOT NULL COMMENT '充值卡号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '充值姓名',
  `amount` decimal(10, 2) NOT NULL COMMENT '充值金额',
  `user_tel` varbinary(11) NULL DEFAULT NULL COMMENT '手机号',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `update_time` bigint NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL COMMENT '用户id',
  `user_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password_md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码MD5',
  `user_sex` char(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别 M/F',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `register_time` bigint NOT NULL COMMENT '注册日期',
  `last_login_time` bigint NOT NULL COMMENT '最后登录时间',
  `user_flag` int NOT NULL COMMENT '黑名单标记',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user', '$2a$10$pYoC.RWdtgEkNWJLakbYKeWfi0q66.G5tqOMT1DwdFKIh40.tkkb6');

SET FOREIGN_KEY_CHECKS = 1;
