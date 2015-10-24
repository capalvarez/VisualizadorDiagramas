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
import utilities.regions.MyRegion;
import utilities.regions.RectangleRegion;

import dataProcessors.PointInitProcess;
import display.IWindow;

public class SinusoidPerturbationAction extends AbstractAction {
	private IWindow window;	
	
	public SinusoidPerturbationAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Pedir input al usuario*/
		JTextField a = new JTextField(5);
		JTextField b = new JTextField(5);
		JTextField wCos = new JTextField(5);
		JTextField wSin = new JTextField(5);
		
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		
		inputPanel.add(new JLabel("Coseno:"));
		
		JPanel cosAPanel = new JPanel();
		cosAPanel.add(new JLabel("Amplitud:"));
		cosAPanel.add(a);
		inputPanel.add(cosAPanel);
		
		JPanel cosFPanel = new JPanel();
		cosFPanel.add(new JLabel("Frecuencia:"));
		cosFPanel.add(wCos);
		inputPanel.add(cosFPanel);
		
		inputPanel.add(new JLabel("Seno:"));
		
		JPanel sinAPanel = new JPanel();
		sinAPanel.add(new JLabel("Amplitud:"));
		sinAPanel.add(b);
		inputPanel.add(sinAPanel);
		
		JPanel sinFPanel = new JPanel();
		sinFPanel.add(new JLabel("Frecuencia:"));
		sinFPanel.add(wSin);
		inputPanel.add(sinFPanel);
	
				
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Región desde altura y ancho", JOptionPane.OK_CANCEL_OPTION);
		
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			/*Chequear si el usuario desea un punto de origen especifico->por ahora asumir buenos usuarios (no va a ingresar
			 * en un solo espacio)*/
			double amC = 0;
			double frC = 0;
			double amS = 0;
			double frS = 0;
						
			if(a.getText().length () != 0){
				amC = Double.parseDouble(a.getText());
				frC = Double.parseDouble(wCos.getText());
			}
			
			if(b.getText().length () != 0){
				amS = Double.parseDouble(b.getText());
				frS = Double.parseDouble(wSin.getText());
			}
		
			MyPoint[] points = window.getCurrentPoints();
			
			for(int i=0;i<points.length;i++){
				double xVal = amC*Math.cos(frC*points[i].getX()) + amS*Math.sin(frS*points[i].getX());
				double yVal = amC*Math.cos(frC*points[i].getY()) + amS*Math.sin(frS*points[i].getY());
				
				points[i].add(xVal,yVal);
			}
			window.drawPointsInPanel(points, points);
			window.repaint();
		}
	}

}
