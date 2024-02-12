package www.intermodular.com.appversion1.service;

import org.springframework.stereotype.Service;


@Service
public interface JugadorService {

    public void marcarTurnoPerdido(Long id);
    public void actualizarPosicion(Long id, int nuevaPosicion);

}
