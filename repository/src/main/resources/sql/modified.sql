-- ALT + X executa mai multe randuri
-- CTRL + ENTER executa interogarea curenta
/*------------------------------------------------------------------USERS--------------------------------------------------------------------*/
drop table `shopping_cart_users`;
drop table `shopping_cart_products`;
drop table `shopping_cart`;
drop table `user`;
drop table `restaurant`;
drop table `product`;

ALTER TABLE `user` ENGINE = InnoDB;
ALTER TABLE `restaurant` ENGINE = InnoDB;
ALTER TABLE `product` ENGINE = InnoDB;
ALTER TABLE `shopping_cart` ENGINE = InnoDB;
ALTER TABLE `shopping_cart_products` ENGINE = InnoDB;
ALTER TABLE `shopping_cart_users` ENGINE = InnoDB;


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
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1lt-listing.jpg','Times','Strada Codrescu, nr 6', 3,'Carne, Burgeri, Pizza, Vegetariana, Bucatarie Internationala');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1ur-listing.jpg','BigBur',' Aleea Nicolina, nr. 13, Iași 700259', 4,'Burgeri, Mâncare Romaneasca, Pizza, Bucătarie internațională');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v4ri-listing.jpg','Restaurant Bavaria','Bulevardul Nicolae Iorga, nr.2A, Iasi', 5 ,'Bucatarie Internationala, Gratar, Pui, Vegetariana, Vita');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v8vy-listing.jpg','Pizzeria Mady s',' Strada Petre Tutea, nr. 913, Iasi',6 ,'Quick Service Restaurant, Mancare Romaneasca, Pizza, Bucatarie Internationala, Gratar');

INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v5go-listing.jpg','Pizzeria Golfo di Napoli','Soseaua Valea Rediului, nr.259, Iasi',4,'Pizza, Bucatarie Italiana, Carne, Paste, Salate');

-- https://images.deliveryhero.io/image/fd-ro/LH/v7hs-listing.jpg
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v7hs-listing.jpg','Aroma Zen','Strada Pacurari, nr.79, Iasi',4,'Salate, Paste, Carne, Bucatarie Internationala, Vegetariana');
-- https://images.deliveryhero.io/image/fd-ro/LH/v4ek-listing.jpg
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v4ek-listing.jpg','Take & Eat','Strada Otilia Cazimir, nr.18, Iasi', 4, 'Meniuri, Salate, Sandwich-uri, Pizza, Desert');


/*Pizzeria Mady
*Quick Service Restaurant, Mancare Romaneasca, Pizza, Bucatarie Internationala, Gratar
Strada Petre Tutea, nr. 913, Iasi*/

/* 4 Big Bur

delete from `restaurant` where id=6
 */

-- select * from `restaurant`;


/*------------------------------------------------------------------PRODUCTS-----------------------------------------------------------------------------------------------*/
 
-- drop table `product`;

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

alter table product modify `name` varchar(100);

INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'SupaCremaDeCiuperci.jpg','Supe creme', 'Supa crema de ciuperci','250 g', 13.90);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'SupaCremaDeRosii.jpg','Supe creme', 'Supa crema de rosii cu busuioc si crutoane','300 g', 15.50);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'SupaCremaDeDovlecei.jpg','Supe creme', 'Supa crema de dovlecei','250 g', 13.90); 

INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'CiorbaDeLegume.jpg','Ciorbe', 'Ciorba de legume','350g - ingrediente:  morcov, ceapa, ardei, rosii, sfecla rosie, patrunjel', 9.50);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'CiorbaRadauteana.jpg', 'Ciorbe', 'Ciorba radauteana', '300g - ingrediente: piept de pui, smantana, usturoi, ou, legume ', 13.90);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (1, 'CiorbaDeVacuta.jpg','Ciorbe', 'Ciorba de vacuta', '350g - ingrediente: carne de vita, morcov, ceapa. ardei, rosii, patrunjel, insotita de ardei iute si smantanta ', 14.90);


INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_A_La_Chef.jpg','Pizza', 'Pizza a la Chef','ingrediente: mozzarella, sos, roșii cherry, babic, bocconcini de mozzarela, aluat', 25.90);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Bacon_Cipola.jpg','Pizza', 'Bacon & Cipola','ingrediente: ceapa rosie, kaizer, mozzarella, sos, aluat', 23.80);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Beef_Deluxe.jpg','Pizza', 'Beef Deluxe','ingrediente: ardei gras, ciuperci, rosii, mozzarella, sos, masline verzi umplute cu gogosari, vrabioara de vita marinata, aluat', 29.70);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Capriciosa.jpg','Pizza', 'Capriciosa','ingrediente: ciuperci, kaizer, grana padano, sunca, mozzarella, sos, aluat', 26.30);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Double_Cheese.jpg','Pizza', 'Double Cheese','ingrediente:ciuperci, salam super, sos, mozzarella (portie dubla), aluat', 28.80);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Espagnola.jpg','Pizza', 'Espagnola','ingrediente: gorgonzola, kaizer, sunca, mozzarella, sos, aluat', 25.90);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Florenza.jpg','Pizza', 'Florenza','ingrediente: ardei gras, ceapa rosie, ciuperci, kaizer, masline negre, pastrama porc, salam vara, sunca, mozzarella, sos, aluat', 27.30);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (2, 'Pizza_Full_Meat.jpg','Pizza', 'Full Meat','ingrediente: kaizer, pastrama porc, piept de pui, salam super, salam vara, sunca, mozzarella, sos, aluat', 30.70);


INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Burgeri', 'American Burger', 'paine brioche, carne vita, bacon, branza ceddar, salata iceberg, ceapa rosie, castraveti in otet, ardei iuti in otet, rosii, sos aioli', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Burgeri', 'Times Burger ', 'paine brioche, carne de vita, bacon, branza ceddar, salata iceberg, ceapa rosie, castraveti in otet, parmezam, crispy chips, crispy onion, sos aioli', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Burgeri', 'Marmelade Burger', 'paine brioche, carne vita, bacon, blue cheese, salata iceberg, marmelada din ceapa rosie, blue cheese sauce', 21.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Burgeri', 'Crispy Chicken Burger', 'paine brioche, snitel de pui in pesmet, castraveti in otet, sos aioli', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Burgeri', 'Veggie Burger', 'paine brioche, branza halloumi, salata iceberg, vanata grill, castraveti in otet, ardei iuti in otet, rosii, sos blue cheese', 17.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza Times', 'aluat italian, sos pizza, mozzarella, sunzam salam, ciuperci, ardei, rosii, masline - 700 gr', 22.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza Quattro Stagioni ', 'aluat italian, sos pizza, mozzarella, șuncă, salam, ceapă, ciuperci - 600 gr', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza Prosciutto Cotto e Funghi', 'aluat italian, sos pizza, mozzarella, prosciutto cotto, ciuperci, rucola', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza Quattro Formagi', 'aluat italian, mozzarella, parmezan, gorgonzola, brânză brie - 600 gr', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza Salami Picant', 'aluat italian, sos pizza, mozzarella, salam picant, ardei - 550 gr', 24.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Pizza', 'Pizza al Tono', ' aluat italian, sos pizza, mozzarella, ton, porumb, masline, lamaie - 550 gr', 24.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', 'Times&#039; Special', 'coaste de porc marinate, gătite la cuptor și servite cu sos BBQ, garnitură de cartofi rondele și salată coleslaw **alergeni: gluten, ouă, lapte', 19.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', 'Times Schnitel ', 'piept de pui, ou, lapte, pesmet, cartofi (wedges/pai) (400 g)', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', ' Aripioare Buffalo', ' 8 aripioare picante, cartofi wedges, sos blue cheese, telina', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Salată de vinete (cu maioneză)', 'vinete, ceapă roșie, maioneză și pâine prăjită', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Avocado hummus', 'servit cu lipie și legume crude - 200g', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Platou de brânzeturi fine', 'blue cheese, brie, parmesan - servit cu struguri, nuci și biscuit pentru brânză **alergeni: gluten, mustar, ouă, lapte, nuci', 27.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Platouri', 'Beast Mode', 'Coaste de porc cu sos bbq, aripioare de pui, crispy de pui, frigărui din piept de pui, bile din mozarella, cartofi wedges, sos mujdei și sos picant (deservește 3 - 4 persoane) **alergeni: gluten, muștar, ouă, lapte - 1200 g', 95.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Platouri', 'Vegetarian Platter', 'Inele de ceapă, avocado hummus, bile din mozzarella, salată de vinete cu maioneză, cartofi wedges sau pai, sos maioneză cu usturoi și sos picant (deservește 3 - 4 persoane) **alergeni: gluten, muștar, ouă, lapte', 80.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Salate', 'salata iceberg, piept de pui, rosii cherry, crutoane, parmezan, dressing caesar', 21.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Cartofi pai', '140g', 8.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Cartofi pai cu parmezan și usturoi', '140g', 9.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Chifla', '75 g', 1.50);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Desert', 'Chesse Cake', 'servit cu dulceață de casă: 100g/30g', 16.50);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Desert', 'Lava cake', 'servit cu o cupă de înghețată - 130g', 16.50);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Prosciutto', 'Blat Italian, Mozzarella, Sos salsa, jambon, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Prosciutto Funghi', 'Blat Italian, mozzarella, sos salsa, jambon, ciuperci, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Zucchini', 'Blat Italian, mozzarella, sos salsa, vinete, dovlecei zucchini, ardei gras, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Capriciosa', 'Blat Italian, mozzarella, sos salsa ciuperci, anghinare, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Calzone', 'Blat Italian, mozzarella, sos salsa, jambon, ciuperci, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Quattro Stagione', 'Blat Italian, mozzarela sos salsa, salam, jambon, ciuperci, masline, condimente - 600g', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Italia', 'Blat Italian, mozzarella, sos salsa, busuioc, felii de rosii, rucola, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Orlando', 'Blat Italian, mozzarella, sos salsa, piept de pui la Kebab, ceapa rosie, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Kebab', 'Blat Italian, mozzarella, sos salsa, kebab de piept de pui, cartofi pai, masline, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Tonno', 'Blat Italian, mozzarella, sos salsa, peste ton, ceapa rosie, masline verzi, condimente. - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Quattro Formaggi', 'Blat Italian, mozzarella, sos salsa, gorgonzola, parmezan condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Greceasca', 'Blat italian, mozzarella, sos salsa, branza feta, masline, ardei gras, condimente. - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Vip', 'Blat Italian, mozzarella, sos salsa, salam, jambon, ciuperci, piept de pui, condimente. - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Deliciosa', 'Blat Italian, mozzarella, sos salsa, rosii cherry, dovlecei, prosciutto crudo, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Pollo', 'Blat Italian, mozzarella, sos salsa, piept de pui, porumb, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Frutti di Mare', 'Blat Italian, mozzarella, sos salsa, fructe de mare, jambon, brocoli, condimente. - 600 gr', 28.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Somn', 'Blat Italian, mozzarella, sos salsa, somon fume, capere, lamaie, condimente - 600 gr', 28.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Prosciutto Crudo', 'Blat Italian, mozzarella, sos salsa, prosciotto crudo, parmezan,rucola, condimente - 600 gr', 28.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Bigbur', 'Blat Italian, mozzarella, sos salsa, jambon, ceapa rosie, condimente - 600 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Diavolo', 'Blat Italian, mozzarella, sos salsa, salam crud uscat picant, ardei picanti, condimente - 600 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Salami', 'Blat Italian, mozzarella, sos salsa, salam crud uscat, condimente - 600 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Taraneasca', 'Blat Italian, mozzarella, sos salsa, carnati, bacon,ceapa rosie, masline, ardel gras, condimente - 600 gr', 24.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Concarne', 'Blat Italian, mozzarella, sos salsa, carne tocata, ceapa rosie, condimente. - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Gyros', 'Blat Italian, mozzarella, sos salsa, ceapa rosie, piept de pui, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Krispy', 'Blat Italian, mozzarella, sos salsa, crispy, ardel gras, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Hawai', 'Blat Italian, mozzarella, sos salsa, jambon, ananas, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza New York', 'Blat Italian, mozzarella, sos salsa, bacon, ceapa rosie, porumb, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Grill', 'Blat Italian, mozzarella, sos salsa, piept de pui, ceapa rosie, ardei grasi, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Roma', 'Blat Italian, mozzarella, sos salsa, ciuperci, ardei gras, salam, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Olandeza', 'Blat Italian, mozzarella, sos salsa, jambon, brocoli, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Siciliana', 'Blat Italian, mozzarella, sos salsa, jambon, gorgonzola, condimente - 600 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Carnivora', 'Blat Italian, mozzarella, sos salsa, carnati, salam, jambon, masline, condimente - 600 gr', 24.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Rustica', 'Blat Italian, mozzarella, sos salsa, jambon, ciuperci, condimente - 600 gr', 24.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Tonno', 'ton, cascaval, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Hot Krispy', 'krispy piept de pui, mozzarella, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Grill', 'grill piept de pui, ananas, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Greceasca', 'masline kalamata, branza feta, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Paradise', 'jambon, cascaval, ou, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Salate', 'Salata Italia', 'mozzarella, castraveti, porumb, morcov, rucola, salata, ceapa, rosii cherry, spanac, seminte de floarea soarelui, dressing - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Paste', 'Paste Arrabbiata', 'penne, mozzarella, bacon, salsa - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Paste', 'Paste Carbonara', 'penne, smantana, bacon, ou, parmezan - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Paste', 'Paste Milaneze', 'penne, ciuperci, sunca, parmezan - 600 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'BigBurger', 'Paine cu susan, burger de vita, cascaval pane, salata iceberg, cedar, muraturi, ceapa rosie, maioneza de casa, mustar, ketchup si bucket cartofi prajiti - 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'American Burger', 'Paine cu susan, burger de vita, bacon, cedar, salata iceberg, ceapa caramelizata, ceapa rosie, maioneza de casa, mustar, ketchup si bucket cartofi prajiti- 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Happy Cheese', 'Paine cu susan, burger de vita, cascaval pane, salata iceberg, cedar, muraturi, ceapa rosie, maioneza de casa, mustar, ketchup si bucket cartofi prajiti- 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Spicy', 'Paine cu susan, burger de vita, salata iceberg, cedar, dulceata de ardei iuti, muraturi, ceapa rosie, maioneza de casa, mustar, ketchup si bucket cartofi prajiti.- 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Mad Mushroom', 'Paine cu susan, burger de vita, salata iceberg, cedar, ceapa caramelizata, ciuperci, bacon, ceapa rosie, maioneza de casa, mustar, ketchup si bucket cartofi prajiti.- 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Royal Beef Burger', 'Paine cu susan, burger de vita, cartofi, castraveti, salata, rosie, ceapa, ardei iuti, ketchup si maioneza de casa si bucket cartofi prajiti - 500 g', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Shaorma de pui', 'lipie, piept de pui, cartofi, varza, castraveti, sos - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Pita ham &amp; bacon', 'lipie, bacon, sunca, ou, ciuperci, cartofi, salata, sos - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Gyros', 'lipie, pui, cartofi, salata, castraveti, sos - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Souvlakia', 'lipie, piept de pui, salata, sos tzatziki - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Twister Krispy', 'tortilla, piept de pui, krispy, salata, sos - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Twister Fish', 'tortilla, peste krispy, salata, sos - 400 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Vegetarian', 'ciuperci, ardei kapia, masline, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Fish', 'peste pane, cedar, salata, ceapa, sos alb - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Tonno', 'ton, ou, masline verzi, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Krispy', 'pui krispy, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Toast', 'cedar, ou, ciuperci, ardei, bacon, sunca, salata, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Fresh', 'cedar, ou, ciuperci, bacon, sunca, salata, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Bigburger Pui', 'burger de pui, cedar, salata, rosii, ceapa, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Bigburger Vita', 'burger de vita, cedar, salata, rosii, ceapa, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Doner Kebab Pui', 'pui, cedar, salata, rosii, castraveti, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Doner Kebab Vita', 'vita, cedar, salata, rosii, castraveti, sos - sandwich mic 280 gr sau sandwich mare - 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Prosciutto Crudo', 'prosciutto crudo, cedar, salata, rosii, castraveti, sos - sandwich mic-280 gr sau sandwich mare-400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'BigBur', 'Paine cu susan, bacon, kebap de pui, branza topita, ceapa, rosii, salata, castraveti murati, ketchup si maioneaza de casa.', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Shaorma de vita', 'lipie, vita, cartofi, varza, castraveti, sos - 400 gr', 13.00);


/*Restaurant Bavaria
*Bucatarie Internationala, Gratar, Pui, Vegetariana, Vita
*Bulevardul Nicolae Iorga, nr.2A, Iasi
*/

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Muschi de vita in sos de hribi', ' muschi de vita, hribi, smantana, gran cuccina, rosii - 200/150 gr', 55.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Duetul casei', 'muschi de vita, muschiuler de porc, ciuperci, smantana, ceapa, usturoi, gran cucina - 250/150 gr', 45.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Pui cu mozzarella si legume basque', ' piept de pui, mozzarella, pesmet, legume basque, ardei gras, vinete, dovlecel, morcovi - 200/250 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Pui taranesc cu mamaliguta', 'piept de pui, ardei gras, ceapa, morcov, rosii, cartofi, mamaliguta - 300/200 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Piept de curcan aromat cu afine', 'piept de curcan, unt, afine, gran cucina - 250 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Piept de pui bavarez cu mamaliguta', 'piept de pui, praz, ardei gras, fasole rosie, sos pimento, mamaliguta - 250/200 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Minuturi', 'Bruschete cu rosii', 'chifla, rosii, ceapa rosie - 250 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Minuturi', 'Bruschete cu ton', 'chifla, rosii, ceapa rosie, ton - 280 gr', 12.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Minuturi', 'Paine toast cu salsa tonno', 'chifle, ton, busuioc, capere - 200 gr', 12.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Minuturi', 'Omleta simpla', '100 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Minuturi', 'Omleta cu ciuperci si cascaval', 'Ou, cascaval, ciuperci - 200 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de burta', '50/300 gr', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba radauteana', '50/300 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de vacuta', '50/300 gr', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de fasole cu afumatura', '50/300 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de pui cu taitei de casa', '50/300 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de legume', '350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Ciorba de fasole', '350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', 'Supa crema de ciuperci', '350 gr', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de vita', 'Muschi de vita la gratar', 'muschi vita, unt, lamaie - 200 gr', 49.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de vita', 'Tagliata di manzo', 'muschi vita, parmezan, salata verde, lamaie - 170/100 gr', 48.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de vita', 'Tocanita din muschi de vita', 'muschi vita, ardei gras, rosii, ceapa, cartofi, usturoi, sos rosii - 450 gr', 43.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de vita', 'Turnedo de vita', 'muschi vita, mar, mozzarella, ficatei, ciuperci, rosii - 450 gr', 43.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de vita', 'Muschi de vita in sos gorgonzola', 'muschi vita, gran cucina, gorgonzola, unt - 170/200 gr', 48.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Pui vienez', 'piept de pui, cartofi, ciuperci, cascaval, gran cucina, praz - 450 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Gratin de pui in sos picant', 'piept de pui, ciuperci, cascaval, cartofi, ardei iute, praz, sos rosu - 450 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Valdostana din piept de pui', 'piept de pui, sunca, cascaval, ciuperci, gran cucina - 300 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Pui Carbonara', 'piept de pui, ciuperci, bacon, gran cucina - 300 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Piept de pui cu sos gorgonzola', 'piept de pui, gorgonzola, gran cucina - 250 gr', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Carne de pasare', 'Piept de pui cu smantana si ciuperci', 'piept de pui, ciuperci, gran cucina - 250 gr', 23.00);



/*Pizzeria Mady
*Quick Service Restaurant, Mancare Romaneasca, Pizza, Bucatarie Internationala, Gratar
Strada Petre Tutea, nr. 913, Iasi*/

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Madys', 'mozzarela, sos pomodoro, salam de vara, sunca,carnaciori picanti,bacon,masline, ciuperci, rosii, ardei gras', 15.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Quattro Stagione', 'mozzarela, sos pomodoro, carnaciori, salam de vara, ciuperci, masline', 15.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Carnivore', 'mozzarella, sos pomodoro, salam, sunca,carnati, bacon, ceapa, masline', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Taraneasca', 'mozarella, sos pomodoro, carnati, salam, bacon, ardei gras, ceapa, rosii, porumb', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Margherita', 'mozzarella, sos pomodoro, rosii, ardei gras', 12.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Kebab', 'mozzarella, sos pomodoro, ciuperci, masline, carne pui kebab, rosii', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Nero', 'mozzarella, sos pomodoro, piept de pui, ciuperci, masline, porumb', 15.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Diavolo', 'mozzarella,, sos pomodor, salam picant, carnati, ardei iute', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza cu Ton', 'mozzarella, sos pomodor, ton, ceapa rosie, lamaie', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Pizza', 'Pizza Salami', 'mozzarella, sos pomodoro, salam italian, carnati, masline', 15.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Platouri', 'Platou  Madys', 'ceafa de porc, 2 mici, jambon, carnaciori picanti, cartofi prajiti, castraveti murati', 40.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Platouri', 'Platoul Ucenicului', 'aripioare, frigarui pui, mici 2, cartofi prajiti, salata de varza', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata Madys', 'piept de pui, sunca praga, rosii, castraveti, salata verde, ardei gras, ceapa, crutoane, masline', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata greceasca', 'rosii, ceapa, salata verde, ardei gras, ceapa, piept de pui, branza feta', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata bulgareasca', 'rosii, salata verde,ardei gras, ceapa, masline, sunca, branza feta, ou', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata cu ton', 'rosii, salata verde, castraveti, ceapa, ardei gras, ton', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata de sezon', 'rosii, ceapa, castraveti', 4.00);


/* Pizzeria Golfo di Napoli */

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Margherita',' sos tomate, mozzarella, busuioc proaspat - 450 gr',  20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Prosciutto',' sos tomate, mozzarella, sunca Praga - 550 gr', 22.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Prosciutto Funghi',' sos tomate, mozzarella, champignon, sunca Praga - 550 gr', 23.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Diavola','sos tomate, mozzarella, salam peperoncino - 550 gr', 22.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Tonno','sos tomate, mozzarella, ton, ceapa rosie, masline, busuioc proaspat - 550 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Pollo','sos tomate, mozzarella, piept de pui, champignon - 550 gr', 23.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Quattro Stagione','sos tomate, mozzarella, salami, sunca Praga, ardei gras, masline, champignon - 550 gr', 24.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Quattro Formaggi',' mozzarella, gorgonzola, parmezan, escamorza (cascaval afumat) - 550 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Carnivore','sos tomate, mozzarella, sunca Praga, salami, bacon, piept de pui - 550 gr', 25.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Rustica','sos tomate, mozzarella, sunca taraneasca, carnati boieresti, champignon, ardei gras - 550 gr', 25.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Hawaiana','sos tomate, mozzarella, piept de pui, ananas - 550 gr', 25.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Bella Napoli',' tomate cherry, mozzarella, prosciutto crudo, rucola, parmezan - 450 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Napoletana','sos tomate, mozzarella, anchoa, oregano - 550 gr', 23.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza cu fructe de mare','sos tomate, mozzarella, mix fructe de mare, usturoi - 550 gr', 27.00 );
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Pizza', 'Pizza Vegetariana','sos tomate, mozzarella, ardei gras, champignon, brocoli, masline, porumb, vanata - 550 gr', 25.00 );

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Paste', 'Paste Carbonara','paste, smantana, ou, ceapa, panceta - 450 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Paste', 'Paste Bolognese','paste, carne tocata, sos tomate - 450 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Paste', 'Paste Amatriciana','paste, sos tomate, panceta, ceapa, busuioc, peperoncino - 450 gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Paste', 'Paste cu fructe de mare','paste, mix fructe de mare, usturoi, peperoncino, vin, tomate cherry - 450 gr', 27.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Salate', 'Salata cu ton','salata, mozzarella, tomate cherry, ton, masline, ceapa rosie', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Salate', 'Caprese','rucola, tomate, mozzarella, masline - 380 gr', 15.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Salate', 'Salata asortata',' tomate, castraveti, ceapa, ardei gras, masline', 6.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Coaste','coaste de porc, cartofi gratinati, salata asortata - 450 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Snitel de pui','snitel de pui crocant in sos de tomate, cartofi copti gratinati la cuptor - 450 gr', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Aripioare in sos BBQ','aripioare de pui, cartofi gratinati - 450 gr', 26.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Crispy','crispy din piept de pui cu sos de usturoi si cartofi pai - 450 gr', 22.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Gratar de pui','piept de pui la gratar cu cartofi pai - 400 gr',20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Ceafa de porc','ceafa de porc cu cartofi pai - 400 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (7, 'Meniuri', 'Meniu Rozbif','muschiulet de porc in sos de smantana, ciuperci si vin cu cartofi gratinati', 25.00);


-- INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (7, '','Meniuri', '','', );
-- INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (7, '','Meniuri', '','', );

INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Omlete / Quiche', 'Omleta simpla','oua, smantana, busuioc', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Omlete / Quiche', 'Omleta cu ardei si sparanghel','oua, smantana, ardei, sparanghel, parmezan', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Omlete / Quiche', 'Omleta cu somon si gorgonzola','oua, smantana, somon, gorgonzola', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Omlete / Quiche', 'Quiche cu dovlecei','dovlecei, branza gorgonzola, branza de capra, oua, piper', 17.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de rosii aromata cu busuioc + crutoane','300 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de mazare cu smantana + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de dovlecei cu germeni + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de ardei cu chives + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de linte cu sumac + crutoane','350 gr', 10.00 );
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de broccoli cu smanana + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de ciuperci cu smantana + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Supe', 'Supa crema de legume cu smantana + crutoane','350 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Salata quinoa','spanac, salata, quinoa tricolora, rosii, castraveti, ardei, dressing de spanac si avocado', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Linte cu ciuperci si praz','linte rosie, praz, ciuperci, vin, parmezan', 17.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Legume cu tofu tras la tigaie','tofu, ardei, vinete, dovlecei, ceapa rosie', 19.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Quinoa cu legume provensale','quinoa tricolora, ardei, vinete, dovlecei, rosii cherry, cimbru, vin', 22.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Supa crema de rosii cu busuioc si quinoa','350 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Wrap cu tofu şi avocado','lipie, dressing de spanac si avocado, rucola, spanac, rosii, tofu, avocado - 400 gr (2 buc)', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Guacamole','avocado, ceapa rosie, rosii', 12.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Hummus','naut, pasta de susan, lamaie, ulei extravirgin de masline, rosii, sumac', 12.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Meniu vegetarian', 'Branza degresata cu dulceata dietetica Aroma Zen','200 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Tortilla', 'Tortilla cu legume','tortilla, rosii, castraveti, germeni, morcov, dressing de spanac si avocado', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Tortilla', 'Tortilla cu spanac',' tortilla, spanac, branza gorgonzola', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Tortilla', 'Tortilla cu sunca de curcan','tortilla, sunca, rosii, dressing de spanac si avocado', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Tortilla', 'Tortilla cu somon','tortilla, somon, rosii, parmezan, germeni', 20.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata Explozie de vitamine','avocado, spanac, rosii cherry, salata, morcov, telina, rosii, germeni, ulei extravirgin de masline, sare de Himalaya, lamaie', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata Mix Grande','mix de salata, rosii, rosii cherry, castraveti, ardei, ulei extravirgin de masline', 14.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata de ton','salata verde, rosii, rosii cherry, ton, parmezan, dressing de mustar aromat', 18.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata cu branza de capra','spanac, rucola, branza de capra, ardei, rosii cherry, busuioc, menta, ulei extravirgin de masline', 20.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata de pui asezonata cu mustar','piept de pui, salata verde, rosii, rosii cherry, morcovi, seminte de dovleac, dressing de mustar aromat', 20.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata cu pui crocant',' salata verde, rosii, dovlecei, morcovi, pui crocant, vinegreta', 23.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata Raw','spanac, salata verde, nuci, mere, dressing balsamic', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata cu avocado','spanac, rosii, rosii cherry, avocado, ceapa rosie, patrunjel, lamaie, ulei extravirgin de masline', 19.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata tabouleh','patrunjel, rosii, ceapa rosie, bulgur, lamaie, ulei extravirgin de masline', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata cu somon','valeriana, rucola, somon, rosii, crutoane, ulei extravirgin aromat cu ghimbir', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata greceasca','salata verde, rosii, castraveti, ardei, ceapa rosie, specialitate feta, masline kalamata', 19.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Salate', 'Salata de sfecla','cu dressing de vişine şi coacăze - 200 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Paste', 'Paste cu Somon afumat',' paste din grau dur, somon afumat, parmezan, smantână dulce, gălbenușul unui ou ecologic, sare de mare - 400 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Paste', 'Paste din legume','dovlecei, morcovi, ceapa rosie, ierburi aromate. ardei iute, piper, sare de mare fara iod - 300 gr', 17.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Paste', 'Penne cu ciuperci spanac si nuci','paste din grau dur, ciuperci, spanac, nuci, parmezan, smantana dulce, sare de mare - 400 gr', 23.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Paste', 'Farfalle cu legume si seminte Chia','farfalle din grau dur, dovlecei, vinete, ciuperci, brocoli (produs congelat), rosii cherry, seminte Chia, parmezan, vin, sare de mare fara iod - 400 gr', 23.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Paste', 'Paste pesto',' paste din grau dur, sos pesto, parmezan, sare de mare si piepr - 400 gr', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Peste', 'Pastrav cu rozmarin la cuptor','250 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Peste', 'Somon provensal cu salata','somon file, salata de rosii, ceapa, tarhon, otet balsamic', 33.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Peste', 'Somon provensal pe pat de spanac','somon file, spanac cu ierburi aromate', 33.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Peste', 'Cod cu broccoli aromat cu sos de soia si ghimbir','300 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Pulpe dezosate la tigai','300 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Pulpe dezosate la gratar','300 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Piept de pui cu rozmarin la tigaie','300 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Piept de pui cu cimbru si vin','300 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Pui crocant cu usturoi','pui invelit in crusta crocanta din ierburi aromate si fulgi de ovaz', 21.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Piept de curcan la wok cu cimbru si vin','300 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Curcan cu broccoli, cartofi natur si sos de soia','400 gr', 30.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Curcan cu legume provensale','piept de curcan, ardei, vinete, dovlecei, rosii cherry, cimbru si vin', 29.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Escalop de curcan','piept de curcan, ciuperci, cimbru si vin', 29.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Cotlet de porc cu mustar aromat','300 gr', 20.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Cotlet de porc cu legume la cuptor','300 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'File de porc','300 gr', 16.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'File de porc legume provensale', '350 gr', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Platou mix','piept de pui, file de porc, broccoli, cartofi copti', 32.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Carne', 'Pui in Piper','200 gr', 17.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Legume provensale la wok','dovlecei, vinete, ardei, rosii cherry', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Legume mediteraneene la cuptor','dovlecei, vinete, ardei, ceapa rosie', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Legume la gratar','ovlecei, vinete, ardei, ceapa rosie, rosii cherry, ierburi aromate', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Dovlecei sote cu ardei iute',' dovlecei, ardei, tabasco, vin demisec', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Cartofi copti','200 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Cartofi natur','200 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Cartofi copti cu parmezan','250 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Piure de legume cu seminte de chia','cartofi, morcovi, mazare, ardei, porumb, seminte chia', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Ciuperci la gratar','200 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Broccoli cu sos de soia si ghimbir','200 gr', 11.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Orez basmati cu sparanghel, menta si lamaie','250 gr', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Orez basmati cu legume provensale, cimbru si vin','200 gr', 13.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Orez basmati cu legume mexicane','orez basmati, mazare, fasole verde, fasole galbena, morcov, porumb, parmezan', 13.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Orez tricolor','orez basmati brun integral, ceapa rosie, otet balsamic, patrunjel', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Garnituri', 'Salata Mix','mix de salata, rosii, rosii cherry, castraveti, ardei, ulei extravirgin de masline', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Deserturi', 'Clatite dietetice','clatite din faina integrala cu dulceata Aroma Zen din fructe rosii', 11.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Deserturi', 'Salata de fructe','fructe de sezon, miere de albine, mix de seminte nutritive', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Deserturi', 'Mere caramelizate','mere, nuci, alune crude, zahar brun', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (8, '', 'Deserturi', 'Strudel de mere cu nuci si alune crude','250 gr', 10.00);


INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Meniuri', 'Meniu 1: Piept de pui la gratar cu orez cu legume',' Piept de pui la gratar, orez cu legume, gogosari in otet, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62092.jpg','Meniuri', 'Meniu 2: Aripi de pui picante','Aripi de pui picante, cartofi pai, salata de castraveti murati, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62093.jpg','Meniuri', 'Meniu 3: Pulpe de pui dezosate la gratar','Pulpe de pui dezosate la gratar, orez cu legume, salata de varza, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62094.jpg','Meniuri', 'Meniu 4: Copanele de pui la ceaun',' Copanele de pui la ceaun, cartofi picanti, salata de rosii, castraveti, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62095.jpg','Meniuri', 'Meniu 5: Snitel din piept de pui',' Snitel din piept de pui, piure de cartofi, salata de varza alba cu morcovi, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62096.jpg','Meniuri', 'Meniu 6: Krispy din piept de pui','Krispy din piept de pui, cartofi prajiti, salata de varza, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62097.jpg','Meniuri', 'Meniu 7: Parjolute din piept de pui','Parjolute din piept de pui, piure de cartofi, salata de castraveti murati, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62098.jpg','Meniuri', 'Meniu 8: Cotlet de porc la gratar','Cotlet de porc la gratar, cartofi pai, salata de rosii si castraveti, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62099.jpg','Meniuri', 'Meniu 9: Ceafa de porc la gratar','Ceafa de porc la gratar, cartofi pai, salata de castraveti murati, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62100.jpg','Meniuri', 'Meniu 10: Mici la gratar','Mici la gratar, cartofi pai, mustar, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62101.jpg','Meniuri', 'Meniu 11: Carnaciori proaspeti la gratar','Carnaciori proaspeti la gratar, piure de cartofi, salata de gogosari in otet, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62102.jpg','Meniuri', 'Meniu 12: Carnati traditionali la gratar','Carnati traditionali la gratar, piure de cartofi, salata de gogosari in otet, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/77207.jpg','Meniuri', 'Meniu 13: Piept de pui la gratar cu cartofi pai','Piept de pui la gratar, cartofi pai, salata asortata, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/77208.jpg','Meniuri', 'Meniu 14: Piept de pui la gratar cu amestec mexican','Piept de pui la gratar, amestec mexican, salata de varza, paine', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62135.jpg','Salate', 'Salata piept de pui','Salata asortata 200gr, piept de pui 80gr, masline 50gr, branza feta 50gr, porumb 30gr, lamaie 20gr, maioneza de casa 50gr + o bautura Somersby la 330 ml din partea casei', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62136.jpg','Salate', 'Salata ton','Salata asortata 200gr, ton 80gr, masline 50gr, ceapa rosie 30gr, porumb 30gr, lamaie 20gr, maioneza de casa 50gr + o bautura Somersby la 330 ml din partea casei', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62137.jpg','Salate', 'Salata bulgareasca','Salata asortata 200gr, sunca 80gr, masline 50gr, branza feta 50gr, ou, maioneza casa 50gr + o bautura Somersby la 330 ml din partea casei', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62138.jpg','Salate', 'Salata greceasca','Salata asortata 200gr, ceapa rosie 30gr, ardei gras 30gr, masline 50gr, branza feta 50gr, sos tzatiky 50gr + o bautura Somersby la 330 ml din partea casei', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62139.jpg','Salate', 'Salata crispy','Salata asortata 200gr, crispy 80gr, masline 50gr, branza feta 50gr, maioneza casa 50gr + o bautura Somersby la 330 ml din partea casei', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62127.jpg','Sandvisuri', 'Sandvis piept de pui','Piept de pui, cartofi pai, salata asortata, paine, sosuri', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62128.jpg','Sandvisuri', 'Sandvis sunca','Sunca, cascaval, cartofi pai, salata asortata, paine, sosuri', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62129.jpg','Sandvisuri', 'Sandvis pastrama','Muschi de porc, cascaval, cartofi pai, salata asortata, paine, sosuri', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62130.jpg','Sandvisuri', 'Sandvis ton','Ton, ceapa rosie, lamaie, salata asortata, paine, sosuri', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62131.jpg','Sandvisuri', 'Sandvis crispy',' Crispy, cartofi pai, salata sortata, paine, sosuri', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62132.jpg','Sandvisuri', 'Sandvis snitel','Snitel piept de pui, cartofi pai, salata asortata, paine, sosuri', .00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62133.jpg','Sandvisuri', 'Super Sandvis','Ceafa de porc, carnati, cartofi pai, salata asortata, paine, sosuri', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/62134.jpg','Sandvisuri', 'Crispy lipie','Crispy, cartofi pai, salata, lipie, sosuri', 12.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, 'https://images.deliveryhero.io/image/fd-ro/Products/194312.jpg','Sandvisuri', 'Sandvis Take&amp;eat','Prosciuto crudo, rosii cherry, salata verde, gorgonzola, mozarella, turta de casa', 14.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Crudo e rucola','sos pizza, mozarella bufala, prosciutto crudo, rosii cherry, rucola, parmezan', 30.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Rimini','sos pizza, mozzarella, bufala, ansoa, oregnao', 30.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Romana','sos pizza, mozzarella, ansoa, masline, capere, oregano', 27.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Picante','sos picant, mozzarella bufala, salam picant, busuioc. *PICANT', 30.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Take &amp; Eat','sos pizza, mozzarella, prosciutto cotto, salam Milano, piept pui, ardei, porumb, ciuperci', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Quattro Stagioni','sos pizza, mozzarella, prosciutto cotto, anghinare, ciuperci,masline', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Capriciosa','sos pizza, mozzarella, sunca, ardei ,masline, ciuperci', 23.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Pollo &amp; Funghi','sos pizza, mozzarella, piept pui, ciuperci', 23.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Chicken','sos pizza, mozzarella, piept pui, porumb, pancetta', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Diavola','Sos pizza, mozarella, salam uscat, pepperoni', 26.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Milanese','sos pizza, mozzarella, salam Milano, ciuperci, rucola', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Contadino','sos pizza, mozarella, pancetta, salsiccia, prosciutto cotto, ceapa, ardei', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Tono Cipolla','sos pizza, mozzarella, ton, ceapa, rosii cherry', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Prosciutto e Funghi','sos pizza, mozzarella, prosciutto cotto, ciuperci', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Italiano','sos pizza, mozzarella, prosciutto cotto, vinete, parmesan', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Salsiccia cipola','sos pizza, mozzarella, salsiccia, ceapa', 22.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Vegetariano','sos pizza, mozzarella / cascaval de post, ciuperci, vinete, dovleci, rosii cherry', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Calzone vegetariano','', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Salsiccia e pancetta','sos alb, salsiccia, pancetta, porumb, usturoi', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Mantova','sos pizza, mozzarella, salam picant gorgonzola *PICANT', 26.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Calabria','sos pizza, mozzarella, cascaval afumat, salam picant. *PICANT', 25.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Bambino','sos pizza, mozzarella, prosciutto cotto, ananas', 24.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Pizza', 'Pizza Pollo e uovo','sos pizza, mozzarella, piept de pui, gorgonzola, rosii, ou', 25.00);

INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Desert', 'Clatite cu dulceata','clatite, dulceata - 250 gr', 8.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Desert', 'Clatite cu Nutella','Clatite, Nutella - 250 gr', 10.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Desert', 'Pizza cu Nutella','blat pizza, Nutella - 360 gr', 15.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Desert', 'Papanasi cu branza si dulceata','branza de vaci, smantana, dulceata - 300 gr', 12.00);
INSERT INTO `product` (`idRestaurant`,`image`,`category`,`name`,`description`,`price`) VALUES (9, '','Desert', 'Papanasi cu Nutella','branza de vaci, smantana, Nutella - 300 gr', 15.00);
-- pana aici
select * from `product`;
-- delete from product where idRestaurant=6;


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
	`id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nrProducts` int(3),
	`idUser`bigint(20),
	`idProduct` bigint(20),
	`idShoppingCart` bigint(20),
	FOREIGN KEY fk_idProduct_scp(idProduct) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY fk_idUser_scp(idUser) REFERENCES user(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY fk_idCart_scp(idShoppingCart) REFERENCES shopping_cart(id) ON UPDATE CASCADE ON DELETE RESTRICT
);
ALTER TABLE `shopping_cart_products` ENGINE = InnoDB;

-- DATETIME format : YYYY-MM-DD HH:MM:SS

INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (70, '2018-05-01 10:08:45', '2018-05-01 10:08:45', '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (80, '2018-04-21 20:50:09', '2018-04-21 20:50:09', '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (78, '2018-04-23 12:45:30', '2018-04-23 12:45:30', '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (0, '2018-04-23 12:45:30', null, '1');
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (0, '2018-05-16 09:30:09', null, '2');

INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 1, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 2, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 1);

INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 2);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 5); 

-- La un shopping cart pot contribui mai multi useri. Aici vad care user a adaugat in cos.

select * from `shopping_cart`;
select * from `shopping_cart_products` where idShoppingCart=1;
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

-- Un cos apartine unuia sau mai multor useri
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (2 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (3 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (4 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (2 , 5);

INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (1 , 2);




select * from `shopping_cart_users`;
select * from `user`;


SELECT SHA2('abc', 256);


select sc.id from shopping_cart sc join shopping_cart_users scu on sc.id = scu.idShoppingCart where sc.totalPrice=0 and scu.idUser=2








