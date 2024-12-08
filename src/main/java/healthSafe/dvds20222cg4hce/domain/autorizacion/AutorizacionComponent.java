package healthSafe.dvds20222cg4hce.domain.autorizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="acp_tipo_componente")
@Table(name="autorizaciones_componentes")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class AutorizacionComponent implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -7115395897084557857L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "acp_id")
	private Long id;
	
	@Column(name = "acp_codigo")
	private String codigo;
	
	@Column(name = "acp_descripcion")
	private String descripcion;
	
	@Column(name = "acp_activo")
	@NotAudited
	private Boolean activo;
	
	@ManyToMany(mappedBy = "componentes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Autorizacion> autorizaciones;
	
	@ManyToMany(mappedBy = "autorizacionesComponentes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Usuario> usuarios;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorizacionComponent other = (AutorizacionComponent) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion);
	}
	
	
	
	
}	