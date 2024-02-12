package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorInfo implements Serializable{
    private Long id_jugador;
    private String nombre_jugador;
    private int posicion_jugador;
    private boolean turnoPerdido_jugador;
}
