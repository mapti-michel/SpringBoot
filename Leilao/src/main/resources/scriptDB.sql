-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bd_leilao
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_leilao
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_leilao` DEFAULT CHARACTER SET utf8 ;
USE `bd_leilao` ;

-- -----------------------------------------------------
-- Table `bd_leilao`.`tbuser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tbuser` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(65) NULL,
  `senha` LONGBLOB NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_leilao`.`tbperfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tbperfil` (
  `idperfil` INT NOT NULL AUTO_INCREMENT,
  `perfil` VARCHAR(55) NULL,
  PRIMARY KEY (`idperfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_leilao`.`tbproduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tbproduto` (
  `idproduto` INT NOT NULL AUTO_INCREMENT,
  `produto` VARCHAR(85) NULL,
  `status` TINYINT NULL,
  `lanceinicial` DOUBLE NULL,
  `coduser` INT NOT NULL,
  PRIMARY KEY (`idproduto`),
  INDEX `fk_tbproduto_tbuser1_idx` (`coduser` ASC) VISIBLE,
  CONSTRAINT `fk_tbproduto_tbuser1`
    FOREIGN KEY (`coduser`)
    REFERENCES `bd_leilao`.`tbuser` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_leilao`.`tbuserperfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tbuserperfil` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codperfil` INT NOT NULL,
  `coduser` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbuserperfil_tbperfil1_idx` (`codperfil` ASC) VISIBLE,
  INDEX `fk_tbuserperfil_tbuser1_idx` (`coduser` ASC) VISIBLE,
  CONSTRAINT `fk_tbuserperfil_tbperfil1`
    FOREIGN KEY (`codperfil`)
    REFERENCES `bd_leilao`.`tbperfil` (`idperfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbuserperfil_tbuser1`
    FOREIGN KEY (`coduser`)
    REFERENCES `bd_leilao`.`tbuser` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_leilao`.`tbcompra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tbcompra` (
  `idcompra` INT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NULL,
  `codproduto` INT NOT NULL,
  PRIMARY KEY (`idcompra`),
  INDEX `fk_tbcompra_tbproduto1_idx` (`codproduto` ASC) INVISIBLE,
  CONSTRAINT `fk_tbcompra_tbproduto1`
    FOREIGN KEY (`codproduto`)
    REFERENCES `bd_leilao`.`tbproduto` (`idproduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_leilao`.`tblance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_leilao`.`tblance` (
  `idlance` INT NOT NULL AUTO_INCREMENT,
  `dataHora` DATETIME NULL,
  `valorLance` DOUBLE NULL,
  `codcompra` INT NOT NULL,
  PRIMARY KEY (`idlance`),
  INDEX `fk_tblance_tbcompra1_idx` (`codcompra` ASC) VISIBLE,
  CONSTRAINT `fk_tblance_tbcompra1`
    FOREIGN KEY (`codcompra`)
    REFERENCES `bd_leilao`.`tbcompra` (`idcompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
