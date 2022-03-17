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

import STAGE.Entity.Site;
import STAGE.Service.SiteServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/site")
@RequiredArgsConstructor
public class SiteController {
	
	private final SiteServiceImpl siteServiceImpl;
	
	
	//CREER
	@PostMapping("creer/{id}")//marche
	public String creerSite(@RequestBody Site site,@PathVariable(value="id")Long idClient, Authentication authentication) {
		return siteServiceImpl.creerSite(site,idClient, authentication);
	}
	
	
	//MODIFIER
	@PutMapping("modifier/{id}")
	public String modifSite(@RequestBody Site site, @PathVariable(value="id") Long id, Authentication authentication) {
		return siteServiceImpl.modifierSite(id, site, authentication);
	}
	
	
	//DESACTIVER
	@DeleteMapping("supprimer/{id}")
	public String deleteSite(@PathVariable(value="id")Long idSite, Authentication authentication) {
		return siteServiceImpl.desactiverSite(idSite, authentication);
	}
	
	//LISTAGE
	@GetMapping("lister")
	public List<Site> allSite(Authentication auth){
		return siteServiceImpl.allSite(auth);
	}
	
	@GetMapping("lister/{id}")
	public Site siteById(@PathVariable(value="id")Long id, Authentication auth) {
		return siteServiceImpl.siteById(id, auth);
	}

}
