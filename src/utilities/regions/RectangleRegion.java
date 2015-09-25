package utilities.regions;

import generalTools.LineIntersector;
import generalTools.LinePointGenerator;
import generalTools.NonUniformPointGenerator;
import generalTools.PointGenerator;
import generalTools.RowPointGenerator;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

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
	
	public MyPoint[] generateRandom(int number){
		/*Generar puntos aleatoriamente*/
		MyPoint[] pointArray = new MyPoint[number];
						
		double w =  getWidth();
		double h =  getHeight();
		Random r = new Random();
		
		for(int i=0;i<number;i++){		
			double x = Math.abs(r.nextInt()) % w + getLeftCorner();
			double y = Math.abs(r.nextInt()) % h + getUpCorner();
			
			pointArray[i] = new MyPoint(x,y);
		}
		
		return pointArray;
	}
	
	public MyPoint[] generateUniformByDistance(double dX, double dY, boolean secondRow){
		int nX = (int)Math.floor(getWidth()/dX)+1;
		int nY = (int)Math.floor(getHeight()/dY)+1;
		
		return generateUniform(dX,dY,nX,nY,secondRow);
	}
	
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow){
		double dX = getWidth()/(nX-1);
		double dY = getHeight()/(nY-1);
		
		return generateUniform(dX,dY,nX,nY,secondRow);
	}
	
	public MyPoint[] generateUniform(double dX, double dY, int nX, int nY, boolean secondRow){
		MyPoint[] pointArray;
						
		if(!secondRow){				
			/*Generar los puntos*/
			pointArray = (new PointGenerator(nX,dX,nY,dY,getUpCorner(),getLeftCorner())).getPoints();		
		}else{
			ArrayList<MyPoint> finalList = new ArrayList<MyPoint>();
						
			for(int i=0; i<nY;i++){
				double yValue = i*dY + getUpCorner(); 
				ArrayList<MyPoint> pointList;
				/*Generar los puntos*/
				if(i%2==0){
					/*Filas pares se comportan normalmente*/
					pointList = (new RowPointGenerator(getLeftCorner(),getRightCorner(),dX,yValue)).getPoints();	
				}else{
					double initX = getLeftCorner() + Math.floor(dX/2);
					double endX = getRightCorner() - Math.floor(dX/2);
					
					pointList = (new RowPointGenerator(initX,endX,dX,yValue)).getPoints();
					/*Al estar desfasada naturalmente no se incluyen los puntos de los bordes, asi que 
					 * hay que ponerlos a mano*/
					
					MyPoint initPoint = new MyPoint(getLeftCorner(),yValue);
					MyPoint endPoint = new MyPoint(getRightCorner(),yValue);
					
					pointList.add(initPoint);
					pointList.add(endPoint);
					
				}
				finalList.addAll(pointList);
			}
			
			pointArray = new MyPoint[finalList.size()];
			pointArray = finalList.toArray(pointArray);				
			
		}
		
		return pointArray;
	}
	
	public MyPoint[] generateBorderByDistance(double distances[], boolean forAll){
		if(!forAll){
			double dX = distances[0];
			double dY = distances[1];
							
			LinePointGenerator lpg = new LinePointGenerator();
			
			MyPoint[] pointArrayLine1 = lpg.getPointsY(getUpCorner(),getDownCorner(),dY,getLeftCorner());
			MyPoint[] pointArrayLine2 = lpg.getPointsX(getLeftCorner(),getRightCorner(),dX,getDownCorner());
			MyPoint[] pointArrayLine3 = lpg.getPointsY(getUpCorner(),getDownCorner(),dY,getRightCorner());
			MyPoint[] pointArrayLine4 = lpg.getPointsX(getLeftCorner(),getRightCorner(),dX,getUpCorner());
		
			MyPoint[] both = ArrayUtils.addAll(pointArrayLine1, pointArrayLine2);
			MyPoint[] both2 = ArrayUtils.addAll(pointArrayLine3,pointArrayLine4);
			MyPoint[] both3 = ArrayUtils.addAll(both,both2);
						
			return both3;
			
		}else{		
			double d1 = distances[0];
			double d2 = distances[1];
			double d3 = distances[2];
			double d4 = distances[3];
												
			LinePointGenerator lpg = new LinePointGenerator();
			
			MyPoint[] pointArrayLine1 = lpg.getPointsY(getUpCorner(),getDownCorner(),d1,getLeftCorner());
			MyPoint[] pointArrayLine2 = lpg.getPointsX(getLeftCorner(),getRightCorner(),d2,getDownCorner());
			MyPoint[] pointArrayLine3 = lpg.getPointsY(getUpCorner(),getDownCorner(),d3,getRightCorner());
			MyPoint[] pointArrayLine4 = lpg.getPointsX(getLeftCorner(),getRightCorner(),d4,getUpCorner());
		
			MyPoint[] both = ArrayUtils.addAll(pointArrayLine1, pointArrayLine2);
			MyPoint[] both2 = ArrayUtils.addAll(pointArrayLine3,pointArrayLine4);
			MyPoint[] both3 = ArrayUtils.addAll(both,both2);
						
			return both3;
		}
	}
	
	public MyPoint[] generateBorderByNumber(int numbers[], boolean forAll){
		if(!forAll){
			double dX = getWidth()/(numbers[0]-1);
			double dY = getHeight()/(numbers[0]-1);
			
			double[] distances = {dX,dY};
			
			return generateBorderByDistance(distances ,forAll);
		}else{
			double d1 = getHeight()/(numbers[0]-1);
			double d2 = getWidth()/(numbers[1]-1);
			double d3 = getHeight()/(numbers[2]-1);
			double d4 = getWidth()/(numbers[3]-1);
			
			double[] distances = {d1,d2,d3,d4};
			
			return generateBorderByDistance(distances ,forAll);
		}
	}
}
