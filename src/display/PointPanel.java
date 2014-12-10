package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

import utilities.MyPoint;

public class PointPanel extends ICenterPanel{
	private MyPoint[] points;
	private boolean shown = false;
    
	public PointPanel(){

	}
	    
	public void setPointsToPaint(MyPoint[] points){
		this.points = points;
	}
	    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        for (int i = 0; i < points.length; i++) {
        	
            g2d.drawLine(points[i].getX(), points[i].getY(), points[i].getX(), points[i].getY());
        
        }
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
