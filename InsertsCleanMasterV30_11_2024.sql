INSERT INTO `cliente` (`id`, `nombre`, `correo`, `password`, `movil`) VALUES
    (1, 'Prueba Cliente', 'Prueba.Cliente@yopmai.com', 'UGFzd29yZDEyMw==', '612345678');

INSERT INTO `empleado` (`id`, `nombre`, `apellidos`, `numss`, `iban`, `movil`, `idEncargada`, `correo`, `password`) VALUES ('1', 'Prueba ', 'Encargado Geneeral', '1234567890', 'ES1234567890', '612345678', NULL, 'Prueba.Empleada.Encargada@yopmail.com', 'UGFzd29yZDEyMw==');

INSERT INTO `ENCARGADO` (`id`, `idEmpleado`) VALUES (NULL, '1')

    INSERT INTO `empleado` ( `nombre`, `apellidos`, `numss`, `iban`, `movil`, `idEncargada`, `correo`, `password`) VALUES ( 'Prueba ', 'Empleado', '1234567890', 'ES1234567890', '612345678', 1, 'Prueba.Empleado@yopmail.com', 'UGFzd29yZDEyMw==');

