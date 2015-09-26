package utilities.perforations;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

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
	
}
