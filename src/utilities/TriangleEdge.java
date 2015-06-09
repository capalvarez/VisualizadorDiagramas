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
	
	public MyPoint getP1(){
		return p1;
	}
	
	public MyPoint getP2(){
		return p2;
	}
		
	@Override
	public boolean equals(Object o){
		if(o instanceof TriangleEdge){
			TriangleEdge e = (TriangleEdge) o;
			
			if(e.getP1().equals(this.p1) && e.getP2().equals(this.p2)){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
		
	}
	
	
}
