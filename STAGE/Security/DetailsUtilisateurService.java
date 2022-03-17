package STAGE.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import STAGE.Entity.Utilisateur;
import STAGE.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DetailsUtilisateurService implements UserDetailsService{
	
	private final UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurRepository.findByMail(mail);
		if (utilisateur != null)
			return new DetailsUtilisateur(utilisateur);
		throw new UsernameNotFoundException("Un compte avec le mail renseigné :" + mail + " n'existe pas dans la base de données");
	}

}
