package STAGE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import STAGE.Entity.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

}
