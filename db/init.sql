-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.4.0 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 sbsm 的数据库结构
DROP DATABASE IF EXISTS `sbsm`;
CREATE DATABASE IF NOT EXISTS `sbsm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sbsm`;

-- 导出  表 sbsm.master 结构
DROP TABLE IF EXISTS `master`;
CREATE TABLE IF NOT EXISTS `master` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '御主',
  `password` varchar(50) NOT NULL DEFAULT '123',
  `sq` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.master 的数据：~9 rows (大约)
DELETE FROM `master`;
INSERT INTO `master` (`id`, `name`, `password`, `sq`) VALUES
	(1, '御主', '123', 999),
	(2, '御主2', '1234', 9),
	(3, '御主3', '1231', 99),
	(4, '御主4', '1232', 999),
	(5, '芙芙', '123', 0),
	(6, '芙宁娜', '123', 0),
	(7, '芙宁娜', '123', 0),
	(8, '芙宁娜', '123', 0),
	(9, '芙宁娜', '123', 0);

-- 导出  表 sbsm.servant 结构
DROP TABLE IF EXISTS `servant`;
CREATE TABLE IF NOT EXISTS `servant` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `star` int unsigned NOT NULL DEFAULT '5',
  `name` varchar(50) NOT NULL DEFAULT '玛修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.servant 的数据：~33 rows (大约)
DELETE FROM `servant`;
INSERT INTO `servant` (`id`, `star`, `name`) VALUES
	(1, 4, '玛修'),
	(2, 5, '贞德'),
	(3, 5, '梅柳齐娜'),
	(4, 5, '吉尔伽美什'),
	(5, 5, '贞德[Alter]'),
	(6, 5, 'BB'),
	(7, 4, '阿塔兰忒'),
	(8, 4, '织田信长'),
	(9, 4, '哈贝特洛特'),
	(10, 4, '斯露德'),
	(11, 4, '壹与'),
	(12, 4, '九纹龙伊丽莎'),
	(13, 4, '迷之偶像X[Alter]'),
	(14, 3, '贝德维尔'),
	(15, 3, '尤瑞艾莉'),
	(16, 3, '亚历山大'),
	(17, 3, '豹人'),
	(18, 3, '罗穆路斯'),
	(19, 3, '大流士三世'),
	(20, 2, '陈宫'),
	(21, 2, '加雷斯'),
	(22, 2, '列奥尼达一世'),
	(23, 2, '咒腕哈桑'),
	(24, 2, '安徒生'),
	(25, 2, '黑胡子'),
	(26, 2, '莎乐美'),
	(27, 1, '伊阿宋'),
	(28, 1, '阿拉什'),
	(29, 1, '莫扎特'),
	(30, 1, '夏洛蒂·科黛'),
	(31, 1, '玛塔·哈丽'),
	(32, 1, '斯巴达克斯'),
	(33, 1, '织田信胜');

-- 导出  表 sbsm.shadow 结构
DROP TABLE IF EXISTS `shadow`;
CREATE TABLE IF NOT EXISTS `shadow` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sid` int unsigned DEFAULT NULL,
  `mid` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__servant` (`sid`),
  KEY `FK__master` (`mid`),
  CONSTRAINT `FK__master` FOREIGN KEY (`mid`) REFERENCES `master` (`id`),
  CONSTRAINT `FK__servant` FOREIGN KEY (`sid`) REFERENCES `servant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.shadow 的数据：~0 rows (大约)
DELETE FROM `shadow`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
