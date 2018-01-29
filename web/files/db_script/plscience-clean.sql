-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: plscience
-- ------------------------------------------------------
-- Server version	5.5.47

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
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL,
   `dblp_name` VARCHAR(255) NULL DEFAULT NULL,
  `scholar_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idAgent`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `fk_Agent_Entity1_idx` (`Institution`),
  KEY `fk_Status_Agent_idx` (`status_id`),
  KEY `fk_competence_agent_idx` (`competence_id`),
  KEY `fk_role_agent_idx` (`role_id`),
  CONSTRAINT `fk_Agent_Entity1` FOREIGN KEY (`Institution`) REFERENCES `entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_competence_agent` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_agent` FOREIGN KEY (`role_id`) REFERENCES `roler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_agent` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `issuer_id_agent_fkc` FOREIGN KEY (`issuer_id`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `receiver_id_agent_fkc` FOREIGN KEY (`receiver_id`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-18 16:03:25
