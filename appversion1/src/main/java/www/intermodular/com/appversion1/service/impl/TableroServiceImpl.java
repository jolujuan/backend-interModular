package www.intermodular.com.appversion1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.db.CasillaDb;
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
    public Long getIdUserTablero(String nickname) {
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

    @Override //CREATE GAME_BOARD CREAR TABLERO
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

    @Override //ADD PLAYER
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
        Long idUsuario = getIdUserTablero(nickname);
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

        String player1 = tablero.getJugador1() != null ? tablero.getJugador1().getNombre() : "esperando";
        String player2 = tablero.getJugador2() != null ? tablero.getJugador2().getNombre() : "essperando";
        String player3 = tablero.getJugador3() != null ? tablero.getJugador3().getNombre() : "esperando";
        String player4 = tablero.getJugador4() != null ? tablero.getJugador4().getNombre() : "esperando";

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
        
        return "IdTablero: " + tablero.getIdTablero()+ " Estado "+ tablero.getEstado();
    }

}
