package STAGE.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nom;
	@Column(nullable = false)
	private String num_Siret;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String telephone;
	@Column(nullable = false)
	private String adresse;
	@Column(nullable = false)
	private String codePostal;
	@Column(nullable = false)
	private String ville;
	@Column(nullable = false)
	private boolean soustraitance;
	@Column(nullable = false)
	private boolean prospect;
	private String commentaire;
	@Enumerated(EnumType.STRING)
	private TypeClient typeClient;
	private boolean isActif;
	
	@OneToMany(mappedBy = "client_site",orphanRemoval = true)
	private List<Site> site;
	
	@OneToMany(mappedBy="client_facture",orphanRemoval = true)
	private List<Facture> facture;
	
	
	
}
