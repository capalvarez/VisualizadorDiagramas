package utilities.regions;

import generalTools.LineIntersector;
import generalTools.LinePointGenerator;
import generalTools.NonUniformPointGenerator;
import generalTools.PointGenerator;
import generalTools.RowPointGenerator;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.PointPair;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.perforations.Perforation;

public class RectangleRegion extends AbstractRegion{
	MyPoint upLeftReal;
	MyPoint downRightReal;
	MyScale scale;
	MyEdge[] regionSides;
				
	public RectangleRegion(MyPoint pUL, MyPoint pDR, MyScale scale){
		upLeftReal = pUL;
		downRightReal = pDR;
		this.scale = scale;
		
		MyEdge e1 = new InternalEdge(new MyPoint(upLeftReal.getX(),upLeftReal.getY()),new MyPoint(upLeftReal.getX(),downRightReal.getY()));
		MyEdge e2 = new InternalEdge(new MyPoint(upLeftReal.getX(),downRightReal.getY()),new MyPoint(downRightReal.getX(),downRightReal.getY()));
		MyEdge e3 = new InternalEdge(new MyPoint(downRightReal.getX(),upLeftReal.getY()),new MyPoint(downRightReal.getX(),downRightReal.getY()));
		MyEdge e4 = new InternalEdge(new MyPoint(upLeftReal.getX(),upLeftReal.getY()),new MyPoint(downRightReal.getX(),upLeftReal.getY()));	
		
		regionSides = new MyEdge[4];
		regionSides[0] = e1;
		regionSides[1] = e2; 
		regionSides[2] = e3;
		regionSides[3] = e4;	
	}
	
	public RectangleRegion(MyPoint[] p, MyScale scale){
		this(p[0],p[1],scale);
	}
	
	public MyPoint[] getPoints(){
		MyPoint p2 = new MyPoint(upLeftReal.getX(),downRightReal.getY());
		MyPoint p4 = new MyPoint(downRightReal.getX(),upLeftReal.getY());
		
		MyPoint[] points = {upLeftReal,p2,downRightReal,p4};
		
		return points;
	}
		
	public MyEdge[] getEdges(){
		MyEdge[] indexEdges = new MyEdge[4];
		
		indexEdges[0] = new InternalEdge(1,2);
		indexEdges[1] = new InternalEdge(2,3);
		indexEdges[2] = new InternalEdge(3,4);
		indexEdges[3] = new InternalEdge(4,1);
		
		return indexEdges;
	}
	
	public void drawRegion(Graphics2D g2d){
		MyPoint upLeftPixel = scale.getPixelValue(upLeftReal);
		MyPoint downRightPixel = scale.getPixelValue(downRightReal);
		
        ArrayList<PointPair> pointsToDraw = initPointPair();
                
        /*Se deben dibujar las perforaciones como corresponden*/
        for(Perforation p: perforation){	
        	if(!processIntersection(pointsToDraw,p)){
        		p.drawPerforation(g2d, scale, null);
        	}else{
        		
        	}
        }	
        
        for(PointPair p: pointsToDraw){
        	p.draw(g2d,scale);
        }
        
	}
	
	private ArrayList<PointPair> initPointPair(){
		ArrayList<PointPair> list = new ArrayList<PointPair>();
		
		list.add(new PointPair(upLeftReal,new MyPoint(upLeftReal.getX(),downRightReal.getY())));
		list.add(new PointPair(new MyPoint(upLeftReal.getX(),downRightReal.getY()),downRightReal));
		list.add(new PointPair(downRightReal,new MyPoint(downRightReal.getX(),upLeftReal.getY())));
		list.add(new PointPair(new MyPoint(downRightReal.getX(),upLeftReal.getY()),upLeftReal));
			
		return list;
	}
	
	public boolean processIntersection(ArrayList<PointPair> list,Perforation p){
		ArrayList<MyPoint> inter = new ArrayList<MyPoint>();
		boolean changed = false;
		int n = list.size();
		
		for(int i=0;i<n;i++){
			inter = list.get(i).intersectionPoint(p);
			
			if(inter.size()==2){
				changed = true;
				
				MyPoint first = list.get(i).getFirst().closestTo(inter);
				PointPair newPair = list.get(i).divide(first);
				inter.remove(first);
				PointPair newPair2 = newPair.divide(inter.get(0));
				
				newPair.setPerforation(p);
				
				list.add(newPair);
				list.add(newPair2);
			}
			
			if(inter.size()==1){
				/*Hay que distinguir entre contacto e interseccion "interrumpida"*/
				PointPair side = list.get(i);
				System.out.println(list.get(i));
				
				if(p.contains(side.getFirst())){
					PointPair newPair = side.divide(inter.get(0));
					newPair.setPerforation(p);
					list.add(newPair);
					System.out.println(inter.get(0));
				}else{
					if(p.contains(side.getSecond())){
						PointPair newPair = side.divide(inter.get(0));
						newPair.setPerforation(p);
						list.add(newPair);
					}
				}
			}
			
		}
		
		return changed;
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
		
		ArrayList<MyPoint> list = generateUniform(dX,dY,nX,nY,secondRow);
		cleanPerforation(list);
		
		return pointListToArray(list);
	}
	
	public MyPoint[] generateUniformByNumber(int nX, int nY, boolean secondRow){
		double dX = getWidth()/(nX-1);
		double dY = getHeight()/(nY-1);
		
		ArrayList<MyPoint> list = generateUniform(dX,dY,nX,nY,secondRow);
		cleanPerforation(list);
		
		return pointListToArray(list);
	}
	
	public ArrayList<MyPoint> generateUniform(double dX, double dY, int nX, int nY, boolean secondRow){
		ArrayList<MyPoint> finalList = new ArrayList<MyPoint>();	
		
		if(!secondRow){				
			/*Generar los puntos*/
			finalList = (new PointGenerator(nX,dX,nY,dY,getUpCorner(),getLeftCorner())).getPoints();		
		}else{
									
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
			
		}
		
		return finalList;
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
