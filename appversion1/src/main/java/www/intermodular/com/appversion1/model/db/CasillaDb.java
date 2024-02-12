package www.intermodular.com.appversion1.model.db;

import java.io.Serializable;


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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="casilla")
public class CasillaDb implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;

    @OneToOne
    @JoinColumn(name = "tipoCasilla", referencedColumnName = "name", insertable = false, updatable = false)
    private CasillaTipoDb tipo;

    @ManyToOne
    @JoinColumn(name = "tableroId")
    private TableroDb tablero;

}
