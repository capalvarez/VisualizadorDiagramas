package utilities.regions;

import java.awt.Graphics2D;

import utilities.MyPoint;
import utilities.perforations.Perforation;

public interface MyRegion {
	
	public boolean isInside(MyPoint point);
	public void drawRegion(Graphics2D g2d);

	public MyPoint[] generateNonUniform(double initX, double multX, double initY, double multY);
	public MyPoint[] generateRandom(int number);
	public MyPoint[] generateUniformByDistance(double dx, double dy, boolean secondRow);
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow);

	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll);
	public MyPoint[] generateBorderByDistance(double[] distances, boolean forAll);
	
	public void addPerforation(Perforation p);

}
