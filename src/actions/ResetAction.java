package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import display.AbstractPanel;
import display.IWindow;

public class ResetAction extends AbstractAction {
	private IWindow window;	
	
	public ResetAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractPanel Panel = window.getPanel();
		Panel.switchRegion();
		window.repaint();
		window.validate();

	}

}
