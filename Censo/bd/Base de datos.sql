-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tp_final
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Censo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Censo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Censo` DEFAULT CHARACTER SET utf8mb3 ;
USE `Censo` ;

-- -----------------------------------------------------
-- Table `Censo`.`comuna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`comuna` (
  `ID_comuna` INT NOT NULL,
  `Nombre_sede` VARCHAR(45) NOT NULL,
  `Numero_Comuna` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ID_comuna`),
  UNIQUE KEY `uk_comuna_numero` (`Numero_Comuna`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Censo`.`tipo_barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`tipo_barrio` (
  `ID_tipo_barrio` INT NOT NULL,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  `Cant_monoblock` INT NULL,
  `Macro_activo` BIT(1) NULL,
  `Cant_edificios` INT NULL,
  `Porcentaje_asf` VARCHAR(67) NULL,
  `Nro_ley` VARCHAR(20) NULL,
  `Conexion_agua` BIT(1) NULL,
  PRIMARY KEY (`ID_tipo_barrio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Censo`.`barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`barrio` (
  `ID_barrio` INT NOT NULL,
  `Superficie_H` FLOAT NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `Tipo_barrio_ID_tipo_barrio` INT NOT NULL,
  `Comuna_ID_comuna` INT NOT NULL,
  PRIMARY KEY (`ID_barrio`),
  INDEX `fk_Barrio_Tipo_barrio_idx` (`Tipo_barrio_ID_tipo_barrio` ASC) VISIBLE,
  INDEX `fk_Barrio_Comuna1_idx` (`Comuna_ID_comuna` ASC) VISIBLE,
  CONSTRAINT `fk_Barrio_Comuna1`
    FOREIGN KEY (`Comuna_ID_comuna`)
    REFERENCES `Censo`.`comuna` (`ID_comuna`),
  CONSTRAINT `fk_Barrio_Tipo_barrio`
    FOREIGN KEY (`Tipo_barrio_ID_tipo_barrio`)
    REFERENCES `Censo`.`tipo_barrio` (`ID_tipo_barrio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-----------------------------------------------------
-- Table `Censo`.`situacion_laboral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`situacion_laboral` (
  `ID_Situacion_laboral` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Situacion_laboral`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Censo`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`persona` (
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
    REFERENCES `Censo`.`barrio` (`ID_barrio`),
  CONSTRAINT `fk_Persona_Situacion_laboral1`
    FOREIGN KEY (`Situacion_laboral_ID_Situacion_laboral`)
    REFERENCES `Censo`.`situacion_laboral` (`ID_Situacion_laboral`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `Censo`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Censo`.`historial` (
  `ID_persona` INT NOT NULL,
  `Fecha_traslado` DATE NOT NULL,
  `ID_historial` INT NOT NULL,
  PRIMARY KEY (`ID_persona`, `ID_historial`),
  INDEX `fk_Historial_Persona_idx` (`ID_persona` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Persona`
    FOREIGN KEY (`ID_persona`)
    REFERENCES `Censo`.`persona` (`ID_persona`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- 
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




############INSERTS 


#COMUNAS
INSERT INTO comuna
(ID_comuna, Nombre_sede, Numero_Comuna)
VALUES
(1, 'Sede Comunal 9', '9'),
(2, 'Sede Comunal 10', '10'),
(3, 'Sede Comunal 11', '11');



##TIPOS DE BARRIO (3)

INSERT INTO tipo_barrio
(ID_tipo_barrio, Superficie_H, nombre,
 Cant_monoblock, Macro_activo,
 Cant_edificios, Porcentaje_asf,
 Nro_ley, Conexion_agua)
VALUES
(1, 12.5, 'Trazado formal',
 NULL, NULL, 12, '90%', NULL, NULL),
(2, 8.3, 'Complejo habitacional',
 8, b'1', NULL, NULL, NULL, NULL),
(3, 15.7, 'Urbanizacion planificada',
 NULL, NULL, NULL, NULL, '3100/2001', b'0');


##BARRIOS (6)


INSERT INTO barrio
(ID_barrio, Superficie_H, nombre,
 Tipo_barrio_ID_tipo_barrio, Comuna_ID_comuna)
VALUES
-- Comuna 9
(1, 2.5, 'Mataderos', 1, 1),
(2, 3.2, 'Liniers', 2, 1),

-- Comuna 10
(3, 4.1, 'Floresta', 3, 2),
(4, 5.3, 'Villa Luro', 1, 2),

-- Comuna 11
(5, 2.8, 'Villa Devoto', 2, 3),
(6, 6.1, 'Villa del Parque', 3, 3);


##SITUACIONES LABORALES (3)


INSERT INTO situacion_laboral
(ID_Situacion_laboral, estado)
VALUES
(1, 'Ocupado'),
(2, 'Subocupado'),
(3, 'Desocupado');


##PERSONAS (10)


INSERT INTO persona
(DNI, ID_persona, Nombre, Apellido,
 Fecha_nacimiento,
 Situacion_laboral_ID_Situacion_laboral,
 Barrio_ID_barrio)
VALUES
('40123456', 1, 'Juan', 'Perez',
 '1995-04-12', 1, 1),

('38987654', 2, 'Maria', 'Gomez',
 '1992-08-23', 2, 2),

('42123456', 3, 'Lucas', 'Fernandez',
 '1998-01-15', 3, 3),

('35678901', 4, 'Sofia', 'Rodriguez',
 '1988-11-30', 1, 4),

('44789123', 5, 'Mateo', 'Lopez',
 '2001-06-05', 2, 5),

('32876543', 6, 'Valentina', 'Martinez',
 '1985-03-19', 1, 6),

('37654321', 7, 'Tomas', 'Garcia',
 '1990-09-27', 3, 1),

('41234567', 8, 'Camila', 'Sanchez',
 '1997-12-10', 1, 2),

('29876543', 9, 'Tralalero', 'Tralala',
 '1978-05-14', 2, 3),

('43567890', 10, 'Lucia', 'Torres',
 '2000-02-21', 3, 4);


##HISTORIAL


INSERT INTO historial
(ID_persona, Fecha_traslado, ID_historial)
VALUES
(1, '2018-03-15', 1),
(1, '2021-07-20', 2),

(2, '2019-05-10', 1),
(2, '2022-08-14', 2),

(3, '2017-11-22', 1),
(3, '2020-02-14', 2),

(4, '2022-01-05', 1),
(4, '2024-06-18', 2),

(5, '2016-09-18', 1),
(5, '2020-04-12', 2),

(6, '2019-12-03', 1),

(7, '2015-06-25', 1),

(8, '2021-03-17', 1),

(9, '2010-08-11', 1),

(10, '2022-07-07', 1);






