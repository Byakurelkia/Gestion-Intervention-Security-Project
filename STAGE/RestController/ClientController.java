package STAGE.RestController;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import STAGE.Entity.Client;
import STAGE.Service.ClientServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")//les urls ayant /client sont réglé pourêtre accédé uniquement par le role admin sur security configuration
@RequiredArgsConstructor
public class ClientController {

	private final ClientServiceImpl clientServiceImpl;
	
	//CREER
	@PostMapping("create")//marche 
	public String creerClient(@RequestBody Client client, Authentication authentication) {
		return clientServiceImpl.creerClient(client, authentication);
	}
	
	
	//MODIFIER
	@PutMapping("modifier/{id}")//marche 
	public String modifClient(@RequestBody Client client, @PathVariable(value="id") Long id, Authentication authentication) {
		return clientServiceImpl.modifierClient(id, client, authentication);
	}
	
	
	//DESACTIVER
	@DeleteMapping("supprimer/{id}")//marche
	public String deleteClient(@PathVariable(value="id")Long idClient, Authentication authentication) {
		return clientServiceImpl.desactiverClient(idClient, authentication);
	}
	
	
	//LISTER TOUT
	@GetMapping("lister")
	public List<Client> listClients(Authentication auth){
		return clientServiceImpl.listClients(auth);
	}
	
	
	//LISTER UN
	@GetMapping("lister/{id}")
	public Client getClientById(@PathVariable(value="id")Long id, Authentication auth) {
		return clientServiceImpl.clientById(id, auth);
	}
	
}
