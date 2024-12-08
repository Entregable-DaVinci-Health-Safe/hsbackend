package healthSafe.dvds20222cg4hce.domain.historia;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="historias_medicas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class HistoriaMedica implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2099418597333972740L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "hta_id")
	private Long id;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<VisitaMedica> visitasMedicas;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<Profesional> profesionales;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<InstitucionSalud> institucionesSalud;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<Calendario> calendarios;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<HistoriaMedicamento> historiaMedicamentos;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<SignoVitalPaciente> signosVitalesPaciente;
	
	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<SignoVitalCustom> signosVitalesCustoms;

	@OneToMany(mappedBy="historiaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<Turno> turnos;
	
	@JsonIgnore
	@OneToOne(targetEntity = UsuarioPaciente.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval  = true)
	@JoinColumn(name = "hta_usr_id", referencedColumnName="usr_id", nullable = false)
	@NotAudited
	private UsuarioPaciente paciente;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "historias", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotAudited
	private List<GrupoFamiliar> gruposFamiliares;
	
	public String getNombreCompletoPaciente() {
		return paciente.getApellido() + " " + paciente.getNombre();
	}
	
	public void addVisitaMedica(VisitaMedica visitaMedica) {
		if(this.visitasMedicas == null) this.visitasMedicas = new ArrayList<VisitaMedica>();
		this.visitasMedicas.add(visitaMedica);
	}
	
	public void addProfesional(Profesional profesional) {
		if(this.profesionales == null) this.profesionales = new ArrayList<Profesional>();
		this.profesionales.add(profesional);
	}
	
	public void addInstitucionSalud(InstitucionSalud centroSalud) {
		if(this.institucionesSalud == null) this.institucionesSalud = new ArrayList<InstitucionSalud>();
		this.institucionesSalud.add(centroSalud);
	}
	
	public void addCalendario(Calendario calendario) {
		if(this.calendarios == null) this.calendarios = new ArrayList<Calendario>();
		this.calendarios.add(calendario);
	}
	
	public void addHistoriaMedicamento(HistoriaMedicamento historiaMedicamento) {
		if(historiaMedicamentos == null) this.historiaMedicamentos = new ArrayList<HistoriaMedicamento>();
		historiaMedicamentos.add(historiaMedicamento);
	}
	
	public void removeHistoriaMedicamento(HistoriaMedicamento historiaMedicamento) {
		historiaMedicamentos.remove(historiaMedicamento);
	}
	
	public void addGrupoFamiliar(GrupoFamiliar grupoFamiliar) {
		if(this.gruposFamiliares == null) this.gruposFamiliares = new ArrayList<GrupoFamiliar>();
		this.gruposFamiliares.add(grupoFamiliar);
	}
	
	public void addSignoVitalPaciente(SignoVitalPaciente signoVitalPaciente) {
		if(this.signosVitalesPaciente == null) this.signosVitalesPaciente = new ArrayList<SignoVitalPaciente>();
		this.signosVitalesPaciente.add(signoVitalPaciente);
	}
	
	public void addSignoVitalCustom(SignoVitalCustom signoVitalCustom) {
		if(this.signosVitalesCustoms == null) this.signosVitalesCustoms = new ArrayList<SignoVitalCustom>();
		this.signosVitalesCustoms.add(signoVitalCustom);
	}

	public void addTurno(Turno turno) {
		if(this.turnos == null) this.turnos = new ArrayList<Turno>();
		this.turnos.add(turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoriaMedica other = (HistoriaMedica) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "HistoriaMedica [id=" + id + "]";
	}
	
	
	
}
