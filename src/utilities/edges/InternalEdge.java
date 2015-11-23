package utilities.edges;

import utilities.MyPoint;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class InternalEdge extends AbstractEdge {
	public InternalEdge(MyPoint p1, MyPoint p2){
		setPoints(p1,p2);
	}
	
	public InternalEdge(int i, int j){
		i1 = i;
		i2 = j;
	}
	
	public void setTriangle(MyTriangle t){
		triangles.add(t);
	}
	
}
