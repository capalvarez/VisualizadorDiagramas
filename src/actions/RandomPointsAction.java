package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.*;
import display.DisplayWindow;
import display.IWindow;

public class RandomPointsAction extends AbstractAction{
	private IWindow window;	
	
	public RandomPointsAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent event){
		/*Preguntar al usuario cuantos puntos quiere*/
		String number = (String)JOptionPane.showInputDialog(window,
                	"Ingrese numero de puntos a generar:",
                	"Generación aleatoria", JOptionPane.QUESTION_MESSAGE, null, null, null);
		int n = Integer.parseInt(number);
		
		/*Generar puntos aleatoriamente*/
		
		
		//PointPanel panel = new PointPanel(n,);
		
		//window.dibujar(panel);
		window.repaint();//Importante!
	}	
}
