-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2017 at 05:11 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perecords`
--

-- --------------------------------------------------------

--
-- Table structure for table `budget`
--

CREATE TABLE `budget` (
  `bdg_id` int(50) NOT NULL,
  `bdgp_id` int(50) NOT NULL,
  `bdgp_cate` varchar(15) NOT NULL,
  `bdgseasname` varchar(30) NOT NULL,
  `bdg_expected` int(10) NOT NULL,
  `bdg_start` date NOT NULL,
  `bdg_end` date NOT NULL,
  `bdg_status` varchar(15) NOT NULL,
  `bdg_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `budget`
--

INSERT INTO `budget` (`bdg_id`, `bdgp_id`, `bdgp_cate`, `bdgseasname`, `bdg_expected`, `bdg_start`, `bdg_end`, `bdg_status`, `bdg_regdate`) VALUES
(2, 1, 'plan', 'Preparation', 2000, '2017-07-31', '2017-08-01', 'active', '2017-05-24');

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `cont_id` int(50) NOT NULL,
  `cont_name` varchar(50) NOT NULL,
  `cont_nickname` varchar(50) NOT NULL,
  `cont_phone` int(12) NOT NULL,
  `cont_email` varchar(50) NOT NULL,
  `cont_location` varchar(50) NOT NULL,
  `cont_sex` varchar(10) NOT NULL,
  `status` varchar(30) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`cont_id`, `cont_name`, `cont_nickname`, `cont_phone`, `cont_email`, `cont_location`, `cont_sex`, `status`, `date`) VALUES
(1, 'Manzi Roger', 'Asua', 726183049, 'mnzroger@gmail.com', 'Nkoto', 'male', 'active', '2017-05-11'),
(3, 'Hakizimana Dieudonne', 'Didos', 780330338, 'didos@yahoo.fr', 'Murehe', 'male', 'active', '2017-05-12'),
(4, 'Kanyana Aline', 'Allen', 723546249, 'al@gmail.com', 'Gahanga', 'female', 'active', '2017-05-12'),
(6, 'Abayo Alice', 'Alyce', 788359630, 'neretseroger@yahoo.com', 'Kanombe', 'female', 'active', '2017-05-15');

-- --------------------------------------------------------

--
-- Table structure for table `lifestyles`
--

CREATE TABLE `lifestyles` (
  `lfs_id` int(50) NOT NULL,
  `lfs_title` varchar(50) NOT NULL,
  `lfs_descr` varchar(255) NOT NULL,
  `lfs_type` varchar(15) NOT NULL,
  `lfs_start` date NOT NULL,
  `lfs_end` date NOT NULL,
  `lfs_status` varchar(15) NOT NULL,
  `lfs_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lifestyles`
--

INSERT INTO `lifestyles` (`lfs_id`, `lfs_title`, `lfs_descr`, `lfs_type`, `lfs_start`, `lfs_end`, `lfs_status`, `lfs_regdate`) VALUES
(2, 'Learning Java', 'Leaning Java From Morning to noon at 08:00 to 17:00\nMonday-Friday', 'Daily Life', '2017-05-01', '2017-05-31', 'active', '2017-05-19');

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `md_id` int(50) NOT NULL,
  `md_nwname` varchar(30) NOT NULL,
  `md_username` varchar(30) NOT NULL,
  `md_phone` int(12) NOT NULL,
  `md_email` varchar(50) NOT NULL,
  `md_password` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`md_id`, `md_nwname`, `md_username`, `md_phone`, `md_email`, `md_password`, `status`) VALUES
(1, 'Facebook', 'Ashraf RUT', 730985357, 'rogerneretse@yahoo.com', '27091998', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `menu_id` int(50) NOT NULL,
  `menu_name` varchar(15) NOT NULL,
  `menu_status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menus`
--

INSERT INTO `menus` (`menu_id`, `menu_name`, `menu_status`) VALUES
(1, 'login', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `uid` int(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `pl_id` int(50) NOT NULL,
  `pl_title` varchar(50) NOT NULL,
  `pl_descr` varchar(200) NOT NULL,
  `pl_implementation` text NOT NULL,
  `pl_start` date NOT NULL,
  `pl_end` date NOT NULL,
  `pl_status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`pl_id`, `pl_title`, `pl_descr`, `pl_implementation`, `pl_start`, `pl_end`, `pl_status`) VALUES
(1, 'Attending TAS', 'TAS will take place on 31st July 2017', 'at KLab Rwanda 6th Floor Telecom House', '2017-07-31', '2017-08-03', 'waiting');

-- --------------------------------------------------------

--
-- Table structure for table `profit`
--

CREATE TABLE `profit` (
  `prof_id` int(50) NOT NULL,
  `profp_id` int(50) NOT NULL,
  `profp_cate` varchar(15) NOT NULL,
  `profdescr` varchar(35) NOT NULL,
  `prof_expected` int(10) NOT NULL,
  `prof_status` varchar(15) NOT NULL,
  `prof_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profit`
--

INSERT INTO `profit` (`prof_id`, `profp_id`, `profp_cate`, `profdescr`, `prof_expected`, `prof_status`, `prof_regdate`) VALUES
(1, 1, 'plan', 'get to know more about Market', 1000, 'active', '2017-05-24');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `pr_id` int(50) NOT NULL,
  `pr_title` varchar(50) NOT NULL,
  `pr_type` varchar(15) NOT NULL,
  `pr_platform` varchar(15) NOT NULL,
  `pr_descr` varchar(255) NOT NULL,
  `pr_start` date NOT NULL,
  `pr_end` date NOT NULL,
  `pr_status` varchar(15) NOT NULL,
  `pr_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`pr_id`, `pr_title`, `pr_type`, `pr_platform`, `pr_descr`, `pr_start`, `pr_end`, `pr_status`, `pr_regdate`) VALUES
(1, 'Libraries development', 'Website', 'Web', 'helps to simplify work in a web application development\nthose libraries are jslive,csslive,PHP and facilitate me to master \nJavascript,CSS,Php', '2017-06-15', '2017-08-31', 'waiting', '2017-06-14');

-- --------------------------------------------------------

--
-- Table structure for table `restricteds`
--

CREATE TABLE `restricteds` (
  `restrd_id` int(50) NOT NULL,
  `restrdmenu_id` int(50) NOT NULL,
  `restrd_restrid` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restricteds`
--

INSERT INTO `restricteds` (`restrd_id`, `restrdmenu_id`, `restrd_restrid`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `restricters`
--

CREATE TABLE `restricters` (
  `restr_id` int(50) NOT NULL,
  `restr_name` varchar(50) NOT NULL,
  `restr_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restricters`
--

INSERT INTO `restricters` (`restr_id`, `restr_name`, `restr_password`) VALUES
(1, 'RUTGER', 'asua2020');

-- --------------------------------------------------------

--
-- Table structure for table `source`
--

CREATE TABLE `source` (
  `src_id` int(50) NOT NULL,
  `srcp_id` int(50) NOT NULL,
  `srcp_cate` varchar(15) NOT NULL,
  `srcname` varchar(30) NOT NULL,
  `src_expected` int(10) NOT NULL,
  `src_status` varchar(15) NOT NULL,
  `src_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `source`
--

INSERT INTO `source` (`src_id`, `srcp_id`, `srcp_cate`, `srcname`, `src_expected`, `src_status`, `src_regdate`) VALUES
(3, 1, 'plan', 'Me', 250, 'active', '2017-05-31');

-- --------------------------------------------------------

--
-- Table structure for table `trip`
--

CREATE TABLE `trip` (
  `trp_id` int(50) NOT NULL,
  `trp_name` varchar(50) NOT NULL,
  `trp_purpose` varchar(50) NOT NULL,
  `trp_from` varchar(30) NOT NULL,
  `trp_to` varchar(30) NOT NULL,
  `trp_date` date NOT NULL,
  `trp_direction` varchar(30) NOT NULL,
  `trp_status` varchar(15) NOT NULL,
  `trp_regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trip`
--

INSERT INTO `trip` (`trp_id`, `trp_name`, `trp_purpose`, `trp_from`, `trp_to`, `trp_date`, `trp_direction`, `trp_status`, `trp_regdate`) VALUES
(1, 'Result Slip', 'to go to ETS to bring S6 result slip', 'Nkoto', 'Save', '2017-05-25', 'double', 'active', '2017-05-17');

-- --------------------------------------------------------

--
-- Table structure for table `tripdetail`
--

CREATE TABLE `tripdetail` (
  `trpdt_id` int(50) NOT NULL,
  `trpdttrp_id` varchar(50) NOT NULL,
  `trpdt_detail` varchar(50) NOT NULL,
  `trpdt_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tripdetail`
--

INSERT INTO `tripdetail` (`trpdt_id`, `trpdttrp_id`, `trpdt_detail`, `trpdt_amount`) VALUES
(1, '1', 'Nkoto Kigali', 400),
(3, '1', 'Kigali Save', 2510),
(4, '1', 'Kigali Nkoto ', 500);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `uid` int(50) NOT NULL,
  `uname` varchar(30) NOT NULL,
  `upwd` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `budget`
--
ALTER TABLE `budget`
  ADD PRIMARY KEY (`bdg_id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`cont_id`);

--
-- Indexes for table `lifestyles`
--
ALTER TABLE `lifestyles`
  ADD PRIMARY KEY (`lfs_id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`md_id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`pl_id`);

--
-- Indexes for table `profit`
--
ALTER TABLE `profit`
  ADD PRIMARY KEY (`prof_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`pr_id`);

--
-- Indexes for table `restricteds`
--
ALTER TABLE `restricteds`
  ADD PRIMARY KEY (`restrd_id`);

--
-- Indexes for table `restricters`
--
ALTER TABLE `restricters`
  ADD PRIMARY KEY (`restr_id`);

--
-- Indexes for table `source`
--
ALTER TABLE `source`
  ADD PRIMARY KEY (`src_id`);

--
-- Indexes for table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`trp_id`);

--
-- Indexes for table `tripdetail`
--
ALTER TABLE `tripdetail`
  ADD PRIMARY KEY (`trpdt_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `budget`
--
ALTER TABLE `budget`
  MODIFY `bdg_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `cont_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `lifestyles`
--
ALTER TABLE `lifestyles`
  MODIFY `lfs_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `md_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `menu_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `uid` int(50) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `pl_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `profit`
--
ALTER TABLE `profit`
  MODIFY `prof_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `pr_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `restricteds`
--
ALTER TABLE `restricteds`
  MODIFY `restrd_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `restricters`
--
ALTER TABLE `restricters`
  MODIFY `restr_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `source`
--
ALTER TABLE `source`
  MODIFY `src_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `trip`
--
ALTER TABLE `trip`
  MODIFY `trp_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tripdetail`
--
ALTER TABLE `tripdetail`
  MODIFY `trpdt_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `uid` int(50) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
