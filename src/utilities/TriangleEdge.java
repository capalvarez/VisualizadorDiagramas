package utilities;

public class TriangleEdge {
	private MyPoint p1;
	private MyPoint p2;
	
	public TriangleEdge(MyPoint p1, MyPoint p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public MyPoint getMidPoint(){
		return new MyPoint((p1.getX() + p2.getX())/2,(p1.getY() + p2.getY())/2); 
	}
	
	
	
}
