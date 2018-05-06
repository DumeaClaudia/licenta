-- ALT + X executa mai multe randuri
-- CTRL + ENTER executa interogarea curenta
/*------------------------------------------------------------------USERS--------------------------------------------------------------------*/
drop table `shopping_cart_users`;
drop table `shopping_cart_products`;
drop table `shopping_cart`;
drop table `user`;
drop table `restaurant`;
drop table `product`;



CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `username` varchar(100) UNIQUE not null,
  `password` char(64) NOT NULL, -- sha256/8 = 64 
  `email` varchar(100) UNIQUE NOT NULL,
    PRIMARY KEY (`id`));
    

INSERT INTO `user` (`firstName`,`lastName`,`username`,`password`,`email`) VALUES ('Claudia','Dumea','claudia',SHA2('user', 256),'claudia.dumea@mail.com');
INSERT INTO `user` (`firstName`,`lastName`,`username`,`password`,`email`) VALUES ('Vasile','Ionescu','vasile',SHA2('vasile', 256),'vasile.ionescu@gmail.com');
INSERT INTO `user` (`firstName`,`lastName`,`username`,`password`,`email`) VALUES ('Maria','Popescu','maria',SHA2('maria', 256),'popescu.maria@yahoo.com');
INSERT INTO `user` (`firstName`,`lastName`,`username`,`password`,`email`) VALUES ('Victor','Toma','victor',SHA2('victor',256),'victor.toma20@yahoo.ro');
INSERT INTO `user` (`firstName`,`lastName`,`username`,`password`,`email`) VALUES ('Ana','Karenina','anamaria',SHA2('ana', 256),'k.ana@mail.com');

 select * from `user`;
/*------------------------------------------------------------------RESTAURANTS--------------------------------------------------------------------*/


 CREATE TABLE `restaurant` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`img` varchar(100),
	`name` varchar(100) NOT NULL,
	/*`idAddress` bigint(20) NOT NULL,*/
    `address` varchar(100),
    `stars` int(2),
    `description` varchar(300),
    PRIMARY KEY (`id`)
);

INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v4gc-listing.jpg','Pizzeria di Mattia','Strada Latcu Voda, Valea Adanca 707317',4,'Pizza, Meniuri, Ciorbe, Paste, Tea, Coffee and more');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://d16bcxkz31d65o.cloudfront.net/860x350/v2/Promo/prima%20pagina/Mananci%20pe%20cinste.png','Restaurant Mamma Mia','Iasi, bd. Stefan cel Mare si Sfant, nr. 10-12, la Casa Modei',2,'Pizza, Preparate, Platouri, Cofetarie & Patiserie');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1lt-listing.jpg','Times','Strada Codrescu, nr 6',3,'Carne, Burgeri, Pizza, Vegetariana, Bucatarie Internationala');

select * from `restaurant`;


/*------------------------------------------------------------------PRODUCTS-----------------------------------------------------------------------------------------------*/
 
CREATE TABLE `product` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`idRestaurant` bigint(20),
	`image` varchar(100),
	`category` varchar(30) NOT NULL,
    `name` varchar(50) NOT NULL,
	`description` varchar(300),
	`price` double NOT NULL,
	`discount` int(3),
	FOREIGN KEY fk_idRestaurant_p(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT
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
	`createdDate` datetime not null,
	`sendDate`datetime,
	`idRestaurant` bigint(20),
	FOREIGN KEY fk_idRestaurant_sc(idRestaurant) REFERENCES restaurant(id) ON UPDATE CASCADE ON DELETE RESTRICT	
);

CREATE TABLE `shopping_cart_products`(
	`nrProducts` int(3),
	`idUser`bigint(20),
	`idProduct` bigint(20),
	`idShoppingCart` bigint(20),
	FOREIGN KEY fk_idProduct_scp(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idUser_scp(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY fk_idCart_scp(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

-- DATETIME format : YYYY-MM-DD HH:MM:SS

INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (70, '2018-05-01 10:08:45', '2018-05-01 10:08:45', '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (80, '2018-04-21 20:50:09', '2018-04-21 20:50:09', '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (78, '2018-04-23 12:45:30', '2018-04-23 12:45:30', '1');

INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 1, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 2, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 1);

select * from `shopping_cart`;
select * from `shopping_cart_products`;


select u.username, r.name, p.name, p.description scp FROM shopping_cart_products scp 
	join shopping_cart sc on scp.idShoppingCart=sc.id 
	join restaurant r on sc.idRestaurant=r.id
	join user u on scp.idUser = u.id 
	join product p on scp.idProduct=p.id
	where sc.id=1;



CREATE TABLE `shopping_cart_users` (
	`idUser` bigint(20) NOT NULL,
	`idShoppingCart` bigint(20) NOT NULL,
	FOREIGN KEY fk_idUser_scu(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY fk_idCart_scu(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    PRIMARY KEY(idUser, idShoppingCart)
);

INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (2 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (3 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (4 , 1);


select * from `shopping_cart_users`;
select * from `user`;


SELECT SHA2('abc', 256);













