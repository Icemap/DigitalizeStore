/*
Navicat MySQL Data Transfer

Source Server         : MyCloud
Source Server Version : 50173
Source Host           : wangqizhi.top:3306
Source Database       : digitalize_store

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-20 14:10:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand_info
-- ----------------------------
DROP TABLE IF EXISTS `brand_info`;
CREATE TABLE `brand_info` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand_info
-- ----------------------------
INSERT INTO `brand_info` VALUES ('1', '隔壁王氏珠宝', null);

-- ----------------------------
-- Table structure for business_unit_info
-- ----------------------------
DROP TABLE IF EXISTS `business_unit_info`;
CREATE TABLE `business_unit_info` (
  `id` int(11) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '事业部的名称',
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_unit_info
-- ----------------------------
INSERT INTO `business_unit_info` VALUES ('1', '1', '隔壁王氏华南区', null);
INSERT INTO `business_unit_info` VALUES ('2', '1', '隔壁王氏华东区', null);

-- ----------------------------
-- Table structure for camera_info
-- ----------------------------
DROP TABLE IF EXISTS `camera_info`;
CREATE TABLE `camera_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL COMMENT '摄像头可访问的Url',
  `is_enter` tinyint(4) DEFAULT NULL COMMENT '所对位置是否是门口',
  `area` varchar(255) DEFAULT NULL COMMENT '所对区域',
  `hot_point_path` varchar(255) DEFAULT NULL COMMENT '摄像机热点的存储路径',
  `path_path` varchar(255) DEFAULT NULL COMMENT '摄像头动线文件存储路径',
  `store_id` int(11) NOT NULL,
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of camera_info
-- ----------------------------
INSERT INTO `camera_info` VALUES ('1', 'rtsp://cheese.123.top/oflaDemo/123', '0', '金', '/hotpath/123', '/pathpath/123', '2', null);

-- ----------------------------
-- Table structure for camera_push_msg
-- ----------------------------
DROP TABLE IF EXISTS `camera_push_msg`;
CREATE TABLE `camera_push_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `camera_id` int(11) DEFAULT NULL COMMENT '本信息所属摄像机的id',
  `datetime` datetime DEFAULT NULL,
  `is_add` tinyint(11) DEFAULT NULL COMMENT '是否为增加，反之为减少',
  `age` int(11) DEFAULT NULL,
  `is_male` tinyint(4) DEFAULT NULL COMMENT '是否为男性',
  `is_enter_store` tinyint(4) DEFAULT NULL COMMENT '是否进店',
  `store_id` int(11) DEFAULT NULL COMMENT '所属门店id',
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of camera_push_msg
-- ----------------------------
INSERT INTO `camera_push_msg` VALUES ('51', '1', '2017-03-19 11:13:11', '0', '0', '1', '1', '2', null);
INSERT INTO `camera_push_msg` VALUES ('2', '1', '2017-03-19 09:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('3', '1', '2017-03-19 10:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('4', '1', '2017-03-19 11:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('5', '1', '2017-03-19 13:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('6', '1', '2017-03-19 17:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('7', '1', '2017-03-19 19:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('8', '1', '2017-03-19 20:13:11', '0', '0', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('9', '1', '2017-03-19 15:13:11', '0', '20', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('10', '1', '2017-03-19 14:13:11', '0', '20', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('11', '1', '2017-03-19 17:13:11', '0', '20', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('12', '1', '2017-03-19 20:13:11', '0', '20', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('13', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('14', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('15', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('16', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('17', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('18', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('19', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('20', '1', '2017-03-19 20:13:11', '0', '30', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('21', '1', '2017-03-19 09:13:11', '0', '40', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('22', '1', '2017-03-19 11:13:11', '0', '40', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('23', '1', '2017-03-19 11:13:11', '0', '40', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('24', '1', '2017-03-19 13:13:11', '0', '40', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('25', '1', '2017-03-19 13:13:11', '0', '50', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('26', '1', '2017-03-19 17:13:11', '0', '50', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('27', '1', '2017-03-19 17:13:11', '0', '50', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('28', '1', '2017-03-19 17:13:11', '0', '50', '0', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('29', '1', '2017-03-19 17:13:11', '0', '50', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('30', '1', '2017-03-19 11:13:11', '0', '50', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('31', '1', '2017-03-19 18:13:11', '0', '50', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('32', '1', '2017-03-19 15:13:11', '0', '50', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('33', '1', '2017-03-19 16:13:11', '0', '50', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('34', '1', '2017-03-19 16:13:11', '0', '40', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('35', '1', '2017-03-19 11:13:11', '0', '40', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('36', '1', '2017-03-19 13:13:11', '0', '40', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('37', '1', '2017-03-19 13:13:11', '0', '30', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('38', '1', '2017-03-19 11:13:11', '0', '30', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('39', '1', '2017-03-19 09:13:11', '0', '30', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('40', '1', '2017-03-19 20:13:11', '0', '30', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('41', '1', '2017-03-19 20:13:11', '0', '20', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('42', '1', '2017-03-19 18:13:11', '0', '20', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('43', '1', '2017-03-19 17:13:11', '0', '20', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('44', '1', '2017-03-19 09:13:11', '0', '20', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('45', '1', '2017-03-19 09:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('46', '1', '2017-03-19 13:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('47', '1', '2017-03-19 18:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('48', '1', '2017-03-19 15:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('49', '1', '2017-03-19 11:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('50', '1', '2017-03-19 14:13:11', '0', '0', '1', '0', '2', null);
INSERT INTO `camera_push_msg` VALUES ('52', '1', '2017-03-19 11:13:11', '0', '0', '1', '1', '2', null);
INSERT INTO `camera_push_msg` VALUES ('53', '1', '2017-03-19 11:13:11', '0', '0', '1', '1', '2', null);

-- ----------------------------
-- Table structure for store_bills_push_msg
-- ----------------------------
DROP TABLE IF EXISTS `store_bills_push_msg`;
CREATE TABLE `store_bills_push_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `bought_money` varchar(255) DEFAULT NULL,
  `bought_list` varchar(255) DEFAULT NULL,
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store_bills_push_msg
-- ----------------------------
INSERT INTO `store_bills_push_msg` VALUES ('6', '2', '2017-03-19 11:00:00', '10000', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('7', '2', '2017-03-19 11:00:00', '2000', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('4', '2', '2017-03-19 11:00:00', '1000', '[\"猪\",\"鸡\",\"羊\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('5', '2', '2017-03-19 12:00:00', '8000', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('8', '2', '2017-03-19 13:00:00', '4500', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('9', '2', '2017-03-19 13:00:00', '1200', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('10', '2', '2017-03-19 14:00:00', '1800', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('11', '2', '2017-03-19 15:00:00', '1234', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('12', '2', '2017-03-19 16:00:00', '1357', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('13', '2', '2017-03-19 17:00:00', '17894', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('14', '2', '2017-03-19 17:00:00', '12345', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('15', '2', '2017-03-19 18:00:00', '78945', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);
INSERT INTO `store_bills_push_msg` VALUES ('16', '2', '2017-03-19 20:00:00', '43517', '[\"猪\",\"鸡\",\"羊\",\"牛\"]', null);

-- ----------------------------
-- Table structure for store_info
-- ----------------------------
DROP TABLE IF EXISTS `store_info`;
CREATE TABLE `store_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '该门店的名称',
  `business_unit_id` int(11) DEFAULT NULL,
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store_info
-- ----------------------------
INSERT INTO `store_info` VALUES ('1', '隔壁王氏南沙店', '1', null);
INSERT INTO `store_info` VALUES ('2', '隔壁王氏越秀店', '1', null);
INSERT INTO `store_info` VALUES ('3', '隔壁王氏同福里店', '2', null);
INSERT INTO `store_info` VALUES ('4', '大老王接口特卖', '2', null);
INSERT INTO `store_info` VALUES ('5', '大老王接口特卖2店', '2', null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `level` int(255) DEFAULT NULL COMMENT '1为品牌下属，2为事业部下属，3位门店下属',
  `brand_id` int(11) NOT NULL COMMENT '品牌id',
  `business_unit_id` int(11) DEFAULT NULL COMMENT '事业部id',
  `store_id` int(11) DEFAULT NULL COMMENT '门店id',
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'dalaowang', '123', '1', '1', null, null, null);
INSERT INTO `user_info` VALUES ('2', 'huananlaowang', '123', '2', '1', '1', null, null);
INSERT INTO `user_info` VALUES ('3', 'huadonglaowang', '123', '2', '1', '2', null, null);
INSERT INTO `user_info` VALUES ('4', 'nanshalaowang', '123', '3', '1', '1', '1', null);
INSERT INTO `user_info` VALUES ('5', 'root', 'root', '0', '0', '0', '0', '最高权限');
INSERT INTO `user_info` VALUES ('6', 'cheese', '123456', '1', '1', '2', '1', null);
INSERT INTO `user_info` VALUES ('9', 'cheese2', '123456', '2', '1', '1', '1', null);
INSERT INTO `user_info` VALUES ('10', 'cheeseUpdate', '123456', '3', '1', '1', '2', null);

-- ----------------------------
-- Table structure for vip_info
-- ----------------------------
DROP TABLE IF EXISTS `vip_info`;
CREATE TABLE `vip_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `other_label` varchar(1024) DEFAULT NULL COMMENT 'json保存其他自定义字段',
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_info
-- ----------------------------
INSERT INTO `vip_info` VALUES ('1', '小萝莉', '11', '13838384438', '1', '{sex:\"女\"}', null);

-- ----------------------------
-- Table structure for vip_push_msg
-- ----------------------------
DROP TABLE IF EXISTS `vip_push_msg`;
CREATE TABLE `vip_push_msg` (
  `id` int(11) NOT NULL,
  `vip_id` int(11) DEFAULT NULL COMMENT 'vip的id',
  `datetime` datetime DEFAULT NULL COMMENT '到店时间',
  `is_bought` tinyint(4) DEFAULT NULL COMMENT '是否购物',
  `bought_money` varchar(255) DEFAULT NULL,
  `bought_list` varchar(255) DEFAULT NULL COMMENT '购买物品的清单',
  `hold` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_push_msg
-- ----------------------------
