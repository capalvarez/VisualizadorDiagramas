package display;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {
	protected Color color = Color.blue;
	protected int pointSize = 5;
	
	
	public void setColor(Color newColor){
		color = newColor;
	}
	
	public void setBackGroundColor(Color newColor){
		setBackground(newColor);
	}
	
	public int getPointSize(){
		return pointSize;
	}
	
	public void setPointSize(int newPoint){
		pointSize = newPoint;
	}
	
	public void drawCoordinateSystem(){
		
	}

	public abstract void switchDiagram();
	public abstract void switchPoints();
	public abstract void switchRegion();
	
}
