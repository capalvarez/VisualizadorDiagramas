package actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.regions.MyRegion;
import utilities.regions.RectangleRegion;

import dataProcessors.PointInitProcess;
import display.IWindow;


public class HeightWeightShapeAction extends AbstractAction {
	private IWindow window;	
	
	public HeightWeightShapeAction(IWindow w){
		window = w;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Pedir input al usuario*/
		JTextField xFieldO = new JTextField(5);
		JTextField yFieldO = new JTextField(5);
		JTextField alto = new JTextField(5);
		JTextField ancho = new JTextField(5);
		
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
				
		JPanel altoPanel = new JPanel();
		altoPanel.add(new JLabel("Altura:"));
		altoPanel.add(alto);
		inputPanel.add(altoPanel);
		
		JPanel anchoPanel = new JPanel();
		altoPanel.add(new JLabel("Ancho:"));
		altoPanel.add(ancho);
		inputPanel.add(anchoPanel);
		
		inputPanel.add(new JLabel("Punto de origen: (0,0) por defecto"));
		
		JPanel oXPanel = new JPanel();
		oXPanel.add(new JLabel("x:"));
		oXPanel.add(xFieldO);
		inputPanel.add(oXPanel);
		
		JPanel oYPanel = new JPanel();
		oYPanel.add(new JLabel("y:"));
		oYPanel.add(yFieldO);
		inputPanel.add(oYPanel);
				
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Región desde altura y ancho", JOptionPane.OK_CANCEL_OPTION);
		
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			/*Chequear si el usuario desea un punto de origen especifico->por ahora asumir buenos usuarios (no va a ingresar
			 * en un solo espacio)*/
			double origenX = 0;
			double origenY = 0;
			
			if(xFieldO.getText().length () != 0){
				origenX = Double.parseDouble(xFieldO.getText());
				origenY = Double.parseDouble(yFieldO.getText());
			}
		
			double newX = origenX + Double.parseDouble(ancho.getText());
			double newY = origenY + Double.parseDouble(alto.getText());
			
			MyPoint origen = new MyPoint(origenX,origenY);
			MyPoint p2 = new MyPoint(newX,newY);
			MyPoint[] pointArray = {origen,p2};
			
			PointInitProcess pip = new PointInitProcess(pointArray,window);
			MyScale scale = pip.getScale();
			
			MyRegion region = new RectangleRegion(origen,p2,scale);
			region.emptyPerforations();
									
			window.drawRegionInPanel(region,scale);
			window.repaint();
		}
		
		

	}

}
