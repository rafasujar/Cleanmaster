-- Inserciones para la tabla cliente
INSERT INTO `cliente` (`id`, `nombre`, `correo`, `password`, `movil`) VALUES
                                                                          (1, 'Juan Pérez', 'juan.perez@example.com', 'password123', '1234567890'),
                                                                          (2, 'Ana García', 'ana.garcia@example.com', 'password456', '0987654321'),
                                                                          (3, 'Luis Torres', 'luis.torres@example.com', 'password789', '5678901234');

-- Inserciones para la tabla direcciones
INSERT INTO `direcciones` (`id`, `idCliente`, `direccion`) VALUES
                                                               (1, 1, 'Calle 123, Ciudad A'),
                                                               (2, 2, 'Avenida 456, Ciudad B'),
                                                               (3, 3, 'Plaza 789, Ciudad C');

-- Inserciones para la tabla empleado
INSERT INTO `empleado` (`id`, `nombre`, `apellidos`, `numss`, `iban`, `movil`, `idEncargada`, `correo`, `password`) VALUES
                                                                                                                        (1, 'Carlos Fernández', 'López', 123456789, 'ES1234567890123456789012', '1122334455', NULL, 'carlos.fernandez@example.com', 'empleado123'),
                                                                                                                        (2, 'Sofía Martínez', 'Gómez', 987654321, 'ES9876543210123456789012', '2233445566', NULL, 'sofia.martinez@example.com', 'empleado456');

-- Inserciones para la tabla ENCARGADO
INSERT INTO `ENCARGADO` (`id`, `idEmpleado`) VALUES
    (1, 1);

-- Inserciones para la tabla tiposServicios
INSERT INTO `tiposServicios` (`id`, `nombre`) VALUES
                                                  (1, 'Limpieza general'),
                                                  (2, 'Peluquería'),
                                                  (3, 'Masajes');

-- Inserciones para la tabla empleado_tiposServicios
INSERT INTO `empleado_tiposServicios` (`id`, `idEmpleado`, `idTipoServicio`) VALUES
                                                                                 (1, 1, 1),
                                                                                 (2, 2, 2),
                                                                                 (3, 2, 3);

-- Inserciones para la tabla mensaje
INSERT INTO `mensaje` (`id`, `idCliente`, `idEmpleado`, `asunto`, `mensaje`) VALUES
                                                                                 (1, 1, 1, 'Consulta de limpieza', '¿Cuánto cuesta el servicio de limpieza?'),
                                                                                 (2, 2, 2, 'Cita de peluquería', 'Quiero agendar una cita para el sábado.'),
                                                                                 (3, 3, 1, 'Consulta de masaje', '¿Qué tipo de masajes ofrecen?');

-- Inserciones para la tabla reservarCita
INSERT INTO `reservarCita` (`id`, `idCliente`, `idEmpleado`, `fecha`, `idTipoServicio`, `idDireccion`, `especificaciones`) VALUES
                                                                                                                               (1, 1, 1, '2024-12-01', 1, 1, 'Limpieza profunda en la sala'),
                                                                                                                               (2, 2, 2, '2024-12-02', 2, 2, 'Corte de cabello estilo moderno'),
                                                                                                                               (3, 3, 1, '2024-12-03', 3, 3, 'Masaje relajante de una hora');

-- Inserciones para la tabla direcciones
INSERT INTO `direcciones` (`id`, `idCliente`, `direccion`) VALUES
                                                               (4, 1, 'Calle 100, Ciudad D'),
                                                               (5, 2, 'Boulevard 200, Ciudad E'),
                                                               (6, 3, 'Carrera 300, Ciudad F');
