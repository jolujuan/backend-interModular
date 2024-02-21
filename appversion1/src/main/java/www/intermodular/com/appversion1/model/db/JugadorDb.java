package www.intermodular.com.appversion1.model.db;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="jugador")
public class JugadorDb implements Serializable {
    @Id
    @JoinColumn(name = "idUsuario")
    private Long idUsuario;

    private String nombre;

    private Integer posicion;

    private Boolean turnoPerdido;

    private Integer preguntasFalladas;

    // @OneToOne(mappedBy = "jugador1")
    // private TableroDb tableroJugador1;

    // @OneToOne(mappedBy = "jugador2")
    // private TableroDb tableroJugador2;
    
    // @OneToOne(mappedBy = "jugador3")
    // private TableroDb tableroJugador3;

    // @OneToOne(mappedBy = "jugador4")
    // private TableroDb tableroJugador4;
    
    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioDb usuario;
}


