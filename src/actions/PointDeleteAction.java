package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import display.AbstractPanel;
import display.IWindow;

public class PointDeleteAction extends AbstractAction {
	private IWindow window;	
	
	public PointDeleteAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		window.deletePoints();
	}

}
