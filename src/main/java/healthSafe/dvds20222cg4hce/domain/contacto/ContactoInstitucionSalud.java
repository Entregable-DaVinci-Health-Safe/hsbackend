package healthSafe.dvds20222cg4hce.domain.contacto;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "cic_id")
@DiscriminatorValue("INSTITUCION_SALUD")
@Table(name="contactos_instituciones_salud")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class ContactoInstitucionSalud extends Contacto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8180149756268235272L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cic_cis_id", referencedColumnName = "itc_id", nullable = true)
	@JsonBackReference
	private InstitucionSalud institucionSalud;

}
