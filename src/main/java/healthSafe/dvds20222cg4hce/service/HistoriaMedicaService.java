package healthSafe.dvds20222cg4hce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface HistoriaMedicaService {
	List<HistoriaMedica> list();
	Page<HistoriaMedica> list(Pageable pageable);
	
	HistoriaMedica save(HistoriaMedica historiaMedica) throws BusinessException;
	
	HistoriaMedica update(HistoriaMedica historiaMedica) throws BusinessException;
	
	HistoriaMedica findById(Long id) throws BusinessException;
	HistoriaMedica findByPacienteId(Long id) throws BusinessException;
	HistoriaMedica findByPacienteMail(String mail) throws BusinessException;
	
	void addVisitaMedica(Long id, VisitaMedica visitaMedica) throws BusinessException;
	void addTurno(Long id, Turno turno) throws BusinessException;
	void addProfesional(Long id, Profesional profesional) throws BusinessException;
	void addCentroSalud(Long id, InstitucionSalud centroSalud) throws BusinessException;
	void addMedicamento(Long id, HistoriaMedicamento historiaMedicamento)throws BusinessException;
	void addSignoVitalCustom(Long id, SignoVitalCustom signoVitalCustom)throws BusinessException;
	void addSignoVitalPaciente(Long id, SignoVitalPaciente signoVitalPaciente)throws BusinessException;
	
	HistoriaMedica createCalendarioPersonalSalud(HistoriaMedica historiaMedica) throws BusinessException;
	List<Calendario> getCalendariosByHistoriaMedicaId(Long historiaMedicaId)throws BusinessException;
	
	
	void delete(HistoriaMedica historiaMedica);
	void delete(Long id);
	
	long count();
}
