package STAGE.RestController;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import STAGE.Entity.Ronde;
import STAGE.Service.ServiceClass_Service_Impl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ronde")
@RequiredArgsConstructor
public class RondeController {
	
	private final ServiceClass_Service_Impl service_Impl;

	//CREER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PostMapping("creer/{id}/{idSite}/{idSpe}")//ça marche 
	public String creerRonde(@RequestBody Ronde ronde,@PathVariable(value="id") Long idClient, 
			@PathVariable(value="idSite") Long idSite,@PathVariable(value="idSpe") Long idSpe, Authentication authentication) {
		return service_Impl.creerService_Ronde(ronde, idClient, idSite, idSpe, authentication);
	}
	
	
	//MODIFIER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PutMapping("modifier/{idRonde}/{idClient}/{idSite}/{idSpe}")
	public String modifRonde(@RequestBody Ronde ronde, @PathVariable(value="idRonde") Long idRonde, @PathVariable(value="idClient")Long idClient, 
			@PathVariable(value="idSite")Long idSite,@PathVariable(value="idSpe")Long idSpe, Authentication authentication) {
		return service_Impl.modifierService_Ronde(idRonde, idSite, idClient, idSpe, ronde, authentication);
	}
	
	
	//DESACTIVER
	@Secured("[ROLE_ADMIN]")
	@DeleteMapping("desactiver/{id}")
	public String deleteRonde(@PathVariable(value="id")Long idRonde, Authentication authentication) {
		return service_Impl.desactiverRonde(idRonde, authentication);
	}
	
	//LISTAGE
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@GetMapping("lister")
	public List<Ronde> listAllRonde(Authentication auth){
		return service_Impl.listAllRonde(auth);
	}
	
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@GetMapping("lister/{id}")
	public Ronde rondeById(@PathVariable(value="id")Long id, Authentication auth) {
		return service_Impl.rondeById(id, auth);
	}
	
}
