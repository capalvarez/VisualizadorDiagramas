package drawers;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import utilities.MyPoint;

public class PointDrawer {
	private MyPoint[] pointsToDraw;
	private int pointSize;
	
	public PointDrawer(MyPoint[] points, int size){
		this.pointsToDraw = points;
		this.pointSize = size;
	}
	
	public void drawPoints(Graphics2D g2d){
	
		for (int i = 0; i < pointsToDraw.length; i++) {
	       	double x = pointsToDraw[i].getX()-(pointSize/2);
	       	double y = pointsToDraw[i].getY()-(pointSize/2);
	       	g2d.draw(new Ellipse2D.Double(x,y,pointSize,pointSize));   
	    }
	}

}
