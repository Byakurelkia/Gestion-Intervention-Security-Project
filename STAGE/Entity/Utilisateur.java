package STAGE.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {

	public Utilisateur(String nom, String prenom, String mdp, String email, Long phone, Role comptable,//nécessaire pour premier initialisation du dbInit.class
			boolean b) {
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.mail = email;
		this.phoneNumber = phone;
		this.role= comptable;
		this.isActif = b;
	}

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private String avatar;
	@Column(nullable=false)
	private String nom;
	private String prenom;
	@Column(nullable=false)
	private String mdp;
	@Column(nullable=false, unique = true)
	private String mail;
	@Column(nullable=false)
	private Long phoneNumber;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private String adresse;
	private String description;
	private boolean isActif;
	
	@ManyToOne
	private Specialite specialite;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Servic> servic = new ArrayList<>();
	
	@OneToMany(mappedBy="utilisateur")
	private List<Site> site_utilisateur = new ArrayList<>();
	
	
}
