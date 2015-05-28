package utilities;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class MyTriangle {
	MyPoint[] points;
	MyTriangle[] neighbours;
	
	public MyTriangle(MyPoint[] p){
		points = p;
		
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
		MyPoint p1 = scale.getPixelValue(points[0]);
		MyPoint p2 = scale.getPixelValue(points[1]);
		MyPoint p3 = scale.getPixelValue(points[2]);
				
		g2d.draw(new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
		g2d.draw(new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY()));
		g2d.draw(new Line2D.Double(p3.getX(), p3.getY(), p1.getX(), p1.getY()));
	}
	
}
