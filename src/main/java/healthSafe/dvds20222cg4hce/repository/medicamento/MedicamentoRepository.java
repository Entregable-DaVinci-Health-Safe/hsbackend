package healthSafe.dvds20222cg4hce.repository.medicamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import healthSafe.dvds20222cg4hce.domain.medicamento.Medicamento;

public interface MedicamentoRepository extends JpaRepository <Medicamento, Long>{
	Page<Medicamento> findByNombreIgnoreCaseContaining(Pageable pageable, String nombre);
	List<Medicamento> findByNombreIgnoreCaseContainingOrderByNombreAsc(String nombre);
}
