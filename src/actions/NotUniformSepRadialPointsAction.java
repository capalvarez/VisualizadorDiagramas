package actions;

import generalTools.CircularPointGenerator;

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

public class NotUniformSepRadialPointsAction extends AbstractAction {
private IWindow window;	
	
	public NotUniformSepRadialPointsAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel center = new JLabel("Punto de inicio");
		JLabel x = new JLabel("X:");
		JLabel y = new JLabel("Y:");
			
		JTextField centerX = new JTextField(5);			
		JTextField centerY = new JTextField(5);
		
		JTextField inRad = new JTextField(5);			
		JTextField mult = new JTextField(5);
		
		JTextField sep = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		
		inputPanel.add(center);
		JPanel xPanel = new JPanel();
		xPanel.add(x);
		xPanel.add(centerX);
		inputPanel.add(xPanel);
		
		JPanel yPanel = new JPanel();
		yPanel.add(y);
		yPanel.add(centerY);
		inputPanel.add(yPanel);
		
		JPanel radPanel = new JPanel();
		
		radPanel.add(new JLabel("Radio inicial:"));
		radPanel.add(inRad);
		inputPanel.add(radPanel);
		
		JPanel multPanel = new JPanel();
		multPanel.add(new JLabel("Multiplicador:"));
		multPanel.add(mult);
		inputPanel.add(multPanel);

		JPanel angPanel = new JPanel();
		angPanel.add(new JLabel("Separacion angular (10° por defecto): "));
		angPanel.add(sep);
		inputPanel.add(angPanel);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos no uniformes", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			double initX = Double.parseDouble(centerX.getText());
			double initY = Double.parseDouble(centerX.getText());		
			
			MyRegion current = window.getCurrentRegion();
		
			double initRad = Double.parseDouble(inRad.getText());
			double mul = Double.parseDouble(mult.getText());
			double separation;
			
			if(!sep.getText().equals("")){
				separation = Double.parseDouble(sep.getText());
			}else{
				separation = 10;
			}
			
			/*Generar los puntos*/
			MyPoint[] pointArray = (new CircularPointGenerator(initX,initY,initRad,mul,separation,current)).getPoints();
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
			
		}
		
	}

}
