
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
-- FIN TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD ====LOGIN/REGISTRO
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Estadisticas Usuario
-- -----------------------------------------------------

DROP TABLE IF EXISTS `usuarios_estadisticas` ;

CREATE  TABLE IF NOT EXISTS `usuarios_estadisticas`(
idUsuario BigInt not null,
PRIMARY KEY(`idUsuario`),
PartidasTotales BigInt DEFAULT 0,
PartidasPerdidas BigInt DEFAULT 0,
PreguntasTotales BigInt DEFAULT 0,
PreguntasAcertadas BigInt DEFAULT 0,
PreguntasFalladas BigInt DEFAULT 0,
FOREIGN KEY (`idUsuario`)
REFERENCES  `usuarios`(`id`))

ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;







-- -----------------------------------
--RELACIONADO CON EL JUEGO
-- -----------------------------------



-- ---------------------------------
-- TEMATICAS
-- --------------------------------

DROP TABLE IF EXISTS `tematicas` ;

CREATE  TABLE IF NOT EXISTS `tematicas`(
    Id_tematica BigInt PRIMARY KEY AUTO_INCREMENT ,
    tematica VARCHAR(255) NOT NULL
    
    
);
-- ---------------------------------
-- tematicas insert
-- --------------------------------

INSERT INTO `tematicas`(`tematica`) VALUES
('Deportes'),('Animales'),('Arte')
,('CienciaYNaturaleza'),('Famosos'),('Historia'),('Geografia');

-- ----------------------------------
-- PREGUNTAS 
-- -----------------------------------


DROP TABLE IF EXISTS `preguntas` ;

CREATE  TABLE IF NOT EXISTS `preguntas`(
    ID_pregunta BigInt PRIMARY KEY AUTO_INCREMENT,
    Pregunta VARCHAR(255) UNIQUE NOT NULL,
    Categoria VARCHAR(255) NOT NULL,
    RespuestaCorrecta VARCHAR(255) NOT NULL,
    RespuestasIncorrectas VARCHAR(255) NOT NULL,
    Dificultad VARCHAR(255) NOT NULL,
    CONSTRAINT `fk_categoria`
    FOREIGN KEY (`Categoria`) REFERENCES `tematicas`(`tematica`)
);

-- ----------------
-- INSERT DEPORTES
-- ----------------
INSERT INTO `preguntas` (`Categoria`, `Pregunta`, `RespuestaCorrecta`, `RespuestasIncorrectas`, `Dificultad`) VALUES
('Deportes', '¿En qué deporte se utiliza una raqueta para golpear una pelota?', 'Tenis', 'Squash, Bádminton, Pádel', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Canadá?', 'Hockey sobre hielo', 'Fútbol, Baloncesto, Golf', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Liga Premier inglesa?', 'Alan Shearer', 'Wayne Rooney, Thierry Henry, Sergio Agüero', 'fácil'),
('Deportes', '¿En qué país se originó el deporte del cricket?', 'Inglaterra', 'India, Australia, Sudáfrica', 'fácil'),
('Deportes', '¿Cuántos jugadores conforman un equipo de voleibol en la cancha?', '6', '5, 7, 8', 'fácil'),
('Deportes', '¿Quién ganó la medalla de oro en la carrera de 100 metros lisos masculina en los Juegos Olímpicos de 2016?', 'Usain Bolt', 'Justin Gatlin, Andre De Grasse, Yohan Blake', 'fácil'),
('Deportes', '¿En qué ciudad se encuentra el circuito de carreras de Fórmula 1 conocido como "Mónaco"?', 'Monte Carlo', 'París, Barcelona, Milán', 'fácil'),
('Deportes', '¿Quién es conocido como "The Greatest" y fue campeón mundial de boxeo en varias categorías de peso?', 'Muhammad Ali', 'Mike Tyson, Floyd Mayweather, Sugar Ray Leonard', 'fácil'),
('Deportes', '¿Qué país ganó la medalla de oro en fútbol masculino en los Juegos Olímpicos de 2008?', 'Argentina', 'Brasil, España, Italia', 'fácil'),
('Deportes', '¿En qué deporte se utiliza una red y una pelota para golpearla con la mano?', 'Voleibol', 'Tenis, Bádminton, Balonmano', 'fácil'),
('Deportes', '¿Quién es el único jugador de la NBA que ha ganado 6 campeonatos con los Chicago Bulls?', 'Michael Jordan', 'LeBron James, Kobe Bryant, Shaquille O Neal', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Estados Unidos?', 'Béisbol', 'Fútbol americano, Baloncesto, Hockey sobre hielo', 'fácil'),
('Deportes', '¿En qué año se celebraron los primeros Juegos Olímpicos de la era moderna?', '1896', '1900, 1912, 1924', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la selección argentina de fútbol?', 'Lionel Messi', 'Gabriel Batistuta, Diego Maradona, Hernán Crespo', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Australia?', 'Rugby', 'Cricket, Fútbol, Tenis', 'fácil'),
('Deportes', '¿Qué equipo ganó la Copa Mundial de Rugby en 2019?', 'Sudáfrica', 'Nueva Zelanda, Inglaterra, Australia', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Nueva Zelanda?', 'Rugby', 'Cricket, Fútbol, Baloncesto', 'fácil'),
('Deportes', '¿Qué equipo de fútbol ganó la Copa del Mundo de la FIFA en 2010?', 'España', 'Países Bajos, Alemania, Argentina', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un octógono como área de combate?', 'Artes marciales mixtas (MMA)', 'Boxeo, Taekwondo, Judo', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Irlanda?', 'Hurling', 'Fútbol gaélico, Rugby, Cricket', 'fácil'),
('Deportes', '¿Quién es conocido como "The Black Mamba" y fue una leyenda de la NBA?', 'Kobe Bryant', 'LeBron James, Michael Jordan, Magic Johnson', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Sudáfrica?', 'Rugby', 'Fútbol, Cricket, Tenis', 'fácil'),
('Deportes', '¿En qué deporte se utiliza una paleta para golpear una pelota?', 'Pádel', 'Tenis, Squash, Bádminton', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Argentina?', 'Pato', 'Fútbol, Rugby, Hockey sobre césped', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Liga de Campeones de la CONCACAF?', 'Carlos Ruiz', 'Javier Hernández, Clint Dempsey, Landon Donovan', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un arco y flechas?', 'Tiro con arco', 'Caza, Esgrima, Béisbol', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en México?', 'Fútbol', 'Béisbol, Boxeo, Baloncesto', 'fácil'),
('Deportes', '¿Qué equipo ganó la medalla de oro en baloncesto masculino en los Juegos Olímpicos de 2004?', 'Argentina', 'Estados Unidos, España, Italia', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Argentina?', 'Fútbol', 'Rugby, Hockey sobre césped, Tenis', 'fácil'),
('Deportes', '¿En qué deporte se utiliza una tabla y una vela para deslizarse sobre el agua?', 'Windsurf', 'Surf, Kitesurf, Esquí acuático', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Bundesliga alemana?', 'Gerd Müller', 'Robert Lewandowski, Klaus Fischer, Jupp Heynckes', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un bate para golpear una pelota?', 'Béisbol', 'Críquet, Softbol, Hockey sobre césped', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Serie A italiana?', 'Silvio Piola', 'Cristiano Ronaldo, Francesco Totti, Gunnar Nordahl', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Brasil?', 'Fútbol', 'Voleibol, Surf, Capoeira', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un guante para atrapar una pelota?', 'Béisbol', 'Críquet, Softbol, Rugby', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Premier League inglesa?', 'Alan Shearer', 'Wayne Rooney, Thierry Henry, Andy Cole', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Colombia?', 'Fútbol', 'Ciclismo, Boxeo, Béisbol', 'fácil'),
('Deportes', '¿En qué deporte se utiliza una raqueta y una pelota con plumas?', 'Bádminton', 'Tenis, Squash, Pádel', 'fácil'),
('Deportes', '¿Quién ganó el Balón de Oro en 2021?', 'Lionel Messi', 'Robert Lewandowski, Cristiano Ronaldo, Neymar', 'fácil'),
('Deportes', '¿En qué deporte se compite por medallas de oro, plata y bronce en los Juegos Olímpicos?', 'Atletismo', 'Natación, Gimnasia, Ciclismo', 'fácil'),
('Deportes', '¿Cuál es el deporte más popular en Alemania?', 'Fútbol', 'Baloncesto, Tenis, Voleibol', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un casco y hombreras para protegerse?', 'Fútbol americano', 'Rugby, Hockey sobre hielo, Boxeo', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la selección española de fútbol?', 'David Villa', 'Raúl, Fernando Torres, David Silva', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de España?', 'Fútbol', 'Baloncesto, Tenis, Balonmano', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un palo para golpear una pelota hacia un agujero?', 'Golf', 'Hockey sobre césped, Polo, Croquet', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la Copa del Mundo de la FIFA?', 'Miroslav Klose', 'Ronaldo, Pelé, Gerd Müller', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Francia?', 'Fútbol', 'Rugby, Baloncesto, Tenis', 'fácil'),
('Deportes', '¿En qué deporte se utiliza un palo largo y una bola para golpearla?', 'Golf', 'Hockey sobre césped, Croquet, Polo', 'fácil'),
('Deportes', '¿Quién es el máximo goleador en la historia de la selección alemana de fútbol?', 'Miroslav Klose', 'Gerd Müller, Thomas Müller, Lukas Podolski', 'fácil'),
('Deportes', '¿Cuál es el deporte nacional de Portugal?', 'Fútbol', 'Futsal, Surf, Atletismo', 'fácil');
ON DUPLICATE KEY UPDATE `Pregunta` = `Pregunta`;


-- ----------------------
-- Insert Animales
-------------------------


INSERT INTO `preguntas` (`Categoria`, `Pregunta`, `RespuestaCorrecta`, `RespuestasIncorrectas`, `Dificultad`) VALUES
('Animales', '¿Cuál es el animal más rápido en tierra?', 'Guepardo', 'León, Tigre, Lobo', 'fácil'),
('Animales', '¿Qué animal es conocido por tener una memoria excepcional?', 'Elefante africano', 'Delfín, Chimpancé, Gorila', 'fácil'),
('Animales', '¿Cuál es el animal más peligroso del mundo para los humanos?', 'Mosquito', 'Tiburón blanco, Cocodrilo, León', 'fácil'),
('Animales', '¿Qué animal es conocido por hibernar durante el invierno?', 'Oso pardo', 'Murciélago, Ardilla, Conejo', 'fácil'),
('Animales', '¿Cuál es el animal más resistente a las radiaciones ionizantes?', 'Tardígrado', 'Cucaracha, Escarabajo, Gusano', 'fácil'),
('Animales', '¿Qué animal tiene el cerebro más grande en relación con su tamaño corporal?', 'Cachalote', 'Elefante africano, Delfín, Chimpancé', 'fácil'),
('Animales', '¿Cuál es el animal más grande del océano?', 'Cachalote', 'Tiburón blanco, Ballena azul, Tiburón ballena', 'fácil'),
('Animales', '¿Qué animal es conocido por tener la lengua más larga en relación con su cuerpo?', 'Camaleón', 'Jirafa, Serpiente, Elefante africano', 'fácil'),
('Animales', '¿Cuál es el animal más alto del mundo?', 'Jirafa', 'Elefante africano, Oso polar, Rinoceronte', 'fácil'),
('Animales', '¿Qué animal es conocido por su capacidad para regenerar partes de su cuerpo?', 'Estrella de mar', 'Salamandra, Lagarto, Cangrejo ermitaño', 'fácil'),
('Animales', '¿Cuál es el animal más fuerte del mundo en términos de fuerza relativa?', 'Escarabajo rinoceronte', 'Elefante africano, Gorila, León', 'fácil'),
('Animales', '¿Qué animal tiene la esperanza de vida más larga?', 'Tortuga de las Galápagos', 'Elefante africano, Ballena azul, Loro', 'fácil'),
('Animales', '¿Cuál es el animal más ruidoso del mundo?', 'Ballena azul', 'Elefante africano, Tigre, León', 'fácil'),
('Animales', '¿Qué animal tiene el sentido del olfato más desarrollado?', 'Oso polar', 'Perro, Elefante africano, Gato', 'fácil'),
('Animales', '¿Cuál es el animal más pequeño del mundo?', 'Murciélago nariz de cerdo', 'Ratón pigmeo africano, Abeja abeja enana, Colibrí abeja', 'fácil'),
('Animales', '¿Qué animal tiene la vista más aguda?', 'Águila', 'Buitre, Gato, Delfín', 'fácil'),
('Animales', '¿Cuál es el animal más resistente en términos de supervivencia en condiciones extremas?', 'Tardígrado', 'Cocodrilo, Escarabajo rinoceronte, Oso polar', 'fácil'),
('Animales', '¿Qué animal es conocido por su capacidad para caminar sobre el agua?', 'Basilisco común', 'Pato, Lagarto, Araña', 'fácil'),
('Animales', '¿Cuál es el animal más lento del mundo?', 'Caracol', 'Tortuga, Koala, Canguro', 'fácil'),
('Animales', '¿Qué animal es conocido por tener la piel más gruesa?', 'Rinoceronte', 'Elefante africano, Hipopótamo, Cocodrilo', 'fácil'),
('Animales', '¿Cuál es el animal más venenoso del mundo?', 'Medusa caja', 'Araña viuda negra, Serpiente de cascabel, Escorpión', 'fácil'),
('Animales', '¿Qué animal tiene el sentido del oído más desarrollado?', 'Murciélago', 'Elefante africano, Delfín, Gato', 'fácil'),
('Animales', '¿Cuál es el animal más grande que ha vivido en la Tierra?', 'Ballena azul', 'Dinosaurio, Elefante africano, Megalodón', 'fácil'),
('Animales', '¿Qué animal tiene el periodo de gestación más largo?', 'Elefante africano', 'Ballena azul, Oso polar, Jirafa', 'fácil'),
('Animales', '¿Cuál es el animal más longevo del mundo?', 'Medusa Turritopsis dohrnii', 'Tortuga, Elefante africano, Loro', 'fácil'),
('Animales', '¿Qué animal es conocido por tener el latido más lento del corazón?', 'Elefante africano', 'Ballena azul, Cocodrilo, Tortuga', 'fácil'),
('Animales', '¿Cuál es el animal más resistente a las condiciones extremas del desierto?', 'Camello', 'Escorpión, Serpiente, Lagartija', 'fácil'),
('Animales', '¿Qué animal tiene el mayor número de dientes?', 'Delfín', 'Tiburón, Ballena, Elefante africano', 'fácil'),
('Animales', '¿Cuál es el animal más grande que ha volado en la Tierra?', 'Pteranodon', 'Ave del terror, Quetzalcoatlus, Halcón peregrino', 'fácil'),
('Animales', '¿Qué animal tiene la piel más resistente?', 'Cocodrilo', 'Rinoceronte, Elefante africano, Hipopótamo', 'fácil'),
('Animales', '¿Cuál es el animal más fuerte del mundo?', 'Escarabajo rinoceronte', 'Elefante africano, Gorila, León', 'fácil'),
('Animales', '¿Qué animal es conocido por su capacidad para regenerar partes de su cuerpo?', 'Estrella de mar', 'Salamandra, Lagarto, Cangrejo ermitaño', 'fácil'),
('Animales', '¿Cuál es el animal más fuerte del mundo en términos de fuerza relativa?', 'Escarabajo rinoceronte', 'Elefante africano, Gorila, León', 'fácil'),
('Animales', '¿Qué animal tiene la esperanza de vida más larga?', 'Tortuga de las Galápagos', 'Elefante africano, Ballena azul, Loro', 'fácil'),
('Animales', '¿Cuál es el animal más ruidoso del mundo?', 'Ballena azul', 'Elefante africano, Tigre, León', 'fácil'),
('Animales', '¿Qué animal tiene el sentido del olfato más desarrollado?', 'Oso polar', 'Perro, Elefante africano, Gato', 'fácil'),
('Animales', '¿Cuál es el animal más pequeño del mundo?', 'Murciélago nariz de cerdo', 'Ratón pigmeo africano, Abeja abeja enana, Colibrí abeja', 'fácil'),
('Animales', '¿Qué animal tiene la vista más aguda?', 'Águila', 'Buitre, Gato, Delfín', 'fácil'),
('Animales', '¿Cuál es el animal más resistente en términos de supervivencia en condiciones extremas?', 'Tardígrado', 'Cocodrilo, Escarabajo rinoceronte, Oso polar', 'fácil'),
('Animales', '¿Qué animal es conocido por su capacidad para caminar sobre el agua?', 'Basilisco común', 'Pato, Lagarto, Araña', 'fácil'),
('Animales', '¿Cuál es el animal más lento del mundo?', 'Caracol', 'Tortuga, Koala, Canguro', 'fácil'),
('Animales', '¿Qué animal es conocido por tener la piel más gruesa?', 'Rinoceronte', 'Elefante africano, Hipopótamo, Cocodrilo', 'fácil'),
('Animales', '¿Cuál es el animal más venenoso del mundo?', 'Medusa caja', 'Araña viuda negra, Serpiente de cascabel, Escorpión', 'fácil'),
('Animales', '¿Qué animal tiene el sentido del oído más desarrollado?', 'Murciélago', 'Elefante africano, Delfín, Gato', 'fácil'),
('Animales', '¿Cuál es el animal más grande que ha vivido en la Tierra?', 'Ballena azul', 'Dinosaurio, Elefante africano, Megalodón', 'fácil'),
('Animales', '¿Qué animal tiene el periodo de gestación más largo?', 'Elefante africano', 'Ballena azul, Oso polar, Jirafa', 'fácil'),
('Animales', '¿Cuál es el animal más longevo del mundo?', 'Medusa Turritopsis dohrnii', 'Tortuga, Elefante africano, Loro', 'fácil'),
('Animales', '¿Qué animal es conocido por tener el latido más lento del corazón?', 'Elefante africano', 'Ballena azul, Cocodrilo, Tortuga', 'fácil'),
('Animales', '¿Cuál es el animal más resistente a las condiciones extremas del desierto?', 'Camello', 'Escorpión, Serpiente, Lagartija', 'fácil'),
('Animales', '¿Qué animal tiene el mayor número de dientes?', 'Delfín', 'Tiburón, Ballena, Elefante africano', 'fácil'),
('Animales', '¿Cuál es el animal más grande que ha volado en la Tierra?', 'Pteranodon', 'Ave del terror, Quetzalcoatlus, Halcón peregrino', 'fácil'),
('Animales', '¿Qué animal tiene la piel más resistente?', 'Cocodrilo', 'Rinoceronte, Elefante africano, Hipopótamo', 'fácil')
ON DUPLICATE KEY UPDATE `Pregunta` = `Pregunta`;


-- ----------------------
-- Insert Arte
-------------------------
INSERT INTO `preguntas` (`Categoria`, `Pregunta`, `RespuestaCorrecta`, `RespuestasIncorrectas`, `Dificultad`) VALUES
('Arte', '¿Quién pintó la Mona Lisa?', 'Leonardo da Vinci', 'Vincent van Gogh, Pablo Picasso, Rembrandt', 'fácil'),
('Arte', '¿En qué siglo vivió el pintor renacentista italiano Michelangelo?', 'Siglo XVI', 'Siglo XV, Siglo XVII, Siglo XVIII', 'fácil'),
('Arte', '¿Quién es el autor de la obra "La noche estrellada"?', 'Vincent van Gogh', 'Claude Monet, Salvador Dalí, Pablo Picasso', 'fácil'),
('Arte', '¿Qué movimiento artístico se caracteriza por representar objetos de forma distorsionada y subjetiva?', 'Cubismo', 'Expresionismo, Impresionismo, Surrealismo', 'fácil'),
('Arte', '¿Quién pintó "La persistencia de la memoria", también conocida como "Los relojes blandos"?', 'Salvador Dalí', 'Pablo Picasso, Vincent van Gogh, Claude Monet', 'fácil'),
('Arte', '¿En qué ciudad italiana se encuentra la famosa escultura de "David" de Michelangelo?', 'Florencia', 'Roma, Milán, Venecia', 'fácil'),
('Arte', '¿Qué artista es conocido por sus pinturas de grandes flores, como "Girasoles" y "Ramo de almendro en flor"?', 'Vincent van Gogh', 'Claude Monet, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿Quién es el autor de la obra "Guernica"?', 'Pablo Picasso', 'Vincent van Gogh, Salvador Dalí, Claude Monet', 'fácil'),
('Arte', '¿En qué movimiento artístico se encuentra la famosa obra "El grito"?', 'Expresionismo', 'Impresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién es conocido como el arquitecto del Siglo XX y fue uno de los principales exponentes del movimiento modernista?', 'Antoni Gaudí', 'Le Corbusier, Frank Lloyd Wright, Ludwig Mies van der Rohe', 'fácil'),
('Arte', '¿Cuál es la técnica de impresión desarrollada en China en el siglo IX que se utiliza principalmente para la reproducción de textos e imágenes?', 'Xilografía', 'Litografía, Serigrafía, Grabado', 'fácil'),
('Arte', '¿Quién pintó "La noche estrellada sobre el Ródano"?', 'Vincent van Gogh', 'Claude Monet, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué período artístico se desarrolló el Renacimiento?', 'Siglo XIV al XVI', 'Siglo XI al XIII, Siglo XVII al XIX, Siglo XX al XXI', 'fácil'),
('Arte', '¿Quién es conocido por su escultura "El pensador"?', 'Auguste Rodin', 'Michelangelo, Leonardo da Vinci, Antonio Canova', 'fácil'),
('Arte', '¿Cuál es el movimiento artístico que surgió en Francia a finales del siglo XIX y se caracteriza por representar la luz y el color de manera subjetiva?', 'Impresionismo', 'Expresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién pintó "Los lirios de agua"?', 'Claude Monet', 'Vincent van Gogh, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué ciudad se encuentra el Museo del Prado?', 'Madrid', 'Barcelona, París, Roma', 'fácil'),
('Arte', '¿Cuál es el nombre de la obra maestra de Miguel Ángel que se encuentra en la Capilla Sixtina?', 'La creación de Adán', 'La última cena, La piedad, La bóveda celeste', 'fácil'),
('Arte', '¿Quién es el autor de la obra "La persistencia de la memoria", también conocida como "Los relojes blandos"?', 'Salvador Dalí', 'Pablo Picasso, Vincent van Gogh, Claude Monet', 'fácil'),
('Arte', '¿En qué movimiento artístico se encuentra la famosa obra "El grito"?', 'Expresionismo', 'Impresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién es conocido como el arquitecto del Siglo XX y fue uno de los principales exponentes del movimiento modernista?', 'Antoni Gaudí', 'Le Corbusier, Frank Lloyd Wright, Ludwig Mies van der Rohe', 'fácil'),
('Arte', '¿Cuál es la técnica de impresión desarrollada en China en el siglo IX que se utiliza principalmente para la reproducción de textos e imágenes?', 'Xilografía', 'Litografía, Serigrafía, Grabado', 'fácil'),
('Arte', '¿Quién pintó "La noche estrellada sobre el Ródano"?', 'Vincent van Gogh', 'Claude Monet, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué período artístico se desarrolló el Renacimiento?', 'Siglo XIV al XVI', 'Siglo XI al XIII, Siglo XVII al XIX, Siglo XX al XXI', 'fácil'),
('Arte', '¿Quién es conocido por su escultura "El pensador"?', 'Auguste Rodin', 'Michelangelo, Leonardo da Vinci, Antonio Canova', 'fácil'),
('Arte', '¿Cuál es el movimiento artístico que surgió en Francia a finales del siglo XIX y se caracteriza por representar la luz y el color de manera subjetiva?', 'Impresionismo', 'Expresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién pintó "Los lirios de agua"?', 'Claude Monet', 'Vincent van Gogh, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué ciudad se encuentra el Museo del Prado?', 'Madrid', 'Barcelona, París, Roma', 'fácil'),
('Arte', '¿Cuál es el nombre de la obra maestra de Miguel Ángel que se encuentra en la Capilla Sixtina?', 'La creación de Adán', 'La última cena, La piedad, La bóveda celeste', 'fácil'),
('Arte', '¿Quién es el autor de la obra "La joven de la perla"?', 'Johannes Vermeer', 'Leonardo da Vinci, Pablo Picasso, Vincent van Gogh', 'fácil'),
('Arte', '¿En qué ciudad se encuentra la famosa Torre Eiffel?', 'París', 'Londres, Roma, Madrid', 'fácil'),
('Arte', '¿Cuál es el nombre del movimiento artístico que surgió en los años 60 en Estados Unidos y busca representar la realidad de forma objetiva y literal?', 'Hiperrealismo', 'Surrealismo, Expresionismo, Cubismo', 'fácil'),
('Arte', '¿Quién pintó "La última cena"?', 'Leonardo da Vinci', 'Pablo Picasso, Vincent van Gogh, Claude Monet', 'fácil'),
('Arte', '¿En qué período artístico se desarrolló el Barroco?', 'Siglo XVII', 'Siglo XVI, Siglo XVIII, Siglo XIX', 'fácil'),
('Arte', '¿Quién es el autor de la obra "El jardín de las delicias"?', 'Hieronymus Bosch', 'Pablo Picasso, Vincent van Gogh, Salvador Dalí', 'fácil'),
('Arte', '¿En qué país se encuentra el Museo del Louvre?', 'Francia', 'Italia, España, Estados Unidos', 'fácil'),
('Arte', '¿Cuál es el nombre del movimiento artístico que surgió en Rusia a principios del siglo XX y se caracteriza por el uso de formas geométricas y colores primarios?', 'Suprematismo', 'Constructivismo, Expresionismo, Dadaísmo', 'fácil'),
('Arte', '¿Quién pintó "La persistencia de la memoria", también conocida como "Los relojes blandos"?', 'Salvador Dalí', 'Pablo Picasso, Vincent van Gogh, Claude Monet', 'fácil'),
('Arte', '¿En qué movimiento artístico se encuentra la famosa obra "El grito"?', 'Expresionismo', 'Impresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién es conocido como el arquitecto del Siglo XX y fue uno de los principales exponentes del movimiento modernista?', 'Antoni Gaudí', 'Le Corbusier, Frank Lloyd Wright, Ludwig Mies van der Rohe', 'fácil'),
('Arte', '¿Cuál es la técnica de impresión desarrollada en China en el siglo IX que se utiliza principalmente para la reproducción de textos e imágenes?', 'Xilografía', 'Litografía, Serigrafía, Grabado', 'fácil'),
('Arte', '¿Quién pintó "La noche estrellada sobre el Ródano"?', 'Vincent van Gogh', 'Claude Monet, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué período artístico se desarrolló el Renacimiento?', 'Siglo XIV al XVI', 'Siglo XI al XIII, Siglo XVII al XIX, Siglo XX al XXI', 'fácil'),
('Arte', '¿Quién es conocido por su escultura "El pensador"?', 'Auguste Rodin', 'Michelangelo, Leonardo da Vinci, Antonio Canova', 'fácil'),
('Arte', '¿Cuál es el movimiento artístico que surgió en Francia a finales del siglo XIX y se caracteriza por representar la luz y el color de manera subjetiva?', 'Impresionismo', 'Expresionismo, Cubismo, Surrealismo', 'fácil'),
('Arte', '¿Quién pintó "Los lirios de agua"?', 'Claude Monet', 'Vincent van Gogh, Pablo Picasso, Salvador Dalí', 'fácil'),
('Arte', '¿En qué ciudad se encuentra el Museo del Prado?', 'Madrid', 'Barcelona, París, Roma', 'fácil'),
('Arte', '¿Cuál es el nombre de la obra maestra de Miguel Ángel que se encuentra en la Capilla Sixtina?', 'La creación de Adán', 'La última cena, La piedad, La bóveda celeste', 'fácil'),
('Arte', '¿Quién es el autor de la obra "La joven de la perla"?', 'Johannes Vermeer', 'Leonardo da Vinci, Pablo Picasso, Vincent van Gogh', 'fácil'),
('Arte', '¿En qué ciudad se encuentra la famosa Torre Eiffel?', 'París', 'Londres, Roma, Madrid', 'fácil'),
('Arte', '¿Cuál es el nombre del movimiento artístico que surgió en los años 60 en Estados Unidos y busca representar la realidad de forma objetiva y literal?', 'Hiperrealismo', 'Surrealismo, Expresionismo, Cubismo', 'fácil'),
('Arte', '¿Quién pintó "La última cena"?', 'Leonardo da Vinci', 'Pablo Picasso, Vincent van Gogh, Claude Monet', 'fácil'),
('Arte', '¿En qué período artístico se desarrolló el Barroco?', 'Siglo XVII', 'Siglo XVI, Siglo XVIII, Siglo XIX', 'fácil'),
('Arte', '¿Quién es el autor de la obra "El jardín de las delicias"?', 'Hieronymus Bosch', 'Pablo Picasso, Vincent van Gogh, Salvador Dalí', 'fácil'),
('Arte', '¿En qué país se encuentra el Museo del Louvre?', 'Francia', 'Italia, España, Estados Unidos', 'fácil'),
('Arte', '¿Cuál es el nombre del movimiento artístico que surgió en Rusia a principios del siglo XX y se caracteriza por el uso de formas geométricas y colores primarios?', 'Suprematismo', 'Constructivismo, Expresionismo, Dadaísmo', 'fácil')
ON DUPLICATE KEY UPDATE `Pregunta` = `Pregunta`;

-- ----------------------
-- Insert CienciaYNaturaleza
-------------------------
INSERT INTO `preguntas` (`Categoria`, `Pregunta`, `RespuestaCorrecta`, `RespuestasIncorrectas`, `Dificultad`) VALUES
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual las plantas liberan oxígeno a la atmósfera?', 'Fotosíntesis', 'Respiración, Transpiración, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el hueso más largo del cuerpo humano?', 'Fémur', 'Húmero, Tibia, Radio', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano más grande del cuerpo humano?', 'La piel', 'Hígado, Corazón, Pulmones', 'fácil'),
('Ciencia y Naturaleza', '¿Cuántos huesos tiene el cuerpo humano?', '206', '300, 100, 400', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más rápido del mundo en tierra?', 'Guepardo', 'Leopardo, Tigre, León', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano encargado de producir la insulina en el cuerpo humano?', 'Páncreas', 'Hígado, Riñones, Estómago', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el gas más abundante en la atmósfera terrestre?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Hidrógeno', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es la unidad básica de la materia?', 'Átomo', 'Molécula, Partícula, Neutrón', 'fácil'),
('Ciencia y Naturaleza', '¿Qué causa la marea alta y la marea baja en los océanos?', 'Gravedad lunar', 'Gravedad solar, Vientos, Corrientes oceánicas', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual las plantas producen su propio alimento?', 'Fotosíntesis', 'Respiración, Transpiración, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el hueso más pequeño del cuerpo humano?', 'Estribo', 'Martillo, Yunque, Fémur', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano principal del sistema circulatorio humano?', 'Corazón', 'Pulmones, Hígado, Riñones', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más grande del mundo?', 'La ballena azul', 'Elefante, Dinosaurio, Tiburón ballena', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es la principal función de los glóbulos rojos en la sangre?', 'Transportar oxígeno', 'Transportar dióxido de carbono, Combatir infecciones, Coagular la sangre', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el metal más abundante en la corteza terrestre?', 'Aluminio', 'Hierro, Oro, Plata', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el componente principal del aire que respiramos?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Argón', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más venenoso del mundo?', 'La medusa de caja', 'Serpiente de cascabel, Escorpión, Araña', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual los seres vivos obtienen energía de los alimentos?', 'Respiración celular', 'Fotosíntesis, Fermentación, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano más grande del cuerpo humano?', 'La piel', 'Hígado, Corazón, Pulmones', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más rápido del mundo en tierra?', 'Guepardo', 'Leopardo, Tigre, León', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano encargado de producir la insulina en el cuerpo humano?', 'Páncreas', 'Hígado, Riñones, Estómago', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el gas más abundante en la atmósfera terrestre?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Hidrógeno', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es la unidad básica de la materia?', 'Átomo', 'Molécula, Partícula, Neutrón', 'fácil'),
('Ciencia y Naturaleza', '¿Qué causa la marea alta y la marea baja en los océanos?', 'Gravedad lunar', 'Gravedad solar, Vientos, Corrientes oceánicas', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual las plantas producen su propio alimento?', 'Fotosíntesis', 'Respiración, Transpiración, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el hueso más pequeño del cuerpo humano?', 'Estribo', 'Martillo, Yunque, Fémur', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano principal del sistema circulatorio humano?', 'Corazón', 'Pulmones, Hígado, Riñones', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más grande del mundo?', 'La ballena azul', 'Elefante, Dinosaurio, Tiburón ballena', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es la principal función de los glóbulos rojos en la sangre?', 'Transportar oxígeno', 'Transportar dióxido de carbono, Combatir infecciones, Coagular la sangre', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el metal más abundante en la corteza terrestre?', 'Aluminio', 'Hierro, Oro, Plata', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el componente principal del aire que respiramos?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Argón', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más venenoso del mundo?', 'La medusa de caja', 'Serpiente de cascabel, Escorpión, Araña', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual los seres vivos obtienen energía de los alimentos?', 'Respiración celular', 'Fotosíntesis, Fermentación, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más grande del mundo?', 'Ballena azul', 'Elefante, Dinosaurio, Tiburón ballena', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el gas más abundante en la atmósfera terrestre?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Argón', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el componente principal del aire que respiramos?', 'Nitrógeno', 'Oxígeno, Dióxido de carbono, Argón', 'fácil'),
('Ciencia y Naturaleza', '¿Qué causa la marea alta y la marea baja en los océanos?', 'Gravedad lunar', 'Gravedad solar, Vientos, Corrientes oceánicas', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el proceso mediante el cual las plantas producen su propio alimento?', 'Fotosíntesis', 'Respiración, Transpiración, Digestión', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el hueso más pequeño del cuerpo humano?', 'Estribo', 'Martillo, Yunque, Fémur', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el órgano principal del sistema circulatorio humano?', 'Corazón', 'Pulmones, Hígado, Riñones', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es la principal función de los glóbulos rojos en la sangre?', 'Transportar oxígeno', 'Transportar dióxido de carbono, Combatir infecciones, Coagular la sangre', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el metal más abundante en la corteza terrestre?', 'Aluminio', 'Hierro, Oro, Plata', 'fácil'),
('Ciencia y Naturaleza', '¿Cuál es el animal más venenoso del mundo?', 'Medusa de caja', 'Serpiente de cascabel, Escorpión, Araña', 'fácil');













-- ----------------------
-- Insert Historia
-------------------------












-- ----------------------
-- Insert Geografia
-------------------------