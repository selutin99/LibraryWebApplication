CREATE TABLE `author` 
(
  `idAuthor` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Table with authors'
