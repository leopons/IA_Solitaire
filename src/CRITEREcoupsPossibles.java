
import java.util.ArrayList;


public class CRITEREcoupsPossibles extends CRITERE {

	int critere(Plateau P, ArrayList<Coup> ListeCoups){
		return ListeCoups.size();
		
		/* Le Critere choisi pour decrire une situation est le nombre de coups
		 * possibles dans cette situation : plus il est grand, plus la situation
		 * est favorable car moins elle est "bloquee". */
	}
	
}
