package utilities;

public class MyPoint {
	private int x;
	private int y;
	private int z;
	private int dimension;
	private int infXS = 1;
	private int infYS = 1;
	
	public MyPoint(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimension = 3;
	}
	
	public MyPoint(int x, int y){
		this.x = x;
		this.y = y;
		this.z = 0;
		this.dimension = 2;
	}
	
	public MyPoint(int x, int y, int xSign, int ySign){
		this.x = x;
		this.y = y;
		this.z = 0;
		this.dimension = 2;
		this.infXS = xSign;
		this.infYS = ySign;	
	}
		
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getZ(){
		return this.z;
	}
	
	public int getDimension(){
		return dimension;
	}
	
	public int getInfinityX(){
		return this.infXS*1000;
	}
	
	public int getInfinityY(){
		return this.infYS*1000;
	}
	
	public double distanceZero(){
		return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
	}
	
	public boolean equals(MyPoint p2){
		return p2.getX()==this.getY() && p2.getY()==this.getY()
			   && p2.getZ()==this.getZ();			   	
	}
	
	public String toString(){
		return "("+this.x+","+this.y+","+this.z+")";
	}
	
	public double distance(MyPoint p2){
		return Math.sqrt(distanceZero() + p2.distanceZero());
	}
	
	public MyPoint getPointRight(MyPoint p2){
		if(this.x<=p2.getX()){
			return p2;
		}else{
			return this;
		}	
	}
	
	public MyPoint getPointLeft(MyPoint p2){
		if(this.x<=p2.getX()){
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint getPointDown(MyPoint p2){
		if(this.y<=p2.getY()){
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint getPointUp(MyPoint p2){
		if(this.y>=p2.getY()){
			return this;
		}else{
			return p2;
		}	
	}
	
	
}
