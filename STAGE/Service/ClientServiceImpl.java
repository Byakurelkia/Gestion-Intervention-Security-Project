package STAGE.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import STAGE.Entity.Client;
import STAGE.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	
	private final ClientRepository clientRepository;

	@Override
	public String creerClient(Client client, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]")) {
			client.setAdresse(client.getAdresse());
			client.setCodePostal(client.getCodePostal());
			client.setCommentaire(client.getCommentaire());
			client.setEmail(client.getEmail());
			client.setNom(client.getNom());
			client.setNum_Siret(client.getNum_Siret());
			client.setProspect(client.isProspect());
			client.setSoustraitance(client.isSoustraitance());
			client.setTelephone(client.getTelephone());
			client.setVille(client.getVille());
			client.setTypeClient(client.getTypeClient());
			clientRepository.save(client);
			retour = "Le client a bien été enregistré!";
		}else {
			retour ="Vous n'êtes pas administrateur, vous ne pouvez créer des clients..";
		}
		
		return retour;
	}

	
	@Override
	public String modifierClient(Long idClient, Client client, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		
		if(role.equals("[ROLE_ADMIN]"))
		{
			if(clientRepository.findById(idClient).isEmpty())
				retour = "Le client précisé n'existe pas!";
			else
			{
				Client clientModif = clientRepository.findById(idClient).get();
				clientModif.setAdresse(client.getAdresse());
				clientModif.setCodePostal(client.getCodePostal());
				clientModif.setCommentaire(client.getCommentaire());
				clientModif.setEmail(client.getEmail());
				clientModif.setNom(client.getNom());
				clientModif.setNum_Siret(client.getNum_Siret());
				clientModif.setProspect(client.isProspect());
				clientModif.setSoustraitance(client.isSoustraitance());
				clientModif.setTelephone(client.getTelephone());
				clientModif.setTypeClient(client.getTypeClient());
				clientModif.setVille(client.getVille());
				clientRepository.save(clientModif);
				retour = "Le client a bien été modifié!";
			}
			
		}else {
			retour = "Vous n'êtes pas administrateur, vousne pouvez modifier les clients!";
		}
		return retour;
	}

	@Override
	public String desactiverClient(Long idClient, Authentication authentication) {
		String retour;
		String role = authentication.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")) {
			if(clientRepository.findById(idClient).isEmpty())
				retour ="Le client précisé n'existe pas!";
			else {
				clientRepository.findById(idClient).ifPresent(c->{c.setActif(false);
					});
				retour = "Le client a bien été supprimé!";
			}
		}else {
			retour = "Vous n'êtes pas administrateur, vous ne pouvez effacer des clients!";
		}
		return retour;
	}


	@Override
	public List<Client> listClients(Authentication auth) {
		String role = auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]"))
			return  clientRepository.findAll();
		else
			return null;
	}


	@Override
	public Client clientById(Long id, Authentication authentication) {
		String role = authentication.getAuthorities().toString();
		if(!role.equals("[ROLE_ADMIN]"))
			return null;
		else if(clientRepository.findById(id).isEmpty())
			return null;
		else
			return clientRepository.findById(id).get();
	}
	
	public int nmbClient() {
		return clientRepository.findAll().size();
	}

}
