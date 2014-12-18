-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 18/12/2014 às 12:20
-- Versão do servidor: 5.5.40-0ubuntu0.14.04.1
-- Versão do PHP: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `PL-Science_ProVersion`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `ActedOnBeHalfOf_Task`
--

CREATE TABLE IF NOT EXISTS `ActedOnBeHalfOf_Task` (
  `idActedOnBeHalfOf` int(11) NOT NULL AUTO_INCREMENT,
  `Task_father` int(11) NOT NULL,
  `Task_children` int(11) NOT NULL,
  PRIMARY KEY (`idActedOnBeHalfOf`),
  KEY `fk_ActedOnBeHalfOf_Task1_idx` (`Task_father`),
  KEY `fk_ActedOnBeHalfOf_Task2_idx` (`Task_children`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Activity`
--

CREATE TABLE IF NOT EXISTS `Activity` (
  `idActivity` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) NOT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Function` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idActivity`),
  KEY `fk_Activity_Entity1_idx` (`Entity_idEntity`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=6 ;

--
-- Fazendo dump de dados para tabela `Activity`
--

INSERT INTO `Activity` (`idActivity`, `Entity_idEntity`, `Name`, `Function`, `Description`) VALUES
(1, 1, 'Fórmula de Bhaskara', 'Resolver equações quadráticas', 'fórmula geral ax2+bx+c=0, com coeficientes reais, com a≠0'),
(2, 2, 'Teste', 'Func', 'Desc'),
(3, 3, 'Teste 2', 'Fun', 'Des');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Agent`
--

CREATE TABLE IF NOT EXISTS `Agent` (
  `idAgent` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Email` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Password` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Institution` int(11) NOT NULL,
  `Function` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idAgent`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `fk_Agent_Entity1_idx` (`Institution`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Fazendo dump de dados para tabela `Agent`
--

INSERT INTO `Agent` (`idAgent`, `Login`, `Email`, `Password`, `Name`, `Institution`, `Function`, `Description`) VALUES
(1, 'tassiofms', 'tassio@tassio.eti.br', 'xxxx', 'Tassio Ferenzini Martins Sirqueira', 1, 'Pesquisador', 'f'),
(2, 'humbertodalpra', 'humbertodalpra@gmail.com ', 'ccccc', 'Humberto Dalpra', 1, 'Pesquisador', 'f');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Agent_has_ResearchGroup`
--

CREATE TABLE IF NOT EXISTS `Agent_has_ResearchGroup` (
  `idAgent_has_ResearchGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAgent_has_ResearchGroup`),
  KEY `fk_Agent_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`),
  KEY `fk_Agent_has_ResearchGroup_Agent1_idx` (`Agent_idAgent`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=6 ;

--
-- Fazendo dump de dados para tabela `Agent_has_ResearchGroup`
--

INSERT INTO `Agent_has_ResearchGroup` (`idAgent_has_ResearchGroup`, `Agent_idAgent`, `ResearchGroup_idResearchGroup`) VALUES
(1, 1, 2),
(2, 2, 2),
(3, 2, 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Entity`
--

CREATE TABLE IF NOT EXISTS `Entity` (
  `idEntity` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Acronym` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idEntity`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=7 ;

--
-- Fazendo dump de dados para tabela `Entity`
--

INSERT INTO `Entity` (`idEntity`, `Name`, `Acronym`) VALUES
(1, 'Universidade Federal de Juiz de Fora', 'UFJF'),
(2, 'Centro de Ensino Superior de Juiz de Fora', 'CESJF'),
(3, 'Instituto Federal de Educação', 'IFET');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Experiment`
--

CREATE TABLE IF NOT EXISTS `Experiment` (
  `Experiment` int(11) NOT NULL AUTO_INCREMENT,
  `Entity_idEntity` int(11) DEFAULT NULL,
  `Activity_idActivity` int(11) DEFAULT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `DateStarted` date DEFAULT NULL,
  `DateEnded` date DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`Experiment`),
  KEY `fk_Expiriment_Entity1_idx` (`Entity_idEntity`),
  KEY `fk_Expiriment_Activity1_idx` (`Activity_idActivity`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=4 ;

--
-- Fazendo dump de dados para tabela `Experiment`
--

INSERT INTO `Experiment` (`Experiment`, `Entity_idEntity`, `Activity_idActivity`, `Name`, `DateStarted`, `DateEnded`, `Description`, `Version`) VALUES
(1, 1, 1, 'Operação Matemática', '2014-12-16', NULL, 'Equação do segundo grau', '1.0'),
(2, 3, 2, 'Teste', '2014-12-17', NULL, 'Desc', '01.00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ResearchGroup`
--

CREATE TABLE IF NOT EXISTS `ResearchGroup` (
  `idResearchGroup` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  PRIMARY KEY (`idResearchGroup`),
  KEY `fk_Agent_has_Expiriment_Agent1_idx` (`Agent_idAgent_chef`),
  KEY `fk_ResearchGroup_Expiriment1_idx` (`Experiment_Experiment`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=7 ;

--
-- Fazendo dump de dados para tabela `ResearchGroup`
--

INSERT INTO `ResearchGroup` (`idResearchGroup`, `Name`, `Description`, `Agent_idAgent_chef`, `Experiment_Experiment`) VALUES
(2, 'NENC', 'PGCC UFJF', 1, 1),
(3, 'Forever', 'UFJF', 2, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Task`
--

CREATE TABLE IF NOT EXISTS `Task` (
  `idTask` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Type` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Started` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Ended` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idTask`),
  KEY `fk_Task_Workflow2_idx` (`Workflow_idWorkflow`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Fazendo dump de dados para tabela `Task`
--

INSERT INTO `Task` (`idTask`, `Name`, `Type`, `Description`, `Workflow_idWorkflow`, `Started`, `Ended`) VALUES
(1, 'Delta', 'Matemática', 'Calcular o Delta', 1, '2014-12-16 13:34:13', '2014-12-16 13:34:59');

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasControledBy_Agent_Activity`
--

CREATE TABLE IF NOT EXISTS `WasControledBy_Agent_Activity` (
  `idWasControledBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  PRIMARY KEY (`idWasControledBy`),
  KEY `fk_WasControledBy_Activity1_idx` (`Activity_idActivity`),
  KEY `fk_WasControledBy_Agent1_idx` (`Agent_idAgent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasDerivedFrom`
--

CREATE TABLE IF NOT EXISTS `WasDerivedFrom` (
  `idWasDerivedFrom` int(11) NOT NULL AUTO_INCREMENT,
  `DerivedOf` int(11) NOT NULL,
  `DerivedTo` int(11) NOT NULL,
  `Type` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`idWasDerivedFrom`),
  KEY `fk_WasDerivedFrom_Workflow1_idx` (`DerivedOf`),
  KEY `fk_WasDerivedFrom_Workflow2_idx` (`DerivedTo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasInformedBy`
--

CREATE TABLE IF NOT EXISTS `WasInformedBy` (
  `idWasInformedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Entity_idEntity` int(11) NOT NULL,
  PRIMARY KEY (`idWasInformedBy`),
  KEY `fk_WasInformedBy_Port_Entity_Task1_idx` (`Task_idTask`),
  KEY `fk_WasInformedBy_Port_Entity_Entity1_idx` (`Entity_idEntity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasStartedBy_Task_Activity`
--

CREATE TABLE IF NOT EXISTS `WasStartedBy_Task_Activity` (
  `idWasStartedBy` int(11) NOT NULL AUTO_INCREMENT,
  `Task_idTask` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWasStartedBy`),
  KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`),
  KEY `fk_WasStartedBy_Agent1_idx` (`Agent_idAgent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Workflow`
--

CREATE TABLE IF NOT EXISTS `Workflow` (
  `idWorkflow` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `DateVersion` date DEFAULT NULL,
  `SGWfC` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idWorkflow`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=6 ;

--
-- Fazendo dump de dados para tabela `Workflow`
--

INSERT INTO `Workflow` (`idWorkflow`, `Name`, `Description`, `Version`, `DateVersion`, `SGWfC`) VALUES
(1, 'Equação do 2º grau', 'Efetuar equação do segundo grau', '1.0', '2014-12-16', 'Kepler'),
(2, 'Teste', 'Teste desc', '2.0', '2014-12-17', 'Kepler'),
(3, 'Teste 2', 'Descrição', '01.01.00', '2014-12-18', 'Taverna');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Workflow_has_Experiment`
--

CREATE TABLE IF NOT EXISTS `Workflow_has_Experiment` (
  `idWorkflow_has_Experiment` int(11) NOT NULL AUTO_INCREMENT,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWorkflow_has_Experiment`),
  KEY `fk_Workflow_has_Expiriment_Expiriment1_idx` (`Experiment_Experiment`),
  KEY `fk_Workflow_has_Expiriment_Workflow1_idx` (`Workflow_idWorkflow`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=4 ;

--
-- Fazendo dump de dados para tabela `Workflow_has_Experiment`
--

INSERT INTO `Workflow_has_Experiment` (`idWorkflow_has_Experiment`, `Workflow_idWorkflow`, `Experiment_Experiment`) VALUES
(1, 1, 1),
(2, 1, 2);

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `ActedOnBeHalfOf_Task`
--
ALTER TABLE `ActedOnBeHalfOf_Task`
  ADD CONSTRAINT `fk_ActedOnBeHalfOf_Task1` FOREIGN KEY (`Task_father`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ActedOnBeHalfOf_Task2` FOREIGN KEY (`Task_children`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Activity`
--
ALTER TABLE `Activity`
  ADD CONSTRAINT `fk_Activity_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Agent`
--
ALTER TABLE `Agent`
  ADD CONSTRAINT `fk_Agent_Entity1` FOREIGN KEY (`Institution`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Agent_has_ResearchGroup`
--
ALTER TABLE `Agent_has_ResearchGroup`
  ADD CONSTRAINT `fk_Agent_has_ResearchGroup_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Agent_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Experiment`
--
ALTER TABLE `Experiment`
  ADD CONSTRAINT `fk_Expiriment_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Expiriment_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
  ADD CONSTRAINT `fk_Agent_has_Expiriment_Agent1` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ResearchGroup_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`Experiment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Task`
--
ALTER TABLE `Task`
  ADD CONSTRAINT `fk_Task_Workflow2` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasControledBy_Agent_Activity`
--
ALTER TABLE `WasControledBy_Agent_Activity`
  ADD CONSTRAINT `fk_WasControledBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_WasControledBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
  ADD CONSTRAINT `fk_WasDerivedFrom_Workflow1` FOREIGN KEY (`DerivedOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_WasDerivedFrom_Workflow2` FOREIGN KEY (`DerivedTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
  ADD CONSTRAINT `fk_WasInformedBy_Port_Entity_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_WasInformedBy_Port_Entity_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasStartedBy_Task_Activity`
--
ALTER TABLE `WasStartedBy_Task_Activity`
  ADD CONSTRAINT `fk_WasStartedBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_WasStartedBy_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Workflow_has_Experiment`
--
ALTER TABLE `Workflow_has_Experiment`
  ADD CONSTRAINT `fk_Workflow_has_Expiriment_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`Experiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Workflow_has_Expiriment_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
