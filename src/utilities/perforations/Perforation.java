package utilities.perforations;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyScale;

public interface Perforation {
	public void drawPerforation(Graphics2D g2d, MyScale scale, MyPoint[] limits);
	public ArrayList<MyPoint> intersectionPoints(MyPoint a, MyPoint b);
	public boolean contains(MyPoint p);
	public String toString();

	public ArrayList<MyPoint> getPerforationPoints();
}
