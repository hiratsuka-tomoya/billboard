-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: billboard
-- ------------------------------------------------------
-- Server version	5.6.11

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
-- Table structure for table `branchs`
--

DROP TABLE IF EXISTS `branchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branchs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branchs`
--

LOCK TABLES `branchs` WRITE;
/*!40000 ALTER TABLE `branchs` DISABLE KEYS */;
INSERT INTO `branchs` VALUES (1,'本社','2017-01-16 06:25:04','2017-01-16 06:25:04'),(2,'支店A','2017-01-16 06:25:24','2017-01-16 06:25:24'),(3,'支店B','2017-01-16 06:25:38','2017-01-16 06:25:38'),(4,'支店C','2017-01-16 06:25:44','2017-01-16 06:25:44'),(5,'支店D','2017-01-16 06:26:10','2017-01-16 06:26:10'),(6,'支店E','2017-01-16 06:26:18','2017-01-16 06:26:18'),(7,'支店F','2017-01-16 06:26:27','2017-01-16 06:26:27'),(8,'支店G','2017-01-16 06:26:40','2017-01-16 06:26:40');
/*!40000 ALTER TABLE `branchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(500) NOT NULL,
  `user_id` int(11) NOT NULL,
  `posting_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'テスト本文１',1,1,'2017-01-17 02:44:06','2017-01-17 02:44:06'),(2,'テストコメント２',1,1,'2017-01-17 07:16:01','2017-01-17 07:16:01'),(3,'テストコメント3',1,2,'2017-01-17 07:16:09','2017-01-17 07:16:09'),(8,'あ',2,4,'2017-01-19 07:03:33','2017-01-19 07:03:33'),(9,'aaaaa',2,6,'2017-01-23 03:16:01','2017-01-23 03:16:01'),(10,'bbbb',2,6,'2017-01-23 03:16:04','2017-01-23 03:16:04'),(13,'a',2,8,'2017-01-23 06:23:21','2017-01-23 06:23:21'),(14,'あああ',2,8,'2017-01-23 06:31:35','2017-01-23 06:31:35'),(15,'ああ\r\nええ\r\nｄｄ',2,8,'2017-01-23 06:31:40','2017-01-23 06:31:40'),(16,'あｓ',2,8,'2017-01-23 06:33:24','2017-01-23 06:33:24'),(17,'sss',2,8,'2017-01-23 06:37:38','2017-01-23 06:37:38'),(18,'ああ',2,7,'2017-01-23 06:51:36','2017-01-23 06:51:36'),(19,'ｓｓ',2,7,'2017-01-23 06:51:50','2017-01-23 06:51:50'),(20,'コメント改行テスト\r\n2行目',2,7,'2017-01-23 07:29:46','2017-01-23 07:29:46'),(21,'sd',2,10,'2017-01-23 08:09:17','2017-01-23 08:09:17'),(23,'asdf',2,10,'2017-01-23 08:09:22','2017-01-23 08:09:22'),(28,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',2,2,'2017-01-24 02:22:52','2017-01-24 02:22:52'),(30,'ｄｄｄｄｄｄｄｄｄｄｄｄｄｄｄｄｄｄああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ',2,16,'2017-01-24 04:03:34','2017-01-24 04:03:34'),(35,'a\r\n\r\na\r\n',2,15,'2017-01-24 06:04:28','2017-01-24 06:04:28'),(36,'aaaaaaa',2,17,'2017-01-25 08:38:11','2017-01-25 08:38:11'),(39,'あああ',14,23,'2017-01-26 07:55:50','2017-01-26 07:55:50'),(40,'ああ',14,24,'2017-01-26 07:55:58','2017-01-26 07:55:58'),(41,'ああ',14,22,'2017-01-26 07:56:00','2017-01-26 07:56:00'),(43,'A支店社員コメント\r\nA支店社員コメント',13,27,'2017-01-26 08:50:03','2017-01-26 08:50:03'),(44,'BBBBBBBBBBBBBBBBB\r\nBBBBBBBBBBBBBBBBB',15,31,'2017-01-26 08:54:22','2017-01-26 08:54:22'),(46,'A支店長A\r\nA支店長A\r\nA支店長A\r\nA支店長A\r\nA支店長A',8,31,'2017-01-26 08:55:27','2017-01-26 08:55:27');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'総務人事担当者','2017-01-16 06:27:21','2017-01-16 06:27:21'),(2,'情報管理担当者','2017-01-16 06:27:41','2017-01-16 06:27:41'),(3,'支店長','2017-01-16 06:27:51','2017-01-16 06:27:51'),(4,'社員','2017-01-16 06:28:00','2017-01-16 06:28:00');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postings`
--

DROP TABLE IF EXISTS `postings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `category` varchar(10) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postings`
--

LOCK TABLES `postings` WRITE;
/*!40000 ALTER TABLE `postings` DISABLE KEYS */;
INSERT INTO `postings` VALUES (24,'あああ','あああ','ああ',14,'2017-01-26 07:55:55','2017-01-26 07:55:55'),(28,'BBBBBB','BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB','投稿削除テスト',15,'2017-01-26 08:50:55','2017-01-26 08:50:55'),(29,'AAAAAA','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\n','投稿削除テスト',13,'2017-01-26 08:51:42','2017-01-26 08:51:42'),(31,'テスト','テストテスト\r\nテストテストテスト','コメント削除テスト',2,'2017-01-26 08:53:42','2017-01-26 08:53:42'),(32,'1月27日本日の投稿','テストテスト\r\nテストテス','テストカテゴリーA',2,'2017-01-27 01:10:22','2017-01-27 01:10:22'),(33,'aaa','aaa','aaa',2,'2017-01-27 02:07:14','2017-01-27 02:07:14'),(34,'a','a','a<t>a',4,'2017-01-27 02:20:08','2017-01-27 02:20:08'),(35,'a','a','<a>p:</a>',4,'2017-01-27 02:20:44','2017-01-27 02:20:44');
/*!40000 ALTER TABLE `postings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(10) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `is_stopped` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'admin1','7QJFe1xB2WTb0vKmCdY_4bt1KNvlXhq_W1LCSc1zV5c','総務人事担当者1',1,1,0,'2017-01-18 07:39:09','2017-01-26 08:41:25'),(3,'admin2','7QJFe1xB2WTb0vKmCdY_4bt1KNvlXhq_W1LCSc1zV5c','総務人事担当者2',1,1,0,'2017-01-19 07:07:09','2017-01-26 09:21:49'),(8,'Amaster','7QJFe1xB2WTb0vKmCdY_4bt1KNvlXhq_W1LCSc1zV5c','支店A店長',2,3,1,'2017-01-23 07:50:35','2017-01-27 02:14:53'),(13,'Aregular','7QJFe1xB2WTb0vKmCdY_4bt1KNvlXhq_W1LCSc1zV5c','支店A社員',2,4,0,'2017-01-26 07:40:32','2017-01-26 08:06:03'),(15,'Bregular2','7QJFe1xB2WTb0vKmCdY_4bt1KNvlXhq_W1LCSc1zV5c','支店B社員',4,3,0,'2017-01-26 08:06:29','2017-01-27 02:14:16');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `users_comments`
--

DROP TABLE IF EXISTS `users_comments`;
/*!50001 DROP VIEW IF EXISTS `users_comments`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `users_comments` (
  `id` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `user_id` tinyint NOT NULL,
  `posting_id` tinyint NOT NULL,
  `user_name` tinyint NOT NULL,
  `user_department_id` tinyint NOT NULL,
  `user_branch_id` tinyint NOT NULL,
  `created_at` tinyint NOT NULL,
  `updated_at` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `users_postings`
--

DROP TABLE IF EXISTS `users_postings`;
/*!50001 DROP VIEW IF EXISTS `users_postings`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `users_postings` (
  `id` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `category` tinyint NOT NULL,
  `user_id` tinyint NOT NULL,
  `user_name` tinyint NOT NULL,
  `user_department_id` tinyint NOT NULL,
  `user_branch_id` tinyint NOT NULL,
  `created_at` tinyint NOT NULL,
  `updated_at` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `users_comments`
--

/*!50001 DROP TABLE IF EXISTS `users_comments`*/;
/*!50001 DROP VIEW IF EXISTS `users_comments`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp932 */;
/*!50001 SET character_set_results     = cp932 */;
/*!50001 SET collation_connection      = cp932_japanese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `users_comments` AS (select `comments`.`id` AS `id`,`comments`.`text` AS `text`,`comments`.`user_id` AS `user_id`,`comments`.`posting_id` AS `posting_id`,`users`.`name` AS `user_name`,`users`.`department_id` AS `user_department_id`,`users`.`branch_id` AS `user_branch_id`,`comments`.`created_at` AS `created_at`,`comments`.`updated_at` AS `updated_at` from (`comments` join `users` on((`comments`.`user_id` = `users`.`id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `users_postings`
--

/*!50001 DROP TABLE IF EXISTS `users_postings`*/;
/*!50001 DROP VIEW IF EXISTS `users_postings`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp932 */;
/*!50001 SET character_set_results     = cp932 */;
/*!50001 SET collation_connection      = cp932_japanese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `users_postings` AS (select `postings`.`id` AS `id`,`postings`.`title` AS `title`,`postings`.`text` AS `text`,`postings`.`category` AS `category`,`postings`.`user_id` AS `user_id`,`users`.`name` AS `user_name`,`users`.`department_id` AS `user_department_id`,`users`.`branch_id` AS `user_branch_id`,`postings`.`created_at` AS `created_at`,`postings`.`updated_at` AS `updated_at` from (`postings` join `users` on((`postings`.`user_id` = `users`.`id`)))) */;
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

-- Dump completed on 2017-01-27 17:20:23
