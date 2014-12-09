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
        
        
        /*Dibujado con lineas
        for (int i = 0; i < nPoints - 1; i++) {
        	g2d.drawLine(points[i].getX(), points[i].getY(), points[i+1].getX(), points[i+1].getY());
        }
        Cerrar la figura
        g2d.drawLine(points[0].getX(), points[0].getY(), points[nPoints-1].getX(), points[nPoints-1].getY());*/
        
    }

    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        System.out.print("oooo");
        if(shown)
        	doDrawing(g);
    }

    public void switchShown(){
    	if(!shown)
    		shown = !shown;
    }
}
