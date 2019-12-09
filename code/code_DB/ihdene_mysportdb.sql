-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: mysql-ihdene.alwaysdata.net
-- Generation Time: Dec 09, 2019 at 12:02 PM
-- Server version: 10.3.17-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ihdene_mysportdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_annonce`
--

CREATE TABLE `t_annonce` (
  `id_annonce` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `jour_debut` date NOT NULL,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL,
  `jour_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_reserve`
--

CREATE TABLE `t_reserve` (
  `id_reserve` int(11) NOT NULL,
  `id_annonce` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nombre_reserve` int(11) NOT NULL,
  `jour_reserve` date NOT NULL,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_terrain`
--

CREATE TABLE `t_terrain` (
  `id_terrain` int(11) NOT NULL,
  `id_annonce` int(11) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `code_postal` varchar(20) NOT NULL,
  `capacite` int(11) NOT NULL,
  `type_sport` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `id_user` int(11) NOT NULL,
  `user_first_name` varchar(20) NOT NULL,
  `user_last_name` varchar(20) NOT NULL,
  `user_mail` varchar(50) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_tel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_annonce`
--
ALTER TABLE `t_annonce`
  ADD PRIMARY KEY (`id_annonce`) USING BTREE,
  ADD KEY `t_annonce_ibfk_1` (`id_user`);

--
-- Indexes for table `t_reserve`
--
ALTER TABLE `t_reserve`
  ADD PRIMARY KEY (`id_reserve`) USING BTREE,
  ADD KEY `user_constraint` (`id_user`) USING BTREE,
  ADD KEY `annonce_constraint` (`id_annonce`);

--
-- Indexes for table `t_terrain`
--
ALTER TABLE `t_terrain`
  ADD PRIMARY KEY (`id_terrain`),
  ADD KEY `annonce_constrainte` (`id_annonce`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_annonce`
--
ALTER TABLE `t_annonce`
  MODIFY `id_annonce` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_reserve`
--
ALTER TABLE `t_reserve`
  MODIFY `id_reserve` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_terrain`
--
ALTER TABLE `t_terrain`
  MODIFY `id_terrain` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_annonce`
--
ALTER TABLE `t_annonce`
  ADD CONSTRAINT `t_annonce_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `t_reserve`
--
ALTER TABLE `t_reserve`
  ADD CONSTRAINT `annonce_constraint` FOREIGN KEY (`id_annonce`) REFERENCES `t_annonce` (`id_annonce`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_constraint` FOREIGN KEY (`id_user`) REFERENCES `t_user` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `t_terrain`
--
ALTER TABLE `t_terrain`
  ADD CONSTRAINT `annonce_constrainte` FOREIGN KEY (`id_annonce`) REFERENCES `t_annonce` (`id_annonce`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
