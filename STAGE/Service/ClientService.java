package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import STAGE.Entity.Client;

public interface ClientService {

	String creerClient(Client client, Authentication authentication);
	String modifierClient(Long idClient, Client client, Authentication authentication);
	String desactiverClient(Long idClient, Authentication authentication);
	List<Client> listClients(Authentication authentication);
	Client clientById(Long id, Authentication authentication);
}
