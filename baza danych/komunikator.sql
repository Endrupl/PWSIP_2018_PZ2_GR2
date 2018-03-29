-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 29 Mar 2018, 14:43
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
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `wiadomosci`
--

INSERT INTO `wiadomosci` (`idWiadomosci`, `idWysylajacego`, `idAdresata`, `tresc`, `data`) VALUES
(1, 1, 1, 'asd', '2018-03-24 00:00:00'),
(2, 1, 1, 'asd', '2018-03-24 00:00:00'),
(3, 1, 2, 'TEST', '2018-03-26 18:14:05'),
(4, 1, 2, 'TEST', '2018-03-26 18:14:28'),
(5, 1, 2, 'TEST', '2018-03-26 18:15:17'),
(6, 1, 2, 'TEST', '2018-03-26 18:24:32'),
(7, 1, 2, 'test', '2018-03-26 18:35:12'),
(8, 1, 2, 'TextBox', '2018-03-26 18:36:08'),
(9, 1, 2, 'TextBox', '2018-03-26 18:37:45'),
(10, 1, 2, 'TextBox', '2018-03-26 18:44:35'),
(11, 1, 2, '1', '2018-03-26 18:47:21'),
(12, 1, 2, '2', '2018-03-26 18:47:34'),
(13, 1, 2, 'TEST', '2018-03-26 18:49:31'),
(14, 1, 2, 'gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg', '2018-03-26 19:09:49'),
(15, 1, 2, 'fgdfgdfg', '2018-03-26 20:14:38'),
(16, 1, 2, 'fgddfg', '2018-03-26 20:14:43'),
(17, 1, 2, 'hsked', '2018-03-26 20:15:13'),
(18, 1, 2, '', '2018-03-26 20:15:14'),
(19, 1, 2, 'fdadfddasf', '2018-03-26 20:15:16'),
(20, 1, 2, 'fdf', '2018-03-26 20:15:20'),
(21, 1, 2, 'l;lk;kl;', '2018-03-26 20:15:23'),
(22, 1, 2, 'Hello', '2018-03-26 20:18:55'),
(23, 1, 2, 'ZEA', '2018-03-26 20:51:05'),
(24, 1, 2, 'al', '2018-03-26 20:51:30'),
(25, 1, 2, 'asc', '2018-03-26 21:00:20'),
(26, 1, 2, '???', '2018-03-26 21:02:36'),
(27, 1, 2, 'hghg', '2018-03-26 21:02:51'),
(28, 1, 2, 'fgdfgf', '2018-03-26 21:04:01'),
(29, 1, 2, 'gytry', '2018-03-26 21:04:16'),
(30, 1, 2, 'ghghgf', '2018-03-26 21:06:33'),
(31, 1, 2, 'TEST', '2018-03-26 21:07:17'),
(32, 1, 2, 'aaaaa', '2018-03-26 21:08:02'),
(33, 1, 2, 'aaa', '2018-03-26 21:09:52'),
(34, 1, 2, '???', '2018-03-26 21:12:06'),
(35, 1, 2, 'test', '2018-03-29 12:37:39'),
(36, 1, 2, 'test2222', '2018-03-29 12:37:55'),
(37, 1, 2, 'tetas', '2018-03-29 12:37:58'),
(38, 1, 2, 'teate', '2018-03-29 12:38:00'),
(39, 1, 2, 'fgfh', '2018-03-29 12:42:12'),
(40, 1, 2, 'test', '2018-03-29 12:52:50'),
(41, 1, 2, 'test', '2018-03-29 12:55:37'),
(42, 1, 2, 'test', '2018-03-29 12:56:43'),
(43, 1, 2, 'grta', '2018-03-29 13:02:30'),
(44, 1, 2, 'test', '2018-03-29 13:22:49'),
(45, 1, 2, 'test', '2018-03-29 13:24:13'),
(46, 1, 2, 'test', '2018-03-29 13:24:14'),
(47, 1, 2, 'test', '2018-03-29 13:24:26'),
(48, 1, 2, 'test', '2018-03-29 13:25:49'),
(49, 1, 2, 'glsdhglsdhgsdhgldshgdklsgskdgkgjkg', '2018-03-29 13:26:00'),
(50, 1, 2, 'gdjskgjdkgjdkgjkgjfkjgkfjgkfjgkfjgkfjgkfgjkfjgkfgjfgjsfkgjsfg', '2018-03-29 13:26:05'),
(51, 1, 2, 'test', '2018-03-29 13:32:31'),
(52, 1, 2, 'test', '2018-03-29 13:33:06'),
(53, 1, 2, 'test', '2018-03-29 13:33:59'),
(54, 1, 2, 'TEST', '2018-03-29 13:42:44'),
(55, 1, 2, 'TEST', '2018-03-29 13:42:44'),
(56, 1, 2, 'test', '2018-03-29 13:44:27'),
(57, 1, 2, 'test', '2018-03-29 13:45:14'),
(58, 1, 2, 'test', '2018-03-29 13:46:57'),
(59, 1, 2, 'fhdgjhsdfg', '2018-03-29 13:47:22'),
(60, 1, 2, 'Lorem ipsum', '2018-03-29 13:47:55'),
(61, 1, 2, 'sfafjakkadgj ahdfsf isdjaisdasidj fdijg', '2018-03-29 13:48:03'),
(62, 2, 1, 'test', '2018-03-29 13:52:14'),
(63, 2, 1, 'test2', '2018-03-29 13:52:16'),
(64, 2, 1, 'test3', '2018-03-29 13:52:19'),
(65, 2, 1, 'test', '2018-03-29 13:52:21'),
(66, 2, 1, 'tesr3', '2018-03-29 13:52:23'),
(67, 1, 2, 'test', '2018-03-29 13:52:44'),
(68, 1, 2, 'test', '2018-03-29 13:52:46'),
(69, 1, 2, 'test', '2018-03-29 13:52:47'),
(70, 1, 2, 'test3', '2018-03-29 13:52:49');

--
-- Indeksy dla zrzutów tabel
--

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
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `idUzytkownika` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `wiadomosci`
--
ALTER TABLE `wiadomosci`
  MODIFY `idWiadomosci` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
