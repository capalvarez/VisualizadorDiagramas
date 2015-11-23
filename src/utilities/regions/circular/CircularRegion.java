package utilities.regions.circular;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.perforations.Perforation;
import utilities.regions.AbstractRegion;
import utilities.regions.MyRegion;

public abstract class CircularRegion extends AbstractRegion {
	ArrayList<Perforation> perforation = new ArrayList<Perforation>();
	double innerR;
	double outerR;
	MyPoint center;
	MyScale scale;
	
	public CircularRegion(double iR, double oR, MyPoint c, MyScale s){
		innerR = iR;
		outerR = oR;
		center = c;
		scale = s;	
	}
	
	@Override
	public boolean isInside(MyPoint point) {
		// TODO Auto-generated method stub
		return false;
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
	public void addPerforation(Perforation p) {
		perforation.add(p);	
	}
	
	@Override
	public void emptyPerforations() {
		// TODO Auto-generated method stub
		
	}
}
