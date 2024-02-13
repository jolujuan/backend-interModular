package www.intermodular.com.appversion1.model.db;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.model.dto.EstadoTablero;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="tablero")
public class TableroDb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTablero;

    @Enumerated(EnumType.STRING)
    private EstadoTablero estado;

    private String preguntasHechas;

    // Relación con la entidad Jugador
    @OneToOne
    @JoinColumn(name = "jugador1")
    private JugadorDb jugador1;

    @OneToOne
    @JoinColumn(name = "jugador2")
    private JugadorDb jugador2;

    // Relación con la entidad Casilla
    @OneToOne
    @JoinColumn(name = "casillaJugador1")
    private CasillaDb casillaJugador1;

    @OneToOne
    @JoinColumn(name = "casillaJugador2")
    private CasillaDb casillaJugador2;

    private Long turnoJugador;
    
    private Long ganador;

   
}
