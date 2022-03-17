package STAGE.RestController;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import STAGE.Entity.Specialite;
import STAGE.Service.SpecialiteServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/specialite")
@RequiredArgsConstructor
public class SpecialiteController {

	
	private final SpecialiteServiceImpl specialiteServiceImpl;
	
	
	//CREER
	@PostMapping("creer")//marche
	public String creerSpe(@RequestBody Specialite specialite, Authentication authentication) {
		return specialiteServiceImpl.creerSpecialite(specialite, authentication);
	}
	
	
	//MODIFIER
	@PutMapping("modifier/{id}")//marche 
	public String modifSpe(@RequestBody Specialite specialite, @PathVariable(value="id") Long id, Authentication authentication) {
		return specialiteServiceImpl.modifierSpecialite(id, specialite, authentication);
	}
	
	//DESACTIVER
	@DeleteMapping("desactiver/{id}")
	public String deleteSpe(@PathVariable(value="id")Long idSpe, Authentication authentication) {
		return specialiteServiceImpl.desactiverSpecialite(idSpe, authentication);
	}
	
	//LISTAGE
	@GetMapping("lister")
	public List<Specialite> getAllSpe(Authentication auth){
		return specialiteServiceImpl.allSpe(auth);
	}
	
	@GetMapping("lister/{id}")
	public Specialite getSpeById(@PathVariable(value="id")Long id, Authentication auth) {
		return specialiteServiceImpl.speById(id, auth);
	}
}
