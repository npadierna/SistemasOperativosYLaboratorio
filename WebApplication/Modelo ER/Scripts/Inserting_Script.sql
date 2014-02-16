-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`CATEGORY`
-- -----------------------------------------------------
INSERT INTO `AhorcadoOSApp`.`CATEGORY` (`name`, `image_name`)
	VALUES (NULL, NULL);


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`PLAYER`
-- -----------------------------------------------------
INSERT INTO `AhorcadoOSApp`.`PLAYER` (`user_name`, `password`, `email`)
	VALUES (NULL, NULL, NULL);


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`WORD`
-- -----------------------------------------------------
INSERT INTO `AhorcadoOSApp`.`WORD` (`name`)
	VALUES (NULL);


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`CATEGORY_WORDS`
-- -----------------------------------------------------
INSERT INTO `AhorcadoOSApp`.`CATEGORY_WORDS` (`category`, `word`)
	VALUES (NULL, NULL);


-- -----------------------------------------------------
-- Table `AhorcadoOSApp`.`RECORD`
-- -----------------------------------------------------
INSERT INTO `AhorcadoOSApp`.`RECORD` (`category`, `word`, `points`, `date`, `player_user_name`)
	VALUES (NULL, NULL, NULL, NULL, NULL);