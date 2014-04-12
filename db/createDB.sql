CREATE DATABASE IF NOT EXISTS url_shortener;

USE url_shortener;

DROP TABLE IF EXISTS `url_map`;

CREATE TABLE `url_map` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tiny_url` varchar(6) NOT NULL,
  `long_url` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
