create  table  mensaje (
  id  int  not null auto_increment,
  id_receptor   int  not null,
  id_emisor  int  not null,
  asunto varchar ( 100 ) not null,
  mensaje  varchar ( 500 ) not null,
  primary key (id_mensaje),
  foreign key id_receptor references empleado(id_empleado),
    foreign key id_emisor references empleado(id_empleado),
    foreign key id_receptor references cliente(id_cliente),
    foreign key id_emisor references cliente(id_cliente)
);