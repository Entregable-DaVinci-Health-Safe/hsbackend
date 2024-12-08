package healthSafe.dvds20222cg4hce.domain.ubicacion;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="dir_tipo_entidad")
@Table(name="direcciones")

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Audited
public abstract class Direccion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1038500857758060585L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "dir_id")
	private Long id;
	
	@Column(name = "dir_direccion")
	private String direccion;
	
	@Column(name = "dir_provincia")
	private String provincia;
	
	@Column(name = "dir_localidad")
	private String localidad;
	
	@Column(name = "dir_barrio")
	private String barrio;
	
	@Column(name = "dir_piso")
	private String piso;
	
	@Column(name = "dir_departamento")
	private String departamento;
	
	@Column(name = "dir_referencia")
	private String referencia;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(departamento, other.departamento) && Objects.equals(id, other.id)
				&& Objects.equals(piso, other.piso) && Objects.equals(referencia, other.referencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(departamento, id, piso, referencia);
	}
	
	
	
	
}
