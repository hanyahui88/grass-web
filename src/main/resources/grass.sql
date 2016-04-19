/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : grass

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-04-19 16:04:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('12', '12');
INSERT INTO `sys_user` VALUES ('342', '2321');
INSERT INTO `sys_user` VALUES ('123423', '231');
INSERT INTO `sys_user` VALUES ('321', '21');
INSERT INTO `sys_user` VALUES ('123', '23');
