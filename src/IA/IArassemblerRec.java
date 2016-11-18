package IA;
import java.util.ArrayList;
import Main.*;

public class IArassemblerRec extends IA {

	// A CHOISIR :
	int NombreDeCoupsAvance = 2;

	public Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
		Coup rep = ListeCoups.get(0);
		ArrayList<Coup> ListeCoupsAVoir = new ArrayList<Coup>();
		int bestCentrage = 0;
		for (Coup Coupi : ListeCoups){
			P.AppliquerCoup(Coupi);
			int Centrage = calculCentrage(P);
			if (Centrage > bestCentrage) {
				ListeCoupsAVoir.clear();
				ListeCoupsAVoir.add(Coupi);
				bestCentrage = Centrage;
			}
			if (Centrage == bestCentrage) {
				ListeCoupsAVoir.add(Coupi);
			}
			P.Retour(Coupi);
		}
		int valMax = - (P.getilength()*P.getjlength());
		for (int i=0; i<ListeCoupsAVoir.size() ; i++){
			Coup Coupi = ListeCoupsAVoir.get(i);
			P.AppliquerCoup(Coupi);
			int calcul = CalculCritere(P, NombreDeCoupsAvance);
			//On choisit le coup qui maximise le critere :
			if (calcul >= valMax) {
				valMax = calcul;
				rep = Coupi;
			}
			P.Retour(Coupi);
		}
		return rep;
	}

	int CalculCritere (Plateau P, int n) {
		ArrayList<Coup> ListeCoups = P.ListeCoupsPossibles();
		if (n == 1) {
			return ListeCoups.size();
			/* Le Critere choisi pour decrire une situation est le nombre de coups
			 * possibles dans cette situation : plus il est grand, plus la situation
			 * est favorable car moins elle est "bloquee". */
		}
		else {
			if (ListeCoups.isEmpty()){
				return 0;
			}
			else {
				ArrayList<Coup> ListeCoupsAVoir = new ArrayList<Coup>();
				int bestCentrage = 0;
				for (Coup Coupi : ListeCoups){
					P.AppliquerCoup(Coupi);
					int Centrage = calculCentrage(P);
					if (Centrage > bestCentrage) {
						ListeCoupsAVoir.clear();
						ListeCoupsAVoir.add(Coupi);
						bestCentrage = Centrage;
					}
					if (Centrage == bestCentrage) {
						ListeCoupsAVoir.add(Coupi);
					}
					P.Retour(Coupi);
				}
				int valMax = - (P.getilength()*P.getjlength());
				for (int i=0; i<ListeCoupsAVoir.size() ; i++){
					Coup Coupi = ListeCoupsAVoir.get(i);
					P.AppliquerCoup(Coupi);
					int calcul = CalculCritere(P, n-1);
					//On choisit le coup qui maximise le critere :
					if (calcul >= valMax) {
						valMax = calcul;
					}
					P.Retour(Coupi);
				}
				return valMax;
			}
		}
	}

	int calculCentrage (Plateau P) {

		int rep = 0;
		int ilength = P.getilength();
		int jlength = P.getjlength();
		Case[][] etat = P.getEtat();
		boolean cond = true;
		while (cond == true) {
			for (int i = 0; i < ilength; i++){
				if (etat[i][0+rep]==Case.Bille){
					cond = false;
				}
				if (etat[i][jlength-(rep+1)]==Case.Bille){
					cond = false;
				}
			}
			for (int j = 0; j < jlength; j++){
				if (etat[0+rep][j]==Case.Bille){
					cond = false;
				}
				if (etat[ilength-(rep+1)][j]==Case.Bille){
					cond = false;
				}
			}
			if (cond==true){
				rep++;
			}
		}

		return rep;

	}


	int nbBillesExentrees (Plateau P , int centrage){
		int rep = 0;
		int ilength = P.getilength();
		int jlength = P.getjlength();
		Case[][] etat = P.getEtat();
		for (int i = 0; i < ilength; i++){
			if (etat[i][0+centrage]==Case.Bille){
				rep++;
			}
			if (etat[i][jlength-(centrage+1)]==Case.Bille){
				rep++;
			}
		}
		for (int j = 0; j < jlength; j++){
			if (etat[0+centrage][j]==Case.Bille){
				rep++;
			}
			if (etat[ilength-(centrage+1)][j]==Case.Bille){
				rep++;
			}
		}

		return rep;
	}



}

