package www.intermodular.com.appversion1.srv;

import www.intermodular.com.appversion1.model.dto.DadoMovimientoInfo;

public interface DadoMovimientoService {

    // Método para obtener un dado de movimiento por su ID
    public DadoMovimientoInfo obtenerDadoPorId(Long id);

    // Método para obtener un número aleatorio entre 1 y 4 (simulando un lanzamiento de dado de movimiento)
    public int lanzarDadoMovimiento();

    // Otros métodos según sea necesario
}
