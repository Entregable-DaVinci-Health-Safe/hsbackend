package healthSafe.dvds20222cg4hce.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "usr_id")
@DiscriminatorValue("PROFESIONAL")
@Table(name="usuario_profesionales")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class UsuarioProfesional extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 939935556451374923L;
	
	@Column(name = "usr_pfl_matricula")
	private String matricula;

}
