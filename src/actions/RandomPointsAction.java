package actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.*;

import utilities.*;
import utilities.regions.MyRegion;
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
		
		/*Verificar que el usuario haya ingresado un numero valido (o haya ingresado algo)*/
		if(number==null){
			return;
		}
		
		int n = Integer.parseInt(number);
		MyPoint[] pointArray = window.getCurrentRegion().generateRandom(n);
			
		window.drawPointsInPanel(pointArray,pointArray);
		window.repaint();
	}	
}
