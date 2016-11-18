package IA.MinMax;
import java.util.ArrayList;

import IA.IA;
import Main.Coup;
import Main.Plateau;

// Cette IA est un minmax classique, mais � chaque noeud de l'arbre des situations,
// l'algo ne viste qu'une partie des noeuds fils (contrairement au minmax classique qui les visite tous)
// et les noeuds visit�s sont ceux qui obtiennent le meilleur r�sultat avec le crit�re d'ordre 0

public class IAminmaxPartiel extends IA {
	
	// A CHOISIR :
	int NombreDeCoupsAvance = 2;
	int NombreDeNoeudsVus = 10;
	CRITERE critereChoisi = new CRITEREcoupsPossibles();
	
	public Coup CoupSuivant (ArrayList<Coup> ListeCoups, Plateau P) {
		Coup rep = ListeCoups.get(0);
		ArrayList<Integer> ListeCriteres = new ArrayList<Integer>();
		ArrayList<Coup> ListeCoupsAVoir = new ArrayList<Coup>();
		for (int i=0; i<NombreDeNoeudsVus; i++){
			//initialisation de la liste : l'important est d'avoir des 0 dans
			//les criteres, pour les coups peu importe, c'est juste pour eviter
			//les NullPointer lors de leur suppression par la suite
			ListeCriteres.add(0);
			ListeCoupsAVoir.add(rep);
		}
		for (int i=0; i<ListeCoups.size() ; i++){
			Coup Coupi = ListeCoups.get(i);
			P.AppliquerCoup(Coupi);
			int calcul = CalculCritere(P, 1);
			//Les deux listes sont triees par ordre croissant de "critere"
			//On regarde donc la valeur du critere de Coupi pour eventuellement 
			//l'integrer dans la liste (a la bonne place dans ce cas)
			boolean cond = true;
			int j = 0;
			while (cond == true){
				if (calcul >= ListeCriteres.get(j)) {
					 if (j==0) { 
						 ListeCriteres.add(0, calcul);
						 ListeCoupsAVoir.add(0, Coupi);
						 ListeCriteres.remove(1);
						 ListeCoupsAVoir.remove(1);
					 }
					 else {
						 ListeCriteres.add(j+1, calcul);
						 ListeCoupsAVoir.add(j+1, Coupi);
						 ListeCriteres.remove(j-1);
						 ListeCoupsAVoir.remove(j-1);
					 }
					 j++;
					 if (j>=NombreDeNoeudsVus) {
						 cond = false;
					 }
				}
				else {
					cond = false;
				}
			}
			P.Retour(Coupi);
		}
		int val = 0;
		for (int i=0; i<ListeCoupsAVoir.size() ; i++){
			Coup Coupi = ListeCoupsAVoir.get(i);
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
			return critereChoisi.critere(P, ListeCoups);
		}
		else {
			if (ListeCoups.isEmpty()){
				return 0;
			}
			else {
				Coup init = ListeCoups.get(0);
				ArrayList<Integer> ListeCriteres = new ArrayList<Integer>();
				ArrayList<Coup> ListeCoupsAVoir = new ArrayList<Coup>();
				for (int i=0; i<NombreDeNoeudsVus; i++){
					//initialisation de la liste : l'important est d'avoir des 0 dans
					//les criteres, pour les coups peu importe, c'est juste pour eviter
					//les NullPointer lors de leur suppression par la suite
					ListeCriteres.add(0);
					ListeCoupsAVoir.add(init);
				}
				for (int i=0; i<ListeCoups.size() ; i++){
					Coup Coupi = ListeCoups.get(i);
					P.AppliquerCoup(Coupi);
					int calcul = CalculCritere(P, 1);
					//Les deux listes sont triees par ordre croissant de "critere"
					//On regarde donc la valeur du critere de Coupi pour eventuellement 
					//l'integrer dans la liste (a la bonne place dans ce cas)
					boolean cond = true;
					int j = 0;
					while (cond == true){
						if (calcul >= ListeCriteres.get(j)) {
							 if (j==0) { 
								 ListeCriteres.add(0, calcul);
								 ListeCoupsAVoir.add(0, Coupi);
								 ListeCriteres.remove(1);
								 ListeCoupsAVoir.remove(1);
							 }
							 else {
								 ListeCriteres.add(j+1, calcul);
								 ListeCoupsAVoir.add(j+1, Coupi);
								 ListeCriteres.remove(j-1);
								 ListeCoupsAVoir.remove(j-1);
							 }
							 j++;
							 if (j>=NombreDeNoeudsVus) {
								 cond = false;
							 }
						}
						else {
							cond = false;
						}
					}
					P.Retour(Coupi);
				}
				int val = 0;
				for (int i=0; i<ListeCoupsAVoir.size() ; i++){
					Coup Coupi = ListeCoupsAVoir.get(i);
					P.AppliquerCoup(Coupi);
					int calcul = CalculCritere(P, n-1);
					//On choisit le coup qui maximise le critere :
					if (calcul >= val) {
						val = calcul;
					}
					P.Retour(Coupi);
				}
				return val;
			}
		}
	}
	
	

}

