-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2015 at 12:45 PM
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
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '01A20A207', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '06G80G81', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
('PMS10015', 'Hospital Melaka', '2013-07-19', '2013-07-19', '2013-07-19', '07H55H599', 'tp001', 'At risk of heart disease ', '891031065213', 'M031210009', 'L', 'FTMK'),
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
