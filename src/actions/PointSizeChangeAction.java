package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import display.IWindow;
import display.dialogs.SliderDialog;

public class PointSizeChangeAction extends AbstractAction {
	private IWindow window;	
	
	public PointSizeChangeAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		SliderDialog input = new SliderDialog(window,window);
		//input.setSize(500, 174);	
	}
	
		
}	
