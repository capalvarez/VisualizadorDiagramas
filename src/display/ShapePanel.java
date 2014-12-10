package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utilities.MyPoint;

public class ShapePanel extends JPanel {
	private MyPoint[] points;
	private boolean shown = false;
    
    public ShapePanel(){
    	
    }
    
    public void setPointsToPaint(MyPoint[] points){
    	this.points = points;
    }
    
    private void doDrawing(Graphics g) {
    	
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        int height = Math.abs(points[0].getY()-points[1].getY());
        int width = Math.abs(points[0].getX()-points[1].getX());			
        
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
