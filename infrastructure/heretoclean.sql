-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 06, 2019 at 12:26 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `heretoclean`
--

-- --------------------------------------------------------

--
-- Table structure for table `association`
--


DROP TABLE IF EXISTS `association`;
CREATE TABLE IF NOT EXISTS `association` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number_rna` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `association`
--

INSERT INTO `association` (`id`, `description`, `email`, `name`, `number_rna`) VALUES
(1, 'Description 1', 'asso@gmail.com', 'Asso 1', 15788),
(2, 'Description 2', 'asso2@gmail.com', 'Asso 2', 5987),
(3, 'Description 3', 'asso3@gmail.com', 'Asso 3', 98756),
(4, 'Description 4', 'asso4@gmail.com', 'Asso 4', 125478),
(5, 'Description 5', 'asso5@gmail.com', 'Asso 5', 21578);

-- --------------------------------------------------------

--
-- Table structure for table `association_volunteer`
--

DROP TABLE IF EXISTS `association_volunteer`;
CREATE TABLE IF NOT EXISTS `association_volunteer` (
  `volunteer_id` bigint(20) NOT NULL,
  `association_id` bigint(20) NOT NULL,
  PRIMARY KEY (`volunteer_id`,`association_id`),
  KEY `FKt38ptx5ht5ige6bfwsqyc7kq1` (`association_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `command`
--

DROP TABLE IF EXISTS `command`;
CREATE TABLE IF NOT EXISTS `command` (
  `id` bigint(20) NOT NULL,
  `date_command` datetime NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `volunteer_id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK88s2idm90tb8dpe0fqnd6hecg` (`volunteer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `command_compo`
--

DROP TABLE IF EXISTS `command_compo`;
CREATE TABLE IF NOT EXISTS `command_compo` (
  `quantity` int(11) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `command_id` bigint(20) NOT NULL,
  PRIMARY KEY (`product_id`,`command_id`),
  KEY `FKmbqrst3noif48mwo0hgee4bul` (`command_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `command_compo_command`
--

DROP TABLE IF EXISTS `command_compo_command`;
CREATE TABLE IF NOT EXISTS `command_compo_command` (
  `command_id` bigint(20) NOT NULL,
  `compo` int(11) DEFAULT NULL,
  `compo_command_key` bigint(20) NOT NULL,
  PRIMARY KEY (`command_id`,`compo_command_key`),
  KEY `FKsiw7qf202pwm0sj0xth7kngaa` (`compo_command_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `command_products`
--

DROP TABLE IF EXISTS `command_products`;
CREATE TABLE IF NOT EXISTS `command_products` (
  `commands_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL,
  KEY `FK18m81slt86eikab9245keg19h` (`products_id`),
  KEY `FKd8d65cdkpjnvsqiu4htnpkd` (`commands_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` bigint(20) NOT NULL,
  `begin_date` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `end_date` datetime NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url_image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `begin_date`, `description`, `end_date`, `location`, `name`, `url_image`) VALUES
(1, '2019-07-10 00:00:00', 'This is the first description', '2019-07-08 00:00:00', 'Paris', 'First Event', 'https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png'),
(2, '2019-07-23 00:00:00', 'This is the second event', '2019-07-24 00:00:00', 'Paris', 'Second Event', 'https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png');

-- --------------------------------------------------------

--
-- Table structure for table `event_volunteers`
--

DROP TABLE IF EXISTS `event_volunteers`;
CREATE TABLE IF NOT EXISTS `event_volunteers` (
  `events_id` bigint(20) NOT NULL,
  `volunteers_id` bigint(20) NOT NULL,
  KEY `FK183n1vbbwvtonlr75s3s91hpc` (`volunteers_id`),
  KEY `FKesvw13rt501cse3a5lne8h9go` (`events_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
CREATE TABLE IF NOT EXISTS `gift` (
  `id` bigint(20) NOT NULL,
  `amount` float NOT NULL,
  `association_id` bigint(20) DEFAULT NULL,
  `volunteer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKasilf3mye4ecwev3mthly2kro` (`association_id`),
  KEY `FKtbb7qpsg8u5esvkjwnvpnffht` (`volunteer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2),
(2),
(2),
(2),
(2),
(2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `association_id` bigint(20) NOT NULL,
  `command_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkbswvstdcd2ulpqpshje6ipx8` (`association_id`),
  KEY `FK8dsmafb624b1usthp3jfpq3i4` (`command_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `volunteer`
--

DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE IF NOT EXISTS `volunteer` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `birthday` datetime NOT NULL,
  `city` varchar(255) NOT NULL,
  `city_code` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h0jd9uwghm4wei8h8yoh5nm5x` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `volunteer`
--

INSERT INTO `volunteer` (`id`, `address`, `birthday`, `city`, `city_code`, `email`, `first_name`, `last_name`) VALUES
(1, 'Adresse', '2019-01-02 00:05:00', 'Adresse', 95722, 'test@gmail.com', 'Test', 'Test');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
