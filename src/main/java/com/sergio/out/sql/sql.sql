DROP TABLE IF EXISTS `girl_friend`;
CREATE TABLE `girl_friend` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `account_id` varchar(36) NOT NULL COMMENT 'account id',
  `name` varchar(36) NOT NULL COMMENT 'name',
  `age` int(11) NOT NULL COMMENT 'age',
  `mobile` int(11) NOT NULL COMMENT 'mobile',
  `create_time` datetime NOT NULL,
  `create_by` varchar(36) NOT NULL,
  `modify_time` datetime NOT NULL,
  `modify_by` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;