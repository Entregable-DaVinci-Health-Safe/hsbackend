package healthSafe.dvds20222cg4hce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

@Repository
public interface HistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Long>{
	Boolean existsByPacienteId(Long pacienteId);
	Boolean existsByPaciente_Mail(String mail);
	Optional<HistoriaMedica> findByPacienteId(Long pacienteId);
}
