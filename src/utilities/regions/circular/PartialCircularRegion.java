package utilities.regions.circular;

import generalTools.CurveDiscretizer;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import regionBounders.CircleBounder;

import drawers.PointDrawer;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.perforations.Perforation;
import utilities.regions.RectangleRegion;

public class PartialCircularRegion extends CircularRegion {
	double[] angles;
	
	public PartialCircularRegion(double iR, double oR, MyPoint c, MyScale s, double[] angles){
		super(iR,oR,c,s);
		this.angles = angles;
		
		innerPointsDiscrete = new CurveDiscretizer(innerR,center).discretizeArc(angles[0],angles[1],20);
		outerPointsDiscrete = new CurveDiscretizer(outerR,center).discretizeArc(angles[0],angles[1],20);
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
	public boolean isInside(MyPoint p) {
		double value = Math.pow((p.getX() - center.getX()),2) + Math.pow((p.getY() - center.getY()),2);
		boolean circle = value <= Math.pow(outerR,2) && value >= Math.pow(innerR,2); 		
		
		MyPoint[] bounding = (new CircleBounder(center,outerR,angles)).otherCircleBounding();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);	
		System.out.println(boundingRec);
		
		return circle && boundingRec.isInside(p);
	}
	
	@Override
	public void drawRegion(Graphics2D g2d) {
		double sInnerR = scale.getPixelValue(innerR);
		double sOuterR = scale.getPixelValue(outerR);
		
		MyPoint scaledCenter = scale.getPixelValue(center);
				
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sInnerR,scaledCenter.getY()-sInnerR,2*sInnerR,2*sInnerR,angles[0],angles[1], Arc2D.OPEN));
		g2d.draw(new Arc2D.Double(scaledCenter.getX()-sOuterR,scaledCenter.getY()-sOuterR,2*sOuterR,2*sOuterR,angles[0],angles[1], Arc2D.OPEN));
					
		MyPoint pInner1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sInnerR,scaledCenter.getY()+Math.sin(radian(angles[0]))*sInnerR);
		MyPoint pInner2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sInnerR,scaledCenter.getY()-Math.sin(radian(angles[1]))*sInnerR);
		MyPoint pOuter1 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[0]))*sOuterR,scaledCenter.getY()+ Math.sin(radian(angles[0]))*sOuterR);
		MyPoint pOuter2 = new MyPoint(scaledCenter.getX()+Math.cos(radian(angles[1]))*sOuterR,scaledCenter.getY()- Math.sin(radian(angles[1]))*sOuterR);

		g2d.draw(new Line2D.Double(pInner1.getX(), pInner1.getY(), pOuter1.getX(), pOuter1.getY()));
		g2d.draw(new Line2D.Double(pInner2.getX(), pInner2.getY(), pOuter2.getX(), pOuter2.getY()));
		
		for (Perforation p : perforation) {
			if (!processIntersection(p)) {
				p.drawPerforation(g2d, scale, null);
			} else {

			}
		}
	}

	public boolean processIntersection(Perforation p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public MyPoint[] generateRandom(int number) {
		MyPoint[] bounding = (new CircleBounder(center,outerR,angles)).otherCircleBounding();
		RectangleRegion boundingRec = new RectangleRegion(bounding,scale);
				
		return cleanOutsidePoints(boundingRec.generateRandom(number));
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
				double y = center.getY() + rad*Math.sin(radian(angle));
								
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

	@Override
	public MyEdge[] getEdges() {
		MyEdge[] edges = new MyEdge[outerPointsDiscrete.size() + innerPointsDiscrete.size()];
		
		int j = 0;
		int nOut = outerPointsDiscrete.size();
		
		for(int i=1;i<nOut;i++){
			edges[j] = new InternalEdge(i%nOut,i%nOut+1);
			j++;
		}
		
		int nIn = innerPointsDiscrete.size();
		
		for(int i=1;i<nIn;i++){
			edges[j] = new InternalEdge(i%nIn+nOut,i%nIn+nOut+1);
			j++;
		}
		
		edges[j] = new InternalEdge(1,nOut+1);
		edges[j+1] = new InternalEdge(nOut,nOut+nIn);
		
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
