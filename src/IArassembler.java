import java.util.ArrayList;

//Cette IA a pour but de favoriser les situations dont les billes les
//plus éxcentrées le sont le moins possible, en calculant une valeur de 
//"centrage" correspondant à la "couronne de cases" la plus large sur laquelle
//se trouve des billes, et en cherchant à "éliminer" ces couronnes tour à tour.
//Le développement de cette IA a malheureusement été abandonné face à quelques 
//inefficacités et complications (l’algo n’arrive même pas à se débarrasser des 
//premières couronnes, et son principe est donc vite oublié)

public class IArassembler extends IA {

	Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
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
		
		for (Coup Coupi : ListeCoupsAVoir){
			P.AppliquerCoup(Coupi);
			int vali = P.ListeCoupsPossibles().size() - nbBillesExentrees(P,bestCentrage);
			if (vali >= valMax) {
				rep = Coupi;
				valMax = vali;
			}
			P.Retour(Coupi);
		}
		
		return rep;
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
