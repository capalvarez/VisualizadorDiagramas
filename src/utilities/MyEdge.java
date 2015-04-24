package utilities;

public class MyEdge {
	private int i1;
	private int i2;
	private MyPoint p1;
	private MyPoint p2;
	
	public MyEdge(int i, int j){
		i1 = i;
		i2 = j;
	}
	
	public void setPoints(MyPoint p1,MyPoint p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public MyPoint[] getPoints(){
		MyPoint[] points = new MyPoint[2];
		points[0] = p1;
		points[1] = p2;
				
		return points;
	}
	
	public int getIndexFirst(){
		return i1;
	}

	public int getIndexSecond(){
		return i2;
	}	

	public String toString(){
		return this.i1+" "+this.i2 + " " + getNormal();
	}
	
	public String getNormal(){
		double norm = Math.sqrt(Math.pow((p2.getX() - p2.getX()),2) + Math.pow((p2.getY() - p1.getY()),2));
		double coord1 = -(p2.getY() - p1.getY())/norm;
		double coord2 = (p2.getX() - p1.getX())/norm;
		
		return coord1 + " " + coord2;
	
	}
	
	public boolean equals(MyEdge e2){
		return (e2.p1.equals(this.p1) && e2.p2.equals(this.p2)) ||
			   (e2.p1.equals(this.p2) && e2.p1.equals(this.p1));
	}

	public int getNormalDir(MyEdge e){
		if(e.p1.equals(this.p1) && e.p2.equals(this.p2)){
			return 1;
		}else{
			return -1;
		}
	}
	
}
