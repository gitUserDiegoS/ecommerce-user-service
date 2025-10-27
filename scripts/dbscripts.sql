-- Create schema
DROP SCHEMA IF EXISTS `ecommerce-db`;

CREATE SCHEMA `ecommerce-db`;
USE `ecommerce-db` ;

-- Table `ecommerce-db`.`type_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce-db`.`role_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
  )
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- users
CREATE TABLE IF NOT EXISTS `ecommerce-db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `document_id` VARCHAR(20) DEFAULT NULL,
  `name` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `mobile` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `role_id` BIGINT NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_role_id` (`role_id`),
  CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_type` (`id`)
  )
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- Table `full-stack-ecommerce`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce-db`.`product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- products
-- Table `full-stack-ecommerce`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce-db`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `unit_price` DECIMAL(13,2) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `units_in_stock` INT(11) DEFAULT NULL,
  `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- orders
--
-- Table structure for table `orders`
--
CREATE TABLE `ecommerce-db`.`orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_tracking_number` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `billing_address` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `shipping_address` bigint DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_user_id` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB
AUTO_INCREMENT=1 ;

-- order_items (join table between order and product)
--
-- Table structure for table `order_items`
--
CREATE TABLE `ecommerce-db`.`order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB
AUTO_INCREMENT=1;

-- Payments table using single-table inheritance
CREATE TABLE IF NOT EXISTS `payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id_paid` bigint DEFAULT NULL,
  `type` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `amount` DECIMAL(19,2),
  -- credit card specific
  `card_number` VARCHAR(32),
  `card_holder` VARCHAR(255),
  -- paypal specific
  `paypal_account` VARCHAR(255),
  `date_created` datetime(6) DEFAULT NULL,
   PRIMARY KEY (`id`),
  CONSTRAINT `FK_order_id_paid` FOREIGN KEY (`order_id_paid`) REFERENCES `orders` (`id`)
)ENGINE=InnoDB
AUTO_INCREMENT=1;


insert into role_type values (1, "ADMIN"	, "Admin role");
insert into role_type values (2, "CUSTOMER", "Customer role");

INSERT INTO product_category(category_name) VALUES ('Books');
INSERT INTO product_category(category_name) VALUES ('Coffee Mugs');


INSERT INTO product (sku, name, description, active, units_in_stock, unit_price, category_id,date_created) VALUES ('BOOK-TECH-1000', 'Crash Course in Python', 'Learn Python at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!',  1, 100, 14.99, 1, NOW());
INSERT INTO product (sku, name, description, active, units_in_stock, unit_price, category_id,date_created) VALUES ('BOOK-TECH-1001', 'Become a Guru in JavaScript', 'Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!',  1, 100, 20.99, 1, NOW());
INSERT INTO product (sku, name, description, active, units_in_stock, unit_price, category_id,date_created) VALUES ('BOOK-TECH-1004', 'Crash Course in Big Data', 'Learn Big Data at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 1, 100, 18.99, 1, NOW());
INSERT INTO product (sku, name, description, active, units_in_stock, unit_price, category_id,date_created) VALUES ('BOOK-TECH-1005', 'JavaScript Cookbook', 'Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 1, 100, 23.99, 1, NOW());

INSERT INTO product (sku, name, description,  active, units_in_stock, unit_price, category_id,date_created) VALUES ('COFFEEMUG-1000', 'Coffee Mug - Express', 'Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don\'t have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!', 1, 100, 18.99, 2, NOW());
INSERT INTO product (sku, name, description,  active, units_in_stock, unit_price, category_id,date_created) VALUES ('COFFEEMUG-1001', 'Coffee Mug - Cherokee', 'Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don\'t have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!',  1, 100, 18.99, 2, NOW());
INSERT INTO product (sku, name, description,  active, units_in_stock, unit_price, category_id,date_created) VALUES ('COFFEEMUG-1002', 'Coffee Mug - Sweeper', 'Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don\'t have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!',  1, 100, 18.99, 2, NOW());
INSERT INTO product (sku, name, description,  active, units_in_stock, unit_price, category_id,date_created) VALUES ('COFFEEMUG-1003', 'Coffee Mug - Aspire', 'Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don\'t have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!',  1, 100, 18.99, 2, NOW());

commit;