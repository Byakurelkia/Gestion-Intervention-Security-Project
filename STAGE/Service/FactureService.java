package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import STAGE.Entity.Facture;

public interface FactureService {
	
	String creerFacture(Facture facture,Long idClient,String lignes, Authentication authentication);
	String modifierFacture(Long idFacture, Facture facture, Long idClient,String lignes,  Authentication authentication);
	String desactiverFacture(Long idFacture, Authentication authentication);
	Facture getFactureById(Long id, Authentication auth);
	List<Facture> listFactures(Authentication auth);

}
