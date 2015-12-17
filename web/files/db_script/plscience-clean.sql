-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 17-Dez-2015 às 21:24
-- Versão do servidor: 5.5.46-0+deb8u1
-- PHP Version: 5.6.14-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `plscience`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `ActedOnBehalfOf`
--

CREATE TABLE IF NOT EXISTS `ActedOnBehalfOf` (
`idActedOnBehalfOf` int(11) NOT NULL,
  `InputPort_idPort` int(11) NOT NULL,
  `OutputPort_idPort` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Activity`
--

CREATE TABLE IF NOT EXISTS `Activity` (
`idActivity` int(11) NOT NULL,
  `Entity_idEntity` int(11) NOT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Function` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Agent`
--

CREATE TABLE IF NOT EXISTS `Agent` (
`idAgent` int(11) NOT NULL,
  `Login` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Institution` int(11) NOT NULL,
  `Function` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `collaboration_service`
--

CREATE TABLE IF NOT EXISTS `collaboration_service` (
`id` bigint(20) NOT NULL,
  `group_service_id` bigint(20) NOT NULL,
  `coordination_service_id` bigint(20) NOT NULL,
  `cooperation_service_id` bigint(20) NOT NULL,
  `communication_service_id` bigint(20) NOT NULL,
  `collaborative_service_type_id` bigint(20) NOT NULL,
  `collab_service_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  `developed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `collaborative_service_type`
--

CREATE TABLE IF NOT EXISTS `collaborative_service_type` (
`id` bigint(20) NOT NULL,
  `name_service_type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `communication_service`
--

CREATE TABLE IF NOT EXISTS `communication_service` (
`id` bigint(20) NOT NULL,
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
  `interpretation` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `competence`
--

CREATE TABLE IF NOT EXISTS `competence` (
`id` bigint(20) NOT NULL,
  `competence_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `competence_group_service`
--

CREATE TABLE IF NOT EXISTS `competence_group_service` (
  `group_service_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `concept_xml`
--

CREATE TABLE IF NOT EXISTS `concept_xml` (
`id_concept_xml` bigint(20) NOT NULL,
  `service` varchar(45) NOT NULL,
  `concept_service` varchar(45) NOT NULL,
  `ratio` double DEFAULT NULL,
  `has_concept` tinyint(1) NOT NULL DEFAULT '0',
  `validity` tinyint(1) NOT NULL DEFAULT '0',
  `conceptService1` varchar(45) DEFAULT NULL,
  `conceptService2` varchar(45) DEFAULT NULL,
  `id_struct_xml` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cooperation_service`
--

CREATE TABLE IF NOT EXISTS `cooperation_service` (
`id` bigint(20) NOT NULL,
  `activity` tinyint(1) NOT NULL DEFAULT '0',
  `task` tinyint(1) NOT NULL DEFAULT '0',
  `product` tinyint(1) NOT NULL DEFAULT '0',
  `artifact` tinyint(1) NOT NULL DEFAULT '0',
  `sharedSpace` tinyint(1) NOT NULL DEFAULT '0',
  `resource` tinyint(1) NOT NULL DEFAULT '0',
  `share` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `coordination_service`
--

CREATE TABLE IF NOT EXISTS `coordination_service` (
`id` bigint(20) NOT NULL,
  `workPlan` tinyint(1) NOT NULL DEFAULT '0',
  `deadline` tinyint(1) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `role` tinyint(1) NOT NULL DEFAULT '0',
  `policy` tinyint(1) NOT NULL DEFAULT '0',
  `monitoring` tinyint(1) NOT NULL DEFAULT '0',
  `coupling` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Entity`
--

CREATE TABLE IF NOT EXISTS `Entity` (
`idEntity` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Acronym` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Experiment`
--

CREATE TABLE IF NOT EXISTS `Experiment` (
`idExperiment` int(11) NOT NULL,
  `Entity_idEntity` int(11) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `idAgent` int(11) DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `DateStarted` date DEFAULT NULL,
  `DateEnded` date DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `number_stages` int(11) DEFAULT NULL,
  `parsifal_review` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `experiment_services`
--

CREATE TABLE IF NOT EXISTS `experiment_services` (
`id` bigint(20) NOT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `latestTime_used` date DEFAULT NULL,
  `idExperiment` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `group_service`
--

CREATE TABLE IF NOT EXISTS `group_service` (
`id` bigint(20) NOT NULL,
  `participant` tinyint(1) NOT NULL DEFAULT '0',
  `belief` tinyint(1) NOT NULL DEFAULT '0',
  `confidence` tinyint(1) NOT NULL DEFAULT '0',
  `motivation` tinyint(1) NOT NULL DEFAULT '0',
  `groupp` tinyint(1) NOT NULL DEFAULT '0',
  `competence` tinyint(1) NOT NULL DEFAULT '0',
  `goal` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `InputPort`
--

CREATE TABLE IF NOT EXISTS `InputPort` (
`idPort` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `interoperability_struct_xml`
--

CREATE TABLE IF NOT EXISTS `interoperability_struct_xml` (
`id_struct_xml` bigint(20) NOT NULL,
  `interoperability_name` varchar(45) NOT NULL DEFAULT 'Teste',
  `first_service_id` bigint(20) NOT NULL,
  `second_service_id` bigint(20) NOT NULL,
  `agent_id` bigint(20) NOT NULL DEFAULT '100',
  `first_type_service` varchar(45) NOT NULL,
  `second_type_service` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `IsPartOf`
--

CREATE TABLE IF NOT EXISTS `IsPartOf` (
`idIsPartOf` int(11) NOT NULL,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `OutputPort`
--

CREATE TABLE IF NOT EXISTS `OutputPort` (
`idPort` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ResearchGroup`
--

CREATE TABLE IF NOT EXISTS `ResearchGroup` (
`idResearchGroup` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `roler`
--

CREATE TABLE IF NOT EXISTS `roler` (
`id` bigint(20) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `hierarchy_level` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `role_coordination_service`
--

CREATE TABLE IF NOT EXISTS `role_coordination_service` (
  `coordination_service_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `SGWfC`
--

CREATE TABLE IF NOT EXISTS `SGWfC` (
  `idSGWfC` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `steps_scientific_experimentation`
--

CREATE TABLE IF NOT EXISTS `steps_scientific_experimentation` (
`id` bigint(20) NOT NULL,
  `name_step` varchar(255) NOT NULL,
  `number_step` int(11) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `steps_service`
--

CREATE TABLE IF NOT EXISTS `steps_service` (
  `collab_service_id` bigint(20) NOT NULL,
  `step_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Task`
--

CREATE TABLE IF NOT EXISTS `Task` (
`idTask` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `taverna_workflow`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow` (
`id` bigint(20) NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `t2flow` mediumtext,
  `idAgent` int(11) NOT NULL,
  `experiment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `taverna_workflow_input`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_input` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `taverna_workflow_run`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_run` (
`id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `taverna_workflow_run_input_value`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_run_input_value` (
  `id` bigint(20) NOT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `taverna_workflow_input_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Used`
--

CREATE TABLE IF NOT EXISTS `Used` (
`idUsed` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasAssociatedWith`
--

CREATE TABLE IF NOT EXISTS `WasAssociatedWith` (
`idWasAssociatedWith` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasControledBy`
--

CREATE TABLE IF NOT EXISTS `WasControledBy` (
`idWasControledBy` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasDerivedFrom`
--

CREATE TABLE IF NOT EXISTS `WasDerivedFrom` (
`idWasDerivedFrom` int(11) NOT NULL,
  `DerivedOf` int(11) NOT NULL,
  `DerivedTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasEndedBy`
--

CREATE TABLE IF NOT EXISTS `WasEndedBy` (
`idWasEndedBy` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateEnded` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasEndedByWT`
--

CREATE TABLE IF NOT EXISTS `WasEndedByWT` (
`idWasEndedByWT` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Ended` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasGeneratedBy`
--

CREATE TABLE IF NOT EXISTS `WasGeneratedBy` (
`idWasGeneratedBy` int(11) NOT NULL,
  `Experiment_Experiment` int(11) NOT NULL,
  `ResearchGroup_idResearchGroup` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasInformedBy`
--

CREATE TABLE IF NOT EXISTS `WasInformedBy` (
`idWasInformedBy` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasRevisionOf`
--

CREATE TABLE IF NOT EXISTS `WasRevisionOf` (
`idWasRevisionOf` int(11) NOT NULL,
  `RevisionOf` int(11) NOT NULL,
  `RevisionTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT 'Corrective'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasStartedBy`
--

CREATE TABLE IF NOT EXISTS `WasStartedBy` (
`idWasStartedBy` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateStarted` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `WasStartedByWT`
--

CREATE TABLE IF NOT EXISTS `WasStartedByWT` (
`idWasStartedByWT` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Started` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Workflow`
--

CREATE TABLE IF NOT EXISTS `Workflow` (
`idWorkflow` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `DateVersion` date DEFAULT NULL,
  `NumberStage` int(11) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `SGWfC_idSGWfC` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
 ADD PRIMARY KEY (`idActedOnBehalfOf`), ADD KEY `fk_InputPort_has_OutputPort_OutputPort1_idx` (`OutputPort_idPort`), ADD KEY `fk_InputPort_has_OutputPort_InputPort1_idx` (`InputPort_idPort`);

--
-- Indexes for table `Activity`
--
ALTER TABLE `Activity`
 ADD PRIMARY KEY (`idActivity`), ADD KEY `fk_Activity_Entity1_idx` (`Entity_idEntity`);

--
-- Indexes for table `Agent`
--
ALTER TABLE `Agent`
 ADD PRIMARY KEY (`idAgent`), ADD UNIQUE KEY `Login_UNIQUE` (`Login`), ADD UNIQUE KEY `Email_UNIQUE` (`Email`), ADD KEY `fk_Agent_Entity1_idx` (`Institution`);

--
-- Indexes for table `collaboration_service`
--
ALTER TABLE `collaboration_service`
 ADD PRIMARY KEY (`id`), ADD KEY `group_service_id_fka_idx` (`group_service_id`), ADD KEY `coordination_service_id_fka_idx` (`coordination_service_id`), ADD KEY `cooperation_service_id_fka_idx` (`cooperation_service_id`), ADD KEY `communication_service_id_fka_idx` (`communication_service_id`), ADD KEY `collaboration_service_type_id_fka_idx` (`collaborative_service_type_id`);

--
-- Indexes for table `collaborative_service_type`
--
ALTER TABLE `collaborative_service_type`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `communication_service`
--
ALTER TABLE `communication_service`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `competence`
--
ALTER TABLE `competence`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `competence_group_service`
--
ALTER TABLE `competence_group_service`
 ADD PRIMARY KEY (`group_service_id`,`competence_id`), ADD KEY `competence_id_fka_idx` (`competence_id`);

--
-- Indexes for table `concept_xml`
--
ALTER TABLE `concept_xml`
 ADD PRIMARY KEY (`id_concept_xml`), ADD KEY `id_struct_xml_fka_idx` (`id_struct_xml`);

--
-- Indexes for table `cooperation_service`
--
ALTER TABLE `cooperation_service`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coordination_service`
--
ALTER TABLE `coordination_service`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Entity`
--
ALTER TABLE `Entity`
 ADD PRIMARY KEY (`idEntity`);

--
-- Indexes for table `Experiment`
--
ALTER TABLE `Experiment`
 ADD PRIMARY KEY (`idExperiment`), ADD KEY `fk_Expiriment_Entity1_idx` (`Entity_idEntity`), ADD KEY `fk_Expiriment_Activity1_idx` (`Activity_idActivity`), ADD KEY `fk_Experiment_Agent1_idx` (`idAgent`);

--
-- Indexes for table `experiment_services`
--
ALTER TABLE `experiment_services`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_experiment_services_Experiment1_idx` (`idExperiment`);

--
-- Indexes for table `group_service`
--
ALTER TABLE `group_service`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `InputPort`
--
ALTER TABLE `InputPort`
 ADD PRIMARY KEY (`idPort`), ADD KEY `fk_Port_Task1` (`Task_idTask`);

--
-- Indexes for table `interoperability_struct_xml`
--
ALTER TABLE `interoperability_struct_xml`
 ADD PRIMARY KEY (`id_struct_xml`);

--
-- Indexes for table `IsPartOf`
--
ALTER TABLE `IsPartOf`
 ADD PRIMARY KEY (`idIsPartOf`), ADD KEY `fk_Agent_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`), ADD KEY `fk_Agent_has_ResearchGroup_Agent1_idx` (`Agent_idAgent`);

--
-- Indexes for table `OutputPort`
--
ALTER TABLE `OutputPort`
 ADD PRIMARY KEY (`idPort`), ADD KEY `fk_Port_Task1_idx` (`Task_idTask`);

--
-- Indexes for table `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
 ADD PRIMARY KEY (`idResearchGroup`), ADD KEY `fk_Agent_has_Expiriment_Agent1_idx` (`Agent_idAgent_chef`);

--
-- Indexes for table `roler`
--
ALTER TABLE `roler`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role_coordination_service`
--
ALTER TABLE `role_coordination_service`
 ADD PRIMARY KEY (`coordination_service_id`,`role_id`), ADD KEY `role_id_fka_idx` (`role_id`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
 ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indexes for table `SGWfC`
--
ALTER TABLE `SGWfC`
 ADD PRIMARY KEY (`idSGWfC`);

--
-- Indexes for table `steps_scientific_experimentation`
--
ALTER TABLE `steps_scientific_experimentation`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `steps_service`
--
ALTER TABLE `steps_service`
 ADD PRIMARY KEY (`collab_service_id`,`step_id`), ADD KEY `collab_service_fk_idx` (`collab_service_id`), ADD KEY `step_id_fk` (`step_id`);

--
-- Indexes for table `Task`
--
ALTER TABLE `Task`
 ADD PRIMARY KEY (`idTask`);

--
-- Indexes for table `taverna_workflow`
--
ALTER TABLE `taverna_workflow`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_Agent1_idx` (`idAgent`), ADD KEY `fk_taverna_workflow_Experiment1_idx` (`experiment_id`);

--
-- Indexes for table `taverna_workflow_input`
--
ALTER TABLE `taverna_workflow_input`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_input_taverna_workflow1_idx` (`taverna_workflow_id`);

--
-- Indexes for table `taverna_workflow_run`
--
ALTER TABLE `taverna_workflow_run`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_run_taverna_workflow1_idx` (`taverna_workflow_id`);

--
-- Indexes for table `taverna_workflow_run_input_value`
--
ALTER TABLE `taverna_workflow_run_input_value`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_run_input_value_taverna_workflow_input1_idx` (`taverna_workflow_input_id`);

--
-- Indexes for table `Used`
--
ALTER TABLE `Used`
 ADD PRIMARY KEY (`idUsed`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_Used_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Indexes for table `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
 ADD PRIMARY KEY (`idWasAssociatedWith`), ADD KEY `fk_Workflow_has_Expiriment_Expiriment1_idx` (`Experiment_Experiment`), ADD KEY `fk_Workflow_has_Expiriment_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Indexes for table `WasControledBy`
--
ALTER TABLE `WasControledBy`
 ADD PRIMARY KEY (`idWasControledBy`), ADD KEY `fk_WasControledBy_Activity1_idx` (`Activity_idActivity`), ADD KEY `fk_WasControledBy_Agent1_idx` (`Agent_idAgent`);

--
-- Indexes for table `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
 ADD PRIMARY KEY (`idWasDerivedFrom`), ADD KEY `fk_WasDerivedFrom_Workflow1_idx` (`DerivedOf`), ADD KEY `fk_WasDerivedFrom_Workflow2_idx` (`DerivedTo`);

--
-- Indexes for table `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
 ADD PRIMARY KEY (`idWasEndedBy`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasEndedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Indexes for table `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
 ADD PRIMARY KEY (`idWasEndedByWT`), ADD KEY `fk_Workflow_has_Task_Task2_idx` (`Task_idTask`), ADD KEY `fk_Workflow_has_Task_Workflow2_idx` (`Workflow_idWorkflow`);

--
-- Indexes for table `WasGeneratedBy`
--
ALTER TABLE `WasGeneratedBy`
 ADD PRIMARY KEY (`idWasGeneratedBy`), ADD KEY `fk_Experiment_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`), ADD KEY `fk_Experiment_has_ResearchGroup_Experiment1_idx` (`Experiment_Experiment`);

--
-- Indexes for table `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
 ADD PRIMARY KEY (`idWasInformedBy`), ADD KEY `fk_WasInformedBy_Port_Entity_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasInformedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Indexes for table `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
 ADD PRIMARY KEY (`idWasRevisionOf`), ADD KEY `fk_WasDerivedFrom_Workflow1_idx` (`RevisionOf`), ADD KEY `fk_WasDerivedFrom_Workflow2_idx` (`RevisionTo`);

--
-- Indexes for table `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
 ADD PRIMARY KEY (`idWasStartedBy`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasStartedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Indexes for table `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
 ADD PRIMARY KEY (`idWasStartedByWT`), ADD KEY `fk_Workflow_has_Task_Task1_idx` (`Task_idTask`), ADD KEY `fk_Workflow_has_Task_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Indexes for table `Workflow`
--
ALTER TABLE `Workflow`
 ADD PRIMARY KEY (`idWorkflow`), ADD KEY `fk_Workflow_SGWfC1_idx` (`SGWfC_idSGWfC`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
MODIFY `idActedOnBehalfOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT for table `Activity`
--
ALTER TABLE `Activity`
MODIFY `idActivity` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Agent`
--
ALTER TABLE `Agent`
MODIFY `idAgent` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `collaboration_service`
--
ALTER TABLE `collaboration_service`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `collaborative_service_type`
--
ALTER TABLE `collaborative_service_type`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `communication_service`
--
ALTER TABLE `communication_service`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `competence`
--
ALTER TABLE `competence`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `concept_xml`
--
ALTER TABLE `concept_xml`
MODIFY `id_concept_xml` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=79;
--
-- AUTO_INCREMENT for table `cooperation_service`
--
ALTER TABLE `cooperation_service`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `coordination_service`
--
ALTER TABLE `coordination_service`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `Entity`
--
ALTER TABLE `Entity`
MODIFY `idEntity` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Experiment`
--
ALTER TABLE `Experiment`
MODIFY `idExperiment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `experiment_services`
--
ALTER TABLE `experiment_services`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=152;
--
-- AUTO_INCREMENT for table `group_service`
--
ALTER TABLE `group_service`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `InputPort`
--
ALTER TABLE `InputPort`
MODIFY `idPort` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=276;
--
-- AUTO_INCREMENT for table `interoperability_struct_xml`
--
ALTER TABLE `interoperability_struct_xml`
MODIFY `id_struct_xml` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `IsPartOf`
--
ALTER TABLE `IsPartOf`
MODIFY `idIsPartOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `OutputPort`
--
ALTER TABLE `OutputPort`
MODIFY `idPort` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=145;
--
-- AUTO_INCREMENT for table `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
MODIFY `idResearchGroup` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `roler`
--
ALTER TABLE `roler`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `steps_scientific_experimentation`
--
ALTER TABLE `steps_scientific_experimentation`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=352;
--
-- AUTO_INCREMENT for table `Task`
--
ALTER TABLE `Task`
MODIFY `idTask` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `taverna_workflow`
--
ALTER TABLE `taverna_workflow`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `taverna_workflow_run`
--
ALTER TABLE `taverna_workflow_run`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Used`
--
ALTER TABLE `Used`
MODIFY `idUsed` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=210;
--
-- AUTO_INCREMENT for table `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
MODIFY `idWasAssociatedWith` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `WasControledBy`
--
ALTER TABLE `WasControledBy`
MODIFY `idWasControledBy` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
MODIFY `idWasDerivedFrom` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
MODIFY `idWasEndedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT for table `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
MODIFY `idWasEndedByWT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `WasGeneratedBy`
--
ALTER TABLE `WasGeneratedBy`
MODIFY `idWasGeneratedBy` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
MODIFY `idWasInformedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=71;
--
-- AUTO_INCREMENT for table `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
MODIFY `idWasRevisionOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
MODIFY `idWasStartedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
MODIFY `idWasStartedByWT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Workflow`
--
ALTER TABLE `Workflow`
MODIFY `idWorkflow` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
ADD CONSTRAINT `fk_InputPort_has_OutputPort_InputPort1` FOREIGN KEY (`InputPort_idPort`) REFERENCES `InputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_InputPort_has_OutputPort_OutputPort1` FOREIGN KEY (`OutputPort_idPort`) REFERENCES `OutputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `Activity`
--
ALTER TABLE `Activity`
ADD CONSTRAINT `fk_Activity_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `Agent`
--
ALTER TABLE `Agent`
ADD CONSTRAINT `fk_Agent_Entity1` FOREIGN KEY (`Institution`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `collaboration_service`
--
ALTER TABLE `collaboration_service`
ADD CONSTRAINT `collaboration_service_type_id_fka` FOREIGN KEY (`collaborative_service_type_id`) REFERENCES `collaborative_service_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `communication_service_id_fka` FOREIGN KEY (`communication_service_id`) REFERENCES `communication_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `cooperation_service_id_fka` FOREIGN KEY (`cooperation_service_id`) REFERENCES `cooperation_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `coordination_service_id_fka` FOREIGN KEY (`coordination_service_id`) REFERENCES `coordination_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `group_service_id_fka` FOREIGN KEY (`group_service_id`) REFERENCES `group_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `competence_group_service`
--
ALTER TABLE `competence_group_service`
ADD CONSTRAINT `competence_id_fka` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `group_service_id_fkc` FOREIGN KEY (`group_service_id`) REFERENCES `group_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `concept_xml`
--
ALTER TABLE `concept_xml`
ADD CONSTRAINT `id_struct_xml_fka` FOREIGN KEY (`id_struct_xml`) REFERENCES `interoperability_struct_xml` (`id_struct_xml`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `Experiment`
--
ALTER TABLE `Experiment`
ADD CONSTRAINT `fk_Experiment_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Expiriment_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Expiriment_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `experiment_services`
--
ALTER TABLE `experiment_services`
ADD CONSTRAINT `fk_experiment_services_Experiment1` FOREIGN KEY (`idExperiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `InputPort`
--
ALTER TABLE `InputPort`
ADD CONSTRAINT `fk_Port_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `IsPartOf`
--
ALTER TABLE `IsPartOf`
ADD CONSTRAINT `fk_Agent_has_ResearchGroup_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Agent_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `OutputPort`
--
ALTER TABLE `OutputPort`
ADD CONSTRAINT `fk_Port_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
ADD CONSTRAINT `fk_Agent_has_Expiriment_Agent1` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `role_coordination_service`
--
ALTER TABLE `role_coordination_service`
ADD CONSTRAINT `coordination_service_id_fkb` FOREIGN KEY (`coordination_service_id`) REFERENCES `coordination_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `role_id_fka` FOREIGN KEY (`role_id`) REFERENCES `roler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `steps_service`
--
ALTER TABLE `steps_service`
ADD CONSTRAINT `collab_service_fk` FOREIGN KEY (`collab_service_id`) REFERENCES `collaboration_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `step_id_fk` FOREIGN KEY (`step_id`) REFERENCES `steps_scientific_experimentation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taverna_workflow`
--
ALTER TABLE `taverna_workflow`
ADD CONSTRAINT `fk_taverna_workflow_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_taverna_workflow_Experiment1` FOREIGN KEY (`experiment_id`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taverna_workflow_input`
--
ALTER TABLE `taverna_workflow_input`
ADD CONSTRAINT `fk_taverna_workflow_input_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taverna_workflow_run`
--
ALTER TABLE `taverna_workflow_run`
ADD CONSTRAINT `fk_taverna_workflow_run_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taverna_workflow_run_input_value`
--
ALTER TABLE `taverna_workflow_run_input_value`
ADD CONSTRAINT `fk_taverna_workflow_run_input_value_taverna_workflow_input1` FOREIGN KEY (`taverna_workflow_input_id`) REFERENCES `taverna_workflow_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `Used`
--
ALTER TABLE `Used`
ADD CONSTRAINT `fk_Used_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task100` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
ADD CONSTRAINT `fk_Workflow_has_Expiriment_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Expiriment_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasControledBy`
--
ALTER TABLE `WasControledBy`
ADD CONSTRAINT `fk_WasControledBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasControledBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow1` FOREIGN KEY (`DerivedOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow2` FOREIGN KEY (`DerivedTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
ADD CONSTRAINT `fk_WasEndedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
ADD CONSTRAINT `fk_Workflow_has_Task_Task2` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Task_Workflow2` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasGeneratedBy`
--
ALTER TABLE `WasGeneratedBy`
ADD CONSTRAINT `fk_Experiment_has_ResearchGroup_Experiment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Experiment_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
ADD CONSTRAINT `fk_WasInformedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasInformedBy_Port_Entity_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow10` FOREIGN KEY (`RevisionOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow20` FOREIGN KEY (`RevisionTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
ADD CONSTRAINT `fk_WasStartedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
ADD CONSTRAINT `fk_Workflow_has_Task_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Task_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `Workflow`
--
ALTER TABLE `Workflow`
ADD CONSTRAINT `fk_Workflow_SGWfC1` FOREIGN KEY (`SGWfC_idSGWfC`) REFERENCES `SGWfC` (`idSGWfC`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
