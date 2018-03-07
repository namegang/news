/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : news

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-03-07 23:24:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '默认分类', 'default', '默认分类');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user` int(10) DEFAULT NULL COMMENT '用户',
  `text` varchar(255) NOT NULL COMMENT '内容',
  `issuedate` date DEFAULT NULL COMMENT '提交时间',
  `news` int(10) DEFAULT NULL COMMENT '对应文章',
  `support` int(10) DEFAULT '0' COMMENT '赞',
  `contain` int(10) DEFAULT '0' COMMENT '对应评论',
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `news` (`news`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `publishDate` datetime(6) DEFAULT NULL COMMENT '发布时间',
  `createDate` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `createUserId` int(11) DEFAULT NULL COMMENT '对应创建用户',
  `imgUrl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `content` text COMMENT '内容',
  `clickCount` int(11) DEFAULT NULL COMMENT '点击次数',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `categoryId` int(11) NOT NULL COMMENT '分类id',
  `enddate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for syspower
-- ----------------------------
DROP TABLE IF EXISTS `syspower`;
CREATE TABLE `syspower` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '显示名称',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `pid` int(10) unsigned zerofill NOT NULL COMMENT '父ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syspower
-- ----------------------------
INSERT INTO `syspower` VALUES ('1', '系统管理', '', 'fa fa-sun-o', '0', '', '0', '0000000000');
INSERT INTO `syspower` VALUES ('2', '新闻管理', '', 'fa fa-file-o', '0', '', '0', '0000000000');
INSERT INTO `syspower` VALUES ('3', '角色管理', 'sysRole/index', 'fa fa-file-o', '0', '', '1', '0000000001');
INSERT INTO `syspower` VALUES ('4', '功能权限', 'sysPower/index', 'fa fa-file-o', '0', '', '1', '0000000001');
INSERT INTO `syspower` VALUES ('5', '用户管理', 'sysUser/index', 'fa fa-file-o', '0', '', '1', '0000000001');
INSERT INTO `syspower` VALUES ('6', '新闻管理', 'news/index', 'fa fa-file-o', '0', '', '1', '0000000002');
INSERT INTO `syspower` VALUES ('7', '分类管理', 'category/index', 'fa fa-file-o', '0', null, '1', '0000000002');
INSERT INTO `syspower` VALUES ('8', '评论管理', 'comments/index', 'fa fa-file-o', '0', null, '1', '0000000002');
INSERT INTO `syspower` VALUES ('9', '投票管理', '', 'fa fa-file-o', '0', null, '0', '0000000000');
INSERT INTO `syspower` VALUES ('10', '投票活动管理', 'vote/index', 'fa fa-file-o', '0', null, '1', '0000000009');
INSERT INTO `syspower` VALUES ('11', '投票子项管理', 'vote/subIndex', 'fa fa-file-o', '0', null, '1', '0000000009');
INSERT INTO `syspower` VALUES ('12', '投票分析管理', 'vote/analysisIndex', 'fa fa-file-o', '0', null, '1', '0000000009');

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES ('1', '超级管理员', '啥权限都有哈');
INSERT INTO `sysrole` VALUES ('2', '一级管理员', '审核权限\r\n');
INSERT INTO `sysrole` VALUES ('3', '二级管理员', '发布新闻');
INSERT INTO `sysrole` VALUES ('4', '普通用户', '浏览查看文章，投票');

-- ----------------------------
-- Table structure for sysrolepower
-- ----------------------------
DROP TABLE IF EXISTS `sysrolepower`;
CREATE TABLE `sysrolepower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `powerId` varchar(50) DEFAULT NULL,
  `roleId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrolepower
-- ----------------------------
INSERT INTO `sysrolepower` VALUES ('1', '1', '1');
INSERT INTO `sysrolepower` VALUES ('2', '2', '1');
INSERT INTO `sysrolepower` VALUES ('3', '3', '1');
INSERT INTO `sysrolepower` VALUES ('4', '4', '1');
INSERT INTO `sysrolepower` VALUES ('5', '5', '1');
INSERT INTO `sysrolepower` VALUES ('6', '6', '1');
INSERT INTO `sysrolepower` VALUES ('7', '7', '1');
INSERT INTO `sysrolepower` VALUES ('8', '8', '1');
INSERT INTO `sysrolepower` VALUES ('9', '9', '1');
INSERT INTO `sysrolepower` VALUES ('10', '10', '1');
INSERT INTO `sysrolepower` VALUES ('11', '11', '1');
INSERT INTO `sysrolepower` VALUES ('12', '12', '1');

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `roleId` varchar(50) DEFAULT NULL COMMENT '对应角色',
  `state` int(1) unsigned zerofill DEFAULT NULL COMMENT '状态',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `grava` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', 'sysadmin', 'e531245b04ddbae066d3c2b9fb1af988', '5', '0', '', null);

-- ----------------------------
-- Table structure for uservote
-- ----------------------------
DROP TABLE IF EXISTS `uservote`;
CREATE TABLE `uservote` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `voteid` int(11) NOT NULL COMMENT '投票id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uservote
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `level` int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT '层级',
  `vote` int(11) NOT NULL COMMENT '票数\r\n',
  `pid` int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT '上级投票项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
