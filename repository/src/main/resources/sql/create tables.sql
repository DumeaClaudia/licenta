
CREATE TABLE `user` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(100) NOT NULL,
	`username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `restaurant` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	/*`idAddress` bigint(20) NOT NULL,*/
    `street` varchar(40),
    `number` int(5),CREATE DATABASE `mydatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;

SELECT * FROM mydatabase.user;

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
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(100) NOT NULL,
	`username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `restaurant` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	/*`idAddress` bigint(20) NOT NULL,*/
    `street` varchar(40),
    `number` int(5),
    `stars` int(2),
    `description` varchar(300)
	/*FOREIGN KEY fk_idAddress_restaurant(idAddress) REFERENCES address(id) ON UPDATE CASCADE ON DELETE RESTRICT8*/
);

 /*   
CREATE TABLE `address` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`city` varchar(30) NOT NULL,
	`street` varchar(10) NOT NULL,
	`number` varchar(10) NOT NULL
);
*/

CREATE TABLE `product` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    `name` varchar(30) NOT NULL,
	`description` varchar(10) NOT NULL,
	`price` varchar(10) NOT NULL,
	`discount` int(3)
);

CREATE TABLE `restaurant_products`(
	`idRestaurant` bigint(20),
	`idProduct` bigint(20),
	FOREIGN KEY fk_idRestaurant_rp(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idProduct_rp(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `shopping_cart` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nrProducts` int(3) NOT NULL,
	`totalPrice` double(8,3) NOT NULL,
	`date` date,
	`idUser` bigint(20),
	FOREIGN KEY fk_idUser_cart(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT

);

CREATE TABLE `shopping_cart_products`(
	`idProduct` bigint(20),
	`idShoppingCart` bigint(20),
	FOREIGN KEY fk_idProduct_sc(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY fk_idCart_sc(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `comment` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
	`username` varchar(30),
    `description` varchar(300),
    `date` date
);

CREATE TABLE `restaurant_comments`(
	`idRestaurant` bigint(20),
	`idComment` bigint(20),
	FOREIGN KEY fk_idRestaurant_rc(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idComment_rc(idComment) REFERENCES comment(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

/*CREATE TABLE `order` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`idShoppingCart` bigint(20) NOT NULL,
	`idRestaurant` bigint(20) NOT NULL,
	`date` date,
  	FOREIGN KEY fk_idCart_order(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idRestaurant_order(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT
);*/
    `stars` int(2),
    `description` varchar(300)
	/*FOREIGN KEY fk_idAddress_restaurant(idAddress) REFERENCES address(id) ON UPDATE CASCADE ON DELETE RESTRICT8*/
);

 /*   
CREATE TABLE `address` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`city` varchar(30) NOT NULL,
	`street` varchar(10) NOT NULL,
	`number` varchar(10) NOT NULL
);
*/

CREATE TABLE `product` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    `name` varchar(30) NOT NULL,
	`description` varchar(10) NOT NULL,
	`price` varchar(10) NOT NULL,
	`discount` int(3)
);

CREATE TABLE `restaurant_products`(
	`idRestaurant` bigint(20),
	`idProduct` bigint(20),
	FOREIGN KEY fk_idRestaurant_rp(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idProduct_rp(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `shopping_cart` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nrProducts` int(3) NOT NULL,
	`totalPrice` double(8,3) NOT NULL,
	`date` date,
	`idUser` bigint(20),
	FOREIGN KEY fk_idUser_cart(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT

);

CREATE TABLE `shopping_cart_products`(
	`idProduct` bigint(20),
	`idShoppingCart` bigint(20),
	FOREIGN KEY fk_idProduct_sc(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY fk_idCart_sc(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `comment` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	
	`username` varchar(30),
    `description` varchar(300),
    `date` date
);

CREATE TABLE `restaurant_comments`(
	`idRestaurant` bigint(20),
	`idComment` bigint(20),
	FOREIGN KEY fk_idRestaurant_rc(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idComment_rc(idComment) REFERENCES comment(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

/*CREATE TABLE `order` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`idShoppingCart` bigint(20) NOT NULL,
	`idRestaurant` bigint(20) NOT NULL,
	`date` date,
  	FOREIGN KEY fk_idCart_order(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idRestaurant_order(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT
);*/