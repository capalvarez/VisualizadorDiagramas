package dataProcessors;

import utilities.MyPoint;

public class PointInitProcess extends PointProcess {
	
	
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
		double rel = 0.05;
		
		System.out.println(right.toString()+" "+left.toString());
		System.out.println(up.toString()+" "+down.toString());
		
		
		if((right.getX()-left.getX())<=(up.getY()-down.getY())){
			/*Coordenada mas larga es la vertical, se usa esta escala*/
			double slope = wHeight*(1-2*rel)/(up.getY()-down.getY());
			int nXR = (int) Math.round(rel*wHeight+slope*(right.getY()-down.getX()));
			int nXL = (int) Math.round(rel*wHeight+slope*(left.getY()-down.getX()));
			
			MyPoint p1 = new MyPoint(nXR,(int)Math.round(rel*wHeight));
			MyPoint p2 = new MyPoint(nXL,(int)Math.round(wHeight*(1-rel)));
			
			endPoints[0] = p1;
			endPoints[1] = p2;
			
			System.out.println(endPoints[0].toString()+ " " + endPoints[1].toString());
				
		}else{
			double slope = wWidth*(1-2*rel)/(right.getX()-left.getX());
			int nYUp = (int) Math.round(rel*wWidth+slope*(up.getY()-left.getX()));
			int nYDown = (int) Math.round(rel*wWidth+slope*(down.getY()-left.getX()));
			
			MyPoint p1 = new MyPoint((int)Math.round(rel*wWidth),nYUp);
			MyPoint p2 = new MyPoint((int)Math.round(wWidth*(1-rel)),nYDown);
			
			endPoints[0] = p1;
			endPoints[1] = p2;
			
			System.out.println(slope);
			System.out.println(endPoints[0].toString()+ " " + endPoints[1].toString());
			
		}
	}

}
