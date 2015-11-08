package utilities.perforations;

import generalTools.CurveDiscretizer;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utilities.MyPoint;
import utilities.MyScale;

public class MyCircle implements Perforation{
	MyPoint center;
	double r;
	
	public MyCircle(MyPoint center, double radius){
		this.center = center;
		this.r = radius;
	}
		
	public void drawPerforation(Graphics2D g2d, MyScale scale, MyPoint[] p){
		double scaledRadius = scale.getPixelValue(r);
		MyPoint scaledCenter = scale.getPixelValue(center);
		
		if(p==null){
			Ellipse2D.Double ellipse2D = new Ellipse2D.Double(scaledCenter.getX()-scaledRadius,scaledCenter.getY()-scaledRadius, 2*scaledRadius, 2*scaledRadius);
			g2d.draw(ellipse2D);
		}else{
			MyPoint smallest = p[0].getSmallestPoint(p[1]);
			MyPoint greatest = p[0].getGreatestPoint(p[1]);
			
			double heightInit = smallest.getY()-center.getY();
			double heightEnd = greatest.getY()-center.getY();			
			
			double widthInit = smallest.getX()-center.getX();
			double widthEnd = greatest.getX()-center.getX();
			
			double initAngle = 180*Math.atan(heightInit/widthInit)/Math.PI;
			double endAngle = 180*Math.atan(heightEnd/widthEnd)/Math.PI;
			
			if(heightInit==0 && widthInit<0){
				initAngle = 180;
			}
			
			if(heightEnd==0 && widthEnd<0){
				endAngle = 180;
			}
		
			g2d.draw(new Arc2D.Double(scaledCenter.getX()-scaledRadius,scaledCenter.getY()-scaledRadius,
					2*scaledRadius,2*scaledRadius,initAngle,Math.abs(endAngle-initAngle), Arc2D.OPEN));		
		}
		

	}
	
	public ArrayList<MyPoint> intersectionPoints(final MyPoint pA, final MyPoint pB){
		ArrayList<MyPoint> returnList = new ArrayList<MyPoint>();		
		
		double baX = pB.getX() - pA.getX();
        double baY = pB.getY() - pA.getY();
        double caX = center.getX() - pA.getX();
        double caY = center.getY() - pA.getY();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - r * r;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
             return returnList;
        }
        
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        MyPoint p1 = new MyPoint(pA.getX() - baX * abScalingFactor1, Math.abs(pA.getY()
                - baY * abScalingFactor1));
        if (disc == 0) {
        	if(inSegment(pA,pB,p1)){
           		returnList.add(p1);
        	}
        	
            return returnList;
        }
        
        MyPoint p2 = new MyPoint(pA.getX() - baX * abScalingFactor2, Math.abs(pA.getY()
                - baY * abScalingFactor2));
        
        if(inSegment(pA,pB,p1)){
    		returnList.add(p1);
    	}
        
        if(inSegment(pA,pB,p2)){
    		returnList.add(p2);
    	}
        
        Collections.sort(returnList, new Comparator<MyPoint>() {
	         @Override
	         public int compare(MyPoint p1, MyPoint p2) {
	        	 MyPoint smallest = pA.getSmallestPoint(pB);
	           	 double diff = p1.distance(smallest) - p2.distance(smallest); 
	             	        	 
	        	 if(diff<0){
	             	return -1;
	             }else{
	            	if(diff==0)
	            		return 0;
	            	else
	            		return 1;
	             }
	         }
	     });
        
        return returnList;
    }
	
	public boolean inSegment(MyPoint pA, MyPoint pB, MyPoint pC){
		double AB = Math.sqrt(Math.pow(pB.getX()-pA.getX(),2)+Math.pow(pB.getY()-pA.getY(),2));
		double AP = Math.sqrt(Math.pow(pC.getX()-pA.getX(),2)+Math.pow(pC.getY()-pA.getY(),2));
		double PB = Math.sqrt(Math.pow(pB.getX()-pC.getX(),2)+Math.pow(pB.getY()-pC.getY(),2));
			
		if(AB == AP + PB){
			if(pA.getPointLeft(pB).getX()<=pC.getX() && pA.getPointRight(pB).getX()>=pC.getX() &&
					pA.getPointDown(pB).getY()<=pC.getY() && pA.getPointUp(pB).getY()>=pC.getY()){
				return true;
			}else{
				return false;
			}	
		}else{
			return false;
		}    
	}
	
	public boolean contains(MyPoint p){
		return Math.pow((p.getX() - center.getX()),2) + Math.pow((p.getY() - center.getY()),2) < Math.pow(r,2);
	}
	
	public String toString(){
		return center.toString()+ "   "+ r;
	}
	
	public ArrayList<MyPoint> getPerforationPoints(){
		CurveDiscretizer c = new CurveDiscretizer(r,center);
		
		return c.discretizeCircle(20);
	}
}
