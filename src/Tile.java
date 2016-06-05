
public class Tile {
	private int x;
	private int y;
	private int type;
	private boolean masked;
	
	Tile(int x, int y){
		location(x, y);
	}
	
	Tile(){
		location(0,0);
	}
	
	//setting location
	public void location(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	
	//getters and setters
	public void setType(int type){this.type = type;}
	public int getType(){return this.type;}
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public boolean isMasked() {return masked;}
	public void setMasked(boolean masked) {this.masked = masked;}
	
	
}
