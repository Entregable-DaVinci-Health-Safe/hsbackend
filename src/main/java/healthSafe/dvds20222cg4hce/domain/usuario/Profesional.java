package healthSafe.dvds20222cg4hce.domain.usuario;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="profesionales")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Profesional implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4772047378392897365L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "pfl_id")
	private Long id;
	
	@Column(name = "pfl_nombre")
	private String nombre;
	
	@Column(name = "pfl_tipo_matricula")
	@Enumerated(EnumType.STRING)
	private TipoMatricula tipoMatricula;
	
	@Column(name = "pfl_matricula")
	private Long matricula;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pfl_hta_id", referencedColumnName = "hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@ManyToMany(mappedBy = "profesionales", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@NotAudited
	private List<InstitucionSalud> institucionesSalud;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "profesional_especialidades",
	joinColumns = {@JoinColumn(name = "pep_pfl_id")},
	inverseJoinColumns = {@JoinColumn(name = "pep_epc_id")})
	@NotAudited
	@OrderBy("nombre")
	private List<Especialidad> especialidades;
	
	@OneToMany(mappedBy = "profesional", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<DireccionProfesional> direcciones;
	
	@OneToMany(mappedBy="profesional", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<ContactoProfesional> contactos;
	
	@Column(name = "pfl_activo")
	@NotAudited
	private Boolean activo;
	
	public void addDireccion(DireccionProfesional direccion) {
		if(this.direcciones == null) new ArrayList<DireccionProfesional>();
		direcciones.add(direccion);
	}
	
	public void updateDireccion(DireccionProfesional direccion) {
		direcciones.removeIf(dir -> dir.getId() == direccion.getId());
		addDireccion(direccion);
	}
	
	public void removeDireccion(Direccion direccion) {
		direcciones.remove(direccion);
	}
	
	public void addContacto(ContactoProfesional contacto) {
		if(this.contactos == null) contactos = new ArrayList<ContactoProfesional>();
		contactos.add(contacto);
	}
	
	public void updateContacto(ContactoProfesional contacto) {
		contactos.removeIf(ctc -> ctc.getId() == contacto.getId());
		addContacto(contacto);
	}
	
	public void removeContacto(Contacto contacto) {
		contactos.remove(contacto);
	}
	
	public void addEspecialidad(Especialidad especialidad) {
		if(this.especialidades == null) new ArrayList<Especialidad>();
		especialidades.add(especialidad);
	}
	
	public void removeEspecialidad(Especialidad especialidad) {
		especialidades.remove(especialidad);
	}
	
	public Boolean tieneEspecialidad(Especialidad especialidad) {
		return this.especialidades.contains(especialidad);
	}
	
	public void addListEspecialidades(List<Especialidad> listEspecialidad) {
		if(this.especialidades == null) new ArrayList<Especialidad>();
		especialidades.addAll(listEspecialidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesional other = (Profesional) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
	
}
