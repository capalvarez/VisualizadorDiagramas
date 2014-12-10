package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

import display.DisplayWindow;
import display.IWindow;

public class ManualPointsAction extends AbstractAction{
	private IWindow window;	
	
	public ManualPointsAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		
		((DisplayWindow)origen).abrirTablaManual();
		((DisplayWindow)origen).repaint();//Importante!
		
	}
	
	

}
