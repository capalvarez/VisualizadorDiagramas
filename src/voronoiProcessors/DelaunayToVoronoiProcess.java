package voronoiProcessors;

import java.util.ArrayList;

import utilities.MyPoint;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class DelaunayToVoronoiProcess {
	ArrayList<MyPoint> voronoiPoints = new ArrayList<MyPoint>();
	ArrayList<InternalEdge> voronoiEdges = new ArrayList<InternalEdge>();
	
	public DelaunayToVoronoiProcess(MyTriangle[] t, MyEdge[] e){
		computeVoronoiPoints(t);
		computeVoronoiEdges(e);
	}
	
	public MyPoint[] getVoronoiPoints(){
		MyPoint[] array = new MyPoint[voronoiPoints.size()];
		array = voronoiPoints.toArray(array);
		
		return array;
	}
	
	public MyEdge[] getVoronoiEdges(){
		InternalEdge[] array = new InternalEdge[voronoiEdges.size()];
		array = voronoiEdges.toArray(array);
				
		return array;
	}
	
	
	private void computeVoronoiPoints(MyTriangle[] triangle){
		for(int i=1;i<triangle.length;i++){
			voronoiPoints.add(triangle[i].getCircumcenter());
		}
	}
		
	private void computeVoronoiEdges(MyEdge[] edge){
		for(int i=1;i<edge.length;i++){
			ArrayList<MyTriangle> triangles = edge[i].getTriangles();
		
			if(triangles.size()==2){
				MyPoint p1 = triangles.get(0).getCircumcenter();
				MyPoint p2 = triangles.get(1).getCircumcenter();
						
				InternalEdge newEdge = new InternalEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p2));
				newEdge.setPoints(p1,p2);
				
				voronoiEdges.add(newEdge);
			}else{
				
				 
			}
		}	
	}
	
	
}
