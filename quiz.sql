/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : quiz

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-12-19 11:04:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grupo
-- ----------------------------
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `idgrupo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`idgrupo`),
  KEY `iduserfk` (`iduser`),
  CONSTRAINT `grupo_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grupo
-- ----------------------------
INSERT INTO `grupo` VALUES ('1', 'grupo de prueba', 'description', '1', '2');
INSERT INTO `grupo` VALUES ('2', 'Un nombre editado', 'Una descripción editada', '1', '1');
INSERT INTO `grupo` VALUES ('4', 'Un grupo con check', 'Una descripcion', '0', '1');
INSERT INTO `grupo` VALUES ('5', 'Un grupo de eliminación editada', 'Una descripción', '1', '1');

-- ----------------------------
-- Table structure for grupouser
-- ----------------------------
DROP TABLE IF EXISTS `grupouser`;
CREATE TABLE `grupouser` (
  `idrelaciongu` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `idgrupo` int(11) NOT NULL,
  `idstudent` int(11) NOT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idrelaciongu`),
  KEY `fk_grup_user` (`iduser`),
  KEY `fk_grup_grup` (`idgrupo`),
  CONSTRAINT `fk_grup_grup` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_grup_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grupouser
-- ----------------------------
INSERT INTO `grupouser` VALUES ('1', '1', '4', '3', '0');
INSERT INTO `grupouser` VALUES ('2', '1', '4', '8', '1');
INSERT INTO `grupouser` VALUES ('3', '1', '5', '8', '1');
INSERT INTO `grupouser` VALUES ('4', '1', '5', '3', '0');

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
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `img` varchar(250) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `showallquestion` tinyint(1) NOT NULL DEFAULT '0',
  `mostrar` int(11) NOT NULL DEFAULT '0',
  `vista` tinyint(1) NOT NULL DEFAULT '0',
  `random` tinyint(1) NOT NULL DEFAULT '0',
  `tiempo` time DEFAULT NULL,
  `venceini` date DEFAULT NULL,
  `vencefin` date DEFAULT NULL,
  `intentos` char(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `preguntasc` tinyint(1) NOT NULL DEFAULT '0',
  `respuestac` tinyint(1) NOT NULL DEFAULT '0',
  `preguntasi` tinyint(1) NOT NULL DEFAULT '0',
  `calificacion` tinyint(1) NOT NULL DEFAULT '0',
  `grafico` tinyint(1) NOT NULL DEFAULT '0',
  `istiempo` tinyint(1) NOT NULL DEFAULT '0',
  `mensajesop` tinyint(1) NOT NULL DEFAULT '0',
  `isintentos` tinyint(1) NOT NULL DEFAULT '0',
  `showfechaini` date DEFAULT NULL,
  `showfechafin` date DEFAULT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL DEFAULT '0',
  `publicar` tinyint(1) NOT NULL DEFAULT '0',
  `fecha` datetime DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`idquiz`),
  KEY `idusuario` (`iduser`),
  CONSTRAINT `fk_user_quiz` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quiz
-- ----------------------------
INSERT INTO `quiz` VALUES ('1', 'prueba1', 'esta es la prueba 1 de quiz', null, '0', '0', '0', '0', '02:11:08', '2018-12-05', '2019-02-28', '1', '0', '0', '0', '1', '0', '0', '0', '0', '2018-12-12', '2018-12-25', 'size', '0', '1', '2018-12-31 00:00:00', '1');
INSERT INTO `quiz` VALUES ('2', 'sss', 'ss', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('3', 'uuu', 'descroibr', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('4', 'nombre', 'descripcion', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('5', 'nobre', 'dec', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('6', 'General1', 'General1', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('7', 'simulador1', 'des', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('8', 'Mostrar todas las preguntas', 'Mostrar todas las preguntas', null, '1', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('9', 'Mostrar sólo', 'Mostrar sólo', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('10', 'Mostrar sólo1', 'Mostrar sólo1', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('11', 'Preguntas random1', 'Preguntas random1', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('12', 'Todas las preguntas en una hoja1', 'Todas las preguntas en una hoja', null, '0', '0', '0', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('13', 'Todas las preguntas en una hoja2', 'Todas las preguntas en una hoja2', null, '0', '0', '1', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('14', 'reguntas random', 'reguntas random1', null, '0', '0', '0', '1', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '1', null, '2');
INSERT INTO `quiz` VALUES ('15', 'pppppp', 'ppp', null, '1', '0', '1', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');
INSERT INTO `quiz` VALUES ('16', 'generar', 'quiz', null, '1', '0', '1', '0', null, null, null, null, '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '0', '0', null, '2');

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
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `idstudent` int(11) NOT NULL AUTO_INCREMENT,
  `student` int(11) NOT NULL,
  `teacher` int(11) NOT NULL,
  `nullable` tinyint(1) NOT NULL,
  PRIMARY KEY (`idstudent`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '3', '1', '1');
INSERT INTO `student` VALUES ('2', '4', '2', '1');
INSERT INTO `student` VALUES ('3', '5', '2', '1');
INSERT INTO `student` VALUES ('4', '6', '2', '1');
INSERT INTO `student` VALUES ('5', '7', '2', '1');
INSERT INTO `student` VALUES ('6', '8', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Alfonso', 'Vásquez Cortes', null, 'alvaco_1993@hotmail.com', '', '$2a$10$KVQEE7VUVu/BH44zTDwO0OrqNmtHBdqwIxEcCbv.TFnjnfpABYF.q', null, '1', '1');
INSERT INTO `user` VALUES ('2', 'Enriques', 'sosa', '5548364795', 'kiqueyo847@gmail.com', 'kique', '$2a$10$Iuav6RB24Qem24bGLvEmpu2aIdXg9GtZRNwyfcTlh8nZysdYIrzy6', null, '1', '1');
INSERT INTO `user` VALUES ('3', 'Enrique2', 'sosa', '5548364795', 'kiqueyo8471@gmail.com', 'user', '$2a$10$GX3lW6vYAyCbqgke72MZXuUZEH7V15IFd5wXHPXZ9yyd1k4STZd4u', null, '1', '2');
INSERT INTO `user` VALUES ('4', 'Tatiana', 'vivar', '', 'tatiana@gmail.com', 'tatis', '$2a$10$TQoLTtUyoC/JMeHNfI8jGeQwPzDoiG6N.DZDaF./Fv/TW/DpE849a', null, '1', '2');
INSERT INTO `user` VALUES ('5', 'Andrea', 'legareta', '5548364795', 'andrea@gmail.com', 'andreas', '$2a$10$Kwgih2ZutDwLTP6Rw3m8fewmU29n6mvusgX.rngp1h.bNpe03TVk.', null, '1', '2');
INSERT INTO `user` VALUES ('6', 'fedrico', 'cccc', '', 'federico@gmail.com', 'fede', '$2a$10$WtZ/o8gDGt3TghqBoemRV.XUuRGxGI55/YPm.QO6cDuG7mH4iykn6', null, '1', '2');
INSERT INTO `user` VALUES ('7', 'dd22', 'dd22', '2222222', 'dd@gmail.com', 'username2', '$2a$10$kygtYPpFY2gzIHQefP9uHeUpxT8HL3w59F6IgbDm5lCEA4sA3lqPe', null, '1', '2');
INSERT INTO `user` VALUES ('8', 'Alfonso', 'Vásquez Cortes', '5550824884', 'alvaco12@gmail.com', 'AlfonsoVACOUSR', '$2a$10$g3clgWnHv4/bcjqOARyTv.Sy9B51CNMo8HUPs.6nb19xBOA5OMaR6', null, '1', '2');
