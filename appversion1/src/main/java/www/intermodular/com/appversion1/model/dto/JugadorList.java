package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorList implements Serializable{
    private Long id;
    private String nombre;
    private int posicion;
    private boolean turnoPerdido;
}
