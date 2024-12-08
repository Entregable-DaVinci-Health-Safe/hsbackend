package healthSafe.dvds20222cg4hce.domain.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="usr_tipo_usuario")
@Table(name="usuarios")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Audited
public abstract class Usuario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1648877219127593978L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "usr_id")
	private Long id;
	
	@Column(name = "usr_img_perfil")
	private String imgPerfil;
	
	@Column(name = "usr_documento")
	private Long documento;
	
	@Column(name = "usr_nombre")
	@NotNull(message = "El nombre es obligatorio")
	@NotEmpty(message = "El nombre es obligatorio")
	private String nombre;
	
	@Column(name = "usr_apellido")
	@NotNull(message = "El apellido es obligatorio")
	@NotEmpty(message = "El apellido es obligatorio")
	private String apellido;
	
	@Column(name = "usr_fecha_nacimiento")
	@NotNull(message = "La fecha es obligatoria")
	private Long fechaNacimiento;
	
	@Column(name = "usr_mail")
	@Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
	@NotNull(message = "El mail es obligatorio")
	private String mail;
	
	@Column(name = "usr_password")
	@NotNull(message = "La contraseña es obligatoria")
	private String password;
	
	@Column(name = "usr_genero")
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Column(name = "usr_activo")
	@NotAudited
	private Boolean activo;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "usuarios_autorizaciones_componentes",
	joinColumns = {@JoinColumn(name = "uac_usr_id")},
	inverseJoinColumns = {@JoinColumn(name = "uac_acp_id")})
	@NotAudited
	private List<AutorizacionComponent> autorizacionesComponentes;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<ContactoUsuario> contactos;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<DireccionUsuario> direcciones;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<VerificacionCuenta> verificaciones;
	
	@OneToMany(mappedBy="usuario", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<GrupoNotificacion> gruposNotificaciones;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "admins", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotAudited
	private List<GrupoFamiliar> gruposFamiliaresAdmins;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotAudited
	private List<GrupoFamiliar> gruposFamiliaresUsuarios;
	
	public void addContacto(ContactoUsuario contacto) {
		if(this.contactos == null) contactos = new ArrayList<ContactoUsuario>();
		contactos.add(contacto);
	}
	
	public void updateContacto(ContactoUsuario contacto) {
		contactos.removeIf(ctc -> ctc.getId() == contacto.getId());
		addContacto(contacto);
	}
	
	public void removeContacto(Contacto contacto) {
		contactos.remove(contacto);
	}
	
	public void addDireccion(DireccionUsuario direccion) {
		if(this.direcciones == null) direcciones = new ArrayList<DireccionUsuario>();
		direcciones.add(direccion);
	}
	
	public void updateDireccion(DireccionUsuario direccion) {
		direcciones.removeIf(dir -> dir.getId() == direccion.getId());
		addDireccion(direccion);
	}
	
	public void removeDireccion(Direccion direccion) {
		direcciones.remove(direccion);
	}
	
	public void addVerificacion(VerificacionCuenta verificacion) {
		if(this.verificaciones == null) verificaciones = new ArrayList<VerificacionCuenta>();
		verificaciones.add(verificacion);
	}
	
	public void addAutorizacionComponent(AutorizacionComponent autorizacionComponent) {
		if(this.autorizacionesComponentes == null) autorizacionesComponentes = new ArrayList<AutorizacionComponent>();
		autorizacionesComponentes.add(autorizacionComponent);
	}
	
	public void removeAutorizacionComponent(AutorizacionComponent autorizacionComponent) {
		autorizacionesComponentes.remove(autorizacionComponent);
	}
	
	public void addNotificacion(GrupoNotificacion grupoNotifficacion) {
		if(this.gruposNotificaciones == null) this.gruposNotificaciones = new ArrayList<GrupoNotificacion>();
		gruposNotificaciones.add(grupoNotifficacion);
	}
	
	public void removeNotificacion(GrupoNotificacion grupoNotifficacion) {
		gruposNotificaciones.removeIf(gnt -> gnt.getId() == grupoNotifficacion.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, id, mail, nombre, password);
	}

	
	
	
}
