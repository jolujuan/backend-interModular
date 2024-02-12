
-- -----------------------------------------------------
-- Table `usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuarios` ;

CREATE  TABLE IF NOT EXISTS `usuarios` (
  id BigInt  NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL ,
  `nickname` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `imagen` LONGBLOB,
  PRIMARY KEY (`id`),
  CONSTRAINT usuario_uk_nickname UNIQUE KEY (`nickname`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `roles` ;

CREATE  TABLE IF NOT EXISTS `roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `usuarios_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuarios_roles` ;

CREATE  TABLE IF NOT EXISTS `usuarios_roles` (
  idUsuario BigInt  NOT NULL,  
  idRol INT(11)  NOT NULL,
  PRIMARY KEY (`idUsuario`,`idRol`),
  CONSTRAINT `usuarios_roles_fk_usuarios`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `usuarios` (`id` ),
  CONSTRAINT `usuarios_roles_fk_roles`
    FOREIGN KEY (`idRol` )
    REFERENCES `roles` (`id` ) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- -----------------------------------------------------
-- FIN TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD ==== LOGIN/REGISTRO
-- -----------------------------------------------------

-- -----------------------------------
-- CASILLA TIPO
-- -----------------------------------
DROP TABLE IF EXISTS `casillaTipo`;

CREATE TABLE IF NOT EXISTS casillaTipo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name ENUM('SALIDA', 'BONIFICACION', 'PENALIZACION', 'RETROCESO', 'LLEGADA', 'NORMAL')
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- CASILLA
-- -----------------------------------
DROP TABLE IF EXISTS `casilla`;

CREATE TABLE IF NOT EXISTS casilla (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT,
    tipoCasilla VARCHAR(20),  
    tableroId BIGINT,
    FOREIGN KEY (tipoCasilla) REFERENCES casillaTipo (name),  
    FOREIGN KEY (tableroId) REFERENCES tablero (id)  
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Dado Movimiento
-- -----------------------------------
DROP TABLE IF EXISTS `dadoMovimiento`;

CREATE TABLE IF NOT EXISTS dadoMovimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor INT
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Jugador
-- -----------------------------------
DROP TABLE IF EXISTS `jugador`;

CREATE TABLE IF NOT EXISTS jugador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    posicion INT,
    turnoPerdido BOOLEAN,
    preguntasFalladas INT
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Tablero
-- -----------------------------------
DROP TABLE IF EXISTS `tablero`;

CREATE TABLE IF NOT EXISTS tablero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(15),
    preguntas VARCHAR(255),
    FOREIGN KEY (estado) REFERENCES estadoTablero(name)
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Estado Tablero
-- -----------------------------------
DROP TABLE IF EXISTS `estadoTablero`;

CREATE TABLE IF NOT EXISTS estadoTablero (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name ENUM('EN_CURSO', 'FINALIZADO', 'PAUSADA')
); ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;