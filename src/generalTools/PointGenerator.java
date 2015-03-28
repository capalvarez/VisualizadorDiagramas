package generalTools;

import utilities.MyPoint;

public class PointGenerator {
	private MyPoint[] pointArray;
	
	public PointGenerator(int numX, int dX, int numY, int dY){
		pointArray = new MyPoint[numX*numY];
			
		for(int i = 0; i<numX; i++){
			int posX = (i+1)*dX; 
			
			for(int j = 0; j<numY; j++){
				int index = numY*i + j;
				pointArray[index] = new MyPoint(posX,(j+1)*dY);	
			}
		}

	}
	
	public MyPoint[] getPoints(){
		return pointArray;
	}

}
