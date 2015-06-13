package diagramGenerators;

import java.util.ArrayList;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyTriangle;

public class VoronoiFromDelaunay {
	private MyCell[] voronoiCells;
	private MyPoint[] voronoiPoints;
	private MyEdge[] voronoiEdges;
	private MyTriangle[] delaunay;
	
	public VoronoiFromDelaunay(MyTriangle[] delaunay){
		this.delaunay = delaunay;
	}
	
	private void computeCircumcenter(){
		voronoiPoints = new MyPoint[delaunay.length];
		
		for(int i=0;i<delaunay.length;i++){
			voronoiPoints[i] = delaunay[i].getCircumcenter();
		}
	}
	
	private void computeEdges(){
		ArrayList<MyEdge> edges = new ArrayList<MyEdge>();
		
		MyTriangle[] initNeighbours = delaunay[0].getNeighbours();
		MyPoint circumCenter = voronoiPoints[0];
		
		edges.add(new MyEdge(circumCenter,initNeighbours[0].getCircumcenter()));
		edges.add(new MyEdge(circumCenter,initNeighbours[1].getCircumcenter()));
		edges.add(new MyEdge(circumCenter,initNeighbours[2].getCircumcenter()));
		
		
		
		
	}
	

}
