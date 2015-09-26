package actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.perforations.MyCircle;
import utilities.perforations.Perforation;
import utilities.regions.MyRegion;
import utilities.regions.RectangleRegion;

import dataProcessors.PointInitProcess;
import display.IWindow;

public class CircularHoleAction extends AbstractAction {
	private IWindow window;	
	
	public CircularHoleAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Pedir input al usuario*/
		JTextField xFieldO = new JTextField(5);
		JTextField yFieldO = new JTextField(5);
		JTextField radius = new JTextField(5);
		
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
				
		JPanel radiusPanel = new JPanel();
		radiusPanel.add(new JLabel("Radio:"));
		radiusPanel.add(radius);
		inputPanel.add(radiusPanel);
		
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
				"Perforacion circular", JOptionPane.OK_CANCEL_OPTION);
		
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
		
			double r = Double.parseDouble(radius.getText());
			
			MyPoint origen = new MyPoint(origenX,origenY);
			Perforation p = new MyCircle(origen,r);
			
			window.getCurrentRegion().addPerforation(p);
			window.repaint();
		}

	}

}
