drop database if exists sexshop;
create database sexshop;

use sexshop;




create table usuarios (
	id integer not null auto_increment,
	nombre varchar(50) default 'amedialuz',
	pass varchar(50) default 'amedialuz',
	constraint pkusuarios primary key(id)
);

insert into usuarios values();

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
	check (compra_minima>=0),
    constraint pkproveedor primary key(id)    
);


create table clientes (
    id integer not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    constraint pkcliente primary key(id)    
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
    proveedor_id integer,
	check (precio_venta>0),
	check (precio_compra>0),
	check (stock>=0),
	check (numero_producto>=0),
    constraint pkproducto primary key(id),
	constraint fkproductoproveedor foreign key(proveedor_id) references proveedors(id) on delete set null
);


create table ventas (
    id integer not null auto_increment,
    monto float,
    cliente_id integer,
    fecha date not null,
	check (monto>0),
    constraint pkventa primary key(id),
    constraint fkventacliente foreign key(cliente_id) references clientes(id) on delete set null
);
    

create table compras (
    id integer not null auto_increment,
    monto float,
    proveedor_id integer,
    fecha date not null,
	check (monto>0),
    constraint pkcompra primary key(id),
    constraint fkcompraproveedor foreign key(proveedor_id) references proveedors(id) on delete set null

);



create table clientes_productos(
    id integer not null auto_increment,
    cliente_id integer,
    producto_id integer,
    cantidad integer not null,
	check (cantidad>0),
    constraint pkadquirio primary key(id),
    constraint fkadquiriocliente foreign key(cliente_id) references clientes(id) on delete set null ,
    constraint fkadquirioproducto foreign key(producto_id) references productos(numero_producto) on delete set null
);


create table productos_ventas (
    id integer not null auto_increment,
    venta_id integer,
    producto_id integer,
    cantidad integer not null,
    precio_final float not null,
	check (cantidad>0),
	check (precio_final>0),
    primary key(id),
    foreign key (venta_id) references ventas(id) on delete set null,
    foreign key(producto_id) references productos(numero_producto) on delete set null
);


create table productos_compras (
    id integer not null auto_increment,
    compra_id integer,
    producto_id integer,
    cantidad integer not null,
	check (cantidad>0),
    primary key(id),
    foreign key (compra_id) references compras(id) on delete set null,
    foreign key(producto_id) references productos(numero_producto) on delete set null
);

