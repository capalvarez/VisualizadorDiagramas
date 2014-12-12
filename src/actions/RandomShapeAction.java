package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.AbstractAction;

import utilities.MyPoint;

import display.IWindow;
import display.ShapePanel;

public class RandomShapeAction extends AbstractAction {
	private IWindow window;	
	
	public RandomShapeAction(IWindow w){
		window = w;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Generar dos puntos al azar*/
		Dimension size = window.getSize();
		Insets insets = window.getInsets();
		
		int w =  size.width - insets.left - insets.right;
		int h =  size.height - insets.top - insets.bottom;
		
		Random r = new Random();
		int x1 = Math.abs(r.nextInt()) % w;
		int y1 = Math.abs(r.nextInt()) % h;
		
		int x2 = Math.abs(r.nextInt()) % w;
		int y2 = Math.abs(r.nextInt()) % h;
		
		MyPoint p1 = new MyPoint(x1,y1);
		MyPoint p2 = new MyPoint(x2,y2);
		
		MyPoint right = p1.getPointRight(p2);
		MyPoint left = p1.getPointLeft(p2);
		
		MyPoint[] pointArray = {left,right};
		
		window.drawRegionInPanel(pointArray);
	}

}
