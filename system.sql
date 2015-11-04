-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-07-2015 a las 07:30:19
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `system`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `clave` varchar(50) CHARACTER SET latin1 NOT NULL,
  `paterno` varchar(80) CHARACTER SET latin1 NOT NULL,
  `materno` varchar(80) CHARACTER SET latin1 DEFAULT NULL,
  `nombre` varchar(80) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB  DEFAULT CHARSET=swe7 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id_admin`, `clave`, `paterno`, `materno`, `nombre`) VALUES
(1, '02420', 'Soria', 'Peláez', 'Oliver');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE IF NOT EXISTS `alumnos` (
  `id_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(25) CHARACTER SET latin1 NOT NULL,
  `paterno` varchar(80) CHARACTER SET latin1 NOT NULL,
  `materno` varchar(80) CHARACTER SET latin1 DEFAULT NULL,
  `nombre` varchar(80) CHARACTER SET latin1 NOT NULL,
  `grado` varchar(50) CHARACTER SET latin1 NOT NULL,
  `grupo` varchar(50) CHARACTER SET latin1 NOT NULL,
  `foto` longtext CHARACTER SET latin1,
  `sexo` char(1) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_alumno`)
) ENGINE=InnoDB  DEFAULT CHARSET=swe7 AUTO_INCREMENT=45 ;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id_alumno`, `matricula`, `paterno`, `materno`, `nombre`, `grado`, `grupo`, `foto`, `sexo`) VALUES
(1, '8965138249', 'Salazar', 'Gutierrez', 'Ivan', 'Primero', 'A', 'C:\\Users\\Bill Gates\\Documents\\MEGAsync\\Profesional\\Respaldos\\Ax_1.8\\images\\foto_4.jpg', 'M'),
(2, '8965714895', 'Hinojosa', 'Juarez', 'Gustavo', 'Primero', 'A', 'pendiente', 'M'),
(3, '1965238264', 'Palomino', 'Collazo', 'Viviana', 'Primero', 'A', 'pendiente', 'F'),
(4, '9865217635', 'Bustamante', 'Carmona', 'Laura', 'Primero', 'A', 'pendiente', 'F'),
(5, '8965412897', 'Nuñez', 'López', 'Saúl', 'Primero', 'A', 'pendiente', 'F'),
(6, '7965458513', 'Aristegui', 'Guzmán', 'Carmen', 'Primero', 'A', 'pendiente', 'F'),
(7, '7458963245', 'Orozco', 'Figueroa', 'Claudia', 'Primero', 'A', 'pendiente', 'F'),
(8, '1036524897', 'Zamora', 'Pulido', 'Wendy', 'Primero', 'A', 'pendiente', 'F'),
(9, '1728392882', 'Tapia', 'Montes', 'Brenda', 'Primero', 'A', 'pendiente', 'F'),
(10, '1364958547', 'Huerta', 'Chávez', 'Julio', 'Primero', 'A', 'pendiente', 'M'),
(11, '9845321785', 'Valdivia', 'Toribio', 'Carlos', 'Primero', 'A', 'pendiente', 'M'),
(12, '1232564581', 'Hernandez', 'Peréz', 'Julio', 'Primero', 'A', 'pendiente', 'M'),
(13, '9878450203', 'Fernandez', 'Maduro', 'Hugo', 'Primero', 'A', 'pendiente', 'M'),
(14, '9521479351', 'Gutierrez', 'Soriano', 'Mauricio', 'Primero', 'A', 'pendiente', 'M'),
(16, '9854126325', 'Sulaimán', 'Contreras', 'Martin', 'Primero', 'B', 'pendiente', 'M'),
(17, '1265789641', 'Ronda', 'Cipres', 'Fernando', 'Primero', 'B', 'pendiente', 'M'),
(18, '9874563210', 'Osorio', 'Placencia', 'Samuel', 'Primero', 'B', 'pendiente', 'M'),
(19, '6321458796', 'Liman', 'Flores', 'Manuel', 'Primero', 'B', 'pendiente', 'M'),
(20, '8965214785', 'Basurto', 'Guelatao', 'Iván', 'Primero', 'B', 'pendiente', 'M'),
(21, '8965214785', 'Placencia', 'Guerrero', 'Paola', 'Primero', 'B', 'pendiente', 'F'),
(22, '1245879652', 'Cienfuegos', 'Ortiz', 'Jesica', 'Primero', 'B', 'pendiente', 'F'),
(23, '8965214578', 'Palapa', 'Ramírez', 'Andrea', 'Primero', 'B', 'C:UsersBill GatesDocumentsMEGAsyncProfesionalRespaldosAx_1.9images', 'F'),
(24, '3698547821', 'Navarrete', 'Ramos', 'Evelin', 'Primero', 'B', 'pendiente', 'F'),
(25, '2145898547', 'Rodriguez', 'Perales', 'Nanci', 'Primero', 'B', 'pendiente', 'F'),
(26, '9621459631', 'Ramirez', 'Chávez', 'Ximena', 'Primero', 'B', 'pendiente', 'F'),
(27, '8965214522', 'Hugalde', 'Bautista', 'Marco', 'Primero', 'B', 'pendiente', 'F'),
(28, '9966554895', 'Oropeza', 'Bustamante', 'Gerardo', 'Segundo', 'B', 'pendiente', 'M'),
(29, '9652146982', 'Fernandez', 'Fuentes', 'Hugo', 'Segundo', 'A', 'pendiente', 'M'),
(30, '9955468001', 'Policarpo', 'Montes', 'Paulino', 'Segundo', 'A', 'pendiente', 'M'),
(31, '9874582164', 'Ríos', 'Juarez', 'Felipe', 'Segundo', 'A', 'pendiente', 'M'),
(32, '9854786321', 'Rubio', 'Romero', 'Juan', 'Segundo', 'A', 'pendiente', 'M'),
(33, '3215784965', 'Buendía', 'Carrazco', 'Josúe', 'Segundo', 'A', 'pendiente', 'M'),
(34, '3215874965', 'Tapía', 'Sánchez', 'Alberto', 'Segundo', 'A', '9617843259', 'M'),
(35, '9652147965', 'Marqúez', 'Olivares', 'Maria', 'Segundo', 'A', 'pendiente', 'F'),
(36, '6632541368', 'Álvarez', 'Insurgente', 'Fernanda', 'Segundo', 'A', 'pendiente', 'F'),
(37, '2189657432', 'Pérez', 'Bautista', 'Jimena', 'Segundo', 'A', 'pendiente', 'F'),
(38, '9645218762', 'Andrade', 'Soriano', 'Julia', 'Segundo', 'A', 'pendiente', 'F'),
(39, '1987648532', 'López', 'Suarez', 'Luisa', 'Segundo', 'A', 'pendiente', 'F'),
(40, '1364798219', 'Nuñez', 'Marroqui', 'Karla', 'Segundo', 'A', 'pendiente', 'F'),
(41, '2010608070', 'Carrasco', 'Huerta', 'Damián', 'Tercero', 'A', 'pendiente', 'M'),
(42, '9876543210', 'Carbajal', 'Contreras', 'Ivan', 'Primero', 'A', 'C:\\Users\\Bill Gates\\Documents\\MEGAsync\\Profesional\\Respaldos\\Ax_1.8\\images\\foto_4.jpg', 'M'),
(44, '0242051320', 'Duarte', 'Noriega', 'Paola', 'Primero', 'A', 'C:\\Users\\Bill Gates\\Documents\\MEGAsync\\Profesional\\Respaldos\\Ax_1.8\\images\\foto_3.jpg', 'F');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control`
--

CREATE TABLE IF NOT EXISTS `control` (
  `id_control` int(11) NOT NULL AUTO_INCREMENT,
  `reg_clave` varchar(50) CHARACTER SET latin1 NOT NULL,
  `fecha` varchar(50) CHARACTER SET latin1 NOT NULL,
  `hora` varchar(80) CHARACTER SET latin1 NOT NULL,
  `reg_status` tinyint(1) NOT NULL,
  `evento` varchar(80) CHARACTER SET latin1 NOT NULL,
  `responsable` varchar(50) NOT NULL,
  PRIMARY KEY (`id_control`)
) ENGINE=InnoDB  DEFAULT CHARSET=swe7 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `control`
--

INSERT INTO `control` (`id_control`, `reg_clave`, `fecha`, `hora`, `reg_status`, `evento`, `responsable`) VALUES
(1, '0242051320', '2015-07-24', '23:47', 1, 'Entrada', '- - - - - - - - - - - - - - - - '),
(2, '0200757755', '2015-07-24', '23:47', 1, 'Retiro', '0242051320'),
(3, '0242051320', '2015-07-24', '23:47', 1, 'Salida', 'Martin Martinez Sandoval'),
(4, '0242051320', '2015-07-24', '23:47', 1, 'Entrada', '- - - - - - - - - - - - - - - - '),
(5, '54321', '2015-07-24', '23:47', 1, 'Retiro', '0242051320'),
(6, '0242051320', '2015-07-24', '23:47', 1, 'Salida', 'Andrea Contreras Lima'),
(7, '0242051320', '2015-07-24', '23:48', 1, 'Entrada', '- - - - - - - - - - - - - - - - '),
(8, '54321', '2015-07-24', '23:48', 1, 'Retiro', '0242051320'),
(9, '0200757755', '2015-07-24', '23:49', 1, 'Retiro', '0242051320'),
(10, '0242051320', '2015-07-24', '23:49', 1, 'Salida', 'Martin Martinez Sandoval'),
(11, '8965214785', '2015-07-28', '00:07', 1, 'Entrada', '- - - - - - - - - - - - - - - - '),
(12, '0200757755', '2015-07-28', '00:10', 1, 'Retiro', '0242051320'),
(13, '0242051320', '2015-07-28', '00:12', 1, 'Entrada', '- - - - - - - - - - - - - - - - '),
(14, '54321', '2015-07-28', '00:12', 1, 'Retiro', '0242051320'),
(15, '0242051320', '2015-07-28', '00:12', 1, 'Salida', 'Andrea Contreras Lima');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `padres`
--

CREATE TABLE IF NOT EXISTS `padres` (
  `id_padre` int(11) NOT NULL AUTO_INCREMENT,
  `clave` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `matricula_hijo` varchar(80) CHARACTER SET latin1 NOT NULL,
  `paterno` varchar(80) CHARACTER SET latin1 NOT NULL,
  `materno` varchar(80) CHARACTER SET latin1 DEFAULT NULL,
  `nombre` varchar(80) CHARACTER SET latin1 NOT NULL,
  `parentesco` varchar(80) CHARACTER SET latin1 NOT NULL,
  `correo` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_padre`)
) ENGINE=InnoDB  DEFAULT CHARSET=swe7 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `padres`
--

INSERT INTO `padres` (`id_padre`, `clave`, `matricula_hijo`, `paterno`, `materno`, `nombre`, `parentesco`, `correo`) VALUES
(1, '9785463215', '8965138249', 'Peréz', 'Murrieta', 'Luis', 'Padre', 'jmp_1975@gmail.com'),
(2, '1937824699', '9878541395', 'Figueroa', 'Ramirez', 'Paulina', 'Madre', 'pau178@hotmail.com'),
(3, '0242051', '3050515046', 'Lopez', 'Perez', 'Jorge', 'Papa', 'jfgfjk'),
(4, '0200757755', '0242051320', 'Sandoval', 'Martinez', 'Martin', 'Padre', 'martin@gmail.com'),
(5, '54321', '0242051320', 'Lima', 'Contreras', 'Andrea', 'Madre', 'andrea@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE IF NOT EXISTS `profesores` (
  `id_profesor` int(11) NOT NULL AUTO_INCREMENT,
  `clave` varchar(50) CHARACTER SET latin1 NOT NULL,
  `paterno` varchar(80) CHARACTER SET latin1 NOT NULL,
  `materno` varchar(80) CHARACTER SET latin1 DEFAULT NULL,
  `nombre` varchar(80) CHARACTER SET latin1 NOT NULL,
  `grado` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_profesor`)
) ENGINE=InnoDB  DEFAULT CHARSET=swe7 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`id_profesor`, `clave`, `paterno`, `materno`, `nombre`, `grado`) VALUES
(2, '012345', 'Sulaiman', 'Estrada', 'Hernan', 'Primero');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
