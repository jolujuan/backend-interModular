
-- -----------------------------------------------------
-- Table `usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE IF NOT EXISTS `usuarios` (
    id BigInt NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255) NOT NULL,
    `nickname` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT usuario_uk_nickname UNIQUE KEY (`nickname`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles`;

CREATE TABLE IF NOT EXISTS `roles` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

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
-- FIN TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD ====LOGIN/REGISTRO
-- -----------------------------------------------------

-- -----------------------------------
-- CASILLA TIPO
-- -----------------------------------
DROP TABLE IF EXISTS casillaTipo;

CREATE TABLE IF NOT EXISTS casillaTipo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipocasilla ENUM('SALIDA', 'BONIFICACION', 'PENALIZACION', 'RETROCESO', 'LLEGADA', 'NORMAL') UNIQUE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- CASILLA
-- -----------------------------------
DROP TABLE IF EXISTS casilla;

CREATE TABLE IF NOT EXISTS casilla (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT,
    tipoCasilla ENUM('SALIDA', 'BONIFICACION', 'PENALIZACION', 'RETROCESO', 'LLEGADA', 'NORMAL'),
    CONSTRAINT fk_tipoCasilla FOREIGN KEY (tipoCasilla) REFERENCES casillaTipo(tipocasilla)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;



-- -----------------------------------
-- Dado Movimiento // no se sabe si se va a usar
-- -----------------------------------
DROP TABLE IF EXISTS dadoMovimiento;

CREATE TABLE IF NOT EXISTS dadoMovimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor INT
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Jugador
-- -----------------------------------
DROP TABLE IF EXISTS jugador;

CREATE TABLE IF NOT EXISTS jugador (
    idUsuario BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    posicion INT, -- no se usa de momento
    turnoPerdido BOOLEAN,-- de momento no se si hara
    preguntasFalladas INT,-- de momento no usar
    CONSTRAINT `usuarioJugador` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios`(`id`),
    CONSTRAINT `fk_jugador_usuario_nickname` FOREIGN KEY (`nombre`) REFERENCES `usuarios`(`nickname`)
     
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------
-- Tablero
-- -----------------------------------
DROP TABLE IF EXISTS tablero;

CREATE TABLE IF NOT EXISTS tablero (
    idTablero BIGINT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('EN_CURSO', 'FINALIZADO', 'PAUSADA') ,
    preguntashechas VARCHAR(255),
    jugador1 BigInt,
    jugador2 BigInt,
    jugador3 BigInt,
    jugador4 BigInt,
    casillaJugador1 BigInt,
    casillaJugador2 BigInt,
    casillaJugador3 BigInt,
    casillaJugador4 BigInt,
    turnoJugador BigInt,
    
    ganador BigInt DEFAULT 0,-- guardara el id del que gane
    FOREIGN KEY (`jugador1`) REFERENCES `jugador`(`idUsuario`),
    FOREIGN KEY (`jugador2`) REFERENCES `jugador`(`idUsuario`),
    FOREIGN KEY (`jugador3`) REFERENCES `jugador`(`idUsuario`),
    FOREIGN KEY (`jugador4`) REFERENCES `jugador`(`idUsuario`),
    FOREIGN KEY (`casillaJugador1`) REFERENCES `casilla`(`id`),
    FOREIGN KEY (`casillaJugador2`) REFERENCES `casilla`(`id`),
    FOREIGN KEY (`casillaJugador3`) REFERENCES `casilla`(`id`),
    FOREIGN KEY (`casillaJugador4`) REFERENCES `casilla`(`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- -----------------------------------aa
-- Estado Tablero
-- -----------------------------------
-- DROP TABLE IF EXISTS estadoTablero;

-- CREATE TABLE IF NOT EXISTS estadoTablero (
   -- idTablero BigInt ,
  --  estado ENUM('EN_CURSO', 'FINALIZADO', 'PAUSADA') UNIQUE,
  --  CONSTRAINT `tableroiEstado`
  --  FOREIGN KEY (`idTablero` )
  --  REFERENCES `tablero` (`idTablero` )
-- ) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;







-- -----------------------------------------------------
-- Estadisticas Usuario
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuarios_estadisticas`;

CREATE TABLE IF NOT EXISTS `usuarios_estadisticas`(
    `idUsuario` BIGINT NOT NULL,
    `PartidasTotales` BIGINT DEFAULT 0,
    `PartidasPerdidas` BIGINT DEFAULT 0,
    `PreguntasTotales` BIGINT DEFAULT 0,
    `PreguntasAcertadas` BIGINT DEFAULT 0,
    `PreguntasFalladas` BIGINT DEFAULT 0,
    PRIMARY KEY(`idUsuario`),
    FOREIGN KEY (`idUsuario`) REFERENCES `usuarios`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------
-- RELACIONADO CON EL JUEGO
-- -----------------------------------
DROP TABLE IF EXISTS `questions_answers`;

CREATE TABLE IF NOT EXISTS `questions_answers` (
   `idPregunta` BIGINT NOT NULL AUTO_INCREMENT,
    `resultsType` VARCHAR(8) CHARACTER SET utf8,
    `resultsDifficulty` VARCHAR(4) CHARACTER SET utf8,
    `resultsCategory` VARCHAR(100) CHARACTER SET utf8,
    `resultsQuestion` VARCHAR(150) CHARACTER SET utf8,
    `resultsCorrectAnswer` VARCHAR(70) CHARACTER SET utf8,
    `resultsIncorrectAnswers` VARCHAR(100) CHARACTER SET utf8,
    PRIMARY KEY (`idPregunta`)

	
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

INSERT INTO `questions_answers` ( resultsType, resultsDifficulty, resultsCategory, resultsQuestion, resultsCorrectAnswer, resultsIncorrectAnswers) VALUES 
	-- FOR GEOGRAPHY ****/
	('multiple', 'easy', 'geography', '¿Cuál de las siguientes islas japonesas es la más grande?', 'Honshu', 'Hokkaido, Shikoku, Kyushu'),
    ('multiple', 'easy', 'geography', 'Groenlandia es parte de ¿cuál reino?', 'Dinamarca', 'Suecia, Noruega, Reino Unido'),
    ('multiple', 'easy', 'geography', '¿Con cuántos países limita México?', '3', '2, 4, 1'),
    ('multiple', 'easy', 'geography', '¿Qué estado es el estado más grande de los Estados Unidos de América?', 'Alaska', 'California, Texas, Washington'),
    ('multiple', 'easy', 'geography', '¿Qué estado ruso forma una frontera con Polonia?', 'Kaliningrado', 'Samara, Nizhni Nóvgorod, Omsk'),
    ('multiple', 'easy', 'geography', '¿Cuál de los siguientes antiguos estados yugoslavos no tiene salida al mar?', 'Serbia', 'Bosnia y Herzegovina, Montenegro, Croacia'),
    ('multiple', 'easy', 'geography', '¿Cuál es la capital del estado de Nueva York en EE. UU.?', 'Albany', 'Búfalo, Nueva York, Rochester'),
    ('multiple', 'easy', 'geography', '¿Cuál es la capital de Dinamarca?', 'Copenhague', 'Aarhus, Odense, Aalborg'),
    ('multiple', 'easy', 'geography', 'Todos los siguientes se clasifican como lenguas finoúgricas EXCEPTO:', 'Samoyedo', 'Húngaro, Finés, Estonio'),
    ('multiple', 'easy', 'geography', '¿Cuál es la capital de India?', 'Nueva Delhi', 'Bejing, Montreal, Tithi'),

	-- FOR PEOPLE ****/

    ('multiple', 'easy', 'celebrity', '¿En qué suele centrar sus películas el cineasta Dan Bell?', 'Edificios abandonados y centros comerciales muertos', 'Lugares históricos, Películas de acción, Documentales'),
    ('multiple', 'easy', 'celebrity', 'Gwyneth Paltrow tiene una hija llamada...?', 'Apple', 'Lily, French, Dakota'),
    ('multiple', 'easy', 'celebrity', '¿Por qué nombre es mejor conocido Ramon Estevez?', 'Martin Sheen', 'Charlie Sheen, Ramon Sheen, Emilio Estevez'),
    ('multiple', 'easy', 'celebrity', '¿Qué celebridad anunció su candidatura presidencial en 2015?', 'Kanye West', 'Donald Trump, Leonardo DiCaprio, Miley Cyrus'),
    ('multiple', 'easy', 'celebrity', '¿Cuál fue la causa del suicidio de Marilyn Monroe?', 'Sobredosis de drogas', 'Ataque con cuchillo, Incendio en casa, Disparo'),
    ('multiple', 'easy', 'celebrity', 'Neil Hamburger es interpretado por ¿qué comediante?', 'Gregg Turkington', 'Nathan Fielder, Tim Heidecker, Todd Glass'),
    ('multiple', 'easy', 'celebrity', 'Nombrado después de un personaje que interpretó en una película de 1969, ¿cómo se llama el centro de esquí en Utah que compró Robert Redford en 1968?', 'Sundance', 'Woodward, Turner, Booker'),
    ('multiple', 'easy', 'celebrity', 'Aubrey Graham es mejor conocido como', 'Drake', 'Travis Scott, Lil Wayne, 2 Chainz'),
    ('multiple', 'easy', 'celebrity', '¿Cuál fue el último papel cinematográfico de James Coburn antes de su muerte?', 'American Gun', 'Monsters Inc, Texas Rangers, Snow Dogs'),
    ('multiple', 'easy', 'celebrity', '¿Por qué nombre es mejor conocido Carlos Estevez?', 'Charlie Sheen', 'Ricky Martin, Bruno Mars, Joaquin Phoenix'),

	-- FOR ANIMALS ****/

    ('multiple', 'easy', 'animals', '¿Cómo se llama a un bebé murciélago?', 'Cachorro', 'Cub, Polluelo, Cría'),
    ('multiple', 'easy', 'animals', '¿De qué color es la hembra del mirlo?', 'Marrón', 'Negro, Blanco, Amarillo'),
    ('multiple', 'easy', 'animals', '¿Cuál es el nombre colectivo para un grupo de cuervos?', 'Asesinato', 'Manada, Bandada, Rebaño'),
    ('multiple', 'easy', 'animals', '¿Cuántos dientes tiene un conejo adulto?', '28', '30, 26, 24'),
    ('multiple', 'easy', 'animals', '¿Cuál es el animal terrestre más rápido?', 'Guepardo', 'León, Gacela Thomson, Antílope Pronghorn'),
    ('multiple', 'easy', 'animals', '¿A qué clase de animales pertenecen las salamandras?', 'Anfibio', 'Pez, Reptiles, Mamíferos'),
    ('multiple', 'easy', 'animals', '¿Cuál es el verdadero nombre de Grumpy Cat?', 'Salsa Tártara', 'Salsa, Minnie, Brócoli'),
    ('multiple', 'easy', 'animals', 'Por definición, ¿dónde vive un animal abisopelágico?', 'En el fondo del océano', 'En el desierto, En la cima de una montaña, Dentro de un árbol'),
    ('multiple', 'easy', 'animals', '¿Cómo se llama la morada de un conejo?', 'Madriguera', 'Nido, Cueva, Drey'),
    ('multiple', 'easy', 'animals', '¿Cuál es el nombre científico de los humanos modernos?', 'Homo Sapiens', 'Homo Ergaster, Homo Erectus, Homo Neanderthalensis'),

	-- FOR HISTORY ****/

('multiple', 'easy', 'history', '¿Quién fue el primer emperador romano?', 'Augusto', 'Julio César, Marco Antonio, Nerón'),
       ('multiple', 'easy', 'history', '¿Cuál fue el primer imperio en la historia de la humanidad?', 'Imperio Acadio', 'Imperio Persa, Imperio Romano, Imperio Mongol'),
       ('multiple', 'easy', 'history', '¿Quién fue el líder de la Revolución Rusa en 1917?', 'Vladimir Lenin', 'Joseph Stalin, Leon Trotsky, Mikhail Gorbachev'),
       ('multiple', 'easy', 'history', '¿Cuál fue la primera guerra mundial conocida en la historia?', 'Guerra del Peloponeso', 'Guerra de Troya, Guerra de los Cien Años, Guerra de los Treinta Años'),
       ('multiple', 'easy', 'history', '¿Cuál era la capital del Imperio Inca?', 'Cuzco', 'Machu Picchu, Quito, Lima'),
       ('multiple', 'easy', 'history', '¿Qué evento marcó el inicio de la Revolución Francesa?', 'La Toma de la Bastilla', 'La Revuelta de los Campesinos, La Marcha de las Mujeres a Versalles, La Decapitación de Luis XVI'),
       ('multiple', 'easy', 'history', '¿Quién fue el líder de la Revolución Cubana?', 'Fidel Castro', 'Che Guevara, Raúl Castro, Fulgencio Batista'),
       ('multiple', 'easy', 'history', '¿Cuál fue la primera civilización en la historia de la humanidad?', 'Civilización Sumeria', 'Civilización Egipcia, Civilización China, Civilización Harappa'),
       ('multiple', 'easy', 'history', '¿Quién fue el primer presidente de los Estados Unidos?', 'George Washington', 'Thomas Jefferson, Abraham Lincoln, John Adams'),
       ('multiple', 'easy', 'history', '¿En qué año terminó la Segunda Guerra Mundial?', '1945', '1943, 1944, 1946');

    /* ('boolean', 'easy', 'history', 'Adolf Hitler fue un soldado alemán en la Primera Guerra Mundial.', 'True', 'False'),
    ('boolean', 'easy', 'history', 'Estados Unidos de América fue el primer país en lanzar un hombre al espacio.', 'False', 'True'),
    ('boolean', 'easy', 'history', 'La Guerra Fría terminó con la muerte de Joseph Stalin.', 'False', 'True'),
    ('boolean', 'easy', 'history', 'Estados Unidos fue miembro de la Liga de Naciones.', 'False', 'True'),
    ('boolean', 'easy', 'history', 'El Spitfire se originó de un avión de carreras.', 'True', 'False'),
    ('boolean', 'easy', 'history', 'Las protestas de la Plaza de Tiananmen de 1989 se llevaron a cabo en Hong Kong.', 'False', 'True'),
    ('boolean', 'easy', 'history', '¿Kublai Khan es el nieto de Genghis Khan?', 'True', 'False'),
    ('boolean', 'easy', 'history', 'El Departamento de Seguridad Nacional de Estados Unidos se formó en respuesta a los ataques del 11 de septiembre.', 'True', 'False'),
    ('boolean', 'easy', 'history', 'Adolf Hitler fue juzgado en los juicios de Núremberg.', 'False', 'True'),
    ('boolean', 'easy', 'history', 'El presidente de Estados Unidos, John F. Kennedy, fue asesinado durante su caravana presidencial en Atlanta, Georgia, el 22 de noviembre de 1963.', 'False', 'True'); */

-- ------------------------
--
-- ------------------------
INSERT INTO casillaTipo (tipocasilla) VALUES 
    ('SALIDA'),
    ('BONIFICACION'),
    ('PENALIZACION'),
    ('RETROCESO'),
    ('LLEGADA'),
    ('NORMAL');




-- ------------------------
-- inserts de casillas //'SALIDA', 'BONIFICACION', 'PENALIZACION', 'RETROCESO', 'LLEGADA', 'NORMAL'
-- ------------------------

INSERT INTO casilla (numero, tipoCasilla) VALUES 
    (1, 'SALIDA'),
    (2, 'NORMAL'),
    (3, 'BONIFICACION'),
    (4, 'NORMAL'),
    (5, 'NORMAL'),
    (6, 'BONIFICACION'),
    (7, 'RETROCESO'),
    (8, 'NORMAL'),
    (9, 'BONIFICACION'),
    (10, 'NORMAL'),
    (11, 'NORMAL'),
    (12, 'BONIFICACION'),
    (13, 'RETROCESO'),
    (14, 'NORMAL'),
    (15, 'NORMAL'),
    (16, 'BONIFICACION'),
    (17, 'LLEGADA');
