package healthSafe.dvds20222cg4hce.domain.calendario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="vacunas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Vacuna implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -8467767824949358498L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "vcn_id")
	private Long id;
	
	@Column(name = "vcn_nombre")
	private String nombre;
	
	@Column(name = "vcn_descripcion")
	private String descripcion;
	
	@Column(name = "vcn_obligatoria")
	private Boolean obligatoria;
	
	@Column(name = "vcn_cantidad_dosis")
	private Integer cantidadDosis;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "vacunas_tipo_calendarios",
	joinColumns = {@JoinColumn(name = "vtc_vcn_id")},
	inverseJoinColumns = {@JoinColumn(name = "vtc_tcl_id")})
	@JsonIgnore
	@NotAudited
	private List<TipoCalendario> tipoCalendarios;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "vacunas_edades",
	joinColumns = {@JoinColumn(name = "vcd_vcn_id")},
	inverseJoinColumns = {@JoinColumn(name = "vcd_ede_id")})
	@JsonIgnore
	@NotAudited
	private List<RangoEdad> rangoEdades;
	
	@OneToMany(mappedBy="vacuna", fetch = FetchType.EAGER, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@JsonIgnore
	@NotAudited
	private List<VacunaUsuario> vacunaUsuario;
	
	public void addCalendarioEdadVacunaLink(VacunaUsuario link) {
		if(this.vacunaUsuario == null) vacunaUsuario = new ArrayList<VacunaUsuario>();
		vacunaUsuario.add(link);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacuna other = (Vacuna) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(obligatoria, other.obligatoria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, nombre, obligatoria);
	}
	
	
	
}
