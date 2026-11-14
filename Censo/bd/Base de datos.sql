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
-- Table `censo`.`tipo_barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`tipo_barrio` (
  `ID_tipo_barrio` INT NOT NULL,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  `Cant_monoblock` INT NULL DEFAULT NULL,
  `Macro_activo` BIT(1) NULL DEFAULT NULL,
  `Cant_edificios` INT NULL DEFAULT NULL,
  `Porcentaje_asf` VARCHAR(67) NULL DEFAULT NULL,
  `Nro_ley` VARCHAR(20) NULL DEFAULT NULL,
  `Conexion_agua` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_tipo_barrio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`barrio` (
  `ID_barrio` INT NOT NULL,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `ID_tipo_barrio` INT NOT NULL,
  `ID_Comuna` INT NOT NULL,
  PRIMARY KEY (`ID_barrio`),
  INDEX `fk_Barrio_Tipo_barrio_idx` (`ID_tipo_barrio` ASC) VISIBLE,
  INDEX `fk_Barrio_Comuna1_idx` (`ID_Comuna` ASC) VISIBLE,
  CONSTRAINT `fk_Barrio_Comuna1`
    FOREIGN KEY (`ID_Comuna`)
    REFERENCES `censo`.`comuna` (`ID_comuna`),
  CONSTRAINT `fk_Barrio_Tipo_barrio`
    FOREIGN KEY (`ID_tipo_barrio`)
    REFERENCES `censo`.`tipo_barrio` (`ID_tipo_barrio`))
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
  `ID_Situacion_Laboral` INT NOT NULL,
  `ID_Barrio` INT NOT NULL,
  PRIMARY KEY (`ID_persona`),
  INDEX `fk_Persona_Situacion_laboral1_idx` (`ID_Situacion_Laboral` ASC) VISIBLE,
  INDEX `fk_Persona_Barrio1_idx` (`ID_Barrio` ASC) VISIBLE,
  CONSTRAINT `fk_Persona_Barrio1`
    FOREIGN KEY (`ID_Barrio`)
    REFERENCES `censo`.`barrio` (`ID_barrio`),
  CONSTRAINT `fk_Persona_Situacion_laboral1`
    FOREIGN KEY (`ID_Situacion_Laboral`)
    REFERENCES `censo`.`situacion_laboral` (`ID_Situacion_laboral`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`historial` (
  `ID_persona` INT NOT NULL,
  `ID_historial` INT NOT NULL,
  `Fecha_traslado` DATE NOT NULL,
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
