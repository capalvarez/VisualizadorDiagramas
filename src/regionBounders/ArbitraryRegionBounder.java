package regionBounders;

import utilities.MyPoint;

public class ArbitraryRegionBounder {
	private double maxX = Double.MIN_VALUE;
	private double minX = Double.MAX_VALUE;
	private double maxY = Double.MIN_VALUE;
	private double minY = Double.MAX_VALUE;
	
	public ArbitraryRegionBounder(MyPoint[] points){
				
		for(int i=0;i<points.length;i++){
			if(points[i].getX()<minX){
				minX = points[i].getX();
			}
			
			if(points[i].getX()>maxX){
				maxX = points[i].getX();
			}
		
			if(points[i].getY()<minY){
				minY = points[i].getY();
			}
			
			if(points[i].getY()>maxY){
				maxY = points[i].getY();
			}
		}
	}
	
	public MyPoint[] getMinMax(){
		MyPoint[] bounding = {new MyPoint(minX,maxY), new MyPoint(maxX,minY)};
	
		return bounding;
	}
	
	public MyPoint[] getMinMin(){
		MyPoint[] bounding = {new MyPoint(minX,minY), new MyPoint(maxX,maxY)};
		
		return bounding;
	}

}
