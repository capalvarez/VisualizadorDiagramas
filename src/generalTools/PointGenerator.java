package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;

public class PointGenerator {
	private ArrayList<MyPoint> pointArray;
	
	public PointGenerator(int numX, double dX, int numY, double dY){
		this(numX,dX,numY,dY,0,0);
	}
	
	public PointGenerator(int numX, double dX, int numY, double dY, double xInit, double yInit){
		pointArray = new ArrayList<MyPoint>();
			
		for(int i = 0; i<numX; i++){
			double posX = i*dX + xInit; 
			
			for(int j = 0; j<numY; j++){
				pointArray.add(new MyPoint(posX,j*dY + yInit));	
			}
		}
	}
	
	public ArrayList<MyPoint> getPoints(){
		return pointArray;
	}

}
