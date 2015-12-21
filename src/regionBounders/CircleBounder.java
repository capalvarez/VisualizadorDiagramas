package regionBounders;

import utilities.MyPoint;

public class CircleBounder {
	MyPoint origin;
	double r;
	double[] angles;
	
	public CircleBounder(MyPoint origin, double radius, double[] angles){
		this.origin = origin;
		r = radius;
		
//		if(angles!=null){
//			this.angles = new double[2];
//			this.angles[0] = angles[0]*Math.PI/180;
//			this.angles[1] = angles[1]*Math.PI/180;
//		}	
		this.angles = angles;
	
	}
	
	public MyPoint[] circleBounding(){
		MyPoint[] array = {new MyPoint(origin.getX()-r,origin.getY()-r),new MyPoint(origin.getX()+r,origin.getY()+r)};
				
		return array;
	}
	
	public MyPoint[] halfCircleBounding(){
		MyPoint[] array = {new MyPoint(origin.getX()-r,origin.getY()),new MyPoint(origin.getX()+r,origin.getY()+r)};
		
		return array;
	}
	
	public MyPoint[] quarterCircleBounding(){
		MyPoint[] array = {origin,new MyPoint(origin.getX()+r,origin.getY()+r)};
		
		return array;
	}
	
	public MyPoint[] otherCircleBounding(double otherR){
		double minX = Math.min(Math.min(origin.getX()+otherR*Math.cos(angles[0]),origin.getX()+otherR*Math.cos(angles[1])),
				Math.min(origin.getX()+r*Math.cos(angles[0]),origin.getX()+r*Math.cos(angles[1])));
		double maxX = Math.max(Math.max(origin.getX()+otherR*Math.cos(angles[0]),origin.getX()+otherR*Math.cos(angles[1])), 
				Math.max(origin.getX()+r*Math.cos(angles[0]),origin.getX()+r*Math.cos(angles[1])));
		
		double minY = Math.min(Math.min(origin.getY()+otherR*Math.sin(angles[0]),origin.getY()+otherR*Math.sin(angles[1])),
				Math.min(origin.getY()+r*Math.sin(angles[0]),origin.getY()+r*Math.sin(angles[1])));
		
		double maxY = Math.max(Math.max(origin.getY()+otherR*Math.sin(angles[0]),origin.getY()+otherR*Math.sin(angles[1])), 
				Math.max(origin.getY()+r*Math.sin(angles[0]),origin.getY()+r*Math.sin(angles[1])));
		
		MyPoint[] array = {new MyPoint(minX,minY),new MyPoint(maxX,maxY)};
		
		return array;
	
	}
	
	public MyPoint[] otherCircleBounding(){
		if(angles[1]==90.0){
			return quarterCircleBounding();
		}
		
		if(angles[1]==180.0){
			return halfCircleBounding();
		}
		
		return null;
	}
	
}
