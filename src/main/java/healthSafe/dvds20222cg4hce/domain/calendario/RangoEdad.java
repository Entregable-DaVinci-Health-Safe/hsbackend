package healthSafe.dvds20222cg4hce.domain.calendario;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="rango_edades")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class RangoEdad implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5554594992693561847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ede_id")
	private Long id;
	
	@Column(name = "ede_nombre")
	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "edades_tipo_calendarios",
	joinColumns = {@JoinColumn(name = "etc_ede_id")},
	inverseJoinColumns = {@JoinColumn(name = "etc_tcl_id")})
	@JsonIgnore
	@NotAudited
	private List<TipoCalendario> tipoCalendarios;
	
	@OneToMany(mappedBy="rangoEdad", fetch = FetchType.EAGER, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@JsonIgnore
	@NotAudited
	private List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks;
	
	@ManyToMany(mappedBy = "rangoEdades", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@NotAudited
	private List<Calendario> calendarios;
	
	@ManyToMany(mappedBy = "rangoEdades", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vacuna> vacunas;
	
	public void addCalendarioEdadVacunaLink(CalendarioEdadVacunaLink link) {
		if(this.calendarioEdadVacunaLinks == null) calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		calendarioEdadVacunaLinks.add(link);
	}

	public void addTipoCalendarios(TipoCalendario tipoCalendario) {
		if(tipoCalendarios == null) tipoCalendarios = new ArrayList<TipoCalendario>();
		tipoCalendarios.add(tipoCalendario);
	}
	
	public void addListTipoCalendarios(List<TipoCalendario> listTipoCalendarios) {
		if(tipoCalendarios == null) tipoCalendarios = new ArrayList<TipoCalendario>();
		tipoCalendarios.addAll(listTipoCalendarios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoEdad other = (RangoEdad) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
}
