
LOCK TABLES `residenze` WRITE;
/*!40000 ALTER TABLE `residenze` DISABLE KEYS */;
INSERT INTO `residenze` VALUES 
(1,'I Peperoncini','Strada Vicinale Figuruja, 10 - Località Mamuntanas Alghero - 07041 ( Sassari)',1);
/*!40000 ALTER TABLE `residenze` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `camere` WRITE;
/*!40000 ALTER TABLE `camere` DISABLE KEYS */;
INSERT INTO `camere` VALUES
(1,'Cayenna',3,_binary '',100,'Matrimoniale Luxury','10:30',1),
(2,'Habanero',3,_binary '',100,'Matrimoniale','10:30',1),
(3,'Jalapeño',4,_binary '',120,'Doppia','10:30',1),
(4,'Tabasco',4,_binary '',100,'Doppia','10:30',1);

/*!40000 ALTER TABLE `camere` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `img_camera` WRITE;
/*!40000 ALTER TABLE `img_camera` DISABLE KEYS */;
INSERT INTO `img_camera` VALUES 
(1,'/assets/images/camere/cayenna1.jpg',1),
(2,'/assets/images/camere/cayenna2.jpg',1),
(3,'/assets/images/camere/cayenna3.jpg',1),
(4,'/assets/images/camere/cayenna4.jpg',1),
(5,'/assets/images/camere/cayenna5.jpg',1),
(6,'/assets/images/camere/cayenna6.jpg',1),
(7,'/assets/images/camere/cayenna7.jpg',1),
(8,'/assets/images/camere/habanero1.jpg',2),
(9,'/assets/images/camere/habanero2.jpg',2),
(10,'/assets/images/camere/habanero3.jpg',2),
(11,'/assets/images/camere/habanero4.jpg',2),
(12,'/assets/images/camere/habanero5.jpg',2),
(13,'/assets/images/camere/habanero6.jpg',2),
(14,'/assets/images/camere/jalape1.jpg',3),
(15,'/assets/images/camere/jalape2.jpg',3),
(16,'/assets/images/camere/jalape3.jpg',3),
(17,'/assets/images/camere/jalape4.jpg',3),
(18,'/assets/images/camere/jalape5.jpg',3),
(19,'/assets/images/camere/tabasco1.jpg',4),
(20,'/assets/images/camere/tabasco2.jpg',4),
(21,'/assets/images/camere/tabasco3.jpg',4),
(22,'/assets/images/camere/tabasco4.jpg',4),
(23,'/assets/images/camere/tabasco5.jpg',4),
(24,'/assets/images/camere/tabasco6.jpg',4),
(25,'/assets/images/camere/frigobar.jpg',4),
(26,'/assets/images/camere/frigobar.jpg',1),
(27,'/assets/images/camere/frigobar.jpg',3),
(28,'/assets/images/camere/frigobar.jpg',2);

/*!40000 ALTER TABLE `img_camera` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Azmodeus','VonDemoniae','1988-08-02','GBNIDSAO','EE99AA9','azmo@azmo.com','Azmodeus','azmo',_binary '',NULL),(2,'Gianni','Giannini','1900-01-01','GBNIDSAO','GNNWISA','gianni@gmail.com','gianni','gianni',_binary '\0',NULL),(3,'Giovanni','Giovannini','1800-02-02','GBNIDSAO','AOISF9090','lll@lll.it','user','user',_binary '\0',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;







LOCK TABLES `lista_prezzi` WRITE;
/*!40000 ALTER TABLE `lista_prezzi` DISABLE KEYS */;
INSERT INTO `lista_prezzi` VALUES (1,90,'2025-01-01','2025-02-01',1),
(2,110,'2025-05-31','2025-06-15',1),(3,98,'2025-08-01','2025-08-31',1);
/*!40000 ALTER TABLE `lista_prezzi` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `img_user` WRITE;
/*!40000 ALTER TABLE `img_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `img_user` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `img_residenza` WRITE;
/*!40000 ALTER TABLE `img_residenza` DISABLE KEYS */;
INSERT INTO `img_residenza` VALUES 
(1,'/assets/images/residenze/peperoncini.jpg');
/*!40000 ALTER TABLE `img_residenza` ENABLE KEYS */;
UNLOCK TABLES;
