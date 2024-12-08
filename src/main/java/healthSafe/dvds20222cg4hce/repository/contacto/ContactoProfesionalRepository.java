package healthSafe.dvds20222cg4hce.repository.contacto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;

@Repository
public interface ContactoProfesionalRepository extends JpaRepository<ContactoProfesional, Long>{

}
