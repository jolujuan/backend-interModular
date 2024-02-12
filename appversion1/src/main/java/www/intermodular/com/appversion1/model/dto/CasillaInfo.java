package www.intermodular.com.appversion1.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillaInfo implements Serializable{
    private Long id_casilla;
    private int numero_casilla;
    private CasillaTipo tipo_casilla;
}
