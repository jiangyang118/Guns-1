
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 系统后台管理员
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `email` varchar(255) DEFAULT NULL COMMENT 'email',
  `is_enabled` bit(1) NOT NULL COMMENT '是否可用，0-可用，1-不可用',
  `is_locked` bit(1) NOT NULL COMMENT '是否锁定，0-锁定，1-没有锁定',
  `lock_key` varchar(255) NOT NULL COMMENT '锁的key',
  `locked_date` datetime DEFAULT NULL COMMENT '锁定日期',
  `login_date` datetime DEFAULT NULL COMMENT '登录日期',
  `login_failure_count` int(11) NOT NULL COMMENT '登录失败次数',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `organization` bigint(20) DEFAULT NULL COMMENT '组织机构',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_admin_username` (`username`) USING BTREE,
  KEY `FK_d9hesdejix49q0ayawccnhc60` (`organization`) USING BTREE,
  CONSTRAINT `sys_admin_ibfk_1` FOREIGN KEY (`organization`) REFERENCES `sys_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统后台管理员';

-- ----------------------------
-- 组织机构
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `grade` int(11) DEFAULT NULL COMMENT '等级',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `tree_path` varchar(255) DEFAULT NULL COMMENT '树形结构',
  `parent` bigint(20) DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`id`),
  KEY `FK_kxj8p1ptqoy0yviop0027p3fj` (`parent`) USING BTREE,
  CONSTRAINT `sys_organization_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `sys_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='组织机构';


-- ----------------------------
-- 店铺基本信息（包含餐饮商、供应商的店铺）
-- ----------------------------
DROP TABLE IF EXISTS `str_store_base`;
CREATE TABLE `str_store_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `begin_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime  DEFAULT NULL COMMENT '结束日期',
  `appointer` bigint(20) NOT NULL  COMMENT '任命者',
  `assignee1` bigint(20) NOT NULL  COMMENT '执法者1',
  `assignee2` bigint(20) NOT NULL  COMMENT '执法者2',
  `store` bigint(20) NOT NULL  COMMENT '被检查的店铺',
  `sn` varchar(255) DEFAULT NULL COMMENT '任务编号',
  `status` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格',
  `noti_status` tinyint(2)  DEFAULT '0' NOT NULL COMMENT '通知被检查店铺的状态：0-未通知；1-已通知',
  PRIMARY KEY (`id`),
  KEY `FK_appointer` (`appointer`) USING BTREE,
  KEY `FK_assignee1` (`assignee1`) USING BTREE,
  KEY `FK_assignee2` (`assignee2`) USING BTREE,
  KEY `FK_store` (`store`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务基本信息';

-- ----------------------------
-- 任务详细信息
-- ----------------------------
DROP TABLE IF EXISTS `job_task_detail`;
CREATE TABLE `job_task_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `task_id` bigint(20) NOT NULL  COMMENT '任务基本信息id',
  `withdrawal` tinyint(2)   DEFAULT '0' NOT NULL COMMENT '是否回避，0-回避；1-不回避',
  `assignee_sign` varchar(255) DEFAULT NULL COMMENT '执法者签名',
  `store_sign` varchar(255) DEFAULT NULL COMMENT '被检查者签名',
  `item` varchar(1000) DEFAULT NULL COMMENT '检查项详情',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_task_id` (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务详细信息';