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


INSERT INTO `restaurant` (`id`,`name`,`street`,`number`,`stars`,`description`) VALUES (``,``,``,``,``,``);
INSERT INTO `restaurant` (`id`,`name`,`street`,`number`,`stars`,`description`) VALUES (``,``,``,``,``,``);
INSERT INTO `restaurant` (`id`,`name`,`street`,`number`,`stars`,`description`) VALUES (``,``,``,``,``,``);

INSERT INTO `product` (`id`,`name`,`description`,`price`,`discount`) VALUES (``,``,``,``,``);

INSERT INTO `restaurant_products` (`idRestaurant`,`idProduct`) VALUES (``,``);

INSERT INTO `shopping_cart` (`id`,`nrProducts`,`totalPrice`,`date`,`idUser`) VALUES (``,``,``,``,``);

INSERT INTO `shopping_cart_products` (`idShoppingCart`,`idProduct`) VALUES (``,``);

INSERT INTO `comment` (`id`,`description`,`username`,`date`) VALUES (``,``,``,``);

INSERT INTO `restaurant_comments` (`idRestaurant`,`idComment`) VALUES (``,``);





