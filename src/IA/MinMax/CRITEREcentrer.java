package IA.MinMax;
import java.util.ArrayList;

import Main.Case;
import Main.Coup;
import Main.Plateau;

public class CRITEREcentrer extends CRITERE {

	int critere(Plateau P, ArrayList<Coup> ListeCoups){
		int rep = ListeCoups.size();
		int ilength = P.getilength();
		int jlength = P.getjlength();
		int centrei = (int)(ilength/2);
		int centrej = (int)(jlength/2);
		Case[][] etat = P.getEtat();
		for (int i = 0; i < ilength; i++){
			for (int j = 0; j < jlength; j++){
				if(etat[i][j] == Case.Bille){
				rep = rep - 2*Math.abs(j-centrej)*Math.abs(j-centrej)/(centrej*centrej);
				rep = rep - 2*Math.abs(i-centrei)*Math.abs(i-centrei)/(centrei*centrei);
				}
			}
		}
		return rep;		
	}
	
}
	