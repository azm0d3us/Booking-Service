

CREATE TABLE `img_residenza` (
  `id_img_residenza` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_img_residenza`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `residenze` (
  `id_residenza` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_img_residenza` bigint DEFAULT NULL,
  PRIMARY KEY (`id_residenza`),
  UNIQUE KEY `UK_k1y1p0v5n8yfbc571ni5ss98e` (`id_img_residenza`),
  CONSTRAINT `FKpvcarxrvm7jdxm60if6cx9163` FOREIGN KEY (`id_img_residenza`) REFERENCES `img_residenza` (`id_img_residenza`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



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


CREATE TABLE `img_camera` (
  `id_img_camera` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `id_camera` bigint DEFAULT NULL,
  PRIMARY KEY (`id_img_camera`),
  KEY `FKtousp39bv6ldrj5v2b197ys8g` (`id_camera`),
  CONSTRAINT `FKtousp39bv6ldrj5v2b197ys8g` FOREIGN KEY (`id_camera`) REFERENCES `camere` (`id_camera`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `img_user` (
  `id_img_user` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_img_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
