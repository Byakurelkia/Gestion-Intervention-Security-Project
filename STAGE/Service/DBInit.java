package STAGE.Service;

import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import STAGE.Entity.Role;
//import STAGE.Entity.Utilisateur;
//import SEKUR.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {
//
//    private final UtilisateurRepository utilisateurRepository;
//	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
//	utilisateurRepository.save(new Utilisateur("SASA","Ayaya",passwordEncoder.encode("1234"),"@YAHOO.COM",(long) 1234,Role.COMPTABLE,true));
//	utilisateurRepository.save(new Utilisateur("ELIF","elif",passwordEncoder.encode("1234"),"@GMAIL.COM",(long) 1234,Role.ADMIN,true));

	}

}
