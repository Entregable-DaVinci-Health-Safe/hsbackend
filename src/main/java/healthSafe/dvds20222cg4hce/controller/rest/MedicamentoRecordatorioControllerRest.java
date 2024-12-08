package healthSafe.dvds20222cg4hce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.medicamento.MedicamentoRecordatorioRequest;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoRecordatorioResponse;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoRecordatorio;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.medicamento.MedicamentoRecordatorioService;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class MedicamentoRecordatorioControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private MedicamentoRecordatorioService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@PostMapping(path = "/medicamentoRecordatorio")
	public ResponseEntity<Object> createMedicamentoRecordatorio(@RequestBody MedicamentoRecordatorioRequest request){
		MedicamentoRecordatorio recordatorio = null;
		MedicamentoRecordatorioResponse response = null;
		
		try { recordatorio = mapper.map(request, MedicamentoRecordatorio.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {recordatorio = service.save(recordatorio);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		try { response = mapper.map(recordatorio, MedicamentoRecordatorioResponse.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
