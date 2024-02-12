package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableroInfo implements Serializable{
    private Long id_tablero;
    private EstadoTablero estado_tablero;
    private List<CasillaList> casillas_tablero = new ArrayList<>();
}
