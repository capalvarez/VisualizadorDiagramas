package actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import display.DisplayWindow;
import display.IWindow;

public class ManualPointsAction extends AbstractAction{
	private IWindow window;	
	
	public ManualPointsAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent event) {
		/*Pedir input al usuario*/
		JTable table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		JScrollPane scrollpane = new JScrollPane(table);
	    window.add(scrollpane);
		
		//window.drawDiagramInPanel(pointsArray);
		window.repaint();
		
	}
	
	

}
