import java.util.ArrayList;


public class IAaleatoire extends IA {

	Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
		Coup rep;
		int nombreAleatoire = (int)(Math.random() * (ListeCoups.size()));
		rep = ListeCoups.get(nombreAleatoire);	
		return rep;
	}
		
}
