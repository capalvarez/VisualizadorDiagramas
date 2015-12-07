package utilities.edges;

import java.util.ArrayList;

import utilities.MyPoint;
import utilities.perforations.Perforation;
import utilities.triangles.MyTriangle;

public interface MyEdge {

	public void setPoints(MyPoint p1, MyPoint p2);

	public ArrayList<MyTriangle> getTriangles();

	public MyPoint[] getPoints();

	public int getIndexFirst();

	public int getIndexSecond();

	public MyPoint getFirstPoint();

	public MyPoint getSecondPoint();

	public String toString();

	public String getNormal();

	public boolean equals(Object o);

	public int getNormalDir(AbstractEdge e);

	public boolean inEdge(MyPoint p);

	public ArrayList<MyPoint> intersectionPoint(Perforation p);

	public MyPoint getMidPoint();
	
	public void setTriangle(MyTriangle t);
	
	public int exactCompare(MyEdge e);

}