package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import display.AbstractPanel;
import display.IWindow;

public class DiagramDeleteAction extends AbstractAction {
	private IWindow window;	
	
	public DiagramDeleteAction(IWindow w){
		window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractPanel Panel = window.getPanel();
		Panel.switchDiagram();
		window.repaint();
		window.validate();
	}

}

