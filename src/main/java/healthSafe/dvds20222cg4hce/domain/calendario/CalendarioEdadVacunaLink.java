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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="calendarios_edades_vacunas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class CalendarioEdadVacunaLink implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 820847860727704180L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "cev_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cev_cld_id", referencedColumnName="cld_id", nullable = false)
	@JsonBackReference
	private Calendario calendario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cev_ede_id", referencedColumnName="ede_id", nullable = false)
	@JsonBackReference
	private RangoEdad rangoEdad;
	
	@OneToOne(targetEntity = VacunaUsuario.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval  = true)
	@JoinColumn(name = "cev_vcp_id", referencedColumnName="vcp_id", nullable = false)
	@NotAudited
	private VacunaUsuario vacunaUsuario;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarioEdadVacunaLink other = (CalendarioEdadVacunaLink) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
