-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `camere`
--

DROP TABLE IF EXISTS `camere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camere` (
  `id_camera` bigint NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `posti_letto` int DEFAULT NULL,
  `disponibile` bit(1) DEFAULT NULL,
  `prezzo_base` double DEFAULT NULL,
  `tipo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `info_check_out` varchar(255) DEFAULT NULL,
  `id_residenza` bigint DEFAULT NULL,
  PRIMARY KEY (`id_camera`),
  KEY `FKpg4iugabvxlrav5ibtkih4u5b` (`id_residenza`),
  CONSTRAINT `FKpg4iugabvxlrav5ibtkih4u5b` FOREIGN KEY (`id_residenza`) REFERENCES `residenze` (`id_residenza`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camere`
--

LOCK TABLES `camere` WRITE;
/*!40000 ALTER TABLE `camere` DISABLE KEYS */;
INSERT INTO `camere` VALUES (1,'Cayenna',3,_binary '',100,'Matrimoniale Luxury','10:30',1),(2,'Habanero',3,_binary '',100,'Matrimoniale','10:30',1),(3,'Jalapeño',4,_binary '',120,'Doppia','10:30',1),(4,'Tabasco',4,_binary '',100,'Doppia','10:30',1);
/*!40000 ALTER TABLE `camere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img_camera`
--

DROP TABLE IF EXISTS `img_camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img_camera` (
  `id_img_camera` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `id_camera` bigint DEFAULT NULL,
  PRIMARY KEY (`id_img_camera`),
  KEY `FKtousp39bv6ldrj5v2b197ys8g` (`id_camera`),
  CONSTRAINT `FKtousp39bv6ldrj5v2b197ys8g` FOREIGN KEY (`id_camera`) REFERENCES `camere` (`id_camera`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img_camera`
--

LOCK TABLES `img_camera` WRITE;
/*!40000 ALTER TABLE `img_camera` DISABLE KEYS */;
INSERT INTO `img_camera` VALUES (1,'/assets/images/camere/cayenna1.jpg',1),(2,'/assets/images/camere/cayenna2.jpg',1),(3,'/assets/images/camere/cayenna3.jpg',1),(4,'/assets/images/camere/cayenna4.jpg',1),(5,'/assets/images/camere/cayenna5.jpg',1),(6,'/assets/images/camere/cayenna6.jpg',1),(7,'/assets/images/camere/cayenna7.jpg',1),(8,'/assets/images/camere/habanero1.jpg',2),(9,'/assets/images/camere/habanero2.jpg',2),(10,'/assets/images/camere/habanero3.jpg',2),(11,'/assets/images/camere/habanero4.jpg',2),(12,'/assets/images/camere/habanero5.jpg',2),(13,'/assets/images/camere/habanero6.jpg',2),(14,'/assets/images/camere/jalape1.jpg',3),(15,'/assets/images/camere/jalape2.jpg',3),(16,'/assets/images/camere/jalape3.jpg',3),(17,'/assets/images/camere/jalape4.jpg',3),(18,'/assets/images/camere/jalape5.jpg',3),(19,'/assets/images/camere/tabasco1.jpg',4),(20,'/assets/images/camere/tabasco2.jpg',4),(21,'/assets/images/camere/tabasco3.jpg',4),(22,'/assets/images/camere/tabasco4.jpg',4),(23,'/assets/images/camere/tabasco5.jpg',4),(24,'/assets/images/camere/tabasco6.jpg',4),(25,'/assets/images/camere/frigobar.jpg',4),(26,'/assets/images/camere/frigobar.jpg',1),(27,'/assets/images/camere/frigobar.jpg',3),(28,'/assets/images/camere/frigobar.jpg',2);
/*!40000 ALTER TABLE `img_camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img_residenza`
--

DROP TABLE IF EXISTS `img_residenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img_residenza` (
  `id_img_residenza` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_img_residenza`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img_residenza`
--

LOCK TABLES `img_residenza` WRITE;
/*!40000 ALTER TABLE `img_residenza` DISABLE KEYS */;
INSERT INTO `img_residenza` VALUES (1,'/assets/images/residenze/peperoncini.jpg');
/*!40000 ALTER TABLE `img_residenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img_user`
--

DROP TABLE IF EXISTS `img_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img_user` (
  `id_img_user` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_img_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img_user`
--

LOCK TABLES `img_user` WRITE;
/*!40000 ALTER TABLE `img_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `img_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_prezzi`
--

DROP TABLE IF EXISTS `lista_prezzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_prezzi` (
  `id_listino` bigint NOT NULL AUTO_INCREMENT,
  `prezzo` double DEFAULT NULL,
  `validita_fine` date DEFAULT NULL,
  `validita_inizio` date DEFAULT NULL,
  `id_camera` bigint DEFAULT NULL,
  PRIMARY KEY (`id_listino`),
  KEY `FKlclr37dlt67x42pxra28g60v2` (`id_camera`),
  CONSTRAINT `FKlclr37dlt67x42pxra28g60v2` FOREIGN KEY (`id_camera`) REFERENCES `camere` (`id_camera`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_prezzi`
--

LOCK TABLES `lista_prezzi` WRITE;
/*!40000 ALTER TABLE `lista_prezzi` DISABLE KEYS */;
INSERT INTO `lista_prezzi` VALUES (1,90,'2025-01-01','2025-02-01',1),(2,110,'2025-05-31','2025-06-15',1),(3,98,'2025-08-01','2025-08-31',1);
/*!40000 ALTER TABLE `lista_prezzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazioni`
--

DROP TABLE IF EXISTS `prenotazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotazioni` (
  `id_prenotazione` bigint NOT NULL AUTO_INCREMENT,
  `num_persone` int DEFAULT NULL,
  `totale` double DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `id_camera` bigint DEFAULT NULL,
  PRIMARY KEY (`id_prenotazione`),
  KEY `FKbwpee0in9eetnmb8jskwcscov` (`id_camera`),
  KEY `FK775f6nhhxoi6707v6nmjpfl96` (`id_user`),
  CONSTRAINT `FK775f6nhhxoi6707v6nmjpfl96` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  CONSTRAINT `FKbwpee0in9eetnmb8jskwcscov` FOREIGN KEY (`id_camera`) REFERENCES `camere` (`id_camera`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazioni`
--

LOCK TABLES `prenotazioni` WRITE;
/*!40000 ALTER TABLE `prenotazioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `prenotazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residenze`
--

DROP TABLE IF EXISTS `residenze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residenze` (
  `id_residenza` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_img_residenza` bigint DEFAULT NULL,
  PRIMARY KEY (`id_residenza`),
  UNIQUE KEY `UK_k1y1p0v5n8yfbc571ni5ss98e` (`id_img_residenza`),
  CONSTRAINT `FKpvcarxrvm7jdxm60if6cx9163` FOREIGN KEY (`id_img_residenza`) REFERENCES `img_residenza` (`id_img_residenza`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residenze`
--

LOCK TABLES `residenze` WRITE;
/*!40000 ALTER TABLE `residenze` DISABLE KEYS */;
INSERT INTO `residenze` VALUES (1,'I Peperoncini','Strada Vicinale Figuruja, 10 - Località Mamuntanas Alghero - 07041 ( Sassari)',1);
/*!40000 ALTER TABLE `residenze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cognome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ddn` date DEFAULT NULL,
  `cf` varchar(255) DEFAULT NULL,
  `cod_documento` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `id_img_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_kvl06ynnkwlv7wrddh7hjihx3` (`id_img_user`),
  CONSTRAINT `FKysgte1kh8a6swr994tg5yug1` FOREIGN KEY (`id_img_user`) REFERENCES `img_user` (`id_img_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Azmodeus','VonDemoniae','1988-08-02','GBNIDSAO','EE99AA9','azmo@azmo.com','Azmodeus','azmo',_binary '',NULL),(2,'Gianni','Giannini','1900-01-01','GBNIDSAO','GNNWISA','gianni@gmail.com','gianni','gianni',_binary '\0',NULL),(3,'Giovanni','Giovannini','1800-02-02','GBNIDSAO','AOISF9090','lll@lll.it','user','user',_binary '\0',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'booking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 13:44:59
