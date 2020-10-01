DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `cost` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `photo` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


INSERT INTO `products` (`id`, `code`, `title`, `description`, `cost`, `active`) VALUES ('1', '123123', 'Socks', 'Men\'s socks', '30', '1');
INSERT INTO `products` (`id`, `code`, `title`, `description`, `cost`, `active`) VALUES ('2', '123125', 'Jeans', 'Levis jeans', '150', '1');
INSERT INTO `products` (`id`, `code`, `title`, `description`, `cost`, `active`) VALUES ('3', '123125', 'Dress', 'DnG beautiful dress', '120', '1');
