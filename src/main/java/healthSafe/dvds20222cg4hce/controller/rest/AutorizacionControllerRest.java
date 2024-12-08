package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.request.autorizacion.AutorizacionComponentsIdsRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.AutorizacionRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.GrupoRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.PermisoRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.RolRequest;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.GrupoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.PermisoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.RolResponse;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Grupo;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Permiso;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Rol;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.autorizacion.AutorizacionComponentService;
import healthSafe.dvds20222cg4hce.utils.AutorizacionUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class AutorizacionControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private AutorizacionComponentService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/permisos/all")
	public List<Permiso> getListPermisosAll(){return service.listPermiso();}
	
	@GetMapping(path = "/roles/all")
	public List<Rol> getListRolesAll(){return service.listRol();}
	
	@GetMapping(path = "/grupos/all")
	public List<Grupo> getListGruposAll(){return service.listGrupo();}
	
	@GetMapping(path = "/autorizaciones/all")
	public List<Autorizacion> getListAutorizacionesAll() {return service.listAutorizacion();}
	
	@GetMapping(path = "/roles")
	public ResponseEntity<Object> getRoles(){
		
		List<Rol> roles = null;
		List<RolResponse> rolesResponse = null;
		
		roles = service.listRol();
		rolesResponse = AutorizacionUtils.getListRolesResponse(roles);
		
		return new ResponseEntity<>(rolesResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/autorizaciones")
	public ResponseEntity<Object> createAutorizacion(@RequestBody AutorizacionRequest autorizacionData){
		Autorizacion autorizacion = null;
		AutorizacionResponse response = null;
		
		try { autorizacion = mapper.map(autorizacionData, Autorizacion.class); }
		catch(Exception e) { e.printStackTrace();}
		
		try { autorizacion = service.save(autorizacion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = mapper.map(autorizacion, AutorizacionResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/grupos")
	public ResponseEntity<Object> createGrupo(@RequestBody GrupoRequest grupoData){
		Grupo grupo = null;
		GrupoResponse response = null;
		
		try { grupo = mapper.map(grupoData, Grupo.class); }
		catch(Exception e) { e.printStackTrace();}
		
		try { grupo = service.save(grupo);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = mapper.map(grupo, GrupoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/roles")
	public ResponseEntity<Object> createRol(@RequestBody RolRequest rolData){
		Rol rol = null;
		RolResponse response = null;
		
		try { rol = mapper.map(rolData, Rol.class); }
		catch(Exception e) { e.printStackTrace();}
		
		try { rol = service.save(rol);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = mapper.map(rol, RolResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/permisos")
	public ResponseEntity<Object> createPermiso(@RequestBody PermisoRequest permisoData){
		Permiso permiso = null;
		PermisoResponse response = null;
		
		try { permiso = mapper.map(permisoData, Permiso.class); }
		catch(Exception e) { e.printStackTrace();}
		
		try { permiso = service.save(permiso);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = mapper.map(permiso, PermisoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/roles/{id}/agregarPermisos")
	public ResponseEntity<Object> addPermisosInRol(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Rol rol = null;
		RolResponse response = null;
		
		try { rol = (Rol) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { rol = service.addPermisos(rol, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(rol, RolResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/roles/{id}/removerPermisos")
	public ResponseEntity<Object> removePermisosInRol(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Rol rol = null;
		RolResponse response = null;
		
		try { rol = (Rol) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { rol = service.removePermisos(rol, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(rol, RolResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/grupos/{id}/agregarPermisos")
	public ResponseEntity<Object> addPermisosInGrupo(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Grupo grupo = null;
		GrupoResponse response = null;
		
		try { grupo = (Grupo) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupo = service.addPermisos(grupo, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(grupo, GrupoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/grupos/{id}/removerPermisos")
	public ResponseEntity<Object> removePermisosInGrupo(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Grupo grupo = null;
		GrupoResponse response = null;
		
		try { grupo = (Grupo) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupo = service.removePermisos(grupo, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(grupo, GrupoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/grupos/{id}/agregarRoles")
	public ResponseEntity<Object> addRolesInGrupo(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Grupo grupo = null;
		GrupoResponse response = null;
		
		try { grupo = (Grupo) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupo = service.addRoles(grupo, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(grupo, GrupoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/grupos/{id}/removerRoles")
	public ResponseEntity<Object> removeRolesInGrupo(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Grupo grupo = null;
		GrupoResponse response = null;
		
		try { grupo = (Grupo) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupo = service.removeRoles(grupo, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(grupo, GrupoResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/autorizaciones/{id}/agregarComponentes")
	public ResponseEntity<Object> addComponentesInAutorizacion(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Autorizacion autorizacion = null;
		AutorizacionResponse response = null;
		
		try { autorizacion = (Autorizacion) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { autorizacion = service.addComponentes(autorizacion, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(autorizacion, AutorizacionResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/autorizaciones/{id}/removerComponentes")
	public ResponseEntity<Object> removeComponentesInAutorizacion(@PathVariable Long id, 
			@RequestBody AutorizacionComponentsIdsRequest componentsIdsData){
		
		Autorizacion autorizacion = null;
		AutorizacionResponse response = null;
		
		try { autorizacion = (Autorizacion) service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { autorizacion = service.removeComponentes(autorizacion, componentsIdsData.getComponentsIds());}
		catch(BusinessException e) {}
		
		try {response = mapper.map(autorizacion, AutorizacionResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
