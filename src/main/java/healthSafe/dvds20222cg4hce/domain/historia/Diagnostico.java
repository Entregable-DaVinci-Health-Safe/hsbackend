package healthSafe.dvds20222cg4hce.domain.historia;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="diagnosticos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Diagnostico implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 5873902314574043919L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "dgt_id")
	private Long id;
	
	@Column(name = "dgt_nombre")
	private String nombre;
	
	@OneToMany(mappedBy="diagnostico", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@NotAudited
	private List<VisitaMedica> visitasMedicas;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnostico other = (Diagnostico) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
	
}
