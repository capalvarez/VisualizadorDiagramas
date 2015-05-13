package drawers;

import java.awt.Graphics2D;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyScale;

public class VoronoiDrawer {
	private MyEdge[] edges;
	private MyScale scale;
	
	public VoronoiDrawer(MyEdge[] edges, MyScale scale){
		this.edges = edges;
		this.scale = scale;
	}
	
	public void drawDiagram(Graphics2D g2d){
		/*Dibujar los arcos de Voronoi*/
    	for(int i=0;i<edges.length;i++){
    		MyPoint p1 = edges[i].getFirstPoint();
    		MyPoint p2 = edges[i].getSecondPoint();
    
    		System.out.println(p1);
    		System.out.println(p2);
    		MyPoint pixelP1 = scale.getPixelValue(p1);
    		MyPoint pixelP2 = scale.getPixelValue(p2);
    		
    		g2d.drawLine(pixelP1.getX(), pixelP1.getY(), pixelP2.getX(), pixelP2.getY());
    		
    	}
	}
	

}
