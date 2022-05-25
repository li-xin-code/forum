-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: localhost    Database: common_db
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `author_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `topic_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `reply_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '回复',
  `reply_author` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_comment_id_uindex` (`comment_id`),
  KEY `comment_topic_id_index` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'JqfGdDxH','6PKSv8XZ','评论','XRTMuPwX','','','2022-05-16 15:39:13'),(3,'YrcNGsBn','6PKSv8XZ','评论','XRTMuPwX','JqfGdDxH','ben','2022-05-16 15:46:51'),(4,'cbACfnlt','6PKSv8XZ','评论','XRTMuPwX','JqfGdDxH','ben','2022-05-16 15:52:43'),(5,'o70VnxJZ','6PKSv8XZ','回复','XRTMuPwX','JqfGdDxH','ben','2022-05-16 15:53:26'),(11,'gwZI11RM','NqbqgDgf','<p>阿巴阿巴阿巴</p>','XRTMuPwX','o70VnxJZ','ben','2022-05-24 23:26:39'),(12,'u9unTgog','NqbqgDgf','<p>阿巴阿巴阿巴</p>','XRTMuPwX','o70VnxJZ','ben','2022-05-24 23:26:53'),(13,'g4jbSSCV','NqbqgDgf','<p>测试回复</p>','XRTMuPwX','u9unTgog','tom','2022-05-24 23:37:04'),(14,'WvvmgKXh','NqbqgDgf','评论goodgood','Km4KCIYK','',' ','2022-05-25 00:25:20'),(15,'00Qzl1zK','NqbqgDgf','评论goodgood','Km4KCIYK','','','2022-05-25 00:26:40'),(16,'woGFgGzt','NqbqgDgf','回复->评论goodgood','Km4KCIYK','WvvmgKXh','tom','2022-05-25 00:27:03');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `author_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_topic_id_uindex` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'XRTMuPwX','6PKSv8XZ','标题','正文','2022-05-12 18:00:43'),(2,'kanG54fJ','6PKSv8XZ','标题','内容','2022-05-12 20:33:19'),(3,'f15omsOn','NqbqgDgf','标题','内容','2022-05-21 20:28:05'),(4,'vzQNkN3r','NqbqgDgf','标题','正文……','2022-05-22 00:39:50'),(5,'ScXFQbFn','NqbqgDgf','标题','<p><span style=\"text-decoration: underline;\"><em><strong>正文&hellip;&hellip;</strong></em></span></p>','2022-05-22 01:04:35'),(6,'btxZ0k5d','NqbqgDgf','标题','正文……','2022-05-23 02:13:09'),(7,'OzNi6DZ4','NqbqgDgf','tinymce','<p>tinymce</p>\n<p>img</p>\n<p>Cannot read properties of undefined (reading \'then\')</p>','2022-05-23 02:15:07'),(8,'KZLkgyYn','NqbqgDgf','图片测试','<p>正文&hellip;&hellip;</p>\n<p><img src=\"http://localhost:8080/campus/upload/083bb550c1764212b5e75ced844007b7.png\" alt=\"\" width=\"256\" height=\"160\"></p>','2022-05-23 21:26:09'),(10,'xicLSje8','NqbqgDgf','标题','正文……','2022-05-24 12:57:17'),(11,'Km4KCIYK','NqbqgDgf','图片测试','<p><img src=\"http://localhost:8080/campus/60dc926c31fd4bd4a1a32e789d0092d1.png\" alt=\"\" width=\"256\" height=\"160\"></p>','2022-05-24 15:50:47'),(12,'6ZW7dmok','NqbqgDgf','test','<p>test</p>','2022-05-25 00:31:28'),(13,'vF2PxEzs','NqbqgDgf','标题11','<p>正文11</p>','2022-05-25 17:30:22');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_uuid_uindex` (`uuid`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'EBsBUo4U','Marry','21218cca77804d2ba1922c33e0151105'),(19,'NqbqgDgf','Tom','21218cca77804d2ba1922c33e0151105'),(20,'6PKSv8XZ','ben','21218cca77804d2ba1922c33e0151105');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26  1:43:12
