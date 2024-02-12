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
public class TableroList implements Serializable{
    private Long id;
    private EstadoTablero estado;
    private List<CasillaList> casillas = new ArrayList<>();
}
