package STAGE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import STAGE.Entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByMail(String mail);
	
}
