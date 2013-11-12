

drop table if exists proveedor;
create table proveedor (
    id integer not null auto_increment,
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50),
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    cuil varchar(50),
    nombre_banco varchar(50),
    sucursal varchar(50),
    tipo_cuenta varchar(50),
    cuenta integer,
    compra_minima integer,
    constraint pkproveedor primary key(id)    
);

drop table if exists cliente;
create table cliente (
    id integer not null auto_increment,
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    constraint pkcliente primary key(id)    
);

drop table if exists producto;
create table producto (
    precio_venta real,
	precio_compra real,
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
    idcliente integer not null,
    fecha date,
    constraint pkventa primary key(id),
    constraint fkventacliente foreign key(idcliente) references cliente(id)
);
    
drop table if exists compra;
create table compra (
    id integer not null auto_increment,
    monto real not null,
    idproveedor integer not null,
    fecha date,
    constraint pkcompra primary key(id),
    constraint fkcompraproveedor foreign key(idproveedor) references proveedor(id)
);


drop table if exists provee;
create table provee(
    idproveedor integer not null,
    idproducto integer not null,
    constraint pkprovee primary key(idproveedor,idproducto),
    constraint fkproveeproducto foreign key(idproducto) references producto(numero_producto),
    constraint fkproveeproveedor foreign key(idproveedor) references proveedor(id)
);


drop table if exists adquirio;
create table adquirio(
    idcliente integer not null,
    idproducto integer not null,
    constraint pkadquirio primary key(idproducto,idcliente),
    constraint fkadquiriocliente foreign key(idcliente) references cliente(id) ,
    constraint fkadquirioproducto foreign key(idproducto) references producto(numero_producto) 
);


drop table if exists productosvendidos;
create table productosvendidos (
    idventa integer not null,
    idproducto integer not null,
	cantidad integer,
    primary key(idventa,idproducto),
    foreign key (idventa) references venta(id),
    foreign key(idproducto) references producto(numero_producto)
);

drop table if exists productoscomprados;
create table productoscomprados (
    idcompra integer not null,
    idproducto integer not null,
	cantidad integer,
    primary key(idcompra,idproducto),
    foreign key (idcompra) references compra(id),
    foreign key(idproducto) references producto(numero_producto)
);


