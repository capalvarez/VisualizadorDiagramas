package drawers;

import java.awt.Graphics2D;

import utilities.MyPoint;

public class CoordSysDrawer {
	private MyPoint origin;
	private int scaleToDraw;
	
	public CoordSysDrawer(int scale){
		this.origin = origin;
		this.scaleToDraw = scale;
	}
	
	public void setOrigin(MyPoint point){
		origin = point;
	}
	
	public void drawOrigin(Graphics2D g2d){
		g2d.drawLine(origin.getX(), origin.getY(), origin.getX()+5*scaleToDraw, origin.getY());
		g2d.drawLine(origin.getX(), origin.getY(), origin.getX(), origin.getY()-5*scaleToDraw);
	}

}
