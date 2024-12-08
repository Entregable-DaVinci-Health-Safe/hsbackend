package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medicamentos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Medicamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -678574938113471170L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "mdc_id")
	private Long id;
	
	@Column(name = "mdc_nombre")
	private String nombre;
	
	@OneToMany(mappedBy="medicamento", cascade = CascadeType.PERSIST,  orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<MedicamentoProducto> productos;
	
	public void addProducto(MedicamentoProducto producto) {
		if(productos == null) this.productos = new ArrayList<MedicamentoProducto>();
		this.productos.add(producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
	
	
}
