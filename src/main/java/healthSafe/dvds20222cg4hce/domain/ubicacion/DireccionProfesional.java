package healthSafe.dvds20222cg4hce.domain.ubicacion;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "dir_id")
@DiscriminatorValue("PROFESIONAL")
@Table(name="direcciones_profesionales")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class DireccionProfesional extends Direccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7236541175367321462L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dir_pfl_id", referencedColumnName = "pfl_id", nullable = true)
	@JsonBackReference
	private Profesional profesional;

}
