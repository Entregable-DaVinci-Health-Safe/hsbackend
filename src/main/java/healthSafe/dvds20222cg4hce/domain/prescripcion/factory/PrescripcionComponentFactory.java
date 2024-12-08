package healthSafe.dvds20222cg4hce.domain.prescripcion.factory;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;

public interface PrescripcionComponentFactory {
	Receta crearReceta();
	Estudio crearEstudio();
}
