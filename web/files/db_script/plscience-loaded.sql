-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 17-Dez-2015 às 21:23
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

--
-- Extraindo dados da tabela `ActedOnBehalfOf`
--

INSERT INTO `ActedOnBehalfOf` (`idActedOnBehalfOf`, `InputPort_idPort`, `OutputPort_idPort`, `Description`, `Wf`) VALUES
(1, 8, 3, 'Task 1 acted on behalf of task1', NULL),
(2, 14, 9, 'Task 1 acted on behalf of task1', NULL),
(3, 19, 11, 'Task 1 acted on behalf of task5', NULL),
(4, 22, 13, 'Task 1 acted on behalf of task5', NULL),
(5, 27, 16, 'Task 1 acted on behalf of task5', NULL),
(6, 30, 18, 'Task 1 acted on behalf of task5', NULL),
(7, 35, 21, 'Task Sum acted on behalf of taskMultiplication', NULL),
(8, 38, 23, 'Task Sum acted on behalf of taskMultiplication', NULL),
(9, 42, 26, 'Task Sum acted on behalf of task Multiplication', NULL),
(10, 46, 28, 'Task Sum acted on behalf of task Multiplication', NULL),
(11, 50, 31, 'Task Sum acted on behalf of task Multiplication', NULL),
(12, 54, 33, 'Task Sum acted on behalf of task Multiplication', NULL),
(13, 58, 36, 'Task Sum acted on behalf of task Multiplication', NULL),
(14, 61, 38, 'Task Sum acted on behalf of task Multiplication', NULL),
(15, 66, 41, 'Task Sum acted on behalf of task Multiplication', NULL),
(16, 69, 43, 'Task Sum acted on behalf of task Multiplication', NULL),
(17, 74, 46, 'Task Sum acted on behalf of task Multiplication', NULL),
(18, 77, 48, 'Task Sum acted on behalf of task Multiplication', NULL),
(19, 82, 51, 'Task Sum acted on behalf of task Multiplication', NULL),
(20, 85, 53, 'Task Sum acted on behalf of task Multiplication', NULL),
(21, 91, 56, 'Task Sum acted on behalf of task Multiplication', NULL),
(22, 94, 58, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(23, 99, 61, 'Task Sum acted on behalf of task Multiplication', NULL),
(24, 104, 63, 'Task Sum acted on behalf of task Multiplication', NULL),
(25, 109, 65, 'Task Sum acted on behalf of task Multiplication', NULL),
(26, 114, 67, 'Task Sum acted on behalf of task Multiplication', NULL),
(27, 119, 69, 'Task Sum acted on behalf of task Multiplication', NULL),
(28, 124, 71, 'Task Sum acted on behalf of task Multiplication', NULL),
(29, 133, 73, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(30, 138, 75, 'Task Sum acted on behalf of task Multiplication', NULL),
(31, 143, 77, 'Task Sum acted on behalf of task Multiplication', NULL),
(32, 148, 79, 'Task Sum acted on behalf of task Multiplication', NULL),
(33, 153, 81, 'Task Sum acted on behalf of task Multiplication', NULL),
(34, 156, 83, 'Task Sum acted on behalf of task Multiplication', NULL),
(35, 161, 86, 'Task Sum acted on behalf of task Multiplication', NULL),
(36, 166, 88, 'Task Sum acted on behalf of task Multiplication', NULL),
(37, 171, 90, 'Task Sum acted on behalf of task Multiplication', NULL),
(38, 174, 92, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(39, 181, 95, 'Task Sum acted on behalf of task Multiplication', NULL),
(40, 184, 97, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(41, 196, 100, 'Task Sum acted on behalf of task Multiplication', NULL),
(42, 199, 102, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(43, 207, 105, 'Task Sum acted on behalf of task Multiplication', NULL),
(44, 210, 107, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(45, 216, 110, 'Task Sum acted on behalf of task Multiplication', NULL),
(46, 219, 112, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(47, 231, 115, 'Task Sum acted on behalf of task Multiplication', NULL),
(48, 234, 117, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(49, 239, 120, 'Task Sum acted on behalf of task Multiplication', NULL),
(50, 242, 122, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(51, 247, 125, 'Task Sum acted on behalf of task Multiplication', NULL),
(52, 250, 127, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(53, 255, 130, 'Task Sum acted on behalf of task Multiplication', NULL),
(54, 258, 132, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(55, 263, 135, 'Task Sum acted on behalf of task Multiplication', NULL),
(56, 266, 137, 'Task Subtraction acted on behalf of task Multiplication', NULL),
(57, 271, 140, 'Task Sum acted on behalf of task Multiplication', NULL),
(58, 274, 142, 'Task Subtraction acted on behalf of task Multiplication', NULL);

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

--
-- Extraindo dados da tabela `Activity`
--

INSERT INTO `Activity` (`idActivity`, `Entity_idEntity`, `Name`, `Function`, `Description`) VALUES
(1, 1, 'Calculus', 'Return the value of the executed calculation', 'Given a set of input values, the workflow should return the results of operations'),
(3, 1, 'Calculo', 'Executar calculos matemáticos', 'Demonstracao da ferramenta');

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

--
-- Extraindo dados da tabela `Agent`
--

INSERT INTO `Agent` (`idAgent`, `Login`, `Email`, `Password`, `Name`, `Institution`, `Function`, `Description`) VALUES
(1, 'tassio', 'tassio.sirqueira@ice.ufjf.br', 'f10354719639a6e97c1ccc7ed0c5f2d3', 'Tassio Ferenzini M. Sirqueira', 1, 'Pesquisador', 'Aluno de Pós-graduação'),
(2, 'regina', 'regina@acessa.com', '221182760f5b980c97c7a74a94d57364', 'Regina Braga', 1, 'Pesquisadora', 'Professora de Pós-graduação'),
(3, 'humberto', 'humbertodalpra@gmail.com', '8767bbc52e71900d1f3a50b53196d0e2', 'Humberto Dalpra', 1, 'Pesquisador', 'Aluno de Pós-Graduação'),
(4, 'marco', 'maraujo@acessa.com', 'f5888d0bb58d611107e11f7cbc41c97a', 'Marco Antônio Pereira Araújo', 1, 'Pesquisador', 'Professor de Pós-graduação');

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

--
-- Extraindo dados da tabela `collaboration_service`
--

INSERT INTO `collaboration_service` (`id`, `group_service_id`, `coordination_service_id`, `cooperation_service_id`, `communication_service_id`, `collaborative_service_type_id`, `collab_service_name`, `description`, `developed`) VALUES
(1, 1, 1, 1, 1, 1, 'User List UFJF', 'User List UFJF members.', 0),
(2, 2, 2, 2, 2, 1, 'User List USP', 'User List USP.', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `collaborative_service_type`
--

CREATE TABLE IF NOT EXISTS `collaborative_service_type` (
`id` bigint(20) NOT NULL,
  `name_service_type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `collaborative_service_type`
--

INSERT INTO `collaborative_service_type` (`id`, `name_service_type`, `description`) VALUES
(1, 'User List', 'Service to control a users list of a group (or institution).'),
(2, 'Workflow Prototyping', 'Service for prototyping of a scientific workflow.');

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

--
-- Extraindo dados da tabela `communication_service`
--

INSERT INTO `communication_service` (`id`, `message`, `issuer`, `receiver`, `communicationProtocol`, `commonSense`, `synchronism`, `transmissionMode`, `compromise`, `negotiation`, `code`, `mode`, `interpretation`) VALUES
(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `competence`
--

CREATE TABLE IF NOT EXISTS `competence` (
`id` bigint(20) NOT NULL,
  `competence_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `competence`
--

INSERT INTO `competence` (`id`, `competence_name`, `description`) VALUES
(1, 'JAVA Developer', 'It has the competence to desemvolver in JAVA language.'),
(2, 'C Developer', 'It has the competence to desemvolver in C language.'),
(3, 'Developer', 'It has the competence to develop in any programming language.'),
(4, 'Programmer', 'It has the competence to develop in any programming language.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `competence_group_service`
--

CREATE TABLE IF NOT EXISTS `competence_group_service` (
  `group_service_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `competence_group_service`
--

INSERT INTO `competence_group_service` (`group_service_id`, `competence_id`) VALUES
(1, 1),
(2, 2),
(1, 3),
(2, 4);

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

--
-- Extraindo dados da tabela `concept_xml`
--

INSERT INTO `concept_xml` (`id_concept_xml`, `service`, `concept_service`, `ratio`, `has_concept`, `validity`, `conceptService1`, `conceptService2`, `id_struct_xml`) VALUES
(1, 'Group Service', 'Group', NULL, 0, 0, NULL, NULL, 1),
(2, 'Group Service', 'Participant', NULL, 0, 0, NULL, NULL, 1),
(3, 'Coordination Service', 'Role', 100, 1, 0, 'Manager', 'Manager', 1),
(4, 'Coordination Service', 'Role', 76.32, 1, 0, 'Scientist', 'Researcher', 1),
(5, 'Coordination Service', 'Status', NULL, 0, 0, NULL, NULL, 1),
(6, 'Group Service', 'Group', NULL, 0, 1, NULL, NULL, 2),
(7, 'Group Service', 'Participant', NULL, 0, 0, NULL, NULL, 2),
(8, 'Coordination Service', 'Role', 100, 1, 0, 'Manager', 'Manager', 2),
(9, 'Coordination Service', 'Role', 76.32, 1, 0, 'Scientist', 'Researcher', 2),
(10, 'Coordination Service', 'Status', NULL, 0, 0, NULL, NULL, 2),
(11, 'Group Service', 'Group', NULL, 0, 0, NULL, NULL, 3),
(12, 'Group Service', 'Participant', NULL, 0, 0, NULL, NULL, 3),
(13, 'Coordination Service', 'Role', 100, 1, 0, 'Manager', 'Manager', 3),
(14, 'Coordination Service', 'Role', 76.32, 1, 0, 'Scientist', 'Researcher', 3),
(15, 'Coordination Service', 'Status', NULL, 0, 0, NULL, NULL, 3),
(16, 'Group Service', 'Group', NULL, 0, 1, NULL, NULL, 4),
(17, 'Group Service', 'Participant', NULL, 0, 0, NULL, NULL, 4),
(18, 'Coordination Service', 'Role', 100, 1, 0, 'Manager', 'Manager', 4),
(19, 'Coordination Service', 'Role', 76.32, 1, 0, 'Scientist', 'Researcher', 4),
(20, 'Coordination Service', 'Status', NULL, 0, 0, NULL, NULL, 4);

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

--
-- Extraindo dados da tabela `cooperation_service`
--

INSERT INTO `cooperation_service` (`id`, `activity`, `task`, `product`, `artifact`, `sharedSpace`, `resource`, `share`) VALUES
(1, 0, 0, 0, 0, 0, 0, 0),
(2, 0, 0, 0, 0, 0, 0, 0);

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

--
-- Extraindo dados da tabela `coordination_service`
--

INSERT INTO `coordination_service` (`id`, `workPlan`, `deadline`, `status`, `role`, `policy`, `monitoring`, `coupling`) VALUES
(1, 0, 0, 1, 1, 0, 0, 0),
(2, 0, 0, 1, 1, 0, 0, 0);

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

--
-- Extraindo dados da tabela `Entity`
--

INSERT INTO `Entity` (`idEntity`, `Name`, `Acronym`, `Description`) VALUES
(1, 'Universidade Federal de Juiz de Fora', 'UFJF', 'Mestrado em Ciência da Computação'),
(2, 'Instituto Federal de Educação, Ciência e Tecnologia do Sudeste de Minas Gerais', 'IF Sudeste MG', 'Bacharelado em Sistemas de Informação');

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

--
-- Extraindo dados da tabela `Experiment`
--

INSERT INTO `Experiment` (`idExperiment`, `Entity_idEntity`, `Activity_idActivity`, `idAgent`, `Name`, `DateStarted`, `DateEnded`, `Description`, `Version`, `number_stages`, `parsifal_review`) VALUES
(1, 1, 1, 1, 'Mathematical operations', '2015-06-18', NULL, 'Given a set of input values, the workflow should return the results of operations', '01.00', NULL, NULL),
(2, 1, 3, 1, 'Experimento matematico', '2015-10-08', '2015-10-10', 'Demonstracao ao NEnC', '01.00', NULL, NULL),
(3, NULL, NULL, NULL, 'Teste', '2015-12-09', '2015-12-30', 'Teste de Criação', '', 2, NULL);

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

--
-- Extraindo dados da tabela `experiment_services`
--

INSERT INTO `experiment_services` (`id`, `service_name`, `stage`, `latestTime_used`, `idExperiment`) VALUES
(51, '', 0, '2015-12-02', 3),
(101, 'teste 2', 1, '2015-12-02', 3),
(151, 'teste 1', 0, '2015-12-02', 3);

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

--
-- Extraindo dados da tabela `group_service`
--

INSERT INTO `group_service` (`id`, `participant`, `belief`, `confidence`, `motivation`, `groupp`, `competence`, `goal`) VALUES
(1, 1, 0, 0, 0, 1, 1, 0),
(2, 1, 0, 0, 0, 1, 1, 0);

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

--
-- Extraindo dados da tabela `InputPort`
--

INSERT INTO `InputPort` (`idPort`, `Name`, `Description`, `Task_idTask`, `Value`, `Wf`) VALUES
(1, 'Starting port workflow', 'Port of task with valeu ', 1, '6', NULL),
(2, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(3, 'Starting port workflow', 'Port of task with valeu ', 1, '8', NULL),
(4, 'Starting port workflow', 'Port of task with valeu ', 1, '8', NULL),
(5, 'Starting port workflow', 'Port of task with valeu ', 1, '3', NULL),
(6, 'Starting port workflow', 'Port of task with valeu ', 1, '7', NULL),
(7, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(8, 'Starting port task1', '1 task input with valueinput 0', 1, '0', NULL),
(9, 'Starting port task1', '1 task input with valueinput 0', 1, '0', NULL),
(10, 'Starting port workflow', 'Port of task with valeu ', 1, '3', NULL),
(11, 'Starting port workflow', 'Port of task with valeu ', 1, '7', NULL),
(12, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(13, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(14, 'Starting port task1', '1 task input with valueinput 12', 1, '12', NULL),
(15, 'Starting port task1', '1 task input with valueinput 12', 1, '12', NULL),
(16, 'Starting port workflow', 'Port of task with valeu ', 1, '3', NULL),
(17, 'Starting port workflow', 'Port of task with valeu ', 1, '7', NULL),
(18, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(19, 'Starting port task5', '5 task input with valueinput 8', 5, '8', NULL),
(20, 'Starting port task5', '5 task input with valueinput 8', 5, '8', NULL),
(21, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(22, 'Starting port task5', '5 task input with valueinput -12', 5, '-12', NULL),
(23, 'Starting port task5', '5 task input with valueinput -12', 5, '-12', NULL),
(24, 'Starting port workflow', 'Port of task with valeu ', 1, '3', NULL),
(25, 'Starting port workflow', 'Port of task with valeu ', 1, '7', NULL),
(26, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(27, 'Starting port task5', '5 task input with valueinput 8', 5, '8', NULL),
(28, 'Starting port task5', '5 task input with valueinput 8', 5, '8', NULL),
(29, 'Starting port workflow', 'Port of task with valeu ', 1, '5', NULL),
(30, 'Starting port task5', '5 task input with valueinput 12', 5, '12', NULL),
(31, 'Starting port task5', '5 task input with valueinput 12', 5, '12', NULL),
(32, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(33, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(34, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(35, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', NULL),
(36, 'Starting port taskMultiplication', 'Task Multiplication input with valueinput 8', 5, '8', NULL),
(37, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(38, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(39, 'Starting port taskMultiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(40, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(41, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(42, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(43, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(44, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(45, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(46, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(47, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(48, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(49, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(50, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(51, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(52, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(53, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(54, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(55, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(56, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(57, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(58, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(59, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(60, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(61, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', NULL),
(62, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', NULL),
(63, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(64, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(65, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(66, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(67, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(68, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(69, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', NULL),
(70, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', NULL),
(71, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(72, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(73, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(74, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(75, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(76, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(77, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(78, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(79, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(80, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(81, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(82, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(83, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(84, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(85, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(86, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(87, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(88, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(89, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(90, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(91, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(92, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(93, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(94, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(95, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(96, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(97, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(98, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(99, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(100, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(101, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(102, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(103, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(104, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(105, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(106, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(107, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(108, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(109, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(110, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(111, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(112, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(113, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(114, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(115, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(116, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(117, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(118, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(119, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(120, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(121, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(122, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(123, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(124, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(125, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(126, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(127, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(128, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(129, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(130, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(131, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(132, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(133, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(134, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(135, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(136, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(137, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(138, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(139, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(140, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(141, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(142, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(143, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(144, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(145, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(146, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(147, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(148, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(149, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(150, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(151, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', NULL),
(152, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(153, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', NULL),
(154, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', NULL),
(155, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', NULL),
(156, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(157, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', NULL),
(158, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(159, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(160, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(161, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(162, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(163, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(164, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(165, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(166, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(167, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(168, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(169, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(170, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(171, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(172, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(173, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(174, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(175, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(176, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(177, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(178, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(179, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(180, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(181, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(182, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(183, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(184, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(185, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(186, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(187, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(188, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(189, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(190, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(191, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(192, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(193, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(194, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(195, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(196, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(197, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(198, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(199, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(200, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(201, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(202, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(203, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(204, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(205, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(206, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(207, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(208, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(209, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(210, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(211, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(212, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(213, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(214, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(215, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(216, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(217, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(218, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(219, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(220, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(221, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(222, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(223, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(224, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(225, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(226, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(227, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(228, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(229, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(230, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(231, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(232, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(233, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(234, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(235, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(236, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(237, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(238, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(239, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(240, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(241, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(242, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(243, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(244, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(245, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(246, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(247, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(248, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(249, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(250, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(251, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(252, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(253, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(254, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(255, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(256, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(257, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(258, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(259, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(260, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(261, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(262, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(263, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(264, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(265, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(266, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(267, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(268, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', NULL),
(269, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', NULL),
(270, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', NULL),
(271, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(272, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', NULL),
(273, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', NULL),
(274, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL),
(275, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', NULL);

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

--
-- Extraindo dados da tabela `interoperability_struct_xml`
--

INSERT INTO `interoperability_struct_xml` (`id_struct_xml`, `interoperability_name`, `first_service_id`, `second_service_id`, `agent_id`, `first_type_service`, `second_type_service`) VALUES
(1, '1-User List UFJF-2-User List USP-100', 1, 2, 100, 'User List', 'User List'),
(2, '1-User List UFJF-2-User List USP-100', 1, 2, 100, 'User List', 'User List'),
(3, '1-User List UFJF-2-User List USP-100', 1, 2, 100, 'User List', 'User List'),
(4, '1-User List UFJF-2-User List USP-100', 1, 2, 100, 'User List', 'User List');

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

--
-- Extraindo dados da tabela `IsPartOf`
--

INSERT INTO `IsPartOf` (`idIsPartOf`, `Agent_idAgent`, `ResearchGroup_idResearchGroup`, `Description`) VALUES
(1, 2, 1, 'Professora de Pós-graduação'),
(2, 1, 1, 'Aluno de Pós-graduação'),
(3, 3, 1, 'Aluno de Pós-graduação'),
(4, 4, 1, 'Professor de Pós-graduação');

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

--
-- Extraindo dados da tabela `OutputPort`
--

INSERT INTO `OutputPort` (`idPort`, `Name`, `Description`, `Task_idTask`, `Value`, `Wf`) VALUES
(1, 'Ended Port Task 1', 'Task output with valueoutput 11', 1, '11', NULL),
(2, 'Ended Port Task 1', 'Task output with valueoutput 16', 1, '16', NULL),
(3, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(4, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(5, 'Ended Port Task 1', 'Task output with valueoutput 15', 1, '15', NULL),
(6, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(7, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(8, 'Ended Port Task 1', 'Task output with valueoutput 20', 1, '20', NULL),
(9, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(10, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(11, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(12, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(13, 'Ended Port Task 1', 'Task output with valueoutput -12', 1, '-12', NULL),
(14, 'Ended Port Task 1', 'Task output with valueoutput -12', 1, '-12', NULL),
(15, 'Ended Port Task 5', 'Task output with valueoutput 96', 5, '96', NULL),
(16, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(17, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', NULL),
(18, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(19, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', NULL),
(20, 'Ended Port Task 5', 'Task output with valueoutput 96', 5, '96', NULL),
(21, 'Ended Port Task Sum', 'Task Sumoutput with valueoutput 8', 1, '8', NULL),
(22, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', NULL),
(23, 'Ended Port Task Sum', 'Task Sumoutput with valueoutput -12', 1, '-12', NULL),
(24, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(25, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 96', 5, '96', NULL),
(26, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(27, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(28, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(29, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(30, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', NULL),
(31, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(32, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(33, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(34, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(35, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', NULL),
(36, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(37, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(38, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', NULL),
(39, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', NULL),
(40, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', NULL),
(41, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(42, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(43, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', NULL),
(44, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', NULL),
(45, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', NULL),
(46, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(47, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(48, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', NULL),
(49, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', NULL),
(50, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(51, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(52, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(53, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', NULL),
(54, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', NULL),
(55, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(56, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(57, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(58, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(59, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(60, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(61, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(62, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(63, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(64, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(65, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(66, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(67, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(68, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(69, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(70, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(71, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(72, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(73, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(74, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(75, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(76, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(77, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(78, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(79, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(80, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(81, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', NULL),
(82, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', NULL),
(83, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(84, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', NULL),
(85, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 96', 5, '96', NULL),
(86, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(87, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(88, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(89, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(90, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(91, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(92, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(93, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(94, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(95, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(96, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(97, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(98, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(99, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(100, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(101, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(102, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(103, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(104, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(105, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(106, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(107, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(108, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(109, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(110, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(111, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(112, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(113, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(114, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(115, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(116, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(117, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(118, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(119, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(120, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(121, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(122, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(123, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(124, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(125, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(126, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(127, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(128, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(129, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(130, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(131, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(132, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(133, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(134, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(135, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(136, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(137, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(138, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(139, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL),
(140, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(141, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', NULL),
(142, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(143, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', NULL),
(144, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', NULL);

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

--
-- Extraindo dados da tabela `ResearchGroup`
--

INSERT INTO `ResearchGroup` (`idResearchGroup`, `Name`, `Description`, `Agent_idAgent_chef`) VALUES
(1, 'NEnC', 'Núcleo de Engenharia do Conhecimento', 2);

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

--
-- Extraindo dados da tabela `roler`
--

INSERT INTO `roler` (`id`, `role_name`, `hierarchy_level`, `description`) VALUES
(1, 'Manager', 1, 'Manager of a scientific experiment.'),
(2, 'Scientist', 5, 'Scientist of a scientific experiment.'),
(3, 'Researcher', 5, 'Researcher of a scientific experiment.'),
(4, 'Supervisor', 3, 'Supervisor of a scientific experiment.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `role_coordination_service`
--

CREATE TABLE IF NOT EXISTS `role_coordination_service` (
  `coordination_service_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `role_coordination_service`
--

INSERT INTO `role_coordination_service` (`coordination_service_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', 400);

-- --------------------------------------------------------

--
-- Estrutura da tabela `SGWfC`
--

CREATE TABLE IF NOT EXISTS `SGWfC` (
  `idSGWfC` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `SGWfC`
--

INSERT INTO `SGWfC` (`idSGWfC`, `Name`, `Description`) VALUES
(1, 'Kepler', 'Kepler é um software livre do sistema para a concepção, execução, reutilizando, evoluindo, arquivamento e compartilhamento científicos'),
(2, 'Taverna Workbench', 'Taverna é um conjunto de ferramentas usadas para criar e executar workflows científicos e ajuda na experimentação in silico');

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

--
-- Extraindo dados da tabela `steps_scientific_experimentation`
--

INSERT INTO `steps_scientific_experimentation` (`id`, `name_step`, `number_step`, `description`) VALUES
(201, 'Problem Investigation', 1, 'Primeira etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),
(251, 'Experiment Prototyping', 2, 'Segunda etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),
(301, 'Experiment Execution', 3, 'Terceira etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).'),
(351, 'Results Publication', 4, 'Quarta etapa do ciclo de vida de um experimento científico, segundo BELLOUM et al., (2011).');

-- --------------------------------------------------------

--
-- Estrutura da tabela `steps_service`
--

CREATE TABLE IF NOT EXISTS `steps_service` (
  `collab_service_id` bigint(20) NOT NULL,
  `step_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `steps_service`
--

INSERT INTO `steps_service` (`collab_service_id`, `step_id`) VALUES
(1, 201),
(2, 201),
(2, 251),
(2, 301),
(2, 351);

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

--
-- Extraindo dados da tabela `Task`
--

INSERT INTO `Task` (`idTask`, `Name`, `Type`, `Description`) VALUES
(1, 'Sum', 'Integer', 'Sum of two values'),
(2, 'Sum', 'Float', 'Sum of two values'),
(3, 'Subtraction', 'Integer', 'Subtraction of two values'),
(4, 'Subtraction', 'Float', 'Subtraction of two values'),
(5, 'Multiplication', 'Integer', 'Multiplication of two values'),
(6, 'Multiplication', 'Float', 'Multiplication of two values'),
(7, 'Division', 'Float', 'Division of two values');

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

--
-- Extraindo dados da tabela `Used`
--

INSERT INTO `Used` (`idUsed`, `Task_idTask`, `Workflow_idWorkflow`, `Description`) VALUES
(1, 1, 1, 'Task was used in workflow'),
(2, 1, 1, 'Task was used in workflow'),
(3, 1, 2, 'Task was used in workflow'),
(4, 1, 2, 'Task was used in workflow'),
(5, 1, 2, 'Task was used in workflow'),
(6, 1, 2, 'Task 1 was used in workflow 2'),
(7, 1, 3, 'Task was used in workflow'),
(8, 1, 3, 'Task was used in workflow'),
(9, 1, 3, 'Task was used in workflow'),
(10, 1, 3, 'Task was used in workflow'),
(11, 1, 3, 'Task 1 was used in workflow 3'),
(12, 1, 4, 'Task was used in workflow'),
(13, 1, 4, 'Task was used in workflow'),
(14, 1, 4, 'Task was used in workflow'),
(15, 5, 4, 'Task 5 was used in workflow 4'),
(16, 1, 4, 'Task was used in workflow'),
(17, 5, 4, 'Task 5 was used in workflow 4'),
(18, 1, 5, 'Task was used in workflow'),
(19, 1, 5, 'Task was used in workflow'),
(20, 1, 5, 'Task was used in workflow'),
(21, 5, 5, 'Task 5 was used in workflow 5'),
(22, 1, 5, 'Task was used in workflow'),
(23, 5, 5, 'Task 5 was used in workflow 5'),
(24, 1, 4, 'TaskSumwas used in workflowSimpleCount'),
(25, 1, 4, 'TaskSumwas used in workflowSimpleCount'),
(26, 1, 4, 'TaskSumwas used in workflowSimpleCount'),
(27, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(28, 1, 4, 'TaskSumwas used in workflowSimpleCount'),
(29, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(30, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(31, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(32, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(33, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(34, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(35, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(36, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(37, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(38, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(39, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(40, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(41, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(42, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(43, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(44, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(45, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(46, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(47, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(48, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(49, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(50, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(51, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(52, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(53, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(54, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(55, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(56, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(57, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(58, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(59, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(60, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(61, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(62, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(63, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(64, 5, 4, 'Task Multiplication was used in workflow SimpleCount'),
(65, 1, 4, 'Task Sum was used in workflow SimpleCount'),
(192, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(193, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(194, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(195, 5, 6, 'Task Multiplication was used in workflow Demonstracao'),
(196, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(197, 5, 6, 'Task Multiplication was used in workflow Demonstracao'),
(198, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(199, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(200, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(201, 5, 6, 'Task Multiplication was used in workflow Demonstracao'),
(202, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(203, 5, 6, 'Task Multiplication was used in workflow Demonstracao'),
(204, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(205, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(206, 1, 6, 'Task Sum was used in workflow Demonstracao'),
(207, 5, 6, 'Task Multiplication was used in workflow Demonstracao'),
(208, 3, 6, 'Task Subtraction was used in workflow Demonstracao'),
(209, 5, 6, 'Task Multiplication was used in workflow Demonstracao');

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

--
-- Extraindo dados da tabela `WasAssociatedWith`
--

INSERT INTO `WasAssociatedWith` (`idWasAssociatedWith`, `Workflow_idWorkflow`, `Experiment_Experiment`, `Description`) VALUES
(1, 1, 1, NULL),
(2, 2, 1, NULL),
(3, 3, 1, NULL),
(4, 4, 1, NULL),
(5, 5, 1, NULL),
(6, 1, 1, 'Workflow was attributed to experimento'),
(7, 1, 1, 'Workflow was attributed to experimento'),
(8, 2, 1, 'Workflow was attributed to experimento'),
(9, 3, 1, 'Workflow was attributed to experimento'),
(10, 4, 1, 'Workflow was attributed to experimento'),
(11, 5, 1, 'Workflow was attributed to experimento'),
(12, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(13, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(14, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(15, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(16, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(17, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(18, 4, 1, 'Workflow SimpleCount was attributed to experimento Mathematical operations'),
(41, 6, 2, 'Workflow Demonstracao was attributed to experimento Experimento matematico'),
(42, 6, 2, 'Workflow Demonstracao was attributed to experimento Experimento matematico'),
(43, 6, 2, 'Workflow Demonstracao was attributed to experimento Experimento matematico');

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

--
-- Extraindo dados da tabela `WasDerivedFrom`
--

INSERT INTO `WasDerivedFrom` (`idWasDerivedFrom`, `DerivedOf`, `DerivedTo`, `Type`) VALUES
(1, 1, 2, 'Evolution'),
(2, 2, 3, 'Evolution');

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

--
-- Extraindo dados da tabela `WasEndedBy`
--

INSERT INTO `WasEndedBy` (`idWasEndedBy`, `Task_idTask`, `Activity_idActivity`, `DateEnded`, `Description`) VALUES
(1, 1, 1, '2015-08-06 21:12:24', 'task 1 ended to activity 1'),
(2, 1, 1, '2015-08-06 21:21:27', 'task 1 ended to activity 1'),
(3, 1, 1, '2015-08-06 21:29:02', 'task 1 ended to activity 1'),
(4, 1, 1, '2015-08-06 21:29:03', 'task 1 ended to activity 1'),
(5, 1, 1, '2015-08-06 21:31:49', 'task 1 ended to activity 1'),
(6, 1, 1, '2015-08-06 21:31:50', 'task 1 ended to activity 1'),
(7, 1, 1, '2015-08-06 21:31:51', 'task 1 ended to activity 1'),
(8, 1, 1, '2015-08-06 21:36:37', 'task 1 ended to activity 1'),
(9, 1, 1, '2015-08-06 21:36:39', 'task 1 ended to activity 1'),
(10, 5, 1, '2015-08-06 21:36:39', 'task 5 ended to activity 1'),
(11, 1, 1, '2015-08-06 21:39:19', 'task 1 ended to activity 1'),
(12, 1, 1, '2015-08-06 21:39:20', 'task 1 ended to activity 1'),
(13, 5, 1, '2015-08-06 21:39:20', 'task 5 ended to activity 1'),
(41, 1, 3, '2015-10-08 16:00:46', 'Task Sum ended to activity Calculo'),
(42, 3, 3, '2015-10-08 16:00:47', 'Task Subtraction ended to activity Calculo'),
(43, 5, 3, '2015-10-08 16:00:47', 'Task Multiplication ended to activity Calculo'),
(44, 1, 3, '2015-10-19 17:06:36', 'Task Sum ended to activity Calculo'),
(45, 3, 3, '2015-10-19 17:06:37', 'Task Subtraction ended to activity Calculo'),
(46, 5, 3, '2015-10-19 17:06:37', 'Task Multiplication ended to activity Calculo'),
(47, 1, 3, '2015-10-19 19:07:43', 'Task Sum ended to activity Calculo'),
(48, 3, 3, '2015-10-19 19:07:47', 'Task Subtraction ended to activity Calculo'),
(49, 5, 3, '2015-10-19 19:07:47', 'Task Multiplication ended to activity Calculo');

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

--
-- Extraindo dados da tabela `WasInformedBy`
--

INSERT INTO `WasInformedBy` (`idWasInformedBy`, `Description`, `Task_idTask`, `Activity_idActivity`) VALUES
(1, 'task 1 was successful for activity 1', 1, 1),
(2, 'task 1 was successful for activity 1', 1, 1),
(3, 'task 1 was successful for activity 1', 1, 1),
(4, 'task 1 was successful for activity 1', 1, 1),
(5, 'task 1 was successful for activity 1', 1, 1),
(6, 'task 1 was successful for activity 1', 1, 1),
(7, 'task 1 was successful for activity 1', 1, 1),
(8, 'task 1 was successful for activity 1', 1, 1),
(9, 'task 1 was successful for activity 1', 1, 1),
(10, 'task 5 was successful for activity 1', 5, 1),
(11, 'task 1 was successful for activity 1', 1, 1),
(12, 'task 1 was successful for activity 1', 1, 1),
(13, 'task 5 was successful for activity 1', 5, 1),
(59, 'Task Sum was successful for activity Calculo', 1, 3),
(60, 'Task Subtraction was successful for activity Calculo', 3, 3),
(61, 'Task Multiplication was successful for activity Calculo', 5, 3),
(62, 'Task Sum was successful for activity Calculo', 1, 3),
(63, 'Task Subtraction was successful for activity Calculo', 3, 3),
(64, 'Task Multiplication was successful for activity Calculo', 5, 3),
(65, 'Task Sum was successful for activity Calculo', 1, 3),
(66, 'Task Subtraction was successful for activity Calculo', 3, 3),
(67, 'Task Multiplication was successful for activity Calculo', 5, 3),
(68, 'Task Sum was successful for activity Calculo', 1, 3),
(69, 'Task Subtraction was successful for activity Calculo', 3, 3),
(70, 'Task Multiplication was successful for activity Calculo', 5, 3);

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

--
-- Extraindo dados da tabela `WasRevisionOf`
--

INSERT INTO `WasRevisionOf` (`idWasRevisionOf`, `RevisionOf`, `RevisionTo`, `Type`) VALUES
(1, 4, 5, 'Corretive');

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

--
-- Extraindo dados da tabela `WasStartedBy`
--

INSERT INTO `WasStartedBy` (`idWasStartedBy`, `Task_idTask`, `Activity_idActivity`, `DateStarted`, `Description`) VALUES
(1, 1, 1, '2015-08-06 21:12:24', 'task 1 started to activity 1'),
(2, 1, 1, '2015-08-06 21:21:26', 'task 1 started to activity 1'),
(3, 1, 1, '2015-08-06 21:29:01', 'task 1 started to activity 1'),
(4, 1, 1, '2015-08-06 21:29:02', 'task 1 started to activity 1'),
(5, 1, 1, '2015-08-06 21:31:48', 'task 1 started to activity 1'),
(6, 1, 1, '2015-08-06 21:31:51', 'task 1 started to activity 1'),
(7, 5, 1, '2015-08-06 21:36:37', 'task 5 started to activity 1'),
(8, 5, 1, '2015-08-06 21:36:39', 'task 5 started to activity 1'),
(9, 1, 1, '2015-08-06 21:39:17', 'task 1 started to activity 1'),
(10, 5, 1, '2015-08-06 21:39:18', 'task 5 started to activity 1'),
(11, 5, 1, '2015-08-06 21:39:20', 'task 5 started to activity 1'),
(32, 5, 3, '2015-10-08 16:00:46', 'Task Multiplication started to activity Calculo'),
(33, 5, 3, '2015-10-08 16:00:47', 'Task Multiplication started to activity Calculo'),
(34, 5, 3, '2015-10-19 17:06:36', 'Task Multiplication started to activity Calculo'),
(35, 5, 3, '2015-10-19 17:06:37', 'Task Multiplication started to activity Calculo'),
(36, 5, 3, '2015-10-19 19:07:42', 'Task Multiplication started to activity Calculo'),
(37, 5, 3, '2015-10-19 19:07:47', 'Task Multiplication started to activity Calculo');

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
-- Extraindo dados da tabela `Workflow`
--

INSERT INTO `Workflow` (`idWorkflow`, `Name`, `Description`, `Version`, `DateVersion`, `NumberStage`, `link`, `SGWfC_idSGWfC`) VALUES
(1, 'SimpleAddition', 'Sum of two values', '01.00.00', '2015-06-18', 1, NULL, 1),
(2, 'SimpleSum', 'Sum of three values', '01.00.00', '2015-06-19', 2, NULL, 1),
(3, 'SimpleSum2', 'Sum of four values', '01.00.00', '2015-06-22', 3, NULL, 1),
(4, 'SimpleCount', 'Calculation values', '01.00.00', '2015-06-24', 3, NULL, 1),
(5, 'SimpleCount2', 'Calculation values', '01.00.00', '2015-06-26', 3, NULL, 1),
(6, 'Demonstracao', 'Apresentacao NenC', '01.00.00', '2015-10-08', 3, NULL, 1);

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
