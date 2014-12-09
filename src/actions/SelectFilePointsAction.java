package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import display.DisplayWindow;
import display.IWindow;

public class SelectFilePointsAction extends AbstractAction{
	private IWindow window;	
	
	public SelectFilePointsAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent event){
		/*Hacer que el usuario seleccione el archivo deseado*/
	
		
		/*Generar puntos aleatoriamente*/
		
		
		//PointPanel panel = new PointPanel(n,);
		
		//window.dibujar(panel);
		window.repaint();//Importante!
	}		

	
}
