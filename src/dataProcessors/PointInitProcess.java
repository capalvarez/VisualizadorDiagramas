package dataProcessors;

import display.IWindow;
import generalTools.ValueCenter;
import utilities.MyPoint;
import utilities.MyScale;

public class PointInitProcess extends PointProcess {
	MyScale scale;
	IWindow window;
	MyPoint[] initRegionPoints;
	
	public PointInitProcess(MyPoint[] points, IWindow window){
		wWidth = window.getWidth();
		wHeight = (int) (window.getHeight()*0.9);
		this.window = window; 
	
		//initPointsList = getBoundingPoints(points);
		initPointsList = points;
		initRegionPoints = points;
		endPoints = new MyPoint[2];
				
		processPoints();	
	}
	
	private MyPoint[] getBoundingPoints(MyPoint[] points){
		MyPoint[] bounding = new MyPoint[2];
		double maxX = Double.MIN_VALUE;
		double minX = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		
		for(int i=0;i<points.length;i++){
			if(points[i].getX()<minX){
				minX = points[i].getX();
			}
			
			if(points[i].getX()>maxX){
				maxX = points[i].getX();
			}
		
			if(points[i].getY()<minY){
				minY = points[i].getY();
			}
			
			if(points[i].getY()>maxY){
				maxY = points[i].getY();
			}
		}
		
		bounding[0] = new MyPoint(minX,maxY);
		bounding[1] = new MyPoint(maxX,minY);

		return bounding;
	}
		
	private void processPoints(){
		MyPoint right = initPointsList[0].getPointRight(initPointsList[1]);
		MyPoint left = initPointsList[0].getPointLeft(initPointsList[1]);
		MyPoint up = initPointsList[0].getPointUp(initPointsList[1]);
		MyPoint down = initPointsList[0].getPointDown(initPointsList[1]);
		double rel = 0.1;
			
		if((right.getX()-left.getX())<=(up.getY()-down.getY())){
			/*Coordenada mas larga es la vertical, se usa esta escala*/
			double slope = wHeight*(1-2*rel)/(up.getY()-down.getY());
			double nXR = rel*wHeight+slope*(right.getX()-down.getY());
			double nXL = rel*wHeight+slope*(left.getX()-down.getY());
					
			double[] valuesToCenter = {nXR, nXL};
			double[] values = (new ValueCenter(wWidth,valuesToCenter)).getCenteredValues();
						
			MyPoint p1 = new MyPoint(values[1],rel*wHeight);
			MyPoint p2 = new MyPoint(values[0],wHeight*(1-rel));

			endPoints[0] = p1;
			endPoints[1] = p2;
				
			scale = new MyScale(new MyPoint(left.getX(),down.getY()),p1,new MyPoint(right.getX(),up.getY()),p2,window);
		}else{
			System.out.println("aca");
			double slope = wWidth*(1-2*rel)/(right.getX()-left.getX());
			double nYUp = rel*wWidth+slope*(up.getY()-left.getX());
			double nYDown = rel*wWidth+slope*(down.getY()-left.getX());
					
			double[] valuesToCenter = {nYUp, nYDown};
			double[] values = (new ValueCenter(wHeight,valuesToCenter)).getCenteredValues();
			
			MyPoint p1 = new MyPoint(rel*wWidth,values[1]);
			MyPoint p2 = new MyPoint(wWidth*(1-rel),values[0]);
			
			endPoints[0] = p1;
			endPoints[1] = p2;
			scale = new MyScale(new MyPoint(left.getX(),down.getY()),p1,new MyPoint(right.getX(),up.getY()),p2,window);
		}
	}
	
	public MyScale getScale(){
		return scale;
	}
	
	public MyPoint[] getRegionPoints(){
		MyPoint[] newRegionPoints = new MyPoint[initRegionPoints.length];
		
		for(int i=0;i<initRegionPoints.length;i++){
			newRegionPoints[i] = scale.getPixelValue(initRegionPoints[i]);
		}
		
		return newRegionPoints;
	}

}
