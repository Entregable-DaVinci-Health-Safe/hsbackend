package healthSafe.dvds20222cg4hce.domain.prescripcion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgEstudio;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "esd_id")
@Table(name="argentina_estudios")

@Data
@NoArgsConstructor
@SuperBuilder
public class ArgEstudio extends Estudio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6755305525975818589L;
	
	@Column(name = "esd_tipo_estudio")
	@Enumerated(EnumType.STRING)
	private TipoArgEstudio tipoEstudio;
	
	/*@JsonIgnore
	@Transient
	private EstudioStrategy estudioStrategy;*/
}
