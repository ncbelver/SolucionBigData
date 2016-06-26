 /*   bbdd.aplicacionesweb.sql: contiene la estructura de tablas y datos iniciales del proyecto 

    Copyright (C) 2016  Natividad Crespo Belver. 
    Proyecto desarrollado como parte del TFG en la Universidad Internacional de la Rioja

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

--  ESTRUCTURA Y DATOS DEL ESQUEMA DE BD APLICACIONESWEB --                                                                                                                                                                                                                                                                                           
-- Table structure for table `aplicaciones
DROP TABLE IF EXISTS `aplicaciones`;
CREATE TABLE `aplicaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador de la aplicación',
  `codigo` varchar(3) DEFAULT NULL COMMENT 'codigo de la aplicacion. 3 letras para identificar la aplicacion',
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `IPhost` varchar(15) DEFAULT NULL,
  `IPlog` varchar(15) DEFAULT NULL,
  `idTipo` int(11) DEFAULT NULL,
  `idIdioma` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idDepto` int(11) DEFAULT NULL,
  `idArea` int(11) DEFAULT NULL,
  `idCiudad` varchar(3) DEFAULT NULL,
  `idPais` varchar(3) DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  `fechaBaja` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='Almacena aplicaciones junto a información espedífica de cada una de ellas, nombre, descripcion de su funcionalidad, IP de ejecucion, IP de la ubicacion del LOG..';
-- Dumping data for table `aplicaciones`       
LOCK TABLES `aplicaciones` WRITE;                                              
INSERT INTO `aplicaciones` VALUES (1,'GOT','Gestor de Ordenes ','Gestion de Ordenes de Transferencia de Pensiones','192.168.22.66','192.168.22.66',1,1,1,1,1,'ESP','ESP','2015-01-01
10:00:00',NULL),(2,'GPP','Gestionar Pagos','Gestion de pagos (cursos, anticipos, etc.)','192.168.22.64','192.168.22.64',1,1,3,1,1,'ESP','ESP','2015-01-01
10:00:00',NULL),(3,'SIA','Supervisar Impugnaciones','Gestion de las impugnaciones administrativas','192.168.22.66','192.168.22.66',1,1,4,1,1,'ESP','ESP','2015-01-01
10:00:00',NULL),(4,'IBI','Inventario de Bienes Inmuebles','Inventario de Bienes Inmuebles de la Entidad','192.168.22.65','192.168.22.65',1,1,2,1,3,'ESP','ESP','2015-01-01
10:00:00',NULL),(5,'GCA','Gestionar  Contratos','Gestionar Contratos de Arrendamientos de Bienes e Inmuebles','192.168.22.65','192.168.22.65',1,1,4,1,3,'ESP','ESP','2015-01-01
10:00:00',NULL),(6,'VIO','Venta de Inmuebles','Venta de Inmuebles de la Organización','192.168.22.68','192.168.22.68',1,1,2,1,3,'ESP','ESP','2015-01-01
10:00:00',NULL),(7,'MIT','Mantenimiento de Inspecciones','Gestion de Tramites Legales y Tecnicos de las Inspecciones Tecnicas de
Edificios','192.168.22.66','192.168.22.66',1,1,3,1,2,'ESP','ESP','2015-01-01 10:00:00',NULL),(8,'VBS','Valorar Buzon Sugerencias','Buzon de
Sugerencias','192.168.22.60','192.168.22.60',1,1,1,1,4,'ESP','ESP','2015-01-01 10:00:00',NULL),(9,'SCA','Seguimiento y Control','Seguimiento y Control de actuaciones de Servicios
de Prevención y Salud','192.168.22.60','192.168.22.60',1,1,2,1,4,'ESP','ESP','2015-01-01 10:00:00',NULL);                                                                                                                                                                                                                                                            
UNLOCK TABLES;
-- Table structure for table `versiones`
DROP TABLE IF EXISTS `versiones`;
CREATE TABLE `versiones` (
  `idAplic` int(2) NOT NULL,
  `idVersion` int(2) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `numIniReq` int(2) DEFAULT NULL,
  `numFinReq` int(2) DEFAULT NULL,
  `numUsuarios` int(2) DEFAULT NULL,
  `idrolUsuario` int(2) DEFAULT NULL,
  `fechaInicial` datetime DEFAULT NULL,
  `fechaImplantacion` datetime DEFAULT NULL,
  `defectosFuncionales` int(2) DEFAULT NULL,
  `defectosDesarrollo` int(2) DEFAULT NULL,
  `defectosArquitectura` int(2) DEFAULT NULL,
  PRIMARY KEY (`idAplic`,`idVersion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Dumping data for table `versiones`
LOCK TABLES `versiones` WRITE;
INSERT INTO `versiones` VALUES (1,1,'Version Inicial de la Gestion de Ordenes',5,10,2,1,'2015-01-01 10:00:00','2015-05-31 20:00:00',4,9,4),(1,2,' Actualizaciones de valores
economicos',9,12,3,1,'2015-06-01 10:00:00','2015-12-31 20:00:00',5,6,5),(1,3,'Mejoras estilos de pantalla',7,10,3,1,'2016-01-01 00:00:00','2016-06-01 00:00:00',3,8,1),(2,1,'Versión
Inicial Gestion de Pagos',12,15,4,2,'2015-01-01 10:00:00','2015-12-31 20:00:00',2,2,2),(2,2,'Descripcion del objetivo de la version',6,7,5,2,'2016-01-01 10:00:00','2016-04-20
10:00:00',1,5,1),(2,3,'Descripcion del objetivo de la version',4,8,4,2,'2016-04-21 10:00:00','2016-05-31 10:00:00',1,8,1),(3,1,'Version Inicial Gestion de
Impugnaciones',13,18,6,1,'2015-01-01 10:00:00','2015-01-01 20:00:00',9,2,5),(3,2,'Ampliacion funcional',5,6,3,1,'2016-01-01 10:00:00','2016-05-01 20:00:00',2,6,5),(4,1,'Version
Inicial Inventario de Bienes e Inmuebles',14,19,4,2,'2015-01-01 10:00:00','2015-12-31 20:00:00',8,9,2),(4,2,'Mejoras consultas',5,8,3,1,'2016-01-01 10:00:00','2016-06-01
20:00:00',3,4,1),(5,1,'Version Inicial Gestion de Contratos',12,14,5,2,'2015-01-01 10:00:00','2015-12-31 20:00:00',5,2,3),(5,2,'Mejoras tiempos consultas',8,9,3,2,'2016-01-01
10:00:00','2016-06-01 20:00:00',2,9,3),(6,1,'Version Inicial  Venta de Inmuebles',9,12,3,2,'2015-01-01 10:00:00','2015-12-31 20:00:00',3,2,5),(6,2,'Evolutivo
Funcional',4,6,2,2,'2016-01-01 10:00:00','2016-06-01 20:00:00',3,2,5),(7,1,'Version Inicial Gestion de Inspecciones',7,12,4,2,'2015-01-01 10:00:00','2015-12-31
20:00:00',4,7,2),(7,2,'Ampliación Navegación',7,8,2,2,'2016-01-01 10:00:00','2016-06-01 20:00:00',4,9,3),(8,1,'Version Inicial Buzon',12,15,5,2,'2015-01-01 10:00:00','2015-12-31
20:00:00',2,6,3),(8,2,'Nuevos Formularios',8,11,3,2,'2016-01-01 10:00:00','2016-06-01 20:00:00',2,6,3),(9,1,'Version Inicial  Seguimiento y Control',10,10,2,1,'2015-01-01
10:00:00','2015-12-31 20:00:00',4,8,1),(9,2,'Ampliar campos de entrada',8,11,3,1,'2016-01-01 10:00:00','2016-06-01 20:00:00',3,5,3);
UNLOCK TABLES;
-- Table structure for table `area`
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `idArea` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Dumping data for table `area`
LOCK TABLES `area` WRITE;
INSERT INTO `area` VALUES (1,'Control Administrativo'),(2,'Desarrollos Instrumentales'),(3,'Inventario Regular'),(4,'Mantenimiento Estructural');
UNLOCK TABLES;
-- Table structure for table `ciudades`
DROP TABLE IF EXISTS `ciudades`;
CREATE TABLE `ciudades` (
  `codPais` varchar(10) NOT NULL,
  `idCiudad` int(5) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `poblacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCiudad`,`codPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Dumping data for table `ciudades`
LOCK TABLES `ciudades` WRITE;
INSERT INTO `ciudades` VALUES
('ESP',653,'Madrid','Madrid',2879052);
UNLOCK TABLES;
-- Table structure for table `departamento`
DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
  `idDepartamento` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`idDepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Dumping data for table `departamento`
LOCK TABLES `departamento` WRITE;
INSERT INTO `departamento` VALUES (1,'Gestión Interna');
UNLOCK TABLES;
-- Table structure for table `idiomas`
DROP TABLE IF EXISTS `idiomas`;
CREATE TABLE `idiomas` (
  `idIdioma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idIdioma`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
-- Dumping data for table `idiomas`
LOCK TABLES `idiomas` WRITE;
INSERT INTO `idiomas` VALUES (1,'Español'),(2,'Inglés'),(3,'Francés'),(4,'Alemán'),(5,'Italiano'),(6,'Chino'),(7,'Portugués'),(8,'Japonés'),(9,'Mandarín'),(10,'arabe');
UNLOCK TABLES;
-- Table structure for table `paises`
DROP TABLE IF EXISTS `paises`;
CREATE TABLE `paises` (
  `codPais` text,
  `nombre` text,
  `poplacion` int(11) DEFAULT NULL,
  `esperanzaVida` double DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `independencia` int(11) DEFAULT NULL,
  `region` text,
  `continente` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Dumping data for table `paises`
LOCK TABLES `paises` WRITE;
INSERT INTO `paises` VALUES 
 ('ESP','Spain',39441700,78.8,505992,1492,'Southern Europe','Europe');
UNLOCK TABLES;
-- Table structure for table `responsable`
DROP TABLE IF EXISTS `responsable`;
CREATE TABLE `responsable` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido1` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
-- Dumping data for table `responsable`
LOCK TABLES `responsable` WRITE;
INSERT INTO `responsable` VALUES (1,'Director Servicios Centrales','00000000','Juan  ','Nadie','Nadie','jnn@gmail.es'),(2,'Gestor de
Cuentas','11111111','Maria','España','España','mee@gmail.es'),(3,'Inspectores','22222222','Sara','Ruiz','Ruiz','srr@gmail.es'),(4,'Supervisor de
Aplicaciones','33333333','Luis','Mar','Mar','lmm@gmail.es');
UNLOCK TABLES;
-- Table structure for table `roles`
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- Dumping data for table `roles`
LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES (1,'Supervisor','Ejecuta los analisis y recibe los informes generados'),(2,'Personal  ','Ejecuta los analisis y recibe los informes generados');
UNLOCK TABLES;
-- Table structure for table `tipoaplicacion`
DROP TABLE IF EXISTS `tipoaplicacion`;
CREATE TABLE `tipoaplicacion` (
  `idTipAplicacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`idTipAplicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
-- Dumping data for table `tipoaplicacion`
LOCK TABLES `tipoaplicacion` WRITE;
INSERT INTO `tipoaplicacion` VALUES (1,'Centro'),(2,'Departamento'),(3,'Especial'),(4,'General'),(5,'Personal');
UNLOCK TABLES;


