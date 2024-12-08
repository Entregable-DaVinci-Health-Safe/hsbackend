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

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "ctc_id")
@DiscriminatorValue("USUARIO")
@Table(name="contactos_usuarios")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class ContactoUsuario extends Contacto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 6662821322656505617L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ctc_usr_id", referencedColumnName="usr_id", nullable = true)
	@JsonBackReference
	private Usuario usuario;

}
