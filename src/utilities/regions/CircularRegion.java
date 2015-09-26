package utilities.regions;

import java.awt.Graphics2D;

import utilities.MyPoint;
import utilities.perforations.Perforation;

public class CircularRegion implements MyRegion {

	@Override
	public boolean isInside(MyPoint point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawRegion(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public MyPoint[] generateNonUniform(double initX, double multX, double initY,
			double multY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] generateRandom(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] generateUniformByDistance(double dx, double dy,
			boolean secondRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] generateBorderByDistance(double[] distances, boolean forAll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPerforation(Perforation p) {
		// TODO Auto-generated method stub
		
	}

}
