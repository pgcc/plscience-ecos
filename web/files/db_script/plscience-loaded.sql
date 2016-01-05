-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: plscience
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `actedonbehalfof`
--

DROP TABLE IF EXISTS `actedonbehalfof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actedonbehalfof` (
  `idActedOnBehalfOf` int(11) NOT NULL AUTO_INCREMENT,
  `InputPort_idPort` int(11) NOT NULL,
  `OutputPort_idPort` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idActedOnBehalfOf`),
  KEY `fk_InputPort_has_OutputPort_OutputPort1_idx` (`OutputPort_idPort`),
  KEY `fk_InputPort_has_OutputPort_InputPort1_idx` (`InputPort_idPort`),
  CONSTRAINT `fk_InputPort_has_OutputPort_InputPort1` FOREIGN KEY (`InputPort_idPort`) REFERENCES `inputport` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_InputPort_has_OutputPort_OutputPort1` FOREIGN KEY (`OutputPort_idPort`) REFERENCES `outputport` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actedonbehalfof`
--

LOCK TABLES `actedonbehalfof` WRITE;
/*!40000 ALTER TABLE `actedonbehalfof` DISABLE KEYS */;
INSERT INTO `actedonbehalfof` VALUES (1,8,3,'Task 1 acted on behalf of task1',NULL),(2,14,9,'Task 1 acted on behalf of task1',NULL),(3,19,11,'Task 1 acted on behalf of task5',NULL),(4,22,13,'Task 1 acted on behalf of task5',NULL),(5,27,16,'Task 1 acted on behalf of task5',NULL),(6,30,18,'Task 1 acted on behalf of task5',NULL),(7,35,21,'Task Sum acted on behalf of taskMultiplication',NULL),(8,38,23,'Task Sum acted on behalf of taskMultiplication',NULL),(9,42,26,'Task Sum acted on behalf of task Multiplication',NULL),(10,46,28,'Task Sum acted on behalf of task Multiplication',NULL),(11,50,31,'Task Sum acted on behalf of task Multiplication',NULL),(12,54,33,'Task Sum acted on behalf of task Multiplication',NULL),(13,58,36,'Task Sum acted on behalf of task Multiplication',NULL),(14,61,38,'Task Sum acted on behalf of task Multiplication',NULL),(15,66,41,'Task Sum acted on behalf of task Multiplication',NULL),(16,69,43,'Task Sum acted on behalf of task Multiplication',NULL),(17,74,46,'Task Sum acted on behalf of task Multiplication',NULL),(18,77,48,'Task Sum acted on behalf of task Multiplication',NULL),(19,82,51,'Task Sum acted on behalf of task Multiplication',NULL),(20,85,53,'Task Sum acted on behalf of task Multiplication',NULL),(21,91,56,'Task Sum acted on behalf of task Multiplication',NULL),(22,94,58,'Task Subtraction acted on behalf of task Multiplication',NULL),(23,99,61,'Task Sum acted on behalf of task Multiplication',NULL),(24,104,63,'Task Sum acted on behalf of task Multiplication',NULL),(25,109,65,'Task Sum acted on behalf of task Multiplication',NULL),(26,114,67,'Task Sum acted on behalf of task Multiplication',NULL),(27,119,69,'Task Sum acted on behalf of task Multiplication',NULL),(28,124,71,'Task Sum acted on behalf of task Multiplication',NULL),(29,133,73,'Task Subtraction acted on behalf of task Multiplication',NULL),(30,138,75,'Task Sum acted on behalf of task Multiplication',NULL),(31,143,77,'Task Sum acted on behalf of task Multiplication',NULL),(32,148,79,'Task Sum acted on behalf of task Multiplication',NULL),(33,153,81,'Task Sum acted on behalf of task Multiplication',NULL),(34,156,83,'Task Sum acted on behalf of task Multiplication',NULL),(35,161,86,'Task Sum acted on behalf of task Multiplication',NULL),(36,166,88,'Task Sum acted on behalf of task Multiplication',NULL),(37,171,90,'Task Sum acted on behalf of task Multiplication',NULL),(38,174,92,'Task Subtraction acted on behalf of task Multiplication',NULL),(39,181,95,'Task Sum acted on behalf of task Multiplication',NULL),(40,184,97,'Task Subtraction acted on behalf of task Multiplication',NULL),(41,196,100,'Task Sum acted on behalf of task Multiplication',NULL),(42,199,102,'Task Subtraction acted on behalf of task Multiplication',NULL),(43,207,105,'Task Sum acted on behalf of task Multiplication',NULL),(44,210,107,'Task Subtraction acted on behalf of task Multiplication',NULL),(45,216,110,'Task Sum acted on behalf of task Multiplication',NULL),(46,219,112,'Task Subtraction acted on behalf of task Multiplication',NULL),(47,231,115,'Task Sum acted on behalf of task Multiplication',NULL),(48,234,117,'Task Subtraction acted on behalf of task Multiplication',NULL),(49,239,120,'Task Sum acted on behalf of task Multiplication',NULL),(50,242,122,'Task Subtraction acted on behalf of task Multiplication',NULL),(51,247,125,'Task Sum acted on behalf of task Multiplication',NULL),(52,250,127,'Task Subtraction acted on behalf of task Multiplication',NULL),(53,255,130,'Task Sum acted on behalf of task Multiplication',NULL),(54,258,132,'Task Subtraction acted on behalf of task Multiplication',NULL),(55,263,135,'Task Sum acted on behalf of task Multiplication',NULL),(56,266,137,'Task Subtraction acted on behalf of task Multiplication',NULL),(57,271,140,'Task Sum acted on behalf of task Multiplication',NULL),(58,274,142,'Task Subtraction acted on behalf of task Multiplication',NULL);
/*!40000 ALTER TABLE `actedonbehalfof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `idActivity` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) NOT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Function` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idActivity`),
  KEY `fk_Activity_Entity1_idx` (`Entity_idEntity`),
  CONSTRAINT `fk_Activity_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'Calculus','Return the value of the executed calculation','Given a set of input values, the workflow should return the results of operations'),(3,1,'Calculo','Executar calculos matemáticos','Demonstracao da ferramenta');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_concept`
--

DROP TABLE IF EXISTS `activity_concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_concept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_concept`
--

LOCK TABLES `activity_concept` WRITE;
/*!40000 ALTER TABLE `activity_concept` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_concept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_cooperation_service`
--

DROP TABLE IF EXISTS `activity_cooperation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_cooperation_service` (
  `cooperation_service_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cooperation_service_id`,`activity_id`),
  KEY `activity_id_fka_idx` (`activity_id`),
  CONSTRAINT `activity_id_fka` FOREIGN KEY (`activity_id`) REFERENCES `activity_concept` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cooperation_service_id_act_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_cooperation_service`
--

LOCK TABLES `activity_cooperation_service` WRITE;
/*!40000 ALTER TABLE `activity_cooperation_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_cooperation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `idAgent` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Institution` int(11) NOT NULL,
  `Function` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idAgent`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `fk_Agent_Entity1_idx` (`Institution`),
  CONSTRAINT `fk_Agent_Entity1` FOREIGN KEY (`Institution`) REFERENCES `entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1,'tassio','tassio.sirqueira@ice.ufjf.br','f10354719639a6e97c1ccc7ed0c5f2d3','Tassio Ferenzini M. Sirqueira',1,'Pesquisador','Aluno de Pós-graduação'),(2,'regina','regina@acessa.com','221182760f5b980c97c7a74a94d57364','Regina Braga',1,'Pesquisadora','Professora de Pós-graduação'),(3,'humberto','humbertodalpra@gmail.com','8767bbc52e71900d1f3a50b53196d0e2','Humberto Dalpra',1,'Pesquisador','Aluno de Pós-Graduação'),(4,'marco','maraujo@acessa.com','f5888d0bb58d611107e11f7cbc41c97a','Marco Antônio Pereira Araújo',1,'Pesquisador','Professor de Pós-graduação');
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artifact`
--

DROP TABLE IF EXISTS `artifact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artifact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artifact_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artifact`
--

LOCK TABLES `artifact` WRITE;
/*!40000 ALTER TABLE `artifact` DISABLE KEYS */;
/*!40000 ALTER TABLE `artifact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artifact_cooperation_service`
--

DROP TABLE IF EXISTS `artifact_cooperation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artifact_cooperation_service` (
  `cooperation_service_id` bigint(20) NOT NULL,
  `artifact_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cooperation_service_id`,`artifact_id`),
  KEY `artifact_id_fka_idx` (`artifact_id`),
  CONSTRAINT `artifact_id_fka` FOREIGN KEY (`artifact_id`) REFERENCES `artifact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cooperation_service_id_art_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artifact_cooperation_service`
--

LOCK TABLES `artifact_cooperation_service` WRITE;
/*!40000 ALTER TABLE `artifact_cooperation_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `artifact_cooperation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_communication_service`
--

DROP TABLE IF EXISTS `code_communication_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_communication_service` (
  `communication_service_id` bigint(20) NOT NULL,
  `code_id` bigint(20) NOT NULL,
  PRIMARY KEY (`communication_service_id`,`code_id`),
  KEY `code_id_fka_idx` (`code_id`),
  CONSTRAINT `code_id_fka` FOREIGN KEY (`code_id`) REFERENCES `code` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `communication_service_id_cod_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_communication_service`
--

LOCK TABLES `code_communication_service` WRITE;
/*!40000 ALTER TABLE `code_communication_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `code_communication_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collaboration_service`
--

DROP TABLE IF EXISTS `collaboration_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collaboration_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_service_id` bigint(20) NOT NULL,
  `coordination_service_id` bigint(20) NOT NULL,
  `cooperation_service_id` bigint(20) NOT NULL,
  `communication_service_id` bigint(20) NOT NULL,
  `collaborative_service_type_id` bigint(20) NOT NULL,
  `collab_service_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  `developed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `group_service_id_fka_idx` (`group_service_id`),
  KEY `coordination_service_id_fka_idx` (`coordination_service_id`),
  KEY `cooperation_service_id_fka_idx` (`cooperation_service_id`),
  KEY `communication_service_id_fka_idx` (`communication_service_id`),
  KEY `collaboration_service_type_id_fka_idx` (`collaborative_service_type_id`),
  CONSTRAINT `collaboration_service_type_id_fka` FOREIGN KEY (`collaborative_service_type_id`) REFERENCES `collaborative_service_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `communication_service_id_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cooperation_service_id_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `coordination_service_id_fka` FOREIGN KEY (`coordination_service_id`) REFERENCES `coordination_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_service_id_fka` FOREIGN KEY (`group_service_id`) REFERENCES `group_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collaboration_service`
--

LOCK TABLES `collaboration_service` WRITE;
/*!40000 ALTER TABLE `collaboration_service` DISABLE KEYS */;
INSERT INTO `collaboration_service` VALUES (1,1,1,1,1,1,'User List UFJF','User List UFJF members.',0),(2,2,2,2,2,1,'User List USP','User List USP.',0);
/*!40000 ALTER TABLE `collaboration_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collaborative_service_type`
--

DROP TABLE IF EXISTS `collaborative_service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collaborative_service_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_service_type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collaborative_service_type`
--

LOCK TABLES `collaborative_service_type` WRITE;
/*!40000 ALTER TABLE `collaborative_service_type` DISABLE KEYS */;
INSERT INTO `collaborative_service_type` VALUES (1,'User List','Service to control a users list of a group (or institution).'),(2,'Workflow Prototyping','Service for prototyping of a scientific workflow.');
/*!40000 ALTER TABLE `collaborative_service_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_sense`
--

DROP TABLE IF EXISTS `common_sense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_sense` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common_sense_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_sense`
--

LOCK TABLES `common_sense` WRITE;
/*!40000 ALTER TABLE `common_sense` DISABLE KEYS */;
/*!40000 ALTER TABLE `common_sense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_sense_communication_service`
--

DROP TABLE IF EXISTS `common_sense_communication_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_sense_communication_service` (
  `communication_service_id` bigint(20) NOT NULL,
  `common_sense_id` bigint(20) NOT NULL,
  PRIMARY KEY (`communication_service_id`,`common_sense_id`),
  KEY `common_sense_id_fka_idx` (`common_sense_id`),
  CONSTRAINT `common_sense_id_fka` FOREIGN KEY (`common_sense_id`) REFERENCES `common_sense` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `communication_service_id_sen_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_sense_communication_service`
--

LOCK TABLES `common_sense_communication_service` WRITE;
/*!40000 ALTER TABLE `common_sense_communication_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `common_sense_communication_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `communication_protocol`
--

DROP TABLE IF EXISTS `communication_protocol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `communication_protocol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `communication_protocol_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communication_protocol`
--

LOCK TABLES `communication_protocol` WRITE;
/*!40000 ALTER TABLE `communication_protocol` DISABLE KEYS */;
/*!40000 ALTER TABLE `communication_protocol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `communication_protocol_communication_service`
--

DROP TABLE IF EXISTS `communication_protocol_communication_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `communication_protocol_communication_service` (
  `communication_service_id` bigint(20) NOT NULL,
  `communication_protocol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`communication_service_id`,`communication_protocol_id`),
  KEY `communication_protocol_id_fka_idx` (`communication_protocol_id`),
  CONSTRAINT `communication_protocol_id_fka` FOREIGN KEY (`communication_protocol_id`) REFERENCES `communication_protocol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `communication_service_id_pro_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communication_protocol_communication_service`
--

LOCK TABLES `communication_protocol_communication_service` WRITE;
/*!40000 ALTER TABLE `communication_protocol_communication_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `communication_protocol_communication_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `communication_service`
--

DROP TABLE IF EXISTS `communication_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `communication_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` tinyint(1) NOT NULL DEFAULT '0',
  `issuer` tinyint(1) NOT NULL DEFAULT '0',
  `receiver` tinyint(1) NOT NULL DEFAULT '0',
  `communicationProtocol` tinyint(1) NOT NULL DEFAULT '0',
  `commonSense` tinyint(1) NOT NULL DEFAULT '0',
  `synchronism` tinyint(1) NOT NULL DEFAULT '0',
  `transmissionMode` tinyint(1) NOT NULL DEFAULT '0',
  `compromise` tinyint(1) NOT NULL DEFAULT '0',
  `negotiation` tinyint(1) NOT NULL DEFAULT '0',
  `code` tinyint(1) NOT NULL DEFAULT '0',
  `mode` tinyint(1) NOT NULL DEFAULT '0',
  `interpretation` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communication_service`
--

LOCK TABLES `communication_service` WRITE;
/*!40000 ALTER TABLE `communication_service` DISABLE KEYS */;
INSERT INTO `communication_service` VALUES (1,0,0,0,0,0,0,0,0,0,0,0,0),(2,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `communication_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence`
--

DROP TABLE IF EXISTS `competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `competence_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence`
--

LOCK TABLES `competence` WRITE;
/*!40000 ALTER TABLE `competence` DISABLE KEYS */;
INSERT INTO `competence` VALUES (1,'JAVA Developer','It has the competence to desemvolver in JAVA language.'),(2,'C Developer','It has the competence to desemvolver in C language.'),(3,'Developer','It has the competence to develop in any programming language.'),(4,'Programmer','It has the competence to develop in any programming language.');
/*!40000 ALTER TABLE `competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence_group_service`
--

DROP TABLE IF EXISTS `competence_group_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence_group_service` (
  `group_service_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_service_id`,`competence_id`),
  KEY `competence_id_fka_idx` (`competence_id`),
  CONSTRAINT `competence_id_fka` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_service_id_fkc` FOREIGN KEY (`group_service_id`) REFERENCES `group_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_group_service`
--

LOCK TABLES `competence_group_service` WRITE;
/*!40000 ALTER TABLE `competence_group_service` DISABLE KEYS */;
INSERT INTO `competence_group_service` VALUES (1,1),(2,2),(1,3),(2,4);
/*!40000 ALTER TABLE `competence_group_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compromise`
--

DROP TABLE IF EXISTS `compromise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compromise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `compromise_name` varchar(255) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compromise`
--

LOCK TABLES `compromise` WRITE;
/*!40000 ALTER TABLE `compromise` DISABLE KEYS */;
/*!40000 ALTER TABLE `compromise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compromise_communication_service`
--

DROP TABLE IF EXISTS `compromise_communication_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compromise_communication_service` (
  `communication_service_id` bigint(20) NOT NULL,
  `compromise_id` bigint(20) NOT NULL,
  PRIMARY KEY (`communication_service_id`,`compromise_id`),
  KEY `compromise_id_fka_idx` (`compromise_id`),
  CONSTRAINT `communication_service_id_com_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `compromise_id_fka` FOREIGN KEY (`compromise_id`) REFERENCES `compromise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compromise_communication_service`
--

LOCK TABLES `compromise_communication_service` WRITE;
/*!40000 ALTER TABLE `compromise_communication_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `compromise_communication_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_xml`
--

DROP TABLE IF EXISTS `concept_xml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_xml` (
  `id_concept_xml` bigint(20) NOT NULL AUTO_INCREMENT,
  `service` varchar(45) NOT NULL,
  `concept_service` varchar(45) NOT NULL,
  `ratio` double DEFAULT NULL,
  `has_concept` tinyint(1) NOT NULL DEFAULT '0',
  `validity` tinyint(1) NOT NULL DEFAULT '0',
  `conceptService1` varchar(45) DEFAULT NULL,
  `conceptService2` varchar(45) DEFAULT NULL,
  `id_struct_xml` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_concept_xml`),
  KEY `id_struct_xml_fka_idx` (`id_struct_xml`),
  CONSTRAINT `id_struct_xml_fka` FOREIGN KEY (`id_struct_xml`) REFERENCES `interoperability_struct_xml` (`id_struct_xml`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_xml`
--

LOCK TABLES `concept_xml` WRITE;
/*!40000 ALTER TABLE `concept_xml` DISABLE KEYS */;
INSERT INTO `concept_xml` VALUES (1,'Group Service','Group',NULL,0,0,NULL,NULL,1),(2,'Group Service','Participant',NULL,0,0,NULL,NULL,1),(3,'Coordination Service','Role',100,1,0,'Manager','Manager',1),(4,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',1),(5,'Coordination Service','Status',NULL,0,0,NULL,NULL,1),(6,'Group Service','Group',NULL,0,1,NULL,NULL,2),(7,'Group Service','Participant',NULL,0,0,NULL,NULL,2),(8,'Coordination Service','Role',100,1,0,'Manager','Manager',2),(9,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',2),(10,'Coordination Service','Status',NULL,0,0,NULL,NULL,2),(11,'Group Service','Group',NULL,0,0,NULL,NULL,3),(12,'Group Service','Participant',NULL,0,0,NULL,NULL,3),(13,'Coordination Service','Role',100,1,0,'Manager','Manager',3),(14,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',3),(15,'Coordination Service','Status',NULL,0,0,NULL,NULL,3),(16,'Group Service','Group',NULL,0,1,NULL,NULL,4),(17,'Group Service','Participant',NULL,0,0,NULL,NULL,4),(18,'Coordination Service','Role',100,1,0,'Manager','Manager',4),(19,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',4),(20,'Coordination Service','Status',NULL,0,0,NULL,NULL,4);
/*!40000 ALTER TABLE `concept_xml` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooperation_service`
--

DROP TABLE IF EXISTS `cooperation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cooperation_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity` tinyint(1) NOT NULL DEFAULT '0',
  `task` tinyint(1) NOT NULL DEFAULT '0',
  `product` tinyint(1) NOT NULL DEFAULT '0',
  `artifact` tinyint(1) NOT NULL DEFAULT '0',
  `sharedSpace` tinyint(1) NOT NULL DEFAULT '0',
  `resource` tinyint(1) NOT NULL DEFAULT '0',
  `share` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperation_service`
--

LOCK TABLES `cooperation_service` WRITE;
/*!40000 ALTER TABLE `cooperation_service` DISABLE KEYS */;
INSERT INTO `cooperation_service` VALUES (1,0,0,0,0,0,0,0),(2,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `cooperation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordination_service`
--

DROP TABLE IF EXISTS `coordination_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordination_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workPlan` tinyint(1) NOT NULL DEFAULT '0',
  `deadline` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `role` tinyint(1) NOT NULL DEFAULT '0',
  `policy` tinyint(1) NOT NULL DEFAULT '0',
  `monitoring` tinyint(1) NOT NULL DEFAULT '0',
  `coupling` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordination_service`
--

LOCK TABLES `coordination_service` WRITE;
/*!40000 ALTER TABLE `coordination_service` DISABLE KEYS */;
INSERT INTO `coordination_service` VALUES (1,0,0,1,1,0,0,0),(2,0,0,1,1,0,0,0);
/*!40000 ALTER TABLE `coordination_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity` (
  `idEntity` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Acronym` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idEntity`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES (1,'Universidade Federal de Juiz de Fora','UFJF','Mestrado em Ciência da Computação'),(2,'Instituto Federal de Educação, Ciência e Tecnologia do Sudeste de Minas Gerais','IF Sudeste MG','Bacharelado em Sistemas de Informação');
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment`
--

DROP TABLE IF EXISTS `experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment` (
  `idExperiment` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `idAgent` int(11) DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `DateStarted` date DEFAULT NULL,
  `DateEnded` date DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `number_stages` int(11) DEFAULT NULL,
  `parsifal_review` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExperiment`),
  KEY `fk_Expiriment_Entity1_idx` (`Entity_idEntity`),
  KEY `fk_Expiriment_Activity1_idx` (`Activity_idActivity`),
  KEY `fk_Experiment_Agent1_idx` (`idAgent`),
  CONSTRAINT `fk_Experiment_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expiriment_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expiriment_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment`
--

LOCK TABLES `experiment` WRITE;
/*!40000 ALTER TABLE `experiment` DISABLE KEYS */;
INSERT INTO `experiment` VALUES (1,1,1,1,'Mathematical operations','2015-06-18',NULL,'Given a set of input values, the workflow should return the results of operations','01.00',NULL,NULL),(2,1,3,1,'Experimento matematico','2015-10-08','2015-10-10','Demonstracao ao NEnC','01.00',NULL,NULL),(3,NULL,NULL,NULL,'Teste','2015-12-09','2015-12-30','Teste de Criação','',2,NULL);
/*!40000 ALTER TABLE `experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_services`
--

DROP TABLE IF EXISTS `experiment_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment_services` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(255) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `latestTime_used` date DEFAULT NULL,
  `idExperiment` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_experiment_services_Experiment1_idx` (`idExperiment`),
  CONSTRAINT `fk_experiment_services_Experiment1` FOREIGN KEY (`idExperiment`) REFERENCES `experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_services`
--

LOCK TABLES `experiment_services` WRITE;
/*!40000 ALTER TABLE `experiment_services` DISABLE KEYS */;
INSERT INTO `experiment_services` VALUES (451,'Teste 1',1,'2015-12-29',3),(501,'Teste 2',2,'2015-12-29',3);
/*!40000 ALTER TABLE `experiment_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_service`
--

DROP TABLE IF EXISTS `group_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant` tinyint(1) NOT NULL DEFAULT '0',
  `belief` tinyint(1) NOT NULL DEFAULT '0',
  `confidence` tinyint(1) NOT NULL DEFAULT '0',
  `motivation` tinyint(1) NOT NULL DEFAULT '0',
  `groupp` tinyint(1) NOT NULL DEFAULT '0',
  `competence` tinyint(1) NOT NULL DEFAULT '0',
  `goal` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_service`
--

LOCK TABLES `group_service` WRITE;
/*!40000 ALTER TABLE `group_service` DISABLE KEYS */;
INSERT INTO `group_service` VALUES (1,1,0,0,0,1,1,0),(2,1,0,0,0,1,1,0);
/*!40000 ALTER TABLE `group_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inputport`
--

DROP TABLE IF EXISTS `inputport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inputport` (
  `idPort` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `fk_Port_Task1` (`Task_idTask`),
  CONSTRAINT `fk_Port_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inputport`
--

LOCK TABLES `inputport` WRITE;
/*!40000 ALTER TABLE `inputport` DISABLE KEYS */;
INSERT INTO `inputport` VALUES (1,'Starting port workflow','Port of task with valeu ',1,'6',NULL),(2,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(3,'Starting port workflow','Port of task with valeu ',1,'8',NULL),(4,'Starting port workflow','Port of task with valeu ',1,'8',NULL),(5,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(6,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(7,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(8,'Starting port task1','1 task input with valueinput 0',1,'0',NULL),(9,'Starting port task1','1 task input with valueinput 0',1,'0',NULL),(10,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(11,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(12,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(13,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(14,'Starting port task1','1 task input with valueinput 12',1,'12',NULL),(15,'Starting port task1','1 task input with valueinput 12',1,'12',NULL),(16,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(17,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(18,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(19,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(20,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(21,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(22,'Starting port task5','5 task input with valueinput -12',5,'-12',NULL),(23,'Starting port task5','5 task input with valueinput -12',5,'-12',NULL),(24,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(25,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(26,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(27,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(28,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(29,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(30,'Starting port task5','5 task input with valueinput 12',5,'12',NULL),(31,'Starting port task5','5 task input with valueinput 12',5,'12',NULL),(32,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(33,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(34,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(35,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(36,'Starting port taskMultiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(37,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(38,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(39,'Starting port taskMultiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(40,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(41,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(42,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(43,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(44,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(45,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(46,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(47,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(48,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(49,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(50,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(51,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(52,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(53,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(54,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(55,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(56,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(57,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(58,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(59,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(60,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(61,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(62,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(63,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(64,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(65,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(66,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(67,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(68,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(69,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(70,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(71,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(72,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(73,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(74,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(75,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(76,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(77,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(78,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(79,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(80,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(81,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(82,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(83,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(84,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(85,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(86,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(87,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(88,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(89,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(90,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(91,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(92,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(93,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(94,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(95,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(96,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(97,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(98,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(99,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(100,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(101,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(102,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(103,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(104,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(105,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(106,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(107,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(108,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(109,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(110,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(111,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(112,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(113,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(114,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(115,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(116,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(117,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(118,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(119,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(120,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(121,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(122,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(123,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(124,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(125,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(126,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(127,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(128,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(129,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(130,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(131,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(132,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(133,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(134,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(135,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(136,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(137,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(138,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(139,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(140,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(141,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(142,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(143,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(144,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(145,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(146,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(147,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(148,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(149,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(150,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(151,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(152,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(153,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(154,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(155,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(156,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(157,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(158,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(159,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(160,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(161,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(162,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(163,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(164,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(165,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(166,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(167,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(168,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(169,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(170,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(171,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(172,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(173,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(174,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(175,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(176,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(177,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(178,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(179,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(180,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(181,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(182,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(183,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(184,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(185,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(186,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(187,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(188,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(189,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(190,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(191,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(192,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(193,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(194,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(195,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(196,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(197,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(198,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(199,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(200,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(201,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(202,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(203,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(204,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(205,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(206,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(207,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(208,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(209,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(210,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(211,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(212,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(213,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(214,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(215,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(216,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(217,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(218,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(219,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(220,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(221,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(222,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(223,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(224,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(225,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(226,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(227,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(228,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(229,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(230,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(231,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(232,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(233,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(234,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(235,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(236,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(237,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(238,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(239,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(240,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(241,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(242,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(243,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(244,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(245,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(246,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(247,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(248,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(249,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(250,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(251,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(252,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(253,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(254,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(255,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(256,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(257,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(258,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(259,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(260,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(261,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(262,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(263,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(264,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(265,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(266,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(267,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(268,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(269,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(270,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(271,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(272,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(273,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(274,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(275,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL);
/*!40000 ALTER TABLE `inputport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interoperability_struct_xml`
--

DROP TABLE IF EXISTS `interoperability_struct_xml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interoperability_struct_xml` (
  `id_struct_xml` bigint(20) NOT NULL AUTO_INCREMENT,
  `interoperability_name` varchar(45) NOT NULL DEFAULT 'Teste',
  `first_service_id` bigint(20) NOT NULL,
  `second_service_id` bigint(20) NOT NULL,
  `agent_id` bigint(20) NOT NULL DEFAULT '100',
  `first_type_service` varchar(45) NOT NULL,
  `second_type_service` varchar(45) NOT NULL,
  PRIMARY KEY (`id_struct_xml`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interoperability_struct_xml`
--

LOCK TABLES `interoperability_struct_xml` WRITE;
/*!40000 ALTER TABLE `interoperability_struct_xml` DISABLE KEYS */;
INSERT INTO `interoperability_struct_xml` VALUES (1,'1-User List UFJF-2-User List USP-100',1,2,100,'User List','User List'),(2,'1-User List UFJF-2-User List USP-100',1,2,100,'User List','User List'),(3,'1-User List UFJF-2-User List USP-100',1,2,100,'User List','User List'),(4,'1-User List UFJF-2-User List USP-100',1,2,100,'User List','User List');
/*!40000 ALTER TABLE `interoperability_struct_xml` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ispartof`
--

DROP TABLE IF EXISTS `ispartof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ispartof` (
  `idIsPartOf` int(11) NOT NULL AUTO_INCREMENT,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idIsPartOf`),
  KEY `fk_Agent_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`),
  KEY `fk_Agent_has_ResearchGroup_Agent1_idx` (`Agent_idAgent`),
  CONSTRAINT `fk_Agent_has_ResearchGroup_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agent_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `researchgroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ispartof`
--

LOCK TABLES `ispartof` WRITE;
/*!40000 ALTER TABLE `ispartof` DISABLE KEYS */;
INSERT INTO `ispartof` VALUES (1,2,1,'Professora de Pós-graduação'),(2,1,1,'Aluno de Pós-graduação'),(3,3,1,'Aluno de Pós-graduação'),(4,4,1,'Professor de Pós-graduação');
/*!40000 ALTER TABLE `ispartof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outputport`
--

DROP TABLE IF EXISTS `outputport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outputport` (
  `idPort` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `fk_Port_Task1_idx` (`Task_idTask`),
  CONSTRAINT `fk_Port_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outputport`
--

LOCK TABLES `outputport` WRITE;
/*!40000 ALTER TABLE `outputport` DISABLE KEYS */;
INSERT INTO `outputport` VALUES (1,'Ended Port Task 1','Task output with valueoutput 11',1,'11',NULL),(2,'Ended Port Task 1','Task output with valueoutput 16',1,'16',NULL),(3,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(4,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(5,'Ended Port Task 1','Task output with valueoutput 15',1,'15',NULL),(6,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(7,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(8,'Ended Port Task 1','Task output with valueoutput 20',1,'20',NULL),(9,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(10,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(11,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(12,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(13,'Ended Port Task 1','Task output with valueoutput -12',1,'-12',NULL),(14,'Ended Port Task 1','Task output with valueoutput -12',1,'-12',NULL),(15,'Ended Port Task 5','Task output with valueoutput 96',5,'96',NULL),(16,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(17,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(18,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(19,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(20,'Ended Port Task 5','Task output with valueoutput 96',5,'96',NULL),(21,'Ended Port Task Sum','Task Sumoutput with valueoutput 8',1,'8',NULL),(22,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(23,'Ended Port Task Sum','Task Sumoutput with valueoutput -12',1,'-12',NULL),(24,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(25,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 96',5,'96',NULL),(26,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(27,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(28,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(29,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(30,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(31,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(32,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(33,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(34,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(35,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(36,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(37,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(38,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(39,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(40,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(41,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(42,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(43,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(44,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(45,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(46,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(47,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(48,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(49,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(50,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(51,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(52,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(53,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(54,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(55,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(56,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(57,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(58,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(59,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(60,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(61,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(62,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(63,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(64,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(65,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(66,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(67,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(68,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(69,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(70,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(71,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(72,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(73,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(74,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(75,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(76,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(77,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(78,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(79,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(80,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(81,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(82,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(83,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(84,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(85,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 96',5,'96',NULL),(86,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(87,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(88,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(89,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(90,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(91,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(92,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(93,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(94,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(95,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(96,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(97,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(98,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(99,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(100,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(101,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(102,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(103,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(104,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(105,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(106,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(107,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(108,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(109,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(110,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(111,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(112,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(113,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(114,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(115,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(116,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(117,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(118,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(119,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(120,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(121,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(122,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(123,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(124,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(125,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(126,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(127,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(128,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(129,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(130,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(131,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(132,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(133,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(134,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(135,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(136,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(137,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(138,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(139,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(140,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(141,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(142,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(143,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(144,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL);
/*!40000 ALTER TABLE `outputport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Product A','Product A test.');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_cooperation_service`
--

DROP TABLE IF EXISTS `product_cooperation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_cooperation_service` (
  `cooperation_service_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cooperation_service_id`,`product_id`),
  KEY `product_id_fka_idx` (`product_id`),
  CONSTRAINT `cooperation_service_id_pro_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_id_fka` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_cooperation_service`
--

LOCK TABLES `product_cooperation_service` WRITE;
/*!40000 ALTER TABLE `product_cooperation_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_cooperation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `researchgroup`
--

DROP TABLE IF EXISTS `researchgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `researchgroup` (
  `idResearchGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL,
  PRIMARY KEY (`idResearchGroup`),
  KEY `fk_Agent_has_Expiriment_Agent1_idx` (`Agent_idAgent_chef`),
  CONSTRAINT `fk_Agent_has_Expiriment_Agent1` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `researchgroup`
--

LOCK TABLES `researchgroup` WRITE;
/*!40000 ALTER TABLE `researchgroup` DISABLE KEYS */;
INSERT INTO `researchgroup` VALUES (1,'NEnC','Núcleo de Engenharia do Conhecimento',2);
/*!40000 ALTER TABLE `researchgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_coordination_service`
--

DROP TABLE IF EXISTS `role_coordination_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_coordination_service` (
  `coordination_service_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`coordination_service_id`,`role_id`),
  KEY `role_id_fka_idx` (`role_id`),
  CONSTRAINT `coordination_service_id_fkb` FOREIGN KEY (`coordination_service_id`) REFERENCES `coordination_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fka` FOREIGN KEY (`role_id`) REFERENCES `roler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_coordination_service`
--

LOCK TABLES `role_coordination_service` WRITE;
/*!40000 ALTER TABLE `role_coordination_service` DISABLE KEYS */;
INSERT INTO `role_coordination_service` VALUES (1,1),(2,1),(1,2),(2,3);
/*!40000 ALTER TABLE `role_coordination_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roler`
--

DROP TABLE IF EXISTS `roler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `hierarchy_level` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roler`
--

LOCK TABLES `roler` WRITE;
/*!40000 ALTER TABLE `roler` DISABLE KEYS */;
INSERT INTO `roler` VALUES (1,'Manager',1,'Manager of a scientific experiment.'),(2,'Scientist',5,'Scientist of a scientific experiment.'),(3,'Researcher',5,'Researcher of a scientific experiment.'),(4,'Supervisor',3,'Supervisor of a scientific experiment.');
/*!40000 ALTER TABLE `roler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',550);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sgwfc`
--

DROP TABLE IF EXISTS `sgwfc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sgwfc` (
  `idSGWfC` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSGWfC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sgwfc`
--

LOCK TABLES `sgwfc` WRITE;
/*!40000 ALTER TABLE `sgwfc` DISABLE KEYS */;
INSERT INTO `sgwfc` VALUES (1,'Kepler','Kepler é um software livre do sistema para a concepção, execução, reutilizando, evoluindo, arquivamento e compartilhamento científicos'),(2,'Taverna Workbench','Taverna é um conjunto de ferramentas usadas para criar e executar workflows científicos e ajuda na experimentação in silico');
/*!40000 ALTER TABLE `sgwfc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_coordination_service`
--

DROP TABLE IF EXISTS `status_coordination_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_coordination_service` (
  `coordination_service_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`coordination_service_id`,`status_id`),
  KEY `status_id_fka_idx` (`status_id`),
  CONSTRAINT `coordination_service_id_sta_fka` FOREIGN KEY (`coordination_service_id`) REFERENCES `coordination_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_id_fka` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_coordination_service`
--

LOCK TABLES `status_coordination_service` WRITE;
/*!40000 ALTER TABLE `status_coordination_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_coordination_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps_scientific_experimentation`
--

DROP TABLE IF EXISTS `steps_scientific_experimentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steps_scientific_experimentation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_step` varchar(255) NOT NULL,
  `number_step` int(11) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps_scientific_experimentation`
--

LOCK TABLES `steps_scientific_experimentation` WRITE;
/*!40000 ALTER TABLE `steps_scientific_experimentation` DISABLE KEYS */;
INSERT INTO `steps_scientific_experimentation` VALUES (201,'Problem Investigation',1,'Primeira etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),(251,'Experiment Prototyping',2,'Segunda etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),(301,'Experiment Execution',3,'Terceira etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),(351,'Results Publication',4,'Quarta etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).');
/*!40000 ALTER TABLE `steps_scientific_experimentation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps_service`
--

DROP TABLE IF EXISTS `steps_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steps_service` (
  `collab_service_id` bigint(20) NOT NULL,
  `step_id` bigint(20) NOT NULL,
  PRIMARY KEY (`collab_service_id`,`step_id`),
  KEY `collab_service_fk_idx` (`collab_service_id`),
  KEY `step_id_fk` (`step_id`),
  CONSTRAINT `collab_service_fk` FOREIGN KEY (`collab_service_id`) REFERENCES `collaboration_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `step_id_fk` FOREIGN KEY (`step_id`) REFERENCES `steps_scientific_experimentation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps_service`
--

LOCK TABLES `steps_service` WRITE;
/*!40000 ALTER TABLE `steps_service` DISABLE KEYS */;
INSERT INTO `steps_service` VALUES (1,201),(2,201),(2,251),(2,301),(2,351);
/*!40000 ALTER TABLE `steps_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `idTask` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idTask`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Sum','Integer','Sum of two values'),(2,'Sum','Float','Sum of two values'),(3,'Subtraction','Integer','Subtraction of two values'),(4,'Subtraction','Float','Subtraction of two values'),(5,'Multiplication','Integer','Multiplication of two values'),(6,'Multiplication','Float','Multiplication of two values'),(7,'Division','Float','Division of two values');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_concept`
--

DROP TABLE IF EXISTS `task_concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_concept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_concept`
--

LOCK TABLES `task_concept` WRITE;
/*!40000 ALTER TABLE `task_concept` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_concept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_cooperation_service`
--

DROP TABLE IF EXISTS `task_cooperation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_cooperation_service` (
  `cooperation_service_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cooperation_service_id`,`task_id`),
  KEY `task_id_fka_idx` (`task_id`),
  CONSTRAINT `cooperation_service_id_tas_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `task_id_fka` FOREIGN KEY (`task_id`) REFERENCES `task_concept` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_cooperation_service`
--

LOCK TABLES `task_cooperation_service` WRITE;
/*!40000 ALTER TABLE `task_cooperation_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_cooperation_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taverna_workflow`
--

DROP TABLE IF EXISTS `taverna_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taverna_workflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `t2flow` mediumtext,
  `idAgent` int(11) NOT NULL,
  `experiment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_taverna_workflow_Agent1_idx` (`idAgent`),
  KEY `fk_taverna_workflow_Experiment1_idx` (`experiment_id`),
  CONSTRAINT `fk_taverna_workflow_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_taverna_workflow_Experiment1` FOREIGN KEY (`experiment_id`) REFERENCES `experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taverna_workflow`
--

LOCK TABLES `taverna_workflow` WRITE;
/*!40000 ALTER TABLE `taverna_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `taverna_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taverna_workflow_input`
--

DROP TABLE IF EXISTS `taverna_workflow_input`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taverna_workflow_input` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_taverna_workflow_input_taverna_workflow1_idx` (`taverna_workflow_id`),
  CONSTRAINT `fk_taverna_workflow_input_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taverna_workflow_input`
--

LOCK TABLES `taverna_workflow_input` WRITE;
/*!40000 ALTER TABLE `taverna_workflow_input` DISABLE KEYS */;
/*!40000 ALTER TABLE `taverna_workflow_input` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taverna_workflow_run`
--

DROP TABLE IF EXISTS `taverna_workflow_run`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taverna_workflow_run` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_taverna_workflow_run_taverna_workflow1_idx` (`taverna_workflow_id`),
  CONSTRAINT `fk_taverna_workflow_run_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taverna_workflow_run`
--

LOCK TABLES `taverna_workflow_run` WRITE;
/*!40000 ALTER TABLE `taverna_workflow_run` DISABLE KEYS */;
/*!40000 ALTER TABLE `taverna_workflow_run` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taverna_workflow_run_input_value`
--

DROP TABLE IF EXISTS `taverna_workflow_run_input_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taverna_workflow_run_input_value` (
  `id` bigint(20) NOT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `taverna_workflow_input_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_taverna_workflow_run_input_value_taverna_workflow_input1_idx` (`taverna_workflow_input_id`),
  CONSTRAINT `fk_taverna_workflow_run_input_value_taverna_workflow_input1` FOREIGN KEY (`taverna_workflow_input_id`) REFERENCES `taverna_workflow_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taverna_workflow_run_input_value`
--

LOCK TABLES `taverna_workflow_run_input_value` WRITE;
/*!40000 ALTER TABLE `taverna_workflow_run_input_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `taverna_workflow_run_input_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used`
--

DROP TABLE IF EXISTS `used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `used` (
  `idUsed` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idUsed`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_Used_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Used_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task100` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used`
--

LOCK TABLES `used` WRITE;
/*!40000 ALTER TABLE `used` DISABLE KEYS */;
INSERT INTO `used` VALUES (1,1,1,'Task was used in workflow'),(2,1,1,'Task was used in workflow'),(3,1,2,'Task was used in workflow'),(4,1,2,'Task was used in workflow'),(5,1,2,'Task was used in workflow'),(6,1,2,'Task 1 was used in workflow 2'),(7,1,3,'Task was used in workflow'),(8,1,3,'Task was used in workflow'),(9,1,3,'Task was used in workflow'),(10,1,3,'Task was used in workflow'),(11,1,3,'Task 1 was used in workflow 3'),(12,1,4,'Task was used in workflow'),(13,1,4,'Task was used in workflow'),(14,1,4,'Task was used in workflow'),(15,5,4,'Task 5 was used in workflow 4'),(16,1,4,'Task was used in workflow'),(17,5,4,'Task 5 was used in workflow 4'),(18,1,5,'Task was used in workflow'),(19,1,5,'Task was used in workflow'),(20,1,5,'Task was used in workflow'),(21,5,5,'Task 5 was used in workflow 5'),(22,1,5,'Task was used in workflow'),(23,5,5,'Task 5 was used in workflow 5'),(24,1,4,'TaskSumwas used in workflowSimpleCount'),(25,1,4,'TaskSumwas used in workflowSimpleCount'),(26,1,4,'TaskSumwas used in workflowSimpleCount'),(27,5,4,'Task Multiplication was used in workflow SimpleCount'),(28,1,4,'TaskSumwas used in workflowSimpleCount'),(29,5,4,'Task Multiplication was used in workflow SimpleCount'),(30,1,4,'Task Sum was used in workflow SimpleCount'),(31,1,4,'Task Sum was used in workflow SimpleCount'),(32,5,4,'Task Multiplication was used in workflow SimpleCount'),(33,1,4,'Task Sum was used in workflow SimpleCount'),(34,1,4,'Task Sum was used in workflow SimpleCount'),(35,5,4,'Task Multiplication was used in workflow SimpleCount'),(36,1,4,'Task Sum was used in workflow SimpleCount'),(37,1,4,'Task Sum was used in workflow SimpleCount'),(38,5,4,'Task Multiplication was used in workflow SimpleCount'),(39,1,4,'Task Sum was used in workflow SimpleCount'),(40,1,4,'Task Sum was used in workflow SimpleCount'),(41,5,4,'Task Multiplication was used in workflow SimpleCount'),(42,1,4,'Task Sum was used in workflow SimpleCount'),(43,1,4,'Task Sum was used in workflow SimpleCount'),(44,5,4,'Task Multiplication was used in workflow SimpleCount'),(45,1,4,'Task Sum was used in workflow SimpleCount'),(46,5,4,'Task Multiplication was used in workflow SimpleCount'),(47,1,4,'Task Sum was used in workflow SimpleCount'),(48,1,4,'Task Sum was used in workflow SimpleCount'),(49,1,4,'Task Sum was used in workflow SimpleCount'),(50,5,4,'Task Multiplication was used in workflow SimpleCount'),(51,1,4,'Task Sum was used in workflow SimpleCount'),(52,5,4,'Task Multiplication was used in workflow SimpleCount'),(53,1,4,'Task Sum was used in workflow SimpleCount'),(54,1,4,'Task Sum was used in workflow SimpleCount'),(55,1,4,'Task Sum was used in workflow SimpleCount'),(56,5,4,'Task Multiplication was used in workflow SimpleCount'),(57,1,4,'Task Sum was used in workflow SimpleCount'),(58,5,4,'Task Multiplication was used in workflow SimpleCount'),(59,1,4,'Task Sum was used in workflow SimpleCount'),(60,1,4,'Task Sum was used in workflow SimpleCount'),(61,1,4,'Task Sum was used in workflow SimpleCount'),(62,5,4,'Task Multiplication was used in workflow SimpleCount'),(63,1,4,'Task Sum was used in workflow SimpleCount'),(64,5,4,'Task Multiplication was used in workflow SimpleCount'),(65,1,4,'Task Sum was used in workflow SimpleCount'),(192,1,6,'Task Sum was used in workflow Demonstracao'),(193,3,6,'Task Subtraction was used in workflow Demonstracao'),(194,1,6,'Task Sum was used in workflow Demonstracao'),(195,5,6,'Task Multiplication was used in workflow Demonstracao'),(196,3,6,'Task Subtraction was used in workflow Demonstracao'),(197,5,6,'Task Multiplication was used in workflow Demonstracao'),(198,1,6,'Task Sum was used in workflow Demonstracao'),(199,3,6,'Task Subtraction was used in workflow Demonstracao'),(200,1,6,'Task Sum was used in workflow Demonstracao'),(201,5,6,'Task Multiplication was used in workflow Demonstracao'),(202,3,6,'Task Subtraction was used in workflow Demonstracao'),(203,5,6,'Task Multiplication was used in workflow Demonstracao'),(204,1,6,'Task Sum was used in workflow Demonstracao'),(205,3,6,'Task Subtraction was used in workflow Demonstracao'),(206,1,6,'Task Sum was used in workflow Demonstracao'),(207,5,6,'Task Multiplication was used in workflow Demonstracao'),(208,3,6,'Task Subtraction was used in workflow Demonstracao'),(209,5,6,'Task Multiplication was used in workflow Demonstracao');
/*!40000 ALTER TABLE `used` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasassociatedwith`
--

DROP TABLE IF EXISTS `wasassociatedwith`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasassociatedwith` (
  `idWasAssociatedWith` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWasAssociatedWith`),
  KEY `fk_Workflow_has_Expiriment_Expiriment1_idx` (`Experiment_Experiment`),
  KEY `fk_Workflow_has_Expiriment_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Expiriment_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Expiriment_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasassociatedwith`
--

LOCK TABLES `wasassociatedwith` WRITE;
/*!40000 ALTER TABLE `wasassociatedwith` DISABLE KEYS */;
INSERT INTO `wasassociatedwith` VALUES (1,1,1,NULL),(2,2,1,NULL),(3,3,1,NULL),(4,4,1,NULL),(5,5,1,NULL),(6,1,1,'Workflow was attributed to experimento'),(7,1,1,'Workflow was attributed to experimento'),(8,2,1,'Workflow was attributed to experimento'),(9,3,1,'Workflow was attributed to experimento'),(10,4,1,'Workflow was attributed to experimento'),(11,5,1,'Workflow was attributed to experimento'),(12,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(13,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(14,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(15,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(16,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(17,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(18,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(41,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico'),(42,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico'),(43,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico');
/*!40000 ALTER TABLE `wasassociatedwith` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wascontroledby`
--

DROP TABLE IF EXISTS `wascontroledby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wascontroledby` (
  `idWasControledBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  PRIMARY KEY (`idWasControledBy`),
  KEY `fk_WasControledBy_Activity1_idx` (`Activity_idActivity`),
  KEY `fk_WasControledBy_Agent1_idx` (`Agent_idAgent`),
  CONSTRAINT `fk_WasControledBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasControledBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wascontroledby`
--

LOCK TABLES `wascontroledby` WRITE;
/*!40000 ALTER TABLE `wascontroledby` DISABLE KEYS */;
/*!40000 ALTER TABLE `wascontroledby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasderivedfrom`
--

DROP TABLE IF EXISTS `wasderivedfrom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasderivedfrom` (
  `idWasDerivedFrom` int(11) NOT NULL AUTO_INCREMENT,
  `DerivedOf` int(11) NOT NULL,
  `DerivedTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`idWasDerivedFrom`),
  KEY `fk_WasDerivedFrom_Workflow1_idx` (`DerivedOf`),
  KEY `fk_WasDerivedFrom_Workflow2_idx` (`DerivedTo`),
  CONSTRAINT `fk_WasDerivedFrom_Workflow1` FOREIGN KEY (`DerivedOf`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasDerivedFrom_Workflow2` FOREIGN KEY (`DerivedTo`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasderivedfrom`
--

LOCK TABLES `wasderivedfrom` WRITE;
/*!40000 ALTER TABLE `wasderivedfrom` DISABLE KEYS */;
INSERT INTO `wasderivedfrom` VALUES (1,1,2,'Evolution'),(2,2,3,'Evolution');
/*!40000 ALTER TABLE `wasderivedfrom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasendedby`
--

DROP TABLE IF EXISTS `wasendedby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasendedby` (
  `idWasEndedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateEnded` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWasEndedBy`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_WasEndedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasEndedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasendedby`
--

LOCK TABLES `wasendedby` WRITE;
/*!40000 ALTER TABLE `wasendedby` DISABLE KEYS */;
INSERT INTO `wasendedby` VALUES (1,1,1,'2015-08-06 21:12:24','task 1 ended to activity 1'),(2,1,1,'2015-08-06 21:21:27','task 1 ended to activity 1'),(3,1,1,'2015-08-06 21:29:02','task 1 ended to activity 1'),(4,1,1,'2015-08-06 21:29:03','task 1 ended to activity 1'),(5,1,1,'2015-08-06 21:31:49','task 1 ended to activity 1'),(6,1,1,'2015-08-06 21:31:50','task 1 ended to activity 1'),(7,1,1,'2015-08-06 21:31:51','task 1 ended to activity 1'),(8,1,1,'2015-08-06 21:36:37','task 1 ended to activity 1'),(9,1,1,'2015-08-06 21:36:39','task 1 ended to activity 1'),(10,5,1,'2015-08-06 21:36:39','task 5 ended to activity 1'),(11,1,1,'2015-08-06 21:39:19','task 1 ended to activity 1'),(12,1,1,'2015-08-06 21:39:20','task 1 ended to activity 1'),(13,5,1,'2015-08-06 21:39:20','task 5 ended to activity 1'),(41,1,3,'2015-10-08 16:00:46','Task Sum ended to activity Calculo'),(42,3,3,'2015-10-08 16:00:47','Task Subtraction ended to activity Calculo'),(43,5,3,'2015-10-08 16:00:47','Task Multiplication ended to activity Calculo'),(44,1,3,'2015-10-19 17:06:36','Task Sum ended to activity Calculo'),(45,3,3,'2015-10-19 17:06:37','Task Subtraction ended to activity Calculo'),(46,5,3,'2015-10-19 17:06:37','Task Multiplication ended to activity Calculo'),(47,1,3,'2015-10-19 19:07:43','Task Sum ended to activity Calculo'),(48,3,3,'2015-10-19 19:07:47','Task Subtraction ended to activity Calculo'),(49,5,3,'2015-10-19 19:07:47','Task Multiplication ended to activity Calculo');
/*!40000 ALTER TABLE `wasendedby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasendedbywt`
--

DROP TABLE IF EXISTS `wasendedbywt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasendedbywt` (
  `idWasEndedByWT` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Ended` datetime DEFAULT NULL,
  PRIMARY KEY (`idWasEndedByWT`),
  KEY `fk_Workflow_has_Task_Task2_idx` (`Task_idTask`),
  KEY `fk_Workflow_has_Task_Workflow2_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Task_Task2` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Task_Workflow2` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasendedbywt`
--

LOCK TABLES `wasendedbywt` WRITE;
/*!40000 ALTER TABLE `wasendedbywt` DISABLE KEYS */;
/*!40000 ALTER TABLE `wasendedbywt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasgeneratedby`
--

DROP TABLE IF EXISTS `wasgeneratedby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasgeneratedby` (
  `idWasGeneratedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Experiment_Experiment` int(11) NOT NULL,
  `ResearchGroup_idResearchGroup` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWasGeneratedBy`),
  KEY `fk_Experiment_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`),
  KEY `fk_Experiment_has_ResearchGroup_Experiment1_idx` (`Experiment_Experiment`),
  CONSTRAINT `fk_Experiment_has_ResearchGroup_Experiment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Experiment_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `researchgroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasgeneratedby`
--

LOCK TABLES `wasgeneratedby` WRITE;
/*!40000 ALTER TABLE `wasgeneratedby` DISABLE KEYS */;
/*!40000 ALTER TABLE `wasgeneratedby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasinformedby`
--

DROP TABLE IF EXISTS `wasinformedby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasinformedby` (
  `idWasInformedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  PRIMARY KEY (`idWasInformedBy`),
  KEY `fk_WasInformedBy_Port_Entity_Task1_idx` (`Task_idTask`),
  KEY `fk_WasInformedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasInformedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasInformedBy_Port_Entity_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasinformedby`
--

LOCK TABLES `wasinformedby` WRITE;
/*!40000 ALTER TABLE `wasinformedby` DISABLE KEYS */;
INSERT INTO `wasinformedby` VALUES (1,'task 1 was successful for activity 1',1,1),(2,'task 1 was successful for activity 1',1,1),(3,'task 1 was successful for activity 1',1,1),(4,'task 1 was successful for activity 1',1,1),(5,'task 1 was successful for activity 1',1,1),(6,'task 1 was successful for activity 1',1,1),(7,'task 1 was successful for activity 1',1,1),(8,'task 1 was successful for activity 1',1,1),(9,'task 1 was successful for activity 1',1,1),(10,'task 5 was successful for activity 1',5,1),(11,'task 1 was successful for activity 1',1,1),(12,'task 1 was successful for activity 1',1,1),(13,'task 5 was successful for activity 1',5,1),(59,'Task Sum was successful for activity Calculo',1,3),(60,'Task Subtraction was successful for activity Calculo',3,3),(61,'Task Multiplication was successful for activity Calculo',5,3),(62,'Task Sum was successful for activity Calculo',1,3),(63,'Task Subtraction was successful for activity Calculo',3,3),(64,'Task Multiplication was successful for activity Calculo',5,3),(65,'Task Sum was successful for activity Calculo',1,3),(66,'Task Subtraction was successful for activity Calculo',3,3),(67,'Task Multiplication was successful for activity Calculo',5,3),(68,'Task Sum was successful for activity Calculo',1,3),(69,'Task Subtraction was successful for activity Calculo',3,3),(70,'Task Multiplication was successful for activity Calculo',5,3);
/*!40000 ALTER TABLE `wasinformedby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasrevisionof`
--

DROP TABLE IF EXISTS `wasrevisionof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasrevisionof` (
  `idWasRevisionOf` int(11) NOT NULL AUTO_INCREMENT,
  `RevisionOf` int(11) NOT NULL,
  `RevisionTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT 'Corrective',
  PRIMARY KEY (`idWasRevisionOf`),
  KEY `fk_WasDerivedFrom_Workflow1_idx` (`RevisionOf`),
  KEY `fk_WasDerivedFrom_Workflow2_idx` (`RevisionTo`),
  CONSTRAINT `fk_WasDerivedFrom_Workflow10` FOREIGN KEY (`RevisionOf`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasDerivedFrom_Workflow20` FOREIGN KEY (`RevisionTo`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasrevisionof`
--

LOCK TABLES `wasrevisionof` WRITE;
/*!40000 ALTER TABLE `wasrevisionof` DISABLE KEYS */;
INSERT INTO `wasrevisionof` VALUES (1,4,5,'Corretive');
/*!40000 ALTER TABLE `wasrevisionof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasstartedby`
--

DROP TABLE IF EXISTS `wasstartedby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasstartedby` (
  `idWasStartedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateStarted` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWasStartedBy`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_WasStartedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasStartedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasstartedby`
--

LOCK TABLES `wasstartedby` WRITE;
/*!40000 ALTER TABLE `wasstartedby` DISABLE KEYS */;
INSERT INTO `wasstartedby` VALUES (1,1,1,'2015-08-06 21:12:24','task 1 started to activity 1'),(2,1,1,'2015-08-06 21:21:26','task 1 started to activity 1'),(3,1,1,'2015-08-06 21:29:01','task 1 started to activity 1'),(4,1,1,'2015-08-06 21:29:02','task 1 started to activity 1'),(5,1,1,'2015-08-06 21:31:48','task 1 started to activity 1'),(6,1,1,'2015-08-06 21:31:51','task 1 started to activity 1'),(7,5,1,'2015-08-06 21:36:37','task 5 started to activity 1'),(8,5,1,'2015-08-06 21:36:39','task 5 started to activity 1'),(9,1,1,'2015-08-06 21:39:17','task 1 started to activity 1'),(10,5,1,'2015-08-06 21:39:18','task 5 started to activity 1'),(11,5,1,'2015-08-06 21:39:20','task 5 started to activity 1'),(32,5,3,'2015-10-08 16:00:46','Task Multiplication started to activity Calculo'),(33,5,3,'2015-10-08 16:00:47','Task Multiplication started to activity Calculo'),(34,5,3,'2015-10-19 17:06:36','Task Multiplication started to activity Calculo'),(35,5,3,'2015-10-19 17:06:37','Task Multiplication started to activity Calculo'),(36,5,3,'2015-10-19 19:07:42','Task Multiplication started to activity Calculo'),(37,5,3,'2015-10-19 19:07:47','Task Multiplication started to activity Calculo');
/*!40000 ALTER TABLE `wasstartedby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wasstartedbywt`
--

DROP TABLE IF EXISTS `wasstartedbywt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wasstartedbywt` (
  `idWasStartedByWT` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Started` datetime NOT NULL,
  PRIMARY KEY (`idWasStartedByWT`),
  KEY `fk_Workflow_has_Task_Task1_idx` (`Task_idTask`),
  KEY `fk_Workflow_has_Task_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Task_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Task_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wasstartedbywt`
--

LOCK TABLES `wasstartedbywt` WRITE;
/*!40000 ALTER TABLE `wasstartedbywt` DISABLE KEYS */;
/*!40000 ALTER TABLE `wasstartedbywt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow`
--

DROP TABLE IF EXISTS `workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflow` (
  `idWorkflow` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `DateVersion` date DEFAULT NULL,
  `NumberStage` int(11) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `SGWfC_idSGWfC` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWorkflow`),
  KEY `fk_Workflow_SGWfC1_idx` (`SGWfC_idSGWfC`),
  CONSTRAINT `fk_Workflow_SGWfC1` FOREIGN KEY (`SGWfC_idSGWfC`) REFERENCES `sgwfc` (`idSGWfC`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow`
--

LOCK TABLES `workflow` WRITE;
/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
INSERT INTO `workflow` VALUES (1,'SimpleAddition','Sum of two values','01.00.00','2015-06-18',1,NULL,1),(2,'SimpleSum','Sum of three values','01.00.00','2015-06-19',2,NULL,1),(3,'SimpleSum2','Sum of four values','01.00.00','2015-06-22',3,NULL,1),(4,'SimpleCount','Calculation values','01.00.00','2015-06-24',3,NULL,1),(5,'SimpleCount2','Calculation values','01.00.00','2015-06-26',3,NULL,1),(6,'Demonstracao','Apresentacao NenC','01.00.00','2015-10-08',3,NULL,1);
/*!40000 ALTER TABLE `workflow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-05 11:02:04
