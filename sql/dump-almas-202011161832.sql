-- MySQL dump 10.13  Distrib 5.7.30, for Win64 (x86_64)
--
-- Host: localhost    Database: almas
-- ------------------------------------------------------
-- Server version	5.7.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `y_sys_config`
--

DROP TABLE IF EXISTS `y_sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_config` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置名称',
  `label` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置键',
  `value` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置值',
  `remark` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配置说明',
  `disable` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 0:否 1:是',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_config`
--

LOCK TABLES `y_sys_config` WRITE;
/*!40000 ALTER TABLE `y_sys_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_dept`
--

DROP TABLE IF EXISTS `y_sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_dept` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `parent_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '父部门id',
  `assembly` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门集合体',
  `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构代号',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构名称',
  `logo` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序 数字越大越靠前',
  `disable` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 0:否 1:是',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_dept`
--

LOCK TABLES `y_sys_dept` WRITE;
/*!40000 ALTER TABLE `y_sys_dept` DISABLE KEYS */;
INSERT INTO `y_sys_dept` VALUES (990,343,'','0','根目录','',0,1,0,'admin','2020-11-10 18:11:28','admin','2020-11-10 18:11:28'),(1326105807449759746,0,'','10000','研发部录','',1,0,0,'admin','2020-11-10 18:13:57','admin','2020-11-10 18:13:57'),(1326730031386353665,0,'','10001','测试中心','',1,0,0,'admin','2020-11-12 11:34:24','admin','2020-11-12 11:34:24'),(1326730090924498946,0,'','10002','运营中心','',1,0,0,'admin','2020-11-12 11:34:38','admin','2020-11-12 11:34:38'),(1326730215449190402,1326730090924498946,'','10003','商城运营','',1,0,0,'admin','2020-11-12 11:35:07','admin','2020-11-12 11:35:07'),(1326730263557857281,1326730090924498946,'','10004','OA运营','',1,0,0,'admin','2020-11-12 11:35:19','admin','2020-11-12 11:35:19');
/*!40000 ALTER TABLE `y_sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_dict`
--

DROP TABLE IF EXISTS `y_sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_dict` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `parent_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '字典组id',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '字典类型 1:字典组 2:字典属性',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典名称',
  `label` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典标签',
  `value` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `extend` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展',
  `checked` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否默认 0:否 1:是',
  `disable` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否禁用 0:否 1:是',
  `sort` int(11) DEFAULT NULL COMMENT '排序 数字越大越靠前',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注 0:否 1:是',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_dict`
--

LOCK TABLES `y_sys_dict` WRITE;
/*!40000 ALTER TABLE `y_sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_menu`
--

DROP TABLE IF EXISTS `y_sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_menu` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `parent_id` bigint(32) NOT NULL COMMENT '父级id',
  `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限代码',
  `type` tinyint(2) NOT NULL COMMENT '菜单类型 1:菜单 2:按钮',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单项标题',
  `icon` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标 className',
  `path` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单项标识 可以是路由path',
  `badge` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '徽标配置',
  `target` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单打开页面方式 route 路由打开、href 链接打开、blank 新窗口打开链接、event 触发事件',
  `batch` tinyint(2) DEFAULT NULL COMMENT '是否分组 0:否 1:是',
  `title` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组标题',
  `remark` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `visible` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1' COMMENT '是否可见 0:否 1:是',
  `disabled` tinyint(2) NOT NULL DEFAULT '1' COMMENT '禁用状态 0:否 1:是',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `sort` int(11) DEFAULT NULL COMMENT '排序 数字越大越靠前',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_menu`
--

LOCK TABLES `y_sys_menu` WRITE;
/*!40000 ALTER TABLE `y_sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_role`
--

DROP TABLE IF EXISTS `y_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_role` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色代码',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序 数字越大越靠前',
  `scope_type` tinyint(2) NOT NULL DEFAULT '2' COMMENT '数据权限范围类型 1:自定义 2:全部数据 3:本部门及下属部门 4:仅本部门',
  `data_scope` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据权限范围',
  `disable` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 0:否 1:是',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_role`
--

LOCK TABLES `y_sys_role` WRITE;
/*!40000 ALTER TABLE `y_sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_role_menu`
--

DROP TABLE IF EXISTS `y_sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_role_menu` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `role_id` bigint(32) NOT NULL COMMENT '角色id',
  `menu_id` bigint(32) NOT NULL COMMENT '菜单id',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色-权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_role_menu`
--

LOCK TABLES `y_sys_role_menu` WRITE;
/*!40000 ALTER TABLE `y_sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_user`
--

DROP TABLE IF EXISTS `y_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_user` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `dept_id` bigint(32) NOT NULL COMMENT '机构id 可同时属多部门',
  `user_account` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `cipher` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '暗号',
  `user_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户类型 0:管理员 1:普通会员 2:游客',
  `nickname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别 1:男 2:女 3:未知',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `email` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `remark` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `register_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册ip',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后登录ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `disable` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 0:否 1:是',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_user_account` (`user_account`),
  UNIQUE KEY `UNIQUE_email` (`email`),
  UNIQUE KEY `UNIQUE_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_user`
--

LOCK TABLES `y_sys_user` WRITE;
/*!40000 ALTER TABLE `y_sys_user` DISABLE KEYS */;
INSERT INTO `y_sys_user` VALUES (1314765829839118338,0,'1',NULL,NULL,1,'admin',1,'2020-12-29','138000000000','123',NULL,NULL,NULL,'2020-12-29 12:23:01',NULL,NULL,1,0,'admin','2020-10-10 11:12:56','admin','2020-10-10 11:12:56'),(1323547988452290562,0,'2',NULL,NULL,1,'',1,NULL,'','wewe@qq.com','','',NULL,'2020-12-29 12:23:01',NULL,NULL,1,0,'admin','2020-11-03 16:50:05','admin','2020-11-03 16:50:05'),(1323550088041111554,0,'001',NULL,NULL,1,'234',1,'2020-12-29','001','001@qq.com','','',NULL,'2020-12-29 12:23:01',NULL,'2020-12-29 12:23:01',1,0,'admin','2020-11-03 16:58:26','admin','2020-11-13 15:00:24'),(1323556112684855297,0,'4',NULL,NULL,1,'',1,NULL,'15111','zhang001@qq.com','','',NULL,'2020-12-29 12:23:01',NULL,NULL,1,0,'admin','2020-10-03 17:22:22','admin','2020-11-03 17:22:22'),(1323557672529764353,0,'5',NULL,NULL,1,'大锅饭',1,'2020-12-29','15112','zhang002@qq.com','','',NULL,'2020-12-29 12:23:01',NULL,NULL,1,0,'admin','2020-11-03 17:28:34','admin','2020-11-13 15:01:16'),(1323559350817595393,0,'6',NULL,NULL,1,'',1,NULL,'151123','zhang003@qq.com','','',NULL,'2020-12-29 12:23:01','129.4.32.1',NULL,1,1,'admin','2020-11-01 17:35:14','admin','2020-11-03 17:35:14'),(1324274948841213954,0,'zhangsa004',NULL,NULL,1,'',2,'2020-12-29','151124','zhang004@qq.com','','',NULL,'2020-12-29 12:23:01',NULL,NULL,1,0,'admin','2020-11-05 16:58:46','admin','2020-11-05 16:58:46'),(1326825146184454146,0,'1erttre',NULL,NULL,1,'1',2,NULL,'1','1129503415@qq.com',NULL,NULL,NULL,'2020-12-29 12:23:01',NULL,NULL,0,0,'admin','2020-11-12 17:52:21','admin','2020-11-12 17:52:21');
/*!40000 ALTER TABLE `y_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_sys_user_role`
--

DROP TABLE IF EXISTS `y_sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `y_sys_user_role` (
  `id` bigint(32) NOT NULL COMMENT '主键 唯一ID',
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  `role_id` bigint(32) NOT NULL COMMENT '角色id',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0:否 1:是',
  `updated_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户-角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_sys_user_role`
--

LOCK TABLES `y_sys_user_role` WRITE;
/*!40000 ALTER TABLE `y_sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'almas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-16 18:32:54
