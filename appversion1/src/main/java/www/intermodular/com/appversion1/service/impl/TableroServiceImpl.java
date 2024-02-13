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
    @Override
    public String getStartTablero(Long idUsuario) {

        TableroDb tablero = new TableroDb();
        // tablero.setId(idUsuario); // el id del tablero no hace falta 
        tablero.setGanador(0L); // ganador del Game
        tablero.setEstado(EstadoTablero.PAUSADA); // la empezamos pausada pq tendra que unirse un Jugador
        tablero.setPreguntasHechas("");
        tablero.setTurnoJugador(1L);// el turno del jugador

        CasillaDb casilla = casillaRepository.findById(idUsuario).orElse(null);
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
           
        }
        jugador1.setIdUsuario(idUsuario);
        jugador1.setNombre(getNicknameUserTablero(idUsuario)); //creacion del jugador con Id y Tipo de usuario 
        jugadorRepository.save(jugador1);
        tablero.setJugador1(jugador1);

         // Guardar el tablero en la base de datos
         tablero = tableroRepository.save(tablero);

        // Devolver el ID del tablero guardado
            return "IdTablero: " +tablero.getIdTablero() +" Player_1 "+ jugador1.getNombre();
    }

    @Override
    public String getAnotherPlayer(String nickname, String player,Long idTable) {
        
        TableroDb tablero = tableroRepository.findByIdTablero(idTable);
       
        
          // Verificar si el tablero existe
    if (tablero != null) {
       
        // if (tablero.get) {
            
        // }
        
        
        return "IdTablero: " + tablero.getIdTablero();
    } else {
        // Manejar el caso en el que el tablero no exista
        return "No se encontró el tablero con el ID proporcionado.";
    }


       
    }

     

}
