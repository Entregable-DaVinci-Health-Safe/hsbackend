package healthSafe.dvds20222cg4hce.domain.historia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="visitas_medicas")

// Configuración de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class VisitaMedica implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4940178227097402073L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "vta_id")
	private Long id;
	
	@Column(name = "vta_fecha")
	private Long fechaVisita;
	
	@Column(name = "vta_virtual")
	private Boolean atencionVirtual;
	
	@Column(name = "vta_indicaciones")
	private String indicaciones;
	
	@OneToMany(mappedBy="visitaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Prescripcion> prescripciones;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vta_hta_id", referencedColumnName="hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@OneToOne(targetEntity = InstitucionSalud.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "vta_itc_id", referencedColumnName = "itc_id", nullable = true)
	@NotAudited
	private InstitucionSalud institucionSalud;
	
	@OneToOne(targetEntity = Profesional.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "vta_pfl_id", referencedColumnName = "pfl_id", nullable = true)
	@NotAudited
	private Profesional profesional;
	
	@OneToOne(targetEntity = Especialidad.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "vta_epc_id", referencedColumnName = "epc_id", nullable = true)
	@NotAudited
	private Especialidad especialidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vta_dgt_id", referencedColumnName="dgt_id", nullable = false)
	@JsonBackReference
	private Diagnostico diagnostico;
	
	@Column(name = "vta_activo")
	@NotAudited
	private Boolean activo;
	
	public void addPrescripcion(Prescripcion prescripcion) {
		if(this.prescripciones == null) this.prescripciones = new ArrayList<Prescripcion>();
		this.prescripciones.add(prescripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisitaMedica other = (VisitaMedica) obj;
		return Objects.equals(atencionVirtual, other.atencionVirtual) && Objects.equals(fechaVisita, other.fechaVisita);
	}

	@Override
	public int hashCode() {
		return Objects.hash(atencionVirtual, fechaVisita);
	}
	
	
}
