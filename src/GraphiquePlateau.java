import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;


public class GraphiquePlateau extends JPanel{
	private Plateau plateau;
	private int tailleFenetre;


	public GraphiquePlateau(Plateau plateau, int tailleFenetre){
		this.plateau = plateau;
		this.tailleFenetre = tailleFenetre;

	}

	public int getTaille(){
		return this.tailleFenetre;
	}


	@Override public void paint(Graphics g){

		/* on parcourt tout le plateau:
		 * si la case est inaccessible, on ne fait rien
		 * si la case est accessible mais vide, on colore son fond en blanc
		 * si la case contient une bille, on colore son fond en blanc et on dessine un disque noir au milieu
		 */
		
		int tailleUnitaire = this.plateau.tailleUnitaire(this.getTaille());
		int x=0;
		int y=0;
		for (int i=0; i<this.plateau.getilength(); i++){
			for (int j=0; j<this.plateau.getjlength(); j++){
				if (this.plateau.getEtat()[i][j]!=Case.HorsPlateau){
					y = i*tailleUnitaire; //le couple (i,j) de la matrice correspond à la coordonnee (x,y)=(j*tailleunitaire,i*tailleunitaire)
					x = j*tailleUnitaire;
					g.setColor(Color.white);
					g.fillRect(x,y,tailleUnitaire,tailleUnitaire);
					if (this.plateau.getEtat()[i][j]==Case.Bille){
						g.setColor(Color.black);
						g.fillOval(x+tailleUnitaire/4,y+tailleUnitaire/4,tailleUnitaire/2,tailleUnitaire/2);
					}
				}
			}
		}
		
		
		
	}
}
