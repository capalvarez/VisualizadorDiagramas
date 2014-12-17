package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

import display.IWindow;

public class ColourChangeAction extends AbstractAction {
	private IWindow window;	
	
	public ColourChangeAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JColorChooser clr = new JColorChooser();
		Color color = clr.showDialog(window, "Choose Color", Color.white);
		window.changeColorDiagram(color);
	}

}
