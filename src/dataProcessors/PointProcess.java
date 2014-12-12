package dataProcessors;

import utilities.MyPoint;

public class PointProcess {
	private String[] initPoints;
	private MyPoint[] endPoints;
	private int wHeight;
	private int wWidth;
	
	private PointProcess(String[] points, int height, int width){
		initPoints = points;
		wHeight = height;
		wWidth = width;
	}
	
	private void process(){
		endPoints = new MyPoint[initPoints.length];
		/*Por ahora solamente valido para puntos en 2D*/
		for(int i=0;i<endPoints.length;i++){
			String[] info = initPoints[i].split("\\s+");
			
			int x = (int) Math.round(Double.parseDouble(info[0]));
			int y = 0;
			
			endPoints[i] = new MyPoint(x,y);
		}
	}
	
	

}
