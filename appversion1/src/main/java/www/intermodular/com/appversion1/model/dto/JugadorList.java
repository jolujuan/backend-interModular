package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.model.db.TableroDb;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorList implements Serializable{
    
    private Long idUsuario;
    private String nombre;
    private Integer posicion;
    private Boolean turnoPerdido;
    private Integer preguntasFalladas;
    private TableroDb tableroJugador1;
    private TableroDb tableroJugador2;
    private UsuarioDb usuario;
}
