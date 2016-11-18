package IA.MinMax;

import java.util.ArrayList;

import Main.Coup;
import Main.Plateau;

// Classe abstraite des Criteres utilises par les IAminmax et IAminmaxPartiel
// Le choix est a faire dans les classes des IA

public abstract class CRITERE {
	
	abstract int critere (Plateau P, ArrayList<Coup> ListeCoups);

}
