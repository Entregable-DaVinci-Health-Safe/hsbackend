package healthSafe.dvds20222cg4hce.repository.medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;

public interface HistoriaMedicamentoRepository extends JpaRepository<HistoriaMedicamento, Long>{
	List<HistoriaMedicamento> findByHistoriaMedicaId(Long historiaMedicaId);
}
