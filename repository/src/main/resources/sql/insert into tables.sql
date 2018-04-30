/*
-- Query: SELECT * FROM mydatabase.user
LIMIT 0, 1000

-- Date: 2018-03-20 12:04
*/
INSERT INTO `user` (`password`,`id`,`firstName`,`lastName`,`email`,`username`) VALUES ('user',2,'Claudia','Dumea','claudia.dumea@mail.com','claudia');
INSERT INTO `user` (`password`,`id`,`firstName`,`lastName`,`email`,`username`) VALUES ('vasile',3,'Vasile','Ionescu','vasile.ionescu@gmail.com','vasile');
INSERT INTO `user` (`password`,`id`,`firstName`,`lastName`,`email`,`username`) VALUES ('maria',4,'Maria','Popescu','popescu.maria@yahoo.com','maria');
INSERT INTO `user` (`password`,`id`,`firstName`,`lastName`,`email`,`username`) VALUES ('victor',5,'Victor','Toma','victor.toma20@yahoo.ro','victor');
INSERT INTO `user` (`password`,`id`,`firstName`,`lastName`,`email`,`username`) VALUES ('anamaria',6,'Ana','Karenina','k.ana@mail.com','ana');


-- ALT + X executa mai multe randuri
-- CTRL + ENTER executa interogarea curenta
/*------------------------------------------------------------------USERS--------------------------------------------------------------------*/

CREATE TABLE `user` (
  `password` varchar(100) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100),
    PRIMARY KEY (`id`))
    
 select * from `user`;
/*------------------------------------------------------------------RESTAURANTS--------------------------------------------------------------------*/
 drop table `restaurant`;

 CREATE TABLE `restaurant` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`img` varchar(100),
	`name` varchar(100) NOT NULL,
	/*`idAddress` bigint(20) NOT NULL,*/
    `address` varchar(100),
    `stars` int(2),
    `description` varchar(300),
    PRIMARY KEY (`id`)
	/*FOREIGN KEY fk_idAddress_restaurant(idAddress) REFERENCES address(id) ON UPDATE CASCADE ON DELETE RESTRICT8*/
);

INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v4gc-listing.jpg','Pizzeria di Mattia','Strada Latcu Voda, Valea Adanca 707317',4,'Pizza, Meniuri, Ciorbe, Paste, Tea, Coffee and more');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://d16bcxkz31d65o.cloudfront.net/860x350/v2/Promo/prima%20pagina/Mananci%20pe%20cinste.png','Restaurant Mamma Mia','Iasi, bd. Stefan cel Mare si Sfant, nr. 10-12, la Casa Modei',2,'Pizza, Preparate, Platouri, Cofetarie & Patiserie');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1lt-listing.jpg','Times','Strada Codrescu, nr 6',3,'Carne, Burgeri, Pizza, Vegetariana, Bucatarie Internationala');

select * from `restaurant`;


/*------------------------------------------------------------------PRODUCTS-----------------------------------------------------------------------------------------------*/
 drop table `product`;

CREATE TABLE `product` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`idRestaurant` bigint(20),
	`image` varchar(100),
	`category` varchar(30) NOT NULL,
    `name` varchar(50) NOT NULL,
	`description` varchar(300),
	`price` double NOT NULL,
	`discount` int(3),
	FOREIGN KEY fk_idRestaurant_rp(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1,'SupaCremaDeCiuperci.jpg','Supe creme', 'Supa crema de ciuperci','250 g', 13.90);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1,'SupaCremaDeRosii.jpg','Supe creme', 'Supa crema de rosii cu busuioc si crutoane','300 g', 15.50);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1,'SupaCremaDeDovlecei.jpg','Supe creme', 'Supa crema de dovlecei','250 g', 13.90); 

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (1,'Ciorbe', 'Ciorba de legume', '350g - ingrediente:  morcov, ceapa, ardei, rosii, sfecla rosie, patrunjel', 9.50);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (1,'Ciorbe', 'Ciorba radauteana', '300g - ingrediente: piept de pui, smantana, usturoi, ou, legume ', 13.90);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (1,'Ciorbe', 'Ciorba de vacuta', '350g - ingrediente: carne de vita, morcov, ceapa. ardei, rosii, patrunjel, insotita de ardei iute si smantanta ', 14.90);


select * from `product`;

/*--------------------------------------------------------------------CART--------------------------------------------------------------------------------*/

CREATE TABLE `shopping_cart` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`totalPrice` double(8,3) NOT NULL,
	`date` date,
	`idUser` bigint(20),
	FOREIGN KEY fk_idUser_cart(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT

);


