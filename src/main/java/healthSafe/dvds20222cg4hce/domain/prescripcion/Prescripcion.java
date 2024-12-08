package healthSafe.dvds20222cg4hce.domain.prescripcion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.prescripcion.factory.PrescripcionComponentFactory;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.PaisPrescripcion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="prescripciones")

// Configuración de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Prescripcion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8613546500676011906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pcp_id")
	private Long id;
	
	@Column(name = "pcp_pais_prescripcion")
	@Enumerated(EnumType.STRING)
	private PaisPrescripcion paisPrescripcion;
	
	@JsonIgnore
	@Transient
	private PrescripcionComponentFactory prescripcionComponentFactory;
	
	@OneToMany(mappedBy="prescripcion", cascade = CascadeType.PERSIST,  orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<Receta> recetas;
	
	@OneToMany(mappedBy="prescripcion", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<Estudio> estudios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pcp_vta_id", referencedColumnName="vta_id", nullable = false)
	@JsonBackReference
	private VisitaMedica visitaMedica;
	
	@JsonIgnore
	public Estudio getEstudio() {
		return prescripcionComponentFactory.crearEstudio();
	}
	
	@JsonIgnore
	public Receta getReceta() {
		return prescripcionComponentFactory.crearReceta();
	}
	
	public void addReceta(Receta receta) {
		if(this.recetas == null) this.recetas = new ArrayList<Receta>();
		this.recetas.add(receta);
	}
	
	public void addEstudio(Estudio estudio) {
		if(this.estudios == null) this.estudios = new ArrayList<Estudio>();
		this.estudios.add(estudio);
	}
	
	public void deleteEstudio(Long id) {
		estudios.removeIf(esd -> esd.getId() == id);
	}
	
	public void deleteReceta(Long id) {
		recetas.removeIf(rta -> rta.getId() == id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescripcion other = (Prescripcion) obj;
		return Objects.equals(id, other.id) && paisPrescripcion == other.paisPrescripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, paisPrescripcion);
	}
	
	
}
