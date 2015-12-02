-- phpMyAdmin SQL Dump
-- version 4.2.12deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 28/10/2015 às 16:11
-- Versão do servidor: 5.5.44-0+deb8u1
-- Versão do PHP: 5.6.13-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `plscience`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `ActedOnBehalfOf`
--

CREATE TABLE IF NOT EXISTS `ActedOnBehalfOf` (
`idActedOnBehalfOf` int(11) NOT NULL,
  `InputPort_idPort` int(11) NOT NULL,
  `OutputPort_idPort` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Wf` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `ActedOnBehalfOf`
--

INSERT INTO `ActedOnBehalfOf` (`idActedOnBehalfOf`, `InputPort_idPort`, `OutputPort_idPort`, `Description`, `Wf`) VALUES
(1, 8, 3, 'Task 1 acted on behalf of task1', 0),
(2, 14, 9, 'Task 1 acted on behalf of task1', 0),
(3, 19, 11, 'Task 1 acted on behalf of task5', 0),
(4, 22, 13, 'Task 1 acted on behalf of task5', 0),
(5, 27, 16, 'Task 1 acted on behalf of task5', 0),
(6, 30, 18, 'Task 1 acted on behalf of task5', 0),
(7, 35, 21, 'Task Sum acted on behalf of taskMultiplication', 0),
(8, 38, 23, 'Task Sum acted on behalf of taskMultiplication', 0),
(9, 42, 26, 'Task Sum acted on behalf of task Multiplication', 0),
(10, 46, 28, 'Task Sum acted on behalf of task Multiplication', 0),
(11, 50, 31, 'Task Sum acted on behalf of task Multiplication', 0),
(12, 54, 33, 'Task Sum acted on behalf of task Multiplication', 0),
(13, 58, 36, 'Task Sum acted on behalf of task Multiplication', 0),
(14, 61, 38, 'Task Sum acted on behalf of task Multiplication', 0),
(15, 66, 41, 'Task Sum acted on behalf of task Multiplication', 0),
(16, 69, 43, 'Task Sum acted on behalf of task Multiplication', 0),
(17, 74, 46, 'Task Sum acted on behalf of task Multiplication', 0),
(18, 77, 48, 'Task Sum acted on behalf of task Multiplication', 0),
(19, 82, 51, 'Task Sum acted on behalf of task Multiplication', 0),
(20, 85, 53, 'Task Sum acted on behalf of task Multiplication', 0),
(21, 91, 56, 'Task Sum acted on behalf of task Multiplication', 0),
(22, 94, 58, 'Task Subtraction acted on behalf of task Multiplication', 0),
(23, 99, 61, 'Task Sum acted on behalf of task Multiplication', 0),
(24, 104, 63, 'Task Sum acted on behalf of task Multiplication', 0),
(25, 109, 65, 'Task Sum acted on behalf of task Multiplication', 0),
(26, 114, 67, 'Task Sum acted on behalf of task Multiplication', 0),
(27, 119, 69, 'Task Sum acted on behalf of task Multiplication', 0),
(28, 124, 71, 'Task Sum acted on behalf of task Multiplication', 0),
(29, 133, 73, 'Task Subtraction acted on behalf of task Multiplication', 0),
(30, 138, 75, 'Task Sum acted on behalf of task Multiplication', 0),
(31, 143, 77, 'Task Sum acted on behalf of task Multiplication', 0),
(32, 148, 79, 'Task Sum acted on behalf of task Multiplication', 0),
(33, 153, 81, 'Task Sum acted on behalf of task Multiplication', 0),
(34, 156, 83, 'Task Sum acted on behalf of task Multiplication', 0),
(35, 161, 86, 'Task Sum acted on behalf of task Multiplication', 0),
(36, 166, 88, 'Task Sum acted on behalf of task Multiplication', 0),
(37, 171, 90, 'Task Sum acted on behalf of task Multiplication', 0),
(38, 174, 92, 'Task Subtraction acted on behalf of task Multiplication', 0),
(39, 181, 95, 'Task Sum acted on behalf of task Multiplication', 0),
(40, 184, 97, 'Task Subtraction acted on behalf of task Multiplication', 0),
(41, 196, 100, 'Task Sum acted on behalf of task Multiplication', 0),
(42, 199, 102, 'Task Subtraction acted on behalf of task Multiplication', 0),
(43, 207, 105, 'Task Sum acted on behalf of task Multiplication', 0),
(44, 210, 107, 'Task Subtraction acted on behalf of task Multiplication', 0),
(45, 216, 110, 'Task Sum acted on behalf of task Multiplication', 0),
(46, 219, 112, 'Task Subtraction acted on behalf of task Multiplication', 0),
(47, 231, 115, 'Task Sum acted on behalf of task Multiplication', 0),
(48, 234, 117, 'Task Subtraction acted on behalf of task Multiplication', 0),
(49, 239, 120, 'Task Sum acted on behalf of task Multiplication', 0),
(50, 242, 122, 'Task Subtraction acted on behalf of task Multiplication', 0),
(51, 247, 125, 'Task Sum acted on behalf of task Multiplication', 0),
(52, 250, 127, 'Task Subtraction acted on behalf of task Multiplication', 0),
(53, 255, 130, 'Task Sum acted on behalf of task Multiplication', 0),
(54, 258, 132, 'Task Subtraction acted on behalf of task Multiplication', 0),
(55, 263, 135, 'Task Sum acted on behalf of task Multiplication', 0),
(56, 266, 137, 'Task Subtraction acted on behalf of task Multiplication', 0),
(57, 271, 140, 'Task Sum acted on behalf of task Multiplication', 6),
(58, 274, 142, 'Task Subtraction acted on behalf of task Multiplication', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Activity`
--

CREATE TABLE IF NOT EXISTS `Activity` (
`idActivity` int(11) NOT NULL,
  `Entity_idEntity` int(11) NOT NULL,
  `Name` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  `Function` varchar(45) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Activity`
--

INSERT INTO `Activity` (`idActivity`, `Entity_idEntity`, `Name`, `Function`, `Description`) VALUES
(1, 1, 'Calculus', 'Return the value of the executed calculation', 'Given a set of input values, the workflow should return the results of operations'),
(3, 1, 'Calculo', 'Executar calculos matemáticos', 'Demonstracao da ferramenta');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Agent`
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
-- Fazendo dump de dados para tabela `Agent`
--

INSERT INTO `Agent` (`idAgent`, `Login`, `Email`, `Password`, `Name`, `Institution`, `Function`, `Description`) VALUES
(1, 'tassio', 'tassio.sirqueira@ice.ufjf.br', 'f10354719639a6e97c1ccc7ed0c5f2d3', 'Tassio Ferenzini M. Sirqueira', 1, 'Pesquisador', 'Aluno de Pós-graduação'),
(2, 'regina', 'regina@acessa.com', '221182760f5b980c97c7a74a94d57364', 'Regina Braga', 1, 'Pesquisadora', 'Professora de Pós-graduação'),
(3, 'humberto', 'humbertodalpra@gmail.com', '8767bbc52e71900d1f3a50b53196d0e2', 'Humberto Dalpra', 1, 'Pesquisador', 'Aluno de Pós-Graduação'),
(4, 'marco', 'maraujo@acessa.com', 'f5888d0bb58d611107e11f7cbc41c97a', 'Marco Antônio Pereira Araújo', 1, 'Pesquisador', 'Professor de Pós-graduação');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Entity`
--

CREATE TABLE IF NOT EXISTS `Entity` (
`idEntity` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Acronym` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Entity`
--

INSERT INTO `Entity` (`idEntity`, `Name`, `Acronym`, `Description`) VALUES
(1, 'Universidade Federal de Juiz de Fora', 'UFJF', 'Mestrado em Ciência da Computação'),
(2, 'Instituto Federal de Educação, Ciência e Tecnologia do Sudeste de Minas Gerais', 'IF Sudeste MG', 'Bacharelado em Sistemas de Informação');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Experiment`
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
  `parsifal_review` int(11) DEFAULT NULL,
  `experiment_services_id` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Experiment`
--

INSERT INTO `Experiment` (`idExperiment`, `Entity_idEntity`, `Activity_idActivity`, `idAgent`, `Name`, `DateStarted`, `DateEnded`, `Description`, `Version`, `number_stages`, `parsifal_review`, `experiment_services_id`) VALUES
(1, 1, 1, 1, 'Mathematical operations', '2015-06-18', NULL, 'Given a set of input values, the workflow should return the results of operations', '01.00', NULL, NULL, 0),
(2, 1, 3, 1, 'Experimento matematico', '2015-10-08', '2015-10-10', 'Demonstracao ao NEnC', '01.00', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `experiment_services`
--

CREATE TABLE IF NOT EXISTS `experiment_services` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `service_name` varchar(255) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `idExperiment` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `InputPort`
--

CREATE TABLE IF NOT EXISTS `InputPort` (
`idPort` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `InputPort`
--

INSERT INTO `InputPort` (`idPort`, `Name`, `Description`, `Task_idTask`, `Value`, `Wf`) VALUES
(1, 'Starting port workflow', 'Port of task with valeu ', 1, '6', 0),
(2, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(3, 'Starting port workflow', 'Port of task with valeu ', 1, '8', 0),
(4, 'Starting port workflow', 'Port of task with valeu ', 1, '8', 0),
(5, 'Starting port workflow', 'Port of task with valeu ', 1, '3', 0),
(6, 'Starting port workflow', 'Port of task with valeu ', 1, '7', 0),
(7, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(8, 'Starting port task1', '1 task input with valueinput 0', 1, '0', 0),
(9, 'Starting port task1', '1 task input with valueinput 0', 1, '0', 0),
(10, 'Starting port workflow', 'Port of task with valeu ', 1, '3', 0),
(11, 'Starting port workflow', 'Port of task with valeu ', 1, '7', 0),
(12, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(13, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(14, 'Starting port task1', '1 task input with valueinput 12', 1, '12', 0),
(15, 'Starting port task1', '1 task input with valueinput 12', 1, '12', 0),
(16, 'Starting port workflow', 'Port of task with valeu ', 1, '3', 0),
(17, 'Starting port workflow', 'Port of task with valeu ', 1, '7', 0),
(18, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(19, 'Starting port task5', '5 task input with valueinput 8', 5, '8', 0),
(20, 'Starting port task5', '5 task input with valueinput 8', 5, '8', 0),
(21, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(22, 'Starting port task5', '5 task input with valueinput -12', 5, '-12', 0),
(23, 'Starting port task5', '5 task input with valueinput -12', 5, '-12', 0),
(24, 'Starting port workflow', 'Port of task with valeu ', 1, '3', 0),
(25, 'Starting port workflow', 'Port of task with valeu ', 1, '7', 0),
(26, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(27, 'Starting port task5', '5 task input with valueinput 8', 5, '8', 0),
(28, 'Starting port task5', '5 task input with valueinput 8', 5, '8', 0),
(29, 'Starting port workflow', 'Port of task with valeu ', 1, '5', 0),
(30, 'Starting port task5', '5 task input with valueinput 12', 5, '12', 0),
(31, 'Starting port task5', '5 task input with valueinput 12', 5, '12', 0),
(32, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(33, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(34, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(35, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', 0),
(36, 'Starting port taskMultiplication', 'Task Multiplication input with valueinput 8', 5, '8', 0),
(37, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(38, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(39, 'Starting port taskMultiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(40, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(41, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(42, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(43, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(44, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(45, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(46, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(47, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(48, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(49, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(50, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(51, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(52, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(53, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(54, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(55, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(56, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(57, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(58, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(59, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(60, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(61, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', 0),
(62, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', 0),
(63, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(64, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(65, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(66, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(67, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(68, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(69, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', 0),
(70, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 12', 5, '12', 0),
(71, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(72, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(73, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(74, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(75, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(76, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(77, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(78, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(79, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(80, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(81, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(82, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(83, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(84, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(85, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(86, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(87, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(88, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(89, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(90, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(91, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(92, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(93, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(94, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(95, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(96, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(97, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(98, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(99, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(100, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(101, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(102, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(103, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(104, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(105, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(106, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(107, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(108, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(109, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(110, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(111, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(112, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(113, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(114, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(115, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(116, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(117, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(118, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(119, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(120, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(121, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(122, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(123, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(124, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(125, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(126, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(127, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(128, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(129, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(130, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(131, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(132, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(133, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(134, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(135, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(136, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(137, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(138, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(139, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(140, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(141, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(142, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(143, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(144, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(145, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(146, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(147, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(148, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(149, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(150, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(151, 'Starting port workflow to Sum', 'Port of task with valeu 7', 1, '7', 0),
(152, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(153, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', 0),
(154, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 8', 5, '8', 0),
(155, 'Starting port workflow to Sum', 'Port of task with valeu 5', 1, '5', 0),
(156, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(157, 'Starting port task Multiplication', 'Task Multiplication input with valueinput -12', 5, '-12', 0),
(158, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(159, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(160, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(161, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(162, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(163, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(164, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(165, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(166, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(167, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(168, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(169, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(170, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(171, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(172, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(173, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(174, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(175, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(176, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(177, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(178, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(179, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(180, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(181, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(182, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(183, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(184, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(185, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(186, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(187, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(188, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(189, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(190, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(191, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(192, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(193, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(194, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(195, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(196, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(197, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(198, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(199, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(200, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(201, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(202, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(203, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(204, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(205, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(206, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(207, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(208, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(209, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(210, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(211, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(212, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(213, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(214, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(215, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(216, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(217, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(218, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(219, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(220, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(221, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(222, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(223, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(224, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(225, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(226, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(227, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(228, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(229, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(230, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(231, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(232, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(233, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(234, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(235, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(236, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(237, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(238, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(239, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(240, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(241, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(242, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(243, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(244, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(245, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(246, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(247, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(248, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(249, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(250, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(251, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(252, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 0),
(253, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 0),
(254, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 0),
(255, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(256, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 0),
(257, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 0),
(258, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(259, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 0),
(260, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 6),
(261, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 6),
(262, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 6),
(263, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 6),
(264, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 6),
(265, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 6),
(266, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 6),
(267, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 6),
(268, 'Starting port workflow to Sum', 'Port of task with valeu 3', 1, '3', 6),
(269, 'Starting port workflow to Subtraction', 'Port of task with valeu 7', 3, '7', 6),
(270, 'Starting port workflow to Sum', 'Port of task with valeu 2', 1, '2', 6),
(271, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 6),
(272, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 5', 5, '5', 6),
(273, 'Starting port workflow to Subtraction', 'Port of task with valeu 5', 3, '5', 6),
(274, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 6),
(275, 'Starting port task Multiplication', 'Task Multiplication input with valueinput 2', 5, '2', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `IsPartOf`
--

CREATE TABLE IF NOT EXISTS `IsPartOf` (
`idIsPartOf` int(11) NOT NULL,
  `Agent_idAgent` int(11) DEFAULT NULL,
  `ResearchGroup_idResearchGroup` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `IsPartOf`
--

INSERT INTO `IsPartOf` (`idIsPartOf`, `Agent_idAgent`, `ResearchGroup_idResearchGroup`, `Description`) VALUES
(1, 2, 1, 'Professora de Pós-graduação'),
(2, 1, 1, 'Aluno de Pós-graduação'),
(3, 3, 1, 'Aluno de Pós-graduação'),
(4, 4, 1, 'Professor de Pós-graduação');

-- --------------------------------------------------------

--
-- Estrutura para tabela `OutputPort`
--

CREATE TABLE IF NOT EXISTS `OutputPort` (
`idPort` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  `Wf` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `OutputPort`
--

INSERT INTO `OutputPort` (`idPort`, `Name`, `Description`, `Task_idTask`, `Value`, `Wf`) VALUES
(1, 'Ended Port Task 1', 'Task output with valueoutput 11', 1, '11', 0),
(2, 'Ended Port Task 1', 'Task output with valueoutput 16', 1, '16', 0),
(3, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(4, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(5, 'Ended Port Task 1', 'Task output with valueoutput 15', 1, '15', 0),
(6, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(7, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(8, 'Ended Port Task 1', 'Task output with valueoutput 20', 1, '20', 0),
(9, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(10, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(11, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(12, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(13, 'Ended Port Task 1', 'Task output with valueoutput -12', 1, '-12', 0),
(14, 'Ended Port Task 1', 'Task output with valueoutput -12', 1, '-12', 0),
(15, 'Ended Port Task 5', 'Task output with valueoutput 96', 5, '96', 0),
(16, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(17, 'Ended Port Task 1', 'Task output with valueoutput 8', 1, '8', 0),
(18, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(19, 'Ended Port Task 1', 'Task output with valueoutput 12', 1, '12', 0),
(20, 'Ended Port Task 5', 'Task output with valueoutput 96', 5, '96', 0),
(21, 'Ended Port Task Sum', 'Task Sumoutput with valueoutput 8', 1, '8', 0),
(22, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', 0),
(23, 'Ended Port Task Sum', 'Task Sumoutput with valueoutput -12', 1, '-12', 0),
(24, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(25, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 96', 5, '96', 0),
(26, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(27, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(28, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(29, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(30, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', 0),
(31, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(32, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(33, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(34, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(35, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', 0),
(36, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(37, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(38, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', 0),
(39, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', 0),
(40, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', 0),
(41, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(42, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(43, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', 0),
(44, 'Ended Port Task Sum', 'Task Sum output with valueoutput 12', 1, '12', 0),
(45, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 60', 5, '60', 0),
(46, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(47, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(48, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', 0),
(49, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', 0),
(50, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(51, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(52, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(53, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', 0),
(54, 'Ended Port Task Sum', 'Task Sum output with valueoutput 2', 1, '2', 0),
(55, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(56, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(57, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(58, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(59, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(60, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(61, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(62, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(63, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(64, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(65, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(66, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(67, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(68, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(69, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(70, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(71, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(72, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(73, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(74, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(75, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(76, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(77, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(78, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(79, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(80, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(81, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', 0),
(82, 'Ended Port Task Sum', 'Task Sum output with valueoutput 8', 1, '8', 0),
(83, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(84, 'Ended Port Task Sum', 'Task Sum output with valueoutput -12', 1, '-12', 0),
(85, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 96', 5, '96', 0),
(86, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(87, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(88, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(89, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(90, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(91, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(92, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(93, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(94, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(95, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(96, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(97, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(98, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(99, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(100, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(101, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(102, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(103, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(104, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(105, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(106, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(107, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(108, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(109, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(110, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(111, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(112, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(113, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(114, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(115, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(116, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(117, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(118, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(119, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(120, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(121, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(122, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(123, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(124, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(125, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(126, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(127, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(128, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(129, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(130, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(131, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 0),
(132, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(133, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 0),
(134, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 0),
(135, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 6),
(136, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 6),
(137, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 6),
(138, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 6),
(139, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 6),
(140, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 6),
(141, 'Ended Port Task Sum', 'Task Sum output with valueoutput 5', 1, '5', 6),
(142, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 6),
(143, 'Ended Port Task Subtraction', 'Task Subtraction output with valueoutput 2', 3, '2', 6),
(144, 'Ended Port Task Multiplication', 'Task Multiplication output with valueoutput 10', 5, '10', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `ResearchGroup`
--

CREATE TABLE IF NOT EXISTS `ResearchGroup` (
`idResearchGroup` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Agent_idAgent_chef` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `ResearchGroup`
--

INSERT INTO `ResearchGroup` (`idResearchGroup`, `Name`, `Description`, `Agent_idAgent_chef`) VALUES
(1, 'NEnC', 'Núcleo de Engenharia do Conhecimento', 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `scientist`
--

CREATE TABLE IF NOT EXISTS `scientist` (
`id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `SGWfC`
--

CREATE TABLE IF NOT EXISTS `SGWfC` (
`idSGWfC` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `SGWfC`
--

INSERT INTO `SGWfC` (`idSGWfC`, `Name`, `Description`) VALUES
(1, 'Kepler', 'Kepler é um software livre do sistema para a concepção, execução, reutilizando, evoluindo, arquivamento e compartilhamento científicos'),
(2, 'Taverna Workbench', 'Taverna é um conjunto de ferramentas usadas para criar e executar workflows científicos e ajuda na experimentação in silico');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Task`
--

CREATE TABLE IF NOT EXISTS `Task` (
`idTask` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Task`
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
-- Estrutura para tabela `taverna_workflow`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow` (
  `id` bigint(20) NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `t2flow` mediumtext,
  `idAgent` int(11) NOT NULL,
  `experiment_id` int(11) NOT NULL,
  `scientist_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `taverna_workflow_input`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_input` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `taverna_workflow_run`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_run` (
  `id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `taverna_workflow_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `taverna_workflow_run_input_value`
--

CREATE TABLE IF NOT EXISTS `taverna_workflow_run_input_value` (
  `id` bigint(20) NOT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `taverna_workflow_input_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Used`
--

CREATE TABLE IF NOT EXISTS `Used` (
`idUsed` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Used`
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
-- Estrutura para tabela `WasAssociatedWith`
--

CREATE TABLE IF NOT EXISTS `WasAssociatedWith` (
`idWasAssociatedWith` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) DEFAULT NULL,
  `Experiment_Experiment` int(11) DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasAssociatedWith`
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
-- Estrutura para tabela `WasControledBy`
--

CREATE TABLE IF NOT EXISTS `WasControledBy` (
`idWasControledBy` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `Agent_idAgent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasDerivedFrom`
--

CREATE TABLE IF NOT EXISTS `WasDerivedFrom` (
`idWasDerivedFrom` int(11) NOT NULL,
  `DerivedOf` int(11) NOT NULL,
  `DerivedTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasDerivedFrom`
--

INSERT INTO `WasDerivedFrom` (`idWasDerivedFrom`, `DerivedOf`, `DerivedTo`, `Type`) VALUES
(1, 1, 2, 'Evolution'),
(2, 2, 3, 'Evolution');

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasEndedBy`
--

CREATE TABLE IF NOT EXISTS `WasEndedBy` (
`idWasEndedBy` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateEnded` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasEndedBy`
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
-- Estrutura para tabela `WasEndedByWT`
--

CREATE TABLE IF NOT EXISTS `WasEndedByWT` (
`idWasEndedByWT` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Ended` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasGeneratedBy`
--

CREATE TABLE IF NOT EXISTS `WasGeneratedBy` (
  `idWasGeneratedBy` int(11) NOT NULL,
  `Experiment_Experiment` int(11) NOT NULL,
  `ResearchGroup_idResearchGroup` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasInformedBy`
--

CREATE TABLE IF NOT EXISTS `WasInformedBy` (
`idWasInformedBy` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasInformedBy`
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
-- Estrutura para tabela `WasRevisionOf`
--

CREATE TABLE IF NOT EXISTS `WasRevisionOf` (
`idWasRevisionOf` int(11) NOT NULL,
  `RevisionOf` int(11) NOT NULL,
  `RevisionTo` int(11) NOT NULL,
  `Type` varchar(255) COLLATE utf8_swedish_ci DEFAULT 'Corrective'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasRevisionOf`
--

INSERT INTO `WasRevisionOf` (`idWasRevisionOf`, `RevisionOf`, `RevisionTo`, `Type`) VALUES
(1, 4, 5, 'Corretive');

-- --------------------------------------------------------

--
-- Estrutura para tabela `WasStartedBy`
--

CREATE TABLE IF NOT EXISTS `WasStartedBy` (
`idWasStartedBy` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Activity_idActivity` int(11) NOT NULL,
  `DateStarted` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `WasStartedBy`
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
-- Estrutura para tabela `WasStartedByWT`
--

CREATE TABLE IF NOT EXISTS `WasStartedByWT` (
`idWasStartedByWT` int(11) NOT NULL,
  `Workflow_idWorkflow` int(11) NOT NULL,
  `Task_idTask` int(11) NOT NULL,
  `Started` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Workflow`
--

CREATE TABLE IF NOT EXISTS `Workflow` (
`idWorkflow` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `Version` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `DateVersion` date DEFAULT NULL,
  `NumberStage` int(11) DEFAULT NULL,
  `SGWfC_idSGWfC` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Fazendo dump de dados para tabela `Workflow`
--

INSERT INTO `Workflow` (`idWorkflow`, `Name`, `Description`, `Version`, `DateVersion`, `NumberStage`, `SGWfC_idSGWfC`) VALUES
(1, 'SimpleAddition', 'Sum of two values', '01.00.00', '2015-06-18', 1, 1),
(2, 'SimpleSum', 'Sum of three values', '01.00.00', '2015-06-19', 2, 1),
(3, 'SimpleSum2', 'Sum of four values', '01.00.00', '2015-06-22', 3, 1),
(4, 'SimpleCount', 'Calculation values', '01.00.00', '2015-06-24', 3, 1),
(5, 'SimpleCount2', 'Calculation values', '01.00.00', '2015-06-26', 3, 1),
(6, 'Demonstracao', 'Apresentacao NenC', '01.00.00', '2015-10-08', 3, 1);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
 ADD PRIMARY KEY (`idActedOnBehalfOf`), ADD KEY `fk_InputPort_has_OutputPort_OutputPort1_idx` (`OutputPort_idPort`), ADD KEY `fk_InputPort_has_OutputPort_InputPort1_idx` (`InputPort_idPort`);

--
-- Índices de tabela `Activity`
--
ALTER TABLE `Activity`
 ADD PRIMARY KEY (`idActivity`), ADD KEY `fk_Activity_Entity1_idx` (`Entity_idEntity`);

--
-- Índices de tabela `Agent`
--
ALTER TABLE `Agent`
 ADD PRIMARY KEY (`idAgent`), ADD UNIQUE KEY `Login_UNIQUE` (`Login`), ADD UNIQUE KEY `Email_UNIQUE` (`Email`), ADD KEY `fk_Agent_Entity1_idx` (`Institution`);

--
-- Índices de tabela `Entity`
--
ALTER TABLE `Entity`
 ADD PRIMARY KEY (`idEntity`);

--
-- Índices de tabela `Experiment`
--
ALTER TABLE `Experiment`
 ADD PRIMARY KEY (`idExperiment`), ADD KEY `fk_Expiriment_Entity1_idx` (`Entity_idEntity`), ADD KEY `fk_Expiriment_Activity1_idx` (`Activity_idActivity`), ADD KEY `fk_Experiment_Agent1_idx` (`idAgent`), ADD KEY `fk_Experiment_experiment_services1_idx` (`experiment_services_id`);

--
-- Índices de tabela `experiment_services`
--
ALTER TABLE `experiment_services`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_experiment_services_Experiment1_idx` (`idExperiment`);

--
-- Índices de tabela `InputPort`
--
ALTER TABLE `InputPort`
 ADD PRIMARY KEY (`idPort`), ADD KEY `fk_Port_Task1` (`Task_idTask`);

--
-- Índices de tabela `IsPartOf`
--
ALTER TABLE `IsPartOf`
 ADD PRIMARY KEY (`idIsPartOf`), ADD KEY `fk_Agent_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`), ADD KEY `fk_Agent_has_ResearchGroup_Agent1_idx` (`Agent_idAgent`);

--
-- Índices de tabela `OutputPort`
--
ALTER TABLE `OutputPort`
 ADD PRIMARY KEY (`idPort`), ADD KEY `fk_Port_Task1_idx` (`Task_idTask`);

--
-- Índices de tabela `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
 ADD PRIMARY KEY (`idResearchGroup`), ADD KEY `fk_Agent_has_Expiriment_Agent1_idx` (`Agent_idAgent_chef`);

--
-- Índices de tabela `scientist`
--
ALTER TABLE `scientist`
 ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `SGWfC`
--
ALTER TABLE `SGWfC`
 ADD PRIMARY KEY (`idSGWfC`);

--
-- Índices de tabela `Task`
--
ALTER TABLE `Task`
 ADD PRIMARY KEY (`idTask`);

--
-- Índices de tabela `taverna_workflow`
--
ALTER TABLE `taverna_workflow`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_Agent1_idx` (`idAgent`), ADD KEY `fk_taverna_workflow_Experiment1_idx` (`experiment_id`), ADD KEY `FK_8e7q9jrwky4yo2d83s6p3tu8j` (`scientist_id`);

--
-- Índices de tabela `taverna_workflow_input`
--
ALTER TABLE `taverna_workflow_input`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_input_taverna_workflow1_idx` (`taverna_workflow_id`);

--
-- Índices de tabela `taverna_workflow_run`
--
ALTER TABLE `taverna_workflow_run`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_run_taverna_workflow1_idx` (`taverna_workflow_id`);

--
-- Índices de tabela `taverna_workflow_run_input_value`
--
ALTER TABLE `taverna_workflow_run_input_value`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_taverna_workflow_run_input_value_taverna_workflow_input1_idx` (`taverna_workflow_input_id`);

--
-- Índices de tabela `Used`
--
ALTER TABLE `Used`
 ADD PRIMARY KEY (`idUsed`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_Used_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Índices de tabela `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
 ADD PRIMARY KEY (`idWasAssociatedWith`), ADD KEY `fk_Workflow_has_Expiriment_Expiriment1_idx` (`Experiment_Experiment`), ADD KEY `fk_Workflow_has_Expiriment_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Índices de tabela `WasControledBy`
--
ALTER TABLE `WasControledBy`
 ADD PRIMARY KEY (`idWasControledBy`), ADD KEY `fk_WasControledBy_Activity1_idx` (`Activity_idActivity`), ADD KEY `fk_WasControledBy_Agent1_idx` (`Agent_idAgent`);

--
-- Índices de tabela `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
 ADD PRIMARY KEY (`idWasDerivedFrom`), ADD KEY `fk_WasDerivedFrom_Workflow1_idx` (`DerivedOf`), ADD KEY `fk_WasDerivedFrom_Workflow2_idx` (`DerivedTo`);

--
-- Índices de tabela `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
 ADD PRIMARY KEY (`idWasEndedBy`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasEndedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Índices de tabela `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
 ADD PRIMARY KEY (`idWasEndedByWT`), ADD KEY `fk_Workflow_has_Task_Task2_idx` (`Task_idTask`), ADD KEY `fk_Workflow_has_Task_Workflow2_idx` (`Workflow_idWorkflow`);

--
-- Índices de tabela `WasGeneratedBy`
--
ALTER TABLE `WasGeneratedBy`
 ADD PRIMARY KEY (`idWasGeneratedBy`), ADD KEY `fk_Experiment_has_ResearchGroup_ResearchGroup1_idx` (`ResearchGroup_idResearchGroup`), ADD KEY `fk_Experiment_has_ResearchGroup_Experiment1_idx` (`Experiment_Experiment`);

--
-- Índices de tabela `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
 ADD PRIMARY KEY (`idWasInformedBy`), ADD KEY `fk_WasInformedBy_Port_Entity_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasInformedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Índices de tabela `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
 ADD PRIMARY KEY (`idWasRevisionOf`), ADD KEY `fk_WasDerivedFrom_Workflow1_idx` (`RevisionOf`), ADD KEY `fk_WasDerivedFrom_Workflow2_idx` (`RevisionTo`);

--
-- Índices de tabela `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
 ADD PRIMARY KEY (`idWasStartedBy`), ADD KEY `fk_WasStartedBy_Task1_idx` (`Task_idTask`), ADD KEY `fk_WasStartedBy_Activity1_idx` (`Activity_idActivity`);

--
-- Índices de tabela `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
 ADD PRIMARY KEY (`idWasStartedByWT`), ADD KEY `fk_Workflow_has_Task_Task1_idx` (`Task_idTask`), ADD KEY `fk_Workflow_has_Task_Workflow1_idx` (`Workflow_idWorkflow`);

--
-- Índices de tabela `Workflow`
--
ALTER TABLE `Workflow`
 ADD PRIMARY KEY (`idWorkflow`), ADD KEY `fk_Workflow_SGWfC1_idx` (`SGWfC_idSGWfC`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
MODIFY `idActedOnBehalfOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT de tabela `Activity`
--
ALTER TABLE `Activity`
MODIFY `idActivity` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `Agent`
--
ALTER TABLE `Agent`
MODIFY `idAgent` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `Entity`
--
ALTER TABLE `Entity`
MODIFY `idEntity` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `Experiment`
--
ALTER TABLE `Experiment`
MODIFY `idExperiment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `InputPort`
--
ALTER TABLE `InputPort`
MODIFY `idPort` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=276;
--
-- AUTO_INCREMENT de tabela `IsPartOf`
--
ALTER TABLE `IsPartOf`
MODIFY `idIsPartOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `OutputPort`
--
ALTER TABLE `OutputPort`
MODIFY `idPort` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=145;
--
-- AUTO_INCREMENT de tabela `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
MODIFY `idResearchGroup` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `scientist`
--
ALTER TABLE `scientist`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `SGWfC`
--
ALTER TABLE `SGWfC`
MODIFY `idSGWfC` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `Task`
--
ALTER TABLE `Task`
MODIFY `idTask` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de tabela `Used`
--
ALTER TABLE `Used`
MODIFY `idUsed` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=210;
--
-- AUTO_INCREMENT de tabela `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
MODIFY `idWasAssociatedWith` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT de tabela `WasControledBy`
--
ALTER TABLE `WasControledBy`
MODIFY `idWasControledBy` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
MODIFY `idWasDerivedFrom` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
MODIFY `idWasEndedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT de tabela `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
MODIFY `idWasEndedByWT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
MODIFY `idWasInformedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=71;
--
-- AUTO_INCREMENT de tabela `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
MODIFY `idWasRevisionOf` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
MODIFY `idWasStartedBy` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT de tabela `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
MODIFY `idWasStartedByWT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `Workflow`
--
ALTER TABLE `Workflow`
MODIFY `idWorkflow` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `ActedOnBehalfOf`
--
ALTER TABLE `ActedOnBehalfOf`
ADD CONSTRAINT `fk_InputPort_has_OutputPort_InputPort1` FOREIGN KEY (`InputPort_idPort`) REFERENCES `InputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_InputPort_has_OutputPort_OutputPort1` FOREIGN KEY (`OutputPort_idPort`) REFERENCES `OutputPort` (`idPort`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Restrições para tabelas `Experiment`
--
ALTER TABLE `Experiment`
ADD CONSTRAINT `fk_Experiment_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Experiment_experiment_services1` FOREIGN KEY (`experiment_services_id`) REFERENCES `experiment_services` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Expiriment_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Expiriment_Entity1` FOREIGN KEY (`Entity_idEntity`) REFERENCES `Entity` (`idEntity`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `experiment_services`
--
ALTER TABLE `experiment_services`
ADD CONSTRAINT `fk_experiment_services_Experiment1` FOREIGN KEY (`idExperiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `InputPort`
--
ALTER TABLE `InputPort`
ADD CONSTRAINT `fk_Port_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `IsPartOf`
--
ALTER TABLE `IsPartOf`
ADD CONSTRAINT `fk_Agent_has_ResearchGroup_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Agent_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `OutputPort`
--
ALTER TABLE `OutputPort`
ADD CONSTRAINT `fk_Port_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `ResearchGroup`
--
ALTER TABLE `ResearchGroup`
ADD CONSTRAINT `fk_Agent_has_Expiriment_Agent1` FOREIGN KEY (`Agent_idAgent_chef`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `taverna_workflow`
--
ALTER TABLE `taverna_workflow`
ADD CONSTRAINT `FK_8e7q9jrwky4yo2d83s6p3tu8j` FOREIGN KEY (`scientist_id`) REFERENCES `scientist` (`id`),
ADD CONSTRAINT `fk_taverna_workflow_Agent1` FOREIGN KEY (`idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_taverna_workflow_Experiment1` FOREIGN KEY (`experiment_id`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `taverna_workflow_input`
--
ALTER TABLE `taverna_workflow_input`
ADD CONSTRAINT `fk_taverna_workflow_input_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `taverna_workflow_run`
--
ALTER TABLE `taverna_workflow_run`
ADD CONSTRAINT `fk_taverna_workflow_run_taverna_workflow1` FOREIGN KEY (`taverna_workflow_id`) REFERENCES `taverna_workflow` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `taverna_workflow_run_input_value`
--
ALTER TABLE `taverna_workflow_run_input_value`
ADD CONSTRAINT `fk_taverna_workflow_run_input_value_taverna_workflow_input1` FOREIGN KEY (`taverna_workflow_input_id`) REFERENCES `taverna_workflow_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Used`
--
ALTER TABLE `Used`
ADD CONSTRAINT `fk_Used_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task100` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasAssociatedWith`
--
ALTER TABLE `WasAssociatedWith`
ADD CONSTRAINT `fk_Workflow_has_Expiriment_Expiriment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Expiriment_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasControledBy`
--
ALTER TABLE `WasControledBy`
ADD CONSTRAINT `fk_WasControledBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasControledBy_Agent1` FOREIGN KEY (`Agent_idAgent`) REFERENCES `Agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasDerivedFrom`
--
ALTER TABLE `WasDerivedFrom`
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow1` FOREIGN KEY (`DerivedOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow2` FOREIGN KEY (`DerivedTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasEndedBy`
--
ALTER TABLE `WasEndedBy`
ADD CONSTRAINT `fk_WasEndedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task10` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasEndedByWT`
--
ALTER TABLE `WasEndedByWT`
ADD CONSTRAINT `fk_Workflow_has_Task_Task2` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Task_Workflow2` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasGeneratedBy`
--
ALTER TABLE `WasGeneratedBy`
ADD CONSTRAINT `fk_Experiment_has_ResearchGroup_Experiment1` FOREIGN KEY (`Experiment_Experiment`) REFERENCES `Experiment` (`idExperiment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Experiment_has_ResearchGroup_ResearchGroup1` FOREIGN KEY (`ResearchGroup_idResearchGroup`) REFERENCES `ResearchGroup` (`idResearchGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasInformedBy`
--
ALTER TABLE `WasInformedBy`
ADD CONSTRAINT `fk_WasInformedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasInformedBy_Port_Entity_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasRevisionOf`
--
ALTER TABLE `WasRevisionOf`
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow10` FOREIGN KEY (`RevisionOf`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasDerivedFrom_Workflow20` FOREIGN KEY (`RevisionTo`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasStartedBy`
--
ALTER TABLE `WasStartedBy`
ADD CONSTRAINT `fk_WasStartedBy_Activity1` FOREIGN KEY (`Activity_idActivity`) REFERENCES `Activity` (`idActivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_WasStartedBy_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `WasStartedByWT`
--
ALTER TABLE `WasStartedByWT`
ADD CONSTRAINT `fk_Workflow_has_Task_Task1` FOREIGN KEY (`Task_idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Workflow_has_Task_Workflow1` FOREIGN KEY (`Workflow_idWorkflow`) REFERENCES `Workflow` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `Workflow`
--
ALTER TABLE `Workflow`
ADD CONSTRAINT `fk_Workflow_SGWfC1` FOREIGN KEY (`SGWfC_idSGWfC`) REFERENCES `SGWfC` (`idSGWfC`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
