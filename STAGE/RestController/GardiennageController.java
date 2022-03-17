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

import STAGE.Entity.Gardiennage;
import STAGE.Service.ServiceClass_Service_Impl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gardiennage")
@RequiredArgsConstructor
public class GardiennageController {

	private final ServiceClass_Service_Impl service_Impl;
	
	
	//CREER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PostMapping("creer/{id}/{idSite}/{idSpe}")//Marche
	public String creerGardiennage(@RequestBody Gardiennage gardiennage, @PathVariable(value="id") Long idClient, 
			@PathVariable(value="idSite") Long idSite, @PathVariable(value="idSpe") Long idSpe, Authentication authentication) {
		return service_Impl.creerService_Gardiennage(gardiennage, idClient, idSite, idSpe, authentication);
	}
	
	
	//MODIFIER
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@PutMapping("modifier/{idGard}/{idClient}/{idSite}/{idSpe}")
	public String modifGard(@RequestBody Gardiennage gardiennage, @PathVariable(value="idGard") Long idGard, @PathVariable(value="idClient")Long idClient, 
			@PathVariable(value="idSite")Long idSite,@PathVariable(value="idSpe")Long idSpe, Authentication authentication) {
		return service_Impl.modifierService_Gardiennage(idGard, idSite, idClient, idSpe, gardiennage, authentication);
	}
	
	
	//DESACTIVER
	@Secured("[ROLE_ADMIN]")
	@DeleteMapping("supprimer/{id}")
	public String deleteGard(@PathVariable(value="id")Long idGard, Authentication authentication) {
		return service_Impl.desactiverGardiennage(idGard, authentication);
	}
	
	//LISTAGE
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@GetMapping("lister")
	public List<Gardiennage> listAllGardiennage(Authentication auth){
		return service_Impl.listAllGardiennage(auth);
	}
	
	@Secured({"[ROLE_ADMIN]", "[ROLE_AGENT]"})
	@GetMapping("lister/{id}")
	public Gardiennage gardiennageById(@PathVariable(value="id")Long id, Authentication auth) {
		return service_Impl.gardById(id, auth);
	}
}
