package utilities.regions.circular;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import utilities.MyPoint;
import utilities.MyScale;

public class FullCircularRegion extends CircularRegion {

	public FullCircularRegion(double iR, double oR, MyPoint c, MyScale s){
		super(iR,oR,c,s);
	}
	
	public void drawRegion(Graphics2D g2d) {
		double sInnerR = scale.getPixelValue(innerR);
		double sOuterR = scale.getPixelValue(outerR);
		
		MyPoint scaledCenter = scale.getPixelValue(center);
		
		Ellipse2D.Double innerCircle = new Ellipse2D.Double(scaledCenter.getX()-sInnerR,scaledCenter.getY()-sInnerR, 2*sInnerR, 2*sInnerR);
		Ellipse2D.Double outerCircle = new Ellipse2D.Double(scaledCenter.getX()-sOuterR,scaledCenter.getY()-sOuterR, 2*sOuterR, 2*sOuterR);
		
		g2d.draw(innerCircle);
		g2d.draw(outerCircle);
	}
	
}
