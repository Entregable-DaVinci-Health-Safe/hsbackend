package healthSafe.dvds20222cg4hce.domain.ubicacion;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "dir_id")
@DiscriminatorValue("INSTITUCION_SALUD")
@Table(name="direcciones_instituciones_salud")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class DireccionInstitucionSalud extends Direccion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3847997429948337003L;
	


}
