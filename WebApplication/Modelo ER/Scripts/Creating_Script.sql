SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `AhorcadoOSApp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `AhorcadoOSApp` ;

-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`CATEGORY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AhorcadoOSApp`.`CATEGORY` (
  `name` VARCHAR(25) NOT NULL ,
  `image_name` VARCHAR(35) NOT NULL ,
  PRIMARY KEY (`name`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`PLAYER`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AhorcadoOSApp`.`PLAYER` (
  `user_name` VARCHAR(35) NOT NULL ,
  `password` VARCHAR(35) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`user_name`) ,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`WORD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AhorcadoOSApp`.`WORD` (
  `name` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`name`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`CATEGORY_WORDS`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AhorcadoOSApp`.`CATEGORY_WORDS` (
  `category` VARCHAR(25) NOT NULL ,
  `word` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`category`, `word`) ,
  INDEX `fk_WORDS_CATEGORY_WORD1_idx` (`word` ASC) ,
  CONSTRAINT `fk_WORDS_CATEGORY_CATEGORY`
    FOREIGN KEY (`category` )
    REFERENCES `AhorcadoOSApp`.`CATEGORY` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WORDS_CATEGORY_WORD1`
    FOREIGN KEY (`word` )
    REFERENCES `AhorcadoOSApp`.`WORD` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`RECORD`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AhorcadoOSApp`.`RECORD` (
  `user_name` VARCHAR(35) NOT NULL ,
  `category` VARCHAR(25) NOT NULL ,
  `word` VARCHAR(15) NOT NULL ,
  `points` INT UNSIGNED NOT NULL ,
  `date` DATE NOT NULL ,
  PRIMARY KEY (`user_name`, `category`, `word`) ,
  INDEX `fk_RECORD_CATEGORY_WORDS1_idx` (`category` ASC, `word` ASC) ,
  CONSTRAINT `fk_RECORD_PLAYER1`
    FOREIGN KEY (`user_name` )
    REFERENCES `AhorcadoOSApp`.`PLAYER` (`user_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RECORD_CATEGORY_WORDS1`
    FOREIGN KEY (`category` , `word` )
    REFERENCES `AhorcadoOSApp`.`CATEGORY_WORDS` (`category` , `word` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `AhorcadoOSApp` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
