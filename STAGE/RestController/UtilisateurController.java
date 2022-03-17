package STAGE.RestController;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import STAGE.Entity.Utilisateur;
import STAGE.Service.UtilisateurServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilisateur")
@RequiredArgsConstructor
public class UtilisateurController {

	
	private final UtilisateurServiceImpl utilisateurServiceImpl;
	
	
	//CREER
	@PostMapping(value = "creer", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})//marche bien
	public String creerUser(@RequestPart Utilisateur utilisateur,@RequestPart(value = "avatar", required = false) MultipartFile avatar, Authentication authentication) {
		return utilisateurServiceImpl.creerUtilisateur(utilisateur, avatar, authentication);
	}
	
	
	//MODIFIER
	@PutMapping("modifier/{id}")//marche 
	public String modifUser(@RequestPart Utilisateur utilisateur, @PathVariable(value="id")Long id,
			@RequestPart(value="avatar", required = false) MultipartFile avatar, Authentication authentication) {
		return utilisateurServiceImpl.modifierUtilisateur(id, utilisateur, avatar, authentication);
	}
	
	
	//DESACTIVER
	@DeleteMapping("supprimer/{id}")
	public String deleteUser(@PathVariable(value="id")Long idUser, Authentication authentication) {
		return utilisateurServiceImpl.desactiverUtilisateur(idUser, authentication);
	}
	
	//LISTAGE
	@GetMapping("lister")
	public List<Utilisateur> allUserList(Authentication auth){
		return utilisateurServiceImpl.allUsers(auth);
	}
	
	@GetMapping("lister/{id}")
	public Utilisateur utilById(@PathVariable(value="id")Long id, Authentication auth) {
		return utilisateurServiceImpl.userByID(id, auth);
	}
	
}
