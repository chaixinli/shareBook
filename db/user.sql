/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : sharebook

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-10-20 15:21:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_NAME` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `USER_PASSWORD` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `EMAIL` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
