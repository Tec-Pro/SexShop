

drop table if exists proveedor;
create table proveedor (
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    cuil varchar(50),
    nombre_banco varchar(50),
    sucursal varchar(50),
    tipo_cuenta varchar(50),
    cuenta integer,
    compra_minima integer,
    constraint pkproveedor primary key(dni)    
);

drop table if exists cliente;
create table cliente (
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    constraint pkcliente primary key(dni)    
);

drop table if exists producto;
create table producto (
    precio real,
    stock integer,
    numero_producto integer not null,
    nombre varchar(50),
    tipo varchar(50),
    marca varchar(50),
    constraint pkproducto primary key(numero_producto)
);

drop table if exists venta;
create table venta (
    id integer not null auto_increment,
    monto real not null,
    idcliente varchar(50) not null,
    constraint pkventa primary key(id),
    constraint fkventacliente foreign key(idcliente) references cliente(dni)
);
    
drop table if exists compra;
create table compra (
    id integer not null auto_increment,
    monto real not null,
    idproveedor varchar(50) not null,
    constraint pkcompra primary key(id),
    constraint fkcompraproveedor foreign key(idproveedor) references proveedor(dni)
);


drop table if exists provee;
create table provee(
    idproveedor varchar(50) not null,
    idproducto integer not null,
    constraint pkprovee primary key(idproveedor,idproducto),
    constraint fkproveeproducto foreign key(idproducto) references producto(numero_producto),
    constraint fkproveeproveedor foreign key(idproveedor) references proveedor(dni)
);


drop table if exists adquirio;
create table adquirio(
    idcliente varchar(50) not null,
    idproducto integer not null,
    constraint pkadquirio primary key(idproducto,idcliente),
    constraint fkadquiriocliente foreign key(idcliente) references cliente(dni) ,
    constraint fkadquirioproducto foreign key(idproducto) references producto(numero_producto) 
);


drop table if exists productosvendidos;
create table productosvendidos (
    idventa integer not null,
    idproducto integer not null,
    primary key(idventa,idproducto),
    foreign key (idventa) references venta(id),
    foreign key(idproducto) references producto(numero_producto)
);

drop table if exists productoscomprados;
create table productoscomprados (
    idcompra integer not null,
    idproducto integer not null,
    primary key(idcompra,idproducto),
    foreign key (idcompra) references compra(id),
    foreign key(idproducto) references producto(numero_producto)
);


