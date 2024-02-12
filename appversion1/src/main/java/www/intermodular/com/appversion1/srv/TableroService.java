package www.intermodular.com.appversion1.srv;

import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.TableroInfo;

public interface TableroService {
    public CasillaList obtenerCasillaPorPosicion(int posicion);
     // Método privado para inicializar el tablero
    public void inicializarTablero(TableroInfo tablero);
}