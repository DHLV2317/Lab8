-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema rootsgame
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rootsgame
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rootsgame` DEFAULT CHARACTER SET utf8 ;

-- -----------------------------------------------------
-- Table `rootsgame`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

USE `rootsgame` ;

-- -----------------------------------------------------
-- Table `rootsgame`.`estadocivilizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`estadocivilizacion` (
  `idestadoCivilizacion` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idestadoCivilizacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`civilizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`civilizacion` (
  `idcivilizacion` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `guerraGanada` VARCHAR(45) NOT NULL,
  `guerraPerdida` VARCHAR(45) NOT NULL,
  `estadoCivilizacion_idestadoCivilizacion` INT NOT NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idcivilizacion`),
  INDEX `fk_civilizacion_estadoCivilizacion1_idx` (`estadoCivilizacion_idestadoCivilizacion` ASC) VISIBLE,
  INDEX `fk_civilizacion_usuario_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_civilizacion_estadoCivilizacion1`
    FOREIGN KEY (`estadoCivilizacion_idestadoCivilizacion`)
    REFERENCES `rootsgame`.`estadocivilizacion` (`idestadoCivilizacion`),
  CONSTRAINT `fk_civilizacion_usuario`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `rootsgame`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`profesion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`profesion` (
  `idprofesion` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idprofesion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`personas` (
  `idpersonas` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `alimentacion` INT NOT NULL,
  `moral` INT NOT NULL,
  `tiempo` VARCHAR(45) NOT NULL,
  `fuerza` VARCHAR(45) NULL,
  `produccion` VARCHAR(45) NOT NULL,
  `profesion_idprofesion` INT NOT NULL,
  `civilizacion_idcivilizacion` INT NOT NULL,
  PRIMARY KEY (`idpersonas`),
  INDEX `fk_gestion_personas_profesion1_idx` (`profesion_idprofesion` ASC) VISIBLE,
  INDEX `fk_personas_civilizacion1_idx` (`civilizacion_idcivilizacion` ASC) VISIBLE,
  CONSTRAINT `fk_gestion_personas_profesion1`
    FOREIGN KEY (`profesion_idprofesion`)
    REFERENCES `rootsgame`.`profesion` (`idprofesion`),
  CONSTRAINT `fk_personas_civilizacion1`
    FOREIGN KEY (`civilizacion_idcivilizacion`)
    REFERENCES `rootsgame`.`civilizacion` (`idcivilizacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`recursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`recursos` (
  `idRecursos` INT NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `dia` VARCHAR(45) NOT NULL,
  `produccionT` VARCHAR(45) NOT NULL,
  `poblacion` VARCHAR(45) NULL DEFAULT NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idRecursos`),
  INDEX `fk_recursos_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_recursos_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `rootsgame`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rootsgame`.`usuario_credenciales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rootsgame`.`usuario_credenciales` (
  `id_usuario` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password_hashed` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_table1_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `rootsgame`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
