import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;


public class GraphicMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//CHOIX DE L'IA

		IA IAchoisie = new IAminmax2();

		//INITIALISATION DU PLATEAU


		BufferedReader br =
				new BufferedReader(
						new InputStreamReader(
								// CHOIX DU FICHIER SOURCE
								new FileInputStream("/Users/leo2/Desktop/supelec.sol")));
		String s = br.readLine();

		// CHOIX DU FICHIER CIBLE
		FileWriter f = new FileWriter("/Users/leo2/Desktop/cible.txt");
		String[] ligne1 = s.split(" ");
		int ilength = Integer.parseInt(ligne1[0]);
		int jlength = Integer.parseInt(ligne1[1]);
		Case[][] etat = new Case[ilength][jlength];
		for (int i = 0; i < ilength ; i++){
			String ligne = br.readLine();
			for (int j = 0; j < jlength ; j++){  
				char a = ligne.charAt(j*2); //j*2 pour les espaces
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

		//CALCUL DE LA PARTIE

		boolean cond = true;
		ArrayList<Coup> ListeCoups = Solitaire.ListeCoupsPossibles();

		//creation de la fenetre contenant la representation graphique du plateau
				int tailleFenetre = 500;
				Dimension d = new Dimension(tailleFenetre,tailleFenetre);
				JFrame fenetreGraphique = new JFrame("fenetreGraphique");
				fenetreGraphique.setVisible(true);
				fenetreGraphique.setPreferredSize(d);
				GraphiquePlateau graphiquePlateau = new GraphiquePlateau(Solitaire,tailleFenetre);
				GraphiqueCoup graphiqueCoup = new GraphiqueCoup(Solitaire,tailleFenetre,null);
				fenetreGraphique.add(graphiquePlateau);
//				fenetreGraphique.add(graphiqueCoup);
				fenetreGraphique.pack();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//actualisation du plateau et de sa representation graphique
						while (cond==true) {//
				Coup coupJoue = IAchoisie.CoupSuivant(ListeCoups,Solitaire);
				Solitaire.AppliquerCoup(coupJoue);//
				graphiqueCoup.setCoup(coupJoue);
				////			try {//
				////				Thread.sleep(3000);
				////			} catch (InterruptedException e) {
				////				// TODO Auto-generated catch block
				////				e.printStackTrace();
				////			}//
				graphiqueCoup.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				graphiquePlateau.repaint();//
				fenetreGraphique.pack();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f.write(coupJoue.toString()+"\r\n");
				ListeCoups = Solitaire.ListeCoupsPossibles();
				if (ListeCoups.isEmpty()) { 
					cond = false;
				}
			}

			f.close();
	
	//SCORE:	
	System.out.println(Solitaire.nbre_billes());
	
	//ALARME:	
      Thread playWave=new AePlayWave("/Users/leo2/Desktop/rappel.wav");
      playWave.start();


}

}





