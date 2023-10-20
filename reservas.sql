-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema reservas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reservas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reservas` DEFAULT CHARACTER SET utf8 ;
USE `reservas` ;

-- -----------------------------------------------------
-- Table `reservas`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`casa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`casa` (
  `idcasa` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `local` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `ranking` INT NULL,
  PRIMARY KEY (`idcasa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservas`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservas`.`reserva` (
  `idreserva` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `morada1` VARCHAR(60) NOT NULL,
  `morada2` VARCHAR(60) NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `distrito` VARCHAR(45) NOT NULL,
  `codpostal` VARCHAR(45) NOT NULL,
  `datainicio` DATE NOT NULL,
  `datafim` DATE NOT NULL,
  `user_iduser` INT NOT NULL,
  `casa_idcasa` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idreserva`),
  INDEX `fk_reserva_user_idx` (`user_iduser` ASC) ,
  INDEX `fk_reserva_casa1_idx` (`casa_idcasa` ASC) ,
  CONSTRAINT `fk_reserva_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `reservas`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_casa1`
    FOREIGN KEY (`casa_idcasa`)
    REFERENCES `reservas`.`casa` (`idcasa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;