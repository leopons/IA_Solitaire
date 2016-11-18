package Graphics;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Main.Coup;
import Main.Direction;
import Main.Plateau;


public class GraphicCoup extends JPanel {
	private Plateau plateau;
	private int tailleFenetre;
	private Coup coup;


	public GraphicCoup(Plateau plateau, int tailleFenetre, Coup coup){
		this.plateau = plateau;
		this.tailleFenetre = tailleFenetre;
		this.coup = coup;

	}

	public int getTaille(){
		return this.tailleFenetre;
	}
	
	public void setCoup(Coup coup){
		this.coup = coup;
	}


	@Override public void paint(Graphics g){
		int tailleUnitaire = plateau.tailleUnitaire(this.getTaille());
		/* On realise le traitement graphique suivant pour un coup jouable:
		 * case de depart: fond vert avec "trou" blanc
		 * case intermediaire: fond rouge avec bille noire
		 * case d'arrivee: fond blanc avec bille verte
		 */
			if (this.coup!=null){//coup ne sera nul que quand on affichera le plateau venant d'etre initialise
				int yDepart = this.coup.getI()*tailleUnitaire;
				int xDepart = this.coup.getJ()*tailleUnitaire;
				int yIntermediaire=0;//on initialise les positions qui dependent de la direction du coup, puis on les definit au cas par cas
				int xIntermediaire=0;
				int yArrivee=0;
				int xArrivee=0;
				if(this.coup.getDirection()==Direction.DROITE){
					yIntermediaire = yDepart;
					xIntermediaire = (this.coup.getJ()+1)*tailleUnitaire;
					yArrivee = yDepart;
					xArrivee = (this.coup.getJ()+2)*tailleUnitaire;
				}
				else if(this.coup.getDirection()==Direction.BAS){
					yIntermediaire = (this.coup.getI()-1)*tailleUnitaire;
					xIntermediaire = xDepart;
					yArrivee = (this.coup.getI()-2)*tailleUnitaire;
					xArrivee = xDepart;
				}
				else if(this.coup.getDirection()==Direction.GAUCHE){
					yIntermediaire = yDepart;
					xIntermediaire = (this.coup.getJ()-1)*tailleUnitaire;
					yArrivee = yDepart;
					xArrivee = (this.coup.getJ()-2)*tailleUnitaire;
				}
				else if(this.coup.getDirection()==Direction.HAUT){
					yIntermediaire = (this.coup.getI()+1)*tailleUnitaire;
					xIntermediaire = xDepart;
					yArrivee = (this.coup.getI()+2)*tailleUnitaire;
					xArrivee = xDepart;
				}
				g.setColor(Color.green);
				g.fillRect(xDepart,yDepart,tailleUnitaire,tailleUnitaire);
				g.fillOval(xArrivee+tailleUnitaire/4,yArrivee+tailleUnitaire/4,tailleUnitaire/2,tailleUnitaire/2);
				g.setColor(Color.red);
				g.fillRect(xIntermediaire,yIntermediaire,tailleUnitaire,tailleUnitaire);
				g.setColor(Color.white);
				g.fillOval(xDepart+tailleUnitaire/4,yDepart+tailleUnitaire/4,tailleUnitaire/2,tailleUnitaire/2);
				g.setColor(Color.black);
				g.fillOval(xIntermediaire+tailleUnitaire/4,yIntermediaire+tailleUnitaire/4,tailleUnitaire/2,tailleUnitaire/2);
			}
		}
	}


