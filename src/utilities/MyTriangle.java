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
		System.out.println("resultado para " + this.toString());
		System.out.println(results);
		
		ArrayList<TriangleEdge> thisEdges = this.getEdges();
		for(TriangleEdge e: results){
			thisEdges.remove(e);
		}
		
		System.out.println(thisEdges);
		System.out.println(" ");
		
		return thisEdges;
		
	}

	public ArrayList<TriangleEdge> getEdges(){
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
	
	public boolean contains(MyPoint p){
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

		if(total<0){
			return false;
		}else{
			return true;
		}
	
	}
	
	public MyEdge collinearEdge(MyPoint p){
		ArrayList<TriangleEdge> edges = getEdges();
		return null;
		
	}
	
	public MyPoint getCircumcenter(){
		MyPoint A = points.get(0);
		MyPoint B = points.get(1);
		MyPoint C = points.get(2);
		
		double fA = Math.pow(A.getX(),2) + Math.pow(A.getY(),2); 
		double fB = Math.pow(B.getX(),2) + Math.pow(B.getY(),2);
		double fC = Math.pow(C.getX(),2) + Math.pow(C.getY(),2);
				
		
		double D = 2*(A.getX()*(B.getY()-C.getY()) + B.getY()*(C.getY()-A.getY()) + C.getX()*(A.getY()-B.getY()));
		
		double uX = (fA*(B.getY()-C.getY())+fB*(C.getY()-A.getY())+fC*(A.getY()-B.getY()))/D;
		double uY = (fA*(C.getX()-B.getX())+fB*(A.getX()-C.getX())+fC*(B.getX()-A.getX()))/D;
		
		return new MyPoint(uX,uY);
		
	}
	
	@Override
	public String toString(){
		return "(" + points.get(0).toString() + ")" + "(" + points.get(1).toString() + ")" +"(" +points.get(2).toString() + ")";
	}

	public static void main(String[] args){
		MyPoint[] points = {new MyPoint(0,75),new MyPoint(0,50),new MyPoint(25,50)};
		MyPoint[] points2 = {new MyPoint(25,50),new MyPoint(25,75),new MyPoint(0,75)};
		
		/*Por alguna extraña razon esto da false para ambos siendo que claramente el punto esta en uno de los
		 * dos*/
		MyTriangle t = new MyTriangle(points);
		MyTriangle t2 = new MyTriangle(points);
		
		System.out.println(t.contains(new MyPoint(13,63)));
		System.out.println(t2.contains(new MyPoint(13,63)));
	}
	
	
}
