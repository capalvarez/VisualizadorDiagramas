package utilities;

public class MyPoint {
	private int x;
	private int y;
	private int z;
	
	public MyPoint(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MyPoint(int x, int y){
		this.x = x;
		this.y = y;
		this.z = 0;
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
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint getPointLeft(MyPoint p2){
		if(this.x<=p2.getX()){
			return p2;
		}else{
			return this;
		}	
	}
}
