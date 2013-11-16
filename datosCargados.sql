INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (1, 'Nicolás', 'Pereyra Orcasitas', '4712371', '3584869621', 'nico.orcasitas@gmail.com');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (2, 'Joaquin', 'Heredia', '1921921', '1820103931', '');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (3, 'Ezequiel', 'Zensich', '1928129', '', '');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (4, 'Luciano', 'Putruele', '', '', 'lucho-kpo@hotmail.com.ar');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (5, 'Agustin', 'Bauer', '1921312', '', '');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (6, 'Alan', 'Gonzalez', '', '', '');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`, `mail`) VALUES (7, 'Jacinto', 'Jaimez', '1299192', '', 'Jacinto@yahoo.com.ar');
INSERT INTO `sexshop`.`cliente` (`id`, `nombre`, `apellido`, `telefono`, `celular`) VALUES (8, 'Max', 'Cejuela', '', '');

INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (10.12, 9.19, 19, 1, 'articulo1', 'tipo1', 'marca1');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (192.18, 190, 1, 2, 'articulo2', 'tipo1', 'marca1');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (123, 120.2, 87, 3, 'articulo3', 'tipo2', 'marca2');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (982.95, 980, 9, 4, 'articulo4', 'tipo4', 'marca4');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (82, 81, 821, 5, 'articulo5', 'tipo1', 'marca4');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (91, 81, 0, 6, 'articulo6', 'tipo3', 'marca1');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (18.1, 17, 9, 7, 'articulo7', 'tipo1', 'marca8');
INSERT INTO `sexshop`.`producto` (`precio_venta`, `precio_compra`, `stock`, `numero_producto`, `nombre`, `tipo`, `marca`) VALUES (9.19, 7, 8, 8, 'articulo8', 'tipo9', 'marca18');

INSERT INTO `sexshop`.`venta` (`id`, `monto`, `idcliente`, `fecha`) VALUES (1, 123, 1, '12-12-11');
INSERT INTO `sexshop`.`venta` (`id`, `monto`, `idcliente`, `fecha`) VALUES (2, 82.12, 1, '18-12-13');
INSERT INTO `sexshop`.`venta` (`id`, `monto`, `idcliente`, `fecha`) VALUES (3, 31.15, 2, '17-1-13');
INSERT INTO `sexshop`.`venta` (`id`, `monto`, `idcliente`) VALUES (4, 81.1, 4);

INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (1, 1, 12);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (1, 2, 1);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (1, 4, 7);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (1, 3, 1);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (2, 8, 5);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (3, 8, 7);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (4, 1, 1);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (3, 4, 4);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (3, 5, 5);
INSERT INTO `sexshop`.`productosvendidos` (`idventa`, `idproducto`, `cantidad`) VALUES (4, 5, 5);


