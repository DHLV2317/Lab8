-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema RootsGame
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RootsGame
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RootsGame` DEFAULT CHARACTER SET utf8 ;
USE `RootsGame` ;

-- -----------------------------------------------------
-- Table `RootsGame`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`usuario` (
  `idusuario` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RootsGame`.`Recursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`Recursos` (
  `idRecursos` INT NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `dia` VARCHAR(45) NOT NULL,
  `produccionT` VARCHAR(45) NOT NULL,
  `poblacion` VARCHAR(45) NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idRecursos`),
  INDEX `fk_Recursos_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_Recursos_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `RootsGame`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RootsGame`.`profesion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`profesion` (
  `idprofesion` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idprofesion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RootsGame`.`estadoCivilizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`estadoCivilizacion` (
  `idestadoCivilizacion` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idestadoCivilizacion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RootsGame`.`civilizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`civilizacion` (
  `idcivilizacion` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `guerraGanada` VARCHAR(45) NOT NULL,
  `guerraPerdida` VARCHAR(45) NOT NULL,
  `estadoCivilizacion_idestadoCivilizacion` INT NOT NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idcivilizacion`),
  INDEX `fk_civilizacion_estadoCivilizacion1_idx` (`estadoCivilizacion_idestadoCivilizacion` ASC) VISIBLE,
  INDEX `fk_civilizacion_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_civilizacion_estadoCivilizacion1`
    FOREIGN KEY (`estadoCivilizacion_idestadoCivilizacion`)
    REFERENCES `RootsGame`.`estadoCivilizacion` (`idestadoCivilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_civilizacion_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `RootsGame`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RootsGame`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RootsGame`.`personas` (
  `idpersonas` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `alimentacion` VARCHAR(45) NOT NULL,
  `moral` VARCHAR(45) NOT NULL,
  `tiempo` VARCHAR(45) NOT NULL,
  `fuerza` VARCHAR(45) NOT NULL,
  `produccion` VARCHAR(45) NOT NULL,
  `profesion_idprofesion` INT NOT NULL,
  `civilizacion_idcivilizacion` INT NOT NULL,
  PRIMARY KEY (`idpersonas`),
  INDEX `fk_gestion_personas_profesion1_idx` (`profesion_idprofesion` ASC) VISIBLE,
  INDEX `fk_personas_civilizacion1_idx` (`civilizacion_idcivilizacion` ASC) VISIBLE,
  CONSTRAINT `fk_gestion_personas_profesion1`
    FOREIGN KEY (`profesion_idprofesion`)
    REFERENCES `RootsGame`.`profesion` (`idprofesion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_civilizacion1`
    FOREIGN KEY (`civilizacion_idcivilizacion`)
    REFERENCES `RootsGame`.`civilizacion` (`idcivilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
