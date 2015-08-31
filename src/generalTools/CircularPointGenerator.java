package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyRegion;

public class CircularPointGenerator {
	MyPoint[] points;
	OutsidePointsCleaner opc = new OutsidePointsCleaner();
	
	public CircularPointGenerator(double iX,double iY,double initRad,double mult,double sep,MyRegion region){		
		/*Agregar el punto central, es opcional en caso de que no quieran*/
		ArrayList<MyPoint> result = new ArrayList<MyPoint>();
		result.add(new MyPoint(iX,iY));
		double currentRad = initRad;
		boolean inside = true;
		double sepRad = sep*Math.PI/180;	
		
		while(inside){
			double angle = 0;
			inside = false;
			
			while(angle<2*Math.PI){
				double x = iX + currentRad*Math.cos(angle);
				double y = iY + currentRad*Math.sin(angle);
				MyPoint newPoint = new MyPoint(x,y);
									
				if(opc.inside(newPoint, region)){
					result.add(newPoint);
					inside = true;
				}
				
				angle = angle + sepRad;
			}
			
			currentRad = currentRad*mult;
		}

		points = new MyPoint[result.size()];
		points = result.toArray(points);
	
	}

	
	public MyPoint[] getPoints(){
		return points;
	}

	
}
