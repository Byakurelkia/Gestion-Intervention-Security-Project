package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import STAGE.Entity.Gardiennage;
import STAGE.Entity.Intervention;
import STAGE.Entity.Ronde;

public interface ServiceClass_Service {

	//CREER
	String creerService_Intervention(Intervention intervention, Long idClient, Long idSite, Authentication authentication);
	
	String creerService_Ronde(Ronde ronde, Long idClient, Long idSite,Long idSpecialite, Authentication authentication);
	
	String creerService_Gardiennage(Gardiennage gardiennage,Long idClient, Long idSite,Long idSpecialite, Authentication authentication);
	
	//MODIFIER
	String modifierService_Intervention(Long idItervention, Long idSite, Long idClient, Intervention intervention, Authentication authentication);
	
	String modifierService_Ronde(Long idRonde,Long idSite, Long idClient,Long idSpecialite, Ronde ronde, Authentication authentication);
	
	String modifierService_Gardiennage(Long idGardiennage, Long idSite, Long idClient,Long idSpecialite, Gardiennage gardiennage, Authentication authentication);
	
	//SUPPRIMER
	String desactiverIntervention(Long id, Authentication authentication);
	
	String desactiverRonde(Long id, Authentication authentication);
	
	String desactiverGardiennage(Long id, Authentication authentication);
	
	//LISTAGE
	List<Intervention> listAllInterv(Authentication auth);
	Intervention intervById(Long id,Authentication auth);
	
	List<Ronde> listAllRonde(Authentication auth);
	Ronde rondeById(Long id, Authentication auth);
	
	List<Gardiennage> listAllGardiennage(Authentication auth);
	Gardiennage gardById(Long id, Authentication auth);
}
