package utilities.triangles;

import java.util.Collection;

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

	public Collection<MyTriangle> getNeighboursByPoint(MyPoint p){
		return neighboursMap.values();
	}
	
}
