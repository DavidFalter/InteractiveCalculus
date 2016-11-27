-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: interactivecalculusaid
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `forum_post`
--

DROP TABLE IF EXISTS `forum_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_post` (
  `post_id` int(11) DEFAULT NULL,
  `post_author` varchar(50) DEFAULT NULL,
  `post_body` varchar(72) DEFAULT NULL,
  `op_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_post`
--

LOCK TABLES `forum_post` WRITE;
/*!40000 ALTER TABLE `forum_post` DISABLE KEYS */;
INSERT INTO `forum_post` VALUES (42,'Floyd','All you have to do is work WORK WORK!!!!!!',18),(43,'Ben','This isn\'t stupid youre stupid! lol',19),(44,'Floyd','What is this?',20),(45,'Floyd','What is this?',20),(46,'Tom','What is this?',20),(47,'Tom','Did this work',20);
/*!40000 ALTER TABLE `forum_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_table`
--

DROP TABLE IF EXISTS `forum_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_table` (
  `thread_id` int(11) DEFAULT NULL,
  `thread_name` varchar(50) DEFAULT NULL,
  `thread_description` varchar(68) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_table`
--

LOCK TABLES `forum_table` WRITE;
/*!40000 ALTER TABLE `forum_table` DISABLE KEYS */;
INSERT INTO `forum_table` VALUES (20,'I hope this works.','Trying to get the board up and running','Floyd'),(NULL,'Clown Sighting','too sketchy','Tom'),(NULL,'clowns','clows','Tom');
/*!40000 ALTER TABLE `forum_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) DEFAULT NULL,
  `question` varchar(161) DEFAULT NULL,
  `a1` varchar(98) DEFAULT NULL,
  `a2` varchar(93) DEFAULT NULL,
  `a3` varchar(97) DEFAULT NULL,
  `a4` varchar(135) DEFAULT NULL,
  `correct` int(11) DEFAULT NULL,
  `section` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'Find the derivative of 4','4x','2','0','-4',3,1),(2,'Find the derivative of 3x+5','3','8x','5','-3x-5',1,1),(3,'Find the derivative of 3x<sup>2</sup>','-3x','9x<sup>2</sup>','3','6x',4,1),(4,'Find the derivative of 5x<sup>2</sup>-3x-2','-5x+3','5x<sup>3</sup>-3x<sup>2</sup>-2x','10x<sup>2</sup>+3','10x-3',4,1),(5,'Find the derivative of -7x<sup>2</sup>+6','-7x+6x<sup>2</sup>','-14x','6','7x',2,1),(6,'Find the derivative of <sup>5</sup>&frasl;<sub>2</sub>x<sup>3</sup>+13','15x','10x<sup>2</sup>+13','<sup>15</sup>&frasl;<sub>2</sub>x<sup>2</sup>','<sup>x</sup>&frasl;<sub>2</sub>+13',3,1),(7,'Find the derivative of 4-6x<sup>2</sup>+8x<sup>3</sup>/3','4x-6x<sup>3</sup>+8x<sup>4</sup>/3','4+12x+24x<sup>2</sup>','8x<sup>2</sup>-12x','-12x+8x<sup>2</sup>/3',3,1),(8,'Find the derivative of 16.5x<sup>4</sup>-4x+3.25x<sup>3</sup>','66x<sup>2</sup>+10x-4','16.5x<sup>3</sup>-9.75x<sup>2</sup>-4x','66x<sup>3</sup>+9.75x<sup>2</sup>-4','16.5x<sup>4</sup>+4x-3.25x<sup>2</sup>',3,1),(9,'Find the derivative of x<sup>5</sup>-12x<sup>4</sup>+3x<sup>3</sup>+6x<sup>2</sup>-6x-3','5x<sup>4</sup>-48x<sup>3</sup>+9x<sup>2</sup>+12x-6','4x<sup>5</sup>-36x<sup>4</sup>+6x<sup>3</sup>+12x-6x+3','5x<sup>4</sup>+48x<sup>2</sup>+9x<sup>2</sup>+12','x<sup>5</sup>-48x<sup>3</sup>+9x-6',1,1),(10,'Find the derivative of <sup>3</sup>&frasl;<sub>4</sub>x<sup>5</sup>+<sup>2</sup>&frasl;<sub>3</sub>x<sup>4</sup>+4x<sup>2</sup>-36x','<sup>15</sup>&frasl;<sub>4</sub>x<sup>4</sup>+8x<sup>3</sup>/3+8x-36','<sup>5</sup>&frasl;<sub>3</sub>x<sup>4</sup>-36','<sup>15</sup>&frasl;<sub>4</sub>x<sup>4</sup>+8x<sup>3</sup>+8x-36x','-<sup>5</sup>&frasl;<sub>4</sub>x<sup>4</sup>-<sup>8</sup>&frasl;<sub>3</sub>x<sup>3</sup>+8x<sup>2</sup>',1,1),(11,'2sin(x)','cos<sup>2</sup>(x)','cos(x)','2x<sup>2</sup>','-2tan(x)',2,2),(12,'tan(3x<sup>2</sup>)','3x<sup>2</sup>tan(3x<sup>2</sup>)','6xtan(6x)','sec<sup>2</sup>(6x)','6xsec<sup>2</sup>(3x<sup>2</sup>)',4,2),(13,'6sin(8cos(x))','6cos<sup>2</sup>(x)-8cos<sup>2</sup>(x)','48sin(x)cos(8x)','-48sin(x)cos(8cos(x))','6sin(-8sin(x))',3,2),(14,'5cos(2x+3)','-5sin(2x)','5cos(2x<sup>3</sup>+3x)','-10sin(2x+3)','10sin(2x)',3,2),(15,'(x+1)(3x-2)','6x+1','3x<sup>2</sup>+x-2','3x-2','6x<sup>2</sup>+x',1,2),(16,'(2x+1)(4x-3)','8x-3','6x<sup>2</sup>','8x<sup>2</sup>-2x-3','16x-2',4,2),(17,'(4x<sup>2</sup>+5x+4)(3x<sup>2</sup>-2)','48x<sup>3</sup>-15x<sup>2</sup>+4x','7x<sup>3</sup>-15x<sup>2</sup>+8x-6','48x<sup>3</sup>+45x<sup>2</sup>+8x-10','12x<sup>4</sup>+15x<sup>3</sup>+4x<sup>2</sup>-10x-8',3,2),(18,'(1+&radic;x)(x<sup>2</sup>)','<sup>1</sup>&frasl;<sub>2</sub>x<sup>2</sup>','x<sup>2</sup>+<sup>1</sup>&frasl;<sub>2</sub>x<sup>5</sup>','<sup>5</sup>&frasl;<sub>2</sub>x<sup>3/2</sup>+2x','2&radic;x',3,2),(19,'-cos(x)ln(x)','-sin(x)ln(x)','sin(x)cos(x)','ln(x)sin(x)-<sup>1</sup>&frasl;<sub>x</sub>cos(x)','<sup>1</sup>&frasl;<sub>x</sub>tan(x)',3,2),(20,'5csc<sup>2</sup>(x)','-10cot(x)csc<sup>2</sup>(x)','10tan(x)','-5sec<sup>2</sup>(x)','tan(x)sec<sup>2</sup>(x)',1,2),(21,'<sup>3sin(x)</sup>&frasl;<sub>x<sup>2</sup></sub>','(3xcos(x)-6sin(x))/x<sup>3</sup>','6xsin(x)','3cos(x)+2x','(-6tan(x)+3cot(x))/x<sup>2</sup>',1,3),(22,'<sup>cos(x)</sup>&frasl;<sub>tan(x<sup>2</sup>)</sub>','4xcos(x)csc<sup>2</sup>(x<sup>2</sup>)-cos(x)cot(x<sup>2</sup>)','2xcos(x)csc<sup>2</sup>(x<sup>2</sup>)-sin(x)cot(x<sup>2</sup>)','4xcos(x)sec<sup>2</sup>(x<sup>2</sup>)-sin(x)sin(x<sup>2</sup>)','2xcos(x)sec<sup>2</sup>(x<sup>2</sup>)-cos(x)sin(x<sup>3</sup>)',2,3),(23,'3x<sup>2</sup>sec<sup>2</sup>(x)','3x(xcot(x)+1)sec<sup>2</sup>(x)','6x(xcot(x)+1)csc<sup>2</sup>(x)','6x(xtan(x)+1)sec<sup>2</sup>(x)','3x(xtan(x)+1)csc<sup>2</sup>(x)',3,3),(24,'sin(x)cos(x)','cos<sup>2</sup>(x)-sin<sup>2</sup>(x)','tan<sup>2</sup>x','sin(2x)','sin<sup>2</sup>(x)-cos<sup>2</sup>(x)',1,3),(25,'sec<sup>3</sup>(x<sup>2</sup>)','12xtan(x<sup>2</sup>)csc<sup>3</sup>(x<sup>2</sup>)','6xsec(x<sup>2</sup>)sec<sup>3</sup>(x<sup>3</sup>)','6xtan(x<sup>2</sup>)sec<sup>3</sup>(x<sup>2</sup>)','12xsec(x)csc<sup>3</sup>(x<sup>2</sup>)',2,3),(26,'cos(6x<sup>3</sup>+4x<sup>2</sup>-4x+2)','-(18x<sup>2</sup>+8x-4)sin(6x<sup>3</sup>+4x<sup>2</sup>-4x+2)','(18x<sup>2</sup>+8x-4)sin(12x<sup>3</sup>+2x<sup>2</sup>+x+2)','(18x<sup>2</sup>+8x-4)cos(6x<sup>3</sup>+4x<sup>2</sup>-4x+2)','-(9x<sup>2</sup>+16x-8)sin(6x<sup>3</sup>+4x<sup>2</sup>-4x+2)',1,3),(27,'<sup>x+1</sup>&frasl;<sub>x<sup>3</sup></sub>','(2x+1)/x<sup>4</sup>','x<sup>-2</sup>','(x+1)/x<sup>2</sup>','(3x+4)/x<sup>4</sup>',1,3),(28,'3(ln(x))<sup>2</sup>','<sup>6</sup>&frasl;<sub>x</sub>','6ln(x)','6x<sup>2</sup>','<sup>6</sup>&frasl;<sub>x</sub>ln(x)',4,3),(29,'e<sup>x</sup>sin(x)','e<sup>x</sup>(sin(x)-cos(x))','e<sup>x</sup>(sin(x)+cos(x))','e<sup>x</sup>(sin(x)cos(x))','e<sup>x</sup>(cos(x))',2,3),(30,'x<sup>2</sup>ln(x)','2xln(x)','x<sup>2ln</sup>(x)','x+2xln(x)','x<sup>2</sup>+ln(x)',3,3);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `un` varchar(120) DEFAULT NULL,
  `pw` varchar(120) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'dlfalter','password','dlfalter@hotmail.com'),(2,'ben','lol','haha@hotmail.com'),(3,'ben','lol','haha@hotmail.com'),(4,'david','password','www@hotmail.com'),(5,'ben','goldstein','dlfalter@hotmail.com'),(6,'Tony','Tiger','FrostedFloakes@gmail.com'),(7,'test','test','test@gmail.com'),(8,'testtest','test','test@gmail.com'),(9,'carl','carl','carl@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-27 17:00:47
