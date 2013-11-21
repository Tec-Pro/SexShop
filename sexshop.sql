drop database if exists sexshop;
create database sexshop;

use sexshop;




create table usuarios (
    id integer not null auto_increment,
    nombre varchar(50) default 'dueño',
    pass varchar(50) default 'dueño',
    constraint pkusuarios primary key (id)
);

create table proveedors (
    id integer not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50),
    dni varchar(50),
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    cuil varchar(50) not null,
    nombre_banco varchar(50),
    sucursal varchar(50),
    tipo_cuenta varchar(50),
    cuenta integer,
    compra_minima integer,
    constraint pkproveedor primary key (id)
);


create table clientes (
    id integer not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    constraint pkcliente primary key (id)
);


create table productos (
    id integer not null auto_increment,
    precio_venta float,
    precio_compra float,
    stock integer,
    numero_producto integer not null unique,
    nombre varchar(50),
    tipo varchar(50),
    marca varchar(50),
    proveedor_id integer not null,
    constraint pkproducto primary key (id),
    constraint fkproductoproveedor foreign key (proveedor_id)
        references proveedors (id)
        on delete set null
);


create table ventas (
    id integer not null auto_increment,
    monto float,
    idcliente integer not null,
    fecha date not null,
    constraint pkventa primary key (id),
    constraint fkventacliente foreign key (idcliente)
        references clientes (id)
        on delete set null
);
    

create table compras (
    id integer not null auto_increment,
    monto float,
    idproveedor integer not null,
    fecha date not null,
    constraint pkcompra primary key (id),
    constraint fkcompraproveedor foreign key (idproveedor)
        references proveedors (id)
        on delete set null
);



create table adquiridos (
    idcliente integer not null,
    idproducto integer not null,
    cantidad integer not null,
    constraint pkadquirio primary key (idproducto , idcliente),
    constraint fkadquiriocliente foreign key (idcliente)
        references clientes (id)
        on delete set null,
    constraint fkadquirioproducto foreign key (idproducto)
        references productos (numero_producto)
        on delete set null
);


create table productos_vendidos (
    idventa integer not null,
    idproducto integer not null,
    cantidad integer not null,
    precio_final float not null,
    primary key (idventa , idproducto),
    foreign key (idventa)
        references ventas (id)
        on delete set null,
    foreign key (idproducto)
        references productos (numero_producto)
        on delete set null
);


create table productos_comprados (
    idcompra integer not null,
    idproducto integer not null,
    cantidad integer not null,
    primary key (idcompra , idproducto),
    foreign key (idcompra)
        references compras (id)
        on delete set null,
    foreign key (idproducto)
        references productos (numero_producto)
        on delete set null
);
