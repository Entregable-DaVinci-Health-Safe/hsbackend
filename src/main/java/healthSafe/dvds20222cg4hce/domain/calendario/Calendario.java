package healthSafe.dvds20222cg4hce.domain.calendario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="cld_tcl_id")
@Table(name="calendarios")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Audited
public abstract class Calendario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2516597310072790435L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "cld_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cld_hta_id", referencedColumnName="hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "calendarios_edades",
	joinColumns = {@JoinColumn(name = "cle_cld_id")},
	inverseJoinColumns = {@JoinColumn(name = "cle_ede_id")})
	@NotAudited
	private List<RangoEdad> rangoEdades;
	
	@OneToMany(mappedBy="calendario", fetch = FetchType.EAGER, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@JsonIgnore
	@NotAudited
	private List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks;
	
	public void addCalendarioEdadVacunaLink(CalendarioEdadVacunaLink link) {
		if(this.calendarioEdadVacunaLinks == null) calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		calendarioEdadVacunaLinks.add(link);
	}
	
	public void removeCalendarioEdadVacunaLink(CalendarioEdadVacunaLink link) {
		calendarioEdadVacunaLinks.remove(link);
	}
	
	public void addRangoEdad(RangoEdad rangoEdad) {
		if(this.rangoEdades == null) rangoEdades = new ArrayList<RangoEdad>(); 
		rangoEdades.add(rangoEdad);
	}
	
	public void addListRangoEdades(List<RangoEdad> listRangoEdades) {
		if(this.rangoEdades == null) rangoEdades = new ArrayList<RangoEdad>(); 
		rangoEdades.addAll(listRangoEdades);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calendario other = (Calendario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
