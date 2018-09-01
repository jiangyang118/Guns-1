
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 店铺基本信息（包含餐饮商、供应商的店铺）
-- ----------------------------
DROP TABLE IF EXISTS `str_store_base`;
CREATE TABLE `str_store_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `store_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '店铺监管类型,0供应商,1餐饮商',
  `name` varchar(255) NOT NULL COMMENT '店铺门头名称',
  `address` varchar(255) DEFAULT NULL COMMENT '生产经营地址',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '店铺电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='店铺';


-- ----------------------------
-- 任务基本信息
-- ----------------------------
DROP TABLE IF EXISTS `job_task_base`;
CREATE TABLE `job_task_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL COMMENT '版本号',
  `begin_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime  DEFAULT NULL COMMENT '结束日期',
  `appointer` int(11) NOT NULL  COMMENT '任命者',
  `store` int(11) NOT NULL  COMMENT '被检查的店铺',
  `sn` varchar(255) DEFAULT NULL COMMENT '任务编号',
  `type` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '任务类型',
  `status` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格',
  `noti_status` tinyint(2)  DEFAULT '0' NOT NULL COMMENT '通知被检查店铺的状态：0-未通知；1-已通知',
  PRIMARY KEY (`id`),
  KEY `FK_appointer` (`appointer`) USING BTREE,
  KEY `FK_store` (`store`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务基本信息';

-- ----------------------------
-- 任务接受者
-- ----------------------------
DROP TABLE IF EXISTS `job_task_assignee`;
CREATE TABLE `job_task_assignee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL COMMENT '版本号',
  `assignee` int(11) NOT NULL COMMENT '任务执行者',
  `task_id` int(11) NOT NULL  COMMENT '任务基本信息id',
  `receive` tinyint(2) NOT NULL  DEFAULT '0' COMMENT '是否接收，0-未查看；1-已接受；2-未接受',
  `assignee_sign` varchar(255) DEFAULT NULL COMMENT '执法者签名',
  PRIMARY KEY (`id`),
  KEY `FK_task_id` (`task_id`) USING BTREE,
  KEY `FK_assignee` (`assignee`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT=' 任务接受者';

-- ----------------------------
-- 任务详细信息
-- ----------------------------
DROP TABLE IF EXISTS `job_task_detail`;
CREATE TABLE `job_task_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL COMMENT '版本号',
  `task_id` int(11) NOT NULL  COMMENT '任务基本信息id',
  `withdrawal` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '是否回避，0-回避；1-不回避',
  `store_sign` varchar(255) DEFAULT NULL COMMENT '被检查者签名',
  `item` varchar(1000) DEFAULT NULL COMMENT '检查项详情',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_task_id` (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务详细信息';

-- ----------------------------
-- 检查项信息
-- ----------------------------
DROP TABLE IF EXISTS `job_examine`;
CREATE TABLE `job_examine` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL COMMENT '版本号',
  `type` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '任务类型（检查类型）',
  `orders` int(11) NOT NULL DEFAULT 1 COMMENT '排序序号',
  `parent` int(11)  NULL  COMMENT '父节点',
  `code` varchar(255) DEFAULT NULL COMMENT '检查项的显示序号，例如1；1.1；1.2.1；',
  `key` tinyint(2) NOT NULL  DEFAULT '0' COMMENT '是否重点检查项，0-不是，1-是',
  `deprecate` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '是否废弃，0-不废弃；1-废弃',
  `item` varchar(1000) DEFAULT NULL COMMENT '检查项每条详情内容',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='检查项信息';