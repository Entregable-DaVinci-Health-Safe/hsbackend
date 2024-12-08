package healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="recetas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Audited
public abstract class Receta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3476381124317710351L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "rta_id")
	private Long id; 
	
	@Column(name = "rta_url")
	private String url;
	
	@Column(name = "rta_descripcion")
	private String descripcion;
	
	@Column(name = "rta_fecha")
	private Long fechaCreado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rta_pcp_id", referencedColumnName="pcp_id", nullable = false)
	@JsonBackReference
	private Prescripcion prescripcion;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		return Objects.equals(id, other.id) && Objects.equals(url, other.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, url);
	}
	
	
}
