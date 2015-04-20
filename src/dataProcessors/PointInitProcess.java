package dataProcessors;

import generalTools.ValueCenter;
import utilities.MyPoint;
import utilities.MyScale;

public class PointInitProcess extends PointProcess {
	MyScale scale;
	
	public PointInitProcess(MyPoint[] points, int width, int height){
		wWidth = width;
		wHeight = height;
		initPointsList = points;
		endPoints = new MyPoint[2];
		
		processPoints();
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
			int nXR = (int) Math.round(rel*wHeight+slope*(right.getY()-down.getX()));
			int nXL = (int) Math.round(rel*wHeight+slope*(left.getY()-down.getX()));
			
			System.out.println("nueva derecha" + nXR);
			System.out.println("nueva izquierda" + nXL);
			
			int[] valuesToCenter = {nXR, nXL};
			int[] values = (new ValueCenter(wWidth,valuesToCenter)).getCenteredValues();
			
			MyPoint p1 = new MyPoint(values[1],(int)Math.round(rel*wHeight));
			MyPoint p2 = new MyPoint(values[0],(int)Math.round(wHeight*(1-2*rel)));
			
			System.out.println("nueva derecha despues de centrar" + values[0]);
			System.out.println("nueva izquierda despues de centrar" + values[1]);
			
			endPoints[0] = p1;
			endPoints[1] = p2;
				
			scale = new MyScale(new MyPoint(left.getX(),down.getY()),p1,new MyPoint(right.getX(),up.getY()),p2);
		}else{
			double slope = wWidth*(1-2*rel)/(right.getX()-left.getX());
			int nYUp = (int) Math.round(rel*wWidth+slope*(up.getY()-left.getX()));
			int nYDown = (int) Math.round(rel*wWidth+slope*(down.getY()-left.getX()));
			
			int[] valuesToCenter = {nYUp, nYDown};
			int[] values = (new ValueCenter(wHeight,valuesToCenter)).getCenteredValues();
			
			MyPoint p1 = new MyPoint((int)Math.round(rel*wWidth),values[1]);
			MyPoint p2 = new MyPoint((int)Math.round(wWidth*(1-rel)),values[0]);
			
			endPoints[0] = p1;
			endPoints[1] = p2;
			scale = new MyScale(new MyPoint(left.getX(),down.getY()),p1,new MyPoint(right.getX(),up.getY()),p2);
		}
	}
	
	public MyScale getScale(){
		return scale;
	}

}
