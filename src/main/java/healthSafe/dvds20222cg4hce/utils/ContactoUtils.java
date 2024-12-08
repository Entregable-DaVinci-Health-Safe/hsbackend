package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.ContactoResponse;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;

public final class ContactoUtils {
	private ContactoUtils() {}
	
	public static ContactoUsuario getContactoUsuario(ContactoUsuario contactoRequest) {
		return ContactoUsuario.builder()
				.id(contactoRequest.getId())
				.mailAlternativo(contactoRequest.getMailAlternativo())
				.telefono(contactoRequest.getTelefono())
				.build();
	}
	
	public static ContactoProfesional getContactoProfesional(ContactoProfesional contactoRequest) {
		return ContactoProfesional.builder()
				.id(contactoRequest.getId())
				.mailAlternativo(contactoRequest.getMailAlternativo())
				.telefono(contactoRequest.getTelefono())
				.build();
	}
	
	public static ContactoInstitucionSalud getContactoCentroSalud(ContactoInstitucionSalud contactoRequest) {
		return ContactoInstitucionSalud.builder()
				.id(contactoRequest.getId())
				.mailAlternativo(contactoRequest.getMailAlternativo())
				.telefono(contactoRequest.getTelefono())
				.build();
	}
	
	public static ContactoResponse getContactoResponse(Contacto contactoRequest) {
		return ContactoResponse.builder()
				.id(contactoRequest.getId())
				.mailAlternativo(contactoRequest.getMailAlternativo())
				.telefono(contactoRequest.getTelefono())
				.build();
	}
	
	public static List<ContactoUsuario> getListContactosUsuarios(List<ContactoUsuario> contactosRequest){
		List<ContactoUsuario> contactos = new ArrayList<ContactoUsuario>();
		contactosRequest.stream().forEach(ctc -> contactos.add(getContactoUsuario(ctc)));
		return contactos;
	}
	
	public static List<ContactoProfesional> getListContactosProfesionales(List<ContactoProfesional> contactosRequest){
		List<ContactoProfesional> contactos = new ArrayList<ContactoProfesional>();
		contactosRequest.stream().forEach(ctc -> contactos.add(getContactoProfesional(ctc)));
		return contactos;
	}
	
	public static List<ContactoInstitucionSalud> getListContactosCentrosSalud(List<ContactoInstitucionSalud> contactosRequest){
		List<ContactoInstitucionSalud> contactos = new ArrayList<ContactoInstitucionSalud>();
		contactosRequest.stream().forEach(ctc -> contactos.add(getContactoCentroSalud(ctc)));
		return contactos;
	}
	
	public static List<ContactoResponse> getListContactosUsuariosResponse(
			List<ContactoUsuario> contactosRequest){
		List<ContactoResponse> contactosResponse = new ArrayList<ContactoResponse>();
		contactosRequest.stream().forEach(ctc -> contactosResponse.add(getContactoResponse(ctc)));
		return contactosResponse;
	}
	
	public static List<ContactoResponse> getListContactosProfesionalesResponse(
			List<ContactoProfesional> contactosRequest){
		List<ContactoResponse> contactosResponse = new ArrayList<ContactoResponse>();
		contactosRequest.stream().forEach(ctc -> contactosResponse.add(getContactoResponse(ctc)));
		return contactosResponse;
	}
	
	public static List<ContactoResponse> getListContactosCentrosSaludResponse(
			List<ContactoInstitucionSalud> contactosRequest){
		List<ContactoResponse> contactosResponse = new ArrayList<ContactoResponse>();
		contactosRequest.stream().forEach(ctc -> contactosResponse.add(getContactoResponse(ctc)));
		return contactosResponse;
	}
}
