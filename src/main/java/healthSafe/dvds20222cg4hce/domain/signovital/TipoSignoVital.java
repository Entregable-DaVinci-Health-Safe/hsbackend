package healthSafe.dvds20222cg4hce.domain.signovital;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tipos_signos_vitales")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TipoSignoVital implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4643812985624475638L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "tsv_id")
	private Long id;
	
	@Column(name = "tsv_nombre")
	private String nombre;
	
	@Column(name = "tsv_medida")
	private String medida;
	
	@Column(name = "tsv_cantidad_valores")
	private Integer cantidadValores;
}
