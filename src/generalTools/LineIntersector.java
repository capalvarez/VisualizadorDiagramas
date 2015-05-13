package generalTools;

import javax.swing.JFrame;
import javax.swing.JPanel;

import display.DrawPanel;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;

public class LineIntersector {
	private double EPSILON = 0.000001;
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
	
	public static void main(String[] args){
		MyPoint p1 = new MyPoint(0,0);
		MyPoint p2 = new MyPoint(0,100);
		MyPoint p3 = new MyPoint(100,100);
		MyPoint p4 = new MyPoint(100,0);
		
		MyEdge e = new MyEdge(p1,p4);
		MyEdge e1 = new MyEdge(new MyPoint(10,10),new MyPoint(10,-10));
		
		LineIntersector lI = new LineIntersector(e);
		
		MyPoint p = lI.getIntersection(e1);
		System.out.println(p);
		//MyRegion ej = new MyRegion(p1,p2);
	
		
	    
	}


}