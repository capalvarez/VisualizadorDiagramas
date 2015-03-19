package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utilities.MyPoint;

public class ShapePanel extends AbstractPanel {
	private MyPoint[] points;
	private boolean shown = false;
    
    public ShapePanel(){
    	
    }
    
    public void setPointsToPaint(MyPoint[] points){
    	this.points = points;
    }
    
    private void doDrawing(Graphics g) {
    	
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);

        int height = Math.abs(points[0].getY()-points[1].getY());
        int width = Math.abs(points[0].getX()-points[1].getX());
        
    	int x = points[0].getX()-(pointSize/2);
    	int y = points[0].getY()-(pointSize/2);
    	g2d.fillOval(x,y,pointSize,pointSize);  
    	
    	int x2 = points[1].getX()-(pointSize/2);
    	int y2 = points[1].getY()-(pointSize/2);
    	g2d.fillOval(x2,y2,pointSize,pointSize); 
        
        g2d.drawRect(points[0].getX(),points[0].getY(),width,height);       
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
