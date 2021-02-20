DROP TABLE IF EXISTS `phoenix_user`;
CREATE TABLE `phoenix_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL COMMENT '加密后的密码',
  `salt` VARCHAR(32) NOT NULL COMMENT '加密使用的盐',
  `email` VARCHAR(32) NOT NULL UNIQUE COMMENT '邮箱',
  `phone_number` VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号码',
  `status` INT(2) NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建时间',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '上次登录时间',
  `last_update_time` DATETIME DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='phoenix用户示例表';


DROP TABLE IF EXISTS `info_collection`;
CREATE TABLE `info_collection` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `username` VARCHAR(32) NOT NULL COMMENT '联系人',
  `organization` VARCHAR(128) NOT NULL COMMENT '单位名称',
  `province_code` VARCHAR(32) NOT NULL COMMENT '省编码',
  `province` VARCHAR(128) NOT NULL COMMENT '省',
  `city` VARCHAR(128) NOT NULL COMMENT '市',
  `district` VARCHAR(128) NOT NULL COMMENT '区',
  `address` VARCHAR(128) NOT NULL COMMENT '单位地址',
  `email` VARCHAR(32) NOT NULL COMMENT '邮箱',
  `phone_number` VARCHAR(32) NOT NULL COMMENT '手机号码',
  `comment` VARCHAR(128) NOT NULL COMMENT '备注',
  `product_type` VARCHAR(32) NOT NULL COMMENT '产品类型waf,ddos',
  `sync_status` tinyint NOT NULL DEFAULT -1 COMMENT '是否同步过1同步过，0未同步',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息收集表';

CREATE UNIQUE INDEX ic_phone_pt_index ON info_collection(phone_number,product_type);


