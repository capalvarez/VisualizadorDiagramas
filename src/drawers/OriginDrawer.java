package drawers;

import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.MyPoint;

public class OriginDrawer {
	private MyPoint origin;
		
	public OriginDrawer(int scale){
		this.origin = origin;
	}
	
	public void setOrigin(MyPoint point){
		origin = point;
	}
	
	public void drawOrigin(JPanel panel){
		JLabel originLabel = new JLabel("(0,0)");
		originLabel.setLocation((int)origin.getX(),(int)origin.getY());	
		panel.add(originLabel);
	}

}
