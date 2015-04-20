package actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import utilities.MyPoint;
import utilities.MyScale;
import dataProcessors.PointScaleProcess;
import display.IWindow;

public class CoordinateSystemAction extends AbstractAction {
	private IWindow window;	
	
	public CoordinateSystemAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MyScale scale = window.getScale(); 
		MyPoint newOrigin = new MyPoint(0,0);	
		
		if(scale!=null){
			Dimension size = window.getSize();					
			newOrigin = scale.getPixelValue(newOrigin);
		}
				
		window.drawCoordSysInPanel(newOrigin);
	}
}
