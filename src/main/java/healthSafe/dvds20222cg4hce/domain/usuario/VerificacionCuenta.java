package healthSafe.dvds20222cg4hce.domain.usuario;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="verificaciones_cuentas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VerificacionCuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8954578368484269133L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "vfc_id")
	private Long id;

	@Column(name = "vfc_codigo")
	private String codigo;
	
	@Column(name = "vfc_fecha_generado")
	private Long fechaGenerado;
	
	@Column(name = "vfc_fecha_validado")
	private Long fechaValidado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vfc_usr_id", referencedColumnName="usr_id", nullable = true)
	@JsonBackReference
	private Usuario usuario;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificacionCuenta other = (VerificacionCuenta) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(fechaGenerado, other.fechaGenerado)
				&& Objects.equals(fechaValidado, other.fechaValidado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, fechaGenerado, fechaValidado);
	}
	
	
}
