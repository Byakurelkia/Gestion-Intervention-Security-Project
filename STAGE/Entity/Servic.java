package STAGE.Entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Servic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date_Service;
	private String heure_Debut;
	private String heure_fin;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private Client client; 
	
	@ManyToOne
	private Site site;
	
	
//	protected static void calculerDuree(String hAppel, String hArrive) { //on va l'utiliser sur le friont pour effectuer le calcul sur la facrture
//		String output = null;
//        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        Date d1, d2;
//       try {
//           d1 = format.parse(hAppel);
//           d2 = format.parse(hArrive);
//           long duration  = d2.getTime() - d1.getTime();
//           long hours = TimeUnit.MILLISECONDS.toHours(duration);
//           long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(hours);
//           output = output +  "Temps écoulé entre appel et arrivée: ";
//           if (hours != 0) 
//               output += hours + "h";
//           if (hours != 0 && minutes != 0)
//               output += ":";
//           if (minutes != 0) 
//               output += String.format("%02dm", minutes);
//       } catch (ParseException e) {
//           e.printStackTrace();
//       }
//      System.out.println(output);
//	}
	
	
}
