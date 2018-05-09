-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 09 Maj 2018, 11:40
-- Wersja serwera: 5.6.21
-- Wersja PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `komunikator`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kontakty`
--

CREATE TABLE IF NOT EXISTS `kontakty` (
`idKontaktu` int(3) NOT NULL,
  `idUzytkownika1` int(5) NOT NULL,
  `idUzytkownika2` int(5) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `kontakty`
--

INSERT INTO `kontakty` (`idKontaktu`, `idUzytkownika1`, `idUzytkownika2`) VALUES
(3, 1, 3),
(4, 2, 1),
(6, 3, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE IF NOT EXISTS `uzytkownicy` (
`idUzytkownika` int(5) NOT NULL,
  `login` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_polish_ci NOT NULL DEFAULT 'niedostępny',
  `Email` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(50) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`idUzytkownika`, `login`, `status`, `Email`, `haslo`) VALUES
(1, 'uzytkownik1', 'zajęty', '', 'test'),
(2, 'uzytkownik2', 'dostępny', '', ''),
(3, 'uzytkownik3', 'dostępny', '', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wiadomosci`
--

CREATE TABLE IF NOT EXISTS `wiadomosci` (
`idWiadomosci` int(10) NOT NULL,
  `idWysylajacego` int(6) NOT NULL,
  `idAdresata` int(6) NOT NULL,
  `tresc` varchar(250) COLLATE utf8_polish_ci DEFAULT NULL,
  `data` datetime NOT NULL,
  `wyswietlona` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `wiadomosci`
--

INSERT INTO `wiadomosci` (`idWiadomosci`, `idWysylajacego`, `idAdresata`, `tresc`, `data`, `wyswietlona`) VALUES
(3, 1, 2, 'TEST', '2018-03-26 18:14:05', 1),
(4, 1, 2, 'TEST', '2018-03-26 18:14:28', 1),
(5, 1, 2, 'TEST', '2018-03-26 18:15:17', 1),
(6, 1, 2, 'TEST', '2018-03-26 18:24:32', 1),
(7, 1, 2, 'test', '2018-03-26 18:35:12', 1),
(8, 1, 2, 'TextBox', '2018-03-26 18:36:08', 1),
(9, 1, 2, 'TextBox', '2018-03-26 18:37:45', 1),
(10, 1, 2, 'TextBox', '2018-03-26 18:44:35', 1),
(11, 1, 2, '1', '2018-03-26 18:47:21', 1),
(12, 1, 2, '2', '2018-03-26 18:47:34', 1),
(13, 1, 2, 'TEST', '2018-03-26 18:49:31', 1),
(14, 1, 2, 'gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg', '2018-03-26 19:09:49', 1),
(15, 1, 2, 'fgdfgdfg', '2018-03-26 20:14:38', 1),
(16, 1, 2, 'fgddfg', '2018-03-26 20:14:43', 1),
(17, 1, 2, 'hsked', '2018-03-26 20:15:13', 1),
(18, 1, 2, '', '2018-03-26 20:15:14', 1),
(19, 1, 2, 'fdadfddasf', '2018-03-26 20:15:16', 1),
(20, 1, 2, 'fdf', '2018-03-26 20:15:20', 1),
(21, 1, 2, 'l;lk;kl;', '2018-03-26 20:15:23', 1),
(22, 1, 2, 'Hello', '2018-03-26 20:18:55', 1),
(23, 1, 2, 'ZEA', '2018-03-26 20:51:05', 1),
(24, 1, 2, 'al', '2018-03-26 20:51:30', 1),
(25, 1, 2, 'asc', '2018-03-26 21:00:20', 1),
(26, 1, 2, '???', '2018-03-26 21:02:36', 1),
(27, 1, 2, 'hghg', '2018-03-26 21:02:51', 1),
(28, 1, 2, 'fgdfgf', '2018-03-26 21:04:01', 1),
(29, 1, 2, 'gytry', '2018-03-26 21:04:16', 1),
(30, 1, 2, 'ghghgf', '2018-03-26 21:06:33', 1),
(31, 1, 2, 'TEST', '2018-03-26 21:07:17', 1),
(32, 1, 2, 'aaaaa', '2018-03-26 21:08:02', 1),
(33, 1, 2, 'aaa', '2018-03-26 21:09:52', 1),
(34, 1, 2, '???', '2018-03-26 21:12:06', 1),
(35, 1, 2, 'test', '2018-03-29 12:37:39', 1),
(36, 1, 2, 'test2222', '2018-03-29 12:37:55', 1),
(37, 1, 2, 'tetas', '2018-03-29 12:37:58', 1),
(38, 1, 2, 'teate', '2018-03-29 12:38:00', 1),
(39, 1, 2, 'fgfh', '2018-03-29 12:42:12', 1),
(40, 1, 2, 'test', '2018-03-29 12:52:50', 1),
(41, 1, 2, 'test', '2018-03-29 12:55:37', 1),
(42, 1, 2, 'test', '2018-03-29 12:56:43', 1),
(43, 1, 2, 'grta', '2018-03-29 13:02:30', 1),
(44, 1, 2, 'test', '2018-03-29 13:22:49', 1),
(45, 1, 2, 'test', '2018-03-29 13:24:13', 1),
(46, 1, 2, 'test', '2018-03-29 13:24:14', 1),
(47, 1, 2, 'test', '2018-03-29 13:24:26', 1),
(48, 1, 2, 'test', '2018-03-29 13:25:49', 1),
(49, 1, 2, 'glsdhglsdhgsdhgldshgdklsgskdgkgjkg', '2018-03-29 13:26:00', 1),
(50, 1, 2, 'gdjskgjdkgjdkgjkgjfkjgkfjgkfjgkfjgkfjgkfgjkfjgkfgjfgjsfkgjsfg', '2018-03-29 13:26:05', 1),
(51, 1, 2, 'test', '2018-03-29 13:32:31', 1),
(52, 1, 2, 'test', '2018-03-29 13:33:06', 1),
(53, 1, 2, 'test', '2018-03-29 13:33:59', 1),
(54, 1, 2, 'TEST', '2018-03-29 13:42:44', 1),
(55, 1, 2, 'TEST', '2018-03-29 13:42:44', 1),
(56, 1, 2, 'test', '2018-03-29 13:44:27', 1),
(57, 1, 2, 'test', '2018-03-29 13:45:14', 1),
(58, 1, 2, 'test', '2018-03-29 13:46:57', 1),
(59, 1, 2, 'fhdgjhsdfg', '2018-03-29 13:47:22', 1),
(60, 1, 2, 'Lorem ipsum', '2018-03-29 13:47:55', 1),
(61, 1, 2, 'sfafjakkadgj ahdfsf isdjaisdasidj fdijg', '2018-03-29 13:48:03', 1),
(62, 2, 1, 'test', '2018-03-29 13:52:14', 1),
(63, 2, 1, 'test2', '2018-03-29 13:52:16', 1),
(64, 2, 1, 'test3', '2018-03-29 13:52:19', 1),
(65, 2, 1, 'test', '2018-03-29 13:52:21', 1),
(66, 2, 1, 'tesr3', '2018-03-29 13:52:23', 1),
(67, 1, 2, 'test', '2018-03-29 13:52:44', 1),
(68, 1, 2, 'test', '2018-03-29 13:52:46', 1),
(69, 1, 2, 'test', '2018-03-29 13:52:47', 1),
(70, 1, 2, 'test3', '2018-03-29 13:52:49', 1),
(71, 1, 2, 'TEST', '2018-03-29 14:49:00', 1),
(72, 1, 2, 'TEST', '2018-03-29 14:49:00', 1),
(73, 1, 2, 'TEST', '2018-03-29 15:07:39', 1),
(74, 1, 2, 'TEST', '2018-03-29 15:07:39', 1),
(75, 1, 2, 'test', '2018-03-29 15:19:42', 1),
(76, 1, 2, 'test', '2018-03-29 15:19:58', 1),
(77, 1, 2, 'test', '2018-03-29 15:20:05', 1),
(78, 1, 2, 'test', '2018-03-29 16:40:38', 1),
(79, 1, 2, 'TEST', '2018-03-29 17:50:59', 1),
(80, 1, 2, 'TEST', '2018-03-29 17:50:59', 1),
(81, 1, 2, 'TEST', '2018-03-29 17:50:59', 1),
(82, 1, 2, 'TEST', '2018-03-29 17:52:01', 1),
(83, 1, 2, 'TEST', '2018-03-29 17:52:01', 1),
(84, 1, 2, 'TEST', '2018-03-29 17:52:01', 1),
(85, 1, 2, 'test', '2018-03-29 18:12:43', 1),
(86, 2, 1, 'test test test', '2018-03-29 18:13:46', 1),
(87, 1, 2, 'test', '2018-03-29 18:18:20', 1),
(88, 2, 1, 'test test test', '2018-03-29 18:19:31', 1),
(89, 1, 2, 'test', '2018-03-29 18:22:46', 1),
(90, 2, 1, 'test test test', '2018-03-29 18:23:08', 1),
(91, 1, 2, 'test', '2018-03-29 18:59:56', 1),
(92, 2, 1, 'test test test', '2018-03-29 19:00:25', 1),
(93, 1, 2, 'test', '2018-03-29 19:14:05', 1),
(94, 2, 1, 'test test test', '2018-03-29 19:14:24', 1),
(95, 1, 2, 'test', '2018-03-29 20:23:37', 1),
(96, 1, 2, 'wiadomo?? testowa', '2018-03-29 20:24:43', 1),
(97, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:25:06', 1),
(98, 1, 2, 'wiadomosc testowa', '2018-03-29 20:26:38', 1),
(99, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:27:06', 1),
(100, 1, 2, 'test', '2018-03-29 20:27:53', 1),
(101, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:28:25', 1),
(102, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:29:01', 1),
(103, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:29:35', 1),
(104, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:30:13', 1),
(105, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:32:35', 1),
(106, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:33:27', 1),
(107, 1, 2, 'test', '2018-03-29 20:33:40', 1),
(108, 1, 2, 'TEST', '2018-03-30 15:18:44', 1),
(109, 1, 2, 'TEST', '2018-03-30 15:18:44', 1),
(110, 1, 2, 'TEST', '2018-03-30 15:18:44', 1),
(111, 1, 3, 'test', '2018-03-30 17:51:22', 1),
(112, 1, 3, 'test', '2018-03-30 17:54:28', 1),
(113, 1, 2, 'TEST', '2018-03-30 18:18:12', 1),
(114, 1, 2, 'TEST', '2018-03-30 18:18:12', 1),
(115, 1, 2, 'TEST', '2018-03-30 18:18:12', 1),
(116, 1, 2, 'TEST', '2018-03-30 18:18:12', 1),
(117, 2, 1, 'fgf', '2018-03-30 18:26:31', 1),
(118, 3, 1, 'test', '2018-03-30 18:57:02', 1),
(119, 2, 1, 'test', '2018-03-30 18:57:15', 1),
(120, 2, 1, 'test', '2018-03-30 21:08:16', 1),
(121, 2, 1, 'assa', '2018-03-30 21:28:21', 1),
(122, 2, 1, 'tr', '2018-03-30 21:31:24', 1),
(123, 2, 1, 'dfsdf', '2018-03-30 21:31:26', 1),
(124, 2, 1, 'sdfds', '2018-03-30 21:31:27', 1),
(125, 2, 1, 'dfsfsd', '2018-03-30 21:31:27', 1),
(126, 3, 1, 'gfgf', '2018-03-30 21:31:47', 1),
(127, 3, 1, 'gjh', '2018-03-30 21:31:48', 1),
(128, 3, 1, 'hkj', '2018-03-30 21:31:50', 1),
(129, 1, 2, 'test', '2018-03-30 21:35:24', 1),
(130, 1, 2, 'test', '2018-03-30 21:35:27', 1),
(131, 1, 2, 'test', '2018-03-30 21:35:30', 1),
(132, 1, 2, 'test', '2018-03-30 21:36:26', 1),
(133, 2, 3, 'test', '2018-03-30 21:50:32', 1),
(134, 1, 3, 'tresc', '2018-03-30 21:52:24', 1),
(135, 2, 3, 'tresc', '2018-03-30 21:54:02', 1),
(136, 1, 3, 'tresc', '2018-03-30 21:54:59', 1),
(137, 1, 3, 'tresc', '2018-03-30 21:55:08', 1),
(138, 1, 3, 'tresc', '2018-03-30 21:55:15', 1),
(139, 1, 3, 'tresc', '2018-03-30 21:55:23', 1),
(140, 1, 3, 'tresc', '2018-03-30 21:55:34', 1),
(141, 2, 3, 'tresc', '2018-03-30 21:56:12', 1),
(142, 1, 3, 'test', '2018-03-30 21:56:58', 1),
(143, 1, 3, 'test', '2018-03-30 21:56:59', 1),
(144, 1, 3, 'test', '2018-03-30 21:57:01', 1),
(145, 1, 3, 'test', '2018-03-30 21:57:02', 1),
(146, 1, 3, 'test', '2018-03-30 21:57:03', 1),
(147, 1, 3, 'test', '2018-03-30 21:57:04', 1),
(148, 1, 2, 'test', '2018-03-30 21:57:11', 1),
(149, 1, 2, 'test', '2018-03-30 21:57:12', 1),
(150, 1, 2, 'test', '2018-03-30 21:57:14', 1),
(151, 2, 1, 'test', '2018-03-30 22:00:17', 1),
(152, 2, 3, 'test', '2018-03-30 22:00:24', 1),
(153, 3, 1, 'test', '2018-03-30 22:06:12', 1),
(154, 3, 1, 'test', '2018-03-30 22:06:13', 1),
(155, 3, 1, 'test', '2018-03-30 22:06:14', 1),
(156, 1, 3, 'test', '2018-03-30 22:06:50', 1),
(157, 1, 3, 'test', '2018-03-30 22:06:50', 1),
(158, 1, 3, 'et', '2018-03-30 22:06:52', 1),
(159, 1, 2, 'test', '2018-03-30 22:06:57', 1),
(160, 1, 2, 'test', '2018-03-30 22:06:58', 1),
(161, 1, 2, 'test', '2018-03-30 22:06:59', 1),
(162, 1, 3, 'test', '2018-03-30 22:07:26', 1),
(163, 2, 1, 'test', '2018-03-30 22:08:12', 1),
(164, 2, 3, 'rwar', '2018-03-30 22:08:17', 1),
(165, 3, 1, 'test', '2018-03-30 22:08:58', 1),
(166, 3, 2, 'test', '2018-03-30 22:09:03', 1),
(167, 1, 3, 'test', '2018-03-30 22:10:50', 1),
(168, 1, 2, 'test', '2018-03-30 22:10:55', 1),
(169, 2, 1, 'test', '2018-03-30 22:11:21', 1),
(170, 2, 1, 'test', '2018-03-30 22:12:53', 1),
(171, 2, 3, 'test', '2018-03-30 22:12:58', 1),
(172, 3, 1, 'test', '2018-03-30 22:13:44', 1),
(173, 3, 2, 'test', '2018-03-30 22:13:49', 1),
(174, 1, 3, 'test', '2018-03-30 22:14:18', 1),
(175, 1, 2, 'test', '2018-03-30 22:14:23', 1),
(176, 2, 1, 'test', '2018-03-30 22:19:00', 1),
(177, 2, 3, 'test', '2018-03-30 22:19:06', 1),
(178, 1, 3, 'test', '2018-03-30 22:20:57', 1),
(179, 2, 3, 'test', '2018-03-30 22:21:17', 1),
(180, 2, 3, 'test', '2018-03-30 22:22:58', 1),
(181, 2, 3, 'test', '2018-03-30 22:23:07', 1),
(182, 1, 3, 'test', '2018-03-30 22:23:38', 1),
(183, 1, 3, 'test', '2018-03-30 22:23:50', 1),
(184, 2, 3, 'test', '2018-03-30 22:24:01', 1),
(185, 2, 3, 'test', '2018-03-30 22:24:19', 1),
(186, 1, 3, 'test', '2018-03-30 22:24:47', 1),
(187, 1, 3, 'test', '2018-03-30 22:25:02', 1),
(188, 1, 3, 'test', '2018-03-30 22:25:38', 1),
(189, 1, 3, 'test', '2018-03-30 22:25:38', 1),
(190, 1, 3, 'test', '2018-03-30 22:25:38', 1),
(191, 1, 3, 'test', '2018-03-30 22:25:47', 1),
(192, 1, 3, 'te', '2018-03-30 22:26:12', 1),
(193, 1, 3, 's', '2018-03-30 22:26:13', 1),
(194, 1, 3, 's', '2018-03-30 22:26:13', 1),
(195, 1, 3, 's', '2018-03-30 22:26:13', 1),
(196, 1, 3, 's', '2018-03-30 22:26:14', 1),
(197, 1, 3, 's', '2018-03-30 22:26:14', 1),
(198, 1, 3, 's', '2018-03-30 22:26:14', 1),
(199, 1, 3, 's', '2018-03-30 22:26:14', 1),
(200, 1, 3, 's', '2018-03-30 22:26:14', 1),
(201, 1, 3, 's', '2018-03-30 22:26:14', 1),
(202, 1, 3, 's', '2018-03-30 22:26:14', 1),
(203, 1, 3, '', '2018-03-30 22:26:14', 1),
(204, 1, 3, 's', '2018-03-30 22:26:15', 1),
(205, 1, 3, 's', '2018-03-30 22:26:15', 1),
(206, 1, 3, 's', '2018-03-30 22:26:15', 1),
(207, 1, 3, '', '2018-03-30 22:26:15', 1),
(208, 2, 3, 'test', '2018-03-30 22:39:00', 1),
(209, 2, 3, 'test', '2018-03-30 22:39:42', 1),
(210, 2, 3, 'test', '2018-03-30 22:39:57', 1),
(211, 2, 3, 'test', '2018-03-30 22:39:57', 1),
(212, 2, 3, 'test', '2018-03-30 22:39:57', 1),
(213, 1, 3, 'test', '2018-03-30 22:40:10', 1),
(214, 1, 3, 'test', '2018-03-30 22:40:28', 1),
(215, 1, 3, 'test', '2018-03-30 22:40:28', 1),
(216, 1, 3, 'test', '2018-03-30 22:40:28', 1),
(217, 1, 3, 'test', '2018-03-30 22:40:44', 1),
(218, 1, 3, 'test', '2018-03-30 22:40:44', 1),
(219, 1, 3, 'test', '2018-03-30 22:40:44', 1),
(220, 1, 3, 'test', '2018-03-30 22:40:44', 1),
(221, 3, 1, 'test', '2018-03-30 22:50:01', 1),
(222, 3, 2, 'test', '2018-03-30 22:50:06', 1),
(223, 1, 3, 'test', '2018-03-30 22:53:53', 1),
(224, 1, 2, 'test', '2018-03-30 22:53:59', 1),
(225, 1, 3, 'test', '2018-03-30 22:55:27', 1),
(226, 1, 3, 'test', '2018-03-30 22:55:45', 1),
(227, 1, 3, 'test', '2018-03-30 22:55:45', 1),
(228, 1, 3, 'test', '2018-03-30 22:55:45', 1),
(229, 1, 2, 'test', '2018-03-30 22:56:02', 1),
(230, 1, 2, 'test', '2018-03-30 22:56:10', 1),
(231, 1, 2, 'test', '2018-03-30 22:56:10', 1),
(232, 1, 2, 'test', '2018-03-31 13:37:34', 1),
(233, 1, 2, 'test', '2018-03-31 13:45:14', 1),
(234, 1, 2, 'test', '2018-03-31 13:45:16', 1),
(235, 1, 2, 'test', '2018-03-31 13:45:17', 1),
(236, 3, 2, 'test', '2018-03-31 13:45:40', 1),
(237, 3, 2, 'test', '2018-03-31 13:45:41', 1),
(238, 3, 2, 'test', '2018-03-31 13:45:42', 1),
(239, 3, 2, 'test', '2018-03-31 13:45:43', 1),
(240, 3, 2, 'test', '2018-03-31 13:45:45', 1),
(241, 2, 1, 'test', '2018-03-31 13:51:55', 1),
(242, 2, 1, 'test', '2018-03-31 13:51:56', 1),
(243, 2, 1, 'test', '2018-03-31 13:51:57', 1),
(244, 2, 3, 'test', '2018-03-31 13:52:04', 1),
(245, 3, 1, 'test', '2018-03-31 13:58:46', 1),
(246, 3, 1, 'test', '2018-03-31 13:58:48', 1),
(247, 3, 1, 'test', '2018-03-31 13:58:49', 1),
(248, 3, 1, 'test', '2018-03-31 13:58:50', 1),
(249, 3, 2, 'test', '2018-03-31 13:58:56', 1),
(250, 1, 2, 'test', '2018-04-18 10:53:12', 1),
(251, 2, 1, 'test tesst', '2018-04-18 10:53:29', 1),
(252, 1, 2, 'asdnbjasdjiasd', '2018-04-18 10:53:41', 1),
(253, 2, 3, 'Witaj', '2018-04-18 11:48:05', 0),
(254, 2, 1, 'Witaj', '2018-04-18 11:48:26', 1),
(255, 1, 2, 'Dzie? D', '2018-04-18 11:48:39', 1),
(256, 1, 2, '?', '2018-04-18 11:48:43', 1),
(257, 2, 1, 'dzień', '2018-04-18 14:53:22', 1),
(258, 2, 1, 'ęóąśłżźćń', '2018-04-18 14:53:44', 1),
(259, 1, 2, 'gg', '2018-04-25 11:44:59', 1),
(260, 1, 2, 'ęą', '2018-04-25 11:45:24', 1),
(261, 2, 1, 'gh', '2018-04-25 11:45:40', 1),
(262, 1, 2, 'hgg', '2018-04-25 11:46:06', 1),
(263, 2, 1, 'dobry ąłźN', '2018-04-25 11:46:18', 1),
(264, 1, 3, 'a', '2018-04-25 11:51:21', 1),
(265, 1, 3, 'fluk tam jadziem', '2018-04-25 11:54:58', 1),
(266, 3, 1, 'jooo', '2018-04-25 11:57:01', 1),
(267, 3, 1, 'jooo Halina', '2018-04-25 12:04:11', 1),
(268, 3, 1, 'test', '2018-04-25 12:10:54', 1),
(269, 3, 1, '1test', '2018-04-25 12:11:58', 0),
(270, 3, 1, 'test', '2018-04-25 12:13:00', 0),
(271, 3, 1, 'uhbhububhub', '2018-04-25 12:19:02', 0),
(272, 2, 1, 'dobry dobry ', '2018-05-08 16:48:43', 1),
(273, 1, 2, 'elo', '2018-05-08 16:50:11', 1),
(274, 2, 1, 'Dzień dobry', '2018-05-08 17:01:26', 1),
(275, 1, 2, 'ddd', '2018-05-08 17:02:06', 1),
(276, 2, 1, 'dasdasd', '2018-05-08 17:02:13', 1),
(277, 2, 1, 'asdasd', '2018-05-08 17:02:54', 1),
(278, 1, 2, 'dzi', '2018-05-08 17:03:29', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `kontakty`
--
ALTER TABLE `kontakty`
 ADD PRIMARY KEY (`idKontaktu`);

--
-- Indexes for table `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
 ADD PRIMARY KEY (`idUzytkownika`);

--
-- Indexes for table `wiadomosci`
--
ALTER TABLE `wiadomosci`
 ADD PRIMARY KEY (`idWiadomosci`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `kontakty`
--
ALTER TABLE `kontakty`
MODIFY `idKontaktu` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
MODIFY `idUzytkownika` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
MODIFY `idWiadomosci` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=279;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
