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
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1lt-listing.jpg','Times','Strada Codrescu, nr 6', 3,'Carne, Burgeri, Pizza, Vegetariana, Bucatarie Internationala');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v1ur-listing.jpg','BigBur',' Aleea Nicolina, nr. 13, Iași 700259', 4,'Burgeri, Mâncare Romaneasca, Pizza, Bucătarie internațională');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v4ri-listing.jpg','Restaurant Bavaria','Bulevardul Nicolae Iorga, nr.2A, Iasi', 5 ,'Bucatarie Internationala, Gratar, Pui, Vegetariana, Vita');
INSERT INTO `restaurant` (`img`,`name`,`address`,`stars`,`description`) VALUES ('https://images.deliveryhero.io/image/fd-ro/LH/v8vy-listing.jpg','Pizzeria Mady s',' Strada Petre Tutea, nr. 913, Iasi',6 ,'Quick Service Restaurant, Mancare Romaneasca, Pizza, Bucatarie Internationala, Gratar');


/*Pizzeria Mady
*Quick Service Restaurant, Mancare Romaneasca, Pizza, Bucatarie Internationala, Gratar
Strada Petre Tutea, nr. 913, Iasi*/

/* 4 Big Bur

delete from `restaurant` where id=6
 */

select * from `restaurant`;


/*------------------------------------------------------------------PRODUCTS-----------------------------------------------------------------------------------------------*/
 
drop table `product`

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


INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Burgeri', 'American Burger', 'paine brioche, carne vita, bacon, branza ceddar, salata iceberg, ceapa rosie, castraveti in otet, ardei iuti in otet, rosii, sos aioli', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Burgeri', 'Times Burger ', 'paine brioche, carne de vita, bacon, branza ceddar, salata iceberg, ceapa rosie, castraveti in otet, parmezam, crispy chips, crispy onion, sos aioli', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Burgeri', 'Marmelade Burger', 'paine brioche, carne vita, bacon, blue cheese, salata iceberg, marmelada din ceapa rosie, blue cheese sauce', 21.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Burgeri', 'Crispy Chicken Burger', 'paine brioche, snitel de pui in pesmet, castraveti in otet, sos aioli', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Burgeri', 'Veggie Burger', 'paine brioche, branza halloumi, salata iceberg, vanata grill, castraveti in otet, ardei iuti in otet, rosii, sos blue cheese', 17.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza Times', 'aluat italian, sos pizza, mozzarella, sunzam salam, ciuperci, ardei, rosii, masline(700 gr)', 22.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza Quattro Stagioni ', 'aluat italian, sos pizza, mozzarella, șuncă, salam, ceapă, ciuperci (600 gr)', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza Prosciutto Cotto e Funghi', 'aluat italian, sos pizza, mozzarella, prosciutto cotto, ciuperci, rucola', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza Quattro Formagi', 'aluat italian, mozzarella, parmezan, gorgonzola, brânză brie (600 gr)', 27.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza Salami Picant', 'aluat italian, sos pizza, mozzarella, salam picant, ardei (550 gr)', 24.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3,'Pizza', 'Pizza al Tono', ' aluat italian, sos pizza, mozzarella, ton, porumb, masline, lamaie (550 gr)', 24.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', 'Times&#039; Special', 'coaste de porc marinate, gătite la cuptor și servite cu sos BBQ, garnitură de cartofi rondele și salată coleslaw **alergeni: gluten, ouă, lapte', 19.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', 'Times Schnitel ', 'piept de pui, ou, lapte, pesmet, cartofi (wedges/pai) (400 g)', 20.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Specialitati', ' Aripioare Buffalo', ' 8 aripioare picante, cartofi wedges, sos blue cheese, telina', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Salată de vinete (cu maioneză)', 'vinete, ceapă roșie, maioneză și pâine prăjită', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Avocado hummus', 'servit cu lipie și legume crude (200g)', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Vegetarian Choice', 'Platou de brânzeturi fine', 'blue cheese, brie, parmesan - servit cu struguri, nuci și biscuit pentru brânză **alergeni: gluten, mustar, ouă, lapte, nuci', 27.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Platouri', 'Beast Mode', 'Coaste de porc cu sos bbq, aripioare de pui, crispy de pui, frigărui din piept de pui, bile din mozarella, cartofi wedges, sos mujdei și sos picant (deservește 3 - 4 persoane) **alergeni: gluten, muștar, ouă, lapte (1200 g)', 95.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Platouri', 'Vegetarian Platter', 'Inele de ceapă, avocado hummus, bile din mozzarella, salată de vinete cu maioneză, cartofi wedges sau pai, sos maioneză cu usturoi și sos picant (deservește 3 - 4 persoane) **alergeni: gluten, muștar, ouă, lapte', 80.00);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Salate', 'salata iceberg, piept de pui, rosii cherry, crutoane, parmezan, dressing caesar', 21.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Cartofi pai', '(140g)', 8.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Cartofi pai cu parmezan și usturoi', '(140g)', 9.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Diverse', 'Chifla', '(75 g)', 1.50);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Desert', 'Chesse Cake', 'servit cu dulceață de casă (100g/30g)(75 g)', 16.50);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (3, 'Desert', 'Lava cake', 'servit cu o cupă de înghețată (130g)', 16.50);

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Prosciutto', 'Blat Italian, Mozzarella, Sos salsa, jambon, condimente 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Prosciutto Funghi', 'Blat Italian, mozzarella, sos salsa, jambon, ciuperci, condimente 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Zucchini', 'Blat Italian, mozzarella, sos salsa, vinete, dovlecei zucchini, ardei gras, condimente 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Capriciosa', 'Blat Italian, mozzarella, sos salsa ciuperci, anghinare, condimente 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Calzone', 'Blat Italian, mozzarella, sos salsa, jambon, ciuperci, condimente - 600gr', 18.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Pizza', 'Pizza Quattro Stagione', 'Blat Italian, mozzarela sos salsa, salam, jambon, ciuperci, masline, condimente. 600g', 18.00);
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
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Vegetarian', 'ciuperci, ardei kapia, masline, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Fish', 'peste pane, cedar, salata, ceapa, sos alb - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Tonno', 'ton, ou, masline verzi, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Krispy', 'pui krispy, cedar, salata, ceapa, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Toast', 'cedar, ou, ciuperci, ardei, bacon, sunca, salata, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Fresh', 'cedar, ou, ciuperci, bacon, sunca, salata, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Bigburger Pui', 'burger de pui, cedar, salata, rosii, ceapa, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Bigburger Vita', 'burger de vita, cedar, salata, rosii, ceapa, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Doner Kebab Pui', 'pui, cedar, salata, rosii, castraveti, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Doner Kebab Vita', 'vita, cedar, salata, rosii, castraveti, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Sandwich Prosciutto Crudo', 'prosciutto crudo, cedar, salata, rosii, castraveti, sos - sandwich mic 280 gr sau sandwich mare 400 gr', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'BigBur', 'Paine cu susan, bacon, kebap de pui, branza topita, ceapa, rosii, salata, castraveti murati, ketchup si maioneaza de casa.', 7.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (4, 'Burgeri', 'Shaorma de vita', 'lipie, vita, cartofi, varza, castraveti, sos - 400 gr', 13.00);


/*Restaurant Bavaria
*Bucatarie Internationala, Gratar, Pui, Vegetariana, Vita
*Bulevardul Nicolae Iorga, nr.2A, Iasi
*/

INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Muschi de vita in sos de hribi', ' muschi de vita, hribi, smantana, gran cuccina, rosii - 200/150 gr', 55.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Duetul casei', 'muschi de vita, muschiuler de porc, ciuperci, smantana, ceapa, usturoi, gran cucina - 250/150 gr', 45.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', 'Pui cu mozzarella si legume basque', ' piept de pui, mozzarella, pesmet, legume basque, ardei gras, vinete, dovlecel, morcovi - 200/250 gr', 29.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Specialitatea restaurantului', ' Pui taranesc cu mamaliguta', 'piept de pui, ardei gras, ceapa, morcov, rosii, cartofi, mamaliguta - 300/200 gr', 25.00);
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
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (5, 'Ciorbe', ' Ciorba de fasole', '350 gr', 10.00);
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
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Platouri - 2 persoane', 'Platou  Madys', 'ceafa de porc, 2 mici, jambon, carnaciori picanti, cartofi prajiti, castraveti murati', 40.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Platouri', 'Platoul Ucenicului', 'aripioare, frigarui pui, mici 2, cartofi prajiti, salata de varza', 25.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata Madys', 'piept de pui, sunca praga, rosii, castraveti, salata verde, ardei gras, ceapa, crutoane, masline', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata greceasca', 'rosii, ceapa, salata verde, ardei gras, ceapa, piept de pui, branza feta', 16.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata bulgareasca', 'rosii, salata verde,ardei gras, ceapa, masline, sunca, branza feta, ou', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata cu ton', 'rosii, salata verde, castraveti, ceapa, ardei gras, ton', 14.00);
INSERT INTO `product` (`idRestaurant`,`category`,`name`,`description`,`price`) VALUES (6, 'Salate', 'Salata de sezon', 'rosii, ceapa, castraveti', 4.00);


select * from `product`;
delete from product where idRestaurant=6


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
INSERT INTO `shopping_cart` (`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`) VALUES (0, '2018-04-23 12:45:30', null, '1');

INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 1, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 2, 1);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 1);

INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 2);
INSERT INTO `shopping_cart_products` (`nrProducts`, `idUser`, `idProduct`, `idShoppingCart`) VALUES (3, 3, 3, 5);

-- La un shopping cart pot contribui mai multi useri. Aici vad care user a adaugat in cos.

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

-- Un cos apartine unuia sau mai multor useri
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (2 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (3 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (4 , 1);
INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (2 , 5);

INSERT INTO `shopping_cart_users` (`idUser`, `idShoppingCart`) VALUES (1 , 2);


select * from `shopping_cart_users`;
select * from `user`;


SELECT SHA2('abc', 256);


select sc.id, sc. from shopping_cart sc join shopping_cart_users scu on sc.id = scu.idShoppingCart where sc.totalPrice=0 and scu.idUser=2









