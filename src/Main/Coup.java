package Main;

public class Coup {

	private int I;
	private int J;
	private Direction direction;
	
	public Coup(int i, int j, Direction direction){
		this.I = i;
		this.J = j;
		this.direction = direction;
	}
	
	public String toString() {
		return J +" "+ I +" "+ direction;
	}

	public int getI() {
		return I;
	}
	public void setI(int i) {
		this.I = i;
	}
	public int getJ() {
		return J;
	}
	public void setJ(int j) {
		this.J = j;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
}
