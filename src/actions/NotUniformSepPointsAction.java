package actions;

import generalTools.NonUniformPointGenerator;
import generalTools.PointGenerator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyRegion;
import display.IWindow;

public class NotUniformSepPointsAction extends AbstractAction {
	private IWindow window;	
	
	public NotUniformSepPointsAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel x = new JLabel("X:");
		JLabel y = new JLabel("Y:");
		
		JLabel sep = new JLabel("Separación inicial:");
		JLabel mul = new JLabel("Multiplicador:");
		
		JTextField inX = new JTextField(5);
		JTextField inY = new JTextField(5);
		
		JTextField muX = new JTextField(5);
		JTextField muY = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		
		inputPanel.add(x);
		JPanel xPanel1 = new JPanel();
		xPanel1.add(new JLabel("Separación inicial:"));
		xPanel1.add(inX);
		inputPanel.add(xPanel1);
		
		JPanel xPanel2 = new JPanel();
		xPanel2.add(new JLabel("Multiplicador:"));
		xPanel2.add(muX);
		inputPanel.add(xPanel2);
		
		inputPanel.add(y);
		JPanel yPanel1 = new JPanel();
		yPanel1.add(new JLabel("Separación inicial:"));
		yPanel1.add(inY);
		inputPanel.add(yPanel1);
		
		JPanel yPanel2 = new JPanel();
		yPanel2.add(new JLabel("Multiplicador:"));
		yPanel2.add(muY);
		inputPanel.add(yPanel2);

		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos no uniformes", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			double initX = Double.parseDouble(inX.getText());
			double initY = Double.parseDouble(inY.getText());		
			
			MyRegion current = window.getCurrentRegion();
		
			double multX = Double.parseDouble(muX.getText());
			double multY = Double.parseDouble(muX.getText());			
			
			/*Generar los puntos*/
			MyPoint[] pointArray = (new NonUniformPointGenerator(initX,multX,initY,multY,current.getWidth(),current.getHeight())).getPoints();
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
			
		}
		
	}


}
