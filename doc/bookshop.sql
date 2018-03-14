/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


DROP DATABASE IF EXISTS `bookshop`;
CREATE DATABASE `bookshop`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';/*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookshop`;
CREATE TABLE `admin` (
  `admin_id` int(8) NOT NULL AUTO_INCREMENT,
  `admin_username` varchar(20) DEFAULT NULL,
  `admin_passwd` varchar(20) DEFAULT NULL,
  `admin_realname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `book_id` int(8) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(30) DEFAULT NULL,
  `book_auth` varchar(20) DEFAULT NULL,
  `book_publisher` varchar(30) DEFAULT NULL,
  `book_price` double DEFAULT NULL,
  `book_imgurl` varchar(30) DEFAULT NULL,
  `book_description` varchar(100) DEFAULT NULL,
  `type_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `type_id` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book_type` (
  `type_id` int(8) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_detail` (
  `order_detail_id` int(8) NOT NULL AUTO_INCREMENT,
  `order_id` int(8) DEFAULT NULL,
  `book_id` int(8) DEFAULT NULL,
  `count` int(6) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `book_id` (`book_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `order_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `order_state` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_username` varchar(20) DEFAULT NULL,
  `user_passwd` varchar(20) DEFAULT NULL,
  `user_telephone` varchar(15) DEFAULT NULL,
  `user_address` varchar(50) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `posttime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

ALTER TABLE `book`
ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `book_type` (`type_id`);

ALTER TABLE `order_detail`
ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

ALTER TABLE `orders`
ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
