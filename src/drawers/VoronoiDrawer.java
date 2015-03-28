package drawers;

import java.awt.Graphics2D;

import utilities.MyEdge;
import utilities.MyPoint;

public class VoronoiDrawer {
	private MyPoint[] voronoiPoints;
	private MyEdge[] edges;
	
	public VoronoiDrawer(MyPoint[] points, MyEdge[] edges){
		this.edges = edges;
		this.voronoiPoints = points;	
	}
	
	public void drawDiagram(Graphics2D g2d){
		/*Dibujar los arcos de Voronoi*/
    	for(int i=0;i<edges.length;i++){
    		int index1 = edges[i].getIndexFirst();
    		int index2 = edges[i].getIndexSecond();
    		
    		if(index1!=0 && index2!=0){
    			/*Caso en que se dibuja entre vertices "reales"*/ 
    			g2d.drawLine(voronoiPoints[index1].getX(), voronoiPoints[index1].getY(), voronoiPoints[index2].getX(), voronoiPoints[index2].getY());
    		}else{
    			/*Caso en que se debe dibujar hasta el infinito*/
    			g2d.drawLine(voronoiPoints[index1].getX(), voronoiPoints[index1].getY(),
    					voronoiPoints[index1].getX(), voronoiPoints[index1].getY()+voronoiPoints[index1].getInfinityY());
    			
    			g2d.drawLine(voronoiPoints[index1].getX(), voronoiPoints[index1].getY(), 
    					voronoiPoints[index1].getX()+voronoiPoints[index1].getInfinityX(), voronoiPoints[index1].getY());
    		}    		
    	}
	}
	

}
