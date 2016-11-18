package IA;
import java.util.ArrayList;

import Main.Coup;
import Main.Plateau;


public class IAaleatoire extends IA {

	public Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
		Coup rep;
		int nombreAleatoire = (int)(Math.random() * (ListeCoups.size()));
		rep = ListeCoups.get(nombreAleatoire);	
		return rep;
	}
		
}
