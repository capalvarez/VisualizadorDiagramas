package display;

import java.awt.Color;

import javax.swing.JFrame;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;

public abstract class IWindow extends JFrame{
	double[] scaleToDraw;
	
	public abstract void drawPointsInPanel(MyPoint[] pointsToDraw,MyPoint[] points);

	public abstract void drawRegionInPanel(MyPoint[] points); 
	
	public abstract void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges);

	public abstract MyPoint[] getCurrentPoints();

	public abstract MyRegion getCurrentRegion();
	
	public abstract void changeColorDiagram(Color color);

	public abstract void changeBackGroundColor(Color color);

	public abstract int getCurrPointSize();
	
	public abstract void changePointSize(int value); 
	
	public void setScaleToDraw(double[] scale){
 		scaleToDraw = scale;
 	}
}
