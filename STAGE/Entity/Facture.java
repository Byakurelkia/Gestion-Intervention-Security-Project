package STAGE.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Facture implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String reference;
	private String date_Creation;
	private String date_Modification;
	private String validite;
	private Statut statut;
	@Column(columnDefinition="TEXT")
	private String lignes;
	private boolean isActif;
	
	@ManyToOne
	private Client client_facture;
	
}
	
	
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class LigneFacture{
	private String designation;
	private double quantite;
	private double prix_unitaire;
		
		
	double prixTTC() {
		return prix_unitaire * quantite;
	}
		
}
	
	
