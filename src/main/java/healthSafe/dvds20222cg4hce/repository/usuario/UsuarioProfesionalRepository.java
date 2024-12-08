package healthSafe.dvds20222cg4hce.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;

@Repository
public interface UsuarioProfesionalRepository extends JpaRepository<UsuarioProfesional, Long>{

}
