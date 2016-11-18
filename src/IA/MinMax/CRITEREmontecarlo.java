package IA.MinMax;
import java.util.ArrayList;

import IA.IAaleatoire;
import Main.Coup;
import Main.Plateau;

// Critere d'evaluation d'un Plateau base sur la methode de Montecarlo :

public class CRITEREmontecarlo {
	
	//A CHOISIR :
	int nombre_repetition = 100;

	
	int critere (Plateau P) {
		IAaleatoire IAbis = new IAaleatoire();
		int nombreBillesMin = P.getilength() * P.getjlength() ; 
		for (int i=0; i<nombre_repetition; i++) {
			boolean cond = true;
			ArrayList<Coup> ListeCoups = P.ListeCoupsPossibles();
			if (ListeCoups.isEmpty()) { 
				cond = false;
			}
			ArrayList<Coup> ListeCoupsJoue = new ArrayList<Coup>();
			int NombreDeCoupsJoue = 0;
			while (cond==true) {
				Coup coupJoue = IAbis.CoupSuivant(ListeCoups, P);
				P.AppliquerCoup(coupJoue);
				ListeCoupsJoue.add(coupJoue);
				NombreDeCoupsJoue++;
				ListeCoups = P.ListeCoupsPossibles();
				if (ListeCoups.isEmpty()) { 
					cond = false;
				}	
			}
			//  comparer a chaque fois le nb de billes finales 
			//	avec la meilleure simulation r�alis�e jusqu'� pr�sent,
			//	garder le premier coup que s'il y a moins de billes
				int nombreBillesPi = P.nbre_billes();
				if(nombreBillesPi<(nombreBillesMin)){
					nombreBillesMin = nombreBillesPi;
				}
			for(int j = NombreDeCoupsJoue; j>0; j--){
				P.Retour(ListeCoupsJoue.get(j-1));
			}
		}
		
		//  On veut retourner un entier inversement proportionnel au nombre
		// de billes restantes, donc (int)(1/nombreBillesMin); mais
		// on doit multiplier par une constante sinon on aura 0 tout le temps
		// car (1/nombreBillesMin) est compris entre 0 et 1		
		return (int)(P.getilength() * P.getjlength() * (1/nombreBillesMin));	
		
	}
	
}
