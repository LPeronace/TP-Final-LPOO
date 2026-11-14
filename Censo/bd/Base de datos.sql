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
  `ID_comuna` INT NOT NULL AUTO_INCREMENT,
  `Nombre_sede` VARCHAR(45) NOT NULL,
  `Numero_Comuna` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ID_comuna`),
  UNIQUE INDEX `uk_comuna_numero` (`Numero_Comuna` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`barrio` (
  `ID_barrio` INT NOT NULL AUTO_INCREMENT,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `Comuna_ID_comuna` INT NOT NULL,
  `Cant_monoblock` VARCHAR(4) NULL DEFAULT NULL,
  `Macro_activo` BIT(1) NULL DEFAULT NULL,
  `Cant_edificios` INT NULL DEFAULT NULL,
  `Porcentaje_asf` INT NULL DEFAULT NULL,
  `Nro_ley` VARCHAR(20) NULL DEFAULT NULL,
  `Conexion_agua` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_barrio`),
  INDEX `fk_Barrio_Comuna1_idx` (`Comuna_ID_comuna` ASC) VISIBLE,
  CONSTRAINT `fk_Barrio_Comuna1`
    FOREIGN KEY (`Comuna_ID_comuna`)
    REFERENCES `censo`.`comuna` (`ID_comuna`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`situacion_laboral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`situacion_laboral` (
  `ID_Situacion_laboral` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Situacion_laboral`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`persona` (
  `ID_persona` INT NOT NULL AUTO_INCREMENT,
  `DNI` VARCHAR(20) NOT NULL,
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
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `censo`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `censo`.`historial` (
  `ID_persona` INT NOT NULL,
  `Fecha_traslado` DATE NOT NULL,
  `ID_historial` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_historial`),
  INDEX `fk_Historial_Persona_idx` (`ID_persona` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Persona`
    FOREIGN KEY (`ID_persona`)
    REFERENCES `censo`.`persona` (`ID_persona`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO comuna (Nombre_sede, Numero_Comuna)
VALUES
('Sede Comunal 9', '9'),
('Sede Comunal 10', '10'),
('Sede Comunal 11', '11');


INSERT INTO barrio (
    Superficie_H,
    nombre,
    Comuna_ID_comuna,
    Cant_monoblock,
    Macro_activo,
    Cant_edificios,
    Porcentaje_asf,
    Nro_ley,
    Conexion_agua
)
VALUES
(2.5, 'Mataderos', 1, NULL, NULL, 25, '85', NULL, NULL),
(3.2, 'Liniers', 1, NULL, NULL, NULL, NULL, '2450/85', b'1'),
(4.1, 'Floresta', 2, 12, b'1', NULL, NULL, NULL, NULL),
(5.3, 'Villa Luro', 2, NULL, NULL, 18, '70', NULL, NULL),
(2.8, 'Villa Devoto', 3, NULL, NULL, NULL, NULL, '3100/92', b'0'),
(6.1, 'Villa del Parque', 3, 8, b'0', NULL, NULL, NULL, NULL);


INSERT INTO situacion_laboral (estado)
VALUES
('Ocupado'),
('Desocupado'),
('Subocupado');



INSERT INTO persona (
    DNI,
    Nombre,
    Apellido,
    Fecha_nacimiento,
    Situacion_laboral_ID_Situacion_laboral,
    Barrio_ID_barrio
)
VALUES
('40123456', 'Ana', 'Perez', '1995-04-12', 1, 1),
('38987654', 'Bruno', 'Gomez', '1992-08-23', 2, 1),
('42111222', 'Carla', 'Diaz', '1998-02-15', 1, 2),
('37666555', 'Diego', 'Fernandez', '1990-11-30', 3, 2),
('40555666', 'Elena', 'Romero', '1996-06-18', 2, 3),
('39123456', 'Facundo', 'Torres', '1993-09-05', 1, 3),
('43111222', 'Gabriela', 'Ruiz', '2000-01-20', 3, 4),
('36555444', 'Hugo', 'Alvarez', '1988-12-10', 1, 5),
('41123456', 'Ines', 'Medina', '1997-07-25', 2, 5),
('38555111', 'Juan', 'Castro', '1991-03-08', 1, 6);



INSERT INTO historial (
    ID_persona,
    Fecha_traslado
)
VALUES
(1, '2018-03-12'),
(1, '2021-06-20'),
(2, '2019-05-15'),
(3, '2020-01-10'),
(3, '2022-08-14'),
(4, '2017-11-22'),
(5, '2021-04-03'),
(6, '2016-09-18'),
(6, '2023-02-11'),
(7, '2019-12-05'),
(8, '2015-07-21'),
(8, '2020-10-30'),
(9, '2022-03-16'),
(10, '2018-08-09'),
(10, '2024-01-25');