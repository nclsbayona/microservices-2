package javeriana.ms.rest.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaseoDTO2 {
    private String lugar_llegada;
    private String lugar_salida;
    private String nombre;
    private Date fecha;    
}
