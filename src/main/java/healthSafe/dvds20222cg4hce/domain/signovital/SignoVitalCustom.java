package healthSafe.dvds20222cg4hce.domain.signovital;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="signos_vitales_customs")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class SignoVitalCustom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8092293099115227998L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "svc_id")
	private Long id;
	
	@Column(name = "svc_minimo")
	private BigDecimal minimo;
	
	@Column(name = "svc_maximo")
	private BigDecimal maximo;
	
	@Column(name = "svc_segundo_minimo")
	private BigDecimal segundoMinimo;
	
	@Column(name = "svc_segundo_maximo")
	private BigDecimal segundoMaximo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="svc_hta_id", referencedColumnName = "hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@JsonIgnore
	@OneToOne(targetEntity = TipoSignoVital.class, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY, orphanRemoval  = false)
	@JoinColumn(name = "svc_tsv_id", referencedColumnName="tsv_id", nullable = false)
	@NotAudited
	private TipoSignoVital tipoSignoVital;
	
	@OneToMany(mappedBy="signoVitalCustom", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	@NotAudited
	private List<SignoVitalPaciente> signosVitalesPaciente;
	

}
