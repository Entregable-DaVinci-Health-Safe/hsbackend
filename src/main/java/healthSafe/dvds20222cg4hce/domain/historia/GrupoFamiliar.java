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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="grupos_familiares")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class GrupoFamiliar implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2527879624484224456L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "gfm_id")
	private Long id;
	
	@Column(name = "gfm_fecha_creado")
	private Long fechaCreado;
	
	@Column(name = "gfm_nombre")
	private String nombre;
	 
	@Column(name = "gfm_codigo")
	private String codigo;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
	@JoinTable(name = "historias_familiares",
	joinColumns = {@JoinColumn(name = "htf_gfm_id")},
	inverseJoinColumns = {@JoinColumn(name = "htf_hta_id")})
	@NotAudited
	private List<HistoriaMedica> historias;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "admins_grupos_familiares",
	joinColumns = {@JoinColumn(name = "adg_gfm_id")},
	inverseJoinColumns = {@JoinColumn(name = "adg_usr_id")})
	@NotAudited
	private List<Usuario> admins;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "usuarios_grupos_familiares",
	joinColumns = {@JoinColumn(name = "ugf_gfm_id")},
	inverseJoinColumns = {@JoinColumn(name = "ugf_usr_id")})
	@NotAudited
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="grupoFamiliar", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<GrupoNotificacion> gruposNotificaciones;
	
	@Column(name = "gfm_activo")
	@NotAudited
	private Boolean activo;
	
	public void addHistoriaMedica(HistoriaMedica historia) {
		if(this.historias == null) historias = new ArrayList<HistoriaMedica>();
		historias.add(historia);
	}
	
	public void removeHistoriaMedica(HistoriaMedica historia) {
		this.historias.remove(historia);
	}
	
	public void removeAllHistoriasMedicas() {
		historias.removeAll(historias);
	}
	
	public void addAdmin(Usuario usuario) {
		if(this.admins == null) this.admins = new ArrayList<Usuario>();
		admins.add(usuario);
	}
	
	public void removeAdmin(Usuario usuario) {
		admins.removeIf(admin -> admin.getId() == usuario.getId());
	}
	
	public void removeAllAdmins() {
		admins.removeAll(admins);
	}
	
	public void addUsuario(Usuario usuario) {
		if(this.usuarios == null) this.usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.removeIf(usr -> usr.getId() == usuario.getId());
	}
	
	public void removeAllUsuarios() {
		usuarios.removeAll(usuarios);
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
		GrupoFamiliar other = (GrupoFamiliar) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(fechaCreado, other.fechaCreado)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, fechaCreado, nombre);
	}

	@Override
	public String toString() {
		return "GrupoFamiliar [id=" + id + ", fechaCreado=" + fechaCreado + ", nombre=" + nombre + ", codigo=" + codigo
				+ "]";
	}
	
	

}
