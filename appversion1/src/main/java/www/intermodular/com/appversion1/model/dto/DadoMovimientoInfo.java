package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DadoMovimientoInfo implements Serializable{
    private Long id_dadoMovimiento;
    private int valor_dadoMovimiento;
}
