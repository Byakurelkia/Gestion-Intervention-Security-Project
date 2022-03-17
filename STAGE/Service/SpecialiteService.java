package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import STAGE.Entity.Specialite;

public interface SpecialiteService {
	
	String creerSpecialite(Specialite specialite, Authentication authentication);
	String modifierSpecialite(Long idSpe, Specialite specialite, Authentication authentication);
	String desactiverSpecialite(Long idSpe, Authentication authentication);
	String affecterSpecialiteAgent(Long idEtudiant, Long idSpecialite, Authentication authentication);
	List<Specialite> allSpe(Authentication auth);
	Specialite speById(Long id, Authentication auth);
	

}
