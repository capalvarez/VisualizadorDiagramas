package utilities.edges;

import utilities.MyPoint;
import utilities.triangles.MyTriangle;
import utilities.triangles.NullTriangle;
import utilities.triangles.Triangle;

public class ExternalEdge extends AbstractEdge {
	public ExternalEdge(MyPoint p1, MyPoint p2){
		setPoints(p1,p2);
	}
	
	public ExternalEdge(int i, int j){
		i1 = i;
		i2 = j;
	}
	
	public void setTriangle(MyTriangle t){
		triangles.add(t);
		triangles.add(new NullTriangle(this));
	}
}
