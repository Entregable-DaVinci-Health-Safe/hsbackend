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

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "dir_id")
@DiscriminatorValue("USUARIO")
@Table(name="direcciones_usuarios")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class DireccionUsuario extends Direccion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1523527279033609223L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dir_usr_id", referencedColumnName="usr_id", nullable = true)
	@JsonBackReference
	private Usuario usuario;
}
