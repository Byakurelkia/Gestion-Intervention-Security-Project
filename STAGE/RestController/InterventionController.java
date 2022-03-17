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

import STAGE.Entity.Intervention;
import STAGE.Service.ServiceClass_Service_Impl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/intervention")
@RequiredArgsConstructor
public class InterventionController {

	private final ServiceClass_Service_Impl service_Impl;
	
	
	//CREER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PostMapping("creer/{idClient}/{id}")//marche
	public String creerIntervention(@RequestBody Intervention intervention,@PathVariable(value="idClient") Long idClient,@PathVariable(value="id") Long idSite, Authentication authentication) {
		return service_Impl.creerService_Intervention(intervention, idClient, idSite, authentication);
	}
	
	
	//MODIFIER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PutMapping("modifier/{idInterv}/{idClient}/{id}")
	public String modifInterv(@RequestBody Intervention intervention,@PathVariable(value="idInterv")Long idInterv, 
			@PathVariable(value="idClient") Long idClient,@PathVariable(value="id") Long idSite,Authentication authentication) {
		return service_Impl.modifierService_Intervention(idInterv, idSite, idClient, intervention, authentication);
	}
	
	
	//DESACTIVER
	@Secured("[ROLE_ADMIN]")
	@DeleteMapping("desactiver/{id}")
	public String deleteInterv(@PathVariable(value="id")Long idInterv, Authentication authentication) {
		return service_Impl.desactiverIntervention(idInterv, authentication);
	}
	
	//LISTER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@GetMapping("lister")
	public List<Intervention> listAllIntervention(Authentication auth){
		return service_Impl.listAllInterv(auth);
	}
	
	@GetMapping("lister/{id}")
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	public Intervention intrventionById(@PathVariable(value="id")Long id, Authentication auth) {
		return service_Impl.intervById(id, auth);
	}
	
	
	
	
}
