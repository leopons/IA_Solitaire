import java.util.ArrayList;

// L'algo minmax classique : on maximise un critère arbitraire définissant
// les situations vers lesquelles on préfère stratégiquement se diriger
// en parcourant l'arbre des situations possibles jusqu'à un certain degrés de 
// profondeur (ie avec un certain nombre de coups d'avance).

public class IAminmax extends IA {
	
	// A CHOISIR :
	int NombreDeCoupsAvance = 2;
	CRITERE critereChoisi = new CRITEREcoupsPossibles() ;
	
	Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
		Coup rep = ListeCoups.get(0);
		int val = 0;
	
		for (Coup Coupi : ListeCoups){
			P.AppliquerCoup(Coupi);
			int calcul = CalculCritere(P, NombreDeCoupsAvance);
			//On choisit le coup qui maximise le critere :
			if (calcul >= val) {
				val = calcul;
				rep = Coupi;
			}
			P.Retour(Coupi);
		}
		return rep;
	}
	
	int CalculCritere (Plateau P, int n) {
		ArrayList<Coup> ListeCoups = P.ListeCoupsPossibles();
		if (n == 1) {
			return critereChoisi.critere(P,  ListeCoups);

		}
		else {
			int val = 0;
			for (int i=0; i<ListeCoups.size() ; i++){
				Coup Coupi = ListeCoups.get(i);
				P.AppliquerCoup(Coupi);
				int calcul = CalculCritere(P, n-1);
				if (calcul >= val) {
					val = calcul;
				}
				P.Retour(Coupi);
			}
			return val;
		}
		
	}
	
	

}

