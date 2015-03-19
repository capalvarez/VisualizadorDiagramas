package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

import utilities.MyPoint;
import utilities.MyRegion;

public class PointPanel extends AbstractPanel{
	private MyPoint[] points;
	private MyRegion region;
	private boolean shown = false;
    
	public PointPanel(){

	}
	    
	public void setPointsToPaint(MyPoint[] points, MyRegion region){
		this.points = points;
		this.region = region;
	}
	    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
               
        for (int i = 0; i < points.length; i++) {
        	int x = points[i].getX()-(pointSize/2);
        	int y = points[i].getY()-(pointSize/2);
        	g2d.fillOval(x,y,pointSize,pointSize);   
        }
        
        region.drawRegion(g2d);        
    }

    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        if(shown)
        	doDrawing(g);
    }

    public void switchShown(){
    	shown = !shown;
    }
}
