package utilities;

import generalTools.LineIntersector;

import java.awt.Graphics2D;

public class MyRegion {
	MyPoint upLeftReal;
	MyPoint downRightReal;
	MyPoint upLeftPixel;
	MyPoint downRightPixel;
	MyEdge[] regionSides;
		
	public MyRegion(MyPoint pUL, MyPoint pDR){
		upLeftReal = pUL;
		downRightReal = pDR;
		
		MyPoint left = upLeftReal.getPointLeft(downRightReal);
		MyPoint right = upLeftReal.getPointRight(downRightReal);
		MyPoint up = upLeftReal.getPointUp(downRightReal);
		MyPoint down = upLeftReal.getPointDown(downRightReal);	
		
		MyEdge e1 = new MyEdge(new MyPoint(left.getX(),up.getY()),new MyPoint(left.getX(),down.getY()));
		MyEdge e2 = new MyEdge(new MyPoint(left.getX(),down.getY()),new MyPoint(right.getX(),down.getY()));
		MyEdge e3 = new MyEdge(new MyPoint(right.getX(),up.getY()),new MyPoint(right.getX(),down.getY()));
		MyEdge e4 = new MyEdge(new MyPoint(left.getX(),up.getY()),new MyPoint(right.getX(),up.getY()));	
		
		regionSides = new MyEdge[4];
		regionSides[0] = e1;
		regionSides[1] = e2; 
		regionSides[2] = e3;
		regionSides[3] = e4;	
	}
	
	public void setPixelValues(MyPoint uL, MyPoint dR){
		upLeftPixel = uL;
		downRightPixel = dR;
	}
	
	public void drawRegion(Graphics2D g2d){
        int height = Math.abs(upLeftPixel.getY()- downRightPixel.getY());
        int width = Math.abs(upLeftPixel.getX()- downRightPixel.getX());
       
        g2d.drawRect(upLeftPixel.getX(),upLeftPixel.getY(),width,height);
		
	}
	
	public int getHeight(){
		return Math.abs(upLeftReal.getY() - downRightReal.getY()); 
	}
	
	public int getWidth(){
		return downRightReal.getX() - upLeftReal.getX();
	}
	
	public int getLeftCorner(){
		return upLeftReal.getX();
	}
	
	public int getUpCorner(){
		return upLeftReal.getY();
	}
	
	public MyEdge getIntersection(MyEdge e){
		LineIntersector lI = new LineIntersector(e); 
		
		for(MyEdge regEd: regionSides){
			MyEdge intersect = lI.getIntersection(regEd);
			
			if(intersect!=null){
				return intersect;
			}
		}
		
		return null;		
	}
	
}
