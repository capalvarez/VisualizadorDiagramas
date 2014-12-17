package display;

import java.awt.Color;

import javax.swing.JPanel;

public class AbstractPanel extends JPanel {
	protected Color color = Color.blue;
		
	public void setColor(Color newColor){
		color = newColor;
	}
	
	public void setBackGroundColor(Color newColor){
		setBackground(newColor);
	}
	
}
