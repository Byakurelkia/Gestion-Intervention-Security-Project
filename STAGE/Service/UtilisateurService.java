package STAGE.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import STAGE.Entity.Utilisateur;

public interface UtilisateurService {

	String creerUtilisateur (Utilisateur utilisateur,MultipartFile fileAvatar, Authentication authentication);
	void saveAvatar(String directory, String fileName, MultipartFile file) throws IOException;
	String modifierUtilisateur(Long id, Utilisateur utilisateur,MultipartFile fileAvatar, Authentication authentication);
	String desactiverUtilisateur(Long idUtilisateur, Authentication authentication);
	List<Utilisateur> allUsers(Authentication auth);
	Utilisateur userByID(Long id, Authentication auth);
	
}
