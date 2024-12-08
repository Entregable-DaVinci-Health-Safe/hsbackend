package healthSafe.dvds20222cg4hce.service.grupofamiliar;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.GrupoNotificacionRepository;

@Service
public class GrupoNotificacionServiceImpl implements GrupoNotificacionService{

	private GrupoNotificacionRepository repository;
	
	@Autowired
	public GrupoNotificacionServiceImpl(final GrupoNotificacionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<GrupoNotificacion> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<GrupoNotificacion> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public GrupoNotificacion save(GrupoNotificacion grupoNotificacion) throws BusinessException {
		
		
		grupoNotificacion = GrupoNotificacion.builder()
				.fecha(grupoNotificacion.getFecha())
				.aceptada(grupoNotificacion.getAceptada())
				.grupoFamiliar(grupoNotificacion.getGrupoFamiliar())
				.usuario(grupoNotificacion.getUsuario())
				.build();
				
		return repository.save(grupoNotificacion);
	}

	@Override
	public GrupoNotificacion update(GrupoNotificacion grupoNotificacion) throws BusinessException {
		
		if(grupoNotificacion.getId() != null) {
			return repository.save(grupoNotificacion);
		}
		throw new BusinessException("No se pudo actualizar la notificacion del grupo");
	}

	@Override
	public GrupoNotificacion findById(Long id) throws BusinessException {
		
		Optional<GrupoNotificacion> grupoNotificacionOptional = repository.findById(id);
		if(grupoNotificacionOptional.isPresent()) return grupoNotificacionOptional.get();
		throw new BusinessException("No se pudo encontrar la notificacion del grupo con el id: " + id);
	}
	
	@Override
	public List<GrupoNotificacion> getNotificacionesFromGrupoFamiliarId(Long grupoFamiliarId) {
		
		List<GrupoNotificacion> grupoNotificaciones = repository.findByGrupoFamiliarId(grupoFamiliarId);
		return grupoNotificaciones;
	}

	@Override
	public void delete(GrupoNotificacion grupoNotificacion) {
		
		repository.delete(grupoNotificacion);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

	@Override
	public Boolean existsByUsuarioId(Long usuarioId) throws BusinessException {
		
		return repository.existsByUsuarioId(usuarioId);
	}

}
