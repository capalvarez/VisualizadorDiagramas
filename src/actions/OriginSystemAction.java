package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import utilities.MyPoint;
import utilities.MyScale;

import display.IWindow;

public class OriginSystemAction extends AbstractAction {
	private IWindow window;	
	
	public OriginSystemAction(IWindow w){
		window = w;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		MyScale scale = window.getScale();
		MyPoint origin = scale.getPixelValue(new MyPoint(0,0));
		
		window.drawCoordSysInPanel(origin);
	}

}
