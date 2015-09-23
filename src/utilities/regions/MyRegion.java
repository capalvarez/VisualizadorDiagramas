package utilities.regions;

import java.awt.Graphics2D;

import utilities.MyPoint;

public interface MyRegion {
	public boolean isInside(MyPoint point);
	public void drawRegion(Graphics2D g2d);
	public MyPoint[] generateNonUniform(double initX, double multX, double initY, double multY);
	public void setPixelValues(MyPoint[] pixelValues);
}
