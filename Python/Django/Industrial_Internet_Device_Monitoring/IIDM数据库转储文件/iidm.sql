/*
Navicat MySQL Data Transfer

Source Server         : MySQL80
Source Server Version : 80033
Source Host           : localhost:3306
Source Database       : iidm

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2025-01-10 13:08:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_group
-- ----------------------------
DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_group
-- ----------------------------

-- ----------------------------
-- Table structure for auth_group_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_group_permissions`;
CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_group_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'Can add permission', '1', 'add_permission');
INSERT INTO `auth_permission` VALUES ('2', 'Can change permission', '1', 'change_permission');
INSERT INTO `auth_permission` VALUES ('3', 'Can delete permission', '1', 'delete_permission');
INSERT INTO `auth_permission` VALUES ('4', 'Can view permission', '1', 'view_permission');
INSERT INTO `auth_permission` VALUES ('5', 'Can add group', '2', 'add_group');
INSERT INTO `auth_permission` VALUES ('6', 'Can change group', '2', 'change_group');
INSERT INTO `auth_permission` VALUES ('7', 'Can delete group', '2', 'delete_group');
INSERT INTO `auth_permission` VALUES ('8', 'Can view group', '2', 'view_group');
INSERT INTO `auth_permission` VALUES ('9', 'Can add user', '3', 'add_user');
INSERT INTO `auth_permission` VALUES ('10', 'Can change user', '3', 'change_user');
INSERT INTO `auth_permission` VALUES ('11', 'Can delete user', '3', 'delete_user');
INSERT INTO `auth_permission` VALUES ('12', 'Can view user', '3', 'view_user');
INSERT INTO `auth_permission` VALUES ('13', 'Can add content type', '4', 'add_contenttype');
INSERT INTO `auth_permission` VALUES ('14', 'Can change content type', '4', 'change_contenttype');
INSERT INTO `auth_permission` VALUES ('15', 'Can delete content type', '4', 'delete_contenttype');
INSERT INTO `auth_permission` VALUES ('16', 'Can view content type', '4', 'view_contenttype');
INSERT INTO `auth_permission` VALUES ('17', 'Can add session', '5', 'add_session');
INSERT INTO `auth_permission` VALUES ('18', 'Can change session', '5', 'change_session');
INSERT INTO `auth_permission` VALUES ('19', 'Can delete session', '5', 'delete_session');
INSERT INTO `auth_permission` VALUES ('20', 'Can view session', '5', 'view_session');
INSERT INTO `auth_permission` VALUES ('21', 'Can add industry admin', '6', 'add_industryadmin');
INSERT INTO `auth_permission` VALUES ('22', 'Can change industry admin', '6', 'change_industryadmin');
INSERT INTO `auth_permission` VALUES ('23', 'Can delete industry admin', '6', 'delete_industryadmin');
INSERT INTO `auth_permission` VALUES ('24', 'Can view industry admin', '6', 'view_industryadmin');
INSERT INTO `auth_permission` VALUES ('25', 'Can add industry device', '7', 'add_industrydevice');
INSERT INTO `auth_permission` VALUES ('26', 'Can change industry device', '7', 'change_industrydevice');
INSERT INTO `auth_permission` VALUES ('27', 'Can delete industry device', '7', 'delete_industrydevice');
INSERT INTO `auth_permission` VALUES ('28', 'Can view industry device', '7', 'view_industrydevice');
INSERT INTO `auth_permission` VALUES ('29', 'Can add industry user', '8', 'add_industryuser');
INSERT INTO `auth_permission` VALUES ('30', 'Can change industry user', '8', 'change_industryuser');
INSERT INTO `auth_permission` VALUES ('31', 'Can delete industry user', '8', 'delete_industryuser');
INSERT INTO `auth_permission` VALUES ('32', 'Can view industry user', '8', 'view_industryuser');
INSERT INTO `auth_permission` VALUES ('33', 'Can add log entry', '9', 'add_logentry');
INSERT INTO `auth_permission` VALUES ('34', 'Can change log entry', '9', 'change_logentry');
INSERT INTO `auth_permission` VALUES ('35', 'Can delete log entry', '9', 'delete_logentry');
INSERT INTO `auth_permission` VALUES ('36', 'Can view log entry', '9', 'view_logentry');
INSERT INTO `auth_permission` VALUES ('37', 'Can add device status history', '10', 'add_devicestatushistory');
INSERT INTO `auth_permission` VALUES ('38', 'Can change device status history', '10', 'change_devicestatushistory');
INSERT INTO `auth_permission` VALUES ('39', 'Can delete device status history', '10', 'delete_devicestatushistory');
INSERT INTO `auth_permission` VALUES ('40', 'Can view device status history', '10', 'view_devicestatushistory');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'pbkdf2_sha256$870000$DBaywcOz2HFooIXW0xqfpB$zzQ8i57Rkp8I29/nBKIP8SdCCZyf47+rGo7y17ClPwM=', '2025-01-10 05:02:18.669321', '1', 'chuwu', '', '', '1805948721@qq.com', '1', '1', '2025-01-10 00:59:05.081315');

-- ----------------------------
-- Table structure for auth_user_groups
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_groups`;
CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_user_groups
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_user_permissions`;
CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of auth_user_user_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for django_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `django_admin_log`;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of django_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for django_content_type
-- ----------------------------
DROP TABLE IF EXISTS `django_content_type`;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of django_content_type
-- ----------------------------
INSERT INTO `django_content_type` VALUES ('9', 'admin', 'logentry');
INSERT INTO `django_content_type` VALUES ('2', 'auth', 'group');
INSERT INTO `django_content_type` VALUES ('1', 'auth', 'permission');
INSERT INTO `django_content_type` VALUES ('3', 'auth', 'user');
INSERT INTO `django_content_type` VALUES ('4', 'contenttypes', 'contenttype');
INSERT INTO `django_content_type` VALUES ('10', 'IIDM', 'devicestatushistory');
INSERT INTO `django_content_type` VALUES ('6', 'IIDM', 'industryadmin');
INSERT INTO `django_content_type` VALUES ('7', 'IIDM', 'industrydevice');
INSERT INTO `django_content_type` VALUES ('8', 'IIDM', 'industryuser');
INSERT INTO `django_content_type` VALUES ('5', 'sessions', 'session');

-- ----------------------------
-- Table structure for django_migrations
-- ----------------------------
DROP TABLE IF EXISTS `django_migrations`;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of django_migrations
-- ----------------------------
INSERT INTO `django_migrations` VALUES ('1', 'IIDM', '0001_initial', '2025-01-10 00:46:11.542739');
INSERT INTO `django_migrations` VALUES ('2', 'contenttypes', '0001_initial', '2025-01-10 00:46:11.587499');
INSERT INTO `django_migrations` VALUES ('3', 'contenttypes', '0002_remove_content_type_name', '2025-01-10 00:46:11.631603');
INSERT INTO `django_migrations` VALUES ('4', 'auth', '0001_initial', '2025-01-10 00:46:11.908154');
INSERT INTO `django_migrations` VALUES ('5', 'auth', '0002_alter_permission_name_max_length', '2025-01-10 00:46:11.933944');
INSERT INTO `django_migrations` VALUES ('6', 'auth', '0003_alter_user_email_max_length', '2025-01-10 00:46:11.951347');
INSERT INTO `django_migrations` VALUES ('7', 'auth', '0004_alter_user_username_opts', '2025-01-10 00:46:11.956374');
INSERT INTO `django_migrations` VALUES ('8', 'auth', '0005_alter_user_last_login_null', '2025-01-10 00:46:11.981614');
INSERT INTO `django_migrations` VALUES ('9', 'auth', '0006_require_contenttypes_0002', '2025-01-10 00:46:11.983120');
INSERT INTO `django_migrations` VALUES ('10', 'auth', '0007_alter_validators_add_error_messages', '2025-01-10 00:46:11.988451');
INSERT INTO `django_migrations` VALUES ('11', 'auth', '0008_alter_user_username_max_length', '2025-01-10 00:46:12.015459');
INSERT INTO `django_migrations` VALUES ('12', 'auth', '0009_alter_user_last_name_max_length', '2025-01-10 00:46:12.041247');
INSERT INTO `django_migrations` VALUES ('13', 'auth', '0010_alter_group_name_max_length', '2025-01-10 00:46:12.054432');
INSERT INTO `django_migrations` VALUES ('14', 'auth', '0011_update_proxy_permissions', '2025-01-10 00:46:12.062543');
INSERT INTO `django_migrations` VALUES ('15', 'auth', '0012_alter_user_first_name_max_length', '2025-01-10 00:46:12.100794');
INSERT INTO `django_migrations` VALUES ('16', 'sessions', '0001_initial', '2025-01-10 00:46:12.118663');
INSERT INTO `django_migrations` VALUES ('17', 'IIDM', '0002_alter_industrydevice_device_id_devicestatushistory', '2025-01-10 01:38:23.166442');
INSERT INTO `django_migrations` VALUES ('18', 'admin', '0001_initial', '2025-01-10 01:38:23.277329');
INSERT INTO `django_migrations` VALUES ('19', 'admin', '0002_logentry_remove_auto_add', '2025-01-10 01:38:23.286725');
INSERT INTO `django_migrations` VALUES ('20', 'admin', '0003_logentry_add_action_flag_choices', '2025-01-10 01:38:23.297781');
INSERT INTO `django_migrations` VALUES ('21', 'IIDM', '0003_industryuser_is_staff', '2025-01-10 02:50:26.616129');
INSERT INTO `django_migrations` VALUES ('22', 'IIDM', '0004_remove_industryuser_is_staff', '2025-01-10 02:52:33.799719');

-- ----------------------------
-- Table structure for django_session
-- ----------------------------
DROP TABLE IF EXISTS `django_session`;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of django_session
-- ----------------------------
INSERT INTO `django_session` VALUES ('itih5hlcnj7wul1on8dq4ojx803fx7ue', '.eJxVjEsOgzAMRO_iNYpifklZds8ZkB27Df0ECYi6qHr3igpVYjea92bekBedh1GgK4tfTvRU6CDE_MroLRQwUF7j8PcAjx1TuGvagNwoXScTprTOI5tNMTtdTD-JPs67eziItMRtXWIQG7BmK06d905Qm7Z2zI3U6Fr26J1F1lBhxVQqnSpPpF5IpLnA5wvzFUNe:1tW7A2:0RgeCqLEOoAi97Cv1jSNAzBaIWYq9f5uipZY65V7VG0', '2025-01-24 05:02:18.687274');

-- ----------------------------
-- Table structure for iidm_devicestatushistory
-- ----------------------------
DROP TABLE IF EXISTS `iidm_devicestatushistory`;
CREATE TABLE `iidm_devicestatushistory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `state` varchar(10) NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `device_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IIDM_devicestatushis_device_id_e150427f_fk_IIDM_indu` (`device_id`),
  CONSTRAINT `IIDM_devicestatushis_device_id_e150427f_fk_IIDM_indu` FOREIGN KEY (`device_id`) REFERENCES `iidm_industrydevice` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of iidm_devicestatushistory
-- ----------------------------

-- ----------------------------
-- Table structure for iidm_industryadmin
-- ----------------------------
DROP TABLE IF EXISTS `iidm_industryadmin`;
CREATE TABLE `iidm_industryadmin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `admin_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_name` (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of iidm_industryadmin
-- ----------------------------

-- ----------------------------
-- Table structure for iidm_industrydevice
-- ----------------------------
DROP TABLE IF EXISTS `iidm_industrydevice`;
CREATE TABLE `iidm_industrydevice` (
  `device_id` varchar(100) NOT NULL,
  `device_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `device_state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `device_update_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of iidm_industrydevice
-- ----------------------------
INSERT INTO `iidm_industrydevice` VALUES ('1', '111', '离线', '2025-01-10 04:07:47.879122');
INSERT INTO `iidm_industrydevice` VALUES ('2', '2', '在线', '2025-01-10 01:30:21.535468');
INSERT INTO `iidm_industrydevice` VALUES ('3', '2', '在线', '2025-01-10 01:26:00.894942');
INSERT INTO `iidm_industrydevice` VALUES ('4', 'qwe', '在线', '2025-01-10 01:26:12.548597');
INSERT INTO `iidm_industrydevice` VALUES ('5', '55', '在线', '2025-01-10 02:53:11.089973');

-- ----------------------------
-- Table structure for iidm_industryuser
-- ----------------------------
DROP TABLE IF EXISTS `iidm_industryuser`;
CREATE TABLE `iidm_industryuser` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telephone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of iidm_industryuser
-- ----------------------------
INSERT INTO `iidm_industryuser` VALUES ('2', 'chuwu180', 'chuwu180', '1112');
