CREATE TABLE `book` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `bookAuthor` int(11) NOT NULL,
  `bookGenre` int(11) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `year` year(4) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`idBook`),
  KEY `author_id_idx` (`bookAuthor`),
  KEY `genre_id_idx` (`bookGenre`),
  CONSTRAINT `author_id` FOREIGN KEY (`bookAuthor`) REFERENCES `author` (`idAuthor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `genre_id` FOREIGN KEY (`bookGenre`) REFERENCES `genre` (`idGenre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8
