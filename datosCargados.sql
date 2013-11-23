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

INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (10.12, 9.19, 25, 11111, 'articulo1', 'tipo1', 'marca1',1);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (192.18, 190, 15, 22222, 'articulo2', 'tipo1', 'marca1',1);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (123, 120.2, 87, 33333, 'articulo3', 'tipo2', 'marca2',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (982.95, 980, 42, 44444, 'articulo4', 'tipo4', 'marca4',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (82, 81, 82, 55555, 'articulo5', 'tipo1', 'marca4',2);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (91, 81, 21, 66666, 'articulo6', 'tipo3', 'marca1',3);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (18.1, 17, 27, 77777, 'articulo7', 'tipo1', 'marca8',3);
INSERT INTO `sexshop`.`productos` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`, `proveedor_id`) VALUES (9.19, 7, 18, 88888, 'articulo8', 'tipo9', 'marca18',3);

INSERT INTO `sexshop`.`ventas` (`monto`, `cliente_id`, `fecha`) VALUES (510.11, 5, '2013-11-23');
INSERT INTO `sexshop`.`ventas` (`monto`, `cliente_id`, `fecha`) VALUES (1185.6, 7, '2013-11-23');

INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (1, 33333, 2, 246);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (1, 55555, 3, 246);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (1, 77777, 1, 18.11);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (2, 11111, 1, 10.12);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (2, 22222, 1, 192.18);
INSERT INTO `sexshop`.`productos_ventas` (`venta_id`, `producto_id`, `cantidad`, `precio_final`) VALUES (2, 44444, 1, 982.96);

INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (5, 33333, 2);
INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (5, 55555, 3);
INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (5, 77777, 1);
INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (7, 11111, 1);
INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (7, 22222, 1);
INSERT INTO `sexshop`.`clientes_productos` (`cliente_id`, `producto_id`, `cantidad`) VALUES (7, 44444, 1);


