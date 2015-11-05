package utilities.regions.circular;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import drawers.PointDrawer;

import utilities.MyPoint;
import utilities.MyScale;

public class PartialCircularRegion extends CircularRegion {
	double[] angles;
	
	public PartialCircularRegion(double iR, double oR, MyPoint c, MyScale s, double[] angles){
		super(iR,oR,c,s);
		this.angles = angles;
	}
	
	private double radian(double angle){
		return angle*Math.PI/180.0;	
	}
	
	private double getInnerPerimeter(){
		return Math.abs(radian(angles[0]) - radian(angles[1]))*innerR;
	}
	
	private double getOuterPerimeter(){
		return Math.abs(radian(angles[0]) - radian(angles[1]))*outerR;
	}
	
	@Override
	public void drawRegion(Graphics2D g2d) {
		double sInnerR = scale.getPixelValue(innerR);
		double sOuterR = scale.getPixelValue(outerR);
		
		MyPoint scaledCenter = scale.getPixelValue(center);
				
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sInnerR,scaledCenter.getY()+sInnerR,2*sInnerR,2*sInnerR,angles[0],angles[1], Arc2D.OPEN));
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sOuterR,scaledCenter.getY(),2*sOuterR,2*sOuterR,angles[0],angles[1], Arc2D.OPEN));
					
		MyPoint pInner1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sInnerR,scaledCenter.getY()+Math.sin(radian(angles[0]))*sInnerR + sOuterR);
		MyPoint pInner2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sInnerR,scaledCenter.getY()-Math.sin(radian(angles[1]))*sInnerR + sOuterR);
		MyPoint pOuter1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sOuterR,scaledCenter.getY()+(1 + Math.sin(radian(angles[0])))*sOuterR);
		MyPoint pOuter2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sOuterR,scaledCenter.getY()+(1 - Math.sin(radian(angles[1])))*sOuterR);

		g2d.draw(new Line2D.Double(pInner1.getX(), pInner1.getY(), pOuter1.getX(), pOuter1.getY()));
		g2d.draw(new Line2D.Double(pInner2.getX(), pInner2.getY(), pOuter2.getX(), pOuter2.getY()));
	}

	@Override
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow) {
		double delta = (outerR - innerR)/(nX-1);
		double dA = Math.abs(angles[1]-angles[0])/(nY-1); 
	
		MyPoint[] retPoints = new MyPoint[nX*nY];
		int k = 0;	
		
		for(int i=0;i<nX;i++){
			double rad = innerR + delta*i;
			
			for(int j=0;j<nY;j++){
				double angle = angles[0] + j*dA;
				double x = center.getX() + rad*Math.cos(radian(angle));
				double y = center.getY() + outerR - rad*Math.sin(radian(angle));
								
				retPoints[k] = new MyPoint(x,y);
				k++;
			}	
		}
		
		return retPoints;
	}
	
	@Override
	public MyPoint[] generateUniformByDistance(double dx, double dy,boolean secondRow) {
		int nX = (int) Math.floor((outerR-innerR)/dx);
		int nY = (int) Math.floor(Math.abs(angles[1]-angles[0])/dy);
	
		return generateUniformByNumber(nX,nY,secondRow);
	}
	
	@Override
	public MyPoint[] generateBorderByNumber(int[] numbers, boolean forAll) {
		int total;
		double dInt;
		double dExt;
		
		if(!forAll){
			dInt = Math.abs(angles[1]-angles[0])/(numbers[0]-1); 
			dExt = Math.abs(angles[1]-angles[0])/(numbers[0]-1);
			
			total = 2*numbers[0];
		}else{
			dInt = Math.abs(angles[1]-angles[0])/(numbers[0]-1); 
			dExt = Math.abs(angles[1]-angles[0])/(numbers[1]-1);
			
			total = numbers[0] + numbers[1];
		}
		
		MyPoint[] retPoints = new MyPoint[total];
		int k = 0;	
		
		for(int i=0;i<numbers[0];i++){	
			double angle = i*dInt;
			double x = center.getX() + innerR*Math.cos(radian(angle));
			double y = center.getY() + outerR - innerR*Math.sin(radian(angle));
								
			retPoints[k] = new MyPoint(x,y);
			k++;		
		}
		
		for(int i=0;i<numbers[0];i++){	
			double angle = i*dExt;
			double x = center.getX() + outerR*Math.cos(radian(angle));
			double y = center.getY() + outerR - outerR*Math.sin(radian(angle));
								
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
}
