package dataProcessors;

import utilities.MyPoint;

public class PointStringProcess extends PointProcess{
	private String[] initPoints;
		
	public PointStringProcess(String[] points){
		initPoints = points;
		
		process();
	}
	
	private void process(){
		endPoints = new MyPoint[initPoints.length];
		initPointsList = new MyPoint[initPoints.length];
		
		for(int i=0;i<endPoints.length;i++){
			String[] info = (initPoints[i].trim()).split("\\s+");
			double x = Double.parseDouble(info[0]);
			double y = Double.parseDouble(info[1]);
							
			endPoints[i] = new MyPoint(x,y);	
		}
	}
}
