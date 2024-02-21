package www.intermodular.com.appversion1.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;

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

    private UsuarioDb usuario;
}
