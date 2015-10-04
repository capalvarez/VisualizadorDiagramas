package utilities.perforations;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;

import utilities.MyPoint;
import utilities.MyScale;

public class MyCircle implements Perforation{
	MyPoint center;
	double r;
	
	public MyCircle(MyPoint center, double radius){
		this.center = center;
		this.r = radius;
	}
		
	public void drawPerforation(Graphics2D g2d, MyScale scale){
		double scaledRadius = scale.getPixelValue(r);
		MyPoint scaledCenter = scale.getPixelValue(center);
		
		Ellipse2D.Double ellipse2D = new Ellipse2D.Double(scaledCenter.getX()-scaledRadius,scaledCenter.getY()-scaledRadius, 2*scaledRadius, 2*scaledRadius);
		g2d.draw(ellipse2D);

	}
	
	public ArrayList<MyPoint> intersectionPoints(MyPoint pA, MyPoint pB){
		ArrayList<MyPoint> returnList = new ArrayList<MyPoint>();	
		
		double baX = pB.getX() - pA.getX();
        double baY = pB.getY() - pA.getY();
        double caX = center.getX() - pA.getX();
        double caY = center.getY() - pB.getY();

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

        MyPoint p1 = new MyPoint(pA.getX() - baX * abScalingFactor1, pA.getY()
                - baY * abScalingFactor1);
        if (disc == 0) {
        	returnList.add(p1);
            return returnList;
        }
        
        MyPoint p2 = new MyPoint(pA.getX() - baX * abScalingFactor2, pA.getY()
                - baY * abScalingFactor2);
        
        returnList.add(p1);
        returnList.add(p2);
        
        return returnList;
    }
}
