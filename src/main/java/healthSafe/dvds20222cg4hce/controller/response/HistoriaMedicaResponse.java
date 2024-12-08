package healthSafe.dvds20222cg4hce.controller.response;

import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.HistoriaMedicamentoResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalCustomResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaMedicaResponse {
	private Long id;
	private List<VisitaMedicaResponse> visitasMedicas;
	private List<ProfesionalResponse> profesionales;
	private List<InstitucionSaludResponse> institucionesSalud;
	private List<CalendarioResponse> calendarios;
	private List<HistoriaMedicamentoResponse> medicamentos;
	private List<SignoVitalCustomResponse> signosVitalesCustoms;
	private List<SignoVitalPacienteResponse> signosVitalesPaciente;
	private List<TurnoResponse> turnos;
	private UsuarioPacienteResponse paciente;
	private List<Long> gruposFamiliaresIds;
}
