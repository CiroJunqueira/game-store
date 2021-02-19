CREATE TABLE IF NOT EXISTS `product` (
  `product_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) ,
  `price` decimal(10) ,
  `score` int(6) ,
  `image` varchar(80) ,
  `product_code` bigint(6) ,
  PRIMARY KEY (`product_id`)
) 