package IA;
import java.util.ArrayList;

import IA.MinMax.IAminmaxPartiel;
import Main.*;

// A l'image du principe "Diviser pour Regner"
// Cette IA a pour but de separer le plateau en des plateaux plus petits,
// sur lesquels on peut appliquer l'IA de Montecarlo, dont on sait qui
// donne de tres bons resultats sur les petits tableaux.

// IA tres efficace sur les grands tableaux avec peu de murs 

public class IAdiviserPourRegner extends IA{

	// SEPARATION A CHOISIR EN FONCTION DES DIMENSIONS DU PLATEAU
	int PasI = 7;
	int PasJ = 7;
	int repetitionsI = 5;
	int repetitionsJ = 5;
	
	// INITIALISATION DES COORDONNES DE LA PREMIERE CASE DU SOUS-PLATEAU COURANT
	int plateauCourantI = 0;
	int plateauCourantJ = 0;
	
	public Coup CoupSuivant(ArrayList<Coup> ListeCoups, Plateau P) {
		// Pour pouvoir diviser le plateau en sous plateaux, il faut qu'un trou
		// au moins soit pr�sent sur ces sous plateaux (pour pouvoir jouer)
		// on verifie cela a l'aide de la fonction plateauScindable.
		// Tant que le plateau n'est pas scindable, on utilise une autre IA,
		// par exemple minmax2 qui a le chic de creer rapidement des trous sur
		// l'ensemble du plateau
		if (!plateauScindable(P)){
			IA IAbis = new IAminmaxPartiel();
			return IAbis.CoupSuivant(ListeCoups, P);
		}
		else {
			// Ce bloc construit un nouveau sous plateau qui est le sous plateau courant.
			Case[][] etat = P.getEtat();
			Case[][] etatCourant = new Case[PasI][PasJ];
			IA IAmonte = new IAmontecarlo();
			for (int i = 0; i < PasI ; i++){
				for (int j = 0; j < PasJ ; j++){  
					Case caseCourante = etat[plateauCourantI*PasI+i][plateauCourantJ*PasJ+j];
					if (caseCourante==Case.HorsPlateau) {
						etatCourant[i][j] = Case.HorsPlateau;
					}
					if (caseCourante==Case.Bille) {
						etatCourant[i][j] = Case.Bille;
					}
					if (caseCourante==Case.Trou) {
						etatCourant[i][j] = Case.Trou;
					}
				}
			}
			Plateau plateauCourant = new Plateau(PasI,PasJ,etatCourant);
			ArrayList <Coup> listeCourante = plateauCourant.ListeCoupsPossibles();
			if (!listeCourante.isEmpty()){
				// Si la liste des coups du sous plateau n'est pas vide,
				// on lui applique l'IA de Montecarlo
				Coup CoupATraduire = IAmonte.CoupSuivant(listeCourante, plateauCourant);
				// et on oublie pas de traduire le coup trouve dans les coordonn�es
				// du grand plateau
				Coup rep = new Coup (plateauCourantI*PasI+CoupATraduire.getI(), plateauCourantJ*PasJ+CoupATraduire.getJ(), CoupATraduire.getDirection());
				return rep;
			}
			else {
				// Si la liste des coups est vide, on passe au sous plateau suivant
				// (changement du sous plateau courant)
				if (plateauCourantI<repetitionsI-1){
					plateauCourantI++;
					return this.CoupSuivant(ListeCoups, P);
				}
				else {
					if (plateauCourantJ<repetitionsJ-1){
						plateauCourantJ++;
						plateauCourantI = 0;
						return this.CoupSuivant(ListeCoups, P);
					}
					else {
						// Si le plateau courant etait deja le dernier plateau,
						// mais que la fonction a ete appelee, c'est qu'il reste
						// des coups jouables � la "frontiere" des sous plateaux
						// on termine donc avec l'IA minmax2.
						IA IAbis = new IAminmaxPartiel();
						return IAbis.CoupSuivant(ListeCoups, P);
					}
				}
			}
		}
		
	}
	
	
	boolean plateauScindable(Plateau P){
		boolean rep = true;
		Case[][] etat = P.getEtat();
		for (int I = 0; I<repetitionsI*PasI; I=I+PasI){
			for (int J = 0; J<repetitionsJ*PasJ; J=J+PasJ){
				// I et J sont les coordonnees de la premi�re case du sous tableau
				// a visiter. On visite donc tous les sous plateaux.
				boolean trou = false;
				for (int i = I; i<I+PasI; i++){
					for (int j = J; j<J+PasJ; j++){
						if (etat[i][j] == Case.Trou){
							trou = true;
							// On v�rifie que chaque sous plateau possede 
							// au moins un trou
						}
					}
				}
				if (trou == false){
					rep = false;
				}
			}
		}
		return rep;
	}

}
