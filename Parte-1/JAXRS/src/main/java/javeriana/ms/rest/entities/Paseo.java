package javeriana.ms.rest.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class Paseo {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String nombre;

    @Basic
    private String lugar_salida;

    @Basic
    private String lugar_llegada;
    
    @Basic
    private Date fecha;
}