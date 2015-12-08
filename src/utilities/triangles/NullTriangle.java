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
	
	public void setNeighbourInMap(MyTriangle t, int index){
		if(index!=2){
			neighboursMap.put(nullEdge.getPoint(index),t);
		}else{
			neighboursMap.put(new MyPoint(),t);
		}
	}

}
