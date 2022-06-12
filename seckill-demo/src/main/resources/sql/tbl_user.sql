DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`
(
    `id`              char(11) COLLATE utf8mb4_general_ci                           NOT NULL COMMENT '主键（手机号码）',
    `nickname`        varchar(255) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '昵称',
    `password`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（两次加密）',
    `salt`            varchar(10) COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '盐（密码加密）',
    `portrait`        varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
    `register_time`   datetime                                DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `login_last_time` datetime                                DEFAULT NULL COMMENT '最后一次登录时间',
    `login_count`     int                                     DEFAULT '0' COMMENT '登录次数',
    `delete_flag`     tinyint(1)                              DEFAULT '0' COMMENT '删除标识（0 未删除，1 已删除）',
    `create_time`     datetime                                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息表';