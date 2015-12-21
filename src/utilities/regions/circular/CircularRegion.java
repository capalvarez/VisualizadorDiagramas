package utilities.regions.circular;

import java.util.ArrayList;

import regionBounders.ArbitraryRegionBounder;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.perforations.MyCircle;
import utilities.perforations.Perforation;
import utilities.regions.AbstractRegion;
import utilities.regions.RectangleRegion;

public abstract class CircularRegion extends AbstractRegion {
	ArrayList<Perforation> perforation = new ArrayList<Perforation>();
	ArrayList<MyPoint> innerPointsDiscrete;
	ArrayList<MyPoint> outerPointsDiscrete;
	
	double innerR;
	double outerR;
	MyPoint center;
		
	public CircularRegion(double iR, double oR, MyPoint c, MyScale s){
		innerR = iR;
		outerR = oR;
		center = c;
		scale = s;	
	}

	@Override
	public MyPoint[] generateNonUniform(double initX, double multX, double initY,
			double multY) {
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
	
	@Override
	public ArrayList<Perforation> getPerforationList(){
		perforation.add(new MyCircle(center,innerR));
		
		return perforation;
	}
}
