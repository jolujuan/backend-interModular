package www.intermodular.com.appversion1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.CasillaTipo;
import www.intermodular.com.appversion1.model.dto.TableroInfo;
import www.intermodular.com.appversion1.repository.TableroRepository;
import www.intermodular.com.appversion1.service.TableroService;
import www.intermodular.com.appversion1.service.mapper.TableroMapper;


@Service
public class TableroServiceImpl implements TableroService{
    private final TableroRepository tableroRepository;

    @Autowired
    public TableroServiceImpl(TableroRepository tableroRepository) {
        this.tableroRepository = tableroRepository;
    }

    @Override
    public CasillaList obtenerCasillaPorPosicion(int posicion) {
        // Aquí puedes implementar la lógica para obtener la casilla del tablero por su posición
        // Por ejemplo, puedes buscar la casilla en la lista de casillas del tablero        
        TableroInfo tablero = TableroMapper.INSTANCE.tableroDbToTableroInfo(tableroRepository.findById(1L).orElse(null));; // Obtener el tablero desde la base de datos

        if (tablero == null || tablero.getCasillas_tablero() == null || tablero.getCasillas_tablero().isEmpty()) {
            // Si el tablero no existe o no tiene casillas, retornamos null
            return null;
        }

        // Suponiendo que las casillas están en una lista ordenada por posición
        List<CasillaList> casillas = tablero.getCasillas_tablero();
        for (CasillaList casilla : casillas) {
            if (casilla.getNumero() == posicion) {
                return casilla;
            }
        }

        // Si no se encuentra ninguna casilla en la posición especificada, retornamos null
        return null;
    }
    
    @Override
    // Método privado para inicializar el tablero
    public void inicializarTablero(TableroInfo tablero) {
        // Implementar lógica para inicializar el tablero, como agregar casillas especiales, etc.
        List<CasillaList> casillas = new ArrayList<>();
        
        // Agregar casillas al tablero
        for (int i = 1; i <= 17; i++) {
            CasillaList casilla = new CasillaList();
            casilla.setNumero(i);
            
            // Establecer el tipo de casilla según su número
            switch (i) {
                case 1:
                    casilla.setTipo(CasillaTipo.SALIDA);
                    break;
                case 3:
                case 9:
                case 15:
                    casilla.setTipo(CasillaTipo.BONIFICACION);
                    break;
                case 5:
                case 11:
                    casilla.setTipo(CasillaTipo.PENALIZACION);
                    break;
                case 7:
                case 13:
                    casilla.setTipo(CasillaTipo.RETROCESO);
                    break;
                case 17:
                    casilla.setTipo(CasillaTipo.LLEGADA);
                    break;
                default:
                    casilla.setTipo(CasillaTipo.NORMAL);
                    break;
            }
            
            casillas.add(casilla);
        }
        
        // Guardar el tablero
        tablero.setCasillas_tablero(casillas);
        // tableroRepository.save(tablero);
    }
}
