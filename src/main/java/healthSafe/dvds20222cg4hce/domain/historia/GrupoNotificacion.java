package healthSafe.dvds20222cg4hce.domain.historia;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="grupos_notificaciones")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GrupoNotificacion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -9144511209737988326L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "gnt_id")
	private Long id;
	
	@Column(name = "gnt_fecha")
	private Long fecha;
	
	@Column(name = "gnt_aceptada")
	private Boolean aceptada;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="gnt_usr_id", referencedColumnName="usr_id", nullable = false)
	@JsonBackReference
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="gnt_gfm_id", referencedColumnName="gfm_id", nullable = false)
	@JsonBackReference
	private GrupoFamiliar grupoFamiliar;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoNotificacion other = (GrupoNotificacion) obj;
		return Objects.equals(aceptada, other.aceptada) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aceptada, fecha, id);
	}
	
	

}
