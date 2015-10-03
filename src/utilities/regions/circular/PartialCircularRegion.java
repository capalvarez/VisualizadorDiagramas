package utilities.regions.circular;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import drawers.PointDrawer;

import utilities.MyPoint;
import utilities.MyScale;

public class PartialCircularRegion extends CircularRegion {
	double[] angles;
	
	public PartialCircularRegion(double iR, double oR, MyPoint c, MyScale s, double[] angles){
		super(iR,oR,c,s);
		this.angles = angles;
	}
	
	private double radian(double angle){
		return angle*Math.PI/180.0;	
	}
	
	@Override
	public void drawRegion(Graphics2D g2d) {
		double sInnerR = scale.getPixelValue(innerR);
		double sOuterR = scale.getPixelValue(outerR);
		
		MyPoint scaledCenter = scale.getPixelValue(center);
		
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sInnerR,scaledCenter.getY()+sInnerR,2*sInnerR,2*sInnerR,angles[0],angles[1]-angles[0], Arc2D.OPEN));
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sOuterR,scaledCenter.getY(),2*sOuterR,2*sOuterR,angles[0],angles[1]-angles[0], Arc2D.OPEN));
			
		MyPoint pInner1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sInnerR,scaledCenter.getY()-Math.sin(radian(angles[0]))*sInnerR + sOuterR);
		MyPoint pInner2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sInnerR,scaledCenter.getY()-Math.sin(radian(angles[1]))*sInnerR + sOuterR);
		MyPoint pOuter1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sOuterR,scaledCenter.getY()+(1 - Math.sin(radian(angles[0])))*sOuterR);
		MyPoint pOuter2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sOuterR,scaledCenter.getY()+(1 - Math.sin(radian(angles[1])))*sOuterR);

		g2d.draw(new Line2D.Double(pInner1.getX(), pInner1.getY(), pOuter1.getX(), pOuter1.getY()));
		g2d.draw(new Line2D.Double(pInner2.getX(), pInner2.getY(), pOuter2.getX(), pOuter2.getY()));
	}
}
