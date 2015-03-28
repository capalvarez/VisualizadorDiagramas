package drawers;

import java.awt.Graphics2D;

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
	       	int x = pointsToDraw[i].getX()-(pointSize/2);
	       	int y = pointsToDraw[i].getY()-(pointSize/2);
	       	g2d.fillOval(x,y,pointSize,pointSize);   
	    }
	}

}
