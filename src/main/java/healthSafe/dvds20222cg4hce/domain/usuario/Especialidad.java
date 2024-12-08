package healthSafe.dvds20222cg4hce.domain.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="especialidades")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Especialidad implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 818327411804573746L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "epc_id")
	private Long id;
	
	@Column(name = "epc_nombre")
	private String nombre;
	
	@ManyToMany(mappedBy = "especialidades", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@NotAudited
	private List<Profesional> profesionales;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidad other = (Especialidad) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
}
