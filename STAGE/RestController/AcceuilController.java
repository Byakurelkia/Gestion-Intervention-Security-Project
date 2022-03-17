package STAGE.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import STAGE.Service.ClientServiceImpl;
import STAGE.Service.ServiceClass_Service_Impl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/acceuil")
@RequiredArgsConstructor
public class AcceuilController {
	
	private final ClientServiceImpl clientServiceImpl;
	private final ServiceClass_Service_Impl service_Impl;

	@GetMapping
	public String acceuilMsg() {
		String msg = "Bienvenue à 'NOM DE L'ENTREPRISE'! \n Nous comptons aujourd'hui plus de " + clientServiceImpl.nmbClient() + " clients satisfaits et plus de " + service_Impl.nmbService() + " services effectués! \n Faites confiance à l'expérience et voyez la différence! "; 
		return msg;
	}
}
