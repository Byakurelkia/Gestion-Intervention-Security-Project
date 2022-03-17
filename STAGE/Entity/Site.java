package STAGE.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Site {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String reference;
	private String nom_Site;
	private String adresse;
	private String code_postal;
	private String ville;
	private boolean isActif;
	
	
	@ManyToOne
	private Client client_site;
	
	@ManyToOne
	private Utilisateur utilisateur;
}
