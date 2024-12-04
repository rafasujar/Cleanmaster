-- Crear la base de datos y definir el conjunto de caracteres
DROP DATABASE IF EXISTS CleanMaster;
CREATE DATABASE IF NOT EXISTS `CleanMaster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `CleanMaster`;

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
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: direcciones
DROP TABLE IF EXISTS `direcciones`;
CREATE TABLE IF NOT EXISTS `direcciones` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `id_cliente` INT(11) NULL,
    `direccion` VARCHAR(200) NULL,
    PRIMARY KEY (`id`),
    KEY `id_cliente` (`id_cliente`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: empleado
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NULL,
    `apellidos` VARCHAR(50) NULL,
    `numss` INT(11) NULL,
    `iban` VARCHAR(34) NULL,
    `movil` VARCHAR(15) NULL,
    `id_encargada` INT(11) NULL,
    `correo` VARCHAR(200) NULL,
    `password` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `correo` (`correo`),
    KEY `id_encargada` (`id_encargada`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: empleado_tipos_servicios
DROP TABLE IF EXISTS `empleado_tipos_servicios`;
CREATE TABLE IF NOT EXISTS `empleado_tipos_servicios` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `id_empleado` INT(11) NULL,
    `id_tipo_servicio` INT(11) NULL,
    PRIMARY KEY (`id`),
    KEY `id_empleado` (`id_empleado`),
    KEY `id_tipo_servicio` (`id_tipo_servicio`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: ENCARGADO
DROP TABLE IF EXISTS `encargado`;
CREATE TABLE IF NOT EXISTS `encargado` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `id_empleado` INT(11) NULL,
    PRIMARY KEY (`id`),
    KEY `id_empleado` (`id_empleado`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: mensaje
DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE IF NOT EXISTS `mensaje` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `id_cliente` INT(11) NULL,
    `id_empleado` INT(11) NULL,
    `asunto` VARCHAR(100) NULL,
    `mensaje` VARCHAR(500) NULL,
    PRIMARY KEY (`id`),
    KEY `id_cliente` (`id_cliente`),
    KEY `id_empleado` (`id_empleado`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: reservar_cita
DROP TABLE IF EXISTS `reservar_cita`;
CREATE TABLE IF NOT EXISTS `reservar_cita` (
    `id` INT(11) NULL AUTO_INCREMENT,
    `id_cliente` INT(11) NULL,
    `id_empleado` INT(11) NULL,
    `fecha` DATE NULL,
    `id_tipo_servicio` INT(11) NULL,
    `id_direccion` INT(11) NULL,
    `especificaciones` VARCHAR(200) NULL,
    PRIMARY KEY (`id`),
    KEY `id_cliente` (`id_cliente`),
    KEY `id_empleado` (`id_empleado`),
    KEY `id_tipo_servicio` (`id_tipo_servicio`),
    KEY `id_direccion` (`id_direccion`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: tipos_servicios
DROP TABLE IF EXISTS `tipos_servicios`;
CREATE TABLE IF NOT EXISTS `tipos_servicios` (
                                                 `id` INT(11) NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Relaciones (Claves Foráneas)
ALTER TABLE `direcciones`
    ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL;

ALTER TABLE `empleado`
    ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_encargada`) REFERENCES `encargado` (`id`) ON DELETE SET NULL;

ALTER TABLE `empleado_tipos_servicios`
    ADD CONSTRAINT `empleado_tipos_servicios_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `empleado_tipos_servicios_ibfk_2` FOREIGN KEY (`id_tipo_servicio`) REFERENCES `tipos_servicios` (`id`) ON DELETE SET NULL;

ALTER TABLE `mensaje`
    ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

ALTER TABLE `encargado`
    ADD CONSTRAINT `encargado_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

ALTER TABLE `reservar_cita`
    ADD CONSTRAINT `reservar_cita_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservar_cita_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservar_cita_ibfk_3` FOREIGN KEY (`id_tipo_servicio`) REFERENCES `tipos_servicios` (`id`) ON DELETE SET NULL,
    ADD CONSTRAINT `reservar_cita_ibfk_4` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE SET NULL;

-- Confirmar los cambios realizados
                                                                                                                 -- Insertar datos en la tabla cliente
INSERT INTO `cliente` ( `nombre`, `correo`, `password`, `movil`) VALUES
    ( 'Prueba Cliente', 'Prueba.Cliente@yopmai.com', 'UGFzd29yZDEyMw==', '612345678');

-- Insertar datos en la tabla empleado (encargado)
INSERT INTO `empleado` (`nombre`, `apellidos`, `numss`, `iban`, `movil`, `id_encargada`, `correo`, `password`) VALUES
    ( 'Prueba', 'Encargado General', '1234567890', 'ES1234567890', '612345678', NULL, 'Prueba.Empleada.Encargada@yopmail.com', 'UGFzd29yZDEyMw==');

-- Insertar datos en la tabla encargado
INSERT INTO `encargado` ( `id_empleado`) VALUES ( '1');

-- Insertar datos en la tabla empleado (empleado normal)
INSERT INTO `empleado` (`nombre`, `apellidos`, `numss`, `iban`, `movil`, `id_encargada`, `correo`, `password`) VALUES
    ('Prueba', 'Empleado', '1234567890', 'ES1234567890', '612345678', 1, 'Prueba.Empleado@yopmail.com', 'UGFzd29yZDEyMw==');

-- Insertar datos en la tabla tipos_servicios
INSERT INTO `tipos_servicios` (`nombre`) VALUES
                                             ('Limpieza residencial'),
                                             ('Limpieza comercial'),
                                             ('Limpieza profunda'),
                                             ('Cuidado de jardines'),
                                             ('Lavado de alfombras'),
                                             ('Mantenimiento de piscinas'),
                                             ('Servicio de lavandería'),
                                             ('Organización de espacios'),
                                             ('Pulido de pisos'),
                                             ('Desinfección y sanitización'),
                                             ('Cuidado de mascotas a domicilio'),
                                             ('Limpieza de ventanas'),
                                             ('Eliminación de plagas'),
                                             ('Reparaciones menores'),
                                             ('Limpieza post-construcción'),
                                             ('Limpieza ecológica');

-- Insertar datos en la tabla empleado_tipos_servicios
INSERT INTO `empleado_tipos_servicios` (`id_empleado`, `id_tipo_servicio`) VALUES
                                                                               (2, 1),
                                                                               (2, 2),
                                                                               (2, 3),
                                                                               (2, 4),
                                                                               (2, 5),
                                                                               (2, 6),
                                                                               (2, 7),
                                                                               (2, 8),
                                                                               (2, 9),
                                                                               (2, 10),
                                                                               (2, 11),
                                                                               (2, 12),
                                                                               (2, 13),
                                                                               (2, 14),
                                                                               (2, 15),
                                                                               (2, 16);

-- Insertar datos en la tabla direcciones
INSERT INTO `direcciones` (`id_cliente`, `direccion`) VALUES
    (1, 'Av. Los Robles 452, Colonia Jardines, Ciudad Esperanza, CP 45678');

-- Insertar datos en la tabla reservar_cita
INSERT INTO `reservar_cita` (`id_cliente`, `id_empleado`, `fecha`, `id_tipo_servicio`, `id_direccion`, `especificaciones`) VALUES
                                                                                                                               (1, 2, '2024-12-02', 1, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-02', 2, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-03', 3, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-03', 4, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-04', 5, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-04', 6, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-05', 7, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-05', 8, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-06', 9, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-06', 10, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-07', 11, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-07', 12, 1, 'Segunda cita del día'),
                                                                                                                               (1, 2, '2024-12-08', 13, 1, 'Primera cita del día'),
                                                                                                                               (1, 2, '2024-12-08', 14, 1, 'Segunda cita del día');

COMMIT;
