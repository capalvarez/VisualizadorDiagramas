package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utilities.MyEdge;
import utilities.MyPoint;

public class DiagramPanel extends JPanel {
	
	private MyPoint[] points;
	private MyEdge[] edges;
	private boolean shown = false;
    
    public DiagramPanel(){
    	
    }
	
    public void setLinesToPaint(MyPoint[] points, MyEdge[] edges){
    	this.points = points;
    	this.edges = edges;
    }
    
    private void doDrawing(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

    	g2d.setColor(Color.blue);

    	
    	for(int i=0;i<edges.length;i++){
    		int index1 = edges[i].getIndexFirst();
    		int index2 = edges[i].getIndexSecond();		
    		g2d.drawLine(points[index1].getX(), points[index1].getY(), points[index2].getX(), points[index2].getY());
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
