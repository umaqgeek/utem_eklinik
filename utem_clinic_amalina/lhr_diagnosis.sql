-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2015 at 03:52 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `servercis`
--

-- --------------------------------------------------------

--
-- Table structure for table `lhr_diagnosis`
--

CREATE TABLE IF NOT EXISTS `lhr_diagnosis` (
  `PMI_no` varchar(30) NOT NULL,
  `hfc_cd` varchar(30) NOT NULL,
  `Episode_date` date NOT NULL,
  `encounterdate` date NOT NULL,
  `txnDate` date NOT NULL,
  `DiagnosisCd` varchar(10) NOT NULL,
  `Diagnosis` varchar(10) NOT NULL,
  `DiagnosisDesc` varchar(200) DEFAULT NULL,
  `NATIONAL_ID_NO` varchar(45) NOT NULL,
  `PERSON_ID_NO` varchar(45) NOT NULL,
  `PERSON_STATUS` varchar(45) NOT NULL,
  `LOCATION_CODE` varchar(45) NOT NULL,
  PRIMARY KEY (`hfc_cd`,`Episode_date`,`encounterdate`,`txnDate`,`DiagnosisCd`,`Diagnosis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lhr_diagnosis`
--

INSERT INTO `lhr_diagnosis` (`PMI_no`, `hfc_cd`, `Episode_date`, `encounterdate`, `txnDate`, `DiagnosisCd`, `Diagnosis`, `DiagnosisDesc`, `NATIONAL_ID_NO`, `PERSON_ID_NO`, `PERSON_STATUS`, `LOCATION_CODE`) VALUES
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A15A17', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A15A170', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A15A171', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A15A178', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A15A179', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A20A207', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A77', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A770', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A771', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A772', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A773', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A778', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A75A779', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A80', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A800', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A801', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A802', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A803', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A804', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A80A809', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B08', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B080', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B081', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B082', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B083', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B084', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B00B088', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B46', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B460', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B461', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B462', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B463', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B464', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B468', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B35B469', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B79', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B80', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B81', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B810', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B811', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B812', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B813', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B65B814', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B960', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B961', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B962', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B963', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B964', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B965', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B97', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B970', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B971', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B972', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B973', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B974', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01B95B975', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C16', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C160', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C161', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C162', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C163', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C164', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C15C165', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C37', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C38', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C380', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C381', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C382', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C30C383', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C41', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C410', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C411', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C412', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C413', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C40C414', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C50C501', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C50C502', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C50C508', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C50C509', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C51C51', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C51C510', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C76', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C760', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C761', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C762', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C763', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02C76C764', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00D040', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00D041', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00D042', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00D043', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00D044', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D00DD04', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D22', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D220', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D221', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D222', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D223', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D224', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D225', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D10D226', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D382', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D383', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D384', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D385', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D386', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D48', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D480', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D483', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '02D37D484', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '06G80G81', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H15H17', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H15H170', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H15H171', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H15H178', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H15H179', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H25H27', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H25H270', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H25H271', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H25H278', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H25H279', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H43H43', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H43H430', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H43H431', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H43H432', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H43H433', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H55H599', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I30', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I300', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I301', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKM'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I308', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I309', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I30I39', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I60I68', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I60I680', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I60I681', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I60I682', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I60I688', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I80I88', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I80I880', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I80I881', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I80I888', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '09I80I889', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FPTT'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '17Q10Q110', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '11K55K621', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FKP'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '14N70N711', 'tp001', 'Pestilential fever ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '15O80O839', 'tp001', 'Pestilential fever ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '21Z20Z206', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FKE'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '22U80U80', 'tp001', 'Pestilential fever ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-03', '2014-03-03', '2014-03-03', '22U80U899', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-04', '2014-03-04', '2014-03-04', '01A00A01', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FKEKK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-04', '2014-03-04', '2014-03-04', '18R10R18', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '01A00A01', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '01A00A010', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '03D70D748', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '04E65E669', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '05F20F239', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-03-06', '2014-03-06', '2014-03-06', '12L60L688', 'tp002', 'Yellow fever vaccination ', '891031065213', 'M031210009', 'L', 'FTK'),
('PMS10015', 'Klinik UTeM Induk', '2014-04-03', '2014-04-03', '2014-04-03', '01A00A01', 'tp002', 'Typhoid and paratyphoid fevers', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-04-03', '2014-04-03', '2014-04-03', '08H90H948', 'tp002', 'Typhoid and paratyphoid fevers', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Klinik UTeM Induk', '2014-04-03', '2014-04-03', '2014-04-03', '16P75P78', 'tp002', 'Typhoid and paratyphoid fevers', '891031065213', 'M031210009', 'L', 'FTMK'),
('007172', 'Klinik UTeM Induk', '2014-11-06', '2014-11-06', '2014-11-06', '13M45M489', 'tp001', 'Dengue fever [classical dengue]', '660206015261', '00717', 'L', 'FTMK'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '02C00C020', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FKM'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '09I30I319', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FPTT'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '10J60J64', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FTMK'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '18R50R509', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FTMK'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '19S30S341', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FTMK'),
('8910310652130', 'Klinik UTeM Induk', '2015-07-01', '2015-07-01', '2015-07-01', '20V30V380', 'tp001', 'Fever, unspecified', '891031065213', 'M031210009', 'L', 'FTMK');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
