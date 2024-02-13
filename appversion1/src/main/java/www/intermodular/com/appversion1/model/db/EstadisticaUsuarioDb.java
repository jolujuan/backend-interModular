package www.intermodular.com.appversion1.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="usuarios_estadisticas")
public class EstadisticaUsuarioDb {
    @Id
    @JoinColumn(name = "idUsuario")
    private Long idUsuario;

    private Long PartidasTotales = 0L;
    private Long PartidasPerdidas = 0L;
    private Long PreguntasTotales = 0L;
    private Long PreguntasAcertadas = 0L;
    private Long PreguntasFalladas = 0L;

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioDb usuario;

    // Constructor, getters y setters
}
