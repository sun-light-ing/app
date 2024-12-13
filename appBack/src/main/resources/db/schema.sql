-- 用户表
CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
    `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE `role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL COMMENT '角色名称',
    `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关联表
CREATE TABLE `user_role` (
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 预约表
CREATE TABLE `appointment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `appointment_time` datetime NOT NULL COMMENT '预约时间',
    `description` varchar(500) DEFAULT NULL COMMENT '预约描述',
    `status` tinyint(4) DEFAULT '0' COMMENT '状态：0-待确认，1-已确认，2-已取消',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_appointment_time` (`appointment_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

-- 初始化角色数据
INSERT INTO `role` (`name`, `description`) VALUES
('ROLE_ADMIN', '管理员'),
('ROLE_USER', '普通用户');

-- 初始化管理员用户（密码为：123456）
INSERT INTO `user` (`username`, `password`, `email`, `status`) VALUES
('admin', '$2a$10$X/hX5k2XZQFwOBMv5a5Qz.RpyBsm2VqOOjnXFGfH5R1.9qZGGj9.q', 'admin@example.com', 1);

-- 设置管理员角色
INSERT INTO `user_role` (`user_id`, `role_id`) 
SELECT u.id, r.id FROM `user` u, `role` r 
WHERE u.username = 'admin' AND r.name = 'ROLE_ADMIN'; 