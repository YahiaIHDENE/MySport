-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: mysportdb
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `t_annonce`
--

DROP TABLE IF EXISTS `t_annonce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_annonce` (
  `id_annonce` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `jour_debut` varchar(20) DEFAULT NULL,
  `heure_debut` varchar(20) DEFAULT NULL,
  `heure_fin` varchar(20) DEFAULT NULL,
  `place_restant` int(11) DEFAULT NULL,
  `id_terrain` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_annonce`) USING BTREE,
  KEY `t_annonce_ibfk_1` (`id_user`),
  CONSTRAINT `t_annonce_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_annonce`
--

LOCK TABLES `t_annonce` WRITE;
/*!40000 ALTER TABLE `t_annonce` DISABLE KEYS */;
INSERT INTO `t_annonce` VALUES (2,3,'2010-12-25','14:00:00','18:00:00',4,8),(3,3,'2020-01-10','10:00:00','12:00:00',10,12),(4,3,'2020-01-10','10:00:00','12:00:00',10,12),(5,1,'12-12-1995','12:56','14:00',12,20),(6,1,'2020-12-12','12:00','14:00',12,21),(7,1,'2020-12-12','20:00','23:09',12,22);
/*!40000 ALTER TABLE `t_annonce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reserve`
--

DROP TABLE IF EXISTS `t_reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_reserve` (
  `id_reserve` int(11) NOT NULL AUTO_INCREMENT,
  `id_annonce` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nombre_reserve` int(11) NOT NULL,
  `jour_reserve` varchar(20) DEFAULT NULL,
  `heure_debut` varchar(20) DEFAULT NULL,
  `heure_fin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_reserve`) USING BTREE,
  KEY `user_constraint` (`id_user`) USING BTREE,
  KEY `annonce_constraint` (`id_annonce`),
  CONSTRAINT `annonce_constraint` FOREIGN KEY (`id_annonce`) REFERENCES `t_annonce` (`id_annonce`) ON DELETE CASCADE,
  CONSTRAINT `user_constraint` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reserve`
--

LOCK TABLES `t_reserve` WRITE;
/*!40000 ALTER TABLE `t_reserve` DISABLE KEYS */;
INSERT INTO `t_reserve` VALUES (53,7,2,5,'2020-12-12','14:00:00','18:00:00');
/*!40000 ALTER TABLE `t_reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_terrain`
--

DROP TABLE IF EXISTS `t_terrain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_terrain` (
  `id_terrain` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(50) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `code_postal` varchar(20) NOT NULL,
  `capacite` int(11) NOT NULL,
  `type_sport` varchar(20) NOT NULL,
  `nom_terrain` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_terrain`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_terrain`
--

LOCK TABLES `t_terrain` WRITE;
/*!40000 ALTER TABLE `t_terrain` DISABLE KEYS */;
INSERT INTO `t_terrain` VALUES (1,'5 rue test','Paris_test','75000',10,'Footest',NULL),(2,'tttttttest','ttttttest','75000',10,'Footest',NULL),(8,'tttttttest','ttttttest','75000',10,'Footest',NULL),(9,'tttttttest','ttttttest','75000',10,'Footest',NULL),(12,'5 place jussieu','Paris','75005',10,'Football',NULL),(13,'5 place jussieu','Paris','75005',10,'Football',NULL),(14,'5 place jussieu','Paris','75005',10,'Football',NULL),(15,'5 place jussieu','Paris','75005',10,'Football',NULL),(16,'5 place jussieu','Paris','75005',10,'Football',NULL),(17,'5 place jussieu','Paris','75005',10,'Football',NULL),(18,'5 place jussieu','Paris','75005',10,'Football',NULL),(19,'5 place jussieu','Paris','75005',10,'Football',NULL),(20,'4 Avenue Montaigne','Noisy-le-Grand','93160',12,'Football',NULL),(21,'76 Rue Emile Cossonneau','Neuilly-sur-Marne','93330',12,'Football','Nawfaz Jaufurally'),(22,'yahia','Neuilly-sur-Marne','93330',12,'Football','azdine');
/*!40000 ALTER TABLE `t_terrain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_first_name` varchar(20) NOT NULL,
  `user_last_name` varchar(20) NOT NULL,
  `user_mail` varchar(50) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_tel` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'Yahia ','IHDENE ','i.y.azdine@gmail.com','20101995',668949532),(2,' Davinci ','Irly ','test@gmail.com','11111',668956789),(3,'Test1','Test1','test1111@gmail.com','0000',668941234),(4,'Test2','Test2','test2222@gmail.com','9999',668945678),(5,'Azdine ','Azdine ','ihden.azdine@outlook.fr','555',668949500),(6,'test5555 ','test555 ','ihden.azdine@outlook.fr','555',668949500),(7,'Azdine ','Azdine ','ihden.azdine@outlook.fr','555',668949500),(8,'Azdine ','Azdine ','ihden.azdine@outlook.fr','555',668949500),(9,'Nawfaz','Jaufurally','jdbwjd','12345',1357),(10,'Irlytest ','IHDENEtest ','hhhhhhhhhhhhhh','8888',11111111),(11,'bb vvvv. ','aaaaaaaa','nawfaz555@gmail.com','123',123);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-15 17:30:42
