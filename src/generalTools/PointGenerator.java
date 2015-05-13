package generalTools;

import utilities.MyPoint;

public class PointGenerator {
	private MyPoint[] pointArray;
	
	public PointGenerator(int numX, double dX, int numY, double dY){
		this(numX,dX,numY,dY,0,0);
	}
	
	public PointGenerator(int numX, double dX, int numY, double dY, double xInit, double yInit){
		pointArray = new MyPoint[numX*numY];
			
		for(int i = 0; i<numX; i++){
			double posX = i*dX + xInit; 
			
			for(int j = 0; j<numY; j++){
				int index = numY*i + j;
				pointArray[index] = new MyPoint(posX,j*dY + yInit);	
			}
		}
	}
	
	public MyPoint[] getPoints(){
		return pointArray;
	}

}
