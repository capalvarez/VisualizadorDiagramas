package utilities.regions.circular;

import generalTools.CurveDiscretizer;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.perforations.MyCircle;
import utilities.perforations.Perforation;

public class FullCircularRegion extends CircularRegion {
	
	
	public FullCircularRegion(double iR, double oR, MyPoint c, MyScale s){
		super(iR,oR,c,s);
		
		innerPointsDiscrete = new CurveDiscretizer(outerR,center).discretizeCircle(20);
		outerPointsDiscrete = new CurveDiscretizer(innerR,center).discretizeCircle(20);
	}
	
	private double radian(double angle){
		return angle*Math.PI/180.0;	
	}
	
	private double getInnerPerimeter(){
		return 2*Math.PI*innerR;
	}
	
	private double getOuterPerimeter(){
		return 2*Math.PI*outerR;
	}
	
	public void drawRegion(Graphics2D g2d) {
		double sInnerR = scale.getPixelValue(innerR);
		double sOuterR = scale.getPixelValue(outerR);
		
		MyPoint scaledCenter = scale.getPixelValue(center);
		
		Ellipse2D.Double innerCircle = new Ellipse2D.Double(scaledCenter.getX()-sInnerR,scaledCenter.getY()-sInnerR, 2*sInnerR, 2*sInnerR);
		Ellipse2D.Double outerCircle = new Ellipse2D.Double(scaledCenter.getX()-sOuterR,scaledCenter.getY()-sOuterR, 2*sOuterR, 2*sOuterR);
		
		g2d.draw(innerCircle);
		g2d.draw(outerCircle);
	}
	
	@Override
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow) {
		double delta = (outerR - innerR)/(nX-1);
		double dA = 360/nY; 
						
		MyPoint[] retPoints = new MyPoint[nX*nY];
		int k = 0;	
		
		for(int i=0;i<nX;i++){
			double rad = innerR + delta*i;
			
			for(int j=0;j<nY;j++){
				double angle = j*dA;
				double x = center.getX() + rad*Math.cos(radian(angle));
				double y = center.getY() + rad*Math.sin(radian(angle));
								
				retPoints[k] = new MyPoint(x,y);
				k++;
			}	
		}
		
		return retPoints;
	}
	
	@Override
	public MyPoint[] generateUniformByDistance(double dx, double dy, boolean secondRow) {
		int nX = (int) Math.floor((outerR-innerR)/dx);
		int nY = (int) Math.floor(360/dy);
	
		return generateUniformByNumber(nX,nY,secondRow);
	}

	@Override
	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll) {
		int total;
		double dInt;
		double dExt;
		
		if(!forAll){
			dInt = 360/numbers[0]; 
			dExt = 360/numbers[0];
			
			total = 2*numbers[0];
		}else{
			dInt = 360/numbers[0]; 
			dExt = 360/numbers[1];
			
			total = numbers[0] + numbers[1];
		}
		
		MyPoint[] retPoints = new MyPoint[total];
		int k = 0;	
		
		for(int i=0;i<numbers[0];i++){	
			double angle = i*dInt;
			double x = center.getX() + innerR*Math.cos(radian(angle));
			double y = center.getY() + innerR*Math.sin(radian(angle));
								
			retPoints[k] = new MyPoint(x,y);
			k++;		
		}
		
		for(int i=0;i<numbers[0];i++){	
			double angle = i*dExt;
			double x = center.getX() + outerR*Math.cos(radian(angle));
			double y = center.getY() + outerR*Math.sin(radian(angle));
								
			retPoints[k] = new MyPoint(x,y);
			k++;		
		}
		
		return retPoints;
	}

	@Override
	public MyPoint[] generateBorderByDistance(double[] distances, boolean forAll) {
		if(!forAll){
			int nInner = (int) Math.floor(getInnerPerimeter()/distances[0]);
			int nOuter = (int) Math.floor(getOuterPerimeter()/distances[0]);
			
			int[] array = {nInner,nOuter};
			
			return generateBorderByNumber(array,forAll);
			
		}else{
			int n1 = (int) Math.floor(getInnerPerimeter()/distances[0]);
			int n2 = (int) Math.floor(getOuterPerimeter()/distances[1]);
			
			int[] array = {n1,n2};
			
			return generateBorderByNumber(array,forAll);
		}
	}

	@Override
	public MyEdge[] getEdges() {
		MyEdge[] edges = new MyEdge[outerPointsDiscrete.size() + innerPointsDiscrete.size()];
		
		int j = 0;
		int nOut = outerPointsDiscrete.size();
		
		for(int i=0;i<nOut;i++){
			edges[j] = new InternalEdge(i%nOut+1,(i+1)%nOut+1);
			j++;
		}
		
		int nIn = innerPointsDiscrete.size();
		
		for(int i=0;i<nIn;i++){
			edges[j] = new InternalEdge(i%nIn+1+nOut,(i+1)%nIn+1+nOut);
			j++;
		}
		
		return edges;	
	}

	@Override
	public MyPoint[] getPoints() {		
		ArrayList<MyPoint> circlePoints = new ArrayList<MyPoint>();
			
		circlePoints.addAll(outerPointsDiscrete);
		circlePoints.addAll(innerPointsDiscrete);
						
		return pointListToArray(circlePoints);
	}
	

}
