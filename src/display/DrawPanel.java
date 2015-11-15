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
import utilities.MyScale;
import utilities.MyTriangle;
import utilities.regions.MyRegion;

public class DrawPanel extends AbstractPanel{
	private MyPoint[] points;
	private MyRegion region;
	private MyEdge[] edges;
	private MyScale scale;
	private MyTriangle[] triangles;
	private CoordSysDrawer sysDrawer = new CoordSysDrawer(5);

	private boolean shownRegion = false;
	private boolean shownPoints = false;
	private boolean shownDiagram = false;
	private boolean shownCoordSys = false;
	private boolean shownDelaunay = false;
    
    public DrawPanel(){
    	
    }
    
    public void setPointsToPaint(MyPoint[] points){
    	this.points = points;
    }
    
    public void setRegion(MyRegion region){
    	this.region = region;
    }
    
    public void setLinesToPaint(MyEdge[] edges, MyScale scale){
      	this.edges = edges;
      	this.scale = scale;
    }
    
    public void setTriangles(MyTriangle[] t, MyScale scale){
    	triangles = t;
    	this.scale = scale;
    }
    
    public void setScale(MyScale s){
    	scale = s;
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
    	(new VoronoiDrawer(edges,scale)).drawDiagram(g2d);
    	 
    	 g2d.setColor(Color.RED);
    	/*Dibujar los puntos de input*/
    	(new PointDrawer(points,pointSize)).drawPoints(g2d);
    }

    private void doDrawingCoord(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.BLACK);
    	g2d.setStroke(new BasicStroke(2));
    	sysDrawer.drawOrigin(g2d);
    }
    
    private void doDrawingDelaunay(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(DelaunayColor);
              
        
    	for(int i=0;i<triangles.length;i++){
    		if(triangles[i]!=null){
    			triangles[i].draw(g2d, scale);
    		}	
    	}
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
        
        if(shownDelaunay){
        	doDrawingDelaunay(g);
        }
    }

    public void switchRegion(boolean shown){
   		shownRegion = shown;
    }
    
    public void switchPoints(boolean shown){
   		shownPoints = shown;
    }
    
    public void switchDiagram(boolean shown){
   		shownDiagram = shown;
    }
	
    public void switchCoordSys(MyPoint origin, boolean shown){
    	sysDrawer.setOrigin(origin);
    	shownCoordSys = shown;
    }
	
    public void switchDelaunay(boolean shown){
    	shownDelaunay = shown;
    }
	
}
