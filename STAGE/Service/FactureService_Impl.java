package STAGE.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import STAGE.Entity.Facture;
import STAGE.Entity.Statut;
import STAGE.Repository.ClientRepository;
import STAGE.Repository.FactureRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FactureService_Impl implements FactureService{
	
	private final FactureRepository factureRepository ;
	private final ClientRepository clientRepository;
	private static int refFacture = 00;

	@Override
	public String creerFacture(Facture facture,Long idClient,String lignes, Authentication authentication) {
		String retour = "debut creer facture";
		String role = authentication.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")) 
		{
			if(clientRepository.findById(idClient).isEmpty())
				retour = "Le client précisé n'existe pas!";
			else {
				facture.setDate_Creation(dateNow());
				facture.setLignes(lignes);
				facture.setClient_facture(clientRepository.findById(idClient).get());
				facture.setReference(referenceMois()+ Integer.toString(refFacture));
				facture.setStatut(Statut.NONENVOYE);
				facture.setValidite(dateRetour());
				factureRepository.save(facture);
				refFacture +=1;
				retour = "La Facture a bien été créé!";
			}
		}else {
			retour = "vous n'êtes pas un administrateur, vous ne pouvez créer des factures!";
		}
		return retour;
	}

	@Override
	public String modifierFacture(Long idFacture, Facture facture, Long idClient,String lignes, Authentication authentication) {
		
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(factureRepository.findById(idFacture).isEmpty() || clientRepository.findById(idClient).isEmpty()) {
				retour ="La facture et/ou le client précisé n'existe pas!";
			}else {
				factureRepository.findById(idFacture).ifPresent(factureAModifier->{
					factureAModifier.setClient_facture(clientRepository.findById(idClient).get());
					factureAModifier.setLignes(facture.getLignes());
					factureAModifier.setDate_Modification(dateNow());
					factureAModifier.setReference(facture.getReference());
					factureAModifier.setStatut(facture.getStatut());
					factureAModifier.setValidite(facture.getValidite());
					factureRepository.save(factureAModifier);
				});
				retour = "La facture a bien été modifié!";
			}
		}else {
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez modifier une facture!";
		}
		return retour;
	}

	@Override
	public String desactiverFacture(Long idFacture, Authentication authentication) {
		String retour ;
		String role = authentication.getAuthorities().toString();
		if(factureRepository.findById(idFacture).isEmpty())
			retour="La facture précisé n'existe pas!";
		else {
			if(role.equals("[ROLE_ADMIN]")) {
				factureRepository.findById(idFacture).ifPresent(f->{
					f.setActif(false);
				});
				retour = "La facture a bien été supprimée!";
			}
			else
				retour="Vous n'êtes pas administrateur, vous ne pouvez supprimer une facture!";
			}
		
		return retour;
	}

	

	@Override
	public Facture getFactureById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]") || role.equals("[ROLE_COMPTABLE]"))
			return factureRepository.findById(id).get();
		else
			return null;
	}

	@Override
	public List<Facture> listFactures(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]") || role.equals("[ROLE_COMPTABLE]"))
			return factureRepository.findAll();
		else
			return null;
	}
	
	
	
	
	
	
	//fonctions à réutiliser à chaque création/modification de facture
	private static String dateRetour() {
		LocalDate now = LocalDate.now();
		int mois;
		int annee;
		
		if(now.getMonthValue()>10) {
			if(now.getMonthValue() == 11) {
				mois = 1;
				annee = now.getYear()+1;
			}else {
				mois = 2;
				annee = now.getYear()+1;
			}
		}else {
			mois = now.getMonthValue()+2;
			annee = now.getYear();
		}
		return now.getDayOfMonth()+"/"+Integer.toString(mois)+"/"+Integer.toString(annee);
	}

	private static String referenceMois() {
		
		LocalDate now = LocalDate.now();
		String ref = "";
		switch(now.getMonthValue()) {
		case 1:
			ref = "JAN ";
			break;
		case 2:
			ref = "FEV ";
			break;
		case 3:
			ref="MAR ";
			break;
		case 4:
			ref = "AVR ";
			break;
		case 5:
			ref= "MAI ";
			break;
		case 6:
			ref="JUN ";
			break;
		case 7:
			ref ="JUL ";
			break;
		case 8:
			ref="AOU ";
			break;
		case 9:
			ref="SEP ";
			break;
		case 10:
			ref="OCT ";
			break;
		case 11:
			ref= "NOV ";
			break;
		case 12:
			ref= "DEC ";
			break;
		}
		return ref;
	}

	private static String dateNow() {
		LocalDate dateNow = LocalDate.now();
		return dateNow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
	}

}
