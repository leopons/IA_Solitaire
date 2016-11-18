import java.util.ArrayList;

// Cette IA effectue un nombre de parties aleatoires determine (nombre_repetition)
// a partir de la situation presente, et renvoie le premier coup de la partie
// qui s'est le mieux finie (avec le moins de billes)

public class IAmontecarlo extends IA {
	//A CHOISIR :
	int nombre_repetition = 10000;
	boolean augmentation = true;
	int etalon = 1000;
	int seuil = 1;
	
	// AUGMENTATION et ETALON :
	// Si augmentation est true, le nombre de repetitions augmente au fur et a mesure
	// de l'avancement de la partie. En effet, plus la partie est avancee plus le 
	// calcul est rapide, plus on peut se permettre d'effectuer beaucoup de repetitions
	// Le nombre etalon sert a fixer l'ordre de grandeur du nombre de repetition au 
	// long de la partie.
	
	// SEUIL :
	// Si, par chance, au cours de ses calculs de parties aleatoires, l'algo tombe 
	// sur une partie dont le nombre de billes final est <= au seuil, alors  
	// l'algo s'arrete et deroule tout simplement cette partie en particulier.
	// (pour ne pas risquer de trouver au final un resultat moins interressant
	// avec l'algo normal)
	
	
	// INITIALISATION
	boolean stop = false; //Cette variable est true lorsqu'on depasse le seuil.
	ArrayList <Coup> CoupsSuivants = new ArrayList<Coup>(); //Liste des coups de la partie qui a dapasse le seuil
			

	
	Coup CoupSuivant (ArrayList<Coup> ListeCoups1, Plateau P) {
		if (augmentation){
			//calcul du nombre de repetitions dans le cas de l'augmentation progressive
			nombre_repetition = (int)(etalon*P.getilength()*P.getjlength()/P.nbre_billes());
		}
		if (stop) {
			// deroulage de la partie dans le cas de depassage du seuil
			Coup rep = CoupsSuivants.get(0);
			CoupsSuivants.remove(0);
			return rep;
		}
		else {
			IAaleatoire IAbis = new IAaleatoire();
			int nombreBillesMin = P.getilength() * P.getjlength() ; 
			Coup rep = null;
			for (int i=0; (i<nombre_repetition)&(!stop); i++) {
				boolean cond = true;
				ArrayList<Coup> ListeCoups = P.ListeCoupsPossibles();
				// détermination et stockage du premier coup
				Coup premierCoup = IAbis.CoupSuivant(ListeCoups, P);
				P.AppliquerCoup(premierCoup);
				ListeCoups = P.ListeCoupsPossibles();
				if (ListeCoups.isEmpty()) { 
					cond = false;
				}
				// suite de la partie aleatoire
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
				//	avec la meilleure simulation réalisée jusqu'à présent,
				//	garder le premier coup que s'il y a moins de billes
				int nombreBillesPi = P.nbre_billes();
				if(nombreBillesPi<(nombreBillesMin)){
					nombreBillesMin = nombreBillesPi;
					rep = premierCoup;
				}
				for(int j = NombreDeCoupsJoue; j>0; j--){
					P.Retour(ListeCoupsJoue.get(j-1));
				}
				P.Retour(premierCoup);
				if (nombreBillesPi<=seuil){
					// changement de stop dans le cas de depassage du seuil
					// et stockage des coups a derouler plus tard
					stop = true;
					//Thread playWave=new AePlayWave("/Users/leo2/Desktop/rappel.wav");
				    //playWave.start();
					CoupsSuivants = ListeCoupsJoue;
				}
			}
	
			return rep;
			
			}
		}
		
	
}
	
	


