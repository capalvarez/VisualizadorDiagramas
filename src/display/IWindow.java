package display;

import javax.swing.JFrame;

import utilities.MyPoint;

public abstract class IWindow extends JFrame{

	public abstract void drawPointsInPanel(MyPoint[] points); 
}
