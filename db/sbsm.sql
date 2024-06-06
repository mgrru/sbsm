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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.master 的数据：~4 rows (大约)
INSERT IGNORE INTO `master` (`id`, `name`, `password`, `sq`) VALUES
	(1, '御主', '123', 0),
	(2, '御主2', '1234', 9),
	(3, '御主3', '1231', 99),
	(4, '御主4', '1232', 999);

-- 导出  表 sbsm.servant 结构
DROP TABLE IF EXISTS `servant`;
CREATE TABLE IF NOT EXISTS `servant` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `star` int unsigned NOT NULL DEFAULT '5',
  `name` varchar(50) NOT NULL DEFAULT '玛修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.servant 的数据：~4 rows (大约)
INSERT IGNORE INTO `servant` (`id`, `star`, `name`) VALUES
	(1, 4, '玛修'),
	(2, 5, '贞德'),
	(3, 5, '弓贞'),
	(4, 5, '闪闪');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  sbsm.shadow 的数据：~10 rows (大约)
INSERT IGNORE INTO `shadow` (`id`, `sid`, `mid`) VALUES
	(1, 1, 1),
	(2, 3, 1),
	(3, 4, 1),
	(4, 2, 2),
	(5, 4, 1),
	(6, 4, 1),
	(7, 3, 1),
	(8, 3, 1),
	(9, 2, 1),
	(10, 3, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
