package dataProcessors;

import utilities.MyPoint;

public class CircleBoundingProcess {
	MyPoint origin;
	double r;
	double[] angles;
	
	public CircleBoundingProcess(MyPoint origin, double radius, double[] angles){
		this.origin = origin;
		r = radius;
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
	
	public MyPoint[] otherCircleBounding(){
		double minX = Math.min(origin.getX(), Math.min(origin.getX()-r*Math.cos(angles[0]),origin.getX()+r*Math.cos(angles[1])));
		double maxX = Math.max(origin.getX(), Math.max(origin.getX()-r*Math.cos(angles[0]),origin.getX()+r*Math.cos(angles[1])));
		
		double minY = Math.min(origin.getY(), Math.min(origin.getY()-r*Math.sin(angles[0]),origin.getY()+r*Math.sin(angles[1])));
		double maxY = Math.max(origin.getY(), Math.max(origin.getY()-r*Math.sin(angles[0]),origin.getY()+r*Math.sin(angles[1])));
		
		MyPoint[] array = {new MyPoint(minX,minY),new MyPoint(maxX,maxY)};
		
		return array;
	
	}
	
}
