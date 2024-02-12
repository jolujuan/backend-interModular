package www.intermodular.com.appversion1.srv;

import org.springframework.stereotype.Service;


@Service
public interface JugadorService {

    public void marcarTurnoPerdido(Long id);
    public void actualizarPosicion(Long id, int nuevaPosicion);

}
