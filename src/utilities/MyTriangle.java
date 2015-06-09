package utilities;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

public class MyTriangle {
	ArrayList<MyPoint> points;
	MyTriangle[] neighbours;
	ArrayList<TriangleEdge> edges = new ArrayList<TriangleEdge>();
	
	
	public MyTriangle(MyPoint[] p){
		points = new ArrayList<MyPoint>(Arrays.asList(p));
		edges.add(new TriangleEdge(p[0],p[1]));
		edges.add(new TriangleEdge(p[1],p[2]));
		edges.add(new TriangleEdge(p[2],p[0]));
		
		neighbours = new MyTriangle[3];	
	}
	
	public MyTriangle(){}
	
	public void setNeighbours(MyTriangle[] n){
		neighbours = n;
	}
	
	public void setNeighbour(MyTriangle t, int i){
		neighbours[i] = t;
	}
	
	public void draw(Graphics2D g2d, MyScale scale){
		MyPoint p1 = scale.getPixelValue(points.get(0));
		MyPoint p2 = scale.getPixelValue(points.get(1));
		MyPoint p3 = scale.getPixelValue(points.get(2));
				
		g2d.draw(new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
		g2d.draw(new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY()));
		g2d.draw(new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY()));
	}
	
	
	public ArrayList<TriangleEdge> nullNeighbours(){
		ArrayList<TriangleEdge> results = new ArrayList<TriangleEdge>();
		
		for(int i=1;i<3;i++){
			if(neighbours[i]!=null){
				results.add(neighbours[i].getCommonEdge(this));
			}
		}
		
		ArrayList<TriangleEdge> thisEdges = this.getEdges();
		for(TriangleEdge e: results){
			thisEdges.remove(e);
		}
		
		return thisEdges;
		
	}

	public ArrayList<TriangleEdge> getEdges(){
		return edges;
	}
	
	public ArrayList<MyPoint> getPoints(){
		return points;
	}
	
	private TriangleEdge getCommonEdge(MyTriangle t){
		ArrayList<MyPoint> otherPoints = t.getPoints();
		ArrayList<MyPoint> commonPoints = new ArrayList<MyPoint>();
			
		for(int i=0;i<3;i++){
			if(otherPoints.contains(points.get(i))){
				commonPoints.add(points.get(i));
			}
		}
		
		if(commonPoints.size()==2){
			return new TriangleEdge(commonPoints.get(0),commonPoints.get(1));
		}else{
			return null;
		}
		
	}
	
	public boolean hasNullNeighbours(){
		return neighbours[0]==null || neighbours[1]==null || neighbours[2]==null; 
	}
	
	public boolean contains(MyPoint p){
		ArrayList<TriangleEdge> edges = this.getEdges();
		
		if(orientationTest(edges.get(0).getP1(),p,edges.get(0).getP2())){
			return false;
		}
		
		if(orientationTest(edges.get(1).getP1(),p,edges.get(1).getP2())){
			return false;
		}
		
		if(orientationTest(edges.get(2).getP1(),p,edges.get(2).getP2())){
			return false;
		}
		
		return true;
		
	}
	
	private boolean orientationTest(MyPoint pA, MyPoint pB, MyPoint pC){
		return (pB.getX() - pA.getX())*(pC.getY() - pA.getY()) - (pC.getX() - pA.getX())*(pB.getY() - pA.getY())<0;
	}

}
