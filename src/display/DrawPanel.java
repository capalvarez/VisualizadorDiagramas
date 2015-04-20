package display;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import drawers.CoordSysDrawer;
import drawers.PointDrawer;
import drawers.VoronoiDrawer;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;

public class DrawPanel extends AbstractPanel{
	private MyPoint[] points;
	private MyRegion region;
	private MyEdge[] edges;
	private MyPoint[] diagramPoints;
	private CoordSysDrawer sysDrawer = new CoordSysDrawer(5);
			
	private boolean shownRegion = false;
	private boolean shownPoints = false;
	private boolean shownDiagram = false;
	private boolean shownCoordSys = false;
    
    public DrawPanel(){
    	
    }
    
    public void setPointsToPaint(MyPoint[] points){
    	this.points = points;
    }
    
    public void setRegion(MyRegion region){
    	this.region = region;
    }
    
    public void setLinesToPaint(MyPoint[] points, MyEdge[] edges){
    	this.diagramPoints = points;
    	this.edges = edges;
    }
    
    private void doDrawingRegion(Graphics g) {	
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        region.drawRegion(g2d);      
    }
    
    private void doDrawingPoints(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        (new PointDrawer(points,pointSize)).drawPoints(g2d);
        region.drawRegion(g2d);    
    }
    
    private void doDrawingDiagram(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
    
        /*Dibujar los arcos de Voronoi*/
    	(new VoronoiDrawer(diagramPoints,edges)).drawDiagram(g2d);
    	    	
    	/*Dibujar los puntos de input*/
    	(new PointDrawer(points,pointSize)).drawPoints(g2d);
    }

    private void doDrawingCoord(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.BLACK);
    	g2d.setStroke(new BasicStroke(5));
    	sysDrawer.drawOrigin(g2d);
    }
    
    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        
        if(shownRegion){
        	doDrawingRegion(g);
        }
        
        if(shownPoints){
         	doDrawingPoints(g);
        }
        
        if(shownDiagram){
        	doDrawingDiagram(g);
        } 
        
        if(shownCoordSys){
        	doDrawingCoord(g);
        }
    }

    public void switchRegion(){
   		shownRegion = !shownRegion;
    }
    
    public void switchPoints(){
   		shownPoints = !shownPoints;
    }
    
    public void switchDiagram(){
   		shownDiagram = !shownDiagram;
    }
	
    public void switchCoordSys(MyPoint origin){
    	sysDrawer.setOrigin(origin);
    	shownCoordSys = !shownCoordSys;
    }
	
	
}
