package display;

import javax.swing.JFrame;

import utilities.MyEdge;
import utilities.MyPoint;

public abstract class IWindow extends JFrame{

	public abstract void drawPointsInPanel(MyPoint[] pointsToDraw,MyPoint[] points);

	public abstract void drawRegionInPanel(MyPoint[] points); 
	
	public abstract void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges);

	public abstract MyPoint[] getCurrentPoints();
}
