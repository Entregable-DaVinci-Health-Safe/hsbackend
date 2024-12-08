package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medicamentos_recordatorios")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MedicamentoRecordatorio implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2244641940378132986L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "mrc_id")
	private Long id;
	
	@Column(name = "mrc_fecha_inicio")
	private Long fechaInicio;
	
	@Column(name = "mrc_fecha_final")
	private Long fechaFinal;
	
	@Column(name = "mrc_cronico")
	private Boolean esCronico;
	
	@Column(name = "mrc_frecuencia")
	private Integer frecuencia;
	
	@Column(name = "mrc_tipo_frecuencia")
	@Enumerated(EnumType.STRING)
	private TipoFrecuencia tipoFrecuencia;
	
	@Column(name = "mrc_dosis")
	private Integer dosis;
	
	@Column(name = "mrc_reposicion")
	private Integer reposicion;

	@Column(name = "mrc_google_id")
	private String googleId;
	
	@OneToOne(mappedBy = "recordatorio")
	private HistoriaMedicamento historiaMedicamento;
}
