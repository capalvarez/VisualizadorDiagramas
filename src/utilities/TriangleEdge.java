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
			
			if((e.getP1().equals(this.p1) && e.getP2().equals(this.p2)) || 
					e.getP1().equals(this.p2) && e.getP2().equals(this.p1)){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}	
	}
	
	@Override
	public String toString(){
		return "(" + p1.toString() + "),("+ p2.toString() + ")";
	}
	
	public boolean inEdge(MyPoint p){		
		double AB = Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));
		double AP = Math.sqrt(Math.pow(p.getX()-p1.getX(), 2) + Math.pow(p.getY()-p1.getY(), 2));
		double PB = Math.sqrt(Math.pow(p2.getX()-p.getX(), 2) + Math.pow(p2.getY()-p.getY(), 2));
		
		return Math.abs(AB - (AP + PB))<0.0001;
	}
	
}
