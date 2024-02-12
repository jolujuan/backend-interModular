package www.intermodular.com.appversion1.model.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaList implements Serializable{
    private Long id;
    private TipoTematica tipoTematica;
    private String pregunta;
    // private List<RespuestaList> respuestas;
}