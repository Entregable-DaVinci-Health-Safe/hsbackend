package healthSafe.dvds20222cg4hce.domain.autorizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "acp_id")
@DiscriminatorValue("GRUPO")
@Table(name="grupos")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class Grupo extends AutorizacionComponent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4769917446056627785L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "grupos_permisos",
	joinColumns = {@JoinColumn(name = "gpr_grp_id")},
	inverseJoinColumns = {@JoinColumn(name = "gpr_prm_id")})
	@JsonIgnore
	private List<Permiso> permisos;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "roles_grupos",
	joinColumns = {@JoinColumn(name = "rgp_grp_id")},
	inverseJoinColumns = {@JoinColumn(name = "rgp_rol_id")})
	@JsonIgnore
	private List<Rol> roles;
	
	public void addRol(Rol rol) {
		if(this.roles == null) roles = new ArrayList<Rol>();
		roles.add(rol);
	}
	
	public void removeRol(Rol rol) {
		roles.remove(rol);
	}
	
	public void addPermiso(Permiso permiso) {
		if(this.permisos == null) permisos = new ArrayList<Permiso>();
		permisos.add(permiso);
	}
	
	public void removePermiso(Permiso permiso) {
		permisos.remove(permiso);
	}
}
