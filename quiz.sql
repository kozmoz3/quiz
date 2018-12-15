/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : quiz

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-12-07 11:13:22
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `student` (
  `idstudent` int(11) NOT NULL,
  `student` int(11) NOT NULL,
  `teacher` int(11) NOT NULL,
  `nullable` tinyint(1) NOT NULL
)
-- ----------------------------
-- Table structure for estudiantequiz
-- ----------------------------
DROP TABLE IF EXISTS `estudiantequiz`;
CREATE TABLE `estudiantequiz` (
  `idestudiantequiz` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idestudiantequiz`),
  KEY `fk_estu_quizu` (`iduser`),
  KEY `fk_estu_quizq` (`idquiz`),
  CONSTRAINT `fk_estu_quizq` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`),
  CONSTRAINT `fk_estu_quizu` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- ----------------------------
-- Records of estudiantequiz
-- ----------------------------

-- ----------------------------
-- Table structure for grupo
-- ----------------------------
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `idgrupo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `iduser` int(11)
  PRIMARY KEY (`idgrupo`),
   KEY `fk_grup_user` (`iduser`),
    CONSTRAINT `fk_grup_user` FOREIGN KEY (`iduserfk`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grupo
-- ----------------------------

-- ----------------------------
-- Table structure for grupouser
-- ----------------------------
DROP TABLE IF EXISTS `grupouser`;
CREATE TABLE `grupouser` (
  `idrelaciongu` int(11) NOT NULL AUTO_INCREMENT,
  `iduserfk` int(11) NOT NULL,
  `idgrupo` int(11) NOT NULL,
  PRIMARY KEY (`idrelaciongu`),
  KEY `fk_grup_user` (`iduserfk`),
  KEY `fk_grup_grup` (`idgrupo`),
  CONSTRAINT `fk_grup_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_grup_user` FOREIGN KEY (`iduserfk`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grupouser
-- ----------------------------

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `idquestion` int(11) NOT NULL AUTO_INCREMENT,
  `answers` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `options` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `question` longtext CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `score` smallint(6) NOT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idquestion`),
  KEY `fk_ques_quiz` (`idquiz`),
  CONSTRAINT `fk_ques_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of questions
-- ----------------------------

-- ----------------------------
-- Table structure for quiz
-- ----------------------------
DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz` (
  `idquiz` int(11) NOT NULL AUTO_INCREMENT,
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
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`idquiz`),
  KEY `idusuario` (`iduser`),
  CONSTRAINT `fk_user_quiz` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quiz
-- ----------------------------

-- ----------------------------
-- Table structure for quizgrupo
-- ----------------------------
DROP TABLE IF EXISTS `quizgrupo`;
CREATE TABLE `quizgrupo` (
  `idrelacionsg` int(11) NOT NULL AUTO_INCREMENT,
  `idgrupo` int(11) NOT NULL,
  `idquiz` int(11) NOT NULL,
  PRIMARY KEY (`idrelacionsg`),
  KEY `fk_qugr_quiz` (`idquiz`),
  KEY `fk_qugr_grup` (`idgrupo`),
  CONSTRAINT `fk_qugr_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_qugr_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`idquiz`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quizgrupo
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'ROLE_ROOT');
INSERT INTO `roles` VALUES ('2', 'ROLE_ALUM');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(40) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` char(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(80) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `perfil` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `enable` tinyint(1) NOT NULL,
  `idrol` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  KEY `fk_user_rol` (`idrol`),
  CONSTRAINT `fk_user_rol` FOREIGN KEY (`idrol`) REFERENCES `roles` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Alfonso', 'Vásquez Cortes', null, 'alvaco_1993@hotmail.com', '', '$2a$10$KVQEE7VUVu/BH44zTDwO0OrqNmtHBdqwIxEcCbv.TFnjnfpABYF.q', null, '1', '1');
INSERT INTO `user` VALUES ('2', 'Daniel', 'Sanchez', '1234567890', 'dani@gmail.com', '', '$2a$10$KVQEE7VUVu/BH44zTDwO0OrqNmtHBdqwIxEcCbv.TFnjnfpABYF.q', null, '1', '2');
