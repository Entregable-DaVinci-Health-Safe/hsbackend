package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;
import healthSafe.dvds20222cg4hce.service.DiagnosticoService;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class DiagnosticoControllerRest extends  HistorialMedicoAppRest{
	
	@Autowired
	private DiagnosticoService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/diagnosticos/all")
	public List<Diagnostico> getListAll(){
		return service.list();
	}
	
	@GetMapping(path = "/diagnosticos/all/{likeNombre}")
	public List<Diagnostico> getListAll(@PathVariable String likeNombre){
		return service.listDiagnosticosLike(likeNombre);
	}
}
