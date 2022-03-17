package STAGE.Service;


import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import STAGE.Entity.Specialite;
import STAGE.Entity.Utilisateur;
import STAGE.Repository.SpecialiteRepository;
import STAGE.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialiteServiceImpl implements SpecialiteService
{
	private final SpecialiteRepository specialiteRepository;
	private final UtilisateurRepository utilisateurRepository;
	
	@Override
	public String creerSpecialite(Specialite specialite, Authentication authentication) {
	String retour = "début";
		String role= authentication.getAuthorities().toString();
		String userMail = authentication.getPrincipal().toString();
		Utilisateur resultat = utilisateurRepository.findByMail(userMail);
		if(resultat == null){
			retour = "Vous n'êtes pas enregistré sur la base de données, vous ne pouvez créer une spécialité!";
		}else {
			if(role.equals("[ROLE_ADMIN]"))
			{
				specialite.setTitreSpe(specialite.getTitreSpe());
				specialite.setDescriptionSpe(specialite.getDescriptionSpe());
				specialite.setUtilisateurQuiACree(resultat);
				specialiteRepository.save(specialite);
				retour = "La spécialité a bien été crée!";
			}else {
				retour = "Vous n'êtes pas un administrateur, vous ne pouvez créer de spécialité!";
			}
		}
		
		return retour;
	}

	@Override
	public String modifierSpecialite(Long idSpe, Specialite specialite, Authentication authentication) {
		String retour = "debutModif";
		String role = authentication.getAuthorities().toString();
		
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(specialiteRepository.findById(idSpe).isEmpty()) {
				retour= "La spécialité précisé n'existe pas! Veuillez sélectionner une spécialité avec un id valide..";
			}else {
				specialiteRepository.findById(idSpe).ifPresent( speAModifier ->{
					speAModifier.setTitreSpe(specialite.getTitreSpe());
					speAModifier.setDescriptionSpe(specialite.getDescriptionSpe());
					specialiteRepository.save(speAModifier);
				});
				retour = "La spécialité a bien été modifié avec succès!";
			}
		}else {
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez modifier une spécialité!";
		}
		
		
		return retour;
	}

	@Override
	public String desactiverSpecialite(Long idSpe, Authentication authentication) {
		String retour = "debutSupprim";
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(specialiteRepository.findById(idSpe).isEmpty()) {
				retour = "La spécialité indiqué n'existe pas!";
			}else {
				specialiteRepository.findById(idSpe).ifPresent(Spe->{
					Spe.setActif(false);
				});
				retour = "La spécialité a bien été supprimé de la base de données!";
			}
		}else {
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez modifier une spécialité!";
		}
		
		return retour;
	}

	@Override
	public String affecterSpecialiteAgent(Long idUtilisateur, Long idSpecialite, Authentication authentication) {
		String retour = "debut affecter spé";
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(utilisateurRepository.findById(idUtilisateur).isPresent() && specialiteRepository.findById(idSpecialite).isPresent()) {
				Utilisateur userSpeAModifier = utilisateurRepository.findById(idUtilisateur).get();
				userSpeAModifier.setSpecialite(specialiteRepository.findById(idSpecialite).get());
				utilisateurRepository.save(userSpeAModifier);
				retour = "L'affectation de spécialité a bien été effectué!";
			}else {
				retour="Une erreur est survenue! Veuillez controler que la spécialité où l'utilisateur existe dans la base de données...";
			}
		}else {
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez affecter une spécialité!";
		}
		return retour;
	}

	@Override
	public List<Specialite> allSpe(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]"))
			return specialiteRepository.findAll();
		else
			return null;
	}

	@Override
	public Specialite speById(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]") && specialiteRepository.findById(id).isPresent())
			return specialiteRepository.findById(id).get();
		else
			return null;
	}

}
