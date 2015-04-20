package generalTools;

import utilities.MyPoint;

public class PointGenerator {
	private MyPoint[] pointArray;
	
	public PointGenerator(int numX, int dX, int numY, int dY){
		this(numX,dX,numY,dY,0,0);
	}
	
	public PointGenerator(int numX, int dX, int numY, int dY, int xInit, int yInit){
		pointArray = new MyPoint[numX*numY];
			
		for(int i = 0; i<numX; i++){
			int posX = (i+1)*dX + xInit; 
			
			for(int j = 0; j<numY; j++){
				int index = numY*i + j;
				pointArray[index] = new MyPoint(posX,(j+1)*dY + yInit);	
			}
		}

	}
	
	public MyPoint[] getPoints(){
		return pointArray;
	}

}
