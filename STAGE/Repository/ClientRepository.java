package STAGE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import STAGE.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
