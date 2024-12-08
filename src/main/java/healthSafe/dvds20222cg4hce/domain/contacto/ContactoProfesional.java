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

import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "ctc_id")
@DiscriminatorValue("PROFESIONAL")
@Table(name="contactos_profesionales")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class ContactoProfesional extends Contacto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3905273733344019671L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ctc_pfl_id", referencedColumnName = "pfl_id", nullable = true)
	@JsonBackReference
	private Profesional profesional;

}
