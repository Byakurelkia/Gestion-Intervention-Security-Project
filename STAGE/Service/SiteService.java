package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import STAGE.Entity.Site;

public interface SiteService {
	String creerSite(Site site,Long idClient, Authentication authentication);
	String modifierSite(Long id, Site site, Authentication authentication);
	String desactiverSite(Long id, Authentication authentication);
	List<Site> allSite(Authentication auth);
	Site siteById(Long id, Authentication auth);

}
