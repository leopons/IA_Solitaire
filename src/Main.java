
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Main {

	public static void main(String[] args) throws IOException {

		// CHOIX UTILISATEUR

		IA IAchoisie = new IAmontecarlo();
		String inputFile = "Files/Fichiers tests/petit1.sol";
		String outputFile = "Files/output.txt";

		
		// Lecture du fichier et initialisation du plateau

		BufferedReader br =
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(inputFile)));
		String s = br.readLine();
		FileWriter f = new FileWriter(outputFile);
		String[] ligne1 = s.split(" ");
		int ilength = Integer.parseInt(ligne1[0]);
		int jlength = Integer.parseInt(ligne1[1]);
		Case[][] etat= new Case[ilength][jlength];
		for (int i = 0; i < ilength ; i++){
			String ligne = br.readLine();
			// ligne = br.readLine();
			for (int j = 0; j < jlength ; j++){
				char a = ligne.charAt(j*2); 
				if (a=='#') {
					etat[i][j] = Case.HorsPlateau;
				}
				if (a=='1') {
					etat[i][j] = Case.Bille;
				}
				if (a=='0') {
					etat[i][j] = Case.Trou;
				}
			}
		}
		br.close();
		Plateau Solitaire = new Plateau(ilength,jlength,etat);


		// Calcul de la solution	

		boolean cond = true;
		ArrayList<Coup> ListeCoups = Solitaire.ListeCoupsPossibles();

		while (cond==true) {
			Coup coupJoue = IAchoisie.CoupSuivant(ListeCoups, Solitaire);
			Solitaire.AppliquerCoup(coupJoue);
			f.write(coupJoue.toString()+"\r\n");
			ListeCoups = Solitaire.ListeCoupsPossibles();
			if (ListeCoups.isEmpty()) { 
				cond = false;
			}
		}

		f.close();

		// Affichage du score	
		System.out.println(Solitaire.nbre_billes() + " billes restantes");

		// Alarme sonore fin de calcul	
		Thread playWave=new AePlayWave("Files/rappel.wav");
		playWave.start();

	}
}


