package healthSafe.dvds20222cg4hce.domain.calendario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vacunas_aplicadas")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VacunaUsuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7730909590593732000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name =  "vcp_id")
	private Long id;
	
	@Column(name = "vcp_numero_dosis")
	private Integer numeroDosis;
	
	@Column(name = "vcp_aplicada")
	private VacunaAplicada aplicada;
	
	@Column(name = "vcp_fecha")
	private Long fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vcp_vcn_id", referencedColumnName="vcn_id", nullable = false)
	@JsonBackReference
	private Vacuna vacuna;

}
