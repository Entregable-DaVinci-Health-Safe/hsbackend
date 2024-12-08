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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Instituciones_Salud")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class InstitucionSalud implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9033777372996996742L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "itc_id")
	private Long id;
	
	@Column(name = "itc_nombre")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="itc_hta_id", referencedColumnName = "hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "instituciones_profesionales",
	joinColumns = {@JoinColumn(name = "ipf_itc_id")},
	inverseJoinColumns = {@JoinColumn(name = "ipf_pfl_id")})
	@NotAudited
	@OrderBy("nombre")
	private List<Profesional> profesionales;
	
	@OneToOne(targetEntity = DireccionInstitucionSalud.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval  = true)
	@JoinColumn(name = "itc_dir_id", referencedColumnName="dir_id", nullable = false)
	@NotAudited
	private DireccionInstitucionSalud direccion;
	
	@OneToMany(mappedBy="institucionSalud", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<ContactoInstitucionSalud> contactos;
	
	@Column(name = "itc_activo")
	@NotAudited
	private Boolean activo;
	
	public void addContacto(ContactoInstitucionSalud contacto) {
		if(this.contactos == null) contactos = new ArrayList<ContactoInstitucionSalud>();
		contactos.add(contacto);
	}
	
	public void updateContacto(ContactoInstitucionSalud contacto) {
		contactos.removeIf(ctc -> ctc.getId() == contacto.getId());
		addContacto(contacto);
	}
	
	public void removeContacto(Contacto contacto) {
		contactos.remove(contacto);
	}
	
	public void addProfesional(Profesional profesional) {
		if(this.profesionales == null) new ArrayList<Profesional>();
		profesionales.add(profesional);
	}
	
	public void removeProfesional(Profesional profesional) {
		if(this.profesionales == null) new ArrayList<Profesional>();
		profesionales.remove(profesional);
	}
	
	public Boolean tieneProfesional(Profesional profesional) {
		return this.profesionales.contains(profesional);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstitucionSalud other = (InstitucionSalud) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	

}
