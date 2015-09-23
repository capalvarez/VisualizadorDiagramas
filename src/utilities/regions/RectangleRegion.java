package utilities.regions;

import generalTools.LineIntersector;
import generalTools.NonUniformPointGenerator;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.perforations.Perforation;

public class RectangleRegion implements MyRegion{
	MyPoint upLeftReal;
	MyPoint downRightReal;
	MyPoint upLeftPixel;
	MyPoint downRightPixel;
	MyEdge[] regionSides;
	ArrayList<Perforation> perforation = new ArrayList<Perforation>();
			
	public RectangleRegion(MyPoint pUL, MyPoint pDR){
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
	
	public void setPixelValues(MyPoint[] p){
		upLeftPixel = p[0];
		downRightPixel = p[1];
	}
	
	public MyPoint[] getPoints(){
		MyPoint p2 = new MyPoint(upLeftReal.getX(),downRightReal.getY());
		MyPoint p4 = new MyPoint(downRightReal.getX(),upLeftReal.getY());
		
		MyPoint[] points = {upLeftReal,p2,downRightReal,p4};
		
		return points;
	}
	
	
	public void drawRegion(Graphics2D g2d){
        double height = Math.abs(upLeftPixel.getY()- downRightPixel.getY());
        double width = Math.abs(upLeftPixel.getX()- downRightPixel.getX());
       
        g2d.draw(new Rectangle2D.Double(upLeftPixel.getX(),upLeftPixel.getY(),width,height));
		
	}
	
	public double getHeight(){
		return Math.abs(upLeftReal.getY() - downRightReal.getY()); 
	}
	
	public double getWidth(){
		return downRightReal.getX() - upLeftReal.getX();
	}
	
	public double getLeftCorner(){
		return upLeftReal.getX();
	}
	
	public double getUpCorner(){
		return upLeftReal.getY();
	}
	
	public double getRightCorner(){
		return downRightReal.getX();
	}
	
	public double getDownCorner(){
		return downRightReal.getY();
	}
	
	@Override
	public String toString(){
		return upLeftReal.toString() + " " + downRightReal.toString(); 
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
	
	public boolean isInside(MyPoint p){
		return p.getX()<=getRightCorner() && p.getY()>=getUpCorner() && 
			   p.getX()>=getLeftCorner() && p.getY()<=getDownCorner();
	}
	
	public MyPoint[] generateNonUniform(double initX, double multX, double initY, double multY){
		return (new NonUniformPointGenerator(initX,multX,initY,multY,getWidth(),getHeight())).getPoints();
	}
	
}
