CREATE SCHEMA `cm3` ;

CREATE TABLE `cm3`.`product` (
  `id` VARCHAR(200) NOT NULL,
  `name` VARCHAR(200) NULL,
  `price` DECIMAL NULL,
  `quantity` INT NULL,
  `description` VARCHAR(200) NULL,
  `image` VARCHAR(200) NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `cm3`.`role` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `cm3`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `full_name` VARCHAR(100) NULL,
  `birthday` DATE NULL,
  `phone_number` VARCHAR(20) NULL,
  `email` VARCHAR(100) NULL,
  `address` VARCHAR(50) NULL,
  `image` VARCHAR(300) NULL,
  `role_id` INT NULL,
  `password` VARCHAR(30) NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_id_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `cm3`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
