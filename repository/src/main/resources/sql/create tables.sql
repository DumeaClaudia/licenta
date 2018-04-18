CREATE TABLE `user` (
  `password` varchar(100) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100),
    PRIMARY KEY (`id`))
    
    
    
SELECT * FROM mydatabase.user;


CREATE DATABASE `mydatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*
CREATE TABLE `user` (
	`password` varchar(100) NOT NULL,
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	`username` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

*/

CREATE TABLE `user` (
	`idUser` bigint(20) NOT NULL AUTO_INCREMENT,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(100) NOT NULL,
	`username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `restaurant` (
	`idRestaurant` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	`idAddress` bigint(20) NOT NULL,
	`idProduct` bigint(20) NOT NULL,
    `stars` int(2),
    `idComment` bigint(20),
	FOREIGN KEY idAddress(idAddress) REFERENCES address(idAddress) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY idProduct(idProduct) REFERENCES address(idProduct) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY idComment(idComment) REFERENCES comment(idComment) ON UPDATE CASCADE ON DELETE RESTRICT
);
    
CREATE TABLE `address` (
	`idAddress` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`city` varchar(30) NOT NULL,
	`street` varchar(10) NOT NULL,
	`number` varchar(10) NOT NULL
);

CREATE TABLE `product` (
	`idProduct` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    `name` varchar(30) NOT NULL,
	`description` varchar(10) NOT NULL,
	`price` varchar(10) NOT NULL,
	`discount` int(3)
);

CREATE TABLE `shopping_cart` (
	`idCart` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nrProducts` int(3) NOT NULL,
	`totalPrice` double(8,3) NOT NULL,
	`idProduct` bigint(20) NOT NULL,
	`idUser` bigint(20) NOT NULL,
  	FOREIGN KEY idProduct(idProduct) REFERENCES product(idProduct) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY idUser(idUser) REFERENCES user(idUser) ON UPDATE CASCADE ON DELETE RESTRICT


);

CREATE TABLE `order` (
	`idOrder` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`idCart` bigint(20) NOT NULL,
	`idRestaurant` bigint(20) NOT NULL,
  	FOREIGN KEY idCart(idCart) REFERENCES shopping_cart(idCart) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY idRestaurant(idRestaurant) REFERENCES restaurant(idRestaurant) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `comment` (
	`idComment` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    `description` varchar(300),
	`idUser` bigint(20),
	FOREIGN KEY idUser(idUser) REFERENCES user(idUser) ON UPDATE CASCADE ON DELETE RESTRICT
);