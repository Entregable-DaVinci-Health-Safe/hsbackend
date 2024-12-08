package healthSafe.dvds20222cg4hce.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByMailAndPassword(String mail, String password);
	
	public Optional<Usuario> findByMail(String mail);
	
	public Boolean existsByMail(String mail);
	
	public Boolean existsByMailAndActivoFalse(String mail);
}
