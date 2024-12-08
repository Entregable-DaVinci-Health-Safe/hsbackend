package healthSafe.dvds20222cg4hce.domain.contacto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="ctc_tipo_entidad")
@Table(name="contactos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Audited
public abstract class Contacto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 9079798339135139714L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ctc_id")
	private Long id;
	
	@Column(name = "ctc_mail_alternativo")
	@Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
	private String mailAlternativo;
	
	@Column(name = "ctc_telefono")
	private String telefono;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(id, other.id) && Objects.equals(mailAlternativo, other.mailAlternativo)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mailAlternativo, telefono);
	}
	
	
}
