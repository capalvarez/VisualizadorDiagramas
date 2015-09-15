package utilities;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

import display.IWindow;

public class MyTriangle {
	ArrayList<MyPoint> points;
	MyTriangle[] neighbours;
		
	public MyTriangle(MyPoint[] p){
		points = new ArrayList<MyPoint>(Arrays.asList(p));
		
		neighbours = new MyTriangle[3];
		
		for(int i=0;i<3;i++){
			neighbours[i] = null;
		}
		
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
		
		for(int i=0;i<3;i++){
			if(neighbours[i]!=null){			
				results.add(neighbours[i].getCommonEdge(this));
			}
		}
				
		ArrayList<TriangleEdge> thisEdges = this.getEdges();
		for(TriangleEdge e: results){		
			for(int i=0;i<results.size();i++){
				if(results.get(i).equals(e)){
					thisEdges.remove(e);
				}
			}
		}
		
		return thisEdges;
		
	}

	public ArrayList<TriangleEdge> getEdges(){
		ArrayList<TriangleEdge> edges = new ArrayList<TriangleEdge>();
		
		edges.add(new TriangleEdge(points.get(0),points.get(1)));
		edges.add(new TriangleEdge(points.get(1),points.get(2)));
		edges.add(new TriangleEdge(points.get(2),points.get(0)));		
		
		return edges;
	}
	
	public ArrayList<MyPoint> getPoints(){
		return points;
	}
	
	public MyTriangle[] getNeighbours(){
		return neighbours;
	}
	
	public void setPoints(ArrayList<MyPoint> newPoints){
		points = newPoints;
	}
	
	private TriangleEdge getCommonEdge(MyTriangle t){
		ArrayList<MyPoint> otherPoints = t.getPoints();
		ArrayList<MyPoint> commonPoints = new ArrayList<MyPoint>();
			
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(otherPoints.get(j).equals(points.get(i))){
					commonPoints.add(points.get(i));
				}
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
	
	public boolean containsAnt(MyPoint p){
		double a = points.get(0).getX() - p.getX();
		double b = points.get(0).getY() - p.getY();
		double c = (Math.pow(points.get(0).getX(),2) - Math.pow(p.getX(),2)) + (Math.pow(points.get(0).getY(),2) - Math.pow(p.getY(),2));
		
		double d = points.get(1).getX() - p.getX();
		double e = points.get(1).getY() - p.getY();
		double f = (Math.pow(points.get(1).getX(),2) - Math.pow(p.getX(),2)) + (Math.pow(points.get(1).getY(),2) - Math.pow(p.getY(),2));
	
		double g = points.get(2).getX() - p.getX();
		double h = points.get(2).getY() - p.getY();
		double i = (Math.pow(points.get(2).getX(),2) - Math.pow(p.getX(),2)) + (Math.pow(points.get(2).getY(),2) - Math.pow(p.getY(),2));
	
		double total = a*(e*i-f*h) - d*(b*i-c*h) + g*(b*f-e*c);
		
		if(total<0.00001){
			return false;
		}else{
			return true;
		}
	
	}
	
	public boolean contains(MyPoint point, double precision){		
		return insideTriangle(point,precision) || inEdge(point);
	}
		
	public boolean insideTriangle(MyPoint point, double precision){
		MyPoint p = point.substract(points.get(0));
		MyPoint B = points.get(1).substract(points.get(0));
		MyPoint C = points.get(2).substract(points.get(0));
		
		double d = B.getX()*C.getY() - C.getX()*B.getY();
		
		double wA = (p.getX()*(B.getY()-C.getY()) + p.getY()*(C.getX()-B.getX()) + B.getX()*C.getY() - C.getX()*B.getY())/d; 	
		double wB = (p.getX()*C.getY() - p.getY()*C.getX())/d;
		double wC = (p.getY()*B.getX() - p.getX()*B.getY())/d;

		return wA<=precision && wB<=precision && wC<=precision && 0<=wA && 0<=wB && 0<=wC; 	
	}
	
	public boolean inEdge(MyPoint p){
		ArrayList<TriangleEdge> edges = this.getEdges();
			
		return edges.get(0).inEdge(p) || edges.get(1).inEdge(p) || edges.get(2).inEdge(p);  
	}
	
	@Override
	public String toString(){
		return "(" + points.get(0).toString() + ")" + "(" + points.get(1).toString() + ")" +"(" +points.get(2).toString() + ")";
	}

	public static void main(String[] args){
		MyPoint[] points = {new MyPoint(12.5,100.0),new MyPoint(0,100.0),new MyPoint(6.0,87.5)};
		
		/*Por alguna extraña razon esto da false para ambos siendo que claramente el punto esta en uno de los
		 * dos*/
		MyTriangle t = new MyTriangle(points);
		MyTriangle t2 = new MyTriangle(points);
		 
		System.out.println(t.insideTriangle(new MyPoint(6.25,95.31), 0.99));
		//System.out.println(t2.contains(new MyPoint(13,63)));
	}
	
	
}
