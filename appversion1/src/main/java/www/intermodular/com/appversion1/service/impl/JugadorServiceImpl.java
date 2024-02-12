package www.intermodular.com.appversion1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.dto.JugadorInfo;
import www.intermodular.com.appversion1.repository.JugadorRepository;
import www.intermodular.com.appversion1.service.JugadorService;
import www.intermodular.com.appversion1.service.mapper.JugadorMapper;


@Service
public class JugadorServiceImpl implements JugadorService{
 
    private final JugadorRepository jugadorRepository;

    @Autowired
    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public void marcarTurnoPerdido(Long id) {
        // Buscar el jugador por su ID en la base de datos
        
        JugadorInfo jugador = JugadorMapper.INSTANCE.JugadoroDbToJugadorInfo(jugadorRepository.findById(id).orElse(null));;
        if (jugador != null) {
            // Marcar el turno perdido del jugador
            jugador.setTurnoPerdido_jugador(true);
        }
    }

    @Override
    public void actualizarPosicion(Long id, int nuevaPosicion) {
        JugadorInfo jugador = JugadorMapper.INSTANCE.JugadoroDbToJugadorInfo(jugadorRepository.findById(id).orElse(null));;
        jugador.setPosicion_jugador(nuevaPosicion);
    }
}
