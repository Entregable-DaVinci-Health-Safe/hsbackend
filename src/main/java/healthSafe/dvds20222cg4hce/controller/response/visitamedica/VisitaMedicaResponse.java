package healthSafe.dvds20222cg4hce.controller.response.visitamedica;

import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.DiagnosticoResponse;
import healthSafe.dvds20222cg4hce.controller.response.InstitucionSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.PrescripcionResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.EspecialidadResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitaMedicaResponse {
	private Long id;
	private String fechaVisita;
	private Boolean atencionVirtual;
	private String indicaciones;
	private InstitucionSaludResponse institucionSalud;
	private ProfesionalResponse profesional;
	private EspecialidadResponse especialidad;
	private DiagnosticoResponse diagnostico;
	private List<PrescripcionResponse> prescripciones;
	private Boolean activo;
}
