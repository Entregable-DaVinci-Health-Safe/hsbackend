package healthSafe.dvds20222cg4hce.domain.prescripcion;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.strategy.RecetaStrategy;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgReceta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "rta_id")
@Table(name="argentina_recetas")

@Data
@NoArgsConstructor
@SuperBuilder
public class ArgReceta extends Receta implements Serializable{
	
	/**
	 *            
	 */
	private static final long serialVersionUID = 4771833250350869642L;
	
	@Column(name = "rta_tipo_receta")
	@Enumerated(EnumType.STRING)
	private TipoArgReceta tipoReceta;
	
	/*@JsonIgnore
	@Transient
	private RecetaStrategy recetaStrategy;*/

}
