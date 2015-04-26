-- MySQL dump 10.13  Distrib 5.6.11, for osx10.7 (x86_64)
--
-- Host: localhost    Database: tasmc
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
-- Table structure for table `Equipaje_has_Objeto`
--

DROP TABLE IF EXISTS `Equipaje_has_Objeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipaje_has_Objeto` (
  `Equipaje_idEquipaje` int(11) NOT NULL,
  `Objeto_idObjeto` int(11) NOT NULL,
  PRIMARY KEY (`Equipaje_idEquipaje`,`Objeto_idObjeto`),
  KEY `fk_Equipaje_has_Objeto_Objeto1_idx` (`Objeto_idObjeto`),
  KEY `fk_Equipaje_has_Objeto_Equipaje1_idx` (`Equipaje_idEquipaje`),
  CONSTRAINT `fk_Equipaje_has_Objeto_Equipaje1` FOREIGN KEY (`Equipaje_idEquipaje`) REFERENCES `Equipaje` (`idEquipaje`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipaje_has_Objeto_Objeto1` FOREIGN KEY (`Objeto_idObjeto`) REFERENCES `Objeto` (`idObjeto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipaje_has_Objeto`
--

LOCK TABLES `Equipaje_has_Objeto` WRITE;
/*!40000 ALTER TABLE `Equipaje_has_Objeto` DISABLE KEYS */;
INSERT INTO `Equipaje_has_Objeto` VALUES (1,1),(2,2),(3,2),(15,2),(2,3),(15,3),(3,4),(2,5),(2,6),(3,9),(2,14);
/*!40000 ALTER TABLE `Equipaje_has_Objeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Objeto`
--

DROP TABLE IF EXISTS `Objeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Objeto` (
  `idObjeto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idObjeto`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objeto`
--

LOCK TABLES `Objeto` WRITE;
/*!40000 ALTER TABLE `Objeto` DISABLE KEYS */;
INSERT INTO `Objeto` VALUES (1,'default','default'),(2,'playera','ropa\r'),(3,'blusa','ropa\r'),(4,'pantalon','ropa\r'),(5,'calceta','ropa\r'),(6,'calcetin','ropa\r'),(7,'short','ropa\r'),(8,'rompevientos','ropa\r'),(9,'chamarra','ropa\r'),(10,'zapatos','ropa\r'),(11,'zapatillas','ropa\r'),(12,'tenis','ropa\r'),(13,'vestido','ropa\r'),(14,'falda','ropa\r'),(15,'sandalias','ropa\r'),(16,'bolsa de mano','accesorio\r'),(17,'pinzas p/ cabello','accesorio'),(34,'Tablet','Electronico');
/*!40000 ALTER TABLE `Objeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Servicio`
--

DROP TABLE IF EXISTS `Servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Servicio` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombreLugar` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `rangoPrecios` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `local` varchar(45) NOT NULL,
  `horario` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idServicio`),
  UNIQUE KEY `nombreLugar_UNIQUE` (`nombreLugar`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Servicio`
--

LOCK TABLES `Servicio` WRITE;
/*!40000 ALTER TABLE `Servicio` DISABLE KEYS */;
INSERT INTO `Servicio` VALUES (9,'italiannis','Alimentos y Bebidas','300-500','XX-XX-XX-XX','54-C','10:00-22:00','Restaurant');
/*!40000 ALTER TABLE `Servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `contrasena` varchar(20) NOT NULL,
  `tipo` varchar(13) NOT NULL,
  `clasePref` varchar(20) NOT NULL,
  `catPref` int(11) NOT NULL,
  `Equipaje_idEquipaje` int(11) NOT NULL,
  `ultimaVisita` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Equipaje1_idx` (`Equipaje_idEquipaje`),
  CONSTRAINT `fk_Usuario_Equipaje1` FOREIGN KEY (`Equipaje_idEquipaje`) REFERENCES `Equipaje` (`idEquipaje`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (2,'scscf.1992@gmail.com','Burroblanc0','Administrador','1',1,1,''),(3,'erickvivanco01@hotmail.com','root','Administrador','1',1,1,''),(4,'dtsergio806@gmail.com','sergioleo6171','Placer','Primera Clase',6,1,''),(5,'cristy240771@yahoo.com.mx','leo071','Placer','Primera Clase',6,1,''),(6,'cinty_19942012@hotmail.com','abimopectore','Placer','Primera Clase',6,1,''),(7,'feerdph@gmail.com','bebin','Placer','Primera Clase',6,1,''),(8,'andres.torres@regenersis.com','acerpits','Negocios','Primera Clase',6,1,''),(10,'rosabarajas59@hotmail.com','alphainmobiliaria','Negocios','Primera Clase',6,1,''),(11,'sbarajas@itesm.mx','saraBar','Negocios','Primera Clase',6,1,'');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario_Servicio`
--

DROP TABLE IF EXISTS `Usuario_Servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario_Servicio` (
  `Usuario_idUsuario` int(11) NOT NULL,
  `Servicio_idServicio` int(11) NOT NULL,
  PRIMARY KEY (`Usuario_idUsuario`,`Servicio_idServicio`),
  KEY `fk_Usuario_has_Servicio_Servicio1_idx` (`Servicio_idServicio`),
  KEY `fk_Usuario_has_Servicio_Usuario_idx` (`Usuario_idUsuario`),
  CONSTRAINT `fk_Usuario_has_Servicio_Usuario` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Servicio_Servicio1` FOREIGN KEY (`Servicio_idServicio`) REFERENCES `Servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario_Servicio`
--

LOCK TABLES `Usuario_Servicio` WRITE;
/*!40000 ALTER TABLE `Usuario_Servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario_Servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipaje`
--

DROP TABLE IF EXISTS `equipaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipaje` (
  `idEquipaje` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idEquipaje`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipaje`
--

LOCK TABLES `equipaje` WRITE;
/*!40000 ALTER TABLE `equipaje` DISABLE KEYS */;
INSERT INTO `equipaje` VALUES (3,'Amigos'),(15,'Basquetbol'),(1,'default'),(11,'Express'),(4,'Familiar'),(13,'Futbol Soccer'),(8,'Mundial'),(2,'Negocio'),(12,'Pareja'),(6,'Playa');
/*!40000 ALTER TABLE `equipaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-26  1:59:20
