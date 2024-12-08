package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medicamentos_productos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MedicamentoProducto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -392462700339378780L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "mpt_id")
	private Long id;
	
	@Column(name = "mpt_nombre")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mpt_mdc_id", referencedColumnName="mdc_id", nullable = false)
	@JsonBackReference
	private Medicamento medicamento;
	
	@OneToMany(mappedBy="medicamento", fetch = FetchType.EAGER, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<HistoriaMedicamento> historiaMedicamentos;
	
	public void addHistoriaMedicamento(HistoriaMedicamento historiaMedicamento) {
		if(historiaMedicamentos == null) this.historiaMedicamentos = new ArrayList<HistoriaMedicamento>();
		historiaMedicamentos.add(historiaMedicamento);
	}
	
	public void removeHistoriaMedicamento(HistoriaMedicamento historiaMedicamento) {
		historiaMedicamentos.remove(historiaMedicamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicamentoProducto other = (MedicamentoProducto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
}
