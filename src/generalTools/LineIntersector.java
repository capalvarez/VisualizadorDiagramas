package generalTools;

import utilities.MyPoint;
import utilities.edges.MyEdge;

public class LineIntersector {
	private MyEdge testLine;
	
	public LineIntersector(MyEdge testLine){
		this.testLine = testLine;
	}	
		
	public MyPoint getIntersection(MyEdge e){
		MyPoint[] tP = testLine.getPoints();
		MyPoint[] eP = e.getPoints();
		
		double denominator = (eP[1].getY()-eP[0].getY())*(tP[1].getX()-tP[0].getX()) - (eP[1].getX()-eP[0].getX())*(tP[1].getY()-tP[0].getY());
		double numA = (eP[1].getX()-eP[0].getX())*(tP[0].getY()-tP[0].getY()) - (eP[1].getY()-eP[0].getY())*(tP[0].getX()-eP[0].getX());
		double numB = (tP[1].getX()-tP[0].getX())*(tP[0].getY()-tP[0].getY()) - (tP[1].getY()-tP[0].getY())*(tP[0].getX()-eP[0].getX());
		
		if(denominator==0){	
			if(numA==0 && numB==0){
			/*Caso en que las rectas son coincidentes, por mi sanidad mental espero que no pase*/	
			}
			return null;
		}else{

			double uA = numA/denominator;
			double uB = numB/denominator;
		
			if(uA<0 || uA>1 || uB<0 || uB>1){
				return null;
			}
			
			int x = (int) (tP[0].getX() + uA*(tP[1].getX() - tP[0].getX()));
			int y = (int) (tP[0].getY() + uA*(tP[1].getY() - tP[0].getY()));
			
			return new MyPoint(x,y);
		}
		 
		
	}
}