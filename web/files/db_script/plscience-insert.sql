-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: plscience
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `ActedOnBehalfOf`
--

DROP TABLE IF EXISTS `ActedOnBehalfOf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActedOnBehalfOf` (
  `idActedOnBehalfOf` int(11) NOT NULL AUTO_INCREMENT,
  `InputPort_idPort` int(11) NOT NULL,
  `OutputPort_idPort` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idActedOnBehalfOf`),
  KEY `fk_InputPort_has_OutputPort_OutputPort1_idx` (`OutputPort_idPort`),
  KEY `fk_InputPort_has_OutputPort_InputPort1_idx` (`InputPort_idPort`),
  CONSTRAINT `fk_InputPort_has_OutputPort_InputPort1` FOREIGN KEY (`InputPort_idPort`) REFERENCES `InputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_InputPort_has_OutputPort_OutputPort1` FOREIGN KEY (`OutputPort_idPort`) REFERENCES `OutputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActedOnBehalfOf`
--

LOCK TABLES `ActedOnBehalfOf` WRITE;
/*!40000 ALTER TABLE `ActedOnBehalfOf` DISABLE KEYS */;
INSERT INTO `ActedOnBehalfOf` VALUES (1,8,3,'Task 1 acted on behalf of task1',NULL),(2,14,9,'Task 1 acted on behalf of task1',NULL),(3,19,11,'Task 1 acted on behalf of task5',NULL),(4,22,13,'Task 1 acted on behalf of task5',NULL),(5,27,16,'Task 1 acted on behalf of task5',NULL),(6,30,18,'Task 1 acted on behalf of task5',NULL),(7,35,21,'Task Sum acted on behalf of taskMultiplication',NULL),(8,38,23,'Task Sum acted on behalf of taskMultiplication',NULL),(9,42,26,'Task Sum acted on behalf of task Multiplication',NULL),(10,46,28,'Task Sum acted on behalf of task Multiplication',NULL),(11,50,31,'Task Sum acted on behalf of task Multiplication',NULL),(12,54,33,'Task Sum acted on behalf of task Multiplication',NULL),(13,58,36,'Task Sum acted on behalf of task Multiplication',NULL),(14,61,38,'Task Sum acted on behalf of task Multiplication',NULL),(15,66,41,'Task Sum acted on behalf of task Multiplication',NULL),(16,69,43,'Task Sum acted on behalf of task Multiplication',NULL),(17,74,46,'Task Sum acted on behalf of task Multiplication',NULL),(18,77,48,'Task Sum acted on behalf of task Multiplication',NULL),(19,82,51,'Task Sum acted on behalf of task Multiplication',NULL),(20,85,53,'Task Sum acted on behalf of task Multiplication',NULL),(21,91,56,'Task Sum acted on behalf of task Multiplication',NULL),(22,94,58,'Task Subtraction acted on behalf of task Multiplication',NULL),(23,99,61,'Task Sum acted on behalf of task Multiplication',NULL),(24,104,63,'Task Sum acted on behalf of task Multiplication',NULL),(25,109,65,'Task Sum acted on behalf of task Multiplication',NULL),(26,114,67,'Task Sum acted on behalf of task Multiplication',NULL),(27,119,69,'Task Sum acted on behalf of task Multiplication',NULL),(28,124,71,'Task Sum acted on behalf of task Multiplication',NULL),(29,133,73,'Task Subtraction acted on behalf of task Multiplication',NULL),(30,138,75,'Task Sum acted on behalf of task Multiplication',NULL),(31,143,77,'Task Sum acted on behalf of task Multiplication',NULL),(32,148,79,'Task Sum acted on behalf of task Multiplication',NULL),(33,153,81,'Task Sum acted on behalf of task Multiplication',NULL),(34,156,83,'Task Sum acted on behalf of task Multiplication',NULL),(35,161,86,'Task Sum acted on behalf of task Multiplication',NULL),(36,166,88,'Task Sum acted on behalf of task Multiplication',NULL),(37,171,90,'Task Sum acted on behalf of task Multiplication',NULL),(38,174,92,'Task Subtraction acted on behalf of task Multiplication',NULL),(39,181,95,'Task Sum acted on behalf of task Multiplication',NULL),(40,184,97,'Task Subtraction acted on behalf of task Multiplication',NULL),(41,196,100,'Task Sum acted on behalf of task Multiplication',NULL),(42,199,102,'Task Subtraction acted on behalf of task Multiplication',NULL),(43,207,105,'Task Sum acted on behalf of task Multiplication',NULL),(44,210,107,'Task Subtraction acted on behalf of task Multiplication',NULL),(45,216,110,'Task Sum acted on behalf of task Multiplication',NULL),(46,219,112,'Task Subtraction acted on behalf of task Multiplication',NULL),(47,231,115,'Task Sum acted on behalf of task Multiplication',NULL),(48,234,117,'Task Subtraction acted on behalf of task Multiplication',NULL),(49,239,120,'Task Sum acted on behalf of task Multiplication',NULL),(50,242,122,'Task Subtraction acted on behalf of task Multiplication',NULL),(51,247,125,'Task Sum acted on behalf of task Multiplication',NULL),(52,250,127,'Task Subtraction acted on behalf of task Multiplication',NULL),(53,255,130,'Task Sum acted on behalf of task Multiplication',NULL),(54,258,132,'Task Subtraction acted on behalf of task Multiplication',NULL),(55,263,135,'Task Sum acted on behalf of task Multiplication',NULL),(56,266,137,'Task Subtraction acted on behalf of task Multiplication',NULL),(57,271,140,'Task Sum acted on behalf of task Multiplication',NULL),(58,274,142,'Task Subtraction acted on behalf of task Multiplication',NULL),(59,285,145,'Task geneID acted on behalf of task geneIDCon',10),(60,289,149,'Task geneID acted on behalf of task geneIDCon',10);
/*!40000 ALTER TABLE `ActedOnBehalfOf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Activity` (
  `idActivity` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `Function` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idActivity`),
  KEY `fk_Activity_Entity1_idx` (`Entity_idEntity`),
  CONSTRAINT `fk_Activity_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
INSERT INTO `Activity` VALUES (1,1,'Calculus','Return the value of the executed calculation','Given a set of input values, the workflow should return the results of operations'),(3,1,'Calculo','Executar calculos matemáticos','Demonstracao da ferramenta'),(4,3,'Nuclear Protein Database Query Service','Retrieve Information','This service provides various ways to retrieve information from The Nuclear Protein Database');
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Agent`
--

DROP TABLE IF EXISTS `Agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Agent` (
  `idAgent` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Institution` int(11) NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL,
  `local_agent` tinyint(1) NOT NULL DEFAULT '1',
   `dblp_name` VARCHAR(255) NULL DEFAULT NULL,
  `scholar_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idAgent`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `fk_Agent_Entity1_idx` (`Institution`),
  KEY `fk_Status_Agent_idx` (`status_id`),
  KEY `fk_competence_agent_idx` (`competence_id`),
  KEY `fk_role_agent_idx` (`role_id`),
  CONSTRAINT `fk_Agent_Entity1` FOREIGN KEY (`Institution`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_competence_agent` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_agent` FOREIGN KEY (`role_id`) REFERENCES `roler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_agent` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Agent`
--

LOCK TABLES `Agent` WRITE;
/*!40000 ALTER TABLE `Agent` DISABLE KEYS */;
INSERT INTO `Agent` VALUES (1,'tassio','tassio.sirqueira@ice.ufjf.br','f10354719639a6e97c1ccc7ed0c5f2d3','Tassio',1,'Aluno de Pós-graduação',1,1,1,1, "", ""),(2,'regina','regina@acessa.com','221182760f5b980c97c7a74a94d57364','Regina Braga',1,'Professora de Pós-graduação',1,2,1,1, "", ""),(3,'humberto','humbertodalpra@gmail.com','8767bbc52e71900d1f3a50b53196d0e2','Humberto Dalpra',1,'Aluno de Pós-Graduação',1,2,1,1, "", ""),(4,'marco','maraujo@acessa.com','f5888d0bb58d611107e11f7cbc41c97a','Marco Antônio Pereira Araújo',1,'Professor de Pós-graduação',1,1,1,1, "", ""),(5,'phillipe','phillipe.marques@gmail.com','e10adc3949ba59abbe56e057f20f883e','Phillipe',1,'Teste',1,1,1,1, "", ""),(21,'joao','joao@mail.com','e10adc3949ba59abbe56e057f20f883e','João',1,'Teste',1,1,3,1, "", ""),(22,'maria','maria@mail.com','e10adc3949ba59abbe56e057f20f883e','Maria',1,'Teste',2,2,3,1, "", ""),(23,'miguel','miguel@mail.com','e10adc3949ba59abbe56e057f20f883e','Miguel',1,'Teste',2,1,3,1, "", ""),(24,'alice','alice@mail.com','e10adc3949ba59abbe56e057f20f883e','Alice',1,'Teste',2,2,1,1, "", ""),(25,'paula','paula@mail.com','e10adc3949ba59abbe56e057f20f883e','Paula',1,'Teste',1,1,2,1, "", "");
/*!40000 ALTER TABLE `Agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Entity`
--

DROP TABLE IF EXISTS `Entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Entity` (
  `idEntity` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Acronym` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idEntity`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entity`
--

LOCK TABLES `Entity` WRITE;
/*!40000 ALTER TABLE `Entity` DISABLE KEYS */;
INSERT INTO `Entity` VALUES (1,'Universidade Federal de Juiz de Fora','UFJF','Mestrado em Ciência da Computação'),(2,'Instituto Federal de Educação, Ciência e Tecnologia do Sudeste de Minas Gerais','IF Sudeste MG','Bacharelado em Sistemas de Informação'),(3,'Other','Other','Other Institution');
/*!40000 ALTER TABLE `Entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Experiment`
--

DROP TABLE IF EXISTS `Experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Experiment` (
  `idExperiment` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `idAgent` int(11) DEFAULT NULL,
  `Name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `DateStarted` date DEFAULT NULL,
  `DateEnded` date DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Version` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `number_stages` int(11) DEFAULT NULL,
  `parsifal_review` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExperiment`),
  KEY `fk_Expiriment_Entity1_idx` (`Entity_idEntity`),
  KEY `fk_Expiriment_Activity1_idx` (`Activity_idActivity`),
  KEY `fk_Experiment_Agent1_idx` (`idAgent`),
  CONSTRAINT `fk_Experiment_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expiriment_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expiriment_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Experiment`
--

LOCK TABLES `Experiment` WRITE;
/*!40000 ALTER TABLE `Experiment` DISABLE KEYS */;
INSERT INTO `Experiment` VALUES (1,1,1,1,'Mathematical operations','2015-06-18',NULL,'Given a set of input values, the workflow should return the results of operations','01.00',NULL,NULL),(2,1,3,1,'Experimento matematico','2015-10-08','2015-10-10','Demonstracao ao NEnC','01.00',NULL,NULL),(3,NULL,NULL,NULL,'Teste','2015-12-09','2015-12-30','Teste de Criação','',2,NULL),(4,1,3,NULL,'Teste2','2016-07-26','2016-07-28','Teste2','01.00',NULL,NULL),(5,1,1,NULL,'Solves the equation','2016-09-04','2016-09-07','Solves the equation x = 3: |(x + 5)/(x - 5)|','02.00',NULL,NULL),(6,1,1,NULL,'Simple Equation Solver','2016-09-04','2016-09-07','solves for the problem x = 3 : |(x+5)/(x-5)|','02.00',NULL,NULL),(7,1,1,NULL,'Gene Extraction Experiment','2016-10-17','2016-10-20','Using a Gene Extraction Workflow','01.00',NULL,NULL),(8,3,4,NULL,'Gene Extraction Experiment','2016-10-19','2016-10-28','Using a Gene Extraction Workflow','01.00',NULL,NULL);
/*!40000 ALTER TABLE `Experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `InputPort`
--

DROP TABLE IF EXISTS `InputPort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InputPort` (
  `idPort` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `fk_Port_Task1` (`Task_idTask`),
  CONSTRAINT `fk_Port_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InputPort`
--

LOCK TABLES `InputPort` WRITE;
/*!40000 ALTER TABLE `InputPort` DISABLE KEYS */;
INSERT INTO `InputPort` VALUES (1,'Starting port workflow','Port of task with valeu ',1,'6',NULL),(2,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(3,'Starting port workflow','Port of task with valeu ',1,'8',NULL),(4,'Starting port workflow','Port of task with valeu ',1,'8',NULL),(5,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(6,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(7,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(8,'Starting port task1','1 task input with valueinput 0',1,'0',NULL),(9,'Starting port task1','1 task input with valueinput 0',1,'0',NULL),(10,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(11,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(12,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(13,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(14,'Starting port task1','1 task input with valueinput 12',1,'12',NULL),(15,'Starting port task1','1 task input with valueinput 12',1,'12',NULL),(16,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(17,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(18,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(19,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(20,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(21,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(22,'Starting port task5','5 task input with valueinput -12',5,'-12',NULL),(23,'Starting port task5','5 task input with valueinput -12',5,'-12',NULL),(24,'Starting port workflow','Port of task with valeu ',1,'3',NULL),(25,'Starting port workflow','Port of task with valeu ',1,'7',NULL),(26,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(27,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(28,'Starting port task5','5 task input with valueinput 8',5,'8',NULL),(29,'Starting port workflow','Port of task with valeu ',1,'5',NULL),(30,'Starting port task5','5 task input with valueinput 12',5,'12',NULL),(31,'Starting port task5','5 task input with valueinput 12',5,'12',NULL),(32,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(33,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(34,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(35,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(36,'Starting port taskMultiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(37,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(38,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(39,'Starting port taskMultiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(40,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(41,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(42,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(43,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(44,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(45,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(46,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(47,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(48,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(49,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(50,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(51,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(52,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(53,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(54,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(55,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(56,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(57,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(58,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(59,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(60,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(61,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(62,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(63,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(64,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(65,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(66,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(67,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(68,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(69,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(70,'Starting port task Multiplication','Task Multiplication input with valueinput 12',5,'12',NULL),(71,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(72,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(73,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(74,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(75,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(76,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(77,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(78,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(79,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(80,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(81,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(82,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(83,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(84,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(85,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(86,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(87,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(88,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(89,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(90,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(91,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(92,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(93,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(94,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(95,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(96,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(97,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(98,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(99,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(100,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(101,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(102,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(103,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(104,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(105,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(106,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(107,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(108,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(109,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(110,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(111,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(112,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(113,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(114,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(115,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(116,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(117,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(118,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(119,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(120,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(121,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(122,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(123,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(124,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(125,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(126,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(127,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(128,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(129,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(130,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(131,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(132,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(133,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(134,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(135,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(136,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(137,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(138,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(139,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(140,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(141,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(142,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(143,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(144,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(145,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(146,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(147,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(148,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(149,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(150,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(151,'Starting port workflow to Sum','Port of task with valeu 7',1,'7',NULL),(152,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(153,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(154,'Starting port task Multiplication','Task Multiplication input with valueinput 8',5,'8',NULL),(155,'Starting port workflow to Sum','Port of task with valeu 5',1,'5',NULL),(156,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(157,'Starting port task Multiplication','Task Multiplication input with valueinput -12',5,'-12',NULL),(158,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(159,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(160,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(161,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(162,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(163,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(164,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(165,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(166,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(167,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(168,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(169,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(170,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(171,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(172,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(173,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(174,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(175,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(176,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(177,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(178,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(179,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(180,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(181,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(182,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(183,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(184,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(185,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(186,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(187,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(188,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(189,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(190,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(191,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(192,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(193,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(194,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(195,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(196,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(197,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(198,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(199,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(200,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(201,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(202,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(203,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(204,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(205,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(206,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(207,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(208,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(209,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(210,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(211,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(212,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(213,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(214,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(215,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(216,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(217,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(218,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(219,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(220,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(221,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(222,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(223,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(224,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(225,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(226,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(227,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(228,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(229,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(230,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(231,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(232,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(233,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(234,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(235,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(236,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(237,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(238,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(239,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(240,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(241,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(242,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(243,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(244,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(245,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(246,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(247,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(248,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(249,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(250,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(251,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(252,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(253,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(254,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(255,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(256,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(257,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(258,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(259,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(260,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(261,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(262,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(263,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(264,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(265,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(266,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(267,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(268,'Starting port workflow to Sum','Port of task with valeu 3',1,'3',NULL),(269,'Starting port workflow to Subtraction','Port of task with valeu 7',3,'7',NULL),(270,'Starting port workflow to Sum','Port of task with valeu 2',1,'2',NULL),(271,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(272,'Starting port task Multiplication','Task Multiplication input with valueinput 5',5,'5',NULL),(273,'Starting port workflow to Subtraction','Port of task with valeu 5',3,'5',NULL),(274,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(275,'Starting port task Multiplication','Task Multiplication input with valueinput 2',5,'2',NULL),(276,'Starting port workflow to Add or Subtract','Port of task with valeu 5',8,'5',7),(277,'Starting port workflow to Add or Subtract','Port of task with valeu 6',8,'6',7),(278,'Starting port workflow to Add or Subtract','Port of task with valeu 6',8,'6',7),(279,'Starting port workflow to Add or Subtract','Port of task with valeu 6',8,'6',7),(280,'Starting port workflow to Add or Subtract','Port of task with valeu 6',8,'6',7),(281,'Starting port workflow to Sum','Port of task with valeu 0',1,'0',9),(282,'Starting port task Add or Subtract','Task Add or Subtract input with valueinput http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',8,'http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',10),(283,'Starting port task geneID','Task geneID input with valueinput http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',10,'http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',10),(284,'Starting port workflow to geneID','Port of task with valeu ATRX',10,'ATRX',10),(285,'Starting port task geneIDCon','Task geneIDCon input with valueinput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',11,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(286,'Starting port task geneIDCon','Task geneIDCon input with valueinput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',11,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(287,'Starting port task geneID','Task geneID input with valueinput http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',10,'http://npd.hgu.mrc.ac.uk/soap/npd.wsdl',10),(288,'Starting port workflow to geneID','Port of task with valeu ATRX',10,'ATRX',10),(289,'Starting port task geneIDCon','Task geneIDCon input with valueinput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',11,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(290,'Starting port task geneIDCon','Task geneIDCon input with valueinput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',11,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10);
/*!40000 ALTER TABLE `InputPort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IsPartOf`
--

DROP TABLE IF EXISTS `IsPartOf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IsPartOf` (
  `idIsPartOf` int(11) NOT NULL AUTO_INCREMENT,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idIsPartOf`),
  KEY `fk_Agent_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`),
  KEY `fk_Agent_has_ResearchGroup_Agent1_idx` (`Agent_idAgent`),
  CONSTRAINT `fk_Agent_has_ResearchGroup_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agent_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IsPartOf`
--

LOCK TABLES `IsPartOf` WRITE;
/*!40000 ALTER TABLE `IsPartOf` DISABLE KEYS */;
INSERT INTO `IsPartOf` VALUES (1,5,4,'Aluno'),(2,1,1,'Aluno de Pós-graduação'),(3,3,1,'Aluno de Pós-graduação'),(4,4,1,'Professor de Pós-graduação'),(7,21,4,''),(8,22,4,''),(9,23,5,''),(10,24,5,''),(11,24,6,''),(12,5,6,'Aluno'),(15,5,5,''),(16,25,4,'');
/*!40000 ALTER TABLE `IsPartOf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OutputPort`
--

DROP TABLE IF EXISTS `OutputPort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OutputPort` (
  `idPort` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `fk_Port_Task1_idx` (`Task_idTask`),
  CONSTRAINT `fk_Port_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OutputPort`
--

LOCK TABLES `OutputPort` WRITE;
/*!40000 ALTER TABLE `OutputPort` DISABLE KEYS */;
INSERT INTO `OutputPort` VALUES (1,'Ended Port Task 1','Task output with valueoutput 11',1,'11',NULL),(2,'Ended Port Task 1','Task output with valueoutput 16',1,'16',NULL),(3,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(4,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(5,'Ended Port Task 1','Task output with valueoutput 15',1,'15',NULL),(6,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(7,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(8,'Ended Port Task 1','Task output with valueoutput 20',1,'20',NULL),(9,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(10,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(11,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(12,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(13,'Ended Port Task 1','Task output with valueoutput -12',1,'-12',NULL),(14,'Ended Port Task 1','Task output with valueoutput -12',1,'-12',NULL),(15,'Ended Port Task 5','Task output with valueoutput 96',5,'96',NULL),(16,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(17,'Ended Port Task 1','Task output with valueoutput 8',1,'8',NULL),(18,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(19,'Ended Port Task 1','Task output with valueoutput 12',1,'12',NULL),(20,'Ended Port Task 5','Task output with valueoutput 96',5,'96',NULL),(21,'Ended Port Task Sum','Task Sumoutput with valueoutput 8',1,'8',NULL),(22,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(23,'Ended Port Task Sum','Task Sumoutput with valueoutput -12',1,'-12',NULL),(24,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(25,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 96',5,'96',NULL),(26,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(27,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(28,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(29,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(30,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(31,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(32,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(33,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(34,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(35,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(36,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(37,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(38,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(39,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(40,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(41,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(42,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(43,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(44,'Ended Port Task Sum','Task Sum output with valueoutput 12',1,'12',NULL),(45,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 60',5,'60',NULL),(46,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(47,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(48,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(49,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(50,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(51,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(52,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(53,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(54,'Ended Port Task Sum','Task Sum output with valueoutput 2',1,'2',NULL),(55,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(56,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(57,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(58,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(59,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(60,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(61,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(62,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(63,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(64,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(65,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(66,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(67,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(68,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(69,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(70,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(71,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(72,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(73,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(74,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(75,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(76,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(77,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(78,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(79,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(80,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(81,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(82,'Ended Port Task Sum','Task Sum output with valueoutput 8',1,'8',NULL),(83,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(84,'Ended Port Task Sum','Task Sum output with valueoutput -12',1,'-12',NULL),(85,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 96',5,'96',NULL),(86,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(87,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(88,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(89,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(90,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(91,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(92,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(93,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(94,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(95,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(96,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(97,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(98,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(99,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(100,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(101,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(102,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(103,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(104,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(105,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(106,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(107,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(108,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(109,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(110,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(111,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(112,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(113,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(114,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(115,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(116,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(117,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(118,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(119,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(120,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(121,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(122,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(123,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(124,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(125,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(126,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(127,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(128,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(129,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(130,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(131,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(132,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(133,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(134,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(135,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(136,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(137,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(138,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(139,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(140,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(141,'Ended Port Task Sum','Task Sum output with valueoutput 5',1,'5',NULL),(142,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(143,'Ended Port Task Subtraction','Task Subtraction output with valueoutput 2',3,'2',NULL),(144,'Ended Port Task Multiplication','Task Multiplication output with valueoutput 10',5,'10',NULL),(145,'Ended Port Task geneID','Task geneID output with valueoutput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(146,'Ended Port Task geneID','Task geneID output with valueoutput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(147,'Ended Port Task geneIDCon','Task geneIDCon output with valueoutput ATRX',11,'ATRX',10),(148,'Ended Port Task geneIDCon','Task geneIDCon output with valueoutput 1NP00017',11,'1NP00017',10),(149,'Ended Port Task geneID','Task geneID output with valueoutput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(150,'Ended Port Task geneID','Task geneID output with valueoutput <?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10,'<?xml version=\"1.0\"?><result xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:geneIDResult\"><GeneID xsi:type=\"xsd:string\">1NP00017</GeneID><GeneName xsi:type=\"xsd:string\">ATRX</GeneName></result>',10),(151,'Ended Port Task geneIDCon','Task geneIDCon output with valueoutput ATRX',11,'ATRX',10),(152,'Ended Port Task geneIDCon','Task geneIDCon output with valueoutput 1NP00017',11,'1NP00017',10);
/*!40000 ALTER TABLE `OutputPort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResearchGroup`
--

DROP TABLE IF EXISTS `ResearchGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResearchGroup` (
  `idResearchGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL,
  PRIMARY KEY (`idResearchGroup`),
  KEY `fk_Agent_has_Expiriment_Agent1_idx` (`Agent_idAgent_chef`),
  CONSTRAINT `fk_Agent_has_Expiriment_Agent1` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResearchGroup`
--

LOCK TABLES `ResearchGroup` WRITE;
/*!40000 ALTER TABLE `ResearchGroup` DISABLE KEYS */;
INSERT INTO `ResearchGroup` VALUES (1,'NEnC','Núcleo de Engenharia do Conhecimento',2),(4,'Amigos','Meus Amigos',5),(5,'Teste de Software','Pessoas que trabalham com Teste de Software',3),(6,'Parceiros UFRJ','Parceiros de pesquisa da UFRJ',1);
/*!40000 ALTER TABLE `ResearchGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SGWfC`
--

DROP TABLE IF EXISTS `SGWfC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SGWfC` (
  `idSGWfC` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSGWfC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SGWfC`
--

LOCK TABLES `SGWfC` WRITE;
/*!40000 ALTER TABLE `SGWfC` DISABLE KEYS */;
INSERT INTO `SGWfC` VALUES (1,'Kepler','Kepler é um software livre do sistema para a concepção, execução, reutilizando, evoluindo, arquivamento e compartilhamento científicos'),(2,'Taverna Workbench','Taverna é um conjunto de ferramentas usadas para criar e executar workflows científicos e ajuda na experimentação in silico');
/*!40000 ALTER TABLE `SGWfC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `idTask` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idTask`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
INSERT INTO `Task` VALUES (1,'Sum','Integer','Sum of two values'),(2,'Sum','Float','Sum of two values'),(3,'Subtraction','Integer','Subtraction of two values'),(4,'Subtraction','Float','Subtraction of two values'),(5,'Multiplication','Integer','Multiplication of two values'),(6,'Multiplication','Float','Multiplication of two values'),(7,'Division','Float','Division of two values'),(8,'Add or Subtract','Integer','Adiciona ou subtrai'),(9,'Add or Subtract 2','Integer','Adiciona ou subtrai'),(10,'geneID','xmltoken','Return a geneID - Input: xsd:string output: npd:geneIDResult'),(11,'geneIDCon','String','Connector');
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Used`
--

DROP TABLE IF EXISTS `Used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Used` (
  `idUsed` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idUsed`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_Used_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Used_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task100` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Used`
--

LOCK TABLES `Used` WRITE;
/*!40000 ALTER TABLE `Used` DISABLE KEYS */;
INSERT INTO `Used` VALUES (1,1,1,'Task was used in workflow'),(2,1,1,'Task was used in workflow'),(3,1,2,'Task was used in workflow'),(4,1,2,'Task was used in workflow'),(5,1,2,'Task was used in workflow'),(6,1,2,'Task 1 was used in workflow 2'),(7,1,3,'Task was used in workflow'),(8,1,3,'Task was used in workflow'),(9,1,3,'Task was used in workflow'),(10,1,3,'Task was used in workflow'),(11,1,3,'Task 1 was used in workflow 3'),(12,1,4,'Task was used in workflow'),(13,1,4,'Task was used in workflow'),(14,1,4,'Task was used in workflow'),(15,5,4,'Task 5 was used in workflow 4'),(16,1,4,'Task was used in workflow'),(17,5,4,'Task 5 was used in workflow 4'),(18,1,5,'Task was used in workflow'),(19,1,5,'Task was used in workflow'),(20,1,5,'Task was used in workflow'),(21,5,5,'Task 5 was used in workflow 5'),(22,1,5,'Task was used in workflow'),(23,5,5,'Task 5 was used in workflow 5'),(24,1,4,'TaskSumwas used in workflowSimpleCount'),(25,1,4,'TaskSumwas used in workflowSimpleCount'),(26,1,4,'TaskSumwas used in workflowSimpleCount'),(27,5,4,'Task Multiplication was used in workflow SimpleCount'),(28,1,4,'TaskSumwas used in workflowSimpleCount'),(29,5,4,'Task Multiplication was used in workflow SimpleCount'),(30,1,4,'Task Sum was used in workflow SimpleCount'),(31,1,4,'Task Sum was used in workflow SimpleCount'),(32,5,4,'Task Multiplication was used in workflow SimpleCount'),(33,1,4,'Task Sum was used in workflow SimpleCount'),(34,1,4,'Task Sum was used in workflow SimpleCount'),(35,5,4,'Task Multiplication was used in workflow SimpleCount'),(36,1,4,'Task Sum was used in workflow SimpleCount'),(37,1,4,'Task Sum was used in workflow SimpleCount'),(38,5,4,'Task Multiplication was used in workflow SimpleCount'),(39,1,4,'Task Sum was used in workflow SimpleCount'),(40,1,4,'Task Sum was used in workflow SimpleCount'),(41,5,4,'Task Multiplication was used in workflow SimpleCount'),(42,1,4,'Task Sum was used in workflow SimpleCount'),(43,1,4,'Task Sum was used in workflow SimpleCount'),(44,5,4,'Task Multiplication was used in workflow SimpleCount'),(45,1,4,'Task Sum was used in workflow SimpleCount'),(46,5,4,'Task Multiplication was used in workflow SimpleCount'),(47,1,4,'Task Sum was used in workflow SimpleCount'),(48,1,4,'Task Sum was used in workflow SimpleCount'),(49,1,4,'Task Sum was used in workflow SimpleCount'),(50,5,4,'Task Multiplication was used in workflow SimpleCount'),(51,1,4,'Task Sum was used in workflow SimpleCount'),(52,5,4,'Task Multiplication was used in workflow SimpleCount'),(53,1,4,'Task Sum was used in workflow SimpleCount'),(54,1,4,'Task Sum was used in workflow SimpleCount'),(55,1,4,'Task Sum was used in workflow SimpleCount'),(56,5,4,'Task Multiplication was used in workflow SimpleCount'),(57,1,4,'Task Sum was used in workflow SimpleCount'),(58,5,4,'Task Multiplication was used in workflow SimpleCount'),(59,1,4,'Task Sum was used in workflow SimpleCount'),(60,1,4,'Task Sum was used in workflow SimpleCount'),(61,1,4,'Task Sum was used in workflow SimpleCount'),(62,5,4,'Task Multiplication was used in workflow SimpleCount'),(63,1,4,'Task Sum was used in workflow SimpleCount'),(64,5,4,'Task Multiplication was used in workflow SimpleCount'),(65,1,4,'Task Sum was used in workflow SimpleCount'),(192,1,6,'Task Sum was used in workflow Demonstracao'),(193,3,6,'Task Subtraction was used in workflow Demonstracao'),(194,1,6,'Task Sum was used in workflow Demonstracao'),(195,5,6,'Task Multiplication was used in workflow Demonstracao'),(196,3,6,'Task Subtraction was used in workflow Demonstracao'),(197,5,6,'Task Multiplication was used in workflow Demonstracao'),(198,1,6,'Task Sum was used in workflow Demonstracao'),(199,3,6,'Task Subtraction was used in workflow Demonstracao'),(200,1,6,'Task Sum was used in workflow Demonstracao'),(201,5,6,'Task Multiplication was used in workflow Demonstracao'),(202,3,6,'Task Subtraction was used in workflow Demonstracao'),(203,5,6,'Task Multiplication was used in workflow Demonstracao'),(204,1,6,'Task Sum was used in workflow Demonstracao'),(205,3,6,'Task Subtraction was used in workflow Demonstracao'),(206,1,6,'Task Sum was used in workflow Demonstracao'),(207,5,6,'Task Multiplication was used in workflow Demonstracao'),(208,3,6,'Task Subtraction was used in workflow Demonstracao'),(209,5,6,'Task Multiplication was used in workflow Demonstracao'),(210,8,7,'Task Add or Subtract was used in workflow Testando'),(211,8,7,'Task Add or Subtract was used in workflow Testando'),(212,8,7,'Task Add or Subtract was used in workflow Testando'),(213,8,7,'Task Add or Subtract was used in workflow Testando'),(214,8,7,'Task Add or Subtract was used in workflow Testando'),(215,1,9,'Task Sum was used in workflow Simple Equation Solver'),(216,8,10,'Task Add or Subtract was used in workflow GeneExtraction'),(217,8,10,'Task Add or Subtract was used in workflow GeneExtraction'),(218,10,10,'Task geneID was used in workflow GeneExtraction'),(219,10,10,'Task geneID was used in workflow GeneExtraction'),(220,10,10,'Task geneID was used in workflow GeneExtraction'),(221,11,10,'Task geneIDCon was used in workflow GeneExtraction'),(222,11,10,'Task geneIDCon was used in workflow GeneExtraction'),(223,10,10,'Task geneID was used in workflow GeneExtraction'),(224,10,10,'Task geneID was used in workflow GeneExtraction'),(225,10,10,'Task geneID was used in workflow GeneExtraction'),(226,11,10,'Task geneIDCon was used in workflow GeneExtraction'),(227,11,10,'Task geneIDCon was used in workflow GeneExtraction');
/*!40000 ALTER TABLE `Used` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasAssociatedWith`
--

DROP TABLE IF EXISTS `WasAssociatedWith`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasAssociatedWith` (
  `idWasAssociatedWith` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idWasAssociatedWith`),
  KEY `fk_Workflow_has_Expiriment_Expiriment1_idx` (`Experiment_Experiment`),
  KEY `fk_Workflow_has_Expiriment_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Expiriment_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Expiriment_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasAssociatedWith`
--

LOCK TABLES `WasAssociatedWith` WRITE;
/*!40000 ALTER TABLE `WasAssociatedWith` DISABLE KEYS */;
INSERT INTO `WasAssociatedWith` VALUES (1,1,1,NULL),(2,2,1,NULL),(3,3,1,NULL),(4,4,1,NULL),(5,5,1,NULL),(6,1,1,'Workflow was attributed to experimento'),(7,1,1,'Workflow was attributed to experimento'),(8,2,1,'Workflow was attributed to experimento'),(9,3,1,'Workflow was attributed to experimento'),(10,4,1,'Workflow was attributed to experimento'),(11,5,1,'Workflow was attributed to experimento'),(12,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(13,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(14,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(15,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(16,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(17,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(18,4,1,'Workflow SimpleCount was attributed to experimento Mathematical operations'),(41,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico'),(42,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico'),(43,6,2,'Workflow Demonstracao was attributed to experimento Experimento matematico'),(44,7,4,'Workflow Testando was attributed to experimento Teste2'),(45,7,4,'Workflow Testando was attributed to experimento Teste2'),(46,7,4,'Workflow Testando was attributed to experimento Teste2'),(47,7,4,'Workflow Testando was attributed to experimento Teste2'),(48,7,4,'Workflow Testando was attributed to experimento Teste2'),(49,1,6,'Workflow Simple Equation Solver was attributed to experimento Solves the equation'),(50,9,5,'Workflow Simple Equation Solver was attributed to experimento Solves the equation'),(51,9,5,'Workflow Simple Equation Solver was attributed to experimento Solves the equation'),(52,9,5,'Workflow Simple Equation Solver was attributed to experimento Solves the equation'),(53,9,6,'Workflow Simple Equation Solver was attributed to experimento Simple Equation Solver'),(54,10,8,'Workflow GeneExtraction was attributed to experimento Gene Extraction Experiment'),(55,10,8,'Workflow GeneExtraction was attributed to experimento Gene Extraction Experiment');
/*!40000 ALTER TABLE `WasAssociatedWith` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasControledBy`
--

DROP TABLE IF EXISTS `WasControledBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasControledBy` (
  `idWasControledBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  PRIMARY KEY (`idWasControledBy`),
  KEY `fk_WasControledBy_Activity1_idx` (`Activity_idActivity`),
  KEY `fk_WasControledBy_Agent1_idx` (`Agent_idAgent`),
  CONSTRAINT `fk_WasControledBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasControledBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasControledBy`
--

LOCK TABLES `WasControledBy` WRITE;
/*!40000 ALTER TABLE `WasControledBy` DISABLE KEYS */;
/*!40000 ALTER TABLE `WasControledBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasDerivedFrom`
--

DROP TABLE IF EXISTS `WasDerivedFrom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasDerivedFrom` (
  `idWasDerivedFrom` int(11) NOT NULL AUTO_INCREMENT,
  `DerivedOf` int(11) NOT NULL,
  `DerivedTo` int(11) NOT NULL,
  `Type` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idWasDerivedFrom`),
  KEY `fk_WasDerivedFrom_Workflow1_idx` (`DerivedOf`),
  KEY `fk_WasDerivedFrom_Workflow2_idx` (`DerivedTo`),
  CONSTRAINT `fk_WasDerivedFrom_Workflow1` FOREIGN KEY (`DerivedOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasDerivedFrom_Workflow2` FOREIGN KEY (`DerivedTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasDerivedFrom`
--

LOCK TABLES `WasDerivedFrom` WRITE;
/*!40000 ALTER TABLE `WasDerivedFrom` DISABLE KEYS */;
INSERT INTO `WasDerivedFrom` VALUES (1,1,2,'Evolution'),(2,2,3,'Evolution'),(3,1,7,'1'),(4,1,7,'1'),(5,1,7,'1'),(6,1,7,'1'),(7,1,7,'1');
/*!40000 ALTER TABLE `WasDerivedFrom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasEndedBy`
--

DROP TABLE IF EXISTS `WasEndedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasEndedBy` (
  `idWasEndedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateEnded` datetime DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idWasEndedBy`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_WasEndedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasEndedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasEndedBy`
--

LOCK TABLES `WasEndedBy` WRITE;
/*!40000 ALTER TABLE `WasEndedBy` DISABLE KEYS */;
INSERT INTO `WasEndedBy` VALUES (1,1,1,'2015-08-06 21:12:24','task 1 ended to activity 1'),(2,1,1,'2015-08-06 21:21:27','task 1 ended to activity 1'),(3,1,1,'2015-08-06 21:29:02','task 1 ended to activity 1'),(4,1,1,'2015-08-06 21:29:03','task 1 ended to activity 1'),(5,1,1,'2015-08-06 21:31:49','task 1 ended to activity 1'),(6,1,1,'2015-08-06 21:31:50','task 1 ended to activity 1'),(7,1,1,'2015-08-06 21:31:51','task 1 ended to activity 1'),(8,1,1,'2015-08-06 21:36:37','task 1 ended to activity 1'),(9,1,1,'2015-08-06 21:36:39','task 1 ended to activity 1'),(10,5,1,'2015-08-06 21:36:39','task 5 ended to activity 1'),(11,1,1,'2015-08-06 21:39:19','task 1 ended to activity 1'),(12,1,1,'2015-08-06 21:39:20','task 1 ended to activity 1'),(13,5,1,'2015-08-06 21:39:20','task 5 ended to activity 1'),(41,1,3,'2015-10-08 16:00:46','Task Sum ended to activity Calculo'),(42,3,3,'2015-10-08 16:00:47','Task Subtraction ended to activity Calculo'),(43,5,3,'2015-10-08 16:00:47','Task Multiplication ended to activity Calculo'),(44,1,3,'2015-10-19 17:06:36','Task Sum ended to activity Calculo'),(45,3,3,'2015-10-19 17:06:37','Task Subtraction ended to activity Calculo'),(46,5,3,'2015-10-19 17:06:37','Task Multiplication ended to activity Calculo'),(47,1,3,'2015-10-19 19:07:43','Task Sum ended to activity Calculo'),(48,3,3,'2015-10-19 19:07:47','Task Subtraction ended to activity Calculo'),(49,5,3,'2015-10-19 19:07:47','Task Multiplication ended to activity Calculo'),(50,10,4,'2016-10-19 16:36:22','Task geneID ended to activity Nuclear Protein Database Query Service'),(51,11,4,'2016-10-19 16:36:22','Task geneIDCon ended to activity Nuclear Protein Database Query Service'),(52,11,4,'2016-10-19 16:36:22','Task geneIDCon ended to activity Nuclear Protein Database Query Service'),(53,10,4,'2016-10-26 14:59:10','Task geneID ended to activity Nuclear Protein Database Query Service'),(54,11,4,'2016-10-26 14:59:10','Task geneIDCon ended to activity Nuclear Protein Database Query Service'),(55,11,4,'2016-10-26 14:59:11','Task geneIDCon ended to activity Nuclear Protein Database Query Service');
/*!40000 ALTER TABLE `WasEndedBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasEndedByWT`
--

DROP TABLE IF EXISTS `WasEndedByWT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasEndedByWT` (
  `idWasEndedByWT` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Ended` datetime DEFAULT NULL,
  PRIMARY KEY (`idWasEndedByWT`),
  KEY `fk_Workflow_has_Task_Task2_idx` (`Task_idTask`),
  KEY `fk_Workflow_has_Task_Workflow2_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Task_Task2` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Task_Workflow2` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasEndedByWT`
--

LOCK TABLES `WasEndedByWT` WRITE;
/*!40000 ALTER TABLE `WasEndedByWT` DISABLE KEYS */;
/*!40000 ALTER TABLE `WasEndedByWT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasGeneratedBy`
--

DROP TABLE IF EXISTS `WasGeneratedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasGeneratedBy` (
  `idWasGeneratedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Experiment_Experiment` int(11) NOT NULL,
  `ResearchGroup_idResearchGroup` int(11) NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idWasGeneratedBy`),
  KEY `fk_Experiment_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`),
  KEY `fk_Experiment_has_ResearchGroup_Experiment1_idx` (`Experiment_Experiment`),
  CONSTRAINT `fk_Experiment_has_ResearchGroup_Experiment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Experiment_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasGeneratedBy`
--

LOCK TABLES `WasGeneratedBy` WRITE;
/*!40000 ALTER TABLE `WasGeneratedBy` DISABLE KEYS */;
/*!40000 ALTER TABLE `WasGeneratedBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasInformedBy`
--

DROP TABLE IF EXISTS `WasInformedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasInformedBy` (
  `idWasInformedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  PRIMARY KEY (`idWasInformedBy`),
  KEY `fk_WasInformedBy_Port_Entity_Task1_idx` (`Task_idTask`),
  KEY `fk_WasInformedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasInformedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasInformedBy_Port_Entity_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasInformedBy`
--

LOCK TABLES `WasInformedBy` WRITE;
/*!40000 ALTER TABLE `WasInformedBy` DISABLE KEYS */;
INSERT INTO `WasInformedBy` VALUES (1,'task 1 was successful for activity 1',1,1),(2,'task 1 was successful for activity 1',1,1),(3,'task 1 was successful for activity 1',1,1),(4,'task 1 was successful for activity 1',1,1),(5,'task 1 was successful for activity 1',1,1),(6,'task 1 was successful for activity 1',1,1),(7,'task 1 was successful for activity 1',1,1),(8,'task 1 was successful for activity 1',1,1),(9,'task 1 was successful for activity 1',1,1),(10,'task 5 was successful for activity 1',5,1),(11,'task 1 was successful for activity 1',1,1),(12,'task 1 was successful for activity 1',1,1),(13,'task 5 was successful for activity 1',5,1),(59,'Task Sum was successful for activity Calculo',1,3),(60,'Task Subtraction was successful for activity Calculo',3,3),(61,'Task Multiplication was successful for activity Calculo',5,3),(62,'Task Sum was successful for activity Calculo',1,3),(63,'Task Subtraction was successful for activity Calculo',3,3),(64,'Task Multiplication was successful for activity Calculo',5,3),(65,'Task Sum was successful for activity Calculo',1,3),(66,'Task Subtraction was successful for activity Calculo',3,3),(67,'Task Multiplication was successful for activity Calculo',5,3),(68,'Task Sum was successful for activity Calculo',1,3),(69,'Task Subtraction was successful for activity Calculo',3,3),(70,'Task Multiplication was successful for activity Calculo',5,3),(71,'Task geneID was successful for activity Nuclear Protein Database Query Service',10,4),(72,'Task geneIDCon was successful for activity Nuclear Protein Database Query Service',11,4),(73,'Task geneIDCon was successful for activity Nuclear Protein Database Query Service',11,4),(74,'Task geneID was successful for activity Nuclear Protein Database Query Service',10,4),(75,'Task geneIDCon was successful for activity Nuclear Protein Database Query Service',11,4),(76,'Task geneIDCon was successful for activity Nuclear Protein Database Query Service',11,4);
/*!40000 ALTER TABLE `WasInformedBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasRevisionOf`
--

DROP TABLE IF EXISTS `WasRevisionOf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasRevisionOf` (
  `idWasRevisionOf` int(11) NOT NULL AUTO_INCREMENT,
  `RevisionOf` int(11) NOT NULL,
  `RevisionTo` int(11) NOT NULL,
  `Type` varchar(255) CHARACTER SET utf8 DEFAULT 'Corrective',
  PRIMARY KEY (`idWasRevisionOf`),
  KEY `fk_WasDerivedFrom_Workflow1_idx` (`RevisionOf`),
  KEY `fk_WasDerivedFrom_Workflow2_idx` (`RevisionTo`),
  CONSTRAINT `fk_WasDerivedFrom_Workflow10` FOREIGN KEY (`RevisionOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasDerivedFrom_Workflow20` FOREIGN KEY (`RevisionTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasRevisionOf`
--

LOCK TABLES `WasRevisionOf` WRITE;
/*!40000 ALTER TABLE `WasRevisionOf` DISABLE KEYS */;
INSERT INTO `WasRevisionOf` VALUES (1,4,5,'Corretive'),(2,1,7,'1'),(3,1,7,'1'),(4,1,7,'1'),(5,1,7,'1'),(6,1,7,'1');
/*!40000 ALTER TABLE `WasRevisionOf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasStartedBy`
--

DROP TABLE IF EXISTS `WasStartedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasStartedBy` (
  `idWasStartedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateStarted` datetime DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idWasStartedBy`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_WasStartedBy_Activity1_idx` (`Activity_idActivity`),
  CONSTRAINT `fk_WasStartedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WasStartedBy_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasStartedBy`
--

LOCK TABLES `WasStartedBy` WRITE;
/*!40000 ALTER TABLE `WasStartedBy` DISABLE KEYS */;
INSERT INTO `WasStartedBy` VALUES (1,1,1,'2015-08-06 21:12:24','task 1 started to activity 1'),(2,1,1,'2015-08-06 21:21:26','task 1 started to activity 1'),(3,1,1,'2015-08-06 21:29:01','task 1 started to activity 1'),(4,1,1,'2015-08-06 21:29:02','task 1 started to activity 1'),(5,1,1,'2015-08-06 21:31:48','task 1 started to activity 1'),(6,1,1,'2015-08-06 21:31:51','task 1 started to activity 1'),(7,5,1,'2015-08-06 21:36:37','task 5 started to activity 1'),(8,5,1,'2015-08-06 21:36:39','task 5 started to activity 1'),(9,1,1,'2015-08-06 21:39:17','task 1 started to activity 1'),(10,5,1,'2015-08-06 21:39:18','task 5 started to activity 1'),(11,5,1,'2015-08-06 21:39:20','task 5 started to activity 1'),(32,5,3,'2015-10-08 16:00:46','Task Multiplication started to activity Calculo'),(33,5,3,'2015-10-08 16:00:47','Task Multiplication started to activity Calculo'),(34,5,3,'2015-10-19 17:06:36','Task Multiplication started to activity Calculo'),(35,5,3,'2015-10-19 17:06:37','Task Multiplication started to activity Calculo'),(36,5,3,'2015-10-19 19:07:42','Task Multiplication started to activity Calculo'),(37,5,3,'2015-10-19 19:07:47','Task Multiplication started to activity Calculo'),(38,8,1,'2016-07-26 15:18:08','Task Add or Subtract started to activity Calculus'),(39,8,1,'2016-07-26 15:45:49','Task Add or Subtract started to activity Calculus'),(40,8,1,'2016-07-26 15:48:07','Task Add or Subtract started to activity Calculus'),(41,8,1,'2016-07-26 15:49:17','Task Add or Subtract started to activity Calculus'),(42,8,1,'2016-07-26 15:50:58','Task Add or Subtract started to activity Calculus'),(43,10,4,'2016-10-19 16:36:20','Task geneID started to activity Nuclear Protein Database Query Service'),(44,10,4,'2016-10-19 16:36:20','Task geneID started to activity Nuclear Protein Database Query Service'),(45,11,4,'2016-10-19 16:36:22','Task geneIDCon started to activity Nuclear Protein Database Query Service'),(46,10,4,'2016-10-26 14:59:09','Task geneID started to activity Nuclear Protein Database Query Service'),(47,10,4,'2016-10-26 14:59:09','Task geneID started to activity Nuclear Protein Database Query Service'),(48,11,4,'2016-10-26 14:59:10','Task geneIDCon started to activity Nuclear Protein Database Query Service');
/*!40000 ALTER TABLE `WasStartedBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WasStartedByWT`
--

DROP TABLE IF EXISTS `WasStartedByWT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WasStartedByWT` (
  `idWasStartedByWT` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Started` datetime NOT NULL,
  PRIMARY KEY (`idWasStartedByWT`),
  KEY `fk_Workflow_has_Task_Task1_idx` (`Task_idTask`),
  KEY `fk_Workflow_has_Task_Workflow1_idx` (`Workflow_idWorkflow`),
  CONSTRAINT `fk_Workflow_has_Task_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Workflow_has_Task_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasStartedByWT`
--

LOCK TABLES `WasStartedByWT` WRITE;
/*!40000 ALTER TABLE `WasStartedByWT` DISABLE KEYS */;
/*!40000 ALTER TABLE `WasStartedByWT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Workflow`
--

DROP TABLE IF EXISTS `Workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Workflow` (
  `idWorkflow` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Version` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `DateVersion` date DEFAULT NULL,
  `NumberStage` int(11) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `SGWfC_idSGWfC` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWorkflow`),
  KEY `fk_Workflow_SGWfC1_idx` (`SGWfC_idSGWfC`),
  CONSTRAINT `fk_Workflow_SGWfC1` FOREIGN KEY (`SGWfC_idSGWfC`) REFERENCES `SGWfC` (`idSGWfC`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Workflow`
--

LOCK TABLES `Workflow` WRITE;
/*!40000 ALTER TABLE `Workflow` DISABLE KEYS */;
INSERT INTO `Workflow` VALUES (1,'SimpleAddition','Sum of two values','01.00.00','2015-06-18',1,NULL,1),(2,'SimpleSum','Sum of three values','01.00.00','2015-06-19',2,NULL,1),(3,'SimpleSum2','Sum of four values','01.00.00','2015-06-22',3,NULL,1),(4,'SimpleCount','Calculation values','01.00.00','2015-06-24',3,NULL,1),(5,'SimpleCount2','Calculation values','01.00.00','2015-06-26',3,NULL,1),(6,'Demonstracao','Apresentacao NenC','01.00.00','2015-10-08',3,NULL,1),(7,'Testando','Não faço ideia','01.00.00','2016-07-26',5,'',1),(9,'Simple Equation Solver','This workflow solves for the problem x = 3 : |(x+5)/(x-5)|','00.01.00','2011-09-22',5,'http://www.myexperiment.org/workflows/2404.html',1),(10,'GeneExtraction','This workflow demonstrates the use of the remote genomics data service to  retrieve gene ID from its gene name.  Author: Ilkay Altintas, SDSC ','01.00.00','2016-10-17',8,'',NULL);
/*!40000 ALTER TABLE `Workflow` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collaboration_service`
--

LOCK TABLES `collaboration_service` WRITE;
/*!40000 ALTER TABLE `collaboration_service` DISABLE KEYS */;
INSERT INTO `collaboration_service` VALUES (1,1,1,1,1,1,'User List UFJF','User List UFJF members.',0),(2,2,2,2,2,1,'User List USP','User List USP.',0),(3,3,3,3,3,1,'Protein Sequence Similarity	','Protein Sequence Similarity	',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collaborative_service_type`
--

LOCK TABLES `collaborative_service_type` WRITE;
/*!40000 ALTER TABLE `collaborative_service_type` DISABLE KEYS */;
INSERT INTO `collaborative_service_type` VALUES (1,'User List','Service to control a users list of a group (or institution).'),(2,'Workflow Prototyping','Service for prototyping of a scientific workflow.'),(3,'Teste','Just Testing');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communication_service`
--

LOCK TABLES `communication_service` WRITE;
/*!40000 ALTER TABLE `communication_service` DISABLE KEYS */;
INSERT INTO `communication_service` VALUES (1,0,0,0,0,0,0,0,0,0,0,0,0),(2,0,0,0,0,0,0,0,0,0,0,0,0),(3,0,0,0,0,0,0,0,0,0,0,0,0);
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
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `component_name` varchar(45) DEFAULT NULL,
  `component_description` varchar(255) DEFAULT NULL,
  `component_file_location` varchar(255) DEFAULT NULL,
  `component_file_extension_format` varchar(45) DEFAULT NULL,
  `service_id` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  `component_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_artifact_service1_idx` (`service_id`),
  KEY `fk_artifact_Agent1_idx` (`Agent_idAgent`),
  KEY `fk_component_component_type1_idx` (`component_type_id`),
  CONSTRAINT `fk_artifact_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_artifact_service1` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_component_component_type1` FOREIGN KEY (`component_type_id`) REFERENCES `component_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'Teste','testet','teste','xml',1,3,1),(2,'Modelo de Features de Teste','Um modelo de features','home/documents/featuresmodels/modelo.xml','xml',2,5,2),(3,'Modelo de Features de Teste','Um modelo de features','home/documents/featuresmodels/modelo.xml','xml',2,5,2),(4,'Ontology','Ontologu','htto','owl',1,5,1);
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_type`
--

DROP TABLE IF EXISTS `component_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `component_type_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_type`
--

LOCK TABLES `component_type` WRITE;
/*!40000 ALTER TABLE `component_type` DISABLE KEYS */;
INSERT INTO `component_type` VALUES (1,'Ontology'),(2,'Features Model'),(3,'Mapping File');
/*!40000 ALTER TABLE `component_type` ENABLE KEYS */;
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
  `group_concept` varchar(45) NOT NULL,
  `concept_service` varchar(45) NOT NULL,
  `ratio` double DEFAULT NULL,
  `has_element` tinyint(1) NOT NULL DEFAULT '0',
  `validity` tinyint(1) NOT NULL DEFAULT '0',
  `conceptService1` varchar(45) DEFAULT NULL,
  `conceptService2` varchar(45) DEFAULT NULL,
  `id_struct_xml` bigint(20) DEFAULT NULL,
  `descriptionService1` varchar(255) DEFAULT NULL,
  `descriptionService2` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_concept_xml`),
  KEY `id_struct_xml_fka_idx` (`id_struct_xml`),
  CONSTRAINT `id_struct_xml_fka` FOREIGN KEY (`id_struct_xml`) REFERENCES `interoperability_struct_xml` (`id_struct_xml`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_xml`
--

LOCK TABLES `concept_xml` WRITE;
/*!40000 ALTER TABLE `concept_xml` DISABLE KEYS */;
INSERT INTO `concept_xml` VALUES (1,'Group Service','Group',NULL,0,0,NULL,NULL,1,NULL,NULL,NULL),(2,'Group Service','Participant',NULL,0,0,NULL,NULL,1,NULL,NULL,NULL),(3,'Coordination Service','Role',100,1,0,'Manager','Manager',1,NULL,NULL,NULL),(4,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',1,NULL,NULL,NULL),(5,'Coordination Service','Status',NULL,0,0,NULL,NULL,1,NULL,NULL,NULL),(6,'Group Service','Group',NULL,0,1,NULL,NULL,2,NULL,NULL,NULL),(7,'Group Service','Participant',NULL,0,0,NULL,NULL,2,NULL,NULL,NULL),(8,'Coordination Service','Role',100,1,0,'Manager','Manager',2,NULL,NULL,NULL),(9,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',2,NULL,NULL,NULL),(10,'Coordination Service','Status',NULL,0,0,NULL,NULL,2,NULL,NULL,NULL),(11,'Group Service','Group',NULL,0,0,NULL,NULL,3,NULL,NULL,NULL),(12,'Group Service','Participant',NULL,0,0,NULL,NULL,3,NULL,NULL,NULL),(13,'Coordination Service','Role',100,1,0,'Manager','Manager',3,NULL,NULL,NULL),(14,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',3,NULL,NULL,NULL),(15,'Coordination Service','Status',NULL,0,0,NULL,NULL,3,NULL,NULL,NULL),(16,'Group Service','Group',NULL,0,1,NULL,NULL,4,NULL,NULL,NULL),(17,'Group Service','Participant',NULL,0,0,NULL,NULL,4,NULL,NULL,NULL),(18,'Coordination Service','Role',100,1,0,'Manager','Manager',4,NULL,NULL,NULL),(19,'Coordination Service','Role',76.32,1,0,'Scientist','Researcher',4,NULL,NULL,NULL),(20,'Coordination Service','Status',NULL,0,0,NULL,NULL,4,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperation_service`
--

LOCK TABLES `cooperation_service` WRITE;
/*!40000 ALTER TABLE `cooperation_service` DISABLE KEYS */;
INSERT INTO `cooperation_service` VALUES (1,0,0,0,0,0,0,0),(2,0,0,0,0,0,0,0),(3,0,0,1,0,0,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordination_service`
--

LOCK TABLES `coordination_service` WRITE;
/*!40000 ALTER TABLE `coordination_service` DISABLE KEYS */;
INSERT INTO `coordination_service` VALUES (1,0,0,1,1,0,0,0),(2,0,0,1,1,0,0,0),(3,0,0,1,1,0,0,0);
/*!40000 ALTER TABLE `coordination_service` ENABLE KEYS */;
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
  CONSTRAINT `fk_experiment_services_Experiment1` FOREIGN KEY (`idExperiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `features_model`
--

DROP TABLE IF EXISTS `features_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `features_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `features_modelling_tool` varchar(45) DEFAULT NULL,
  `features_model_img_location` varchar(45) DEFAULT NULL,
  `component_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_features_model_component1_idx` (`component_id`),
  CONSTRAINT `fk_features_model_component1` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features_model`
--

LOCK TABLES `features_model` WRITE;
/*!40000 ALTER TABLE `features_model` DISABLE KEYS */;
INSERT INTO `features_model` VALUES (1,'Eclipse - EMF Feature Model','home/documents/featuresmodel.png',2);
/*!40000 ALTER TABLE `features_model` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_service`
--

LOCK TABLES `group_service` WRITE;
/*!40000 ALTER TABLE `group_service` DISABLE KEYS */;
INSERT INTO `group_service` VALUES (1,1,0,0,0,1,1,0),(2,1,0,0,0,1,1,0),(3,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `group_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hardware_configurations`
--

DROP TABLE IF EXISTS `hardware_configurations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hardware_configurations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hardware_configurations_cpu` varchar(100) DEFAULT NULL,
  `hardware_configurations_ram_gb` varchar(45) DEFAULT NULL,
  `hardware_configurations_rom_gb` varchar(45) DEFAULT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`service_id`),
  KEY `fk_hardware_configurations_service1_idx` (`service_id`),
  CONSTRAINT `fk_hardware_configurations_service1` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hardware_configurations`
--

LOCK TABLES `hardware_configurations` WRITE;
/*!40000 ALTER TABLE `hardware_configurations` DISABLE KEYS */;
INSERT INTO `hardware_configurations` VALUES (1,'123','456','789',1);
/*!40000 ALTER TABLE `hardware_configurations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interoperability_struct_xml`
--

DROP TABLE IF EXISTS `interoperability_struct_xml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interoperability_struct_xml` (
  `id_struct_xml` bigint(20) NOT NULL AUTO_INCREMENT,
  `interoperability_name` varchar(255) NOT NULL DEFAULT 'Teste',
  `first_service_id` bigint(20) NOT NULL,
  `second_service_id` bigint(20) NOT NULL,
  `agent_id` bigint(20) NOT NULL DEFAULT '100',
  `first_type_service` varchar(45) NOT NULL,
  `second_type_service` varchar(45) NOT NULL,
  PRIMARY KEY (`id_struct_xml`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
-- Table structure for table `mapping_file`
--

DROP TABLE IF EXISTS `mapping_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapping_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mapping_file_location` varchar(45) DEFAULT NULL,
  `features_model_id` int(11) NOT NULL,
  `ontology_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mapping_file_features_model1_idx` (`features_model_id`),
  KEY `fk_mapping_file_ontology1_idx` (`ontology_id`),
  CONSTRAINT `fk_mapping_file_features_model1` FOREIGN KEY (`features_model_id`) REFERENCES `features_model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mapping_file_ontology1` FOREIGN KEY (`ontology_id`) REFERENCES `ontology` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapping_file`
--

LOCK TABLES `mapping_file` WRITE;
/*!40000 ALTER TABLE `mapping_file` DISABLE KEYS */;
INSERT INTO `mapping_file` VALUES (1,'home/mapping.xml',1,1);
/*!40000 ALTER TABLE `mapping_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_service`
--

DROP TABLE IF EXISTS `message_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `date_message` datetime NOT NULL,
  `issuer_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `issuer_id_agent_fkc_idx` (`issuer_id`),
  KEY `receiver_id_agent_fkc_idx` (`receiver_id`),
  CONSTRAINT `issuer_id_agent_fkc` FOREIGN KEY (`issuer_id`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `receiver_id_agent_fkc` FOREIGN KEY (`receiver_id`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_service`
--

LOCK TABLES `message_service` WRITE;
/*!40000 ALTER TABLE `message_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ontology`
--

DROP TABLE IF EXISTS `ontology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ontology` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ontology_format` varchar(45) DEFAULT NULL,
  `component_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ontology_component1_idx` (`component_id`),
  CONSTRAINT `fk_ontology_component1` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ontology`
--

LOCK TABLES `ontology` WRITE;
/*!40000 ALTER TABLE `ontology` DISABLE KEYS */;
INSERT INTO `ontology` VALUES (1,'owl',4);
/*!40000 ALTER TABLE `ontology` ENABLE KEYS */;
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
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(45) DEFAULT NULL,
  `service_description` varchar(255) DEFAULT NULL,
  `service_file_location` varchar(45) DEFAULT NULL,
  `service_type` varchar(45) DEFAULT NULL,
  `service_category` varchar(45) DEFAULT NULL,
  `service_documentation_url` varchar(200) DEFAULT NULL,
  `service_license_type` varchar(45) DEFAULT NULL,
  `service_cost` varchar(45) DEFAULT NULL,
  `service_usage_conditions` varchar(200) DEFAULT NULL,
  `service_contact_info_url` varchar(45) DEFAULT NULL,
  `service_how_to_cite` varchar(45) DEFAULT NULL,
  `service_purpose` varchar(200) DEFAULT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_Agent1_idx` (`Agent_idAgent`),
  CONSTRAINT `fk_service_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'teste','teste','teste',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5),(2,'First Service','Test','test','Collaboration','Test','Test','Public','TestCost','TestUsage','Testcontact','Testhow','testpurpose',5);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_has_service`
--

DROP TABLE IF EXISTS `service_has_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_has_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) DEFAULT NULL,
  `service_id1` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_has_service_service2_idx` (`service_id1`),
  KEY `fk_service_has_service_service1_idx` (`service_id`),
  CONSTRAINT `fk_service_has_service_service1` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_has_service_service2` FOREIGN KEY (`service_id1`) REFERENCES `service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_has_service`
--

LOCK TABLES `service_has_service` WRITE;
/*!40000 ALTER TABLE `service_has_service` DISABLE KEYS */;
INSERT INTO `service_has_service` VALUES (1,1,2);
/*!40000 ALTER TABLE `service_has_service` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Online','Online'),(2,'Offline','Offline');
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
INSERT INTO `steps_service` VALUES (1,201),(2,201),(2,251),(2,301),(2,351),(3,251),(3,301);
/*!40000 ALTER TABLE `steps_service` ENABLE KEYS */;
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
  CONSTRAINT `fk_taverna_workflow_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_taverna_workflow_Experiment1` FOREIGN KEY (`experiment_id`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-23 21:50:27
