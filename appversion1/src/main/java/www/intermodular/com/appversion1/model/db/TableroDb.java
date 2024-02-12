package www.intermodular.com.appversion1.model.db;

import java.io.Serializable;
import java.util.ArrayList;
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
public class TableroDb implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "estadoTablero", referencedColumnName = "estado", insertable = false, updatable = false)
    private EstadoTablero estado;

    @OneToMany(mappedBy = "tablero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CasillaDb> casillas = new ArrayList<>();
}
