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
    private boolean shown = false;
	private MyPoint[] points;
	private int nPoints;
    
    /*Recibe el numero de puntos a dibujar y las coordenadas*/
    public PointPanel(int n,MyPoint[] points){
    	this.points = points;
    	this.nPoints = n; 	
    }
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        for (int i = 0; i < nPoints; i++) {
        	
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
