package utilities.triangles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.edges.TriangleEdge;

public interface MyTriangle {

	public abstract void setNeighbours(MyTriangle[] n);

	public abstract void setNeighbour(MyTriangle t, int i);

	public abstract void draw(Graphics2D g2d, MyScale scale);

	public abstract ArrayList<TriangleEdge> nullNeighbours();

	public abstract ArrayList<TriangleEdge> getEdges();

	public abstract ArrayList<MyPoint> getPoints();

	public abstract MyTriangle[] getNeighbours();

	public abstract void setPoints(ArrayList<MyPoint> newPoints);

	public abstract boolean hasNullNeighbours();

	public abstract boolean contains(MyPoint point, double precision);

	public abstract boolean insideTriangle(MyPoint point, double precision);

	public abstract boolean inEdge(MyPoint p);

	public abstract String toString();
	
	public TriangleEdge getCommonEdge(AbstractTriangle t);
	
	public MyPoint getCircumcenter();
	
	public double getArea();

}