-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema JDM-IMPORT
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema JDM-IMPORT
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `JDM-IMPORT` ;
USE `JDM-IMPORT` ;

-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`CUSTOMER` (
  `idCUSTOMER` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`idCUSTOMER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`INDIVIDUAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`INDIVIDUAL` (
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  PRIMARY KEY (`CUSTOMER_idCUSTOMER`),
  CONSTRAINT `fk_INDIVIDUAL_CUSTOMER`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`COMPANY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`COMPANY` (
  `CompanyName` VARCHAR(45) NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  PRIMARY KEY (`CUSTOMER_idCUSTOMER`),
  INDEX `fk_COMPANY_CUSTOMER1_idx` (`CUSTOMER_idCUSTOMER` ASC) VISIBLE,
  CONSTRAINT `fk_COMPANY_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`EMPLOYEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`EMPLOYEE` (
  `idEMPLOYEE` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `PostCode` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idEMPLOYEE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`ORDERABLE_CAR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`ORDERABLE_CAR` (
  `idCar` INT NOT NULL AUTO_INCREMENT,
  `Brand` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Year` YEAR(4) NOT NULL,
  `Price` DECIMAL NOT NULL,
  `ImageURL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCar`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`CAR_ON_STOCK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`CAR_ON_STOCK` (
  `ChassisNumber` VARCHAR(17) NOT NULL,
  `Brand` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Year` YEAR(4) NOT NULL,
  `Price` DECIMAL(4,2) NOT NULL,
  `Milage` INT NOT NULL,
  PRIMARY KEY (`ChassisNumber`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`SALE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`SALE` (
  `Date` DATE NOT NULL,
  `CAR_ON_STOCK_ChassisNumber` VARCHAR(17) NOT NULL,
  `TotalPrice` DECIMAL(6,2) NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  `EMPLOYEE_idEMPLOYEE` INT NOT NULL,
  PRIMARY KEY (`CAR_ON_STOCK_ChassisNumber`),
  INDEX `fk_SALE_CAR_ON_STOCK1_idx` (`CAR_ON_STOCK_ChassisNumber` ASC) VISIBLE,
  INDEX `fk_SALE_CUSTOMER1_idx` (`CUSTOMER_idCUSTOMER` ASC) VISIBLE,
  INDEX `fk_SALE_EMPLOYEE1_idx` (`EMPLOYEE_idEMPLOYEE` ASC) VISIBLE,
  CONSTRAINT `fk_SALE_CAR_ON_STOCK1`
    FOREIGN KEY (`CAR_ON_STOCK_ChassisNumber`)
    REFERENCES `JDM-IMPORT`.`CAR_ON_STOCK` (`ChassisNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALE_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALE_EMPLOYEE1`
    FOREIGN KEY (`EMPLOYEE_idEMPLOYEE`)
    REFERENCES `JDM-IMPORT`.`EMPLOYEE` (`idEMPLOYEE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`INSURANCE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`INSURANCE` (
  `PolicyNumber` INT NOT NULL,
  `InsuranceProvider` VARCHAR(45) NOT NULL,
  `PolicyStartDate` DATE NOT NULL,
  `PolicyEndDate` DATE NOT NULL,
  `CAR_ON_STOCK_ChassisNumber` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`PolicyNumber`),
  INDEX `fk_INSURANCE_CAR_ON_STOCK1_idx` (`CAR_ON_STOCK_ChassisNumber` ASC) VISIBLE,
  CONSTRAINT `fk_INSURANCE_CAR_ON_STOCK1`
    FOREIGN KEY (`CAR_ON_STOCK_ChassisNumber`)
    REFERENCES `JDM-IMPORT`.`CAR_ON_STOCK` (`ChassisNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`SERVICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`SERVICE` (
  `idService` INT NOT NULL AUTO_INCREMENT,
  `CAR_ON_STOCK_ChassisNumber` VARCHAR(17) NOT NULL,
  `Date` DATE NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Price` DECIMAL(4,2) NOT NULL,
  `MilageAtService` INT NOT NULL,
  PRIMARY KEY (`idService`),
  CONSTRAINT `fk_SERVICE_CAR_ON_STOCK1`
    FOREIGN KEY (`CAR_ON_STOCK_ChassisNumber`)
    REFERENCES `JDM-IMPORT`.`CAR_ON_STOCK` (`ChassisNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`MANUFACTURER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`MANUFACTURER` (
  `idMANUFACTURER` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMANUFACTURER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`PART`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`PART` (
  `idPART` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Price` DECIMAL(4,2) NOT NULL,
  `Quantity` INT NOT NULL,
  `MANUFACTURER_idMANUFACTURER` INT NOT NULL,
  PRIMARY KEY (`idPART`),
  INDEX `fk_PART_MANUFACTURER1_idx` (`MANUFACTURER_idMANUFACTURER` ASC) VISIBLE,
  CONSTRAINT `fk_PART_MANUFACTURER1`
    FOREIGN KEY (`MANUFACTURER_idMANUFACTURER`)
    REFERENCES `JDM-IMPORT`.`MANUFACTURER` (`idMANUFACTURER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`SUPPLIER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`SUPPLIER` (
  `idSUPPLIER` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `PostCode` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSUPPLIER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`WARANTY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`WARANTY` (
  `idWaranty` INT NOT NULL AUTO_INCREMENT,
  `Type` INT NOT NULL,
  `StartDate` DATE NULL,
  `EndDate` DATE NULL,
  `CAR_ON_STOCK_ChassisNumber` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`idWaranty`),
  CONSTRAINT `fk_WARANTY_CAR_ON_STOCK1`
    FOREIGN KEY (`CAR_ON_STOCK_ChassisNumber`)
    REFERENCES `JDM-IMPORT`.`CAR_ON_STOCK` (`ChassisNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`ORDER` (
  `idOrder` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  `DeliveryDate` DATE NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  PRIMARY KEY (`idOrder`),
  CONSTRAINT `fk_ORDER_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`ORDERABLE_CAR_has_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`ORDERABLE_CAR_has_ORDER` (
  `ORDERABLE_CAR_idCar` INT NOT NULL,
  `TotalPrice` DECIMAL NOT NULL,
  `Quantity` INT NOT NULL,
  `ORDER_idOrder` INT NOT NULL,
  PRIMARY KEY (`ORDERABLE_CAR_idCar`, `ORDER_idOrder`),
  INDEX `fk_ORDERABLE_CAR_has_ORDER_ORDERABLE_CAR1_idx` (`ORDERABLE_CAR_idCar` ASC) VISIBLE,
  INDEX `fk_ORDERABLE_CAR_has_ORDER_ORDER1_idx` (`ORDER_idOrder` ASC) VISIBLE,
  CONSTRAINT `fk_ORDERABLE_CAR_has_ORDER_ORDERABLE_CAR1`
    FOREIGN KEY (`ORDERABLE_CAR_idCar`)
    REFERENCES `JDM-IMPORT`.`ORDERABLE_CAR` (`idCar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORDERABLE_CAR_has_ORDER_ORDER1`
    FOREIGN KEY (`ORDER_idOrder`)
    REFERENCES `JDM-IMPORT`.`ORDER` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`RENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`RENT` (
  `idRent` INT NOT NULL AUTO_INCREMENT,
  `PricePerDay` DECIMAL(4,2) NOT NULL,
  `StartDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  `CAR_ON_STOCK_ChassisNumber` VARCHAR(17) NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  INDEX `fk_RENT_CUSTOMER1_idx` (`CUSTOMER_idCUSTOMER` ASC) VISIBLE,
  PRIMARY KEY (`idRent`),
  CONSTRAINT `fk_RENT_CAR_ON_STOCK1`
    FOREIGN KEY (`CAR_ON_STOCK_ChassisNumber`)
    REFERENCES `JDM-IMPORT`.`CAR_ON_STOCK` (`ChassisNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RENT_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`REGISTER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`REGISTER` (
  `EMPLOYEE_idEMPLOYEE` INT NOT NULL,
  `CUSTOMER_idCUSTOMER` INT NOT NULL,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`EMPLOYEE_idEMPLOYEE`, `CUSTOMER_idCUSTOMER`),
  INDEX `fk_EMPLOYEE_has_CUSTOMER_EMPLOYEE1_idx` (`EMPLOYEE_idEMPLOYEE` ASC) VISIBLE,
  INDEX `fk_REGISTER_CUSTOMER1_idx` (`CUSTOMER_idCUSTOMER` ASC) VISIBLE,
  CONSTRAINT `fk_EMPLOYEE_has_CUSTOMER_EMPLOYEE1`
    FOREIGN KEY (`EMPLOYEE_idEMPLOYEE`)
    REFERENCES `JDM-IMPORT`.`EMPLOYEE` (`idEMPLOYEE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REGISTER_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_idCUSTOMER`)
    REFERENCES `JDM-IMPORT`.`CUSTOMER` (`idCUSTOMER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`INVOICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`INVOICE` (
  `idINVOICE` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  `SUPPLIER_idSUPPLIER` INT NOT NULL,
  PRIMARY KEY (`idINVOICE`),
  INDEX `fk_INVOICE_SUPPLIER1_idx` (`SUPPLIER_idSUPPLIER` ASC) VISIBLE,
  CONSTRAINT `fk_INVOICE_SUPPLIER1`
    FOREIGN KEY (`SUPPLIER_idSUPPLIER`)
    REFERENCES `JDM-IMPORT`.`SUPPLIER` (`idSUPPLIER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`INVOICE_has_PART`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`INVOICE_has_PART` (
  `INVOICE_idINVOICE` INT NOT NULL,
  `PART_idPART` INT NOT NULL,
  `Price` DECIMAL NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`INVOICE_idINVOICE`, `PART_idPART`),
  INDEX `fk_INVOICE_has_PART_PART1_idx` (`PART_idPART` ASC) VISIBLE,
  INDEX `fk_INVOICE_has_PART_INVOICE1_idx` (`INVOICE_idINVOICE` ASC) VISIBLE,
  CONSTRAINT `fk_INVOICE_has_PART_INVOICE1`
    FOREIGN KEY (`INVOICE_idINVOICE`)
    REFERENCES `JDM-IMPORT`.`INVOICE` (`idINVOICE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INVOICE_has_PART_PART1`
    FOREIGN KEY (`PART_idPART`)
    REFERENCES `JDM-IMPORT`.`PART` (`idPART`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JDM-IMPORT`.`SERVICE_has_PART`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `JDM-IMPORT`.`SERVICE_has_PART` (
  `SERVICE_idService` INT NOT NULL,
  `PART_idPART` INT NOT NULL,
  `Price` DECIMAL NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`SERVICE_idService`, `PART_idPART`),
  INDEX `fk_SERVICE_has_PART_PART1_idx` (`PART_idPART` ASC) VISIBLE,
  INDEX `fk_SERVICE_has_PART_SERVICE1_idx` (`SERVICE_idService` ASC) VISIBLE,
  CONSTRAINT `fk_SERVICE_has_PART_SERVICE1`
    FOREIGN KEY (`SERVICE_idService`)
    REFERENCES `JDM-IMPORT`.`SERVICE` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SERVICE_has_PART_PART1`
    FOREIGN KEY (`PART_idPART`)
    REFERENCES `JDM-IMPORT`.`PART` (`idPART`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
