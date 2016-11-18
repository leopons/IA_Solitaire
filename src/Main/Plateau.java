package Main;
import java.util.ArrayList;


public class Plateau {

	private Case[][] etat;
	private int ilength;
	private int jlength;
	
	public Plateau(int i, int j, Case[][] etat){
		this.ilength=i;
		this.jlength=j;
		this.etat=etat;
	}
	
	public Case[][] getEtat(){
		return this.etat;
	}
	
	public int getilength(){
		return this.ilength;
	}
	
	public int getjlength(){
		return this.jlength;
	}
	
	/*
	public int tailleUnitaire(int tailleFenetre){
		return(tailleFenetreMath/Max(Solitaire.getilength(),Solitaire.getjlength()));
	}
	*/
	
	public ArrayList<Coup> ListeCoupsPossibles () {
		
		ArrayList<Coup> rep = new ArrayList<Coup>();
		
		for (int i = 0; i<this.getilength() ; i++){
			for (int j = 0; j<this.getjlength()-2 ; j++){
				if (this.getEtat()[i][j]==Case.Bille){
						if (this.getEtat()[i][j+1]==Case.Bille){
							if (this.getEtat()[i][j+2]==Case.Trou){
								rep.add(new Coup (i,j,Direction.DROITE));
						}
					}
				}
			}
		}
			
		for (int i = 0; i<this.getilength() ; i++){
			for (int j = 2; j<this.getjlength() ; j++){
				if (this.getEtat()[i][j]==Case.Bille){
						if (this.getEtat()[i][j-1]==Case.Bille){
							if (this.getEtat()[i][j-2]==Case.Trou){
								rep.add(new Coup (i,j,Direction.GAUCHE));
						}
					}
				}
			}
		}
							
			for (int i = 0; i<this.getilength()-2 ; i++){
				for (int j = 0; j<this.getjlength() ; j++){
					if (this.getEtat()[i][j]==Case.Bille){
						if (this.getEtat()[i+1][j]==Case.Bille){
							if (this.getEtat()[i+2][j]==Case.Trou){
								rep.add(new Coup (i,j,Direction.BAS));
							}
						}
					}
				}
			}
									
			for (int i = 2; i<this.getilength() ; i++){
				for (int j = 0; j<this.getjlength() ; j++){
					if (this.getEtat()[i][j]==Case.Bille){
						if (this.getEtat()[i-1][j]==Case.Bille){
							if (this.getEtat()[i-2][j]==Case.Trou){
								rep.add(new Coup (i,j,Direction.HAUT));
							}
						}
					}
				}
			}
		
		return rep;
		
	}
	
	
	public void AppliquerCoup (Coup c) {
		int i = c.getI();
		int j = c.getJ();
		Direction D = c.getDirection();
		this.etat[i][j] = Case.Trou;
		if (D==Direction.DROITE){
			this.etat[i][j+1]=Case.Trou;
			this.etat[i][j+2]=Case.Bille;
		}
		if (D==Direction.GAUCHE){
			this.etat[i][j-1]=Case.Trou;
			this.etat[i][j-2]=Case.Bille;
		}
		if (D==Direction.BAS){
			this.etat[i+1][j]=Case.Trou;
			this.etat[i+2][j]=Case.Bille;
		}
		if (D==Direction.HAUT){
			this.etat[i-1][j]=Case.Trou;
			this.etat[i-2][j]=Case.Bille;
		}
	}
	
	public void Retour (Coup c) {
		int i = c.getI();
		int j = c.getJ();
		Direction D = c.getDirection();
		this.etat[i][j] = Case.Bille;
		if (D==Direction.DROITE){
			this.etat[i][j+1]=Case.Bille;
			this.etat[i][j+2]=Case.Trou;
		}
		if (D==Direction.GAUCHE){
			this.etat[i][j-1]=Case.Bille;
			this.etat[i][j-2]=Case.Trou;
		}
		if (D==Direction.BAS){
			this.etat[i+1][j]=Case.Bille;
			this.etat[i+2][j]=Case.Trou;
		}
		if (D==Direction.HAUT){
			this.etat[i-1][j]=Case.Bille;
			this.etat[i-2][j]=Case.Trou;
		}
	}

    public int nbre_billes(){
    	int rep = 0;
    	for (int i=0; i<this.ilength; i++){
    		for (int j=0; j<this.jlength; j ++){
    			if (this.etat[i][j]==Case.Bille){
    				rep=rep+1;
    				}	
    			}
    		}
    	return rep;
    	}
    
	public int tailleUnitaire(int tailleFenetre){ //retourne la dimension de la taille d'une case
		return(tailleFenetre/Math.max(this.getilength(),this.getjlength()));
	}
    
    }
	
	
	

