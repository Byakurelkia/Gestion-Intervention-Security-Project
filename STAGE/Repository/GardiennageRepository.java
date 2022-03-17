package STAGE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import STAGE.Entity.Gardiennage;

public interface GardiennageRepository extends JpaRepository<Gardiennage, Long> {

}
