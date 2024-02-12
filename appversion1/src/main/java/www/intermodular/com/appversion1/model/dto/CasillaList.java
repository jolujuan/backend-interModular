package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CasillaList implements Serializable{
    private Long id;
    private int numero;
    private CasillaTipo tipo;
}
