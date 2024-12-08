package healthSafe.dvds20222cg4hce.domain.autorizacion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "acp_id")
@DiscriminatorValue("PERMISO")
@Table(name="permisos")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class Permiso extends AutorizacionComponent implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4985238115441114859L;
	
	@ManyToMany(mappedBy = "permisos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rol> roles;
	
	@ManyToMany(mappedBy = "permisos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Grupo> grupos;
}
