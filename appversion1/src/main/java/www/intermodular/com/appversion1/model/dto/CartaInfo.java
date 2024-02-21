package www.intermodular.com.appversion1.model.dto;

import java.io.Serializable;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaInfo implements Serializable{
    
    private Long id;

    private Integer numero;

    
    private CasillaTipo tipoCasilla;
   
}
