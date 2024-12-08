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
@DiscriminatorValue("ROL")
@Table(name="roles")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class Rol extends AutorizacionComponent implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1974460746481377566L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "roles_permisos",
	joinColumns = {@JoinColumn(name = "rpr_rol_id")},
	inverseJoinColumns = {@JoinColumn(name = "rpr_prm_id")})
	@JsonIgnore
	private List<Permiso> permisos;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Grupo> grupos;
	
	public void addPermiso(Permiso permiso) {
		if(this.permisos == null) permisos = new ArrayList<Permiso>();
		permisos.add(permiso);
	}
	
	public void removePermiso(Permiso permiso) {
		permisos.remove(permiso);
	}
}
