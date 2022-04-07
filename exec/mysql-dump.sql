-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boonmoja
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema boonmoja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boonmoja` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `boonmoja` ;

-- -----------------------------------------------------
-- Table `boonmoja`.`contents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`contents` (
  `contents_id` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `img_path` VARCHAR(255) NULL DEFAULT NULL,
  `introduction` VARCHAR(1000) NULL DEFAULT NULL,
  `label` VARCHAR(255) NULL DEFAULT NULL,
  `latitude` VARCHAR(255) NULL DEFAULT NULL,
  `longitude` VARCHAR(255) NULL DEFAULT NULL,
  `phone_no` VARCHAR(255) NULL DEFAULT NULL,
  `post_code` VARCHAR(255) NULL DEFAULT NULL,
  `road_address` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail_path` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`contents_id`),
  UNIQUE INDEX `UK_en0utx3suqotcyyt9okr1mje2` (`title` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`user` (
  `user_seq` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NOT NULL,
  `email` VARCHAR(512) NOT NULL,
  `modified_at` DATETIME(6) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `provider_type` VARCHAR(20) NULL DEFAULT NULL,
  `role_type` VARCHAR(20) NOT NULL,
  `user_id` VARCHAR(64) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE INDEX `UK_a3imlf41l37utmxiquukk8ajc` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`travel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`travel` (
  `travel_seq` BIGINT NOT NULL AUTO_INCREMENT,
  `end_at` DATE NULL DEFAULT NULL,
  `is_finished` BIT(1) NULL DEFAULT NULL,
  `start_at` DATE NULL DEFAULT NULL,
  `travel_title` VARCHAR(255) NOT NULL,
  `user_seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`travel_seq`),
  INDEX `FKk7xq4arrvx5h9u4jfp1uk9ouh` (`user_seq` ASC) VISIBLE,
  CONSTRAINT `FKk7xq4arrvx5h9u4jfp1uk9ouh`
    FOREIGN KEY (`user_seq`)
    REFERENCES `boonmoja`.`user` (`user_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`day`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`day` (
  `day_seq` BIGINT NOT NULL AUTO_INCREMENT,
  `day` INT NULL DEFAULT NULL,
  `travel_seq` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`day_seq`),
  INDEX `FK4qv12pgq5bnokcy9x7kwv9vyb` (`travel_seq` ASC) VISIBLE,
  CONSTRAINT `FK4qv12pgq5bnokcy9x7kwv9vyb`
    FOREIGN KEY (`travel_seq`)
    REFERENCES `boonmoja`.`travel` (`travel_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`day_contents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`day_contents` (
  `day_contents_seq` BIGINT NOT NULL AUTO_INCREMENT,
  `day_contents_no` INT NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `day_seq` BIGINT NOT NULL,
  PRIMARY KEY (`day_contents_seq`),
  INDEX `FKas5g5q2wxj0wu8suearmblfa4` (`title` ASC) VISIBLE,
  INDEX `FK717tk6cn337ni9j94gos3eaab` (`day_seq` ASC) VISIBLE,
  CONSTRAINT `FK717tk6cn337ni9j94gos3eaab`
    FOREIGN KEY (`day_seq`)
    REFERENCES `boonmoja`.`day` (`day_seq`),
  CONSTRAINT `FKas5g5q2wxj0wu8suearmblfa4`
    FOREIGN KEY (`title`)
    REFERENCES `boonmoja`.`contents` (`title`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`user_contents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`user_contents` (
  `user_contents_seq` BIGINT NOT NULL AUTO_INCREMENT,
  `contents_id` VARCHAR(255) NULL DEFAULT NULL,
  `user_seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_contents_seq`),
  INDEX `FKn4jmyvaq9wsksdp3av0230sur` (`contents_id` ASC) VISIBLE,
  INDEX `FK77b9f46samb5f1w57rpdpu87x` (`user_seq` ASC) VISIBLE,
  CONSTRAINT `FK77b9f46samb5f1w57rpdpu87x`
    FOREIGN KEY (`user_seq`)
    REFERENCES `boonmoja`.`user` (`user_seq`),
  CONSTRAINT `FKn4jmyvaq9wsksdp3av0230sur`
    FOREIGN KEY (`contents_id`)
    REFERENCES `boonmoja`.`contents` (`contents_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boonmoja`.`user_refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boonmoja`.`user_refresh_token` (
  `refresh_token_seq` BIGINT NOT NULL AUTO_INCREMENT,
  `refresh_token` VARCHAR(256) NOT NULL,
  `user_id` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`refresh_token_seq`),
  UNIQUE INDEX `UK_qca3mjxv5a1egwmn4wnbplfkt` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
