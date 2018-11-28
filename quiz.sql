/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : quiz

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-28 11:28:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for confgeneral
-- ----------------------------
DROP TABLE IF EXISTS `confgeneral`;
CREATE TABLE `confgeneral` (
  `idconfgen` int(11) NOT NULL AUTO_INCREMENT,
  `mostrar` char(10) COLLATE utf8_spanish_ci NOT NULL,
  `vista` char(2) COLLATE utf8_spanish_ci NOT NULL,
  `random` binary(255) NOT NULL,
  `tiempo` time NOT NULL,
  `venceini` date DEFAULT NULL,
  `vencefin` date DEFAULT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idconfgen`),
  KEY `fk_gene_quiz` (`idquiz`),
  CONSTRAINT `fk_gene_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of confgeneral
-- ----------------------------

-- ----------------------------
-- Table structure for confresultados
-- ----------------------------
DROP TABLE IF EXISTS `confresultados`;
CREATE TABLE `confresultados` (
  `idconfresult` int(11) NOT NULL AUTO_INCREMENT,
  `preguntasc` binary(255) NOT NULL,
  `respuestac` binary(255) NOT NULL,
  `preguntasi` binary(255) NOT NULL,
  `calificacion` binary(255) NOT NULL,
  `grafico` binary(255) NOT NULL,
  `tiempo` binary(255) NOT NULL,
  `mensajesop` binary(255) NOT NULL,
  `intentos` char(10) COLLATE utf8_spanish_ci NOT NULL,
  `showfechaini` date DEFAULT NULL,
  `showfechafin` date DEFAULT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idconfresult`),
  KEY `fk_resu_quiz` (`idquiz`),
  CONSTRAINT `fk_resu_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of confresultados
-- ----------------------------

-- ----------------------------
-- Table structure for confseguridad
-- ----------------------------
DROP TABLE IF EXISTS `confseguridad`;
CREATE TABLE `confseguridad` (
  `idconfseguri` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idconfseguri`),
  KEY `fk_segu_quiz` (`idquiz`),
  CONSTRAINT `fk_segu_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of confseguridad
-- ----------------------------

-- ----------------------------
-- Table structure for grupo
-- ----------------------------
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `idgrupo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idgrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of grupo
-- ----------------------------

-- ----------------------------
-- Table structure for grupousuario
-- ----------------------------
DROP TABLE IF EXISTS `grupousuario`;
CREATE TABLE `grupousuario` (
  `idrelaciongu` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `idgrupo` int(11) NOT NULL,
  PRIMARY KEY (`idrelaciongu`),
  KEY `fk_grup_user` (`idusuario`),
  KEY `fk_grup_grup` (`idgrupo`),
  CONSTRAINT `fk_grup_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_grup_user` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of grupousuario
-- ----------------------------

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `idquestion` int(11) NOT NULL AUTO_INCREMENT,
  `answers` longtext COLLATE utf8_spanish_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `options` longtext COLLATE utf8_spanish_ci NOT NULL,
  `question` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `score` smallint(6) NOT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idquestion`),
  KEY `fk_ques_quiz` (`idquiz`),
  CONSTRAINT `fk_ques_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of questions
-- ----------------------------

-- ----------------------------
-- Table structure for quiz
-- ----------------------------
DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz` (
  `idquiz` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `img` varchar(250) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of quiz
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'ROLE_ROOT');
INSERT INTO `roles` VALUES ('2', 'ROLE_ALUM');

-- ----------------------------
-- Table structure for seguridadgrupo
-- ----------------------------
DROP TABLE IF EXISTS `seguridadgrupo`;
CREATE TABLE `seguridadgrupo` (
  `idrelacionsg` int(11) NOT NULL AUTO_INCREMENT,
  `idgrupo` int(11) NOT NULL,
  `idconfseguri` int(11) NOT NULL,
  PRIMARY KEY (`idrelacionsg`),
  KEY `fk_seg_seg` (`idconfseguri`),
  KEY `fk_seg_grup` (`idgrupo`),
  CONSTRAINT `fk_seg_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_seg_seg` FOREIGN KEY (`idconfseguri`) REFERENCES `confseguridad` (`idconfseguri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of seguridadgrupo
-- ----------------------------

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` char(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `perfil` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idrol` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_user_rol` (`idrol`),
  CONSTRAINT `fk_user_rol` FOREIGN KEY (`idrol`) REFERENCES `roles` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', 'Alfonso', 'Vásquez Cortes', null, 'alvaco_1993@hotmail.com', '$2a$10$KVQEE7VUVu/BH44zTDwO0OrqNmtHBdqwIxEcCbv.TFnjnfpABYF.q', null, '1');
