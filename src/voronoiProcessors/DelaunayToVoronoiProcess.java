package voronoiProcessors;

import java.util.ArrayList;
import java.util.HashMap;

import utilities.MyCell;
import utilities.MyPoint;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class DelaunayToVoronoiProcess {
	ArrayList<MyPoint> voronoiPoints = new ArrayList<MyPoint>();
	ArrayList<InternalEdge> voronoiEdges = new ArrayList<InternalEdge>();
	ArrayList<MyCell> voronoiCells = new ArrayList<MyCell>();
	
	public DelaunayToVoronoiProcess(MyTriangle[] t, MyEdge[] e, HashMap<MyPoint,ArrayList<MyTriangle>> map){
		computeVoronoiPoints(t);
		computeVoronoiEdges(e);
		computeVoronoiCells(map);
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
	
	public MyCell[] getVoronoiCells(){
		MyCell[] array = new MyCell[voronoiCells.size()];
		array = voronoiCells.toArray(array);
				
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
			MyPoint p1 = triangles.get(0).getCircumcenter();
			MyPoint p2 = triangles.get(1).getCircumcenter();

			if(!voronoiPoints.contains(p1)){
				voronoiPoints.add(p1);	
			}
			
			if(!voronoiPoints.contains(p2)){
				voronoiPoints.add(p2);	
			}

			InternalEdge newEdge = new InternalEdge(voronoiPoints.indexOf(p1) + 1, voronoiPoints.indexOf(p2) + 1);
			newEdge.setPoints(p1,p2);

			voronoiEdges.add(newEdge);
		}	
	}
	
	private void computeVoronoiCells(HashMap<MyPoint,ArrayList<MyTriangle>> map){
		for (MyPoint point : map.keySet()) {
			ArrayList<MyTriangle> list = map.get(point);
			
			MyTriangle t = list.get(0);
			
			while(true){
				MyPoint p1 = t.getCircumcenter();
				MyPoint p2 = t.getNeighbourByPoint(point).getCircumcenter();
				
				int index = voronoiEdges.indexOf(new InternalEdge(p1,p2));
				
				
			}
		    
		}
	}
	
	
}
