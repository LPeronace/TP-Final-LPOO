-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema censo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema censo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `censo` DEFAULT CHARACTER SET utf8mb3 ;
USE `censo` ;

-- -----------------------------------------------------
-- Table `censo`.`comuna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`comuna` (
  `ID_comuna` INT NOT NULL,
  `Nombre_sede` VARCHAR(45) NOT NULL,
  `Numero_Comuna` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ID_comuna`),
  UNIQUE INDEX `uk_comuna_numero` (`Numero_Comuna` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`barrio` (
  `ID_barrio` INT NOT NULL,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `Comuna_ID_comuna` INT NOT NULL,
  `Cant_monoblock` VARCHAR(4) NULL DEFAULT NULL,
  `Macro_activo` BIT(1) NULL DEFAULT NULL,
  `Cant_edificios` INT NULL DEFAULT NULL,
  `Porcentaje_asf` VARCHAR(67) NULL DEFAULT NULL,
  `Nro_ley` VARCHAR(20) NULL DEFAULT NULL,
  `Conexion_agua` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_barrio`),
  INDEX `fk_Barrio_Comuna1_idx` (`Comuna_ID_comuna` ASC) VISIBLE,
  CONSTRAINT `fk_Barrio_Comuna1`
    FOREIGN KEY (`Comuna_ID_comuna`)
    REFERENCES `censo`.`comuna` (`ID_comuna`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`situacion_laboral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`situacion_laboral` (
  `ID_Situacion_laboral` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Situacion_laboral`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`persona` (
  `DNI` VARCHAR(20) NOT NULL,
  `ID_persona` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Fecha_nacimiento` DATE NOT NULL,
  `Situacion_laboral_ID_Situacion_laboral` INT NOT NULL,
  `Barrio_ID_barrio` INT NOT NULL,
  PRIMARY KEY (`ID_persona`),
  INDEX `fk_Persona_Situacion_laboral1_idx` (`Situacion_laboral_ID_Situacion_laboral` ASC) VISIBLE,
  INDEX `fk_Persona_Barrio1_idx` (`Barrio_ID_barrio` ASC) VISIBLE,
  CONSTRAINT `fk_Persona_Barrio1`
    FOREIGN KEY (`Barrio_ID_barrio`)
    REFERENCES `censo`.`barrio` (`ID_barrio`),
  CONSTRAINT `fk_Persona_Situacion_laboral1`
    FOREIGN KEY (`Situacion_laboral_ID_Situacion_laboral`)
    REFERENCES `censo`.`situacion_laboral` (`ID_Situacion_laboral`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`historial` (
  `ID_persona` INT NOT NULL,
  `Fecha_traslado` DATE NOT NULL,
  `ID_historial` INT NOT NULL,
  PRIMARY KEY (`ID_persona`, `ID_historial`),
  INDEX `fk_Historial_Persona_idx` (`ID_persona` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Persona`
    FOREIGN KEY (`ID_persona`)
    REFERENCES `censo`.`persona` (`ID_persona`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
