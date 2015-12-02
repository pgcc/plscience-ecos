CREATE DATABASE  IF NOT EXISTS `plscience` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `plscience`;
-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: plscience
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
  `Description` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  `InputPort_idPort` int(11) DEFAULT NULL,
  `OutputPort_idPort` int(11) DEFAULT NULL,
  PRIMARY KEY (`idActedOnBehalfOf`),
  KEY `FK_ActedOnBehalfOf_InputPort_idPort` (`InputPort_idPort`),
  KEY `FK_ActedOnBehalfOf_OutputPort_idPort` (`OutputPort_idPort`),
  CONSTRAINT `FK_ActedOnBehalfOf_InputPort_idPort` FOREIGN KEY (`InputPort_idPort`) REFERENCES `InputPort` (`idPort`),
  CONSTRAINT `FK_ActedOnBehalfOf_OutputPort_idPort` FOREIGN KEY (`OutputPort_idPort`) REFERENCES `OutputPort` (`idPort`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActedOnBehalfOf`
--

LOCK TABLES `ActedOnBehalfOf` WRITE;
/*!40000 ALTER TABLE `ActedOnBehalfOf` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Function` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Entity_idEntity` int(11) DEFAULT NULL,
  PRIMARY KEY (`idActivity`),
  KEY `FK_Activity_Entity_idEntity` (`Entity_idEntity`),
  CONSTRAINT `FK_Activity_Entity_idEntity` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Function` varchar(255) DEFAULT NULL,
  `Login` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Institution` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAgent`),
  KEY `FK_Agent_Institution` (`Institution`),
  CONSTRAINT `FK_Agent_Institution` FOREIGN KEY (`Institution`) REFERENCES `Entity` (`idEntity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Agent`
--

LOCK TABLES `Agent` WRITE;
/*!40000 ALTER TABLE `Agent` DISABLE KEYS */;
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
  `Acronym` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEntity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entity`
--

LOCK TABLES `Entity` WRITE;
/*!40000 ALTER TABLE `Entity` DISABLE KEYS */;
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
  `DateEnded` date DEFAULT NULL,
  `DateStarted` date DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `number_stages` int(11) DEFAULT NULL,
  `parsifal_review` int(11) DEFAULT NULL,
  `Version` varchar(255) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Entity_idEntity` int(11) DEFAULT NULL,
  `idAgent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExperiment`),
  KEY `FK_Experiment_idAgent` (`idAgent`),
  KEY `FK_Experiment_Activity_idActivity` (`Activity_idActivity`),
  KEY `FK_Experiment_Entity_idEntity` (`Entity_idEntity`),
  CONSTRAINT `FK_Experiment_Activity_idActivity` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`),
  CONSTRAINT `FK_Experiment_Entity_idEntity` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`),
  CONSTRAINT `FK_Experiment_idAgent` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Experiment`
--

LOCK TABLES `Experiment` WRITE;
/*!40000 ALTER TABLE `Experiment` DISABLE KEYS */;
INSERT INTO `Experiment` VALUES (1,NULL,'2015-11-16','Protein chain experiment test','Protein Chain',5,NULL,'',NULL,NULL,NULL),(2,NULL,'2015-11-30','Genome variant to Protein change','Genome',3,NULL,'',NULL,NULL,NULL);
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
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `FK_InputPort_Task_idTask` (`Task_idTask`),
  CONSTRAINT `FK_InputPort_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InputPort`
--

LOCK TABLES `InputPort` WRITE;
/*!40000 ALTER TABLE `InputPort` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`idIsPartOf`),
  KEY `FK_IsPartOf_ResearchGroup_idResearchGroup` (`ResearchGroup_idResearchGroup`),
  KEY `FK_IsPartOf_Agent_idAgent` (`Agent_idAgent`),
  CONSTRAINT `FK_IsPartOf_Agent_idAgent` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`),
  CONSTRAINT `FK_IsPartOf_ResearchGroup_idResearchGroup` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IsPartOf`
--

LOCK TABLES `IsPartOf` WRITE;
/*!40000 ALTER TABLE `IsPartOf` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPort`),
  KEY `FK_OutputPort_Task_idTask` (`Task_idTask`),
  CONSTRAINT `FK_OutputPort_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OutputPort`
--

LOCK TABLES `OutputPort` WRITE;
/*!40000 ALTER TABLE `OutputPort` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL,
  PRIMARY KEY (`idResearchGroup`),
  KEY `FK_ResearchGroup_Agent_idAgent_chef` (`Agent_idAgent_chef`),
  CONSTRAINT `FK_ResearchGroup_Agent_idAgent_chef` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `Agent` (`idAgent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResearchGroup`
--

LOCK TABLES `ResearchGroup` WRITE;
/*!40000 ALTER TABLE `ResearchGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `ResearchGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',50);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SGWfC`
--

DROP TABLE IF EXISTS `SGWfC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SGWfC` (
  `idSGWfC` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSGWfC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SGWfC`
--

LOCK TABLES `SGWfC` WRITE;
/*!40000 ALTER TABLE `SGWfC` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsed`),
  KEY `FK_Used_Workflow_idWorkflow` (`Workflow_idWorkflow`),
  KEY `FK_Used_Task_idTask` (`Task_idTask`),
  CONSTRAINT `FK_Used_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`),
  CONSTRAINT `FK_Used_Workflow_idWorkflow` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Used`
--

LOCK TABLES `Used` WRITE;
/*!40000 ALTER TABLE `Used` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasAssociatedWith`),
  KEY `FK_WasAssociatedWith_Workflow_idWorkflow` (`Workflow_idWorkflow`),
  KEY `FK_WasAssociatedWith_Experiment_Experiment` (`Experiment_Experiment`),
  CONSTRAINT `FK_WasAssociatedWith_Experiment_Experiment` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`),
  CONSTRAINT `FK_WasAssociatedWith_Workflow_idWorkflow` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasAssociatedWith`
--

LOCK TABLES `WasAssociatedWith` WRITE;
/*!40000 ALTER TABLE `WasAssociatedWith` DISABLE KEYS */;
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
  `Description` varchar(255) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Agent_idAgent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasControledBy`),
  KEY `FK_WasControledBy_Activity_idActivity` (`Activity_idActivity`),
  KEY `FK_WasControledBy_Agent_idAgent` (`Agent_idAgent`),
  CONSTRAINT `FK_WasControledBy_Activity_idActivity` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`),
  CONSTRAINT `FK_WasControledBy_Agent_idAgent` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `Type` varchar(255) DEFAULT NULL,
  `DerivedOf` int(11) DEFAULT NULL,
  `DerivedTo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasDerivedFrom`),
  KEY `FK_WasDerivedFrom_DerivedTo` (`DerivedTo`),
  KEY `FK_WasDerivedFrom_DerivedOf` (`DerivedOf`),
  CONSTRAINT `FK_WasDerivedFrom_DerivedOf` FOREIGN KEY (`DerivedOf`) REFERENCES `Workflow` (`idWorkflow`),
  CONSTRAINT `FK_WasDerivedFrom_DerivedTo` FOREIGN KEY (`DerivedTo`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasDerivedFrom`
--

LOCK TABLES `WasDerivedFrom` WRITE;
/*!40000 ALTER TABLE `WasDerivedFrom` DISABLE KEYS */;
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
  `DateEnded` datetime DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasEndedBy`),
  KEY `FK_WasEndedBy_Task_idTask` (`Task_idTask`),
  KEY `FK_WasEndedBy_Activity_idActivity` (`Activity_idActivity`),
  CONSTRAINT `FK_WasEndedBy_Activity_idActivity` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`),
  CONSTRAINT `FK_WasEndedBy_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasEndedBy`
--

LOCK TABLES `WasEndedBy` WRITE;
/*!40000 ALTER TABLE `WasEndedBy` DISABLE KEYS */;
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
  `Ended` datetime DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasEndedByWT`),
  KEY `FK_WasEndedByWT_Workflow_idWorkflow` (`Workflow_idWorkflow`),
  KEY `FK_WasEndedByWT_Task_idTask` (`Task_idTask`),
  CONSTRAINT `FK_WasEndedByWT_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`),
  CONSTRAINT `FK_WasEndedByWT_Workflow_idWorkflow` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `idWasGeneratedBy` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasGeneratedBy`),
  KEY `FK_WasGeneratedBy_Experiment_Experiment` (`Experiment_Experiment`),
  KEY `FK_WasGeneratedBy_ResearchGroup_idResearchGroup` (`ResearchGroup_idResearchGroup`),
  CONSTRAINT `FK_WasGeneratedBy_Experiment_Experiment` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`),
  CONSTRAINT `FK_WasGeneratedBy_ResearchGroup_idResearchGroup` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `Description` varchar(255) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasInformedBy`),
  KEY `FK_WasInformedBy_Activity_idActivity` (`Activity_idActivity`),
  KEY `FK_WasInformedBy_Task_idTask` (`Task_idTask`),
  CONSTRAINT `FK_WasInformedBy_Activity_idActivity` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`),
  CONSTRAINT `FK_WasInformedBy_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasInformedBy`
--

LOCK TABLES `WasInformedBy` WRITE;
/*!40000 ALTER TABLE `WasInformedBy` DISABLE KEYS */;
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
  `Type` varchar(255) DEFAULT NULL,
  `RevisionOf` int(11) DEFAULT NULL,
  `RevisionTo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasRevisionOf`),
  KEY `FK_WasRevisionOf_RevisionTo` (`RevisionTo`),
  KEY `FK_WasRevisionOf_RevisionOf` (`RevisionOf`),
  CONSTRAINT `FK_WasRevisionOf_RevisionOf` FOREIGN KEY (`RevisionOf`) REFERENCES `Workflow` (`idWorkflow`),
  CONSTRAINT `FK_WasRevisionOf_RevisionTo` FOREIGN KEY (`RevisionTo`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasRevisionOf`
--

LOCK TABLES `WasRevisionOf` WRITE;
/*!40000 ALTER TABLE `WasRevisionOf` DISABLE KEYS */;
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
  `DateStarted` datetime DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasStartedBy`),
  KEY `FK_WasStartedBy_Task_idTask` (`Task_idTask`),
  KEY `FK_WasStartedBy_Activity_idActivity` (`Activity_idActivity`),
  CONSTRAINT `FK_WasStartedBy_Activity_idActivity` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`),
  CONSTRAINT `FK_WasStartedBy_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WasStartedBy`
--

LOCK TABLES `WasStartedBy` WRITE;
/*!40000 ALTER TABLE `WasStartedBy` DISABLE KEYS */;
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
  `Started` datetime DEFAULT NULL,
  `Task_idTask` int(11) DEFAULT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWasStartedByWT`),
  KEY `FK_WasStartedByWT_Task_idTask` (`Task_idTask`),
  KEY `FK_WasStartedByWT_Workflow_idWorkflow` (`Workflow_idWorkflow`),
  CONSTRAINT `FK_WasStartedByWT_Task_idTask` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`),
  CONSTRAINT `FK_WasStartedByWT_Workflow_idWorkflow` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `DateVersion` date DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `NumberStage` int(11) DEFAULT NULL,
  `Version` varchar(255) DEFAULT NULL,
  `SGWfC_idSGWfC` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWorkflow`),
  KEY `FK_Workflow_SGWfC_idSGWfC` (`SGWfC_idSGWfC`),
  CONSTRAINT `FK_Workflow_SGWfC_idSGWfC` FOREIGN KEY (`SGWfC_idSGWfC`) REFERENCES `SGWfC` (`idSGWfC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Workflow`
--

LOCK TABLES `Workflow` WRITE;
/*!40000 ALTER TABLE `Workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `Workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_services`
--

DROP TABLE IF EXISTS `experiment_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment_services` (
  `id` bigint(20) NOT NULL,
  `latestTime_used` date DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `idExperiment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_experiment_services_idExperiment` (`idExperiment`),
  CONSTRAINT `FK_experiment_services_idExperiment` FOREIGN KEY (`idExperiment`) REFERENCES `Experiment` (`idExperiment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_services`
--

LOCK TABLES `experiment_services` WRITE;
/*!40000 ALTER TABLE `experiment_services` DISABLE KEYS */;
INSERT INTO `experiment_services` VALUES (1,'2015-11-30','',0,1),(2,'2015-11-30','Protein_alignment_transmembrane',1,1),(3,'2015-10-30','protein_protein_interactions.xml',2,1),(4,'2015-11-30','Protein_alignment_transmembrane',3,1),(5,'2015-11-30','',4,1),(6,'2015-10-30','protein_protein_interactions.xml',0,1),(7,'2015-10-31','protein_protein_interactions.xml',0,2),(8,'2015-11-30','Nuclear Protein Database Query Service',1,2),(9,'2015-11-30','',2,2),(10,'2015-11-30','Nuclear Protein Database Query Service',1,2);
/*!40000 ALTER TABLE `experiment_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scientist`
--

DROP TABLE IF EXISTS `scientist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scientist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scientist`
--

LOCK TABLES `scientist` WRITE;
/*!40000 ALTER TABLE `scientist` DISABLE KEYS */;
/*!40000 ALTER TABLE `scientist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taverna_workflow`
--

DROP TABLE IF EXISTS `taverna_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taverna_workflow` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `t2flow` mediumtext,
  `idAgent` int(11) DEFAULT NULL,
  `experiment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_taverna_workflow_experiment_id` (`experiment_id`),
  KEY `FK_taverna_workflow_idAgent` (`idAgent`),
  CONSTRAINT `FK_taverna_workflow_experiment_id` FOREIGN KEY (`experiment_id`) REFERENCES `Experiment` (`idExperiment`),
  CONSTRAINT `FK_taverna_workflow_idAgent` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`)
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
  `taverna_workflow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_taverna_workflow_input_taverna_workflow_id` (`taverna_workflow_id`),
  CONSTRAINT `FK_taverna_workflow_input_taverna_workflow_id` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`)
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
  `id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_taverna_workflow_run_taverna_workflow_id` (`taverna_workflow_id`),
  CONSTRAINT `FK_taverna_workflow_run_taverna_workflow_id` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`)
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
  `taverna_workflow_input_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tavernaworkflowruninputvaluetavernaworkflowinputid` (`taverna_workflow_input_id`),
  CONSTRAINT `tavernaworkflowruninputvaluetavernaworkflowinputid` FOREIGN KEY (`taverna_workflow_input_id`) REFERENCES `taverna_workflow_input` (`id`)
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

-- Dump completed on 2015-12-02 12:03:00
