package healthSafe.dvds20222cg4hce.domain.prescripcion.factory;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;

public class ArgFactory implements PrescripcionComponentFactory{

	@Override
	public ArgReceta crearReceta() {
		
		return ArgReceta.builder().build();
	}

	@Override
	public ArgEstudio crearEstudio() {
		
		return ArgEstudio.builder().build();
	}

}