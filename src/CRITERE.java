
import java.util.ArrayList;

// Classe abstraite des Criteres utilises par les IAminmax et IAminmax2
// Le choix est a faire dans les classes des IA

public abstract class CRITERE {
	
	abstract int critere (Plateau P, ArrayList<Coup> ListeCoups);

}
