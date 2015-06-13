package utilities;

public class MyPoint {
	private double x;
	private double y;
	private double z;
	private int dimension;
	private int infXS = 1;
	private int infYS = 1;
	
	public MyPoint(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimension = 3;
	}
	
	public MyPoint(double x, double y){
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
		
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
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
	
	@Override
	public boolean equals(Object o){
		if(o instanceof MyPoint){
			MyPoint p2 = (MyPoint)o;
			
			return Math.abs(p2.getX()-this.getX())<0.000001 && Math.abs(p2.getY()-this.getY())<0.000001
					   && p2.getZ()-this.getZ()<0.000001;		
		}else{
			return false;
		}		   	
	}
	
	@Override
	public String toString(){
		return this.x + " " + this.y + " " + this.z;
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
