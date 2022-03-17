package STAGE.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import STAGE.Entity.Utilisateur;
import STAGE.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private final UtilisateurRepository utilisateurRepository;
	private final PasswordEncoder passwordEncoder;
	private final String uploadDirectory = "src/main/resources/static/images/users-avatar";
	private final String url = "images/users-avatar";

	@Override
	public String creerUtilisateur(Utilisateur utilisateur,MultipartFile fileAvatar, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		Utilisateur resultat = utilisateurRepository.findByMail(utilisateur.getMail());
		if(role.equals("[ROLE_ADMIN]"))
		{
			if (resultat == null) {
			utilisateur.setNom(utilisateur.getNom());
			utilisateur.setPrenom(utilisateur.getPrenom());
			utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
			utilisateur.setMail(utilisateur.getMail());
			utilisateur.setPhoneNumber(utilisateur.getPhoneNumber());
			utilisateur.setRole(utilisateur.getRole());
			utilisateur.setSexe(utilisateur.getSexe());
			utilisateur.setAdresse(utilisateur.getAdresse());
			utilisateur.setDescription(utilisateur.getDescription());
			utilisateur.setActif(true);
			utilisateurRepository.save(utilisateur);
			retour = "L'utilisateur " + utilisateur.getNom() + " a bien été crée et sauvegardé dans la base de données!";
				if(fileAvatar != null) {
					String filename = StringUtils.cleanPath(Objects.requireNonNull(fileAvatar.getOriginalFilename()));
		            	try {
		            		String path = uploadDirectory + File.separator + utilisateur.getId();
		            		String url = this.url + File.separator + utilisateur.getId();
		            		saveAvatar(path, filename, fileAvatar);
		            		utilisateur.setAvatar(url + File.separator + filename);
		            		utilisateurRepository.save(utilisateur);
		            		retour = "L'utilisateur et l'avatar ont bien été ajouté dans la base de données!";
		            	} catch (Exception e) {
		            		e.printStackTrace();
		            	}
				}
		    }else 
			retour = "L'utilisateur avec le mail renseigné existe dans la base de données! ";
		    
		}else
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez créer un utilisateur!";
		return retour;
	}


	@Override
	public String desactiverUtilisateur(Long idUtilisateur, Authentication authentication) {
		String retour = "debut desactiver user";
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			if(utilisateurRepository.findById(idUtilisateur).isPresent()) {
				Utilisateur userADesactiver = utilisateurRepository.findById(idUtilisateur).get();
				userADesactiver.setActif(false);
				utilisateurRepository.save(userADesactiver);
				retour = "Utilisateur désactivé avec succès!";
			}else {
				retour = "L'utilisateur avec l'id saisi n'existe pas dans la base de données!";
			}
		}else {
			retour = "Vous n'êtes pas un administrateur, vous ne pouvez désactiver un utilisateur!";
		}
		
		return retour;
	}


	@Override
	public String modifierUtilisateur(Long id, Utilisateur utilisateur,MultipartFile fileAvatar, Authentication authentication) {
		String retour ="debut modif User";
		String role = authentication.getAuthorities().toString();
		Utilisateur userModif = utilisateurRepository.findById(id).get();
		
		if(role.equals("[ROLE_ADMIN]")|| authentication.getPrincipal().toString().equals(userModif.getMail())) {
			if(utilisateurRepository.findById(id).isEmpty()) {
				retour = "L'utilisateur saisi n'existe pas dans la base de données!";
			}else {
				utilisateurRepository.findById(id).ifPresent(userAModifier ->{
					userAModifier.setMail(utilisateur.getMail());
					userAModifier.setAdresse(utilisateur.getMail());
					userAModifier.setDescription(utilisateur.getDescription());
					userAModifier.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
					userAModifier.setNom(utilisateur.getNom());
					userAModifier.setPhoneNumber(utilisateur.getPhoneNumber());
					userAModifier.setPrenom(utilisateur.getPrenom());
					userAModifier.setRole(utilisateur.getRole());
					userAModifier.setSexe(utilisateur.getSexe());
					userAModifier.setSpecialite(utilisateur.getSpecialite());
					utilisateurRepository.save(userAModifier);
				});
				retour = " L'utilisateur a été modifié avec succès!";
				if(fileAvatar != null) {
					String filename = StringUtils.cleanPath(Objects.requireNonNull(fileAvatar.getOriginalFilename()));
		            	try {
		            		String path = uploadDirectory + File.separator + utilisateur.getId();
		            		String url = this.url + File.separator + utilisateur.getId();
		            		saveAvatar(path, filename, fileAvatar);
		            		utilisateur.setAvatar(url + File.separator + filename);
		            		utilisateurRepository.save(utilisateur);
		            		retour = "L'avatar et l'utilisateur ont bien été modifié avec succès!";
		            	} catch (Exception e) {
		            		e.printStackTrace();
		            	}
				}
			}
		}else {
			retour = "Vous n'êtes pas administrateur ou bien ce n'est pas votre profil, vous ne pouvez modifier l'utilisateur!";
		}
		
		return retour;
	}


	@Override
	public void saveAvatar(String directory, String fileName, MultipartFile file) throws IOException {
		Path uploadDirectory = Paths.get(directory);
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }

        InputStream inputStream = file.getInputStream();
        Path absolutePath = uploadDirectory.resolve(fileName);
        Files.copy(inputStream, absolutePath, StandardCopyOption.REPLACE_EXISTING);
    }


	@Override
	public List<Utilisateur> allUsers(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]"))
			return utilisateurRepository.findAll();
		else
			return null;
	}


	@Override
	public Utilisateur userByID(Long id, Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]") && utilisateurRepository.findById(id).isPresent())
			return utilisateurRepository.findById(id).get();
		else
			return null;
	}

}
