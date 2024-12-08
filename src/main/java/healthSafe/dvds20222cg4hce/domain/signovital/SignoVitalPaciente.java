package healthSafe.dvds20222cg4hce.domain.signovital;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="signos_vitales_pacientes")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class SignoVitalPaciente implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2207200853961141933L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "svp_id")
	private Long id;
	
	@Column(name = "svp_valor")
	private BigDecimal valor;
	
	@Column(name = "svp_segundo_valor")
	private BigDecimal segundoValor;
	
	@Column(name = "svp_fecha_ingresado")
	private Long fechaIngresado;
	
	@Column(name = "svp_minimo")
	private BigDecimal minimo;
	
	@Column(name = "svp_maximo")
	private BigDecimal maximo;
	
	@Column(name = "svp_segundo_minimo")
	private BigDecimal segundoMinimo;
	
	@Column(name = "svp_segundo_maximo")
	private BigDecimal segundoMaximo;
	
	@Transient
	private ResultadoSignoVital resultado;
	
	@Transient
	private ResultadoSignoVital segundoResultado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="svp_hta_id", referencedColumnName = "hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "svp_svc_id", referencedColumnName="svc_id", nullable = false)
	@NotAudited
	private SignoVitalCustom signoVitalCustom;
	
	@Column(name = "svp_comentario")
	private String comentario;

}
