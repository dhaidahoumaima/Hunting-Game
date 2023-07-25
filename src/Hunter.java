import java.awt.Point;
public class Hunter extends Player {
	private int x;
	private int y;
	
	public Hunter(){
		
	}
	public Hunter(int x,int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Hunter []";
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
