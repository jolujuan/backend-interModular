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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.intermodular.com.appversion1.model.dto.CasillaTipo;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="casillaTipo")
public class CasillaTipoDb implements Serializable{
   
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        @Enumerated(EnumType.STRING)
        private CasillaTipo tipoCasilla;
    }
    

