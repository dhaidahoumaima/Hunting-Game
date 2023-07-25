
public class Fugitive extends Player {
	private int x;
	private int y;
	
	public Fugitive(){
	}
	
	public Fugitive(int x,int y){
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
	public void setPosition(int x,int y) {
		setX(x);
		setY(y);
	}
	@Override
	public String toString() {
		return "Fugitive []";
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
