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
		
		MyEdge e1 = new MyEdge(new MyPoint(upLeftReal.getX(),upLeftReal.getY()),new MyPoint(upLeftReal.getX(),downRightReal.getY()));
		MyEdge e2 = new MyEdge(new MyPoint(upLeftReal.getX(),downRightReal.getY()),new MyPoint(downRightReal.getX(),downRightReal.getY()));
		MyEdge e3 = new MyEdge(new MyPoint(downRightReal.getX(),upLeftReal.getY()),new MyPoint(downRightReal.getX(),downRightReal.getY()));
		MyEdge e4 = new MyEdge(new MyPoint(upLeftReal.getX(),upLeftReal.getY()),new MyPoint(downRightReal.getX(),upLeftReal.getY()));	
		
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
	
	public MyPoint[] getPoints(){
		MyPoint p2 = new MyPoint(upLeftReal.getX(),downRightReal.getY());
		MyPoint p4 = new MyPoint(downRightReal.getX(),upLeftReal.getY());
		
		MyPoint[] points = {upLeftReal,p2,downRightReal,p4};
		
		return points;
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
	
	public MyPoint getIntersection(MyEdge e){
		LineIntersector lI = new LineIntersector(e); 
		
		for(MyEdge regEd: regionSides){
			MyPoint intersect = lI.getIntersection(regEd);
			
			if(intersect!=null){
				return intersect;
			}
		}
		
		return null;		
	}
	
	public boolean contains(MyPoint p){
		int i;
		int j;
		
		MyPoint[] points = getPoints();
				
		/*for (i = 0, j = points.length - 1; i < points.length; j = i++) {			
			if ((points[i].getY() > p.getY()) != (points[j].getY() > p.getY() && 
				(p.getX() < (points[j].getX() - points[i].getX())* (p.getY() - points[i].getY())
							/ (points[j].getY() - points[i].getY()) + points[i].getX()))) {
				result = !result;
			}
		}*/
		
		if(p.getX()>upLeftReal.getX() && p.getX()<downRightReal.getX() &&
		   p.getY()<upLeftReal.getY() && p.getX()>downRightReal.getY()){
			return true;
		}
		
		return false;
	}
	
	public int getInsidePoint(MyEdge e){
		if(contains(e.getFirstPoint())){
			return 0;
		}else{
			return 1;
		}
	}
	
}
