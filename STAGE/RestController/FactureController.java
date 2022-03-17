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

import STAGE.Entity.Facture;
import STAGE.Service.FactureService_Impl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/facture")
@RequiredArgsConstructor
public class FactureController {

	
	private final FactureService_Impl factureService_Impl;
	
	//CREER
	@Secured("[ROLE_ADMIN]")
	@PostMapping("creer")
	public String creerFacture(@RequestBody Facture facture, @PathVariable(value="id") Long idClient, String lignes, Authentication authentication) {
		return factureService_Impl.creerFacture(facture, idClient, lignes, authentication);
	}
	
	
	//MODIFIER
	@Secured("[ROLE_ADMIN]")
	@PutMapping("modifier/{idFact}/{idClient}")
	public String modifierFacture(@PathVariable(value="idFact")Long idFact, @RequestBody Facture facture, 
			@PathVariable(value="idClient")Long idClient, String lignes, Authentication auth) {
		return factureService_Impl.modifierFacture(idFact, facture, idClient, lignes, auth);
	}
	
	
	//DESACTIVER
	@Secured("[ROLE_ADMIN]")
	@DeleteMapping("supprimer/{id}")
	public String supprimerFacture(@PathVariable(value="id")Long idFact, Authentication authentication) {
		return factureService_Impl.desactiverFacture(idFact, authentication);
	}
	
	//LISTER TOUT 
	@Secured({"[ROLE_ADMIN]", "[ROLE_COMPTABLE]"})
	@GetMapping("lister")//admin & comptable
	public List<Facture> listFactures(Authentication auth){
		return factureService_Impl.listFactures(auth);
	}
	
	@Secured({"[ROLE_ADMIN]", "[ROLE_COMPTABLE]"})
	@GetMapping("lister/{id}")//admin & comptable
	public Facture factById(@PathVariable(value="id")Long id, Authentication auth) {
		return factureService_Impl.getFactureById(id, auth);
	}
	
}
