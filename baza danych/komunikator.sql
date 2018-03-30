-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 30 Mar 2018, 19:17
-- Wersja serwera: 10.1.31-MariaDB
-- Wersja PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `komunikator`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kontakty`
--

CREATE TABLE `kontakty` (
  `idKontaktu` int(3) NOT NULL,
  `idUzytkownika1` int(5) NOT NULL,
  `idUzytkownika2` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `kontakty`
--

INSERT INTO `kontakty` (`idKontaktu`, `idUzytkownika1`, `idUzytkownika2`) VALUES
(1, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `idUzytkownika` int(5) NOT NULL,
  `login` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`idUzytkownika`, `login`) VALUES
(1, 'uzytkownik1'),
(2, 'uzytkownik2'),
(3, 'uzytkownik3');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wiadomosci`
--

CREATE TABLE `wiadomosci` (
  `idWiadomosci` int(10) NOT NULL,
  `idWysylajacego` int(6) NOT NULL,
  `idAdresata` int(6) NOT NULL,
  `tresc` varchar(250) DEFAULT NULL,
  `data` datetime NOT NULL,
  `wyswietlona` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `wiadomosci`
--

INSERT INTO `wiadomosci` (`idWiadomosci`, `idWysylajacego`, `idAdresata`, `tresc`, `data`, `wyswietlona`) VALUES
(3, 1, 2, 'TEST', '2018-03-26 18:14:05', 0),
(4, 1, 2, 'TEST', '2018-03-26 18:14:28', 0),
(5, 1, 2, 'TEST', '2018-03-26 18:15:17', 0),
(6, 1, 2, 'TEST', '2018-03-26 18:24:32', 0),
(7, 1, 2, 'test', '2018-03-26 18:35:12', 0),
(8, 1, 2, 'TextBox', '2018-03-26 18:36:08', 0),
(9, 1, 2, 'TextBox', '2018-03-26 18:37:45', 0),
(10, 1, 2, 'TextBox', '2018-03-26 18:44:35', 0),
(11, 1, 2, '1', '2018-03-26 18:47:21', 0),
(12, 1, 2, '2', '2018-03-26 18:47:34', 0),
(13, 1, 2, 'TEST', '2018-03-26 18:49:31', 0),
(14, 1, 2, 'gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg', '2018-03-26 19:09:49', 0),
(15, 1, 2, 'fgdfgdfg', '2018-03-26 20:14:38', 0),
(16, 1, 2, 'fgddfg', '2018-03-26 20:14:43', 0),
(17, 1, 2, 'hsked', '2018-03-26 20:15:13', 0),
(18, 1, 2, '', '2018-03-26 20:15:14', 0),
(19, 1, 2, 'fdadfddasf', '2018-03-26 20:15:16', 0),
(20, 1, 2, 'fdf', '2018-03-26 20:15:20', 0),
(21, 1, 2, 'l;lk;kl;', '2018-03-26 20:15:23', 0),
(22, 1, 2, 'Hello', '2018-03-26 20:18:55', 0),
(23, 1, 2, 'ZEA', '2018-03-26 20:51:05', 0),
(24, 1, 2, 'al', '2018-03-26 20:51:30', 0),
(25, 1, 2, 'asc', '2018-03-26 21:00:20', 0),
(26, 1, 2, '???', '2018-03-26 21:02:36', 0),
(27, 1, 2, 'hghg', '2018-03-26 21:02:51', 0),
(28, 1, 2, 'fgdfgf', '2018-03-26 21:04:01', 0),
(29, 1, 2, 'gytry', '2018-03-26 21:04:16', 0),
(30, 1, 2, 'ghghgf', '2018-03-26 21:06:33', 0),
(31, 1, 2, 'TEST', '2018-03-26 21:07:17', 0),
(32, 1, 2, 'aaaaa', '2018-03-26 21:08:02', 0),
(33, 1, 2, 'aaa', '2018-03-26 21:09:52', 0),
(34, 1, 2, '???', '2018-03-26 21:12:06', 0),
(35, 1, 2, 'test', '2018-03-29 12:37:39', 0),
(36, 1, 2, 'test2222', '2018-03-29 12:37:55', 0),
(37, 1, 2, 'tetas', '2018-03-29 12:37:58', 0),
(38, 1, 2, 'teate', '2018-03-29 12:38:00', 0),
(39, 1, 2, 'fgfh', '2018-03-29 12:42:12', 0),
(40, 1, 2, 'test', '2018-03-29 12:52:50', 0),
(41, 1, 2, 'test', '2018-03-29 12:55:37', 0),
(42, 1, 2, 'test', '2018-03-29 12:56:43', 0),
(43, 1, 2, 'grta', '2018-03-29 13:02:30', 0),
(44, 1, 2, 'test', '2018-03-29 13:22:49', 0),
(45, 1, 2, 'test', '2018-03-29 13:24:13', 0),
(46, 1, 2, 'test', '2018-03-29 13:24:14', 0),
(47, 1, 2, 'test', '2018-03-29 13:24:26', 0),
(48, 1, 2, 'test', '2018-03-29 13:25:49', 0),
(49, 1, 2, 'glsdhglsdhgsdhgldshgdklsgskdgkgjkg', '2018-03-29 13:26:00', 0),
(50, 1, 2, 'gdjskgjdkgjdkgjkgjfkjgkfjgkfjgkfjgkfjgkfgjkfjgkfgjfgjsfkgjsfg', '2018-03-29 13:26:05', 0),
(51, 1, 2, 'test', '2018-03-29 13:32:31', 0),
(52, 1, 2, 'test', '2018-03-29 13:33:06', 0),
(53, 1, 2, 'test', '2018-03-29 13:33:59', 0),
(54, 1, 2, 'TEST', '2018-03-29 13:42:44', 0),
(55, 1, 2, 'TEST', '2018-03-29 13:42:44', 0),
(56, 1, 2, 'test', '2018-03-29 13:44:27', 0),
(57, 1, 2, 'test', '2018-03-29 13:45:14', 0),
(58, 1, 2, 'test', '2018-03-29 13:46:57', 0),
(59, 1, 2, 'fhdgjhsdfg', '2018-03-29 13:47:22', 0),
(60, 1, 2, 'Lorem ipsum', '2018-03-29 13:47:55', 0),
(61, 1, 2, 'sfafjakkadgj ahdfsf isdjaisdasidj fdijg', '2018-03-29 13:48:03', 0),
(62, 2, 1, 'test', '2018-03-29 13:52:14', 1),
(63, 2, 1, 'test2', '2018-03-29 13:52:16', 1),
(64, 2, 1, 'test3', '2018-03-29 13:52:19', 1),
(65, 2, 1, 'test', '2018-03-29 13:52:21', 1),
(66, 2, 1, 'tesr3', '2018-03-29 13:52:23', 1),
(67, 1, 2, 'test', '2018-03-29 13:52:44', 0),
(68, 1, 2, 'test', '2018-03-29 13:52:46', 0),
(69, 1, 2, 'test', '2018-03-29 13:52:47', 0),
(70, 1, 2, 'test3', '2018-03-29 13:52:49', 0),
(71, 1, 2, 'TEST', '2018-03-29 14:49:00', 0),
(72, 1, 2, 'TEST', '2018-03-29 14:49:00', 0),
(73, 1, 2, 'TEST', '2018-03-29 15:07:39', 0),
(74, 1, 2, 'TEST', '2018-03-29 15:07:39', 0),
(75, 1, 2, 'test', '2018-03-29 15:19:42', 0),
(76, 1, 2, 'test', '2018-03-29 15:19:58', 0),
(77, 1, 2, 'test', '2018-03-29 15:20:05', 0),
(78, 1, 2, 'test', '2018-03-29 16:40:38', 0),
(79, 1, 2, 'TEST', '2018-03-29 17:50:59', 0),
(80, 1, 2, 'TEST', '2018-03-29 17:50:59', 0),
(81, 1, 2, 'TEST', '2018-03-29 17:50:59', 0),
(82, 1, 2, 'TEST', '2018-03-29 17:52:01', 0),
(83, 1, 2, 'TEST', '2018-03-29 17:52:01', 0),
(84, 1, 2, 'TEST', '2018-03-29 17:52:01', 0),
(85, 1, 2, 'test', '2018-03-29 18:12:43', 0),
(86, 2, 1, 'test test test', '2018-03-29 18:13:46', 1),
(87, 1, 2, 'test', '2018-03-29 18:18:20', 0),
(88, 2, 1, 'test test test', '2018-03-29 18:19:31', 1),
(89, 1, 2, 'test', '2018-03-29 18:22:46', 0),
(90, 2, 1, 'test test test', '2018-03-29 18:23:08', 1),
(91, 1, 2, 'test', '2018-03-29 18:59:56', 0),
(92, 2, 1, 'test test test', '2018-03-29 19:00:25', 1),
(93, 1, 2, 'test', '2018-03-29 19:14:05', 0),
(94, 2, 1, 'test test test', '2018-03-29 19:14:24', 1),
(95, 1, 2, 'test', '2018-03-29 20:23:37', 0),
(96, 1, 2, 'wiadomo?? testowa', '2018-03-29 20:24:43', 0),
(97, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:25:06', 1),
(98, 1, 2, 'wiadomosc testowa', '2018-03-29 20:26:38', 0),
(99, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:27:06', 1),
(100, 1, 2, 'test', '2018-03-29 20:27:53', 0),
(101, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:28:25', 1),
(102, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:29:01', 1),
(103, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:29:35', 1),
(104, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:30:13', 1),
(105, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:32:35', 1),
(106, 2, 1, 'wiadomo?? testowa', '2018-03-29 20:33:27', 1),
(107, 1, 2, 'test', '2018-03-29 20:33:40', 0),
(108, 1, 2, 'TEST', '2018-03-30 15:18:44', 0),
(109, 1, 2, 'TEST', '2018-03-30 15:18:44', 0),
(110, 1, 2, 'TEST', '2018-03-30 15:18:44', 0),
(111, 1, 3, 'test', '2018-03-30 17:51:22', 0),
(112, 1, 3, 'test', '2018-03-30 17:54:28', 0),
(113, 1, 2, 'TEST', '2018-03-30 18:18:12', 0),
(114, 1, 2, 'TEST', '2018-03-30 18:18:12', 0),
(115, 1, 2, 'TEST', '2018-03-30 18:18:12', 0),
(116, 1, 2, 'TEST', '2018-03-30 18:18:12', 0),
(117, 2, 1, 'fgf', '2018-03-30 18:26:31', 0),
(118, 3, 1, 'test', '2018-03-30 18:57:02', 0),
(119, 2, 1, 'test', '2018-03-30 18:57:15', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `kontakty`
--
ALTER TABLE `kontakty`
  ADD PRIMARY KEY (`idKontaktu`);

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`idUzytkownika`);

--
-- Indeksy dla tabeli `wiadomosci`
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
  MODIFY `idKontaktu` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `idUzytkownika` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  MODIFY `idWiadomosci` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
