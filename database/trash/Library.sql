-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Library
-- -----------------------------------------------------
-- Test task for Haulmont

-- -----------------------------------------------------
-- Schema Library
--
-- Test task for Haulmont
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Library` DEFAULT CHARACTER SET utf8 ;
USE `Library` ;

-- -----------------------------------------------------
-- Table `Library`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`Author` (
  `idAuthor` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `lastName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `patronymic` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL,
  PRIMARY KEY (`idAuthor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`Genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`Genre` (
  `idGenre` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`Book` (
  `idBook` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `bookAuthor` INT NOT NULL,
  `bookGenre` INT NOT NULL,
  `publisher` VARCHAR(45) NOT NULL,
  `year` YEAR NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idBook`),
  INDEX `author_id_idx` (`bookAuthor` ASC),
  INDEX `genre_id_idx` (`bookGenre` ASC),
  CONSTRAINT `author_id`
    FOREIGN KEY (`bookAuthor`)
    REFERENCES `Library`.`Author` (`idAuthor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `genre_id`
    FOREIGN KEY (`bookGenre`)
    REFERENCES `Library`.`Genre` (`idGenre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;