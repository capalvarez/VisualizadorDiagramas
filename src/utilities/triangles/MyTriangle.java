package utilities.triangles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.edges.TriangleEdge;

public interface MyTriangle {

	public void setNeighbours(MyTriangle[] n);

	public void setNeighbour(MyTriangle t, int i);

	public void draw(Graphics2D g2d, MyScale scale);

	public ArrayList<TriangleEdge> nullNeighbours();

	public ArrayList<TriangleEdge> getEdges();

	public ArrayList<MyPoint> getPoints();

	public MyTriangle[] getNeighbours();

	public void setPoints(ArrayList<MyPoint> newPoints);

	public boolean hasNullNeighbours();

	public boolean contains(MyPoint point, double precision);

	public boolean insideTriangle(MyPoint point, double precision);

	public boolean inEdge(MyPoint p);

	public String toString();
	
	public TriangleEdge getCommonEdge(AbstractTriangle t);
	
	public MyPoint getCircumcenter();
	
	public double getArea();
	
	public void setNeighbourInMap(MyTriangle t, int i);
	
	public MyTriangle getNeighbourByPoint(MyPoint p);

}