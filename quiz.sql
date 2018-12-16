-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-12-2018 a las 03:01:26
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `quiz`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `idgrupo` int(11) NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupo`
--

INSERT INTO `grupo` (`idgrupo`, `nombre`, `descripcion`, `status`, `iduser`) VALUES
(1, 'grupo de prueba', 'description', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupouser`
--

CREATE TABLE `grupouser` (
  `idrelaciongu` int(11) NOT NULL,
  `iduserfk` int(11) NOT NULL,
  `idgrupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `questions`
--

CREATE TABLE `questions` (
  `idquestion` int(11) NOT NULL,
  `answers` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `options` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `question` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `score` smallint(6) NOT NULL,
  `idquiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quiz`
--

CREATE TABLE `quiz` (
  `idquiz` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `img` varchar(250) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `mostrar` char(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `vista` char(2) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `random` tinyint(1) NOT NULL,
  `tiempo` time NOT NULL,
  `venceini` date DEFAULT NULL,
  `vencefin` date DEFAULT NULL,
  `intentos` char(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `preguntasc` tinyint(1) NOT NULL,
  `respuestac` tinyint(1) NOT NULL,
  `preguntasi` tinyint(1) NOT NULL,
  `calificacion` tinyint(1) NOT NULL,
  `grafico` tinyint(1) NOT NULL,
  `istiempo` tinyint(1) NOT NULL,
  `mensajesop` tinyint(1) NOT NULL,
  `isintentos` tinyint(1) NOT NULL,
  `showfechaini` date DEFAULT NULL,
  `showfechafin` date DEFAULT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  `tipovista` char(15) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha` datetime NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `quiz`
--

INSERT INTO `quiz` (`idquiz`, `nombre`, `descripcion`, `img`, `mostrar`, `vista`, `random`, `tiempo`, `venceini`, `vencefin`, `intentos`, `preguntasc`, `respuestac`, `preguntasi`, `calificacion`, `grafico`, `istiempo`, `mensajesop`, `isintentos`, `showfechaini`, `showfechafin`, `password`, `estatus`, `tipovista`, `fecha`, `iduser`) VALUES
(1, 'prueba1', 'esta es la prueba 1 de quiz', NULL, 'todas', 'al', 0, '02:11:08', '2018-12-05', '2019-02-28', '1', 0, 0, 0, 1, 0, 0, 0, 0, '2018-12-12', '2018-12-25', 'size', 0, '1', '2018-12-31 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quizgrupo`
--

CREATE TABLE `quizgrupo` (
  `idrelacionsg` int(11) NOT NULL,
  `idgrupo` int(11) NOT NULL,
  `idquiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idrol` int(11) NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idrol`, `descripcion`) VALUES
(1, 'ROLE_ROOT'),
(2, 'ROLE_ALUM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student`
--

CREATE TABLE `student` (
  `idstudent` int(11) NOT NULL,
  `student` int(11) NOT NULL,
  `teacher` int(11) NOT NULL,
  `nullable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `student`
--

INSERT INTO `student` (`idstudent`, `student`, `teacher`, `nullable`) VALUES
(1, 3, 1, 1),
(2, 4, 2, 1),
(3, 5, 2, 1),
(4, 6, 2, 1),
(5, 7, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `nombre` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(40) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` char(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(80) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `perfil` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `enable` tinyint(1) NOT NULL,
  `idrol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`iduser`, `nombre`, `apellidos`, `telefono`, `correo`, `username`, `password`, `perfil`, `enable`, `idrol`) VALUES
(1, 'Alfonso', 'Vásquez Cortes', NULL, 'alvaco_1993@hotmail.com', '', '$2a$10$KVQEE7VUVu/BH44zTDwO0OrqNmtHBdqwIxEcCbv.TFnjnfpABYF.q', NULL, 1, 1),
(2, 'Enriques', 'sosa', '5548364795', 'kiqueyo847@gmail.com', 'kique', '$2a$10$Iuav6RB24Qem24bGLvEmpu2aIdXg9GtZRNwyfcTlh8nZysdYIrzy6', NULL, 1, 1),
(3, 'Enrique2', 'sosa', '5548364795', 'kiqueyo8471@gmail.com', 'user', '$2a$10$GX3lW6vYAyCbqgke72MZXuUZEH7V15IFd5wXHPXZ9yyd1k4STZd4u', NULL, 1, 2),
(4, 'Tatiana', 'vivar', '', 'tatiana@gmail.com', 'tatis', '$2a$10$TQoLTtUyoC/JMeHNfI8jGeQwPzDoiG6N.DZDaF./Fv/TW/DpE849a', NULL, 1, 2),
(5, 'Andrea', 'legareta', '5548364795', 'andrea@gmail.com', 'andreas', '$2a$10$Kwgih2ZutDwLTP6Rw3m8fewmU29n6mvusgX.rngp1h.bNpe03TVk.', NULL, 1, 2),
(6, 'fedrico', 'cccc', '', 'federico@gmail.com', 'fede', '$2a$10$WtZ/o8gDGt3TghqBoemRV.XUuRGxGI55/YPm.QO6cDuG7mH4iykn6', NULL, 1, 2),
(7, 'dd22', 'dd22', '2222222', 'dd@gmail.com', 'username2', '$2a$10$kygtYPpFY2gzIHQefP9uHeUpxT8HL3w59F6IgbDm5lCEA4sA3lqPe', NULL, 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`idgrupo`),
  ADD KEY `iduserfk` (`iduser`);

--
-- Indices de la tabla `grupouser`
--
ALTER TABLE `grupouser`
  ADD PRIMARY KEY (`idrelaciongu`),
  ADD KEY `fk_grup_user` (`iduserfk`),
  ADD KEY `fk_grup_grup` (`idgrupo`);

--
-- Indices de la tabla `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`idquestion`),
  ADD KEY `fk_ques_quiz` (`idquiz`);

--
-- Indices de la tabla `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`idquiz`),
  ADD KEY `idusuario` (`iduser`);

--
-- Indices de la tabla `quizgrupo`
--
ALTER TABLE `quizgrupo`
  ADD PRIMARY KEY (`idrelacionsg`),
  ADD KEY `fk_qugr_quiz` (`idquiz`),
  ADD KEY `fk_qugr_grup` (`idgrupo`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`idstudent`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `fk_user_rol` (`idrol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `grupo`
--
ALTER TABLE `grupo`
  MODIFY `idgrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `grupouser`
--
ALTER TABLE `grupouser`
  MODIFY `idrelaciongu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `questions`
--
ALTER TABLE `questions`
  MODIFY `idquestion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `quiz`
--
ALTER TABLE `quiz`
  MODIFY `idquiz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `quizgrupo`
--
ALTER TABLE `quizgrupo`
  MODIFY `idrelacionsg` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `student`
--
ALTER TABLE `student`
  MODIFY `idstudent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD CONSTRAINT `grupo_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Filtros para la tabla `grupouser`
--
ALTER TABLE `grupouser`
  ADD CONSTRAINT `fk_grup_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  ADD CONSTRAINT `fk_grup_user` FOREIGN KEY (`iduserfk`) REFERENCES `user` (`iduser`);

--
-- Filtros para la tabla `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `fk_ques_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`);

--
-- Filtros para la tabla `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `fk_user_quiz` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Filtros para la tabla `quizgrupo`
--
ALTER TABLE `quizgrupo`
  ADD CONSTRAINT `fk_qugr_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  ADD CONSTRAINT `fk_qugr_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_rol` FOREIGN KEY (`idrol`) REFERENCES `roles` (`idrol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
