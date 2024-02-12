package www.intermodular.com.appversion1.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstadisticaUsuarioList implements Serializable{
    private Long idUsuario;

    private Long partidasTotales;

    private Long partidasPerdidas;

    private Long preguntasTotales;

    private Long preguntasAcertadas;

    private Long preguntasFalladas;

    private Usuario usuario;
}
