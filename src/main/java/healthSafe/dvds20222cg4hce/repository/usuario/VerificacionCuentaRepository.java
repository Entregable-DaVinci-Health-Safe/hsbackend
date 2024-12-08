package healthSafe.dvds20222cg4hce.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;

@Repository
public interface VerificacionCuentaRepository extends JpaRepository<VerificacionCuenta, Long>{
	Optional<VerificacionCuenta> findByCodigoAndUsuarioMail(String codigo, String usuarioMail);
}
