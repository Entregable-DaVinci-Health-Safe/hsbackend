package healthSafe.dvds20222cg4hce.domain.calendario;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tipo_calendarios")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TipoCalendario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814261239040191421L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "tcl_id")
	private String id;
	
	@ManyToMany(mappedBy = "tipoCalendarios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RangoEdad> rangoEdades;
	
	@ManyToMany(mappedBy = "tipoCalendarios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vacuna> vacunas;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCalendario other = (TipoCalendario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
