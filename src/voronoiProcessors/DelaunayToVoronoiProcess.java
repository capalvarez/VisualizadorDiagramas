package voronoiProcessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import utilities.MyCell;
import utilities.MyPoint;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class DelaunayToVoronoiProcess {
	ArrayList<MyPoint> voronoiPoints = new ArrayList<MyPoint>();
	ArrayList<MyEdge> voronoiEdges = new ArrayList<MyEdge>();
	ArrayList<MyCell> voronoiCells = new ArrayList<MyCell>();
	
	public DelaunayToVoronoiProcess(MyTriangle[] t, MyEdge[] e, HashMap<MyPoint,ArrayList<MyTriangle>> map){
		computeVoronoiPoints(t);
		computeVoronoiEdges(e);
		//computeVoronoiCells(map);
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
			MyCell newCell = new MyCell();
			
			int i = 0;
			MyTriangle t = list.get(i);
			MyTriangle lastTriangle = null;
						
			while(!t.equals(list.get(0)) || i==0){
				MyPoint p1 = t.getCircumcenter();
			
				Collection<MyTriangle> usefulNeighbours = t.getNeighboursByPoint(point); 
				Iterator<MyTriangle> it = usefulNeighbours.iterator();
		
                MyTriangle first = it.next();
				MyTriangle second = it.next();

				MyPoint p21 = first.getCircumcenter();
				MyPoint p22 = second.getCircumcenter();

				MyEdge newEdge;
				
				if(lastTriangle==null){
					if(counterClockwise(point, p1, p21)){
						newEdge = new InternalEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p21));
						lastTriangle = t;
						t = first;
					}else{
						newEdge = new InternalEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p22));
						lastTriangle = t;
						t = second;
					}
				}else{
					if(!first.equals(lastTriangle)){
						newEdge = new InternalEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p21));
						lastTriangle = t;
						t = first;
					}else{
						newEdge = new InternalEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p22));
						lastTriangle = t;
						t = second;
					}
				}
					
				if(!voronoiEdges.contains(newEdge)){
					voronoiEdges.add(newEdge);
				}
				
				int index = voronoiEdges.indexOf(newEdge);			
				
				newCell.addEdge(index, voronoiEdges.get(index),	newEdge.exactCompare(voronoiEdges.get(index)));

				i++;
			}
			
			voronoiCells.add(newCell);
		}
	}	
		
	private boolean counterClockwise(MyPoint pA, MyPoint pB, MyPoint pC){	
		return ((pB.getX() * pC.getY() + pA.getX() * pB.getY() + pA.getY()* pC.getX()) - 
				(pA.getY() * pB.getX() + pB.getY() * pC.getX() + pA.getX() * pC.getY())) >= 0;
	}
}

