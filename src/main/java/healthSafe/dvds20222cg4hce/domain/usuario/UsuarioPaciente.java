package healthSafe.dvds20222cg4hce.domain.usuario;

import java.io.Serializable;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "usr_id")
@DiscriminatorValue("PACIENTE")
@Table(name="usuario_pacientes")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class UsuarioPaciente extends Usuario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2979269972294407837L;

}
