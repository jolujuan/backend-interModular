package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.model.db.CasillaDb;
import www.intermodular.com.appversion1.model.db.JugadorDb;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableroInfo implements Serializable{
    
    private Long idTablero;


    private EstadoTablero estado;

    private String preguntasHechas;

    // Relación con la entidad Jugador

    private JugadorDb jugador1;

   
    private JugadorDb jugador2;

    // Relación con la entidad Casilla
    
    private CasillaDb casillaJugador1;

    
    private CasillaDb casillaJugador2;

    private Long turnoJugador;
    
    private Long ganador;

}


