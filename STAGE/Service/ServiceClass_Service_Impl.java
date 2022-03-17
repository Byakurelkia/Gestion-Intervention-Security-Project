package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import STAGE.Entity.Gardiennage;
import STAGE.Entity.Intervention;
import STAGE.Entity.Ronde;
import STAGE.Repository.ClientRepository;
import STAGE.Repository.GardiennageRepository;
import STAGE.Repository.InterventionRepository;
import STAGE.Repository.RondeRepository;
import STAGE.Repository.SiteRepository;
import STAGE.Repository.SpecialiteRepository;
import STAGE.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServiceClass_Service_Impl implements ServiceClass_Service {
	
	private final InterventionRepository interventionRepository;
	private final SpecialiteRepository specialiteRepository;
	private final GardiennageRepository gardiennageRepository;
	private final RondeRepository rondeRepository;
	private final ClientRepository clientRepository;
	private final UtilisateurRepository utilisateurRepository;
	private final SiteRepository siteRepository;
	
	
	
	//CREATION SERVICE
	@Override
	public String creerService_Intervention(Intervention intervention, Long idClient, Long idSite, Authentication authentication) {//agent & admin
		
		String retour = "debut creer service";
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		if(getClientById(idClient) && getSiteById(idSite)) {
			if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			intervention.setDate_Service(intervention.getDate_Service());
			intervention.setHeure_Appel(intervention.getHeure_Appel());
			intervention.setHeure_Arrivee(intervention.getHeure_Arrivee());
			intervention.setHeure_Debut(intervention.getHeure_Debut());
			intervention.setHeure_Depart(intervention.getHeure_Depart());
			intervention.setHeure_fin(intervention.getHeure_fin());
			intervention.setClient(clientRepository.findById(idClient).get());
			intervention.setUtilisateur(utilisateurRepository.findByMail(mail));
			intervention.setSite(siteRepository.findById(idSite).get());
			interventionRepository.save(intervention);
			retour = "L'intervention a bien été créé";
			}else {
			retour = "vous n'êtes pas administrateur ou un agent, vous ne pouvez créer des interventions!";
			} 
		}else {
		
			retour = "Le client et ou le site précisé n'existe pas!";
		}
		
		
		return retour;
	}

	@Override
	public String creerService_Ronde(Ronde ronde,Long idClient, Long idSite,Long idSpecialite, Authentication authentication) {//agent & admin
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(getClientById(idClient) && getSiteById(idSite)) {
			if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			ronde.setDate_Service(ronde.getDate_Service());
			ronde.setHeure_Debut(ronde.getHeure_Debut());
			ronde.setHeure_fin(ronde.getHeure_fin());
			ronde.setSpecialite(specialiteRepository.findById(idSpecialite).get());
			ronde.setClient(clientRepository.findById(idClient).get());
			ronde.setUtilisateur(utilisateurRepository.findByMail(mail));
			ronde.setSite(siteRepository.findById(idSite).get());
			rondeRepository.save(ronde);
			retour = "La ronde a bien été créé";
			}else {
			retour = "Vous n'êtes pas administrateur ou un agent, vous ne pouvez créer des rondes!";
			} 
		}else {
		
			retour = "Le client et ou le site précisé n'existe pas!";
		}

		return retour;
	}

	@Override
	public String creerService_Gardiennage(Gardiennage gardiennage,Long idClient, Long idSite,Long idSpe, Authentication authentication) {//agent & admin
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(getClientById(idClient) && getSiteById(idSite)) {
			if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			gardiennage.setDate_Service(gardiennage.getDate_Service()); 
			gardiennage.setHeure_Debut(gardiennage.getHeure_Debut());
			gardiennage.setHeure_fin(gardiennage.getHeure_fin());
			gardiennage.setClient(clientRepository.findById(idClient).get());
			gardiennage.setSpecialite(specialiteRepository.findById(idSpe).get());
			gardiennage.setUtilisateur(utilisateurRepository.findByMail(mail));
			gardiennage.setSite(siteRepository.findById(idSite).get());
			gardiennageRepository.save(gardiennage);
			retour = "Le gardiennage a bien été créé";
			}else {
			retour = "Vous n'êtes pas administrateur ou un agent, vous ne pouvez créer des gardiennages!";
			} 
		}else {
			
			retour = "Le client et ou le site précisé n'existe pas!";
		}

		return retour;
	}

	
	
	//MODIFICATION SERVICE
	@Override
	public String modifierService_Intervention(Long idItervention, Long idSite, Long idClient, Intervention intervention, Authentication authentication) {//agent & admin
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			if(getInterventionById(idItervention) && getClientById(idClient)&&getSiteById(idSite))
			{
				Intervention interventionAModifier = interventionRepository.findById(idItervention).get();
				interventionAModifier.setClient(clientRepository.findById(idClient).get());
				interventionAModifier.setUtilisateur(utilisateurRepository.findByMail(mail));
				interventionAModifier.setSite(siteRepository.findById(idSite).get());
				interventionAModifier.setDate_Service(intervention.getDate_Service());
				interventionAModifier.setHeure_Appel(intervention.getHeure_Appel());
				interventionAModifier.setHeure_Arrivee(intervention.getHeure_Arrivee());
				interventionAModifier.setHeure_Debut(intervention.getHeure_Debut());
				interventionAModifier.setHeure_Depart(intervention.getHeure_Depart());
				interventionAModifier.setHeure_fin(intervention.getHeure_fin());
				interventionRepository.save(interventionAModifier);
				retour = "L'intervention a bien été modifié";
			
			}else if(clientRepository.findById(idClient).isEmpty())
				retour = "Le client n'existe pas!";
			else if(siteRepository.findById(idSite).isEmpty())
				retour = "Le site indiqué n'existe pas!";
			else 
				retour = "L'intervention indiqué n'existe pas!";
		}else {
			retour = "Vous n'êtes pas amdinistrateur ou un agent, vous ne pouvez modifier des interventions";
		}
		
		return retour;
	}

	@Override
	public String modifierService_Ronde(Long idRonde,Long idSite, Long idClient,Long idSpecialite, Ronde ronde, Authentication authentication) {//agent & admin
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			if(getRondeById(idRonde) && getClientById(idClient)&&getSiteById(idSite))
			{
				Ronde rondeAModifier = rondeRepository.findById(idRonde).get();
				rondeAModifier.setClient(clientRepository.findById(idClient).get());
				rondeAModifier.setUtilisateur(utilisateurRepository.findByMail(mail));
				rondeAModifier.setSite(siteRepository.findById(idSite).get());
				rondeAModifier.setSpecialite(specialiteRepository.findById(idSpecialite).get());
				rondeAModifier.setDate_Service(ronde.getDate_Service());
				rondeAModifier.setHeure_Debut(ronde.getHeure_Debut());
				rondeAModifier.setHeure_fin(ronde.getHeure_fin());
				rondeRepository.save(rondeAModifier);
				retour = "La ronde a bien été modifié";
			
			}else if(clientRepository.findById(idClient).isEmpty())
				retour = "Le client n'existe pas!";
			else if(siteRepository.findById(idSite).isEmpty())
				retour = "Le site indiqué n'existe pas!";
			else if(specialiteRepository.findById(idSpecialite).isEmpty())
				retour = "La spécialité indiqué n'existe pas!";
			else 
				retour = "La ronde indiqué n'existe pas!";
		}else {
			retour = "Vous n'êtes pas amdinistrateur ou un agent, vous ne pouvez modifier des rondes!";
		}
		return retour;
	}

	@Override
	public String modifierService_Gardiennage(Long idGardiennage, Long idSite, Long idClient,Long idSpecialite, Gardiennage gardiennage, Authentication authentication) {//agent & admin
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]")) {
			if(getGardiennageById(idGardiennage) && getClientById(idClient) && getSiteById(idSite) && getSpecialiteById(idSpecialite))
			{
				Gardiennage gardiennageAModifier = gardiennageRepository.findById(idGardiennage).get();
				gardiennageAModifier.setClient(clientRepository.findById(idClient).get());
				gardiennageAModifier.setUtilisateur(utilisateurRepository.findByMail(mail));
				gardiennageAModifier.setSite(siteRepository.findById(idSite).get());
				gardiennageAModifier.setSpecialite(specialiteRepository.findById(idSpecialite).get());
				gardiennageAModifier.setDate_Service(gardiennage.getDate_Service());
				gardiennageAModifier.setHeure_Debut(gardiennage.getHeure_Debut());
				gardiennageAModifier.setHeure_fin(gardiennage.getHeure_fin());
				gardiennageRepository.save(gardiennageAModifier);
				retour = "Le gardiennage a bien été modifié";
			
			}else if(clientRepository.findById(idClient).isEmpty())
				retour = "Le client n'existe pas!";
			else if(siteRepository.findById(idSite).isEmpty())
				retour = "Le site indiqué n'existe pas!";
			else if(specialiteRepository.findById(idSpecialite).isEmpty())
				retour = "La spécialité indiqué n'existe pas!";
			else 
				retour = "Le gardiennage indiqué n'existe pas!";
		}else {
			retour = "Vous n'êtes pas amdinistrateur ou un agent, vous ne pouvez modifier des gardiennages!";
		}
		return retour;
	}

	
	
	@Override
	public List<Intervention> listAllInterv(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return interventionRepository.findAll();
		else
			return null;
	}

	@Override
	public Intervention intervById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return interventionRepository.findById(id).get();
		else
			return null;
	}

	@Override
	public List<Ronde> listAllRonde(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return rondeRepository.findAll();
		else
			return null;
	}

	@Override
	public Ronde rondeById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return rondeRepository.findById(id).get();
		else
			return null;
	}

	@Override
	public List<Gardiennage> listAllGardiennage(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return gardiennageRepository.findAll();
		else
			return null;
	}

	@Override
	public Gardiennage gardById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")||role.equals("[ROLE_AGENT]"))
			return gardiennageRepository.findById(id).get();
		else
			return null;
	}
	
	
	@Override
	public String desactiverIntervention(Long id, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(getInterventionById(id)){
				interventionRepository.findById(id).ifPresent(I->{
					I.setActif(false);
				});
				retour = "L'intervention a bien été supprimé!";
				}
			else
				retour = "L'intervention indiqué n'existe pas!";
				}
		else 
			retour = "Vous n'êtes pas un administrateur vous ne pouvez supprimer des inteventions!";
		return retour;
		}
	
	
	@Override 
	public String desactiverRonde(Long id, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(getRondeById(id)){
				rondeRepository.findById(id).ifPresent(R->{
					R.setActif(false);
				});
				retour = "La ronde a bien été supprimé!";
				}
			else
				retour = "La ronde indiqué n'existe pas!";
				}
		else 
			retour = "Vous n'êtes pas un administrateur vous ne pouvez supprimer des rondes!";
		return retour;
		}
	
	@Override
	public String desactiverGardiennage(Long id, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(getGardiennageById(id)){
				gardiennageRepository.findById(id).ifPresent(G->{
					G.setActif(false);
				});
				retour = "Le gardiennage a bien été supprimé!";
				}
			else
				retour = "Le gardiennage indiqué n'existe pas!";
				}
		else 
			retour = "Vous n'êtes pas un administrateur vous ne pouvez supprimer des gardiennages!";
		return retour;
		}
	
	
	protected boolean getClientById(Long idClient) {
		if(clientRepository.findById(idClient).isEmpty()) 
			return false;
		else 
			return true;
	}

	protected boolean getSiteById(Long idSite) {
		if(siteRepository.findById(idSite).isEmpty())
			return false;
		else
			return true;
	}
	
	protected boolean getInterventionById(Long idIntevrention) {
		if(interventionRepository.findById(idIntevrention).isEmpty())
			return false;
		else
			return true;
	}
	
	protected boolean getRondeById(Long idRonde) {
		if(rondeRepository.findById(idRonde).isEmpty())
			return false;
		else
			return true;
	}
	
	protected boolean getGardiennageById(Long idGardiennage) {
		if(gardiennageRepository.findById(idGardiennage).isEmpty())
			return false;
		else
			return true;
	}
	
	protected boolean getSpecialiteById(Long idSpe) {
		if(specialiteRepository.findById(idSpe).isEmpty())
			return false;
		else
			return true;
	}
	
	public int nmbService() {
		return interventionRepository.findAll().size() + rondeRepository.findAll().size() + gardiennageRepository.findAll().size();
	}

	
}
