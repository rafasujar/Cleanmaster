-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 08-12-2024 a las 04:17:46
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `CleanMaster`
--
DROP DATABASE IF EXISTS `CleanMaster`;
CREATE DATABASE IF NOT EXISTS `CleanMaster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `CleanMaster`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
                           `id` int(11) NOT NULL,
                           `nombre` varchar(50) DEFAULT NULL,
                           `correo` varchar(200) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `movil` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `correo`, `password`, `movil`) VALUES
    (1, 'Prueba Cliente 2', 'Prueba.Cliente@yopmai.com', 'UGFzd29yZDEyMw==', '612345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
CREATE TABLE `direcciones` (
                               `id` int(11) NOT NULL,
                               `id_cliente` int(11) DEFAULT NULL,
                               `direccion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `direcciones`
--

INSERT INTO `direcciones` (`id`, `id_cliente`, `direccion`) VALUES
    (1, 1, 'Av. Los Robles 452, Colonia Jardines, Ciudad Esperanza, CP 45678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
                            `id` int(11) NOT NULL,
                            `nombre` varchar(50) DEFAULT NULL,
                            `apellidos` varchar(50) DEFAULT NULL,
                            `numss` int(11) DEFAULT NULL,
                            `iban` varchar(34) DEFAULT NULL,
                            `movil` varchar(15) DEFAULT NULL,
                            `id_encargada` int(11) DEFAULT NULL,
                            `correo` varchar(200) DEFAULT NULL,
                            `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `nombre`, `apellidos`, `numss`, `iban`, `movil`, `id_encargada`, `correo`, `password`) VALUES
                                                                                                                         (1, 'Prueba', 'Encargado General', 1234567890, 'ES1234567890', '612345678', NULL, 'Prueba.Empleada.Encargada@yopmail.com', 'UGFzd29yZDEyMw=='),
                                                                                                                         (2, 'Prueba', 'Empleado', 1234567890, 'ES1234567890', '612345678', 1, 'Prueba.Empleado@yopmail.com', 'UGFzd29yZDEyMw==');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado_tipos_servicios`
--

DROP TABLE IF EXISTS `empleado_tipos_servicios`;
CREATE TABLE `empleado_tipos_servicios` (
                                            `id` int(11) NOT NULL,
                                            `id_empleado` int(11) DEFAULT NULL,
                                            `id_tipo_servicio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado_tipos_servicios`
--

INSERT INTO `empleado_tipos_servicios` (`id`, `id_empleado`, `id_tipo_servicio`) VALUES
                                                                                     (1, 2, 1),
                                                                                     (2, 2, 2),
                                                                                     (3, 2, 3),
                                                                                     (4, 2, 4),
                                                                                     (5, 2, 5),
                                                                                     (6, 2, 6),
                                                                                     (7, 2, 7),
                                                                                     (8, 2, 8),
                                                                                     (9, 2, 9),
                                                                                     (10, 2, 10),
                                                                                     (11, 2, 11),
                                                                                     (12, 2, 12),
                                                                                     (13, 2, 13),
                                                                                     (14, 2, 14),
                                                                                     (15, 2, 15),
                                                                                     (16, 2, 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargado`
--

DROP TABLE IF EXISTS `encargado`;
CREATE TABLE `encargado` (
                             `id` int(11) NOT NULL,
                             `id_empleado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `encargado`
--

INSERT INTO `encargado` (`id`, `id_empleado`) VALUES
    (1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE `mensaje` (
                           `id` int(11) NOT NULL,
                           `id_cliente` int(11) DEFAULT NULL,
                           `id_empleado` int(11) DEFAULT NULL,
                           `asunto` varchar(100) DEFAULT NULL,
                           `mensaje` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservar_cita`
--

DROP TABLE IF EXISTS `reservar_cita`;
CREATE TABLE `reservar_cita` (
                                 `id` int(11) NOT NULL,
                                 `id_cliente` int(11) DEFAULT NULL,
                                 `id_empleado` int(11) DEFAULT NULL,
                                 `fecha` date DEFAULT NULL,
                                 `id_tipo_servicio` int(11) DEFAULT NULL,
                                 `id_direccion` int(11) DEFAULT NULL,
                                 `finalizada_reserva` tinyint(1) NOT NULL,
                                 `especificaciones` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservar_cita`
--

INSERT INTO `reservar_cita` (`id`, `id_cliente`, `id_empleado`, `fecha`, `id_tipo_servicio`, `id_direccion`, `finalizada_reserva`, `especificaciones`) VALUES
                                                                                                                                                           (1, 1, 2, '2024-12-02', 1, 1, 1, 'Primera cita del día'),
                                                                                                                                                           (2, 1, 2, '2024-12-02', 2, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (3, 1, 2, '2024-12-03', 3, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (4, 1, 2, '2024-12-03', 4, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (5, 1, 2, '2024-12-04', 5, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (6, 1, 2, '2024-12-04', 6, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (7, 1, 2, '2024-12-05', 7, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (8, 1, 2, '2024-12-05', 8, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (9, 1, 2, '2024-12-06', 9, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (10, 1, 2, '2024-12-06', 10, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (11, 1, 2, '2024-12-07', 11, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (12, 1, 2, '2024-12-07', 12, 1, 0, 'Segunda cita del día'),
                                                                                                                                                           (13, 1, 2, '2024-12-08', 13, 1, 0, 'Primera cita del día'),
                                                                                                                                                           (14, 1, 2, '2024-12-08', 14, 1, 0, 'Segunda cita del día');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_servicios`
--

DROP TABLE IF EXISTS `tipos_servicios`;
CREATE TABLE `tipos_servicios` (
                                   `id` int(11) NOT NULL,
                                   `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos_servicios`
--

INSERT INTO `tipos_servicios` (`id`, `nombre`) VALUES
                                                   (1, 'Limpieza residencial'),
                                                   (2, 'Limpieza comercial'),
                                                   (3, 'Limpieza profunda'),
                                                   (4, 'Cuidado de jardines'),
                                                   (5, 'Lavado de alfombras'),
                                                   (6, 'Mantenimiento de piscinas'),
                                                   (7, 'Servicio de lavandería'),
                                                   (8, 'Organización de espacios'),
                                                   (9, 'Pulido de pisos'),
                                                   (10, 'Desinfección y sanitización'),
                                                   (11, 'Cuidado de mascotas a domicilio'),
                                                   (12, 'Limpieza de ventanas'),
                                                   (13, 'Eliminación de plagas'),
                                                   (14, 'Reparaciones menores'),
                                                   (15, 'Limpieza post-construcción'),
                                                   (16, 'Limpieza ecológica');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `direcciones`
--
ALTER TABLE `direcciones`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `id_encargada` (`id_encargada`);

--
-- Indices de la tabla `empleado_tipos_servicios`
--
ALTER TABLE `empleado_tipos_servicios`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`),
  ADD KEY `id_tipo_servicio` (`id_tipo_servicio`);

--
-- Indices de la tabla `encargado`
--
ALTER TABLE `encargado`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `reservar_cita`
--
ALTER TABLE `reservar_cita`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_empleado` (`id_empleado`),
  ADD KEY `id_tipo_servicio` (`id_tipo_servicio`),
  ADD KEY `id_direccion` (`id_direccion`);

--
-- Indices de la tabla `tipos_servicios`
--
ALTER TABLE `tipos_servicios`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direcciones`
--
ALTER TABLE `direcciones`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado_tipos_servicios`
--
ALTER TABLE `empleado_tipos_servicios`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `encargado`
--
ALTER TABLE `encargado`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reservar_cita`
--
ALTER TABLE `reservar_cita`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipos_servicios`
--
ALTER TABLE `tipos_servicios`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `direcciones`
--
ALTER TABLE `direcciones`
    ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
    ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_encargada`) REFERENCES `encargado` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `empleado_tipos_servicios`
--
ALTER TABLE `empleado_tipos_servicios`
    ADD CONSTRAINT `empleado_tipos_servicios_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `empleado_tipos_servicios_ibfk_2` FOREIGN KEY (`id_tipo_servicio`) REFERENCES `tipos_servicios` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `encargado`
--
ALTER TABLE `encargado`
    ADD CONSTRAINT `encargado_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
    ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `reservar_cita`
--
ALTER TABLE `reservar_cita`
    ADD CONSTRAINT `reservar_cita_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `reservar_cita_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `reservar_cita_ibfk_3` FOREIGN KEY (`id_tipo_servicio`) REFERENCES `tipos_servicios` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `reservar_cita_ibfk_4` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE SET NULL;
COMMIT;
