package utilities.regions;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import regionBounders.ArbitraryRegionBounder;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyScale;
import utilities.perforations.Perforation;

public class ArbitraryRegion extends AbstractRegion {
	MyPoint[] points;
	MyScale scale;
	
	public ArbitraryRegion(MyPoint[] p, MyScale s){
		points = p;
		scale = s;
	}
		
	@Override
	public boolean isInside(MyPoint p) {
		int j = points.length - 1 ;
		boolean oddNodes = false;

		for (int i=0; i<points.length; i++) {
			if (points[i].getY()<p.getY() && points[j].getY()>=p.getY() 
					|| points[j].getY()<p.getY() && points[i].getY()>=p.getY()) {
			     
				if (points[i].getX() + (p.getY()-points[i].getY())/(points[j].getY()-points[i].getY())*(points[j].getX()-points[i].getX())<p.getX()){
			        oddNodes=!oddNodes; 
			    }
			}
			j = i; 
		}
		
		return oddNodes;
	}

	@Override
	public void drawRegion(Graphics2D g2d) {
		int n = points.length;
		scale.printScale();
		
		for(int i=0;i<n;i++){
			MyPoint p1 = points[i%n];
			MyPoint p2 = points[(i+1)%n];
			
			MyPoint pixelP1 = scale.getPixelValue(p1);
			MyPoint pixelP2 = scale.getPixelValue(p2);
					
			g2d.draw(new Line2D.Double(pixelP1.getX(), pixelP1.getY(), pixelP2.getX(), pixelP2.getY()));
		}
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
	public MyPoint[] generateUniformByDistance(double dx, double dy, boolean secondRow) {
		MyPoint[] bounding = (new ArbitraryRegionBounder(points)).getMinMin();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);
			
		MyPoint[] pointRec = boundingRec.generateUniformByDistance(dx, dy, secondRow);
	
		return cleanOutsidePoints(pointRec);	
	}

	@Override
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow) {
		MyPoint[] bounding = (new ArbitraryRegionBounder(points)).getMinMin();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);
		
		MyPoint[] pointRec = boundingRec.generateUniformByNumber(nX, nY, secondRow);
		
		return cleanOutsidePoints(pointRec);
	}
	
	private MyPoint[] cleanOutsidePoints(MyPoint[] p){
		ArrayList<MyPoint> inside = new ArrayList<MyPoint>();
		
		for(int i=0;i<p.length;i++){
				
			if(isInside(p[i])){
				inside.add(p[i]);
			}
		}
		
		return pointListToArray(inside);
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
	public MyEdge[] getEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyPoint[] getPoints() {
		// TODO Auto-generated method stub
		return null;
	}
}
