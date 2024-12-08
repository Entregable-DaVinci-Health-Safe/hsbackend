package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoResponse;
import healthSafe.dvds20222cg4hce.domain.medicamento.Medicamento;
import healthSafe.dvds20222cg4hce.service.medicamento.MedicamentoService;
import healthSafe.dvds20222cg4hce.utils.MedicamentoUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class MedicamentoControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private MedicamentoService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/medicamentos/all")
	public ResponseEntity<List<MedicamentoResponse>> getListMedicamentos(){
		
		List<MedicamentoResponse> medicamentosResponse = new ArrayList<MedicamentoResponse>();
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		try { medicamentos = service.listMedicamentos();}
		catch(Exception e) {e.printStackTrace();}
		
		medicamentosResponse = MedicamentoUtils.getListMedicamentoResponse(medicamentos);
		return new ResponseEntity<>(medicamentosResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/medicamentos/all/{likeNombre}")
	public ResponseEntity<List<MedicamentoResponse>> getListMedicamentos(@PathVariable String likeNombre){
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		List<MedicamentoResponse> medicamentosResponse = new ArrayList<MedicamentoResponse>();
		
		try { medicamentos = service.listMedicamentosLike(likeNombre);}
		catch(Exception e) {e.printStackTrace();}
		
		try {medicamentosResponse = MedicamentoUtils.getListMedicamentoResponse(medicamentos);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(medicamentosResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/medicamentos/all/pages")
	public Page<Medicamento> getPagesMedicamentos(){
		
		return service.pageMedicamentos();
	}
	
	@GetMapping(path = "/medicamentos/all/pages/{pageNumber}")
	public Page<Medicamento> getPagesWithNumberMedicamentos(@PathVariable Integer pageNumber){
		return service.pageWithNumberMedicamentos(pageNumber);
	}
	
	@GetMapping(path = "/medicamentos/{likeNombre}/pages")
	public Page<Medicamento> getPagesLikeMedicamentos(@PathVariable String likeNombre){
		return service.pageContainingMedicamentos(likeNombre);
	}
	
	@GetMapping(path = "/medicamentos/{likeNombre}/pages/{pageNumber}")
	public Page<Medicamento> getPagesWithNumberLikeMedicamentos(@PathVariable Integer pageNumber,
			@PathVariable String likeNombre){
		return service.pageWithNumberContainingMedicamentos(pageNumber, likeNombre);
	}
}
