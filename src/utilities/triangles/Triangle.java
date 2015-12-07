package utilities.triangles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import utilities.MyPoint;

import display.IWindow;

public class Triangle extends AbstractTriangle {
	public Triangle(MyPoint[] p){
		this(new ArrayList<MyPoint>(Arrays.asList(p)));	
	}
	
	public Triangle(ArrayList<MyPoint> p){
		points = p;
		neighbours = new Triangle[3];
		
		for(int i=0;i<3;i++){
			neighbours[i] = null;
		}		
	}
	
	public Triangle(){}

	public MyPoint getCircumcenter(){
		ArrayList<MyPoint> p = getPoints();
		MyPoint A = p.get(0);
		MyPoint B = p.get(1);
		MyPoint C = p.get(2);
				
		double d = 2*(A.getX()*(B.getY() - C.getY()) + B.getX()*(C.getY() - A.getY()) + C.getX()*(A.getY() - B.getY()));
				
		double uX = (A.squareNorm()*(B.getY() - C.getY()) + B.squareNorm()*(C.getY() - A.getY()) + C.squareNorm()*(A.getY() - B.getY()))/d;
		double uY = (A.squareNorm()*(C.getX() - B.getX()) + B.squareNorm()*(A.getX() - C.getX()) + C.squareNorm()*(B.getX() - A.getX()))/d;
	
		return new MyPoint(uX,uY);
	}
	
	public Collection<MyTriangle> getNeighboursByPoint(MyPoint p){
		Collection<MyTriangle> copy = neighboursMap.values();
		copy.remove(neighboursMap.get(p));	
				
		return copy;
	}
	
}
