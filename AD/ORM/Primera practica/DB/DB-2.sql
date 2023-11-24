-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-11-2023 a las 19:55:35
-- Versión del servidor: 8.0.31
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ies`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `ALM_ID` int NOT NULL,
  `ALM_NAME` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `ALM_SURNAMES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ALM_FECHA` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `ALM_NIA` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`ALM_ID`, `ALM_NAME`, `ALM_SURNAMES`, `ALM_FECHA`, `ALM_NIA`) VALUES
(12, 'Andreu', 'Sanz Sanz', '12/12/2004', 10813358),
(13, 'Elena', 'García López', '05/03/2003', 20548762),
(14, 'David', 'Martínez Rodríguez', '21/09/2005', 30169584),
(15, 'Laura', 'Fernández Sánchez', '14/07/2004', 40871293),
(16, 'Miguel', 'López Martínez', '02/11/2003', 50783176),
(17, 'Ana', 'González Ruiz', '19/04/2005', 60924857),
(18, 'Javier', 'Pérez Gómez', '30/08/2004', 70836491),
(19, 'Carmen', 'Jiménez González', '10/01/2003', 80547205),
(20, 'Raúl', 'Serrano Pérez', '27/06/2005', 90758314),
(21, 'Isabel', 'Díaz Martín', '08/09/2004', 10069578);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `MAT_ID` int NOT NULL,
  `MAT_ALM_ID` int NOT NULL,
  `MAT_MOD_ID` int NOT NULL,
  `MAT_NOTAS` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`MAT_ID`, `MAT_ALM_ID`, `MAT_MOD_ID`, `MAT_NOTAS`) VALUES
(13, 12, 13, '8.5#7.2#9.0'),
(14, 12, 15, NULL),
(15, 13, 14, NULL),
(16, 13, 17, '7.0#7.8#8.2'),
(17, 14, 16, '8.5#8.0#8.7'),
(18, 14, 18, NULL),
(19, 15, 19, '8.2#8.5#7.8'),
(20, 15, 21, NULL),
(21, 16, 13, '7.5#7.0#7.8'),
(22, 16, 16, '8.8#8.5#9.2'),
(23, 17, 15, '6.5#7.2#6.8'),
(24, 17, 20, '8.0#8.5#7.9'),
(25, 18, 14, '7.9#8.2#8.5'),
(26, 18, 22, NULL),
(27, 19, 18, '7.0#7.5#7.8'),
(28, 19, 19, '8.2#8.5#8.0'),
(29, 20, 16, '8.9#8.5#9.0'),
(30, 20, 21, '7.5#8.0#7.8'),
(31, 21, 14, '8.0#8.5#8.2'),
(32, 21, 17, '9.2#9.0#8.8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulos`
--

CREATE TABLE `modulos` (
  `MOD_ID` int NOT NULL,
  `MOD_NAME` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modulos`
--

INSERT INTO `modulos` (`MOD_ID`, `MOD_NAME`) VALUES
(13, 'Matemáticas'),
(14, 'Programación en C'),
(15, 'Bases de Datos'),
(16, 'Diseño de Interfaces'),
(17, 'Redes de Computadoras'),
(18, 'Sistemas Operativos'),
(19, 'Desarrollo Web'),
(20, 'Inteligencia Artificial'),
(21, 'Seguridad Informática'),
(22, 'Proyecto Final de Ciclo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`ALM_ID`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`MAT_ID`),
  ADD KEY `MAT_ALM_ID` (`MAT_ALM_ID`),
  ADD KEY `MAT_MOD_ID` (`MAT_MOD_ID`);

--
-- Indices de la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD PRIMARY KEY (`MOD_ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `ALM_ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `MAT_ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `modulos`
--
ALTER TABLE `modulos`
  MODIFY `MOD_ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `matriculas_ibfk_1` FOREIGN KEY (`MAT_ALM_ID`) REFERENCES `alumnos` (`ALM_ID`),
  ADD CONSTRAINT `matriculas_ibfk_2` FOREIGN KEY (`MAT_MOD_ID`) REFERENCES `modulos` (`MOD_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
