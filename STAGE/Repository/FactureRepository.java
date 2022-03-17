package STAGE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import STAGE.Entity.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long>{
	
	
}
