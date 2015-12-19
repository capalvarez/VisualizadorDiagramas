package utilities.regions;

import generalTools.LinePointGenerator;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import regionBounders.ArbitraryRegionBounder;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
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
			if (points[i].getY()<=p.getY() && points[j].getY()>=p.getY() 
					|| points[j].getY()<=p.getY() && points[i].getY()>=p.getY()) {
			     
				if (points[i].getX() + (p.getY()-points[i].getY())/(points[j].getY()-points[i].getY())*(points[j].getX()-points[i].getX())<p.getX()){
			        oddNodes=!oddNodes; 
			    }
			}
			j = i; 
		}
		
		return oddNodes || inEdges(p);
	}
	
	public boolean inEdges(MyPoint p){
		boolean inEdge = false;
		int n = points.length;
		
		for(int i=0;i<n;i++){
			if(new InternalEdge(points[i],points[(i+1)%n]).inEdge(p)){
				inEdge = true;
				break;
			}
		}
		
		return inEdge;
	}

	@Override
	public void drawRegion(Graphics2D g2d) {
		int n = points.length;
		
		for(int i=0;i<n;i++){
			MyPoint p1 = points[i%n];
			MyPoint p2 = points[(i+1)%n];
			
			MyPoint pixelP1 = scale.getPixelValue(p1);
			MyPoint pixelP2 = scale.getPixelValue(p2);
					
			g2d.draw(new Line2D.Double(pixelP1.getX(), pixelP1.getY(), pixelP2.getX(), pixelP2.getY()));
		}
	}

	@Override
	public MyPoint[] generateNonUniform(double initX, double multX, double initY, double multY) {
		MyPoint[] bounding = (new ArbitraryRegionBounder(points)).getMinMin();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);
				
		return cleanOutsidePoints(boundingRec.generateNonUniform(initX,multX,initY,multY));
		
	}

	@Override
	public MyPoint[] generateRandom(int number) {
		MyPoint[] bounding = (new ArbitraryRegionBounder(points)).getMinMin();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);
				
		return cleanOutsidePoints(boundingRec.generateRandom(number));
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
		
	@Override
	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll) {
//		int n = points.length;
//		
//		if(!forAll){
//			double dX = numbers[0];
//			double dY = numbers[1];
//							
//			LinePointGenerator lpg = new LinePointGenerator();
//					
//			for(int i=0;i<n;i++){
//				
//			}
//			
//			MyPoint[] pointArrayLine1 = lpg.getPointsY(getUpCorner(),getDownCorner(),dY,getLeftCorner());
//			MyPoint[] pointArrayLine2 = lpg.getPointsX(getLeftCorner(),getRightCorner(),dX,getDownCorner());
//			MyPoint[] pointArrayLine3 = lpg.getPointsY(getUpCorner(),getDownCorner(),dY,getRightCorner());
//			MyPoint[] pointArrayLine4 = lpg.getPointsX(getLeftCorner(),getRightCorner(),dX,getUpCorner());
//		
//			MyPoint[] both = ArrayUtils.addAll(pointArrayLine1, pointArrayLine2);
//			MyPoint[] both2 = ArrayUtils.addAll(pointArrayLine3,pointArrayLine4);
//			MyPoint[] both3 = ArrayUtils.addAll(both,both2);
//						
//			return both3;
//			
//		}else{		
//			double d1 = distances[0];
//			double d2 = distances[1];
//			double d3 = distances[2];
//			double d4 = distances[3];
//												
//			LinePointGenerator lpg = new LinePointGenerator();
//			
//			MyPoint[] pointArrayLine1 = lpg.getPointsY(getUpCorner(),getDownCorner(),d1,getLeftCorner());
//			MyPoint[] pointArrayLine2 = lpg.getPointsX(getLeftCorner(),getRightCorner(),d2,getDownCorner());
//			MyPoint[] pointArrayLine3 = lpg.getPointsY(getUpCorner(),getDownCorner(),d3,getRightCorner());
//			MyPoint[] pointArrayLine4 = lpg.getPointsX(getLeftCorner(),getRightCorner(),d4,getUpCorner());
//		
//			MyPoint[] both = ArrayUtils.addAll(pointArrayLine1, pointArrayLine2);
//			MyPoint[] both2 = ArrayUtils.addAll(pointArrayLine3,pointArrayLine4);
//			MyPoint[] both3 = ArrayUtils.addAll(both,both2);
//						
//			return both3;
//		}
		
		return null;
	}

	@Override
	public MyPoint[] generateBorderByDistance(double[] distances, boolean forAll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyEdge[] getEdges() {
		int n = points.length;
		MyEdge[] edges = new MyEdge[n];
		
		for(int i=0;i<n;i++){
			edges[i] = new InternalEdge(i%n+1,(i+1)%n+1);
		}
		
		return edges;
	}

	@Override
	public MyPoint[] getPoints() {
		return points;
	}
}
