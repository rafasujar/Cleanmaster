-- Crear la base de datos y definir el conjunto de caracteres
CREATE DATABASE IF NOT EXISTS `CleanMaster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `CleanMaster`;
-- Crear Usuario
    CREATE USER 'CleanMaster'@'%' IDENTIFIED VIA mysql_native_password USING '1234';
    GRANT ALL PRIVILEGES ON *.* TO 'CleanMaster'@'%' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
    GRANT ALL PRIVILEGES ON `CleanMaster`.* TO 'CleanMaster'@'%';

                                               -- Tabla: cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
                                         `id` INT(11) NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NULL,
    `correo` VARCHAR(200) NULL,
    `password` VARCHAR(255) NULL,
    `movil` VARCHAR(15) NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `correo` (`correo`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: direcciones
DROP TABLE IF EXISTS `direcciones`;
CREATE TABLE IF NOT EXISTS `direcciones` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `idCliente` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `direccion` VARCHAR(200) NULL,
    PRIMARY KEY (`id`),
    KEY `idCliente` (`idCliente`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: empleado
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
                                          `id` INT(11) NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NULL,
    `apellidos` VARCHAR(50) NULL,
    `numss` INT(11) NULL,
    `iban` VARCHAR(34) NULL,
    `movil` VARCHAR(15) NULL,
    `idEncargada` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `correo` VARCHAR(200) NULL,
    `password` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `correo` (`correo`),
    KEY `idEncargada` (`idEncargada`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: empleado_tiposServicios
DROP TABLE IF EXISTS `empleado_tiposServicios`;
CREATE TABLE IF NOT EXISTS `empleado_tiposServicios` (
                                                         `id` INT(11) NULL AUTO_INCREMENT,
    `idEmpleado` INT(11) NULL,
    `idTipoServicio` INT(11) NULL,
    PRIMARY KEY (`id`),
    KEY `idEmpleado` (`idEmpleado`),
    KEY `idTipoServicio` (`idTipoServicio`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: ENCARGADO
DROP TABLE IF EXISTS `ENCARGADO`;
CREATE TABLE IF NOT EXISTS `ENCARGADO` (
                                           `id` INT(11) NULL AUTO_INCREMENT,
    `idEmpleado` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    PRIMARY KEY (`id`),
    KEY `idEmpleado` (`idEmpleado`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: mensaje
DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE IF NOT EXISTS `mensaje` (
                                         `id` INT(11) NULL AUTO_INCREMENT,
    `idCliente` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `idEmpleado` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `asunto` VARCHAR(100) NULL,
    `mensaje` VARCHAR(500) NULL,
    PRIMARY KEY (`id`),
    KEY `idCliente` (`idCliente`),
    KEY `idEmpleado` (`idEmpleado`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: reservarCita
DROP TABLE IF EXISTS `reservarCita`;
CREATE TABLE IF NOT EXISTS `reservarCita` (
                                              `id` INT(11) NULL AUTO_INCREMENT,
    `idCliente` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `idEmpleado` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `fecha` DATE NULL,
    `idTipoServicio` INT(11) NULL,
    `idDireccion` INT(11) NULL,  -- Permitir NULL en lugar de NULL
    `especificaciones` VARCHAR(200) NULL,
    PRIMARY KEY (`id`),
    KEY `idCliente` (`idCliente`),
    KEY `idEmpleado` (`idEmpleado`),
    KEY `idTipoServicio` (`idTipoServicio`),
    KEY `idDireccion` (`idDireccion`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: tiposServicios
DROP TABLE IF EXISTS `tiposServicios`;
CREATE TABLE IF NOT EXISTS `tiposServicios` (
                                                `id` INT(11) NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Relaciones (Claves Foráneas)
ALTER TABLE `direcciones`
    ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL;

ALTER TABLE `empleado`
    ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`idEncargada`) REFERENCES `ENCARGADO` (`id`) ON DELETE SET NULL;

ALTER TABLE `empleado_tiposServicios`
    ADD CONSTRAINT `empleado_tiposServicios_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `empleado_tiposServicios_ibfk_2` FOREIGN KEY (`idTipoServicio`) REFERENCES `tiposServicios` (`id`) ON DELETE SET NULL;

ALTER TABLE `mensaje`
    ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

ALTER TABLE `ENCARGADO`
    ADD CONSTRAINT `ENCARGADO_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

ALTER TABLE `reservarCita`
    ADD CONSTRAINT `reservarCita_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservarCita_ibfk_2` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservarCita_ibfk_3` FOREIGN KEY (`idTipoServicio`) REFERENCES `tiposServicios` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservarCita_ibfk_4` FOREIGN KEY (`idDireccion`) REFERENCES `direcciones` (`id`) ON DELETE SET NULL;





commit ;