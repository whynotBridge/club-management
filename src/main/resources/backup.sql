-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: club_management
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT COMMENT '活动主键',
  `club_id` int NOT NULL COMMENT '社团ID',
  `theme` varchar(16) NOT NULL COMMENT '活动主题',
  `description` text COMMENT '活动描述',
  `start_time` timestamp NOT NULL COMMENT '开始时间',
  `end_time` timestamp NOT NULL COMMENT '结束时间',
  `location` varchar(16) NOT NULL COMMENT '活动场地',
  `amount` decimal(10,2) NOT NULL COMMENT '活动缴费',
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `theme` (`theme`),
  KEY `idx_club_id` (`club_id`) COMMENT '社团ID索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'篮球比赛','与其他社团进行友谊赛','2024-04-05 02:00:00','2024-04-05 04:00:00','体育馆',50.00),(2,2,'经典作品交流会','分享阅读心得，推荐好书','2024-04-30 07:00:00','2024-04-30 09:00:00','图书馆',5.00),(3,3,'音乐会','展示社团成员的音乐才华','2024-04-10 10:00:00','2024-04-10 13:00:00','音乐厅',30.00),(4,4,'舞蹈表演','展示社团成员的舞蹈能力','2024-04-20 05:00:00','2024-04-20 07:00:00','华山操场',7.00),(5,5,'华农KPL','五五开黑节，校内KPL','2024-04-25 11:00:00','2024-04-25 13:00:00','活动交流中心',30.00),(6,6,'美术展览','展示社团成员的艺术作品','2024-04-15 06:00:00','2024-04-15 09:00:00','美术馆',20.00),(7,7,'志愿者活动','参与社区清洁活动','2024-05-05 01:00:00','2024-05-05 04:00:00','五山社区公园',3.00);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_participation`
--

DROP TABLE IF EXISTS `activity_participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_participation` (
  `participation_Id` int NOT NULL AUTO_INCREMENT COMMENT '活动参与表ID',
  `activity_id` int NOT NULL COMMENT '活动ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `is_signed` tinyint(1) NOT NULL COMMENT '是否签到',
  PRIMARY KEY (`participation_Id`),
  KEY `idx_aid_uid` (`activity_id`,`user_id`) COMMENT '活动id-用户id索引'
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_participation`
--

LOCK TABLES `activity_participation` WRITE;
/*!40000 ALTER TABLE `activity_participation` DISABLE KEYS */;
INSERT INTO `activity_participation` VALUES (1,1,12,0),(2,2,9,0),(3,3,7,0),(4,4,17,0),(5,5,1,0),(6,6,8,0),(7,7,2,0),(8,1,14,0),(9,1,19,0),(10,1,18,0),(11,1,21,0),(12,1,6,0),(13,2,10,0),(14,2,21,0),(15,2,22,0),(16,2,29,0),(17,3,23,0),(18,3,25,0),(19,3,29,0),(20,3,15,0),(21,4,18,0),(22,4,20,0),(23,4,24,0),(24,4,19,0),(25,5,4,0),(26,5,28,0),(27,5,24,0),(28,5,18,0),(29,6,5,0),(30,6,17,0),(31,6,27,0),(32,6,24,0),(33,7,7,0),(34,7,26,0),(35,7,20,0),(36,7,25,0);
/*!40000 ALTER TABLE `activity_participation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员主键',
  `username` varchar(32) NOT NULL COMMENT '管理员名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(24) DEFAULT NULL COMMENT '邮箱',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123456','admin@example.com','2024-04-04 07:34:48');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `club_id` int NOT NULL AUTO_INCREMENT COMMENT '社团主键',
  `club_name` varchar(16) NOT NULL COMMENT '社团名称',
  `description` text NOT NULL COMMENT '简介描述',
  `contact_info` varchar(64) NOT NULL COMMENT '联系方式',
  `activity_space` varchar(32) NOT NULL COMMENT '活动场地',
  `president_id` int NOT NULL COMMENT '社长ID',
  PRIMARY KEY (`club_id`),
  KEY `idx_president_id` (`president_id`) COMMENT '社长ID索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'篮球俱乐部','我们是一个热爱篮球的团体，不论你是初学者还是高手，都欢迎加入我们的大家庭！','basketball_club@example.com','体育馆',12),(2,'读书会','欢迎喜欢阅读的同学加入我们的读书会，每月举办书评交流和阅读分享活动，一起探讨文学之美！','bookclub@example.com','图书馆',9),(3,'音乐会','我们是一个热爱乐曲的社团，致力于分享自己喜欢的音乐！','environment_association@example.com','校园周边',7),(4,'舞蹈团','舞蹈团是一个让舞者展现自我风采的舞台，无论你是街舞还是古典舞的爱好者，都欢迎加入我们！','dance_group@example.com','舞蹈室',17),(5,'游戏社','游戏社是一个汇聚游戏爱好者的集体，我们举办各类游戏比赛和主题活动，让游戏之乐无处不在！','gaming_club@example.com','游戏厅',1),(6,'艺术团','艺术团是一个展示学生才艺的平台，我们举办音乐会、舞台剧等各类文艺活动，欢迎喜欢表演的同学加入！','art_group@example.com','多功能厅',8),(7,'志愿者协会','志愿者协会是一个关爱社会、热心公益的团体，我们定期组织各类公益活动，积极参与社区建设！','volunteer_association@example.com','社区中心',2);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_application`
--

DROP TABLE IF EXISTS `club_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_application` (
  `application_id` int NOT NULL AUTO_INCREMENT COMMENT '社团申请表主键',
  `president_id` int NOT NULL COMMENT '社长ID',
  `club_name` varchar(32) NOT NULL COMMENT '社团名称',
  `description` text NOT NULL COMMENT '简介描述',
  `contact_info` varchar(32) NOT NULL COMMENT '联系方式',
  `activity_space` varchar(32) NOT NULL COMMENT '活动场地',
  `status` enum('apply','agree','reject') DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`application_id`),
  UNIQUE KEY `club_name` (`club_name`),
  KEY `idx_club_status` (`status`) COMMENT '审核状态索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_application`
--

LOCK TABLES `club_application` WRITE;
/*!40000 ALTER TABLE `club_application` DISABLE KEYS */;
INSERT INTO `club_application` VALUES (1,24,'编程爱好者','致力于提高编程技能和解决问题能力的社团','code_club@example.com','图书馆一楼','apply'),(2,4,'摄影协会','记录美好瞬间，分享摄影技巧和作品的社团','photoclub@example.com','校园各处','apply');
/*!40000 ALTER TABLE `club_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `fee_id` int NOT NULL AUTO_INCREMENT COMMENT '费用主键',
  `activity_id` int NOT NULL COMMENT '活动ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `amount` decimal(10,2) NOT NULL COMMENT '缴费金额',
  `is_paid` tinyint(1) NOT NULL COMMENT '缴费状态',
  PRIMARY KEY (`fee_id`),
  KEY `idx_aid_uid` (`activity_id`,`user_id`) COMMENT '活动id-用户id索引'
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (1,1,12,50.00,0),(2,2,9,5.00,0),(3,3,7,30.00,0),(4,4,17,7.00,0),(5,5,1,30.00,0),(6,6,8,20.00,0),(7,7,2,3.00,0),(8,1,14,50.00,0),(9,1,19,50.00,0),(10,1,18,50.00,0),(11,1,21,50.00,0),(12,1,6,50.00,0),(13,2,10,5.00,0),(14,2,21,5.00,0),(15,2,22,5.00,0),(16,2,29,5.00,0),(17,3,23,30.00,0),(18,3,25,30.00,0),(19,3,29,30.00,0),(20,3,15,30.00,0),(21,4,18,7.00,0),(22,4,20,7.00,0),(23,4,24,7.00,0),(24,4,19,7.00,0),(25,5,4,30.00,0),(26,5,28,30.00,0),(27,5,24,30.00,0),(28,5,18,30.00,0),(29,6,5,20.00,0),(30,6,17,20.00,0),(31,6,27,20.00,0),(32,6,24,20.00,0),(33,7,7,3.00,0),(34,7,26,3.00,0),(35,7,20,3.00,0),(36,7,25,3.00,0);
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT COMMENT '成员主键',
  `user_id` int NOT NULL COMMENT '用户ID',
  `club_id` int NOT NULL COMMENT '社团ID',
  `position` enum('alreadyQuit','applyQuit','applyJoin','member','cadreMan','vicePresident','president') NOT NULL COMMENT '社团职位/状态',
  `join_date` timestamp NOT NULL COMMENT '加入时间',
  PRIMARY KEY (`member_id`),
  KEY `idx_user_id` (`user_id`) COMMENT '用户id索引'
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,12,1,'president','2022-07-31 16:00:00'),(2,9,2,'president','2023-06-30 16:00:00'),(3,7,3,'president','2023-05-31 16:00:00'),(4,17,4,'president','2022-12-31 16:00:00'),(5,1,5,'president','2023-04-30 16:00:00'),(6,8,6,'president','2023-02-28 16:00:00'),(7,2,7,'president','2023-09-30 16:00:00'),(8,11,1,'member','2023-11-01 16:00:00'),(9,14,1,'applyJoin','2023-03-22 16:00:00'),(10,16,1,'applyJoin','2023-06-13 16:00:00'),(11,19,1,'cadreMan','2023-12-27 16:00:00'),(12,18,1,'member','2023-09-09 16:00:00'),(13,21,1,'vicePresident','2023-01-20 16:00:00'),(14,6,1,'member','2023-06-21 16:00:00'),(15,22,1,'applyQuit','2023-09-04 16:00:00'),(16,7,2,'applyQuit','2023-09-27 16:00:00'),(17,8,2,'applyJoin','2023-01-10 16:00:00'),(18,10,2,'cadreMan','2023-12-11 16:00:00'),(19,21,2,'member','2023-09-04 16:00:00'),(20,22,2,'vicePresident','2023-06-30 16:00:00'),(21,29,2,'member','2023-08-07 16:00:00'),(22,2,3,'applyQuit','2023-10-08 16:00:00'),(23,13,3,'applyJoin','2023-01-01 16:00:00'),(24,23,3,'cadreMan','2023-04-17 16:00:00'),(25,25,3,'member','2023-09-07 16:00:00'),(26,29,3,'vicePresident','2023-12-08 16:00:00'),(27,15,3,'member','2023-02-05 16:00:00'),(28,3,4,'applyQuit','2023-10-13 16:00:00'),(29,10,4,'applyJoin','2023-07-20 16:00:00'),(30,18,4,'cadreMan','2023-02-07 16:00:00'),(31,20,4,'member','2023-06-30 16:00:00'),(32,24,4,'vicePresident','2023-09-16 16:00:00'),(33,19,4,'member','2023-09-27 16:00:00'),(34,21,5,'applyQuit','2023-09-13 16:00:00'),(35,14,5,'applyJoin','2023-08-15 16:00:00'),(36,4,5,'cadreMan','2023-12-26 16:00:00'),(37,28,5,'member','2023-04-18 16:00:00'),(38,24,5,'vicePresident','2023-08-14 16:00:00'),(39,18,5,'member','2023-09-27 16:00:00'),(40,29,6,'applyQuit','2023-07-20 16:00:00'),(41,15,6,'applyJoin','2023-03-11 16:00:00'),(42,5,6,'cadreMan','2023-05-27 16:00:00'),(43,17,6,'member','2023-01-25 16:00:00'),(44,27,6,'vicePresident','2023-10-21 16:00:00'),(45,24,6,'member','2023-11-14 16:00:00'),(46,4,7,'applyQuit','2023-06-11 16:00:00'),(47,6,7,'applyJoin','2023-09-14 16:00:00'),(48,7,7,'cadreMan','2023-11-25 16:00:00'),(49,26,7,'member','2023-04-18 16:00:00'),(50,20,7,'vicePresident','2023-12-08 16:00:00'),(51,25,7,'member','2023-05-03 16:00:00');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `my_activity_participation_view`
--

DROP TABLE IF EXISTS `my_activity_participation_view`;
/*!50001 DROP VIEW IF EXISTS `my_activity_participation_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `my_activity_participation_view` AS SELECT 
 1 AS `user_id`,
 1 AS `email`,
 1 AS `activity_id`,
 1 AS `theme`,
 1 AS `description`,
 1 AS `activity_start_time`,
 1 AS `activity_end_time`,
 1 AS `location`,
 1 AS `is_signed`,
 1 AS `is_paid`,
 1 AS `amount`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `my_club_members_view`
--

DROP TABLE IF EXISTS `my_club_members_view`;
/*!50001 DROP VIEW IF EXISTS `my_club_members_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `my_club_members_view` AS SELECT 
 1 AS `club_id`,
 1 AS `username`,
 1 AS `email`,
 1 AS `club_name`,
 1 AS `position`,
 1 AS `join_date`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `my_clubs_view`
--

DROP TABLE IF EXISTS `my_clubs_view`;
/*!50001 DROP VIEW IF EXISTS `my_clubs_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `my_clubs_view` AS SELECT 
 1 AS `club_id`,
 1 AS `club_name`,
 1 AS `description`,
 1 AS `contact_info`,
 1 AS `activity_space`,
 1 AS `user_id`,
 1 AS `position`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `summary`
--

DROP TABLE IF EXISTS `summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `summary` (
  `summary_id` int NOT NULL AUTO_INCREMENT COMMENT '总结表主键',
  `activity_id` int DEFAULT NULL COMMENT '活动ID',
  `info` text NOT NULL COMMENT '总结内容',
  PRIMARY KEY (`summary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summary`
--

LOCK TABLES `summary` WRITE;
/*!40000 ALTER TABLE `summary` DISABLE KEYS */;
INSERT INTO `summary` VALUES (1,1,'篮球比赛圆满结束，各队伍展现了出色的团队合作和竞技水平。'),(2,2,'经典作品交流会成功举办，参与者积极分享了各自的阅读体验和心得。'),(3,3,'音乐会吸引了大量观众，社团成员的表演受到了一致好评。'),(4,4,'舞蹈表演展现了社团成员的多样性和舞蹈才华，活动获得巨大成功。'),(5,5,'华农KPL活动吸引了众多电竞爱好者参与，活动现场气氛热烈。'),(6,6,'美术展览展出了社团成员的众多优秀作品，参观者对作品给予了高度评价。'),(7,7,'志愿者活动得到了社区的广泛支持，参与者共同努力为社区环境做出了贡献。');
/*!40000 ALTER TABLE `summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `idx_username_password` (`username`,`password`) COMMENT '用户名-密码索引'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'张三','zhangsan1234','zhangsan@example.com','2024-04-04 07:34:48'),(2,'李四','lisi1234','lisi@example.com','2024-04-04 07:34:48'),(3,'王五','wangwu1234','wangwu@example.com','2024-04-04 07:34:48'),(4,'小红','xiaohong1234','xiaohong@example.com','2024-04-04 07:34:48'),(5,'小明','xiaoming1234','xiaoming@example.com','2024-04-04 07:34:48'),(6,'小花','xiaohua1234','xiaohua@example.com','2024-04-04 07:34:48'),(7,'刘备','liubei1234','liubei@example.com','2024-04-04 07:34:48'),(8,'关羽','guanyu1234','guanyu@example.com','2024-04-04 07:34:48'),(9,'张飞','zhangfei1234','zhangfei@example.com','2024-04-04 07:34:48'),(10,'赵云','zhaoyun1234','zhaoyun@example.com','2024-04-04 07:34:48'),(11,'大乔','daqiao1234','daqiao@example.com','2024-04-04 07:34:48'),(12,'小乔','xiaoqiao1234','xiaoqiao@example.com','2024-04-04 07:34:48'),(13,'孙权','sunquan1234','sunquan@example.com','2024-04-04 07:34:48'),(14,'周瑜','zhouyu1234','zhouyu@example.com','2024-04-04 07:34:48'),(15,'诸葛亮','zhugeliang1234','zhugeliang@example.com','2024-04-04 07:34:48'),(16,'孙尚香','sunshangxiang1234','sunshangxiang@example.com','2024-04-04 07:34:48'),(17,'曹操','caocao1234','caocao@example.com','2024-04-04 07:34:48'),(18,'司马懿','simayi1234','simayi@example.com','2024-04-04 07:34:48'),(19,'貂蝉','diaochan1234','diaochan@example.com','2024-04-04 07:34:48'),(20,'周仓','zhoucang1234','zhoucang@example.com','2024-04-04 07:34:48'),(21,'马超','machao1234','machao@example.com','2024-04-04 07:34:48'),(22,'黄忠','huangzhong1234','huangzhong@example.com','2024-04-04 07:34:48'),(23,'孙坚','sunjian1234','sunjian@example.com','2024-04-04 07:34:48'),(24,'太史慈','taishici1234','taishici@example.com','2024-04-04 07:34:48'),(25,'张角','zhangjiao1234','zhangjiao@example.com','2024-04-04 07:34:48'),(26,'张宝','zhangbao1234','zhangbao@example.com','2024-04-04 07:34:48'),(27,'于吉','yuji1234','yuji@example.com','2024-04-04 07:34:48'),(28,'庞德','pangde1234','pangde@example.com','2024-04-04 07:34:48'),(29,'徐庶','xushu1234','xushu@example.com','2024-04-04 07:34:48');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `my_activity_participation_view`
--

/*!50001 DROP VIEW IF EXISTS `my_activity_participation_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `my_activity_participation_view` AS select `u`.`user_id` AS `user_id`,`u`.`email` AS `email`,`a`.`activity_id` AS `activity_id`,`a`.`theme` AS `theme`,`a`.`description` AS `description`,`a`.`start_time` AS `activity_start_time`,`a`.`end_time` AS `activity_end_time`,`a`.`location` AS `location`,`ap`.`is_signed` AS `is_signed`,`f`.`is_paid` AS `is_paid`,`f`.`amount` AS `amount` from (((`activity_participation` `ap` join `activity` `a` on((`ap`.`activity_id` = `a`.`activity_id`))) join `fee` `f` on(((`ap`.`activity_id` = `f`.`activity_id`) and (`ap`.`user_id` = `f`.`user_id`)))) join `user` `u` on((`ap`.`user_id` = `u`.`user_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `my_club_members_view`
--

/*!50001 DROP VIEW IF EXISTS `my_club_members_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `my_club_members_view` AS select `c`.`club_id` AS `club_id`,`u`.`username` AS `username`,`u`.`email` AS `email`,`c`.`club_name` AS `club_name`,`m`.`position` AS `position`,`m`.`join_date` AS `join_date` from ((`member` `m` left join `club` `c` on((`c`.`club_id` = `m`.`club_id`))) left join `user` `u` on((`u`.`user_id` = `m`.`user_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `my_clubs_view`
--

/*!50001 DROP VIEW IF EXISTS `my_clubs_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `my_clubs_view` AS select `c`.`club_id` AS `club_id`,`c`.`club_name` AS `club_name`,`c`.`description` AS `description`,`c`.`contact_info` AS `contact_info`,`c`.`activity_space` AS `activity_space`,`m`.`user_id` AS `user_id`,`m`.`position` AS `position` from (`club` `c` left join `member` `m` on((`c`.`club_id` = `m`.`club_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-04 15:35:16
