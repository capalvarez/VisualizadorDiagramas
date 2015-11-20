package voronoiProcessors;

import java.util.ArrayList;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyTriangle;

public class DelaunayToVoronoiProcess {
	ArrayList<MyPoint> voronoiPoints = new ArrayList<MyPoint>();
	ArrayList<MyEdge> voronoiEdges = new ArrayList<MyEdge>();
	
	
	
	
	public DelaunayToVoronoiProcess(MyTriangle[] t, MyEdge[] e){
		getVoronoiPoints(t);
		getVoronoiEdges(e);
	}
	
	
	private void getVoronoiPoints(MyTriangle[] triangle){
		for(int i=0;i<triangle.length;i++){
			voronoiPoints.add(getCircumcenter(triangle[i]));
		}
	}
	
	public MyPoint getCircumcenter(MyTriangle t){
		ArrayList<MyPoint> p = t.getPoints();
		MyPoint A = p.get(0);
		MyPoint B = p.get(0);
		MyPoint C = p.get(0);
		
		
		double d = 2*(A.getX()*(B.getY() - C.getY()) + B.getX()*(C.getY() - A.getY()) + C.getX()*(A.getY() - B.getY()));
		
		double uX = (A.squareNorm()*(B.getY() - C.getY()) + B.squareNorm()*(C.getY() - A.getY()) + C.squareNorm()*(A.getY() - B.getY()))/d;
		double uY = (A.squareNorm()*(C.getX() - B.getX()) + B.squareNorm()*(A.getX() - C.getX()) + C.squareNorm()*(B.getX() - A.getX()))/d;
	
		return new MyPoint(uX,uY);
	}
	
	public void getVoronoiEdges(MyEdge[] edge){
		for(int i=0;i<edge.length;i++){
			MyTriangle[] triangles = edge[i].getTriangles();
			MyPoint p1 = getCircumcenter(triangles[0]);
			MyPoint p2 = getCircumcenter(triangles[1]);
					
			MyEdge newEdge = new MyEdge(voronoiPoints.indexOf(p1), voronoiPoints.indexOf(p2));
			newEdge.setPoints(p1,p2);
			
			voronoiEdges.add(newEdge);
		}
		
	}
	
	
}
