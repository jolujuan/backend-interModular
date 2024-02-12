package www.intermodular.com.appversion1.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.model.dto.Usuario;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="usuarios_estadisticas")
public class EstadisticaUsuarioDb {
    @Id
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "PartidasTotales")
    private Long partidasTotales;

    @Column(name = "PartidasPerdidas")
    private Long partidasPerdidas;

    @Column(name = "PreguntasTotales")
    private Long preguntasTotales;

    @Column(name = "PreguntasAcertadas")
    private Long preguntasAcertadas;

    @Column(name = "PreguntasFalladas")
    private Long preguntasFalladas;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;
}
