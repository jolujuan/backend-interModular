package www.intermodular.com.appversion1.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaInfo implements Serializable{
    private Long id_carta;
    private TipoTematica tipoTematica_carta;
    private String pregunta_carta;
    // private List<RespuestaInfo> respuestas_carta;
}
