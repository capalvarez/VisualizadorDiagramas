package dataProcessors;

import utilities.MyPoint;

public class PointScaleProcess extends PointProcess{
	double[] scale;
	
	public PointScaleProcess(MyPoint[] points, double[] scale, int width, int height){
		initPointsList = points;
		this.scale = scale;
		wWidth = width; 
		wHeight = height;
		
		processPoints();
	}
		
	private void processPoints(){
		endPoints = new MyPoint[initPointsList.length];

		for(int i=0;i<initPointsList.length;i++){
			double x1 = initPointsList[i].getX();
			double y1 = initPointsList[i].getY();
					
			int x = (int) Math.round(wWidth*(x1-1.5*scale[1])/(1.5*(scale[0]-scale[1])));
			int y = (int) Math.round(wHeight/(1.5*(scale[2]-scale[3]))*(y1-1.5*scale[3]));
							
			endPoints[i] = new MyPoint(x,y);	
		}
	}
}
