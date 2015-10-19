package utilities.regions.circular;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import utilities.MyPoint;
import utilities.MyScale;

public class FullCircularRegion extends CircularRegion {

	public FullCircularRegion(double iR, double oR, MyPoint c, MyScale s){
		super(iR,oR,c,s);
	}
	
	private double radian(double angle){
		return angle*Math.PI/180.0;	
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
	
	@Override
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow) {
		double delta = (outerR - innerR)/(nX-1);
		double dA = 360/nY; 
						
		MyPoint[] retPoints = new MyPoint[nX*nY];
		int k = 0;	
		
		for(int i=0;i<nX;i++){
			double rad = innerR + delta*i;
			
			for(int j=0;j<nY;j++){
				double angle = j*dA;
				double x = center.getX() + rad*Math.cos(radian(angle));
				double y = center.getY() + rad*Math.sin(radian(angle));
								
				retPoints[k] = new MyPoint(x,y);
				k++;
			}	
		}
		
		return retPoints;
	}
	
	@Override
	public MyPoint[] generateUniformByDistance(double dx, double dy, boolean secondRow) {
		int nX = (int) Math.floor((outerR-innerR)/dx);
		int nY = (int) Math.floor(360/dy);
		
		System.out.println(outerR);
		System.out.println(innerR);
		System.out.println(dx);
		
		return generateUniformByNumber(nX,nY,secondRow);
	}
}
