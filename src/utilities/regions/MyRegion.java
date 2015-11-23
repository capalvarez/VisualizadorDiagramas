package utilities.regions;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.edges.MyEdge;
import utilities.perforations.Perforation;

public interface MyRegion {
	public boolean isInside(MyPoint point);
	public void drawRegion(Graphics2D g2d);
	
	public MyEdge[] getEdges();
	public MyPoint[] getPoints();
	public MyPoint[] getPerforationPoints();

	public MyPoint[] generateNonUniform(double initX, double multX, double initY, double multY);
	public MyPoint[] generateRandom(int number);
	public MyPoint[] generateUniformByDistance(double dx, double dy, boolean secondRow);
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow);

	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll);
	public MyPoint[] generateBorderByDistance(double[] distances, boolean forAll);
	
	public void addPerforation(Perforation p);
	public ArrayList<Perforation> getPerforationList();
	public void emptyPerforations();

}
