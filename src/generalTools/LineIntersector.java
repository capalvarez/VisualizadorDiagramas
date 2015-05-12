package generalTools;

import utilities.MyEdge;
import utilities.MyPoint;

public class LineIntersector {
	private double EPSILON = 0.000001;
	private MyEdge testLine;
	
	public LineIntersector(MyEdge testLine){
		this.testLine = testLine;
	}	
		
	public MyPoint getIntersection(MyEdge e){
		MyPoint[] tP = testLine.getPoints();
		MyPoint[] eP = e.getPoints();
		
		int denominator = (eP[1].getY()-eP[0].getY())*(tP[1].getX()-tP[0].getX()) - (eP[1].getX()-eP[0].getX())*(tP[1].getY()-tP[0].getY());
		int numA = (eP[1].getX()-eP[0].getX())*(tP[0].getY()-tP[0].getY()) - (eP[1].getY()-eP[0].getY())*(tP[0].getX()-eP[0].getX());
		int numB = (tP[1].getX()-tP[0].getX())*(tP[0].getY()-tP[0].getY()) - (tP[1].getY()-tP[0].getY())*(tP[0].getX()-eP[0].getX());
		
		if(denominator==0){	
			if(numA==0 && numB==0){
				
			}
		
			return null;
		}else{

			int uA = numA/denominator;
			int uB = numB/denominator;
		
			if(uA<0 || uA>1 || uB<0 || uB>1){
				return null;
			}
			
			return new MyPoint(uA,uB);
		}
		 
		
	}


}