INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (1, 'Nicolás', 'Pereyra Orcasitas', '4712371', '3584869621', 'nico.orcasitas@gmail.com');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (2, 'Joaquin', 'Heredia', '1921921', '1820103931', '');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (3, 'Ezequiel', 'Zensich', '1928129', '', '');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (4, 'Luciano', 'Putruele', '', '', 'lucho-kpo@hotmail.com.ar');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (5, 'Agustin', 'Bauer', '1921312', '', '');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (6, 'Alan', 'Gonzalez', '', '', '');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (7, 'Jacinto', 'Jaimez', '1299192', '', 'Jacinto@yahoo.com.ar');
INSERT INTO `sexshop`.`clientes` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (8, 'Max', 'Cejuela', '', '','');

INSERT INTO `sexshop`.`proveedors` (`nombre`, `apellido`, `dni`, `telefono`, `celular`, `mail`, `cuil`, `nombre_banco`, `sucursal`, `tipo_cuenta`, `cuenta`, `compra_minima`) VALUES ('prov1', 'ap1', '-', '-', '-', 'prov1@prov', '11111', 'banco1', 'suc1', 'tipo1', 11111, 1);
INSERT INTO `sexshop`.`proveedors` (`nombre`, `apellido`, `dni`, `telefono`, `celular`, `mail`, `cuil`, `nombre_banco`, `sucursal`, `tipo_cuenta`, `cuenta`, `compra_minima`) VALUES ('prov2', 'ap2', '-', '-', '-', 'prov2@prov', '22222', 'banco2', 'suc2', 'tipo2', 22222, 1);
INSERT INTO `sexshop`.`proveedors` (`nombre`, `apellido`, `dni`, `telefono`, `celular`, `mail`, `cuil`, `nombre_banco`, `sucursal`, `tipo_cuenta`, `cuenta`, `compra_minima`) VALUES ('prov3', 'ap3', '-', '-', '-', 'prov3@prov', '33333', 'banco3', 'suc3', 'tipo3', 33333, 1);

INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (10.12, 9.19, 19, 1, 'articulo1', 'tipo1', 'marca1',1);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (192.18, 190, 1, 2, 'articulo2', 'tipo1', 'marca1',1);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (123, 120.2, 87, 3, 'articulo3', 'tipo2', 'marca2',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (982.95, 980, 9, 4, 'articulo4', 'tipo4', 'marca4',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (82, 81, 821, 5, 'articulo5', 'tipo1', 'marca4',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (91, 81, 0, 6, 'articulo6', 'tipo3', 'marca1',3);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (18.1, 17, 9, 7, 'articulo7', 'tipo1', 'marca8',3);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (9.19, 7, 8, 8, 'articulo8', 'tipo9', 'marca18',3);

INSERT INTO `sexshop`.`ventas` (`id`, `monto`, `cliente_id`, `fecha`) VALUES (1, 123, 1, '12-12-11');
INSERT INTO `sexshop`.`ventas` (`id`, `monto`, `cliente_id`, `fecha`) VALUES (2, 82.12, 1, '18-12-13');
INSERT INTO `sexshop`.`ventas` (`id`, `monto`, `cliente_id`, `fecha`) VALUES (3, 31.15, 2, '17-1-13');
INSERT INTO `sexshop`.`ventas` (`id`, `monto`, `cliente_id`,`fecha`) VALUES (4, 81.1, 4,'17-1-13');

INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (1, 1, 12,2);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (1, 2, 1,3);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (1, 4, 7,4);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (1, 3, 1,5);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (2, 8, 5,6);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (3, 8, 7,0);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (4, 1, 1,2);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (3, 4, 4,5);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (3, 5, 5,1);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`,`precio_final`) VALUES (4, 5, 5,9);



