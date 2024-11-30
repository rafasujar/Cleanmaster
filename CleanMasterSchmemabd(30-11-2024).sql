SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `CleanMaster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `CleanMaster`;

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `correo` varchar(200) NOT NULL,
  `password` varchar(255) NOT NULL,
  `movil` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `cliente` VALUES(1, 'Juan Perez', 'juan.perez@yopmail.com', 'Password123', '612345678');
INSERT INTO `cliente` VALUES(2, 'Maria Lopez', 'maria.lopez@yopmail.com', 'Password456', '612345679');
INSERT INTO `cliente` VALUES(3, 'Pedro Martinez', 'pedro.martinez@yopmail.com', 'Password789', '612345680');

DROP TABLE IF EXISTS `direcciones`;
CREATE TABLE IF NOT EXISTS `direcciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `direcciones` VALUES(1, 1, 'Calle Falsa 123, Ciudad A');
INSERT INTO `direcciones` VALUES(2, 1, 'Avenida Siempre Viva 742, Ciudad A');
INSERT INTO `direcciones` VALUES(3, 2, 'Calle Nueva 456, Ciudad B');
INSERT INTO `direcciones` VALUES(4, 3, 'Calle Vieja 789, Ciudad C');

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `numss` int(11) NOT NULL,
  `iban` varchar(34) NOT NULL,
  `movil` varchar(15) NOT NULL,
  `idEncargada` int(11) DEFAULT NULL,
  `correo` varchar(200) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`),
  KEY `idEncargada` (`idEncargada`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `empleado` VALUES(1, 'Carlos', 'Sanchez', 987654321, 'ES1234567890123456789012', '612345681', 1, 'carlos.sanchez@yopmail.com', 'SecurePass1');
INSERT INTO `empleado` VALUES(2, 'Lucia', 'Gomez', 123456780, 'ES1234567890123456789013', '612345682', 2, 'lucia.gomez@yopmail.com', 'SecurePass2');

DROP TABLE IF EXISTS `empleado_tiposServicios`;
CREATE TABLE IF NOT EXISTS `empleado_tiposServicios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) NOT NULL,
  `idTipoServicio` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idEmpleado` (`idEmpleado`),
  KEY `idTipoServicio` (`idTipoServicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `empleado_tiposServicios` VALUES(1, 1, 1);
INSERT INTO `empleado_tiposServicios` VALUES(2, 1, 3);
INSERT INTO `empleado_tiposServicios` VALUES(3, 2, 2);

DROP TABLE IF EXISTS `ENCARGADO`;
CREATE TABLE IF NOT EXISTS `ENCARGADO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `ENCARGADO` VALUES(1);
INSERT INTO `ENCARGADO` VALUES(2);

DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE IF NOT EXISTS `mensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  `asunto` varchar(100) NOT NULL,
  `mensaje` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`),
  KEY `idEmpleado` (`idEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `mensaje` VALUES(1, 1, 1, 'Consulta de limpieza', '¿Cuánto cuesta el servicio de limpieza?');
INSERT INTO `mensaje` VALUES(2, 2, 2, 'Cita de peluquería', 'Quiero agendar una cita para el sábado.');
INSERT INTO `mensaje` VALUES(3, 3, 1, 'Consulta de masaje', '¿Qué tipo de masajes ofrecen?');

DROP TABLE IF EXISTS `reservarCita`;
CREATE TABLE IF NOT EXISTS `reservarCita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `idTipoServicio` int(11) NOT NULL,
  `idDireccion` int(11) NOT NULL,
  `especificaciones` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idCliente` (`idCliente`),
  KEY `idEmpleado` (`idEmpleado`),
  KEY `idTipoServicio` (`idTipoServicio`),
  KEY `idDireccion` (`idDireccion`)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `tiposServicios`;
CREATE TABLE IF NOT EXISTS `tiposServicios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




ALTER TABLE `direcciones`
  ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`);

ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`idEncargada`) REFERENCES `ENCARGADO` (`id`);

ALTER TABLE `empleado_tiposServicios`
  ADD CONSTRAINT `empleado_tiposServicios_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`),
  ADD CONSTRAINT `empleado_tiposServicios_ibfk_2` FOREIGN KEY (`idTipoServicio`) REFERENCES `tiposServicios` (`id`);

ALTER TABLE `mensaje`
  ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ,
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`);

ALTER TABLE `reservarCita`
  ADD CONSTRAINT `reservarCita_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `reservarCita_ibfk_2` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`) ,
  ADD CONSTRAINT `reservarCita_ibfk_3` FOREIGN KEY (`idTipoServicio`) REFERENCES `tiposServicios` (`id`) ;
  ADD CONSTRAINT `reservarCita_ibfk_4` FOREIGN KEY (`idDireccion`) REFERENCES `direcciones` (`id`) ;
COMMIT;


