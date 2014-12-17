package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utilities.MyEdge;
import utilities.MyPoint;

public class DiagramPanel extends AbstractPanel {
	private MyPoint[] points;
	private MyEdge[] edges;
	private MyPoint[] pointsToDraw;
	private boolean shown = false;
    	
    public DiagramPanel(){
    	
    }
	
    public void setLinesToPaint(MyPoint[] points, MyEdge[] edges){
    	this.points = points;
    	this.edges = edges;
    }
    
    public void setPointsToPaint(MyPoint[] points){
    	this.pointsToDraw = points;
    }
        
    private void doDrawing(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

    	g2d.setColor(color);
    	
    	/*Dibujar los arcos de Voronoi*/
    	for(int i=0;i<edges.length;i++){
    		int index1 = edges[i].getIndexFirst();
    		int index2 = edges[i].getIndexSecond();
    		
    		if(index1!=0 && index2!=0){
    			/*Caso en que se dibuja entre vertices "reales"*/ 
    			g2d.drawLine(points[index1].getX(), points[index1].getY(), points[index2].getX(), points[index2].getY());
    		}else{
    			/*Caso en que se debe dibujar hasta el infinito*/
    			g2d.drawLine(points[index1].getX(), points[index1].getY(),
    						 points[index1].getX(), points[index1].getY()+points[index1].getInfinityY());
    			
    			g2d.drawLine(points[index1].getX(), points[index1].getY(), 
    					     points[index1].getX()+points[index1].getInfinityX(), points[index1].getY());
    		}    		
    	}
    	
    	int r = 5;
    	
    	/*Dibujar los puntos de input*/
    	for(int i=0;i<pointsToDraw.length;i++){
    		int x = pointsToDraw[i].getX()-(r/2);
        	int y = pointsToDraw[i].getY()-(r/2);
        	g2d.fillOval(x,y,r,r);  
    	}
    	
    }

    @Override
    public void paintComponent(Graphics g) {   
    	super.paintComponent(g);
    	if(shown)
    		doDrawing(g);
    }

    public void switchShown(){
    	if(!shown)
    		shown = !shown;
    }
}
