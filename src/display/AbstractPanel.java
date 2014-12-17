package display;

import java.awt.Color;

import javax.swing.JPanel;

public class AbstractPanel extends JPanel {
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
	
	
}
