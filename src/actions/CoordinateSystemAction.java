package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import utilities.MyPoint;
import display.IWindow;

public class CoordinateSystemAction extends AbstractAction {
	private IWindow window;	
	
	public CoordinateSystemAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.drawCoordSysInPanel(new MyPoint(100,100));
	}
}
