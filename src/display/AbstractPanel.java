package display;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {
	protected Color color = Color.blue;
	protected Color DelaunayColor = Color.green;
	
	protected int pointSize = 5;
	
	
	public void setColor(Color newColor){
		color = newColor;
	}
	
	public void setDelaunayColor(Color newColor){
		DelaunayColor = newColor;
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

	public abstract void switchDiagram(boolean shown);
	public abstract void switchPoints(boolean shown);
	public abstract void switchRegion(boolean shown);
	public abstract void switchDelaunay(boolean shown);
	
}
