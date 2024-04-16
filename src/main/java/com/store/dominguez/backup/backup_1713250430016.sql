-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: dominguez
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `UK_35t4wyxqrevf09uwx9e9p6o75` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('CAT-AMA-ALM421','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Categoría que engloba dispositivos utilizados para guardar y gestionar datos de manera permanente o temporal en sistemas informáticos. Incluye dispositivos como discos duros, unidades de estado sólido (SSD), tarjetas de memoria, entre otros.',_binary '','https://newsbook.es/wp-content/uploads/2013/09/1foto11406.jpg','Almacenamiento'),('CAT-AUD-ADI921','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Dispositivo de audio que se colocan sobre las orejas para escuchar sonidos provenientes de una computadora u otro dispositivo electrónico.',_binary '','https://http2.mlstatic.com/D_NQ_NP_760857-MLA44771394445_022021-O.webp','Audifonos'),('CAT-CAE-LM435R','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Estructura que aloja y protege los componentes de una computadora.',_binary '','https://mipclista.com/3173-large_default/case-gamer-1st-player-zx7.jpg','Cases'),('CAT-COO-BC123W','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Dispositivo de hardware diseñado para disipar el calor generado por componentes internos de una computadora, como el procesador (CPU) o la tarjeta gráfica (GPU), manteniendo así una temperatura óptima de funcionamiento.',_binary '','https://http2.mlstatic.com/D_NQ_NP_601302-MLM48393265754_112021-O.webp','Coolers de Refrigeración'),('CAT-MON-HG432Z','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Pantalla de visualización utilizada para mostrar imágenes generadas por la computadora.',_binary '','https://consumer.huawei.com/content/dam/huawei-cbg-site/latam/latin/mkt/plp/monitors/mateview-gt-series/mateview-gt-series-1.jpg','Monitores'),('CAT-PAC-YH874B','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Placa de circuito impreso que conecta todos los componentes de hardware de una computadora.',_binary '','https://www.asus.com/media/Odin/websites/global/ProductLine/20200819054034.png','Placas base'),('CAT-POC-AS120K','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Unidad central de procesamiento (CPU) que ejecuta las instrucciones de un programa y realiza cálculos.',_binary '','https://i.blogs.es/9862e7/intel/450_1000.jpeg','Procesadores'),('CAT-PSU-VN876P','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Componente que suministra energía eléctrica a los componentes de una computadora.',_binary '','https://m.media-amazon.com/images/I/41CeZQLDl-S._SL500_.jpg','Fuentes de alimentación'),('CAT-SLL-PL678E','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Sillas ergonómicas diseñadas para proporcionar comodidad y soporte durante largas. Estas sillas suelen tienen la características de soporte lumbar ajustable, reposabrazos acolchados y reclinación ajustable para una experiencia de juego cómoda..',_binary '','https://thumb.pccomponentes.com/w-530-530/articles/18/180612/1.jpg','Sillas Gamer'),('CAT-TJG-ZX321F','2024-04-16 01:53:12.156199','2024-04-16 01:53:12.156199','Componente que procesa y genera imágenes para ser mostradas en la pantalla.',_binary '','https://sc04.alicdn.com/kf/H6e3a33d7fde242709f5803ed816ac8fbD.jpg','Tarjetas gráficas');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `apellidos` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `estado` bit(1) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `numero_documento` varchar(11) NOT NULL,
  `contraseña` varchar(120) NOT NULL,
  `sexo` enum('MASCULINO','FEMENINO','OTROS') DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_rol` varchar(50) DEFAULT NULL,
  `id_tipo_documento_identidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`),
  UNIQUE KEY `UK_hx9w6tpma79yxjbk83xfiw7fo` (`numero_documento`),
  UNIQUE KEY `UK_tk9b57e4h18v9aaf5poja1owu` (`telefono`),
  KEY `FKs4usj99lah5eldgd5g61l3c3w` (`id_rol`),
  KEY `FKmvltgtejvumndm6pncxup322x` (`id_tipo_documento_identidad`),
  CONSTRAINT `FKmvltgtejvumndm6pncxup322x` FOREIGN KEY (`id_tipo_documento_identidad`) REFERENCES `tipo_documento_identidad` (`id_tipo_documento_identidad`),
  CONSTRAINT `FKs4usj99lah5eldgd5g61l3c3w` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('CLI-ANA-2LK002','2024-04-16 01:53:13.254485','2024-04-16 01:53:13.254485','Manolete Mendoza','Av. 456, Ciudad','anaManol@gmail.com',_binary '','Ana','87654371','$2a$10$EFJQmI4dTZKfyOtzcAVneeG9lgh5XjwmCgmPqkAEFbmNHvbiRvbLK','FEMENINO','987654321','ROL-CLI-VLA003','TID-DNI-AMO001'),('CLI-CLA-4GH004','2024-04-16 01:53:13.272540','2024-04-16 01:53:13.272540','Saenz Peña','Av. 789, Ciudad','claudiaSaez@example.com',_binary '','Claudia','85654372','$2a$10$9tLoGbzHIb4l58C.7TKB8eJ0G4XV63A3d40mM67NsiLiMqB2hbxDy','FEMENINO','989023456','ROL-CLI-VLA003','TID-DNI-AMO001'),('CLI-JUP-1AS001','2024-04-16 01:53:13.242446','2024-04-16 01:53:13.242446','Pérez Sosa','Calle 123, Ciudad','juanPer@example.com',_binary '','Juana','87652372','$2a$10$OMt8E5NtcCIrTYwVuuajfe/I9yPa510QHT1VybBb3pyuz23tNErNu','MASCULINO','903456789','ROL-CLI-VLA003','TID-DNI-AMO001'),('CLI-ROB-3DF003','2024-04-16 01:53:13.264016','2024-04-16 01:53:13.264016','Díaz Lopez','Calle 789, Ciudad','manuLopez@yahoo.com',_binary '','Manuel','87354352','$2a$10$6E3yeJU6CbH7.ufPa7EAtOarb4aNtOG8TsKlYJ.xLn1iXsfXC5fJq','MASCULINO','956389123','ROL-CLI-VLA003','TID-DNI-AMO001');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura_compra`
--

DROP TABLE IF EXISTS `detalle_factura_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_factura_compra` (
  `id_detalle_factura_compra` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `id_factura_compra` varchar(20) DEFAULT NULL,
  `id_proveedor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_factura_compra`),
  KEY `FK2fnd14ep5ada0evxn25mxi1ko` (`id_factura_compra`),
  KEY `FKm7jcr7jmqs2hh23extatvxw6f` (`id_proveedor`),
  CONSTRAINT `FK2fnd14ep5ada0evxn25mxi1ko` FOREIGN KEY (`id_factura_compra`) REFERENCES `factura_compra` (`id_factura_compra`),
  CONSTRAINT `FKm7jcr7jmqs2hh23extatvxw6f` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura_compra`
--

LOCK TABLES `detalle_factura_compra` WRITE;
/*!40000 ALTER TABLE `detalle_factura_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_guia_entrada`
--

DROP TABLE IF EXISTS `detalle_guia_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_guia_entrada` (
  `id_detalle_guia_entrada` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `cantidad_detalle_entrada` int DEFAULT NULL,
  `id_guia_entrada` varchar(20) DEFAULT NULL,
  `id_producto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_guia_entrada`),
  KEY `FKbsakipy6sj74bv3dfkj0l778i` (`id_guia_entrada`),
  KEY `FKlgws0r2mtw9pgj9l33tr71ijb` (`id_producto`),
  CONSTRAINT `FKbsakipy6sj74bv3dfkj0l778i` FOREIGN KEY (`id_guia_entrada`) REFERENCES `guia_entrada` (`id_guia_entrada`),
  CONSTRAINT `FKlgws0r2mtw9pgj9l33tr71ijb` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_guia_entrada`
--

LOCK TABLES `detalle_guia_entrada` WRITE;
/*!40000 ALTER TABLE `detalle_guia_entrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_guia_entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_guia_salida`
--

DROP TABLE IF EXISTS `detalle_guia_salida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_guia_salida` (
  `id_detalle_guia_salida` binary(16) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `numero_detalle` varchar(255) DEFAULT NULL,
  `id_guia_salida` binary(16) DEFAULT NULL,
  `id_producto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_guia_salida`),
  KEY `FKqs8dqwa7ktijfynwup8c3nrp4` (`id_guia_salida`),
  KEY `FKjij9i49lxjxeww0dety86ackf` (`id_producto`),
  CONSTRAINT `FKjij9i49lxjxeww0dety86ackf` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `FKqs8dqwa7ktijfynwup8c3nrp4` FOREIGN KEY (`id_guia_salida`) REFERENCES `guia_salida` (`id_gui_salida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_guia_salida`
--

LOCK TABLES `detalle_guia_salida` WRITE;
/*!40000 ALTER TABLE `detalle_guia_salida` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_guia_salida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_detalle_venta`
--

DROP TABLE IF EXISTS `doc_detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doc_detalle_venta` (
  `id` binary(16) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `cantidad` int NOT NULL,
  `precio_total` decimal(38,2) NOT NULL,
  `precio_unitario` decimal(38,2) NOT NULL,
  `productos_id_producto` varchar(50) DEFAULT NULL,
  `id_venta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK89fwfd5blyvrip2h4c1jhkk1p` (`productos_id_producto`),
  KEY `FKcxg27b1aah7irymthqaa23vfa` (`id_venta`),
  CONSTRAINT `FK89fwfd5blyvrip2h4c1jhkk1p` FOREIGN KEY (`productos_id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `FKcxg27b1aah7irymthqaa23vfa` FOREIGN KEY (`id_venta`) REFERENCES `doc_venta` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_detalle_venta`
--

LOCK TABLES `doc_detalle_venta` WRITE;
/*!40000 ALTER TABLE `doc_detalle_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `doc_detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_venta`
--

DROP TABLE IF EXISTS `doc_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doc_venta` (
  `id_venta` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `estado_envio` tinyint DEFAULT NULL,
  `fecha_entrega` date NOT NULL,
  `fecha_envio` date NOT NULL,
  `igv` decimal(38,2) DEFAULT NULL,
  `numero_comprobante` varchar(50) NOT NULL,
  `op_gravadas` decimal(38,2) DEFAULT NULL,
  `precio_total` decimal(38,2) DEFAULT NULL,
  `id_cliente` varchar(50) DEFAULT NULL,
  `id_tipo_transaccion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `FKib6xr9obq5t9xwwbe06e4gyan` (`id_cliente`),
  KEY `FKn19w1r4j13yx9brgyfi1i0hb0` (`id_tipo_transaccion`),
  CONSTRAINT `FKib6xr9obq5t9xwwbe06e4gyan` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `FKn19w1r4j13yx9brgyfi1i0hb0` FOREIGN KEY (`id_tipo_transaccion`) REFERENCES `tipo_transaccion` (`id_tipo_transaccion`),
  CONSTRAINT `doc_venta_chk_1` CHECK ((`estado_envio` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_venta`
--

LOCK TABLES `doc_venta` WRITE;
/*!40000 ALTER TABLE `doc_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `doc_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `id_cliente` varchar(30) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `apellidos` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `estado` bit(1) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `numero_documento` varchar(11) NOT NULL,
  `contraseña` varchar(120) NOT NULL,
  `sexo` enum('MASCULINO','FEMENINO','OTROS') DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_rol` varchar(50) NOT NULL,
  `id_tipo_documento_identidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK_nihg474u49g6e8aolp4lwrj6e` (`email`),
  UNIQUE KEY `UK_nasrrxma712u3u9pitig6ec4v` (`numero_documento`),
  UNIQUE KEY `UK_oinctok6ayd7sbigtv9j1itj6` (`telefono`),
  KEY `FK79c898nfvdjbf53s2e6iluioa` (`id_rol`),
  KEY `FKqrinrpw2qe31fbm8sdkuoxs3m` (`id_tipo_documento_identidad`),
  CONSTRAINT `FK79c898nfvdjbf53s2e6iluioa` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `FKqrinrpw2qe31fbm8sdkuoxs3m` FOREIGN KEY (`id_tipo_documento_identidad`) REFERENCES `tipo_documento_identidad` (`id_tipo_documento_identidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('EMP-ADM-1K001','2024-04-16 01:53:13.710368','2024-04-16 01:53:13.710368','Admin','Calle 123, Ciudad','importacionesDominguez2024@gmail.com',_binary '','Admin','87654372','$2a$10$Aj9JUuvE1CcvaJCmZwL3m.ywiyxDlSGKsdCXYwYYxI1Ex3dcrUnnG','MASCULINO','923456789','ROL-ADM-PUT001','TID-DNI-AMO001'),('EMP-ANA-2LK002','2024-04-16 01:53:13.729948','2024-04-16 01:53:13.729948','López Flores','Av. 456, Ciudad','anaPerez@gmail.com',_binary '','Ana','87654371','$2a$10$RXLHClQgKOjXaALY733pg.dgPRHd4tM2cUPl25/2VQFUdBYNH1OI2','FEMENINO','987654321','ROL-EMP-ALKM02','TID-DNI-AMO001'),('EMP-CLA-4GH004','2024-04-16 01:53:13.751045','2024-04-16 01:53:13.751045','Gómez Rodriguez','Av. 789, Ciudad','clauGomez@hotmail.com',_binary '','Claudia','85654372','$2a$10$l605NLJGycBwapPQvx/g4.fv0lpK/RcwFpLjZv/.0Aw/4FP976U6O','FEMENINO','989123456','ROL-EMP-ALKM02','TID-DNI-AMO001'),('EMP-JUP-1AS001','2024-04-16 01:53:13.720412','2024-04-16 01:53:13.720412','Pérez Montes','Calle 123, Ciudad','juanPerez@gmail.com',_binary '','Juan','87652372','$2a$10$DaTJfhc2ZG6RwyIZ2LYOWO0/d.rKF6wYSc1OVZe57FumXPaucWUlK','MASCULINO','913456789','ROL-EMP-ALKM02','TID-DNI-AMO001'),('EMP-ROB-3DF003','2024-04-16 01:53:13.742010','2024-04-16 01:53:13.742010','Díaz  Manizales','Calle 789, Ciudad','robertDiaz@gmail.com',_binary '','Roberto','87354352','$2a$10$ezjbluVUVmaV5.9g4TKekOOcy6c2.2icMowQV5YM5Zg.NqZ9xmTlu','MASCULINO','956789123','ROL-EMP-ALKM02','TID-DNI-AMO001');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_compra`
--

DROP TABLE IF EXISTS `factura_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_compra` (
  `id_factura_compra` varchar(20) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `igv` decimal(38,2) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `fecha_compra` date NOT NULL,
  `monto_total` decimal(38,2) NOT NULL,
  `subtotal` decimal(38,2) NOT NULL,
  `id_metodo_pago` varchar(20) DEFAULT NULL,
  `id_order_compra` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_factura_compra`),
  KEY `FKpadn9r93p8yhkp6pchfpctd1u` (`id_metodo_pago`),
  KEY `FKed1rdt5qw9bbruu0vdqp0ho5y` (`id_order_compra`),
  CONSTRAINT `FKed1rdt5qw9bbruu0vdqp0ho5y` FOREIGN KEY (`id_order_compra`) REFERENCES `orden_compra` (`id_orden_compra`),
  CONSTRAINT `FKpadn9r93p8yhkp6pchfpctd1u` FOREIGN KEY (`id_metodo_pago`) REFERENCES `metodo_pago` (`id_metodo_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_compra`
--

LOCK TABLES `factura_compra` WRITE;
/*!40000 ALTER TABLE `factura_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guia_entrada`
--

DROP TABLE IF EXISTS `guia_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guia_entrada` (
  `id_guia_entrada` varchar(20) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_entrada` date NOT NULL,
  `id_factura_compra` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_guia_entrada`),
  KEY `FKm9lownbyf6xovmtbhmjkt6do0` (`id_factura_compra`),
  CONSTRAINT `FKm9lownbyf6xovmtbhmjkt6do0` FOREIGN KEY (`id_factura_compra`) REFERENCES `factura_compra` (`id_factura_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guia_entrada`
--

LOCK TABLES `guia_entrada` WRITE;
/*!40000 ALTER TABLE `guia_entrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `guia_entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guia_salida`
--

DROP TABLE IF EXISTS `guia_salida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guia_salida` (
  `id_gui_salida` binary(16) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `numero_guia_salida` varchar(255) DEFAULT NULL,
  `numero_salida` varchar(255) DEFAULT NULL,
  `id_cliente` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_gui_salida`),
  KEY `FK42r57g1scsfw9epmigm8lrgge` (`id_cliente`),
  CONSTRAINT `FK42r57g1scsfw9epmigm8lrgge` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guia_salida`
--

LOCK TABLES `guia_salida` WRITE;
/*!40000 ALTER TABLE `guia_salida` DISABLE KEYS */;
/*!40000 ALTER TABLE `guia_salida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pago`
--

DROP TABLE IF EXISTS `metodo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pago` (
  `id_metodo_pago` varchar(20) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id_metodo_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pago`
--

LOCK TABLES `metodo_pago` WRITE;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_compra`
--

DROP TABLE IF EXISTS `orden_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_compra` (
  `id_orden_compra` varchar(20) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `igv` decimal(38,2) NOT NULL,
  `estado_orden` varchar(20) NOT NULL,
  `fecha_orden` date NOT NULL,
  `montototal` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `id_metodo_pago` varchar(20) DEFAULT NULL,
  `id_proveedor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_orden_compra`),
  KEY `FKd1koickm24yx2o65xsff0arlf` (`id_metodo_pago`),
  KEY `FKpp1o3qr9ak3nnxf9qdiph46bu` (`id_proveedor`),
  CONSTRAINT `FKd1koickm24yx2o65xsff0arlf` FOREIGN KEY (`id_metodo_pago`) REFERENCES `metodo_pago` (`id_metodo_pago`),
  CONSTRAINT `FKpp1o3qr9ak3nnxf9qdiph46bu` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra`
--

LOCK TABLES `orden_compra` WRITE;
/*!40000 ALTER TABLE `orden_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `ficha_tecnica` text NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `stock` int NOT NULL,
  `id_categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `FK9nyueixdsgbycfhf7allg8su` (`id_categoria`),
  CONSTRAINT `FK9nyueixdsgbycfhf7allg8su` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('PROD-AER-PREDQW','2024-04-16 01:53:12.607981','2024-04-16 01:53:12.607981','Monitor gaming de 27 pulgadas con resolución WQHD y frecuencia de actualización de 165Hz de la serie Predator de Acer, ofrece un rendimiento excepcional para juegos de alta velocidad.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://promart.vteximg.com.br/arquivos/ids/6736319-1000-1000/imageUrl_2.jpg?v=638114633780030000','Acer','Acer Predator XB273U',599.99,20,'CAT-MON-HG432Z'),('PROD-AKE-V21100','2024-04-16 01:53:12.747033','2024-04-16 01:53:12.747033','Case con panel lateral de vidrio templado y tres ventiladores RGB preinstalados, ofrece una iluminación vibrante y opciones de enfriamiento eficientes para construcciones de PC gaming de gama media a alta.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://es.thermaltake.com/pub/media/wysiwyg/key3/db/products/case/coreX1/main.jpg','Thermaltake','Thermaltake V200 Tempered Glass RGB',109.99,35,'CAT-CAE-LM435R'),('PROD-AMD-RX6800','2024-04-16 01:53:12.461429','2024-04-16 01:53:12.461429','Tarjeta gráfica de la serie RX 6000 de AMD, ofrece un rendimiento excepcional en juegos de alta resolución y aplicaciones de diseño gráfico.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://m.media-amazon.com/images/I/81c3PiQLBUL.jpg','AMD','AMD Radeon RX 6800',579.99,12,'CAT-TJG-ZX321F'),('PROD-AMD-RX6900XT','2024-04-16 01:53:12.443351','2024-04-16 01:53:12.443351','Tarjeta gráfica de alta gama de AMD, ofrece un rendimiento excepcional en juegos y tareas de renderizado intensivas.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://asset.msi.com/resize/image/global/product/product_1612246958fa75eb32b6ae31058dbd816d78fb3d0e.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png','AMD','AMD Radeon RX 6900 XT',999.99,8,'CAT-TJG-ZX321F'),('PROD-AOS-XG279Q','2024-04-16 01:53:12.600443','2024-04-16 01:53:12.600443','Monitor gaming de 27 pulgadas con resolución WQHD y frecuencia de actualización de 170Hz de la serie ROG Strix de ASUS, diseñado para ofrecer una experiencia de juego suave y envolvente.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://dlcdnwebimgs.asus.com/gain/44B3AED5-B1F6-4012-84F8-F624789BB932/w717/h525','ASUS','ASUS ROG Strix XG279Q',499.99,15,'CAT-MON-HG432Z'),('PROD-ASR-X570','2024-04-16 01:53:12.506124','2024-04-16 01:53:12.506124','Placa base de la serie Taichi de ASRock, compatible con procesadores AMD Ryzen de 3ª generación, con un diseño elegante y características de alta gama.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_909868-MPE69318085184_052023-O.webp','ASRock','ASRock X570 Taichi',299.99,18,'CAT-PAC-YH874B'),('PROD-ASS-AS1210','2024-04-16 01:53:12.719943','2024-04-16 01:53:12.719943','Fuente de alimentación modular de 850W con certificación 80 Plus Platinum y diseño con iluminación RGB integrada, proporciona un suministro de energía estable y eficiente con un aspecto visual impresionante para sistemas de gaming de gama alta.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://store.teslards.pe/wp-content/uploads/2021/05/D_NQ_NP_798013-MPE31893258115_082019-O.jpg','ASUS','ASUS ROG Thor 850P',219.99,40,'CAT-PSU-VN876P'),('PROD-ASS-Z590','2024-04-16 01:53:12.479997','2024-04-16 01:53:12.479997','Placa base de alta gama de la serie ROG Strix de ASUS, diseñada para procesadores Intel de 10ª y 11ª generación, con características avanzadas para gaming y overclocking.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://cyccomputer.pe/33292-large_default/placa-asus-rog-strix-z590-e-gaming-wifi-ddr4-lga-1200-pn90mb1640-m0eayo.jpg','ASUS','ASUS ROG Strix Z590-E Gaming',349.99,15,'CAT-PAC-YH874B'),('PROD-ATC-2K1002','2024-04-16 01:53:12.390994','2024-04-16 01:53:12.390994','Potente procesador de la serie Ryzen de AMD, ideal para aplicaciones exigentes y juegos de alta gama.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://pcexpress.pe/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/p/cpam4r95950x.jpg','AMD','AMD Ryzen 9 5950X',799.99,15,'CAT-POC-AS120K'),('PROD-BNQ-EX270Q','2024-04-16 01:53:12.616490','2024-04-16 01:53:12.616490','Monitor gaming de 27 pulgadas con resolución 2K y frecuencia de actualización de 144Hz de la serie EX de BenQ, ofrece una experiencia de juego fluida y colores vibrantes.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://image.benq.com/is/image/benqco/ex2780q-front-2?$ResponsivePreset$&fmt=png-alpha','BenQ','BenQ EX2780Q',399.99,25,'CAT-MON-HG432Z'),('PROD-COL-ML360R','2024-04-16 01:53:12.661179','2024-04-16 01:53:12.661179','Sistema de enfriamiento líquido todo en uno con radiador de 360 mm y tres ventiladores MF120R ARGB, ofrece una refrigeración potente y una iluminación vibrante para sistemas de gaming de alto rendimiento.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.impacto.com.pe/storage/products/1641940542.jpg','Cooler Master','Cooler Master MasterLiquid ML360R RGB',179.99,30,'CAT-COO-BC123W'),('PROD-COO-MWE850','2024-04-16 01:53:12.712413','2024-04-16 01:53:12.712413','Fuente de alimentación modular de 850W con certificación 80 Plus Gold, ofrece una entrega de energía confiable y eficiente para sistemas de gaming de alto rendimiento.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://dojiw2m9tvv09.cloudfront.net/71228/product/11378.png','Cooler Master','Cooler Master MWE 850 Gold',119.99,35,'CAT-PSU-VN876P'),('PROD-COO-TD5A00','2024-04-16 01:53:12.740033','2024-04-16 01:53:12.740033','Case de torre completa con panel frontal de malla y tres ventiladores ARGB preinstalados, ofrece un flujo de aire superior y un diseño estético para construcciones de PC gaming llamativas.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.yamoshi.com.pe/22617-large_default/case-cooler-master-masterbox-td500-mesh-white-mcb-d500d-wgnn-s01-argb.jpg','Cooler Master','Cooler Master MasterBox TD500',99.99,30,'CAT-CAE-LM435R'),('PROD-COR-HA100I','2024-04-16 01:53:12.642610','2024-04-16 01:53:12.642610','Sistema de enfriamiento líquido todo en uno con radiador de 240 mm y dos ventiladores PWM de 120 mm, ofrece un rendimiento de refrigeración excepcional para procesadores de alto rendimiento en sistemas de gaming.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://assets.corsair.com/image/upload/c_pad,q_auto,h_1024,w_1024,f_auto/products/Certified-Refurbished/CW-9060042-WW/Gallery/H100i_RGB_PLAT_SE_01.webp?width=1080&quality=85&auto=webp&format=pjpg','CORSAIR','CORSAIR Hydro Series H100i RGB Platinum',159.99,20,'CAT-COO-BC123W'),('PROD-COR-HS60RO','2024-04-16 01:53:12.584377','2024-04-16 01:53:12.584377','Audífonos para juegos con sonido envolvente 7.1 de la serie HS60 Pro de CORSAIR, ofrecen una construcción duradera y comodidad para largas sesiones de juego.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://dojiw2m9tvv09.cloudfront.net/48881/product/corsair-hs60-pro-7-1-virtual-surround-sound-pc-55590.jpg','CORSAIR','CORSAIR HS60 Pro',69.99,35,'CAT-AUD-ADI921'),('PROD-COR-RM850X','2024-04-16 01:53:12.687834','2024-04-16 01:53:12.687834','Fuente de alimentación modular de 850W con certificación 80 Plus Gold, diseñada para ofrecer un suministro de energía estable y eficiente para sistemas de gaming de alto rendimiento.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://store.teslards.pe/wp-content/uploads/2022/11/71Bam0yVOSS._AC_SL1500_.jpg','CORSAIR','CORSAIR RM850x',139.99,20,'CAT-PSU-VN876P'),('PROD-COS-27AA5R','2024-04-16 01:53:12.732996','2024-04-16 01:53:12.732996','Case de media torre con panel lateral de vidrio templado y espacio para hasta seis ventiladores, ofrece una construcción robusta y opciones de enfriamiento versátiles para sistemas gaming de alto rendimiento.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://assets.corsair.com/image/upload/f_auto,q_auto/content/CC-9011132-WW-275R-G-04-built.png','CORSAIR','CORSAIR Carbide Series 275R',89.99,25,'CAT-CAE-LM435R'),('PROD-DEE-GX40V2','2024-04-16 01:53:12.679785','2024-04-16 01:53:12.679785','Disipador de calor de CPU con cuatro tubos de calor y ventilador PWM de 120 mm con iluminación LED azul, ofrece una refrigeración eficiente y silenciosa para procesadores gaming de gama media a alta.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.infotec.com.pe/51656-large_default/cooler-para-procesador-deepcool-gammaxx-400-v2-red-dp-mch4-gmx400v2-rd-led-rojo.jpg','DEEPCOOL','DEEPCOOL GAMMAXX 400 V2',49.99,40,'CAT-COO-BC123W'),('PROD-DTC-4K1004','2024-04-16 01:53:12.411569','2024-04-16 01:53:12.411569','Procesador de la serie Ryzen de AMD, ofrece un excelente rendimiento en gaming y tareas multitarea intensivas.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_921861-MLA51718918703_092022-O.webp','AMD','AMD Ryzen 7 5800X',449.99,30,'CAT-POC-AS120K'),('PROD-EGA-SUPERN','2024-04-16 01:53:12.696845','2024-04-16 01:53:12.696845','Fuente de alimentación totalmente modular de 750W con certificación 80 Plus Gold, ofrece una eficiencia excepcional y un rendimiento estable para sistemas de gaming exigentes.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://images.evga.com/products/gallery/png/220-G5-0750-X1_LG_1.png','EVGA','EVGA SuperNOVA 750 G5',129.99,25,'CAT-PSU-VN876P'),('PROD-GIG-B550','2024-04-16 01:53:12.489528','2024-04-16 01:53:12.489528','Placa base de la serie AORUS de GIGABYTE, compatible con procesadores AMD Ryzen de 3ª generación, con características de alta calidad y soporte para PCIe 4.0.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://xercom.com.pe/wp-content/uploads/2021/02/B550-AORUS-ELITE-1.jpg','GIGABYTE','GIGABYTE B550 AORUS Elite',159.99,20,'CAT-PAC-YH874B'),('PROD-HYX-CLODAL','2024-04-16 01:53:12.571348','2024-04-16 01:53:12.571348','Audífonos para juegos con controladores de 50 mm de la serie Cloud Alpha de HyperX, ofrecen un sonido envolvente y comodidad duradera para largas sesiones de juego.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://row.hyperx.com/cdn/shop/products/hyperx_cloud_alpha_black_1_main_900x.jpg?v=1662420668','HyperX','HyperX Cloud Alpha',89.99,25,'CAT-AUD-ADI921'),('PROD-ITC-1K1001','2024-04-16 01:53:12.338820','2024-04-16 01:53:12.338820','Procesador de última generación para computadoras de alto rendimiento.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.hbltecstore.pe/cdn/shop/files/71Qc91x5b8L._AC_SX679.jpg?v=1683148042','Intel','Intel Core i9-11900K',499.99,20,'CAT-POC-AS120K'),('PROD-LAG-27GL8A','2024-04-16 01:53:12.627032','2024-04-16 01:53:12.627032','Monitor gaming de 27 pulgadas con resolución QHD y frecuencia de actualización de 144Hz de la serie UltraGear de LG, ofrece un tiempo de respuesta rápido y una calidad de imagen excepcional.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.lg.com/es/images/monitores/md06177556/gallery/27gl850-01.jpg','LG','LG 27GL850-B',449.99,30,'CAT-MON-HG432Z'),('PROD-LOQ-G73311','2024-04-16 01:53:12.591407','2024-04-16 01:53:12.591407','Audífonos inalámbricos para juegos con tecnología de micrófono Blue VO!CE de la serie G733 de Logitech, ofrecen un diseño colorido y ligero para una experiencia de juego cómoda y personalizable.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://promart.vteximg.com.br/arquivos/ids/769273-1000-1000/image-12e5169ccc964a4da18813c9045fdb4e.jpg?v=637496878856170000','Logitech','Logitech G733',129.99,40,'CAT-AUD-ADI921'),('PROD-MSI-Z490','2024-04-16 01:53:12.497060','2024-04-16 01:53:12.497060','Placa base de la serie MAG de MSI, compatible con procesadores Intel de 10ª generación, con un diseño robusto y características para gaming.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.impacto.com.pe/storage/products/1640823732.jpg','MSI','MSI MAG Z490 TOMAHAWK',199.99,25,'CAT-PAC-YH874B'),('PROD-NVD-GTX1660','2024-04-16 01:53:12.471470','2024-04-16 01:53:12.471470','Tarjeta gráfica de la serie GTX 16 de NVIDIA, ofrece un excelente rendimiento en juegos de 1080p y 1440p a un precio asequible.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://oechsle.vteximg.com.br/arquivos/ids/15768379-1000-1000/image-13a58032418c44c4a39005fa73e3980b.jpg?v=638280803688700000','NVIDIA','NVIDIA GeForce GTX 1660',249.99,20,'CAT-TJG-ZX321F'),('PROD-NVD-RTX3070','2024-04-16 01:53:12.451895','2024-04-16 01:53:12.451895','Tarjeta gráfica de la serie RTX 3000 de NVIDIA, ofrece un excelente rendimiento en juegos de alta gama y aplicaciones de edición de video.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://store.perudataconsult.net/cdn/shop/products/TarjetadeVideoEVGAGeforceRTX3070TIPORTADA.jpg?v=1647987831','NVIDIA','NVIDIA GeForce RTX 3070',499.99,15,'CAT-TJG-ZX321F'),('PROD-NVD-RTX3080','2024-04-16 01:53:12.433816','2024-04-16 01:53:12.433816','Potente tarjeta gráfica de la serie RTX 3000 de NVIDIA, diseñada para ofrecer un rendimiento excepcional en juegos y aplicaciones de renderizado.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://smartbusiness.pe/cdn/shop/products/81UBILsWwiS._AC_SL1500.jpg?v=1702514253','NVIDIA','NVIDIA GeForce RTX 3080 TI',699.99,10,'CAT-TJG-ZX321F'),('PROD-NXT-H51210','2024-04-16 01:53:12.725941','2024-04-16 01:53:12.725941','Case compacto para PC con panel frontal de vidrio templado y sistema de gestión de cables integrado, ofrece un diseño elegante y funcional para construcciones de PC gaming.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://nzxt.com/assets/cms/34299/1617970872-h510-white-black-mainw-system.png?auto=format&fit=crop&h=1000&w=1000','NZXT','NZXT H510',79.99,20,'CAT-CAE-LM435R'),('PROD-NZT-KRKZ63','2024-04-16 01:53:12.651646','2024-04-16 01:53:12.651646','Sistema de enfriamiento líquido todo en uno con pantalla LCD de 2.36 pulgadas, radiador de 280 mm y dos ventiladores Aer P 140 mm, ofrece un control avanzado de la refrigeración y una estética personalizable para los entusiastas del gaming.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://nzxt.com/assets/cms/34299/1615588736-kraken-z63frontbnwith-fanui.png?auto=format&fit=crop&h=1000&w=1000','NZXT','NZXT Kraken Z63',199.99,25,'CAT-COO-BC123W'),('PROD-OTC-3K1003','2024-04-16 01:53:12.401039','2024-04-16 01:53:12.401039','Procesador de alto rendimiento de la 11ª generación de Intel, adecuado para gaming y aplicaciones de productividad.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://store.teslards.pe/wp-content/uploads/2021/03/1880-intel-core-i9-11900k-35-ghz.jpg','Intel','Intel Core i7-11700K',399.99,25,'CAT-POC-AS120K'),('PROD-QTC-5K1005','2024-04-16 01:53:12.422602','2024-04-16 01:53:12.422602','Procesador de la 11ª generación de Intel, ofrece un excelente equilibrio entre rendimiento y precio para gaming y tareas diarias.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://cyccomputer.pe/34230-medium_default/procesador-intel-core-i5-11600k-390ghz12mb-lga-1200-pnbx8070811600k.jpg','Intel','Intel Core i5-11600K',269.99,40,'CAT-POC-AS120K'),('PROD-ROG-Z590','2024-04-16 01:53:12.514630','2024-04-16 01:53:12.514630','Placa base de la serie FTW de asus, diseñada para procesadores Intel de 10ª y 11ª generación, con un diseño sólido y características de overclocking.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://www.impacto.com.pe/storage/products/md/167302276825895.jpg','ASUS','ROG STRIX Z590 FTW WiFi',279.99,12,'CAT-PAC-YH874B'),('PROD-RZR-BLACKS','2024-04-16 01:53:12.563305','2024-04-16 01:53:12.563305','Audífonos para juegos con sonido envolvente THX Spatial Audio de la serie BlackShark V2 de Razer, diseñados para ofrecer una experiencia de juego inmersiva y cómoda.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://promart.vteximg.com.br/arquivos/ids/7413995-1000-1000/image-de617e4422604921856f50ab89b94131.jpg?v=638272155293830000','Razer','Razer BlackShark V2',99.99,20,'CAT-AUD-ADI921'),('PROD-SAP-LC32GA','2024-04-16 01:53:12.634068','2024-04-16 01:53:12.634068','Monitor gaming curvo de 32 pulgadas con resolución QHD y frecuencia de actualización de 240Hz de la serie Odyssey G7 de Samsung, ofrece una experiencia de juego inmersiva y de alta velocidad.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://image-us.samsung.com/SamsungUS/samsungbusiness/products/computing/monitors/g-series/lc27g75tqsnxza/gallery/C27G75T_001_Front_Black_1200x1200.jpg?$support-product-hero-jpg$','Samsung','Samsung Odyssey G7 LC32G75TQSNXZA',699.99,35,'CAT-MON-HG432Z'),('PROD-SEA-2TBHDD','2024-04-16 01:53:12.532207','2024-04-16 01:53:12.532207','Disco duro interno de 3.5 pulgadas con capacidad de 2TB de la serie Barracuda de Seagate, ofrece un equilibrio entre capacidad, rendimiento y precio para aplicaciones de almacenamiento masivo.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_767861-MLA70115526648_062023-O.webp','Seagate','Seagate Barracuda 2TB HDD',69.99,25,'CAT-AMA-ALM421'),('PROD-SEC-FOCU11','2024-04-16 01:53:12.704886','2024-04-16 01:53:12.704886','Fuente de alimentación totalmente modular de 850W con certificación 80 Plus Gold, garantiza una distribución de energía estable y eficiente para sistemas de gaming de alta gama.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_831712-MPE70393736610_072023-O.webp','Seasonic','Seasonic Focus Plus 850 Gold',149.99,30,'CAT-PSU-VN876P'),('PROD-SLL-004ASF','2024-04-16 01:53:12.781170','2024-04-16 01:53:12.781170','Silla ergonómica con diseño moderno y funcionalidades avanzadas. Incluye sistema de masaje integrado, reposapiés retráctil, y soporte lumbar y cervical ajustable para proporcionar una experiencia de juego óptima.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://falabella.scene7.com/is/image/FalabellaPE/gsc_117543285_1799507_2?wid=800&hei=800&qlt=70','DREIZT','Silla Gamer Dreizt Titan Series Negro Premium Almohadilla Inteligente',349.99,20,'CAT-SLL-PL678E'),('PROD-SLL-00XAS5','2024-04-16 01:53:12.788691','2024-04-16 01:53:12.788691','Silla gaming con diseño aerodinámico y confortable. Dispone de soporte lumbar ajustable, reposabrazos 3D y cojín de masaje para garantizar una experiencia de juego placentera durante horas.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://falabella.scene7.com/is/image/FalabellaPE/gsc_128485679_5218434_1?wid=800&hei=800&qlt=70','ANTRYX','SILLA GAMER ANTRYX RECLINABLE CON ALMOHADILLA XTREME RACING NOVA BLACK',279.99,25,'CAT-SLL-PL678E'),('PROD-SLL-1KSA1O','2024-04-16 01:53:12.767109','2024-04-16 01:53:12.767109','Silla con diseño deportivo y estructura robusta. Incluye soporte lumbar integrado, reposabrazos ajustables y acolchados, así como reclinación de hasta 180 grados para mayor confort.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://falabella.scene7.com/is/image/FalabellaPE/gsc_128279450_5136306_1?wid=400&hei=400&qlt=70','NXT','Silla Gamer EliteTech Pro',249.99,40,'CAT-SLL-PL678E'),('PROD-SLL-912KAS','2024-04-16 01:53:12.760602','2024-04-16 01:53:12.760602','Silla ergonómica diseñada para proporcionar comodidad durante largas sesiones de juego. Características incluyen soporte lumbar ajustable, reposabrazos acolchados y reclinación ajustable para una experiencia de juego cómoda.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://es.akracing.com/cdn/shop/products/PRO-blackblue-2_863d3e02-553d-4d40-a44d-3010694224ba_1800x1800.png?v=1583448269','AKRACING','AKRacing Masters Serie Silla Gaming Pro\n',199.99,50,'CAT-SLL-PL678E'),('PROD-SLL-ZM1AS1','2024-04-16 01:53:12.773644','2024-04-16 01:53:12.773644','Silla diseñada para ofrecer la máxima comodidad durante las sesiones de juego prolongadas. Cuenta con respaldo ajustable, cojines lumbares y cervicales, y reposabrazos 4D para adaptarse a las preferencias del usuario.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://falabella.scene7.com/is/image/FalabellaPE/gsc_122889295_3466009_2?wid=800&hei=800&qlt=70','PLAYPRO','Silla Gamer con Masajeador Lumbar Playpro X1 Azul Base de Metal',299.99,30,'CAT-SLL-PL678E'),('PROD-STE-ARCIS7','2024-04-16 01:53:12.577867','2024-04-16 01:53:12.577867','Audífonos inalámbricos para juegos con audio DTS Headphone:X v2.0 de la serie Arctis 7 de SteelSeries, ofrecen una conexión inalámbrica sin pérdida y comodidad para largas sesiones de juego.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_821576-MPE72549507937_102023-O.webp','SteelSeries','SteelSeries Arctis 7',149.99,30,'CAT-AUD-ADI921'),('PROD-SVX-1TBSHD','2024-04-16 01:53:12.557305','2024-04-16 01:53:12.557305','Disco híbrido sólido de 2.5 pulgadas con capacidad de 1TB de la serie FireCuda de Seagate, combina almacenamiento de alta capacidad con rendimiento SSD para una experiencia de juego más rápida.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://m.media-amazon.com/images/I/71wYXvgjyyL._AC_SL1500_.jpg','Seagate','Seagate FireCuda 1TB SSHD',89.99,10,'CAT-AMA-ALM421'),('PROD-THE-RGDUAL','2024-04-16 01:53:12.671252','2024-04-16 01:53:12.671252','Kit de ventiladores de refrigeración con dos ventiladores de 120 mm y un controlador de iluminación ARGB, ofrecen un flujo de aire potente y una iluminación personalizable para mejorar el rendimiento y la estética de tu PC gaming.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6339/6339074_sd.jpg','Thermaltake','Thermaltake Ring Duo 12 RGB',79.99,35,'CAT-COO-BC123W'),('PROD-TSH-500VGB','2024-04-16 01:53:12.539730','2024-04-16 01:53:12.539730','Disco duro interno de 3.5 pulgadas con capacidad de 500GB de la serie P300 de Toshiba, ofrece un almacenamiento confiable para aplicaciones de uso general.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_Q_NP_622097-MLA26004565921_092017-O.webp','Toshiba','Toshiba P300 500GB HDD',39.99,20,'CAT-AMA-ALM421'),('PROD-UGR-GEMINI','2024-04-16 01:53:12.754073','2024-04-16 01:53:12.754073','Case compacto con diseño de doble cámara para una gestión eficiente del flujo de aire y enfriamiento, ofrece un aspecto elegante y funcional para construcciones de PC gaming compactas.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7JujKmrcseo6Qd2Mkcfzf6xeziHoI4CxH7JB2oIaGNn3LTK6lIrpkH7KuDbevtA_BrHg','Cougar','Cougar Gemini T PRO',69.99,40,'CAT-CAE-LM435R'),('PROD-WSD-1TBHDD','2024-04-16 01:53:12.522165','2024-04-16 01:53:12.522165','Útil para guardar programas y documentos con su capacidad de 1 TB.Interfaz de conexión: NVMe.Incrementa el rendimiento de tu equipo.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://http2.mlstatic.com/D_NQ_NP_763844-MLU72461321556_102023-O.webp','Western Digital','Disco Solido M.2 Western Digital Blue Sn580 1tb Pcie Nvme',49.99,30,'CAT-AMA-ALM421'),('PROD-WTD-4TBHDD','2024-04-16 01:53:12.549258','2024-04-16 01:53:12.549258','Disco duro interno de 3.5 pulgadas con capacidad de 4TB de la serie Red de Western Digital, optimizado para sistemas NAS (Network Attached Storage) para entornos de almacenamiento en red.',_binary '','{\"frecuencia\":\"3.9 GHz\",\"nucleos\":\"6\",\"hilos\":\"12\",\"memoria\":\"6 GB GDDR5\",\"nucleosCUDA\":\"1408\",\"frecuenciaReloj\":\"1.53 GHz\",\"nucleosStream\":\"3840\",\"socket\":\"LGA1200\",\"chipset\":\"Intel Z590\",\"formato\":\"ATX\",\"capacidad\":\"1TB\",\"interfaz\":\"SATA 6 Gb/s\",\"velocidad\":\"5400 RPM\",\"tecnologia\":\"SSHD\",\"tipo\":\"Over-ear\",\"conectividad\":\"Inalámbrico\",\"compatibilidad\":\"MicroATX, Mini-ITX\",\"iluminacionRGB\":\"Sí\",\"tamaño\":\"MicroATX\",\"resolucion\":\"2560 x 1440\",\"frecuenciaRefresco\":\"240Hz\",\"tecnologiaPanel\":\"VA\",\"tipoEnfriamiento\":\"Aire\",\"potencia\":\"850W\",\"certificacion\":\"80 Plus Platinum\",\"modularidad\":\"Sí\",\"ventiladoresIncluidos\":\"1\",\"material\":\"Cuero sintético\",\"capacidadPeso\":\"135 kg\",\"dimensiones\":\"78 x 68 x 128 cm\"}','https://storage.googleapis.com/catalog-pictures-carrefour-es/catalog/pictures/hd_510x_/0718037855967_1.jpg','Western Digital','Western Digital Red 4TB HDD',129.99,15,'CAT-AMA-ALM421');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id_proveedor` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombres` varchar(80) NOT NULL,
  `numero_documento` varchar(11) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_tipo_documento_identidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`),
  KEY `FKomni28yh3wakekg5jnlickiue` (`id_tipo_documento_identidad`),
  CONSTRAINT `FKomni28yh3wakekg5jnlickiue` FOREIGN KEY (`id_tipo_documento_identidad`) REFERENCES `tipo_documento_identidad` (`id_tipo_documento_identidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES ('PROV-AMD-1K001','2024-04-16 01:53:13.762585','2024-04-16 01:53:13.762585','Calle Arequipa 123, Ciudad','AMDCenter@gmail.com',_binary '','AMDCenter','89675432','123456789','TID-DNI-AMO001'),('PROV-INT-1AS001','2024-04-16 01:53:13.768631','2024-04-16 01:53:13.768631','Calle Arequipa 123, Ciudad','IntelMarCenter@gmail.com',_binary '','IntelCenter','89675432','123456789','TID-DNI-AMO001');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` varchar(50) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `UK_43kr6s7bts1wqfv43f7jd87kp` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES ('ROL-ADM-PUT001','2024-04-16 01:53:12.808770','2024-04-16 01:53:12.808770','Rol con permisos de administración y control total del sistema.',_binary '','Administrador'),('ROL-CLI-VLA003','2024-04-16 01:53:12.820922','2024-04-16 01:53:12.820922','Rol con permisos limitados para la visualización y compra de productos.',_binary '','Cliente'),('ROL-EMP-ALKM02','2024-04-16 01:53:12.815790','2024-04-16 01:53:12.815790','Rol con permisos limitados para la gestión de productos y ventas.',_binary '','Empleado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento_identidad`
--

DROP TABLE IF EXISTS `tipo_documento_identidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_documento_identidad` (
  `id_tipo_documento_identidad` varchar(255) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_tipo_documento_identidad`),
  UNIQUE KEY `UK_geptg9fa0cmk7jt9xl5ffucpk` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento_identidad`
--

LOCK TABLES `tipo_documento_identidad` WRITE;
/*!40000 ALTER TABLE `tipo_documento_identidad` DISABLE KEYS */;
INSERT INTO `tipo_documento_identidad` VALUES ('TID-DNI-AMO001','2024-04-16 01:53:12.843513','2024-04-16 01:53:12.833964',_binary '','DNI');
/*!40000 ALTER TABLE `tipo_documento_identidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_transaccion`
--

DROP TABLE IF EXISTS `tipo_transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_transaccion` (
  `id_tipo_transaccion` varchar(20) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo_transaccion`),
  UNIQUE KEY `UK_4acs8ftxe48wc23ak75qx38e4` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_transaccion`
--

LOCK TABLES `tipo_transaccion` WRITE;
/*!40000 ALTER TABLE `tipo_transaccion` DISABLE KEYS */;
INSERT INTO `tipo_transaccion` VALUES ('TTR-VENTA-TOC201','2024-04-16 01:53:13.779164','2024-04-16 01:53:13.779164',_binary '','Boleta');
/*!40000 ALTER TABLE `tipo_transaccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16  1:53:50
