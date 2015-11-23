package utilities.triangles;

import utilities.MyPoint;
import utilities.edges.MyEdge;

public class NullTriangle extends AbstractTriangle {
	MyEdge nullEdge;
	
	public NullTriangle(MyEdge e){
		nullEdge = e;
	}
		
	public MyPoint getCircumcenter(){
		return nullEdge.getMidPoint();
	}
}
