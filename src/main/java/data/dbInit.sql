DROP DATABASE IF EXISTS cupcakeshop;
CREATE DATABASE cupcakeshop;
USE cupcakeshop;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
`userid` INT NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(25), 
`password` VARCHAR(25), 
`email` VARCHAR(35), 
`balance` DOUBLE,
PRIMARY KEY(`userid`)
);

DROP TABLE IF EXISTS `bottoms`;
CREATE TABLE `bottoms` (
`bname` VARCHAR(25), 
`bprice` DOUBLE,
PRIMARY KEY(`bname`)
);

DROP TABLE IF EXISTS `toppings`;
CREATE TABLE `toppings` (
`tname` VARCHAR(25), 
`tprice` DOUBLE,
PRIMARY KEY(`tname`)
);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
`ordernr` INT NOT NULL AUTO_INCREMENT, 
`fk_userid` INT, 
`ispaid` BOOLEAN DEFAULT FALSE, 
`orderdate` DATE,
PRIMARY KEY(`ordernr`),
FOREIGN KEY(`fk_userid`) REFERENCES `users`(`userid`)
);

DROP TABLE IF EXISTS `orderlines`;
CREATE TABLE `orderlines` (
`fk_ordernr` INT,
`fk_bname` VARCHAR(25), 
`fk_tname` VARCHAR(25), 
`quantity` INT, 
`price` DOUBLE,
PRIMARY KEY(`fk_ordernr`, `fk_bname`, `fk_tname`),
FOREIGN KEY(`fk_ordernr`) REFERENCES `orders`(`ordernr`),
FOREIGN KEY(`fk_bname`) REFERENCES `bottoms`(`bname`),
FOREIGN KEY(`fk_tname`) REFERENCES `toppings`(`tname`)
);


INSERT INTO `users` (`userid`, `username`, `password`, `email`, `balance`)
VALUES
(null, 'Admin', '1234', 'Admin@CupCake.com', '666'),
(null, 'Rasmus', 'Lynge', 'Rasmus@Lynge.com', '69');

INSERT INTO `bottoms` (`bname`, `bprice`)
VALUES
('Chocolate', '5.0'),
('Vanilla', '5.0'),
('Nutmeg', '5.0'),
('Pistacio', '6.0'),
('Almond', '7.0');

INSERT INTO `toppings` (`tname`, `tprice`)
VALUES
('Chocolate', '5.0'),
('Blueberry', '5.0'),
('Rasberry', '5.0'),
('Crispy', '6.0'),
('Strawberry', '6.0'),
('Rum/Rasin', '7.0');


INSERT INTO `orders`(`ordernr`, `fk_userid`, `ispaid`, `orderdate`)
VALUES
(null, 1, true, '2018-01-01'),
(null, 1, false, '2018-02-01');

INSERT INTO `orderlines`(`fk_ordernr`, `fk_bname`, `fk_tname`, `quantity`, `price`)
VALUES
(1, 'Chocolate', 'Crispy', 4, 10.0),
(1, 'Vanilla', 'Crispy', 4, 10.0),
(2, 'Chocolate', 'Crispy', 4, 10.0);