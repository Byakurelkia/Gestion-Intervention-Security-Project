package STAGE.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Specialite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String titreSpe;
	private String descriptionSpe;
	private boolean isActif;
	
	@OneToMany(mappedBy = "specialite")
	private List<Utilisateur> utilisateurListe = new ArrayList<>();
	
	@OneToOne
	private Utilisateur utilisateurQuiACree;
	
	@ManyToOne
	private Site site;
	
	
	
	public Specialite(String titre) {
		this.titreSpe = titre;
	}

}
