-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.14-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- aboard 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `aboard` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `aboard`;

-- 테이블 aboard.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `memberid` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 aboard.member:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`memberid`, `password`) VALUES
	('aaa', '2222'),
	('mmm', '2222');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 aboard.post 구조 내보내기
CREATE TABLE IF NOT EXISTS `post` (
  `postid` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `memberid` varchar(40) NOT NULL,
  `contents` text NOT NULL,
  `attach` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`postid`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- 테이블 데이터 aboard.post:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`postid`, `title`, `memberid`, `contents`, `attach`) VALUES
	(24, 'sdwefrgnxzca', 'mmm', 'sdefgbn asdas', NULL),
	(25, 'asdfg', 'mmm', 'v xcvb', NULL),
	(26, '123', 'mmm', '123', 'button_help.png'),
	(28, '123', 'aaa', '123', 'flagYellow2.png'),
	(31, 'qwdfegr', 'mmm', 'asdfg', 'gemYellow.png'),
	(32, 'zczxc', 'mmm', 'sqdqsad', '미스트롯3.jpg'),
	(33, '324', 'mmm', '23423\r\n123123\r\nsafsad\r\nsdf', '검정고무신.png');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;

-- 테이블 aboard.reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `reply` (
  `replyid` int(10) NOT NULL AUTO_INCREMENT,
  `postid` int(10) NOT NULL,
  `memberid` varchar(40) NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`replyid`),
  KEY `postid` (`postid`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`postid`) REFERENCES `post` (`postid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- 테이블 데이터 aboard.reply:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` (`replyid`, `postid`, `memberid`, `comment`) VALUES
	(16, 24, 'mmm', 'asdsadasd'),
	(18, 24, 'aaa', 'safdsfsdfsdfsadf'),
	(20, 24, 'aaa', 'adssadasda'),
	(21, 24, 'aaa', 'asdfgn'),
	(24, 25, 'mmm', 'zxcxzc'),
	(25, 32, 'mmm', 'dasdsd'),
	(26, 32, 'mmm', 'sadasd'),
	(27, 32, 'mmm', 'zxczczc'),
	(28, 32, 'aaa', '213'),
	(29, 32, 'aaa', 'zvxcv'),
	(31, 33, 'mmm', 'zcasd123123');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
