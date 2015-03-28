package actions;

import generalTools.PointGenerator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;

import display.IWindow;

public class UniformNumPointsAction extends AbstractAction {
	private IWindow window;	
	
	public UniformNumPointsAction(IWindow w){
		window = w;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField numX = new JTextField(5);
		JTextField numY = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Numero de puntos en X:"));
		
		JPanel xPanel = new JPanel();
		xPanel.add(numX);
		inputPanel.add(xPanel);
		
		inputPanel.add(new JLabel("Numero de puntos en Y:"));
		JPanel yPanel = new JPanel();
		yPanel.add(numY);
		inputPanel.add(yPanel);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos uniforme por numero", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			int nX = Integer.parseInt(numX.getText());
			int nY = Integer.parseInt(numY.getText());	
			
			int dX = (int)Math.floor(window.getWidth()/nX);
			int dY = (int)Math.floor(window.getHeight()/nY);
			
			/*Generar los puntos*/
			MyPoint[] pointArray = (new PointGenerator(nX,dX,nY,dY)).getPoints();
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
			
			
		}

	}

}
