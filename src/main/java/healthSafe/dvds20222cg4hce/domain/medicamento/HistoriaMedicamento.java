package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="historias_medicamentos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class HistoriaMedicamento implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2907924676702840992L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "htm_id")
	private Long id;
	
	@Column(name = "htm_cantidad")
	private Integer cantidad;
	
	@Column(name = "htm_comentarios")
	private String comentarios;
	
	@Column(name = "htm_presentacion")
	@Enumerated(EnumType.STRING)
	private TipoPresentacion presentacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="htm_hta_id", referencedColumnName="hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="htm_mpt_id", referencedColumnName="mpt_id", nullable = false)
	@JsonBackReference
	@NotAudited
	private MedicamentoProducto medicamento;
	
	@OneToOne(targetEntity = MedicamentoRecordatorio.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval  = true)
	@JoinColumn(name = "htm_mrc_id", referencedColumnName="mrc_id", nullable = true)
	@NotAudited
	private MedicamentoRecordatorio recordatorio;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoriaMedicamento other = (HistoriaMedicamento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
