package actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;

import display.IWindow;

public class PolinomicPerturbationAction extends AbstractAction {
	private IWindow window;	
	
	public PolinomicPerturbationAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Pedir input al usuario*/
		JTextField grade = new JTextField(5);
		JTextField coefs = new JTextField(5);
				
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);

		JPanel gradePanel = new JPanel();
		gradePanel.add(new JLabel("Grado del polinomio:"));
		gradePanel.add(grade);
		inputPanel.add(gradePanel);
		
		inputPanel.add(new JLabel("Coefs separados por espacio:"));
		inputPanel.add(coefs);
				
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Perturbacion polinomica", JOptionPane.OK_CANCEL_OPTION);
		
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			/*Chequear si el usuario desea un punto de origen especifico->por ahora asumir buenos usuarios (no va a ingresar
			 * en un solo espacio)*/
			int g  = Integer.parseInt(grade.getText());
			String[] c = (coefs.getText()).split("\\s+");
			double[] a = new double[c.length];
			
			for(int i=0;i<c.length;i++){
				a[i] = Double.parseDouble(c[i]);
			}		
			
			MyPoint[] points = window.getCurrentPoints();
			
			for(int i=0;i<points.length;i++){				
				//points[i].add(0,yVal);
			}
			window.drawPointsInPanel(points, points);
			window.repaint();
		}
	}

}
