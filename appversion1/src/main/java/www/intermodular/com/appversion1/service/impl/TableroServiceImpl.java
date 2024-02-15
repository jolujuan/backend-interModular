package www.intermodular.com.appversion1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.db.CasillaDb;
import www.intermodular.com.appversion1.model.db.CasillaTipoDb;
import www.intermodular.com.appversion1.model.db.JugadorDb;
import www.intermodular.com.appversion1.model.db.TableroDb;
import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.CasillaTipo;
import www.intermodular.com.appversion1.model.dto.EstadoTablero;
import www.intermodular.com.appversion1.model.dto.TableroInfo;
import www.intermodular.com.appversion1.repository.CasillaRepository;
import www.intermodular.com.appversion1.repository.JugadorRepository;
import www.intermodular.com.appversion1.repository.TableroRepository;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;
import www.intermodular.com.appversion1.security.repository.UsuarioRepository;
import www.intermodular.com.appversion1.service.TableroService;
import www.intermodular.com.appversion1.service.mapper.TableroMapper;

@Service
public class TableroServiceImpl implements TableroService {
    private final TableroRepository tableroRepository;
    private final UsuarioRepository usuarioRepository;
    private final CasillaRepository casillaRepository;
    private final JugadorRepository jugadorRepository;

    public TableroServiceImpl(TableroRepository tableroRepository, UsuarioRepository usuarioRepository,
            CasillaRepository casillaRepository, JugadorRepository jugadorRepository) {
        this.tableroRepository = tableroRepository;
        this.usuarioRepository = usuarioRepository;
        this.casillaRepository = casillaRepository;
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public Long getIdUserJugador(String nickname) {
        Optional<UsuarioDb> usuarioOptional = usuarioRepository.findByNickname(nickname);
        if (usuarioOptional.isPresent()) {
            UsuarioDb usuario = usuarioOptional.get();
            return usuario.getId();
        } else {

            return null;
        }
    }

    public String getNicknameUserTablero(Long id) {
        Optional<UsuarioDb> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioDb usuario = usuarioOptional.get();
            return usuario.getNickname();
        } else {

            return "nickname erroneo";
        }
    }

    @Override // CREATE GAME_BOARD CREAR TABLERO
    public String getStartTablero(Long idUsuario) {

        TableroDb tablero = new TableroDb();
        // tablero.setId(idUsuario); // el id del tablero no hace falta
        tablero.setGanador(0L); // ganador del Game
        tablero.setEstado(EstadoTablero.PAUSADA); // la empezamos pausada pq tendra que unirse un Jugador
        tablero.setPreguntasHechas("");
        tablero.setTurnoJugador(idUsuario);// el turno del 1 jugador

        CasillaDb casilla = casillaRepository.findById(1L).orElse(null);
        if (casilla == null) {

            return "No se ha podido Introducir el id Al primer jugador";
        }
        // para guardar casilla del primer jugador y la casilla
        tablero.setCasillaJugador1(casilla);

        // Ver si ya existe un Jugador con ese id o crearlo En la bbd
        // Buscar el jugador usando su ID de usuario
        JugadorDb jugador1 = jugadorRepository.findByIdUsuario(idUsuario);

        // Si el jugador no existe, crearlo
        if (jugador1 == null) {
            jugador1 = new JugadorDb();
            jugador1.setIdUsuario(idUsuario);
            jugador1.setNombre(getNicknameUserTablero(idUsuario));
            jugadorRepository.save(jugador1);
        }
        // creacion del jugador con Id y Tipo de usuario

        tablero.setJugador1(jugador1);

        // Guardar el tablero en la base de datos
        tablero = tableroRepository.save(tablero);

        // Devolver el ID del tablero guardado
        return "IdTablero: " + tablero.getIdTablero() + " Player_1 " + jugador1.getNombre();
    }

    @Override // ADD PLAYER
    public String getAnotherPlayer(String nickname, Long idTable) {
        // encontrar si hay un tabldro con el id
        TableroDb tablero = tableroRepository.findByIdTablero(idTable);

        // controlar si la partida/tablero esta finalizada
        if (tablero.getEstado() == EstadoTablero.FINALIZADO) {
            return "1 ERRO:GAME_IS_FINISHED"; // la partida esta finalizada
        }
        if (tablero.getEstado() == EstadoTablero.EN_CURSO) {
            return "1 ERRO:GAME_IS_ALREADY_STARTED"; // la partida esta finalizada
        }
        Long idUsuario = getIdUserJugador(nickname);
        // encontrar si hay un jugador con ese id si no crearlo
        JugadorDb newJugador = jugadorRepository.findByIdUsuario(idUsuario);
        if (newJugador == null) {
            newJugador = new JugadorDb();

        }
        CasillaDb casilla = casillaRepository.findById(1L).orElse(null);
        // Verificar si el tablero existe

        if (tablero != null) {
            newJugador.setIdUsuario(idUsuario);
            newJugador.setNombre(nickname); // creacion del jugador con Id y Tipo de usuario

            // comprobar que el juego no pueda contener el mismo jugador repetido n veces si
            // es nullo lo controlo para que no pete
            if (tablero.getJugador1() != null
                    && tablero.getJugador1().getNombre() != null && tablero.getJugador1().getNombre().equals(nickname)
                    ||
                    tablero.getJugador2() != null && tablero.getJugador2().getNombre() != null
                            && tablero.getJugador2().getNombre().equals(nickname)
                    ||
                    tablero.getJugador3() != null && tablero.getJugador3().getNombre() != null
                            && tablero.getJugador3().getNombre().equals(nickname)
                    ||
                    tablero.getJugador4() != null && tablero.getJugador4().getNombre() != null
                            && tablero.getJugador4().getNombre().equals(nickname)) {
                return "2 ERROR_PLAYER_ALREADY_IN_GAME";
            }
            jugadorRepository.save(newJugador);
            if (tablero.getJugador2() == null) {

                tablero.setJugador2(newJugador);
                tablero.setCasillaJugador2(casilla);
            } else if (tablero.getJugador3() == null) {
                tablero.setJugador3(newJugador);
                tablero.setCasillaJugador3(casilla);
            } else if (tablero.getJugador4() == null) {
                tablero.setJugador4(newJugador);
                tablero.setCasillaJugador4(casilla);
            } else {
                return "2 ERROR_ONLY_4_PLAYERS_IN_1_GAME";
            }

            tablero = tableroRepository.save(tablero);
            String player1 = tablero.getJugador1() != null ? tablero.getJugador1().getNombre() : "null";
            String player2 = tablero.getJugador2() != null ? tablero.getJugador2().getNombre() : "null";
            String player3 = tablero.getJugador3() != null ? tablero.getJugador3().getNombre() : "null";
            String player4 = tablero.getJugador4() != null ? tablero.getJugador4().getNombre() : "null";

            return "IdTablero: " + tablero.getIdTablero()
                    + " Player_1 " + player1
                    + " Player_2 " + player2
                    + " Player_3 " + player3
                    + " Player_4 " + player4;
        } else {
            // error no encuentra el tablero
            return "3 ERROR_TABLERO_ID_NOT_FOUND";
        }

    }

    @Override
    public String getTablerostatus(Long idTablero) {

        TableroDb tablero = tableroRepository.findByIdTablero(idTablero);

        String player1 = tablero.getJugador1() != null ? tablero.getJugador1().getNombre() : "Jugador-1";
        String player2 = tablero.getJugador2() != null ? tablero.getJugador2().getNombre() : "Jugador-2";
        String player3 = tablero.getJugador3() != null ? tablero.getJugador3().getNombre() : "Jugador-3";
        String player4 = tablero.getJugador4() != null ? tablero.getJugador4().getNombre() : "Jugador-4";

        return "IdTablero: " + tablero.getIdTablero()
                + " Player_1 " + player1
                + " Player_2 " + player2
                + " Player_3 " + player3
                + " Player_4 " + player4;
    }

    @Override
    public String getStartGame(Long idTablero) {
        TableroDb tablero = tableroRepository.findByIdTablero(idTablero);

        tablero.setEstado(EstadoTablero.EN_CURSO);
        tablero = tableroRepository.save(tablero);

        return "IdTablero: " + tablero.getIdTablero() + " Estado " + tablero.getEstado();
    }

    @Override
    public String getRollDice(String nickname, Long idTablero) {
        final int CASILLAS_TOTALES = 17;

        Random random = new Random();

        // Generar un número aleatorio del 1 al 4

        long numrandom = (long) (random.nextInt(4) + 1);
        // ver en que casilla esta el jugador , para saber si la tirada le sirve o
        // pierde turno
        Long jugador = getIdUserJugador(nickname); // id del que tira
        TableroDb partidaActual = tableroRepository.findByIdTablero(idTablero);
        if (partidaActual == null) {
            return "TABLE_NO_VALID";
        }

        Long casilla_actual = 0L;
        String jugadorActual = "";// con esto podre cambiar el turno en caso de que pierda el turno

        // ver que jugador realiza la tirada
        if (partidaActual.getJugador1().getIdUsuario().equals(jugador)) {
            casilla_actual = partidaActual.getCasillaJugador1().getId();
            jugadorActual = "1";
        } else if (partidaActual.getJugador2().getIdUsuario().equals(jugador)) {
            casilla_actual = partidaActual.getCasillaJugador2().getId();
            jugadorActual = "2";
        } else if (partidaActual.getJugador3().getIdUsuario().equals(jugador)) {
            casilla_actual = partidaActual.getCasillaJugador3().getId();
            jugadorActual = "3";
        } else if (partidaActual.getJugador4().getIdUsuario().equals(jugador)) {
            casilla_actual = partidaActual.getCasillaJugador4().getId();
            jugadorActual = "4";
        } else
            return "PLAYER_NOT_FOUND";

        // comprobar si es tu turno realmente
        if (jugador != partidaActual.getTurnoJugador()) {
            return "NOT_YOUR_TURN";
        }
        // comprobar tirada valida
        if (casilla_actual + numrandom > CASILLAS_TOTALES) {
            if (jugadorActual.equals("1")) {
                partidaActual.setTurnoJugador(partidaActual.getJugador2().getIdUsuario());// cambiar el turno al
                                                                                          // siguiente
            } else if (jugadorActual.equals("2")) {
                if (partidaActual.getJugador3().getIdUsuario() == null) {// no hay mas jugadores entonces cambia de
                                                                         // turno al primero
                    partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());
                } else
                    partidaActual.setTurnoJugador(partidaActual.getJugador3().getIdUsuario());// cambiar el turno al
                                                                                              // siguiente
            } else if (jugadorActual.equals("3")) {
                if (partidaActual.getJugador4().getIdUsuario() == null) {// no hay mas jugadores entonces cambia de
                                                                         // turno al primero
                    partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());
                } else
                    partidaActual.setTurnoJugador(partidaActual.getJugador4().getIdUsuario());// cambiar el turno al
                                                                                              // siguiente
            } else if (jugadorActual.equals("4")) {

                partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());// cambiar el turno al
                                                                                          // siguiente de vuelta al
                                                                                          // ciclo
            }

            tableroRepository.save(partidaActual);
            return "LOSE_TURN_NUMBER_" + numrandom;
        }

        // Convertir el número a String
        String tirarDado = String.valueOf(numrandom);

        return tirarDado;
    }

    @Override
    public String movePlayer(String nickname, Long numBoxMove, Long idTable) {
        Long idJugador = getIdUserJugador(nickname); // id del que mueve ficha
        // tablero dnd se mueve la app
        TableroDb tableActual = tableroRepository.findByIdTablero(idTable);
        if (tableActual == null) {
            return "TABLE_NO_VALID";
        }
        if (!tableActual.getTurnoJugador().equals(idJugador)) {
            return "NOT_YOUR_TURN";
        }

        // ver que jugador realiza la tirada
        String jugadorActual = "";
        String casillaTipo = "";
        Long casilla_actual = 0L;
        if (tableActual.getJugador1().getIdUsuario().equals(idJugador)) {
            casilla_actual = tableActual.getCasillaJugador1().getId();
            // para guardar a que tipo casilla nos movemos
            Long nuevoIdCasilla = casilla_actual + numBoxMove;

            CasillaDb nuevaCasilla = casillaRepository.findById(nuevoIdCasilla).orElse(null);
            if (nuevaCasilla == null) {
                return " ERROR_MOVE_INVALID";
            }
            tableActual.setCasillaJugador1(nuevaCasilla);
            // devolver el tipo de casilla a la que me muevo
            CasillaTipo Tipo = nuevaCasilla.getTipoCasilla();
            casillaTipo = Tipo.name(); // nombre de la casilla
            jugadorActual = "1";
        } else if (tableActual.getJugador2().getIdUsuario().equals(idJugador)) {
            casilla_actual = tableActual.getCasillaJugador2().getId();
            Long nuevoIdCasilla = casilla_actual + numBoxMove;
            CasillaDb nuevaCasilla = casillaRepository.findById(nuevoIdCasilla).orElse(null);
            if (nuevaCasilla == null) {
                return "ERROR_MOVE_INVALID";
            }
            tableActual.setCasillaJugador2(nuevaCasilla);

            // devolver el tipo de casilla a la que me muevo
            CasillaTipo Tipo = nuevaCasilla.getTipoCasilla();
            casillaTipo = Tipo.name(); // nombre de la casilla
            jugadorActual = "2";
        } else if (tableActual.getJugador3().getIdUsuario().equals(idJugador)) {
            casilla_actual = tableActual.getCasillaJugador3().getId();

            Long nuevoIdCasilla = casilla_actual + numBoxMove;
            CasillaDb nuevaCasilla = casillaRepository.findById(nuevoIdCasilla).orElse(null);
            if (nuevaCasilla == null) {
                return "ERROR_MOVE_INVALID";
            }
            tableActual.setCasillaJugador3(nuevaCasilla);
            // devolver el tipo de casilla a la que me muevo
            CasillaTipo Tipo = nuevaCasilla.getTipoCasilla();
            casillaTipo = Tipo.name(); // nombre de la casilla
            jugadorActual = "3";
        } else if (tableActual.getJugador4().getIdUsuario().equals(idJugador)) {
            casilla_actual = tableActual.getCasillaJugador4().getId();

            Long nuevoIdCasilla = casilla_actual + numBoxMove;
            CasillaDb nuevaCasilla = casillaRepository.findById(nuevoIdCasilla).orElse(null);
            if (nuevaCasilla == null) {
                return "ERROR_MOVE_INVALID";
            }
            tableActual.setCasillaJugador4(nuevaCasilla);
            // devolver el tipo de casilla a la que me muevo
            CasillaTipo Tipo = nuevaCasilla.getTipoCasilla();
            casillaTipo = Tipo.name(); // nombre de la casilla
            jugadorActual = "4";
        } else
            return "PLAYER_NOT_FOUND";

        tableroRepository.save(tableActual);
        return casillaTipo;
    }
    

    @Override
    public String checkMovement(String nickname, String movementTipe, Long idTable) {

        // Sacamos la partida tablero
        Long idJugador = getIdUserJugador(nickname); // id del que mueve ficha
        // tablero dnd se mueve la app
        TableroDb partidaActual = tableroRepository.findByIdTablero(idTable);
        if (partidaActual == null) {
            return "TABLE_NO_VALID";
        }
        if (!partidaActual.getTurnoJugador().equals(idJugador)) {
            return "NOT_YOUR_TURN";
        }

        String jugadorActual = "";

        Long casilla_actual = 0L;
        // ver que jugador realiza la tirada
        if (partidaActual.getJugador1().getIdUsuario().equals(idJugador)) {
            casilla_actual = partidaActual.getCasillaJugador1().getId();
            jugadorActual = "1";
        } else if (partidaActual.getJugador2().getIdUsuario().equals(idJugador)) {
            casilla_actual = partidaActual.getCasillaJugador2().getId();
            jugadorActual = "2";
        } else if (partidaActual.getJugador3().getIdUsuario().equals(idJugador)) {
            casilla_actual = partidaActual.getCasillaJugador3().getId();
            jugadorActual = "3";
        } else if (partidaActual.getJugador4().getIdUsuario().equals(idJugador)) {
            casilla_actual = partidaActual.getCasillaJugador4().getId();
            jugadorActual = "4";
        } else
            return "PLAYER_NOT_FOUND";

        String[] tematicas = { "geography", "celebrity", "animals", "history" };
        Random random = new Random();
        int indiceAleatorio = random.nextInt(tematicas.length);
        // Obtener la temática aleatoria
        String tematicaAleatoria = tematicas[indiceAleatorio]; // Sacaremos una tematica random con esto

        // hacer los cambios segun que jugador y el tipoDecasilla
        switch (jugadorActual) {
            case "1":
                switch (movementTipe) {
                    case "RETROCEDE": // RETROCEDE TRES CASILLAS Y SE CAMBIA EL TURNO
                        Long casillaActual = partidaActual.getCasillaJugador1().getId();

                        CasillaDb nuevaCasilla = casillaRepository.findById(casillaActual - 3L).orElse(null); // restamos
                                                                                                              // 3
                                                                                                              // casillas
                                                                                                              // para
                                                                                                              // que
                                                                                                              // retroceda
                        if (nuevaCasilla == null) {
                            return "ERROR_MOVE_INVALID";
                        }
                        partidaActual.setCasillaJugador1(nuevaCasilla);// casilla cambiada

                        break;

                    case "NORMAL":// NO PASA NADA SE PASA EL TURNO

                        break;
                    case "BONIFICACION": // CASILLA PREGUNTA LE TOCARA AL JUGADOR RESPONDER UNA PREGUNTA Y CONSEGUIR
                                         // OTRA TIRADA O NO SE PASA EL TURNO

                        return "GET_QUESTION " + tematicaAleatoria;

                    case "LLEGADA":// GANA EL JUGADOR

                        partidaActual.setGanador(idJugador);
                        partidaActual.setEstado(EstadoTablero.FINALIZADO);
                        tableroRepository.save(partidaActual);
                        return "WINNER_" + nickname;

                }
                if ("LLEGADA" != movementTipe || "BONIFICACION" != movementTipe) {
                    partidaActual.setTurnoJugador(partidaActual.getCasillaJugador2().getId());
                    tableroRepository.save(partidaActual);
                    if (movementTipe.equals("NORMAL")) {
                        return "NORMAL_DONE_NEXT_TURN";
                    }

                    if (movementTipe.equals("RETROCEDE")) {
                        return "RETROCEDE_DONE_NEXT_TURN";
                    }
                }
                break;

            case "2":
                switch (movementTipe) {
                    case "RETROCEDE": // RETROCEDE TRES CASILLAS Y SE CAMBIA EL TURNO
                        Long casillaActual = partidaActual.getCasillaJugador2().getId();

                        CasillaDb nuevaCasilla = casillaRepository.findById(casillaActual - 3L).orElse(null); // restamos
                                                                                                              // 3
                                                                                                              // casillas
                                                                                                              // para
                                                                                                              // que
                                                                                                              // retroceda
                        if (nuevaCasilla == null) {
                            return "ERROR_MOVE_INVALID";
                        }
                        partidaActual.setCasillaJugador2(nuevaCasilla);// casilla cambiada

                        break;

                    case "NORMAL":// NO PASA NADA SE PASA EL TURNO
                            System.out.println("ENTRA EN HOLA");
                        break;
                    case "BONIFICACION": // CASILLA PREGUNTA LE TOCARA AL JUGADOR RESPONDER UNA PREGUNTA Y CONSEGUIR
                                         // OTRA TIRADA O NO SE PASA EL TURNO

                        return "GET_QUESTION " + tematicaAleatoria;

                    case "LLEGADA":// GANA EL JUGADOR

                        partidaActual.setGanador(idJugador);
                        partidaActual.setEstado(EstadoTablero.FINALIZADO);
                        tableroRepository.save(partidaActual);
                        return "WINNER_" + nickname;

                }
                if ("LLEGADA" != movementTipe || "BONIFICACION" != movementTipe) {
                    JugadorDb jugador3= jugadorRepository.findByIdUsuario(casilla_actual);

                    if ( partidaActual.getJugador3()==null || partidaActual.getJugador3().getIdUsuario() == null ) {// no hay mas jugadores entonces cambia de
                        // turno al primero
                        partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());
                    } else
                        partidaActual.setTurnoJugador(partidaActual.getJugador3().getIdUsuario());// cambiar el turno al
                    // siguiente
                    tableroRepository.save(partidaActual);
                    if (movementTipe.equals("NORMAL")) {
                        return "NORMAL_DONE_NEXT_TURN";
                    }

                    if (movementTipe.equals("RETROCEDE")) {
                        return "RETROCEDE_DONE_NEXT_TURN";
                    }
                }

                break;
            case "3":
                switch (movementTipe) {
                    case "RETROCEDE": // RETROCEDE TRES CASILLAS Y SE CAMBIA EL TURNO
                        Long casillaActual = partidaActual.getCasillaJugador3().getId();

                        CasillaDb nuevaCasilla = casillaRepository.findById(casillaActual - 3L).orElse(null); // restamos
                                                                                                              // 3
                                                                                                              // casillas
                                                                                                              // para
                                                                                                              // que
                                                                                                              // retroceda
                        if (nuevaCasilla == null) {
                            return "ERROR_MOVE_INVALID";
                        }
                        partidaActual.setCasillaJugador3(nuevaCasilla);// casilla cambiada

                        break;

                    case "NORMAL":// NO PASA NADA SE PASA EL TURNO

                        break;
                    case "BONIFICACION": // CASILLA PREGUNTA LE TOCARA AL JUGADOR RESPONDER UNA PREGUNTA Y CONSEGUIR
                                         // OTRA TIRADA O NO SE PASA EL TURNO

                        return "GET_QUESTION " + tematicaAleatoria;

                    case "LLEGADA":// GANA EL JUGADOR

                        partidaActual.setGanador(idJugador);
                        partidaActual.setEstado(EstadoTablero.FINALIZADO);
                        tableroRepository.save(partidaActual);
                        return "WINNER_" + nickname;

                }
                if ("LLEGADA" != movementTipe || "BONIFICACION" != movementTipe) {
                    if (partidaActual.getJugador4().getIdUsuario() == null) {// no hay mas jugadores entonces cambia de
                        // turno al primero
                        partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());
                    } else
                        partidaActual.setTurnoJugador(partidaActual.getJugador4().getIdUsuario());// cambiar el turno al
                    // siguiente
                    tableroRepository.save(partidaActual);
                    if (movementTipe.equals("NORMAL")) {
                        return "NORMAL_DONE_NEXT_TURN";
                    }

                    if (movementTipe.equals("RETROCEDE")) {
                        return "RETROCEDE_DONE_NEXT_TURN";
                    }
                }

                break;
            case "4":
                switch (movementTipe) {
                    case "RETROCEDE": // RETROCEDE TRES CASILLAS Y SE CAMBIA EL TURNO
                        Long casillaActual = partidaActual.getCasillaJugador4().getId();

                        CasillaDb nuevaCasilla = casillaRepository.findById(casillaActual - 3L).orElse(null); // restamos
                                                                                                              // 3
                                                                                                              // casillas
                                                                                                              // para
                                                                                                              // que
                                                                                                              // retroceda
                        if (nuevaCasilla == null) {
                            return "ERROR_MOVE_INVALID";
                        }
                        partidaActual.setCasillaJugador4(nuevaCasilla);// casilla cambiada

                        break;

                    case "NORMAL":// NO PASA NADA SE PASA EL TURNO

                        break;
                    case "BONIFICACION": // CASILLA PREGUNTA LE TOCARA AL JUGADOR RESPONDER UNA PREGUNTA Y CONSEGUIR
                                         // OTRA TIRADA O NO SE PASA EL TURNO

                        return "GET_QUESTION " + tematicaAleatoria;

                    case "LLEGADA":// GANA EL JUGADOR

                        partidaActual.setGanador(idJugador);
                        partidaActual.setEstado(EstadoTablero.FINALIZADO);
                        tableroRepository.save(partidaActual);
                        return "WINNER_" + nickname;

                }
                if ("LLEGADA" != movementTipe || "BONIFICACION" != movementTipe) {

                    // turno al primero
                    partidaActual.setTurnoJugador(partidaActual.getJugador1().getIdUsuario());

                    tableroRepository.save(partidaActual);
                    if (movementTipe.equals("NORMAL")) {
                        return "NORMAL_DONE_NEXT_TURN";
                    }

                    if (movementTipe.equals("RETROCEDE")) {
                        return "RETROCEDE_DONE_NEXT_TURN";
                    }
                }
                break;
            default:
                return "ERROR_IN_SWITCH";
        }

        return "";

    }

}
