package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import STAGE.Entity.Site;
import STAGE.Repository.ClientRepository;
import STAGE.Repository.SiteRepository;
import STAGE.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {
	
	private final SiteRepository siteRepository;
	private final UtilisateurRepository utilisateurRepository;
	private final ClientRepository clientRepository;

	@Override
	public String creerSite(Site site,Long idClient, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(role.equals("[ROLE_ADMIN]"))//ajoute controler si client existe
		{
			site.setAdresse(site.getAdresse());
			site.setCode_postal(site.getCode_postal());
			site.setNom_Site(site.getNom_Site());
			site.setReference(site.getReference());
			site.setClient_site(clientRepository.findById(idClient).get());
			site.setUtilisateur(utilisateurRepository.findByMail(mail));
			site.setVille(site.getVille());
			siteRepository.save(site);
			retour = "Le site a bien été crée!";
		}else
			retour = "Vous n'êtes pas administrateur vous ne pouvez créer des sites..!";
		return retour;
	}

	@Override
	public String modifierSite(Long id, Site site, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		String mail = authentication.getPrincipal().toString();
		
		if(siteRepository.findById(id).isEmpty())
			retour = "Le site précisé n'existe pas!";
		else
		{
			if(role.equals("[ROLE_ADMIN]")) {
				Site siteModif = siteRepository.findById(id).get();
				siteModif.setAdresse(site.getAdresse());
				siteModif.setCode_postal(site.getCode_postal());
				siteModif.setNom_Site(site.getNom_Site());
				siteModif.setReference(site.getReference());
				siteModif.setVille(site.getVille());
				siteModif.setUtilisateur(utilisateurRepository.findByMail(mail));
				siteRepository.save(siteModif);
				retour= "Le site a bien été modifié!";
				
			}else
				retour="Vous n'êtes pas administrateur, vous ne pouvez modifier des sites..";
		}
		return retour;
	}

	@Override
	public String desactiverSite(Long id, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")) {
			siteRepository.findById(id).ifPresent(S->{
				S.setActif(false);
			});
			retour= "Le site a bien été supprimé!";
		}else
			retour = "Vous n'êtes pas administrateur, vous ne pouvez supprimer des sites!";
			
		return retour;
	}

	@Override
	public List<Site> allSite(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]"))
			return siteRepository.findAll();
		else
			return null;
	}

	@Override
	public Site siteById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]") && siteRepository.findById(id).isPresent())
			return siteRepository.findById(id).get();
		else
			return null;
	}

}
