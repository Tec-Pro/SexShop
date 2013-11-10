/*create table proveedor (
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

create table provee(
    idproveedor varchar(50) not null,
    idcliente varchar(50) not null,
    constraint pkprovee primary key(idproveedor,idcliente),
    constraint fkproveecliente foreign key(idcliente) references cliente(dni),
    constraint fkproveeproveedor foreign key(idproveedor) references proveedor(dni)
);


create table cliente (
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50) not null,
    telefono varchar(50),
    celular varchar(50),
    mail varchar(50),
    constraint pkcliente primary key(dni)    
);

create table adquirio(
    idcliente varchar(50) not null,
    idproducto integer not null,
    constraint pkadquirio primary key(idproducto,idcliente),
    constraint fkadquiriocliente foreign key(idcliente) references cliente(dni) ,
    constraint fkadquirioproducto foreign key(idproducto) references producto(numero_producto) 
);

create table producto (
    precio real,
    stock integer,
    numero_producto integer not null,
    nombre varchar(50),
    tipo varchar(50),
    marca varchar(50),
    idproveedor varchar(50),
    constraint pkproducto primary key(numero_producto),
    constraint fkproductoproveedor foreign key(idproveedor) references proveedor(dni) on update set null
);

create table venta (
    monto real not null,
    idcliente varchar(50) not null,
    idproducto integer not null,
    constraint pkventa primary key(idproducto,idcliente,monto),
    constraint fkventacliente foreign key(idcliente) references cliente(dni),
    constraint fkventaproducto foreign key(idproducto) references producto(numero_producto) 
);
*/
create table compra (
    monto real not null,
    idproveedor varchar(50) not null,
    idproducto integer not null,
    constraint pkcompra primary key(idproducto,idproveedor,monto),
    constraint fkcompraproveedor foreign key(idproveedor) references proveedor(dni),
    constraint fkcompraproducto foreign key(idproducto) references producto(numero_producto)
);